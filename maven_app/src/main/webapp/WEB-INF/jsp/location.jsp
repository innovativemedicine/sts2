<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<tr>
	<td>
		<h2>Edit Locations</h2>

		<form class="form-horizontal" method="post">

			<div class="control-group">
				<label class="control-label">Location Name</label>
				<div class="controls">
					<spring:bind path="command.name">
						<input required type="text" maxlength="64" size="20" name="name"
							value="<c:out value="${status.value}"/>">
					</spring:bind>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Building</label>
				<div class="controls">
					<spring:bind path="command.building">
						<input type="text" maxlength="128" size="20" name="building"
							value="<c:out value="${status.value}"/>">
					</spring:bind>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Room</label>
				<div class="controls">
					<spring:bind path="command.room">
						<input type="text" maxlength="32" size="20" name="room"
							value="<c:out value="${status.value}"/>">
					</spring:bind>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Freezer</label>
				<div class="controls">
					<spring:bind path="command.freezer">
						<input type="text" maxlength="16" size="20" name="freezer"
							value="<c:out value="${status.value}"/>">
					</spring:bind>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Freezer Shelf</label>
				<div class="controls">
					<spring:bind path="command.freezerShelf">
						<input type="text" maxlength="16" size="20" name="freezerShelf"
							value="<c:out value="${status.value}"/>">
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
		<p>
		<table class="table">
			<tr class="info">
				<td><b>Location Name</b></td>
				<td><b>Building</b></td>
				<td><b>Room</b></td>
				<td><b>Freezer</b></td>
				<td><b>Freezer Shelf</b></td>
			</tr>

			<c:forEach items="${LLocations}" var="container">
				<tr>
					<td><a class="act act-primary"
						href="<c:url value="/location.htm">
			<c:param name="locationId" value="${container.locationId}"/></c:url>">
							<c:out value="${container.name}" />
					</a></td>
					<td><c:out value="${container.building}" /></td>
					<td><c:out value="${container.room}" /></td>
					<td><c:out value="${container.freezer}" /></td>
					<td><c:out value="${container.freezerShelf}" /></td>

				</tr>
			</c:forEach>

		</table>

	</td>
</tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>