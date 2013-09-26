<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Edit Runs</h2>


<form class="form-horizontal" method="post"
	enctype="multipart/form-data">

	<div class="control-group">
		<label class="control-label">Project:</label>
		<div class="controls">
			<spring:bind path="command.project">
				<select name='<c:out value="${status.expression}" />'>

					<c:forEach items="${allProjects}" var="project">
						<option
							<c:if test="${command.project != null && command.project.projectId eq project.projectId}">
			   selected
		  </c:if>
							value="<c:out value="${project.projectId}"/>">
							<c:out value="${project.name}" />
						</option>
					</c:forEach>
				</select>
			</spring:bind>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">Notes:</label>
		<div class="controls">
			<spring:bind path="command.note">
				<INPUT type="text" maxlength="255" size="30" name="note"
					value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Run Date:</label>
		<div class="controls">
			<spring:bind path="command.runDate">
				<input class="datepicker" type="text" name="runDate"
					value="<c:out value="${status.value}"/>" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy" >

			</spring:bind>

		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Tests:</label>
		<div class="controls">
			<c:out value="${command.testNameList}" />
			<select name="testIds">
				<c:forEach items="${allTests}" var="test">

					<option value="<c:out value="${test.testId}"/>">
						<c:out value="${test.name}" />
					</option>

				</c:forEach>
			</select>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Plates:</label>
		<div class="controls">
			<c:out value="${command.plateNameList}" />
			<select name="plateIds">
				<c:forEach items="${allPlates}" var="plate">
					<option value="<c:out value="${plate.containerId}"/>">
						<c:out value="${plate.name}" />
					</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label"><h4>Result File</h4></label>

		<div class="controls"></div>
	</div>


	<div class="control-group">
		<label class="control-label">Column Number:</label>
		<div class="controls form-inline">
			<label class="text inline">Sample ID</label> <input
				class="input-micro" type="text" pattern=[0-9]{2}> <label
				class="text inline">Assay</label> <input class="input-micro"
				type="text" pattern=[0-9]{2}> <label class="text inline">Result</label>
			<input class="input-micro" type="text" pattern=[0-9]{2}>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Upload:</label>
		<div class="controls form-inline">


			<input type="file" name="file" /> <br> (tab delimited)
		</div>
	</div>

	<div class="control-group">
		<div class="controls form-inline">

			<input class="btn" type="submit" name="Submit" value="Save">
			<input class="btn" type="reset" name="Submit2" value="Reset">
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>