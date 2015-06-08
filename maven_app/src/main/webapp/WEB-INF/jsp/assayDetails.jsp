<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Assay Details</h2>
<div class="alert alert-error">Warning: You can only delete this
	assay if there are no genotype results associated with this assay.</div>
<a class="btn"
	href="<c:url value="/editAssay.htm"><c:param name="assayId" value="${command.assayId}"/></c:url>">Edit</a>
<a class="btn"
	href="<c:url value="/deleteAssay.htm"><c:param name="assayId" value="${command.assayId}"/></c:url>"
	onclick="return (confirm('Are you sure you want to delete this assay? Read the warning carefully before you confirm!')) ">Delete</a>
<p>
<table class="table">

	<tr>
		<td>Name</td>
		<td><c:out value="${command.name}" /></td>
	</tr>

	<tr>
		<td>Gene Location</td>
		<td><c:out value="${command.location}" /></td>
	</tr>

	<tr>
		<td>Note</td>
		<td><c:out value="${command.note}" /></td>
	</tr>
</table>

<h3>Project List</h3>
<table class="table">
	<tr class="info">
		<td><b>Projects</b></td>
	</tr>
	<tr>
		<c:forEach items="${command.projects}" var="project">
			<td><a
				href="<c:url value="/projectDetails.htm"><c:param name="projectId" value="${project.projectId}"/></c:url>"
				target="_blank"><c:out value="${project.name}" /></a></td>
		</c:forEach>
	</tr>
</table>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>