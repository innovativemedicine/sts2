<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>
	Edit Sample:
	<c:out value="${command.patient.intSampleId}" />
	-
	<c:out value="${command.sampleType.suffix}" />
	-
	<c:out value="${command.sampleDupNo}" />
</h2>
<form method="post">
	<div class="row-fluid">
		<div class="span6">
			<div class="form-horizontal form-inline">
				<div class="alert alert-info">Sample Information</div>
				<div class="control-group">
					<label class="control-label">Sample Type:</label>
					<div class="controls">
						<spring:bind path="command.sampleType.suffix">
							<input disabled type="text" class="input-small" value="<c:out value="${command.sampleType.suffix}"/>" />
						</spring:bind>
						<spring:bind path="command.sampleDupNo">
							<input disabled type="text" class="input-micro" value="<c:out value="${command.sampleDupNo}"/>" />
						</spring:bind>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Notes:</label>
					<div class="controls">
						<spring:bind path="command.notes">
							<input class="input-medium" type="text" name="notes" value="<c:out value="${status.value}"/>">
						</spring:bind>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Concentration:</label>
					<div class="controls">
						<div class="input-append span5">
							<spring:bind path="command.od">
								<input class="input-mini" type="text" name="od" value="<c:out value="${status.value}"/>">
							</spring:bind>

							<span class="add-on">ug/mL</span>
						</div>

						<label> Read Date:</label>
						<spring:bind path="command.odDate">


							<input class="input-small" type="text" name="odDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d" placeholder="DD-MM-YYYY"
								value="<c:out value="${status.value}"/>">

						</spring:bind>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Volume:</label>
					<div class="controls">
						<div class="input-append span5">
							<spring:bind path="command.volumn">
								<input class="input-mini" type="text" name="volumn" value="<c:out value="${status.value}"/>">

							</spring:bind>
							<span class="add-on">mL</span>
						</div>

						<label>Read Date:</label>

						<spring:bind path="command.volumnDate">
							<input class="input-small" type="text" placeholder="DD-MM-YYYY" name="volumnDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d" value="<c:out value="${status.value}"/>">
						</spring:bind>

						<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B>
						</FONT>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Created Date:</label>
					<div class="controls">
						<spring:bind path="command.madeDate">
							<input class="input-small" type="text" placeholder="DD-MM-YYYY" name="madeDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d" value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Transformed Date:</label>
					<div class="controls">
						<spring:bind path="command.transDate">
							<input class="input-small" type="text" placeholder="DD-MM-YYYY" name="transDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d" value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Removed Date:</label>
					<div class="controls">
						<spring:bind path="command.removeDate">
							<input class="input-small" type="text" placeholder="DD-MM-YYYY" name="removeDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d" value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>

				<div class="control-group">

					<div class="controls">
						<input class="btn" type="submit" name="Submit" value="Save">
						<input class="btn" type="reset" name="Submit2" value="Reset">
					</div>
				</div>

			</div>
		</div>

		<div class="span4">
			<div class="alert alert-info">Patient Information</div>

			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label">Internal ID:</label>
					<div class="controls">

						<c:if test="${!command.patient.newPatient}">
							<input class="input-medium" disabled type="text" value="<c:out value="${command.patient.intSampleId}" />" />
						</c:if>
					</div>

				</div>

				<div class="control-group">
					<label class="control-label">External ID:</label>
					<div class="controls">
						<input disabled class="input-medium" type="text" value="<c:out value="${command.patient.extSampleId}" />" />
					</div>
				</div>



				<div class="control-group">
					<label class="control-label">Received Date:</label>
					<div class="controls">
						<spring:bind path="command.patient.receiveDate">
							<input disabled class="input-medium" type="text" placeholder="DD-MM-YYYY" name="receiveDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d" value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Notes:</label>
					<div class="controls">
						<spring:bind path="command.patient.note">
							<input disabled class="input-medium" type="text" name="notes" value="<c:out value="${status.value}"/>">
						</spring:bind>
					</div>
				</div>

			</div>
		</div>


	</div>

</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>