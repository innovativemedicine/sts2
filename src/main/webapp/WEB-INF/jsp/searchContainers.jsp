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
					<td class="form-inline"><label>Container ID:</label> &nbsp;
					<input class="span8 setFocus" name="containerIdFrom"></td>
				</tr>
			</table>

			<input type="hidden" name="containerIdTo">

			<table class="table">
				<tr class="info">
					<td><b>Search by Container List</b></td>
				</tr>
				
				<tr>
					<td><span style="font-size: 12px">(Separate by commas
							or new lines)</span> <br> <textarea class="span10"
							name="containerIdsInTextArea" rows="5"></textarea>
				<br>
					<input class="btn" type="submit" name="Submit"
						value="Search">
						</td>
				</tr>
			</table>
		</div>
		<div class="span3">
			<table class="table">
				<tr class="info">
					<td><b>Filter by Project</b></td>
				</tr>
				<tr>
					<td><select class="span12" size="15" name="projectFilter" multiple>
							<option value="" selected>All Projects</option>

							<c:forEach items="${LProjects}" var="project" varStatus="row">
								<option value="<c:out value="${project.projectId}"/>">
									<c:out value="${project.name}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>

			<table class="table">
				<tr class="info">
					<td><b>Filter by Container Types</b></td>
				</tr>
				<tr>
					<td><select class="span12" size="4" name="containerTypeFilter" multiple>
							<c:forEach items="${LContainerTypes}" var="containerType"
								varStatus="row">
								<option
									value="<c:out value="${containerType.containerTypeId}"/>">
									<c:out value="${containerType.name}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>

		</div>
	</div>

</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>