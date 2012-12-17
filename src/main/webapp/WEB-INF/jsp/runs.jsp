<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>
<h2>Run List:</h2>

<table>
	<tr>
		<th></th>
		<th>Project</th>
		<th>Date</th>
		<th>Note</th>
	</tr>

	<c:forEach items="${runList}" var="run">
		<tr>
			<td><a class="button"
				href="<c:url value="/runDetails.htm"><c:param name="runId" value="${run.runId}"/></c:url>">
					<span>View</span>
			</a></td>
			<td><c:out value="${run.project.name}" /></td>
			<td><c:out value="${run.runDate}" /></td>
			<td><c:out value="${run.note}" /></td>
		</tr>
	</c:forEach>

</table>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>