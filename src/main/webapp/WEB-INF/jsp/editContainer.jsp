<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Register Containers</h2>

<c:if test="${command.motherContainer != null}">
	Mother Container: <c:out value="${command.motherContainer.name}" />
	<br>
</c:if>

<form class="form-horizontal form-inline" method="post">

	<div class="control-group">
		<label class="control-label">Container Name:</label>
		<div class="controls">
			<spring:bind path="command.name">
				<INPUT required class="setFocus" type="text" maxlength="255" size="20"
					name="name" value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">External Name:</label>
		<div class="controls">
			<spring:bind path="command.extContainerId">
				<INPUT type="text" maxlength="255" size="20" name="extContainerId"
					value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>


	<div class="control-group">
		<label class="control-label">Created Date:</label>
		<div class="controls">

			<spring:bind path="command.createdDate">
				<INPUT type="text" placeholder="DD-MM-YYYY" maxlength="255"
					size="20" name="createdDate"
					value="<c:out value="${status.value}"/>">
				<FONT color="red"> <B><c:out
							value="${status.errorMessage}" /></B>
				</FONT>
			</spring:bind>

		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Concentration:</label>
		<div class="controls">


			<spring:bind path="command.concentration">
				<INPUT type="text" maxlength="255" size="20" name="concentration"
					value="<c:out value="${status.value}"/>" placeholder="ug/mL">
				<FONT color="red"> <B><c:out
							value="${status.errorMessage}" /></B>
				</FONT>
			</spring:bind>

		</div>
	</div>


	<div class="control-group">
		<label class="control-label">Comments:</label>
		<div class="controls">

			<spring:bind path="command.comments">
				<INPUT type="text" maxlength="255" size="20" name="comments"
					value="<c:out value="${status.value}"/>">
			</spring:bind>

		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Container Type<FONT color=RED>*</FONT></label>
		<div class="controls">
			<spring:bind path="command.containerType">
				<select required name='<c:out value="${status.expression}" />'>
					<option value="">Select Container Type</option>
					<c:forEach items="${allContainerTypes}" var="containerType">
						<option
							<c:if test="${command.containerType != null && command.containerType.containerTypeId eq containerType.containerTypeId}">
			   selected
		  </c:if>
							value="<c:out value="${containerType.containerTypeId}"/>">
							<c:out value="${containerType.name}" />
						</option>
					</c:forEach>
				</select>

			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Location<FONT color=RED>*</FONT></label>
		<div class="controls">

			<spring:bind path="command.location">
				<select required name='<c:out value="${status.expression}" />'>
					<option value="">Select Location</option>

					<c:forEach items="${allLocations}" var="location">
						<option
							<c:if test="${command.location != null && command.location.locationId eq location.locationId}">
			   selected
			 </c:if>
							value="<c:out value="${location.locationId}"/>">
							<c:out value="${location.name}" />
						</option>
					</c:forEach>
				</select>
				
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Project</label>
		<div class="controls">

			<spring:bind path="command.project">
				<select name="<c:out value="${status.expression}" />">
					<option value="">Select Project</option>
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
	<p>
	<div class="control-group">
		<div class="controls">
			<input class="btn" type="submit" name="Submit" value="Save">
			<input class="btn" type="reset" name="Submit2" value="Reset">
		</div>
	</div>

</form>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>