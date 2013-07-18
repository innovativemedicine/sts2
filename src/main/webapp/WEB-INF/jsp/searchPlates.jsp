<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Search Plates</h2>

<form method="post" enctype="multipart/form-data">

	<div class="row-fluid">
		<div class="span6">
			<table class="table">
				<tr class="info">
					<td colspan="2"><b>Search by Plate ID</b></td>
				</tr>

				<tr>
					<td class="form-inline"><label>Plate&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID:</label>
						<input type="text" class="setFocus span4" name="plateIdFrom">
						<label>TO</label> <input type="text" class="span4"
						name="plateIdTo" placeholder="Optional"></td>
				</tr>
				<tr>
					<td class="form-inline"><label>External ID:</label> <input
						type="text" class="span4" name="externalIdFrom"> <label>TO</label>
						<input type="text" class="span4" name="externalIdTo"
						placeholder="Optional"></td>
				</tr>
			</table>

			<table class="table">
				<tr class="info">
					<td><b>Search by Plate List</b></td>
				</tr>
				<tr>
					<td><span style="font-size: 12px">(Separate by commas
							or new lines)</span> <br> <textarea class="span10"
							name="plateIdsInTextArea" rows="5"></textarea> <br> <input
						class="btn" type="submit" name="Submit" value="Search"></td>
				</tr>

			</table>
		</div>
		<div class="span3">
			<table class="table">
				<tr class="info">
					<td><b>Filter by Project</b></td>
				</tr>
				<tr>
					<td><select class="span12" size="10" name="projectFilter"
						multiple>
							<option value="" selected>All Projects</option>

							<c:forEach items="${LProjects}" var="project" varStatus="row">
								<option value="<c:out value="${project.projectId}"/>">
									<c:out value="${project.name}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<!-- 		</div> -->
			<!-- 		<div class="span3"> -->
			<table class="table">
				<tr class="info">
					<td><b>Filter by Plate Types</b></td>
				</tr>
				<tr>
					<td><select class="span12" name="plateTypeFilter">
							<c:forEach items="${LPlateTypes}" var="plateType" varStatus="row">
								<option value="<c:out value="${plateType.containerTypeId}"/>">
									<c:out value="${plateType.name}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>