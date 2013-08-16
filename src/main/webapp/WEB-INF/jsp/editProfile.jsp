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

<form method="post" action="editProfile.htm">

	<table class="table" class="details">

		<tr>
			<td>Login Name<font color=RED> *</font></td>
			<td><spring:bind path="command.loginname">
					<input required type="text" maxlength="32" size="30" name="loginname"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>

		<tr>
			<td>Password<font color=RED> *</font></td>
			<td><input required type="password" maxlength="32" size="30"
				name="password" value="<c:out value="${status.value}"/>"></td>
		</tr>


		<tr>
			<td>New Password</td>
			<td><input required type="password" maxlength="32" size="30"
				name="newpassword1"></td>
		</tr>

		<tr>
			<td>Reenter New Password</td>
			<td><input required type="password" maxlength="32" size="30"
				name="newpassword2"></td>
		</tr>

		<tr>
			<td>First Name</td>
			<td><spring:bind path="command.firstName">
					<input required type="text" maxlength="32" size="30" name="firstName"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>

		<tr>
			<td>Last Name</td>
			<td><spring:bind path="command.lastName">
					<input required type="text" maxlength="32" size="30" name="lastName"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>

		<tr>
			<td colspan="2">
				<p>
					<input class="btn" type="submit" name="Submit" value="Submit">
				</p>
			</td>
		</tr>
	</table>
</form>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>