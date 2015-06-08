<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>
	Patient:
	<c:out value="${patient.intSampleId}" />
</h2>

<a class="btn" href="<c:url value="/editPatient.htm"><c:param name="intSampleId" value="${patient.intSampleId}"/></c:url>">Edit
	Patient</a>

<%--    		<a class="btn" href="<c:url value="/deletePatient.htm"><c:param name="intSampleId" value="${patient.intSampleId}"/></c:url>" onclick="return (confirm('Warning: Deleting the patient will delete all the samples of this patient as well as all the genotype results of those samples. \n\nAre you sure you want to delete this Patient?')) " >Delete Patient</a> --%>

<p>
<div class="row-fluid">
	<div class="span4">

		<div class="alert alert-info">Patient Information</div>

		<div class="form-horizontal">
			<div class="control-group">
				<label class="control-label">External ID:</label>
				<div class="controls">
					<input class="input-medium" type="text" value="<c:out value="${patient.extSampleId}" />" readonly />
				</div>

			</div>

			<div class="control-group">
				<label class="control-label">Project:</label>
				<div class="controls">
					<input class="input-medium" type="text" value="<c:out value="${patient.project}" />" readonly />
				</div>

			</div>


			<div class="control-group">
				<label class="control-label">Birth Date:</label>
				<div class="controls">
					<input class="input-medium" type="text" value="<fmt:formatDate value="${patient.birthDate}" pattern="dd-MM-yyyy" />"
						placeholder="DD-MM-YYYY" readonly />
				</div>

			</div>

			<div class="control-group">
				<label class="control-label">Receive Date:</label>
				<div class="controls">
					<input class="input-medium" type="text" value="<fmt:formatDate value="${patient.receiveDate}" pattern="dd-MM-yyyy" />"
						placeholder="DD-MM-YYYY" readonly />
				</div>

			</div>

			<div class="control-group">
				<label class="control-label">Note:</label>
				<div class="controls">
					<input class="input-medium" type="text" value="<c:out value="${patient.note}" />" readonly />
				</div>

			</div>
		</div>

	</div>
</div>

<div class="row-fluid">
	<div class="span10">
		<h3>Sample List</h3>
		<!-- Button to Add Sample Type modal -->
		<a href="#myModal" role="button" class="btn" data-toggle="modal">Add Sample Type</a>
		<p>
		<table class="table ">
			<tr class="info">
				<td class="span2">
					<b>Sample Type</b>
				</td>

				<td class="span3">
					<b>Notes</b>
				</td>
			</tr>

			<c:forEach items="${patient.samples}" var="sample">
				<tr>
					<td>
						<a class="act act-primary"
							href="<c:url value="/sampleDetails.htm">
			<c:param name="sampleId" value="${sample.sampleId}"/></c:url>"><c:out
								value="${sample.sampleType.name}" /> - <c:out value="${sample.sampleDupNo}" /> </a>
					</td>

					<td>
						<c:out value="${sample.notes}" />
					</td>

				</tr>
			</c:forEach>

		</table>
	</div>
</div>

<!-- Add New SampleType Model -->
<div id="myModal" class="modal hide fade" tabindex="-1">
	<form method="post" class="form" enctype="multipart/form-data">

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