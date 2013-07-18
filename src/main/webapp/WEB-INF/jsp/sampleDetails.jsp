<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2 class="noMargin">Sample Details</h2>
<a class="btn"
	href='<c:url value="/editSample.htm"><c:param name="sampleId" value="${command.sampleId}"/></c:url>'>
	<span>Edit Sample</span>
</a>
<a class="btn"
	href='<c:url value="/deleteSample.htm"><c:param name="sampleId" value="${command.sampleId}"/></c:url>'
	onclick="return (confirm('Warning: Deleting the sample will delete it from all the containers!\n\nAre you sure you want to delete this sample?')) ">
	<span>Delete Sample</span>
</a>
<p>
<div class="row-fluid">
	<div class="span4">
		<table class="table">
			<tr class="info">
				<td colspan="2"><b>Sample Information</b></td>
			</tr>
			<tr>
				<td>Internal Id:</td>
				<td><c:out value="${command.patient.intSampleId}" /></td>
			</tr>
			<tr>
				<td>Sample Type:</td>
				<td><c:out value="${command.sampleType.name}" /></td>
			</tr>
			<tr>
				<td>Received on (YYYY-MM-DD):</td>
				<td><fmt:formatDate value="${command.receiveDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td>Duplication Number:</td>
				<td><c:out value="${command.sampleDupNo}" /></td>
			</tr>

			<tr>
				<td>Notes:</td>
				<td><c:out value="${command.notes}" /></td>
			</tr>

			<tr>
				<td>Status:</td>
				<td><c:out value="${command.status}" /></td>
			</tr>
			<tr>
				<td colspan="2"></td>
			</tr>
		</table>
	</div>
	<div class="span4">
		<table class="table">
			<tr class="info">
				<td colspan="2"><b><a
					href='<c:url value="/editPatient.htm"><c:param name="intSampleId" value="${command.patient.intSampleId}"/></c:url>'>
						Patient information </a></b></td>
			</tr>
			<tr>
				<td>External ID:</td>
				<td><c:out value="${command.patient.extSampleId}" /></td>
			</tr>
			<tr>
				<td>External ID 2:</td>
				<td><c:out value="${command.patient.anotherExtSampleId}" /></td>
			</tr>
			<tr>
				<td>Birthdate</td>
				<td><fmt:formatDate value="${command.patient.birthDate}"
						pattern="dd-MM-yyyy" /></td>
			</tr>
			<tr>
				<td>Note</td>
				<td><c:out value="${command.patient.note}" /></td>
			</tr>
		</table>
	</div>
	<div class="span4">
		<table class="table">
			<tr class="info">
				<td colspan="2"><b>Sample Concentration</b></td>
			</tr>
			<tr>
				<td>Concentration:</td>
				<td><c:out value="${command.od}" /></td>
			</tr>
			<tr>
				<td>Conc. Reading On:</td>
				<td><c:out value="${command.odDate}" /></td>
			</tr>

			<tr>
				<td>Volume:</td>
				<td><c:out value="${command.volumn}" /></td>
			</tr>

			<tr>
				<td>Vol. Reading on:</td>
				<td><c:out value="${command.volumnDate}" /></td>
			</tr>
			<tr>
				<td>Sample Made On:</td>
				<td><c:out value="${command.madeDate}" /></td>
			</tr>

			<tr>
				<td>Transformation On:</td>
				<td><c:out value="${command.transDate}" /></td>
			</tr>

			<tr>
				<td>Remove On:</td>
				<td><c:out value="${command.removeDate}" /></td>
			</tr>
			<tr>
				<td>Refill On:</td>
				<td><c:out value="${command.refillDate}" /></td>
			</tr>
		</table>
	</div>


</div>
<div class="clear"></div>
<br>
<h3>Current Location:</h3>
<table class="table">
	<tr class="info">
		<td><b>Container Name</b></td>
		<td><b>Container Type</b></td>
		<td colspan="2"><b>Location in the Container</b></td>
	</tr>

	<c:forEach items="${command.samplesInContainersIn}" var="siContainer">

		<tr>

			<td><a
				href='<c:url value="/containerDetails.htm"><c:param name="containerId" value="${siContainer.container.containerId}"/></c:url>'><c:out
						value="${siContainer.container.name}" /></a></td>
			<td><c:out value="${siContainer.container.containerType.name}" /></td>
			<td><c:out value="${siContainer.well}" /></td>
			<td><a class="btn"
				href='<c:url value="/deleteSampleFromContainer.htm"><c:param name="sicId" value="${siContainer.sicId}"/></c:url>'
				onclick="return (confirm('Warning: Retrieving this sample from container will remove it from the container but keep the sample in the system!\n\nAre you sure you want to delete the sample from this container?'')) ">
					<span>Retrieve</span>
			</a></td>
		</tr>
	</c:forEach>

</table>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>