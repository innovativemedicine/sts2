<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Add / Edit User Information</h2>

<form class="form-horizontal" method="post"
	action="adminAddEditUser.htm">

	<div class="control-group">
		<label class="control-label" for="inputEmail">Login Name</label>
		<div class="controls">
			<spring:bind path="command.loginname">
				<input required type="text" maxlength="32" size="30"
					name="loginname" value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputEmail">Password</label>
		<div class="controls">
			<spring:bind path="command.password">
				<input required type="password" maxlength="32" size="30"
					name="password" value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputEmail">Name</label>
		<div class="controls">
			<spring:bind path="command.firstName">
				<input required type="text" maxlength="32" size="30"
					name="firstName" value="<c:out value="${status.value}"/>"
					placeholder="First Name">
			</spring:bind>
			<spring:bind path="command.lastName">
				<input type="text" maxlength="32" size="30" name="lastName"
					value="<c:out value="${status.value}"/>" placeholder="Last Name">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputEmail">Role</label>
		<div class="controls">
			<spring:bind path="command.ROLESs">
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
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Activity</label>
		<div class="controls">
			<spring:bind path="command.activity">
				<input type="checkbox" maxlength="3" size="3" name="activity"
					<c:if test="${status.value=='on'}">
				checked value="on"
			</c:if> />

			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<div class="controls">
			<input class="btn" type="submit" name="Submit" value="Edit">
			<input class="btn" type="submit" name="Submit" value="New">
		</div>
	</div>
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
			<td><a class="act act-primary"
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