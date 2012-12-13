<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<h2>Search Plates:</h2>

<form method="post" enctype="multipart/form-data">
	<div class="left">
		<table style="width: 260px">
			<tr>
				<th colspan="2">Search by Plate ID</th>
			</tr>

			<tr>
				<td>Plate ID:</td>
				<td><input class="setFocus" name="plateIdFrom"></td>
			</tr>
			<tr>
				
				<td>to</td>
				<td><input name="plateIdTo"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="Submit"
					value="SEARCH">
			</tr>
		</table>
		<br>
		<table class="unhideOnHover" style="width: 260px">
			<tr>
				<th>Search by Plate List <a class="unhider" href="#">[Show]</a></th>
			</tr>
			<tr class="hide">
				<td>Enter/Upload list of Plate ID:</td>
			<tr class="hide">
				<td><span style="font-size: 12px">(Separate by commas or
						new lines)</span> <br> <textarea name="plateIdsInTextArea"
						rows="10" cols="30"></textarea></td>
			</tr>
			<tr class="hide">
				<td><input type="file" name="file" /></td>
			</tr>
			<tr class="hide">
				<td><input type="submit" name="Submit" value="SEARCH">
			</tr>
		</table>
	</div>
	<div class="left">
		<table style="width: 200px">
			<tr>
				<th>Filter by Project</th>
			</tr>
			<tr>
				<td><select size="15" name="projectFilter" multiple>
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
	<div>
		<table style="width: 200px">
			<tr>
				<th><b>Filter by Plate Types</b></th>
			</tr>
			<tr>
				<td><select size="15" name="plateTypeFilter" multiple>
						<option value="" selected>All Plate Types</option>

						<c:forEach items="${LPlateTypes}" var="plateType"
							varStatus="row">
							<option value="<c:out value="${plateType.containerTypeId}"/>">
								<c:out value="${plateType.name}" />
							</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>

	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>