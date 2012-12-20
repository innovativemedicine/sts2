<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<table>
	<tr style="vertical-align: top">
		<td>
			<h3>Samples:</h3> <select multiple size="25" name="sampleToAdd">
				<c:forEach items="${sampleList}" var="sample">
					<option value="${sample.sampleId}">
						<c:out value="${sample.patient.intSampleId}" />
						<c:out value="${sample.sampleType.suffix}" />
					</option>
				</c:forEach>
		</select>

		</td>
		<td>
			<h3>Container:</h3> <select class="selectNav" name="container">
				<option value="">--- Select Container ---</option>
				<c:forEach items="${containerList}" var="aContainer">
					<option
						<c:if test="${command.containerId != null && command.containerId eq aContainer.containerId}">
						selected
					</c:if>
						value="?containerId=<c:out value="${aContainer.containerId}"/>">
						<c:out value="${aContainer.name}" />
					</option>

				</c:forEach>
		</select> <c:if test="${!command.noneContainer}">
				<c:if test="${command.emptyContainer}">
					<p>
						<a class="button"
							href="<c:url value="/addSample2Container.htm"><c:param name="containerId" value="${command.containerId}"/><c:param name="isOrdered" value="ordered"/></c:url>">
							<span>Edit Content</span></a>
				</c:if>

				<c:if test="${!command.emptyContainer}">
					<%@ include file="/WEB-INF/jsp/includes/containerContentsBody.jsp"%>
				</c:if>

			</c:if>
		</td>
	</tr>
</table>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>