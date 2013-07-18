<table class="table table-condensed">
	<tr class="info">
		<td><b>Name</b></td>
		<td><b>External Name</b></td>
		<td><b>Location</b></td>
		<td><b>Container Type</b></td>
		<td><b>Created Date</b></td>
		<td><b>Project</b></td>
		<td><b>Status</b></td>
	</tr>

	<c:forEach items="${containerList}" var="container">
		<tr>
			<td><a
				href="<c:url value="/containerDetails.htm"><c:param name="containerId" value="${container.containerId}"/></c:url>"><c:out
						value="${container.name}" /></a>&nbsp;</td>
			<td><c:out
					value="${container.extContainerId}" /></td>
			<td><c:out
					value="${container.location.name}" /></td>

			<td><c:out
					value="${container.containerType.name}" /></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd"
					value="${container.createdDate}" /></td>
			<td><c:out value="${container.project.name}" /></td>
			<td><c:out value="${container.status}" /></td>


		</tr>
	</c:forEach>

</table>