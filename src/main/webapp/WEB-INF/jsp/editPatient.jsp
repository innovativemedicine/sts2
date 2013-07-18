<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<h2>
	Edit Patient:
	<c:out value="${command.intSampleId}" />
</h2>


<form class="form-horizontal form-inline" method="post">

	<div class="control-group">
		<label class="control-label">External ID:</label>
		<div class="controls">
			<spring:bind path="command.extSampleId">
				<INPUT type="text" maxlength="255" size="30" name="extSampleId"
					value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>



	<div class="control-group">
		<label class="control-label">External ID 2:</label>
		<div class="controls">

			<spring:bind path="command.anotherExtSampleId">
				<INPUT type="text" maxlength="255" size="30"
					name="anotherExtSampleId" value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>


	<div class="control-group">
		<label class="control-label">Project:</label>
		<div class="controls">


			<spring:bind path="command.project">
				<select name="<c:out value="${status.expression}" />">

					<c:forEach items="${allProjects}" var="project">
						<option
							<c:if test="${command.project != null && command.project.projectId eq project.projectId}">
			   selected
		 </c:if>
							value="<c:out value="${project.projectId}"/>">
							<c:out value="${project.investigator.fullName}" />
							&nbsp;
							<c:out value="${project.name}" />
						</option>
					</c:forEach>
				</select>
			</spring:bind>
		</div>
	</div>


	<div class="control-group">
		<label class="control-label">Birth Date:</label>
		<div class="controls">


			<spring:bind path="command.birthDate">
				<INPUT type="text" name="birthDate"
					value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label">Note:</label>
		<div class="controls">


			<spring:bind path="command.note">
				<textarea name="note"><c:out value="${status.value}"/></textarea>
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<div class="controls">
			<input class="btn" type="submit" name="Submit" value="Save">
			<input class="btn" type="reset" name="Submit2" value="Reset">

		</div>
	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>