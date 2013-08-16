<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Add / Edit User Information</h2>

<form method="post" action="adminAddEditUser.htm">

	<table class="table">

		<tr>
			<td>Login Name<font color=RED>*</font></td>
			<td><spring:bind path="command.loginname">
					<input required type="text" maxlength="32" size="30" name="loginname"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>

		<tr>
			<td>Password<font color=RED>*</font></td>
			<td><spring:bind path="command.password">
					<input required type="password" maxlength="32" size="30" name="password"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>

		<tr>
			<td>First Name</td>
			<td><spring:bind path="command.firstName">
					<input type="text" maxlength="32" size="30" name="firstName"
						value="<c:out value="${status.value}"/>">
					<font color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</font>
				</spring:bind></td>
		</tr>

		<tr>
			<td>Last Name</td>
			<td><spring:bind path="command.lastName">
					<input type="text" maxlength="32" size="30" name="lastName"
						value="<c:out value="${status.value}"/>">
					<font color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</font>
				</spring:bind></td>
		</tr>


		<tr>
			<td>Role<font color=RED>*</font></td>
			<td><spring:bind path="command.ROLESs">
					<select required name="roles">
						<c:forEach var="witem" items="${RolesList}">
							<c:set value="false" var="mytest" scope="page" />
							<c:forEach var="cc" items="${command.ROLESs}">
								<c:if test="${witem.id==cc.id}">
									<c:set value="true" var="mytest" scope="page" />
								</c:if>
							</c:forEach>
							<option <c:if test="${mytest=='true'}"> selected </c:if>
								value='<c:out value="${witem.id}" />'>
								<c:out value="${witem.name}" />
							</option>
						</c:forEach>
					</select>
				</spring:bind></td>
		</tr>
		<tr>
			<td>Activity</td>
			<td><spring:bind path="command.activity">
					<input type="checkbox" maxlength="3" size="3" name="activity"
						<c:if test="${status.value=='on'}">
				checked value="on"
			</c:if> />

					<font color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</font>
				</spring:bind></td>
		</tr>

		<tr>
			<td colspan="2">
				<p>
					<input class="btn" type="submit" name="Submit" value="Edit">
					<input class="btn" type="submit" name="Submit" value="New">
				</p>
			</td>
		</tr>
	</table>

</form>

<table class="table">
	<tr class="info">
		<td><b>Login Name</b></td>
		<td><b>First Name</b></td>
		<td><b>Last Name</b></td>
		<td><b>Role</b></td>
	</tr>

	<c:forEach items="${LUsers}" var="user">
		<tr>
			<td><a
				href="<c:url value="adminAddEditUser.htm">
			<c:param name="userId" value="${user.userId}"/></c:url>">
					<c:out value="${user.loginname}" />
			</a></td>
			<td><c:out value="${user.firstName}" /></td>
			<td><c:out value="${user.lastName}" /></td>
			<td><c:forEach items="${user.ROLESs}" var="role">
					<c:out value="${role.name}" />
				</c:forEach></td>

		</tr>
	</c:forEach>

</table>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>