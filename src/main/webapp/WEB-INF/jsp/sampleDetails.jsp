<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<h2 class="noMargin"> Sample Details </h2>
<a class="button"
	href='<c:url value="/editSample.htm"><c:param name="sampleId" value="${command.sampleId}"/></c:url>'>
	<span>Edit Sample</span>
</a>
<a class="button"
	href='<c:url value="/deleteSample.htm"><c:param name="sampleId" value="${command.sampleId}"/></c:url>'
	onclick="return (confirm('Warning: Deleting the sample will delete it from all the containers!\n\nAre you sure you want to delete this sample?')) ">
	<span>Delete Sample</span>
</a>
<p>
<div class="left">
	<table>
		<tr>
			<th colspan="2">Sample Information</th>
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
			<td>Sample Received on:</td>
			<td><c:out value="${command.receiveDate}" /></td>
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
		<tr>
			<td colspan="2" align="center">Concentration: <a class="unhider" href="#">[Show]</a></td>
		</tr>
	</table>
</div>

<div class="left">
	<table class="hide">
		<tr>
			<th colspan="2">Sample Concentration</th>
		</tr>
		<tr>
			<td>Concentration:</td>
			<td><c:out value="${command.od}" /></td>
		</tr>
		<tr>
			<td>Reading On:</td>
			<td><c:out value="${command.odDate}" /></td>
		</tr>

		<tr>
			<td>Volume:</td>
			<td><c:out value="${command.volumn}" /></td>
		</tr>

		<tr>
			<td>Reading on:</td>
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

<div>
	<table>
		<tr>
			<th colspan="2"><a
				href='<c:url value="/editPatient.htm"><c:param name="intSampleId" value="${command.patient.intSampleId}"/></c:url>'>
					Patient information </a></th>
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
			<td>Is Controlled</td>
			<td><c:out value="${command.patient.isControl }" /></td>
		</tr>
		<tr>
			<td>Note</td>
			<td><c:out value="${command.patient.note }" /></td>
		</tr>
	</table>
</div>

<div class="clear"></div>
<br>
<h3>Current Location:</h3>
<table>
	<tr>
		<th>Container Name</th>
		<th>Container Type</th>
		<th colspan="2">Location in the Container</th>
	</tr>

	<c:forEach items="${command.samplesInContainersIn}" var="siContainer">

		<tr>

			<td><a
				href='<c:url value="/containerDetails.htm"><c:param name="containerId" value="${siContainer.container.containerId}"/></c:url>'><c:out
						value="${siContainer.container.name}" /></a></td>
			<td><c:out value="${siContainer.container.containerType.name}" /></td>
			<td><c:out value="${siContainer.well}" /></td>
			<td><a class="button"
				href='<c:url value="/deleteSampleFromContainer.htm"><c:param name="sicId" value="${siContainer.sicId}"/></c:url>'
				onclick="return (confirm('Warning: Retrieving this sample from container will remove it from the container but keep the sample in the system!\n\nAre you sure you want to delete the sample from this container?'')) ">
					<span>Retrieve</span>
			</a></td>
		</tr>
	</c:forEach>

</table>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>