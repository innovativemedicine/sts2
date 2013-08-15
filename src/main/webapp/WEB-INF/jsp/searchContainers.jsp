<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Search Containers:</h2>

<form method="post" enctype="multipart/form-data">
	<div class="row-fluid">
		<div class="span6">
			<table class="table">
				<tr class="info">
					<td colspan="2"><b>Search by Container ID</b></td>
				</tr>

				<tr>
					<td class="form-inline"><label>Container ID:</label> &nbsp; <input
						class="span8 setFocus" name="containerIdFrom"></td>

					<td><input class="btn" type="submit" name="Submit"
						value="Search"></td>
				</tr>
			</table>
		</div>
		<div class="span3">
			<table class="table">
				<tr class="info">
					<td><b>Filter by Project</b></td>
				</tr>
				<tr>
					<td><select class="span12" name="projectFilter">
							<option value="" selected>All Projects</option>

							<c:forEach items="${LProjects}" var="project" varStatus="row">
								<option value="<c:out value="${project.projectId}"/>">
									<c:out value="${project.name}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>

		</div>

	</div>

</form>
<div class="row-fluid">
	<div class="span9">
		<%@ include file="/WEB-INF/jsp/includes/containerListBody.jsp"%>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>