<h3>Project List</h3>

<table class="table" class="details">
	<tr class="info">
		<td><b>Project Name</b></td>
		<td><b>Description</b></td>
		<td><b>Created Date</b></td>
		<td><b>Investigator</b></td>
		<td><b>Status</b></td>
	</tr>

	<c:forEach items="${projectList}" var="project">
		<tr>
			<td><a class="act act-primary"
				href="<c:url value="/projectDetails.htm"><c:param name="projectId" value="${project.projectId}"/></c:url>"><c:out
						value="${project.name}" /></a></td>
			<td><c:out value="${project.description}" /></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd"
					value="${project.createdOn}" /></td>
			<td><c:out value="${project.investigator.name.fname}" /> &nbsp;<c:out
					value="${project.investigator.name.lname}" /></td>
			<td><c:out value="${project.status}" />&nbsp;</td>



		</tr>
	</c:forEach>

</table>