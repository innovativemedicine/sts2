<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Search Sample</h2>

<form method="post" enctype="multipart/form-data">
	<div class="row-fluid">
		<div class="span5">

			<div class="alert alert-info">Search by Sample ID</div>


			<div class="form-inline">
				<label>Sample &nbsp;ID:</label>
				<input autocomplete="off" type="text" class="setFocus span4" name="sampleIdFrom">
				<label>TO</label>
				<input autocomplete="off" type="text" class="span4" name="sampleIdTo" placeholder="Optional">
			</div>
			<p></p>
			<div class="form-inline">

				<label>External ID:</label>
				<input autocomplete="off" type="text" class="span4" name="externalIdFrom">
				<label>TO</label>
				<input autocomplete="off" type="text" class="span4" name="externalIdTo" placeholder="Optional">
			</div>
			<p></p>
			<div class="alert alert-info">Search by Sample List</div>


			<div>
				<label class="radio inline">
					<input type="radio" name="optionsID" value="internal" checked>
					Sample ID
				</label>
				<label class="radio inline">
					<input type="radio" name="optionsID" value="external">
					External ID
				</label>

				<textarea class="span11" name="sampleIdsInTextArea" rows="5" placeholder="Separate samples by commas or new lines"></textarea>

				<p></p>
				<input class="btn" type="submit" name="Submit" value="Search">
			</div>
		</div>

		<div class="span3">
			<div class="alert alert-info span11">Filter by Project</div>

			<select class="span11" size="15" name="projectFilter" multiple>
				<option value="" selected>All Projects</option>

				<c:forEach items="${LProjects}" var="project" varStatus="row">
					<option value="<c:out value="${project.projectId}"/>">
						<c:out value="${project.name}" />
					</option>
				</c:forEach>
			</select>

		</div>
		<div class="span3">

			<div class="alert alert-info span10">Filter by Sample Types</div>

			<select class="span10" size="15" name="sampleTypeFilter" multiple>
				<option value="" selected>All Sample Types</option>

				<c:forEach items="${LSampleTypes}" var="sampleType" varStatus="row">
					<option value="<c:out value="${sampleType.sampleTypeId}"/>">
						<c:out value="${sampleType.name}" />
						<c:if test="${sampleType.vials != null}">(<c:out value="${sampleType.vials}" />)</c:if>
					</option>
				</c:forEach>
			</select>
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>