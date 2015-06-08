<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<h2>
	Edit Patient:
	<c:out value="${command.intSampleId}" />
</h2>

<div class="span5">
	<form class="form-horizontal form-inline" method="post">

		<div class="alert alert-success">Patient Information</div>

		<div class="control-group">
			<label class="control-label">External ID:</label>
			<div class="controls">
				<spring:bind path="command.extSampleId">
					<INPUT type="text" maxlength="255" size="30" name="extSampleId" value="<c:out value="${status.value}"/>">
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
					<INPUT autocomplete="off" class="datepicker" data-date-format="dd-mm-yyyy" type="text" name="birthDate" value="<c:out value="${status.value}"/>" placeholder="DD-MM-YYYY">
				</spring:bind>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">Receive Date:</label>
			<div class="controls">


				<spring:bind path="command.receiveDate">
					<INPUT autocomplete="off" class="datepicker" type="text" name="receiveDate" value="<c:out value="${status.value}"/>" data-date-format="dd-mm-yyyy" placeholder="DD-MM-YYYY">
				</spring:bind>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">Note:</label>
			<div class="controls">


				<spring:bind path="command.note">
					<textarea name="note">
					<c:out value="${status.value}" />
				</textarea>
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
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>