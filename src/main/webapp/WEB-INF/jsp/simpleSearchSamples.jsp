
<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<h2>
	Search Sample:
</h2>


<p>
<form method="post" enctype="multipart/form-data">
	<div class="left">
		<table>

			<tr>
				<th>Filter by Sample ID</th>
			</tr>

			<tr>
				<td>Enter Internal Sample ID:
				<span style="font-size: 12px">(Separate by commas or new lines)</span>
					
					<br> <textarea name="sampleIdsInTextArea" rows="15"
						cols="40"></textarea>

				</td>
			</tr>
			<tr>
				<td>Upload Sample ID List file: <br> <input type="file"
					name="file" />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><input type="submit" name="Submit" value="SEARCH">
					<a href="<c:url value="/samples.htm"></c:url>">[Use Advanced
						Search]</a></td>
			</tr>
		</table>
	</div>
	<div class="left">
		<table style="width: 200px">
			<tr>
				<th>Filter by Project</th>
			</tr>
			<tr>
				<td><select size="25" name="projectFilter" multiple>
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
				<th><b>Filter by Sample Types</b></th>
			</tr>
			<tr>
				<td><select size="25" name="sampleTypeFilter" multiple>
						<option value="" selected>All Sample Types</option>

						<c:forEach items="${LSampleTypes}" var="sampleType"
							varStatus="row">
							<option value="<c:out value="${sampleType.sampleTypeId}"/>">
								<c:out value="${sampleType.name}" />
							</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>

	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>