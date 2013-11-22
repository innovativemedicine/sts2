
<table class="table table-bordered table-condensed">
	<tr class="info">
		<td class="input-large">
			<b>Internal ID</b>
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
		<td class="input-mini">
			<b>Conc.<br> (ng/uL)</b>
		</td>
	</tr>

	<c:forEach items="${sampleList}" var="sample" varStatus="sampleCounter">
		<tr>
			<c:if test="${sample.sampleId != -1}">

				<td>
					<input type="checkbox" name="chk<c:out value="${sampleCounter.index}" />" checked />
					<a class="act act-primary"
						href="<c:url value="/sampleDetails.htm">
							<c:param name="intSampleId" value="${sample.patient.intSampleId}"/></c:url>"> <c:out
							value="${sample.patient.intSampleId}" /> <a class="act act-info"
						href="<c:url value="/sampleDetails.htm">
    					<c:param name="sampleId" value="${sample.sampleId}"/></c:url>">(<c:out
								value="${sample.sampleType.suffix}-${sample.sampleDupNo}" />) </a>
					</a>
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
					<fmt:formatDate value="${sample.patient.receiveDate}" pattern="dd-MM-yyyy" />
				</td>
				<td>
					<c:out value="${sample.notes}" />
				</td>
				<td>
					<c:out value="${sample.od}" />
				</td>
				
			</c:if>

		</tr>
	</c:forEach>
</table>