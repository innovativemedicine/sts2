<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2 class="noMargin">
	Sample:
	<c:out value="${command.patient.intSampleId}" />
	-
	<c:out value="${command.sampleType.suffix}" />
	-
	<c:out value="${command.sampleDupNo}" />
</h2>


<p>
<div class="row-fluid">

	<div class="span6">
		<div class="alert alert-info">
			Sample Information
			<div class="pull-right">
				<a class="btn btn-mini"
					href='<c:url value="/deleteSample.htm"><c:param name="sampleId" value="${command.sampleId}"/></c:url>'
					onclick="return (confirm('Warning: Deleting the sample will delete it from all the containers!\n\nAre you sure you want to delete this sample?')) ">
					<i class="iconic-x"> DEL </i>
				</a> <a class="btn btn-mini"
					href='<c:url value="/editSample.htm"><c:param name="sampleId" value="${command.sampleId}"/></c:url>'><i
					class="iconic-pen-alt2"> EDIT</i></a>
			</div>
		</div>

		<form class="form-horizontal">
			<div class="control-group">
				<label class="control-label">Sample Type:</label>
				<div class="controls">
					<spring:bind path="command.sampleType.suffix">
						<input type="text" class="input-small" value="<c:out value="${command.sampleType.suffix}"/>" readonly/>
					</spring:bind>
					<spring:bind path="command.sampleDupNo">
						<input type="text" class="input-micro" value="<c:out value="${command.sampleDupNo}"/>" readonly/>
					</spring:bind>
				</div>
			</div>


			<div class="control-group">
				<label class="control-label">Notes:</label>
				<div class="controls">
					<input class="input-medium" type="text" value="<c:out value="${command.notes}" />" readonly />

				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Concentration:</label>
				<div class="controls form-inline">
					<div class="input-append span5">
						<input class="input-mini" type="text" value="<c:out value="${command.od}" />" placeholder="N/A" readonly />
						<span class="add-on">ug/mL</span>
					</div>
					<label>Read Date:</label>
					<input class="input-small" type="text" value="<c:out value="${command.odDate}"/>" placeholder="DD-MM-YYYY" readonly />

				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Volume:</label>
				<div class="controls form-inline">
					<div class="input-append span5">
						<input class="input-mini" type="text" value="<c:out value="${command.volumn}" />" placeholder="N/A" readonly />
						<span class="add-on">mL</span>
					</div>

					<label>Read Date:</label>
					<input class="input-small" type="text" value="<c:out value="${command.volumnDate}" />" placeholder="DD-MM-YYYY" readonly />

				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Created Date:</label>
				<div class="controls">
					<input class="input-small" type="text" value="<c:out value="${command.madeDate}" />" placeholder="DD-MM-YYYY" readonly />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Transformed Date:</label>
				<div class="controls">
					<input class="input-small" type="text" value="<c:out value="${command.transDate}" />" placeholder="DD-MM-YYYY" readonly />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Removed Date:</label>
				<div class="controls">

					<input class="input-small" type="text" value="<c:out value="${command.removeDate}" />" placeholder="DD-MM-YYYY" readonly />
				</div>
			</div>
		</form>


		<div class="alert alert-info">Sample Location</div>

		<table class="table table-condensed table-bordered">
			<tr>
				<td>
					<b>Container Name</b>
				</td>

				<td colspan="2">
					<b>Location in Container</b>
				</td>
			</tr>

			<c:forEach items="${command.samplesInContainersIn}" var="siContainer">

				<tr>

					<td>
						<a
							href='<c:url value="/containerDetails.htm"><c:param name="containerId" value="${siContainer.container.containerId}"/></c:url>'><c:out
								value="${siContalociner.container.name}" /></a>
					</td>
					<td>
						<c:out value="${siContainer.well}" />
					</td>
					<td>
						<a class="btn"
							href='<c:url value="/deleteSampleFromContainer.htm"><c:param name="sicId" value="${siContainer.sicId}"/></c:url>'
							onclick="return (confirm('Warning: Retrieving this sample from container will remove it from the container but keep the sample in the system!\n\nAre you sure you want to delete the sample from this container?'')) ">
							<span>Retrieve</span>
						</a>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<div class="span4">
		<div class="alert alert-success">
			Patient Information <a class="btn btn-mini pull-right"
				href="<c:url value="/editPatient.htm"><c:param name="intSampleId" value="${command.patient.intSampleId}"/><c:param name="sampleId" value="${command.sampleId}"/></c:url>"><i
				class="iconic-pen-alt2"> EDIT</i></a>
		</div>

		<form class="form-horizontal">
			<div class="form-inline">
				<div class="control-group">
					<label class="control-label">Internal ID:</label>
					<div class="controls">
