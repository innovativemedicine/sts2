<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<form method="post">

	<h2>Sample List</h2>

	<a class="btn" href="<c:url value="/addSample2Container.htm"></c:url>">Assign
		Container</a> <input class="btn" type="submit" name="action"
		value="Export Data"> <input class="btn" type="submit"
		name="action" value="Print Labels">

</form>
<div class="row">
	<div class="span12">
		<p>
		<table class="table table-bordered table-condensed small-list">
			<tr class="info">
				<td class="span1"><b>Internal ID</b></td>
				<td class="span1"><b>External ID</b></td>
				<td class="span1"><b>Sample Type</b></td>
				<td class="span2"><b>Project</b></td>
				<td class="span1"><b>Birth<br>Date</b><br><span class="small-list-details">YYYY-MM-DD</span></td>
				<td class="span1"><b>Received<br> Date</b><br><span class="small-list-details">YYYY-MM-DD</span></td>
				<td class="span2"><b>Note</b></td>
				<td class="span1"><b>Status</b></td>
				<td class="span1"><b>Location</b></td>
			</tr>

			<c:forEach items="${sampleList}" var="sample">
				<tr>
					<c:if test="${sample.sampleId != -1}">

						<td><a
							href="<c:url value="/sampleDetails.htm">
    		<c:param name="sampleId" value="${sample.sampleId}"/></c:url>">
								<c:out value="${sample.patient.intSampleId}" />
						</a></td>
						<td><c:out value="${sample.patient.extSampleId}" /></td>
						<td><c:out value="${sample.sampleType.suffix}-${sample.sampleDupNo}" /></td>
						<td><c:out value="${sample.patient.project.name}" /></td>
						<td><fmt:formatDate value="${sample.patient.birthDate}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${sample.receiveDate}"
								pattern="yyyy-MM-dd" /></td>
						<td><c:out value="${sample.notes}" /></td>
						<td><c:out value="${sample.status}" /></td>
						<td></td>
					</c:if>

				</tr>
			</c:forEach>

		</table>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>