<table class="table table-condensed">
	<tr class="info">
		<td><b>Name</b></td>
		<td><b>Location</b></td>
		<td><b>Container Type</b></td>
		<td><b>Created Date</b></td>
	</tr>

	<c:forEach items="${containerList}" var="container">
		<tr>
			<td><a class="act act-primary"
				href="<c:url value="/containerDetails.htm"><c:param name="containerId" value="${container.containerId}"/></c:url>"><c:out
						value="${container.name}" /></a>&nbsp;</td>

			<td><c:out value="${container.location.name}" /></td>
						
			<td><c:out value="${container.containerType.name}" /></td>

			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${container.createdDate}" /></td>

		</tr>
	</c:forEach>

</table>