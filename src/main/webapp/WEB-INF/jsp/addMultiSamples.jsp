<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp"%>

<h2>Register Samples</h2>

<form method="post" enctype="multipart/form-data">
	<table class="hide1">
		<tr>
			<th colspan="100">Option A: Batch Upload using Excel File
				(External ID)</th>
		</tr>
		<tr>
			<td>SampleID Prefix: <input type="input" size="5"
				id="sampleIdPre" name="sampleIdPre"
				value="<c:out value="${param.sampleIdPre}"/>"></td>

			<td>Sample Type: <select name="sampleTypeID">
				<option value="">Select Sample Type</option> <c:forEach
					items="${allSampleTypes}" var="sampleTypeOpt">

					<option
						<c:if test="${param.sampleTypeID != null && param.sampleTypeID eq sampleTypeOpt.sampleTypeId}">
				   				selected
			 			</c:if>
						value="<c:out value="${sampleTypeOpt.sampleTypeId}"/>">

						<c:out value="${sampleTypeOpt.name}" />

						<c:if test="${sampleTypeOpt.vials != null}">(<c:out
								value="${sampleTypeOpt.vials}" />)</c:if>
					</option>

				</c:forEach></select></td>


			<td>Project: <select size="1" name='projectID'>
					<option value="">Select Project</option>
					<c:forEach items="${allProjects}" var="project">
						<option
							<c:if
								test="${param.projectID != null && param.projectID eq project.projectId}">
						   				selected
					 		</c:if>
							value="<c:out value="${project.projectId}"/>">



							<c:out value="${project.name}" />

						</option>

					</c:forEach>
			</select></td>

			<td>File: <input type="file" name="file"></td>


			<td colspan="20"><input class="button buttonPad" type="submit"
				name="action" value="Upload">
		</tr>
	</table>
	<p>
	<table>
		<tr>
			<th colspan="10">Option B: Manual Upload using Form</th>
		</tr>
		<tr class="hide1">
			<td>SampleID Prefix: <input type="input" size="5"
				id="sampleIdPreForm" name="sampleIdPreForm"
				value="<c:out value="${param.sp}"/>"></td>
			<td>Number of samples? <span class="label">max: 3</span> <input
				class="generateForm" type="text" id="numSamplesText"
				name="numSamplesText" size="1" value="<c:out value="${param.ns}"/>" />
			</td>
			<td><a class="button unhider2" href="#"><span>Generate
						Form</span></a></td>
		</tr>
		<c:if test="${param.ns>0}">
			<tr class="hide2">
				<td>Barcode ID</td>

				<c:forEach var="row" begin="0" end="${param.ns-1}" step="1"
					varStatus="rowStatus">


					<td><spring:bind
							path="command.multiSamples[${row}].patient.intSampleId">
							<INPUT disabled type="text" maxlength="255" size="20"
								name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}"/>">

							<c:if test="${status.errorMessage != ''}">
								<FONT color="red">*</FONT>
							</c:if>
						</spring:bind></td>
				</c:forEach>
			</tr>
			<tr class="hide2">
				<td>External ID</td>
				<c:forEach var="row" begin="0" end="${param.ns-1}" step="1"
					varStatus="rowStatus">

					<td><spring:bind
							path="command.multiSamples[${row}].patient.extSampleId">
							<INPUT type="text" maxlength="64" size="20"
								name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}"/>">

							<c:if test="${status.errorMessage != ''}">
								<FONT color="red">*</FONT>
							</c:if>
						</spring:bind></td>

				</c:forEach>
			</tr>
			<tr class="hide2">
				<td>Project</td>
				<c:forEach var="row" begin="0" end="${param.ns-1}" step="1"
					varStatus="rowStatus">


					<td><spring:bind
							path="command.multiSamples[${row}].patient.project">
							<select size="1" name='<c:out value="${status.expression}" />'>
								<option value="">Select Project</option>
								<c:forEach items="${allProjects}" var="project">
									<option
										<c:if test="${command.multiSamples[row].patient.project != null && command.multiSamples[row].patient.project.projectId eq project.projectId}">
						   				selected
					 				</c:if>
										value="<c:out value="${project.projectId}"/>">
										<c:out value="${project.name}" />

									</option>

								</c:forEach>
							</select>
						</spring:bind></td>
				</c:forEach>
			</tr>
			<tr class="hide2">
				<td>Sample Type</td>
				<c:forEach var="row" begin="0" end="${param.ns-1}" step="1"
					varStatus="rowStatus">


					<td><select multiple size="7"
						name="sampleType[<c:out value="${row}"/>]">
							<c:forEach items="${allSampleTypes}" var="sampleTypeOpt">

								<option
									<c:if test="${command.multiSamples[row].sampleType != null && command.multiSamples[row].sampleType.sampleTypeId eq sampleTypeOpt.sampleTypeId}">
				   				selected
			 				</c:if>
									value="<c:out value="${sampleTypeOpt.sampleTypeId}"/>">

									<c:out value="${sampleTypeOpt.name}" />
									<c:if test="${sampleTypeOpt.vials != null}">(<c:out
											value="${sampleTypeOpt.vials}" />)</c:if>
								</option>

							</c:forEach>
					</select></td>
				</c:forEach>
			</tr>
			<tr class="hide2">
			<tr class="hide2">
				<td>Birth Date</td>
				<c:forEach var="row" begin="0" end="${param.ns-1}" step="1"
					varStatus="rowStatus">

					<td><spring:bind
							path="command.multiSamples[${row}].patient.birthDate">
							<INPUT type="text" maxlength="64" size="20"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
								placeholder="DD-MM-YYYY"
								name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}"/>">

							<c:if test="${status.errorMessage != ''}">
								<FONT color="red">*</FONT>
							</c:if>
						</spring:bind></td>

				</c:forEach>
			</tr>

			<tr class="hide2">
				<td>Received Date</td>
				<c:forEach var="row" begin="0" end="${param.ns-1}" step="1"
					varStatus="rowStatus">

					<td><spring:bind
							path="command.multiSamples[${row}].receiveDate">
							<INPUT type="text" maxlength="64" size="20"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
								placeholder="DD-MM-YYYY"
								name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}"/>">

							<c:if test="${status.errorMessage != ''}">
								<FONT color="red">*</FONT>
							</c:if>
						</spring:bind></td>

				</c:forEach>
			</tr>

			<tr class="hide2">
				<td>Notes</td>
				<c:forEach var="row" begin="0" end="${param.ns-1}" step="1"
					varStatus="rowStatus">

					<td><spring:bind
							path="command.multiSamples[${row}].patient.note">
							<input type="text" maxlength="255" size="25"
								name="<c:out value="${status.expression}"/>">
							<c:out value="${status.value}" />
						</spring:bind></td>
				</c:forEach>
			</tr>
			<tr class="hide2">
				<td colspan="20"><input type="submit" name="action"
					value="Save">
			</tr>
		</c:if>

	</table>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>