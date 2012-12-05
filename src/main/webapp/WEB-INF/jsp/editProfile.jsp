<%--
 * Edit user self's information, change password
 * 
 * @author Jianan Xiao 2005-10-06
 --%>

<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

		<h2>Edit Users</h2> <spring:bind path="command.*">
			<FONT color="red"> <c:forEach items="${status.errorMessages}"
					var="container">
					<B><c:out value="${container}" /></B>
					<br>
				</c:forEach>
			</FONT>
		</spring:bind>

		<form method="post" action="EditProfile.htm">

			<table class="details">

				<tr>
					<td>Login Name<FONT color=RED>*</FONT></td>
					<td><spring:bind path="command.loginname">
							<INPUT type="text" maxlength="32" size="30" name="loginname"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind></td>
				</tr>

				<tr>
					<td>Password<FONT color=RED>*</FONT></td>
					<td><INPUT type="password" maxlength="32" size="30"
						name="password" value="<c:out value="${status.value}"/>">
					</td>
				</tr>


				<tr>
					<td>New Password</td>
					<td><INPUT type="password" maxlength="32" size="30"
						name="newpassword1"></td>
				</tr>

				<tr>
					<td>Reenter New Password</td>
					<td><INPUT type="password" maxlength="32" size="30"
						name="newpassword2"></td>
				</tr>

				<tr>
					<td>First Name</td>
					<td><spring:bind path="command.firstName">
							<INPUT type="text" maxlength="32" size="30" name="firstName"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind></td>
				</tr>

				<tr>
					<td>Last Name</td>
					<td><spring:bind path="command.lastName">
							<INPUT type="text" maxlength="32" size="30" name="lastName"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind></td>
				</tr>

				<tr>
					<td colspan="2">
						<p>
							<input type="submit" name="Submit" value="Submit">
						</p>
					</td>
				</tr>
			</table>
		</form>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>