<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Add results:</h2>
<form class="form-horizontal" method="post">
	<div class="control-group">
		<label class="control-label">Project</label>
		<div class="controls">
			<spring:bind path="command.run.project">
				<select required name='<c:out value="${status.expression}" />'>
					<c:forEach items="${allProjects}" var="project">
						<option value="<c:out value="${project.projectId}"/>">
							<c:out value="${project.name}" />
						</option>
					</c:forEach>
				</select>
			</spring:bind>

		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Note</label>
		<div class="controls">
			<spring:bind path="command.note">
				<input type="text" maxlength="255" size="30" name="note"
					value="<c:out value="${status.value}"/>">
			</spring:bind>

		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Test Date</label>
		<div class="controls">
			<spring:bind path="command.run.runDate">
				<input required type="text" maxlength="255" size="30"
					name="run.runDate" value="<c:out value="${status.value}"/>"
					placeholder="DD-MM-YY">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Sample ID</label>
		<div class="controls">
			<spring:bind path="command.intSampleId">
				<input required type="text" maxlength="255" size="30"
					name="intSampleId" value="<c:out value="${status.value}"/>">

			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Assay</label>
		<div class="controls">
			<spring:bind path="command.assay">
				<select name='<c:out value="${status.expression}" />'>

					<c:forEach items="${allAssays}" var="assay">
						<option value="<c:out value="${assay.assayId}"/>">
							<c:out value="${assay.name}" />
						</option>
					</c:forEach>
				</select>
			</spring:bind>
		</div>
	</div>


	<div class="control-group">
		<label class="control-label">Result</label>
		<div class="controls">
			<spring:bind path="command.result">
				<input type="text" maxlength="255" size="30" name="result"
					value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>



	<!-- 	<td>CGG Study Group:</td> -->
	<%-- 	<td><spring:bind path="command.studyGroupId"> --%>
	<!-- 			<select name="studyGroupId" size="1"> -->

	<%-- 				<c:forEach items="${allStudyGroups}" var="studyGroup"> --%>
	<%-- 					<option value="<c:out value="${studyGroup.studyGroupId}"/>"> --%>
	<%-- 						<c:out value="${studyGroup.studyGroupName}" /> --%>
	<!-- 					</option> -->
	<%-- 				</c:forEach> --%>
	<!-- 			</select> -->
	<%-- 		</spring:bind></td> --%>
	<!-- 	</tr> -->


	<div class="control-group">

		<div class="controls">

			<input class="btn" type="submit" name="Submit" value="Save">
			<input class="btn" type="reset" name="Submit2" value="Reset">
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>