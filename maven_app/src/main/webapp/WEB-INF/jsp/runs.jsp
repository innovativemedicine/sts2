<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Run List:</h2>

<table class="table">
	<tr class="info">
		<td><b>Project</b></td>
		<td><b>Date</b></td>
		<td><b>Note</b></td>
	</tr>

	<c:forEach items="${runList}" var="run">
		<tr>
			<td><div class="form-inline"><a class="btn"
				href="<c:url value="/runDetails.htm"><c:param name="runId" value="${run.runId}"/></c:url>">
				View
			</a> <c:out value="${run.project.name}" /></div></td>
			<td style="width: 100px"><fmt:formatDate pattern="yyyy-MM-dd"
					value="${run.runDate}" /></td>
			<td><c:out value="${run.note}" /></td>
		</tr>
	</c:forEach>

</table>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>