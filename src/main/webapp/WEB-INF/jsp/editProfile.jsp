<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Profile Settings</h2>
<spring:bind path="command.*">
	<font color="red"> <c:forEach items="${status.errorMessages}"
			var="container">
			<B><c:out value="${container}" /></B>
			<br>
		</c:forEach>
	</font>
</spring:bind>

<form class="form-horizontal" method="post" action="editProfile.htm">


	<div class="control-group">
		<label class="control-label" for="inputEmail">Login Name</label>
		<div class="controls">
			<spring:bind path="command.loginname">
				<input required type="text" maxlength="32"
					name="loginname" value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputEmail">Password</label>
		<div class="controls">
			<input required type="password" maxlength="32"
				name="password" value="<c:out value="${status.value}"/>">
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputEmail">New Password</label>
		<div class="controls">
			<input required type="password" maxlength="32"
				name="newpassword1"> <input required type="password"
				maxlength="32" size="30" name="newpassword2"
				placeholder="Re-enter New Password">
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputEmail">Name</label>
		<div class="controls">
			<spring:bind path="command.firstName">
				<input required type="text" maxlength="32"
					name="firstName" value="<c:out value="${status.value}"/>" placeholder="First Name">
			</spring:bind>

			<spring:bind path="command.lastName">
				<input required type="text" maxlength="32" name="lastName"
					value="<c:out value="${status.value}"/>" placeholder="Last Name">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<div class="controls">
		<input class="btn" type="submit" name="Submit" value="Submit">
		</div>
	</div>
</form>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>