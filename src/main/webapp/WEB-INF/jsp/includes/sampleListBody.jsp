
<table class="table table-bordered table-condensed">
	<tr class="info">
		<td class="input-small">
			<b>Internal ID</b>
		</td>
		<td class="input-small">
			<b>Sample Type</b>
		</td>
		<td class="input-medium">
			<b>External ID</b>
		</td>
		<td class="input-medium">
			<b>Project</b>
		</td>
		<td class="input-small">
			<b>Birth Date </b><br> <span class="small-list-details">DD-MM-YYYY</span>
		</td>
		<td class="input-small">
			<b>Rec'd Date </b><br> <span class="small-list-details">DD-MM-YYYY</span>
		</td>
		<td>
			<b>Note</b>
		</td>
	</tr>

	<c:forEach items="${sampleList}" var="sample" varStatus="sampleCounter">
		<tr>
			<c:if test="${sample.sampleId != -1}">

				<td>
<%-- 					<input checked type="checkbox" name="chk<c:out value="${sampleCounter.index}" />" value="<c:out value="${sample.patient.intSampleId}"/>" /> --%>
					<input type="checkbox" name="chk<c:out value="${sampleCounter.index}" />" checked/>					
					<a class="act act-primary"
						href="<c:url value="/patientDetails.htm">
							<c:param name="intSampleId" value="${sample.patient.intSampleId}"/></c:url>">
						<c:out value="${sample.patient.intSampleId}" />
					</a>
				</td>
				<td>
					<a class="act act-info"
						href="<c:url value="/sampleDetails.htm">
    					<c:param name="sampleId" value="${sample.sampleId}"/></c:url>"><c:out
							value="${sample.sampleType.suffix}-${sample.sampleDupNo}" /> </a>
				</td>
				<td>
					<c:out value="${sample.patient.extSampleId}" />
				</td>
				
				<td>
					<c:out value="${sample.patient.project.name}" />
				</td>
				<td>
					<fmt:formatDate value="${sample.patient.birthDate}" pattern="dd-MM-yyyy" />
				</td>
				<td>
					<fmt:formatDate value="${sample.receiveDate}" pattern="dd-MM-yyyy" />
				</td>
				<td>
					<c:out value="${sample.notes}" />
				</td>
			</c:if>

		</tr>
	</c:forEach>
</table>