<!-- 						<span style="display: inline-block; vertical-align: middle; padding: 6px 8px"><a class="act act-primary" -->
<%-- 							href="<c:url value="/patientDetails.htm"><c:param name="intSampleId" value="${command.patient.intSampleId}"/></c:url>"> --%>
<%-- 								<c:out value="${command.patient.intSampleId}" /> --%>
<!-- 						</a></span> -->
					<input class="input-medium" type="text" value="<c:out value="${command.patient.intSampleId}" />" readonly />
					</div>
				</div>

			</div>
			<div class="control-group">
				<label class="control-label">External ID:</label>
				<div class="controls">
					<input class="input-medium" type="text" value="<c:out value="${command.patient.extSampleId}" />" readonly />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Birth Date:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<fmt:formatDate value="${command.patient.birthDate}"
						pattern="dd-MM-yyyy" />" readonly />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Received Date:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<fmt:formatDate value="${command.patient.receiveDate}" pattern="dd-MM-yyyy" />" readonly />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Notes:</label>
				<div class="controls">
					<input class="input-medium" type="text" value="<c:out value="${command.patient.note}" />" readonly />
				</div>
			</div>

		</form>

		<div class="alert alert-success">
			Patient Sample Types
			<!-- Button to Add Sample Type modal -->
			<div class="pull-right">
				<a href="#myModal" class="btn btn-mini" data-toggle="modal"><i class="iconic-plus"> ADD</i></a>
			</div>
		</div>



		<table class="table table-bordered table-condensed ">
			<tr>
				<td>
					<b>Sample Type</b>
				</td>

				<td>
					<b>Notes</b>
				</td>
			</tr>

			<c:forEach items="${command.patient.samples}" var="otherSamples">
				<tr>
					<td>
						<a class="act act-primary"
							href="<c:url value="/sampleDetails.htm">
			<c:param name="sampleId" value="${otherSamples.sampleId}"/></c:url>"><c:out
								value="${otherSamples.sampleType.suffix}" /> - <c:out value="${otherSamples.sampleDupNo}" /> </a>
					</td>

					<td>
						<c:out value="${otherSamples.notes}" />
					</td>

				</tr>
			</c:forEach>

		</table>
	</div>
</div>

<!-- Add New SampleType Model -->
<div id="myModal" class="modal hide fade" tabindex="-1">
	<form method="post" class="form" enctype="multipart/form-data">
		<div class="modal-header">
		</div>
		<div class="modal-body">
			<div class="alert alert-info">
				<h4>Add Sample Type</h4>
			</div>

			<label>Sample Type:</label>
			<select name="addST_sampleTypeId">

				<c:forEach items="${allSampleTypes}" var="sampleTypeOpt">
					<option value="<c:out value="${sampleTypeOpt.sampleTypeId}"/>">
						<c:out value="${sampleTypeOpt.name}" />
					</option>
				</c:forEach>
			</select>

			<label>Amount To Add:</label>
			<input required type="text" class="input-micro" name="addST_numSamples" pattern="[0-9]{1,2}" />

			<label>Notes:</label>
			<input name="addST_notes" type="text" class="input-large" />
		</div>

		<div class="modal-footer">
			<button class="btn" data-dismiss="modal">Close</button>
			<button class="btn btn-primary" type="submit" name="action" value="addST">Add</button>
		</div>
	</form>
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>