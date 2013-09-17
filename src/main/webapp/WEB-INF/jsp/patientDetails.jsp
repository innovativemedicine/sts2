<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>
	Patient:
	<c:out value="${command.intSampleId}" />
</h2>

<a class="btn"
	href="<c:url value="/editPatient.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>">Edit
	Patient</a>

<%--    		<a class="btn" href="<c:url value="/deletePatient.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>" onclick="return (confirm('Warning: Deleting the patient will delete all the samples of this patient as well as all the genotype results of those samples. \n\nAre you sure you want to delete this Patient?')) " >Delete Patient</a> --%>

<p>

<div class="row-fluid">
	<div class="span4">

		<div class="alert alert-info">Patient Information</div>

		<form class="form-horizontal">
			<div class="control-group">
				<label class="control-label">External ID:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<c:out value="${command.extSampleId}" />" readonly />
				</div>

			</div>

			<div class="control-group">
				<label class="control-label">Project:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<c:out value="${command.project}" />" readonly />
				</div>

			</div>


			<div class="control-group">
				<label class="control-label">Birth Date:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<fmt:formatDate value="${command.birthDate}" pattern="dd-MM-yyyy" />"
						placeholder="DD-MM-YYYY" readonly />
				</div>

			</div>

			<div class="control-group">
				<label class="control-label">Note:</label>
				<div class="controls">
					<input class="input-medium" type="text"
						value="<c:out value="${command.note}" />" readonly />
				</div>

			</div>
		</form>

	</div>
</div>

<div class="row-fluid">
	<div class="span10">
		<h3>Sample List</h3>
		<!-- Button to trigger modal -->
		<a href="#myModal" role="button" class="btn" data-toggle="modal">Add Sample Type</a>
		<p>
		<table class="table ">
			<tr class="info">
				<td class="span2"><b>Sample Type</b></td>
				<td class="span1"><b>Received Date</b><br>(DD-MM-YYYY)</td>
				<td class="span3"><b>Notes</b></td>
			</tr>

			<c:forEach items="${command.samples}" var="sample">
				<tr>
					<td><a class="act act-primary"
						href="<c:url value="/sampleDetails.htm">
			<c:param name="sampleId" value="${sample.sampleId}"/></c:url>"><c:out
								value="${sample.sampleType.name}" /> - <c:out
								value="${sample.sampleDupNo}" /> </a></td>
					<td><fmt:formatDate value="${sample.receiveDate}"
							pattern="dd-MM-yyyy" /></td>
					<td><c:out value="${sample.notes}" /></td>

				</tr>
			</c:forEach>

		</table>
	</div>
</div>

<!-- Add New SampleType -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <h3 id="myModalLabel">Add Sample Type</h3>
  </div>
  <div class="modal-body">
    <p>One fine body</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-primary">Add</button>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>