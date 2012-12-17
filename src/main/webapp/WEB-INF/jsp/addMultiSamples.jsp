<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Register Samples</h2>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp"%>

<!-- 				Add File Upload option for addMultiSamples (manifest file). Look at saveSamplesInBatch for ideas -->

<form method="post">
	<table class="hide1">
		<tr>
			<th colspan="3">Option A: Upload Excel File of Sample ID</th>
		</tr>
		<tr>
			<td>SampleID Prefix: <input type="input" size="10"
				id="sampleIdPre"></td>
			<td><input type="file"></td>

			<td colspan="20"><input type="submit" name="Submit" value="Save">
		</tr>
	</table>
	<p>
	<table>
		<tr>
			<th colspan="10">Option B: Register Sample Manually Using Form</th>
		</tr>
		<tr class="hide1">
			<td>SampleID Prefix: <input type="input" size="10"
				id="sampleIdPreForm" value=""></td>
			<td>Number of samples? <span class="label">max: 3</span> <input
				class="generateForm" type="text" id="numSamplesText"
				name="numSamplesText" size="1" value="<c:out value="${param.ns}"/>" />
			</td>
			<td><a class="button unhider2" href="#"><span>Generate
						Form</span></a></td>
			</td>
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
						name="sampleType[<c:out value="${row}"/>]" /> <c:forEach
							items="${allSampleTypes}" var="sampleTypeOpt">

							<option
								<c:if test="${command.multiSamples[row].sampleType != null && command.multiSamples[row].sampleType.sampleTypeId eq sampleTypeOpt.sampleTypeId}">
				   				selected
			 				</c:if>
								value="<c:out value="${sampleTypeOpt.sampleTypeId}"/>">

								<c:out value="${sampleTypeOpt.name}" />
								<c:if test="${sampleTypeOpt.vials != null}">(<c:out
										value="${sampleTypeOpt.vials}" />)</c:if>
							</option>
							</option>

						</c:forEach> </select></td>
				</c:forEach>
			</tr>
			<tr class="hide2">
				<td>Notes</td>
				<c:forEach var="row" begin="0" end="${param.ns-1}" step="1"
					varStatus="rowStatus">

					<td><spring:bind
							path="command.multiSamples[${row}].patient.note">
							<INPUT type="text" maxlength="255" size="25"
								name="<c:out value="${status.expression}"/>">
							<c:out value="${status.value}" />
							</input>
						</spring:bind></td>
				</c:forEach>
			</tr>
			<tr class="hide2">
				<td colspan="20"><input type="submit" name="Submit"
					value="Save">
			</tr>
		</c:if>

	</table>


</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>