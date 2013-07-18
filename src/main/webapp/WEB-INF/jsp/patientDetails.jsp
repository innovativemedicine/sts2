<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>
	Patient:
	<c:out value="${command.intSampleId}" />
</h2>

<h3>Patient Information</h3>
<a class="btn"
	href="<c:url value="/editPatient.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>">Edit
	Patient</a>
<%--    		<a class="btn" href="<c:url value="/deletePatient.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>" onclick="return (confirm('Warning: Deleting the patient will delete all the samples of this patient as well as all the genotype results of those samples. \n\nAre you sure you want to delete this Patient?')) " >Delete Patient</a> --%>
<p>
<div class="row-fluid">
	<table class="table table-bordered">

		<tr class="info">
			<td class="span2"><b>External ID 1</b></td>
			<td class="span2"><b>External ID 2</b></td>
			<td class="span3"><b>Project </b></td>
			<td class="span2"><b>Birth Date</b><br>(YYYY-MM-DD)</td>
			<td class="span3"><b>Note </b></td>
		</tr>
		<tr>
			<td><c:out value="${command.extSampleId}" /></td>
			<td><c:out value="${command.anotherExtSampleId}" /></td>
			<td><c:out value="${command.project.investigator.firstName}" />
				<c:out value="${command.project.name}" /></td>
			<td><fmt:formatDate value="${command.birthDate}"
					pattern="yyyy-MM-dd" /></td>
			<td><c:out value="${command.note}" /></td>
		</tr>
	</table>
</div>

<h3>Sample List</h3>

<table class="table table-bordered">
	<tr class="info">
		<td class="span2"><b>Sample Type</b></td>
		<td class="span1"><b>Dupe #</b></td>
		<td class="span2"><b>Received Date</b></td>
		<td class="span1"><b>Concentration</b></td>
		<td class="span2"><b>Status</b></td>
		<td class="span3"><b>Notes</b></td>
	</tr>

	<c:forEach items="${command.samples}" var="sample">
		<tr>
			<td><a class="act act-primary"
				href="<c:url value="/sampleDetails.htm">
			<c:param name="sampleId" value="${sample.sampleId}"/></c:url>"><c:out
						value="${sample.sampleType.name}" /> </a></td>
			<td><c:out value="${sample.sampleDupNo}" /></td>
			<td><c:out value="${sample.receiveDate}" /></td>
			<td><c:out value="${sample.od}" /></td>
			<td><c:out value="${sample.status}" /></td>
			<td><c:out value="${sample.notes}" /></td>

		</tr>
	</c:forEach>

</table>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>