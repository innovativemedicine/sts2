<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Edit Investigators</h2>

<form method="post" action="investigator.htm">

	<table class="table" class="details">

		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Phone</th>
			<th>Email</th>
		</tr>
		<tr>
			<td><spring:bind path="command.name.fname">
					<input required type="text" maxlength="32" size="15"
						name="name.fname" value="<c:out value="${status.value}"/>">
				</spring:bind></td>

			<td><spring:bind path="command.name.lname">
					<input required type="text" maxlength="32" size="15"
						name="name.lname" value="<c:out value="${status.value}"/>">
				</spring:bind></td>

			<td><spring:bind path="command.contact.phone">
					<input type="text" maxlength="10" size="15" name="contact.phone"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>

			<td><spring:bind path="command.contact.email">
					<input type="text" maxlength="32" size="15" name="contact.email"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		<tr>
			<td colspan="4"><input type="submit" name="Submit" value="Edit">
				<input type="submit" name="Submit" value="New"></td>
		</tr>
	</table>

	<p>
</form>
<table class="table">
	<tr class="info">
		<td><b>Investigator Name</b></td>
		<td><b>Telephone Number</b></td>
		<td><b>Email</b></td>
	</tr>

	<c:forEach items="${LInvestigators}" var="container">
		<tr>
			<td><a class="act act-primary"
				href="<c:url value="/investigator.htm">
			<c:param name="investigatorId" value="${container.investigatorId}"/></c:url>">
					<c:out value="${container.name.fname}" />&nbsp; <c:out
						value="${container.name.lname}" />
			</a></td>
			<td><c:out value="${container.contact.phone}" /></td>
			<td><c:out value="${container.contact.email}" /></td>

		</tr>
	</c:forEach>

</table>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>