<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<h2>Edit Projects</h2>

<form class="form-horizontal form-inline" method="post">

	<div class="control-group">
		<label class="control-label">Project Name:</label>
		<div class="controls">
			<spring:bind path="command.name">
				<INPUT type="text" maxlength="255" size="20" name="name"
					value="<c:out value="${status.value}"/>">
				<FONT color="red"> <B><c:out
							value="${status.errorMessage}" /></B>
				</FONT>
			</spring:bind>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">Description:</label>
		<div class="controls">
			<spring:bind path="command.description">

				<INPUT type="text" maxlength="255" size="20" name="description"
					value="<c:out value="${status.value}"/>">

			</spring:bind>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label"> Status:</label>
		<div class="controls">
			<spring:bind path="command.status">

				<INPUT type="text" maxlength="255" size="20" name="status"
					value="<c:out value="${status.value}"/>">

			</spring:bind>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label"> Created On:</label>
		<div class="controls">
			<spring:bind path="command.createdOn">

				<INPUT type="text" maxlength="255" size="20" name="createdOn"
					placeholder="DD-MM-YYYY" value="<c:out value="${status.value}"/>">
				<FONT color="red"> <B><c:out
							value="${status.errorMessage}" /></B>
				</FONT>
			</spring:bind>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label"> Investigator:</label>
		<div class="controls">
			<spring:bind path="command.investigator">
				<select name='<c:out value="${status.expression}" />'>

					<c:forEach items="${availableIvgs}" var="inv">
						<option
							<c:if test="${command.investigator != null && command.investigator.investigatorId eq inv.investigatorId}">
			   selected
		     </c:if>
							value="<c:out value="${inv.investigatorId}"/>">
							<c:out value="${inv.name.fname}" />
							<c:out value="${inv.name.lname}" />
						</option>
					</c:forEach>
				</select>
			</spring:bind>
		</div>
		<p>
		<div class="control-group">
			<div class="controls">
				<input class="btn" type="submit" name="Submit" value="Save">
				<input class="btn" type="reset" name="Submit2" value="Reset">
			</div>
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>