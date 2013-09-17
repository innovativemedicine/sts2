<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2 class="noMargin">Sample: <c:out value="${command.patient.intSampleId}"/>-<c:out value="${command.sampleType.suffix}"/>-<c:out value="${command.sampleDupNo}"/> </h2>
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
		<div class="alert alert-info">Patient Information</div>

		<form class="form-horizontal">
			<div class="form-inline">
				<div class="control-group">
					<label class="control-label">Internal ID:</label>
					<div class="controls">
						<span
							style="display: inline-block; vertical-align: middle; padding: 6px 8px"><a
							class="act act-primary"
							href="<c:url value="/patientDetails.htm"><c:param name="intSampleId" value="${command.patient.intSampleId}"/></c:url>">
								<c:out value="${command.patient.intSampleId}" />
						</a></span>
					</div>
				</div>

			</div>
			<div class="control-group">
				<label class="control-label">External ID:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<c:out value="${command.patient.extSampleId}" />" readonly />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Birth Date:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<fmt:formatDate value="${command.patient.birthDate}"
						pattern="dd-MM-yyyy" />"
						readonly />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Notes:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<c:out value="${command.patient.note}" />" readonly />
				</div>
			</div>

		</form>

		<div class="alert alert-info">Sample Information</div>

		<form class="form-horizontal">
			<div class="control-group">
				<label class="control-label">Sample Type:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<c:out value="${command.sampleType.name}"/>"
						readonly />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Received Date:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<fmt:formatDate value="${command.receiveDate}" pattern="dd-MM-yyyy" />"
						readonly />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Notes:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<c:out value="${command.notes}" />" readonly />

				</div>
			</div>
		</form>


	</div>


	<div class="span6">
		<div class="alert alert-info">Sample Concentration</div>

		<form class="form-horizontal">
			<div class="control-group">
				<label class="control-label">Concentration:</label>
				<div class="controls form-inline">
					<div class="input-append span5">
						<input class="input-mini" type="text"
							value="<c:out value="${command.od}" />" placeholder="N/A"
							readonly /> <span class="add-on">ug/mL</span>
					</div>
					<label>Read Date:</label> <input class="input-small" type="text"
						value="<c:out value="${command.odDate}"/>"
						placeholder="DD-MM-YYYY" readonly />

				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Volume:</label>
				<div class="controls form-inline">
					<div class="input-append span5">
						<input class="input-mini" type="text"
							value="<c:out value="${command.volumn}" />" placeholder="N/A"
							readonly /> <span class="add-on">mL</span>
					</div>

					<label>Read Date:</label> <input class="input-small" type="text"
						value="<c:out value="${command.volumnDate}" />"
						placeholder="DD-MM-YYYY" readonly />

				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Created Date:</label>
				<div class="controls">
					<input class="input-small" type="text"
						value="<c:out value="${command.madeDate}" />"
						placeholder="DD-MM-YYYY" readonly />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Transformed Date:</label>
				<div class="controls">
					<input class="input-small" type="text"
						value="<c:out value="${command.transDate}" />"
						placeholder="DD-MM-YYYY" readonly />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Removed Date:</label>
				<div class="controls">

					<input class="input-small" type="text"
						value="<c:out value="${command.removeDate}" />"
						placeholder="DD-MM-YYYY" readonly />
				</div>
			</div>
		</form>
	</div>


</div>

<h3>Current Location:</h3>
<div class="row-fluid">
	<div class="span10">
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
	</div>
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>