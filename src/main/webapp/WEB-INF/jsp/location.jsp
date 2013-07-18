<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<tr>
	<td>
		<h2>Edit Locations</h2>

		<form method="post" action="location.htm">
			<table class="table" class="details">

				<tr>
					<th>Location Name</th>
					<td><spring:bind path="command.name">
							<INPUT type="text" maxlength="64" size="20" name="name"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind></td>
				</tr>

				<tr>
					<th>Building</th>
					<td><spring:bind path="command.building">
							<INPUT type="text" maxlength="128" size="20" name="building"
								value="<c:out value="${status.value}"/>">
						</spring:bind></td>
				</tr>

				<tr>
					<th>Room</th>
					<td><spring:bind path="command.room">
							<INPUT type="text" maxlength="32" size="20" name="room"
								value="<c:out value="${status.value}"/>">
						</spring:bind></td>
				</tr>
				<tr>
					<th>Freezer</th>
					<td><spring:bind path="command.freezer">
							<INPUT type="text" maxlength="16" size="20" name="freezer"
								value="<c:out value="${status.value}"/>">
						</spring:bind></td>
				</tr>
				<tr>
					<th>Freezer Shelf</th>
					<td><spring:bind path="command.freezerShelf">
							<INPUT type="text" maxlength="16" size="20" name="freezerShelf"
								value="<c:out value="${status.value}"/>">
						</spring:bind></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" name="Submit"
						value="Edit"> <input type="submit" name="Submit"
						value="New"></td>
				</tr>
			</table>

		</form>
		<p>
		<table class="table" class="details">
			<tr>
				<th>Location Name</th>
				<th>Building</th>
				<th>Room</th>
				<th>Freezer</th>
				<th>Freezer Shelf</th>
			</tr>

			<c:forEach items="${LLocations}" var="container">
				<tr>
					<td><a
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