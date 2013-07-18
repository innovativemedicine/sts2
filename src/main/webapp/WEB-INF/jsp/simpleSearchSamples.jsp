<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Search Sample</h2>

<form method="post" enctype="multipart/form-data">
	<div class="row-fluid">
		<div class="span6">

			<table class="table">
				<tr class=info>
					<td colspan="2"><b>Search by Sample ID</b></td>
				</tr>

				<tr>
					<td class="form-inline"><label>Sample &nbsp;ID:</label> <input
						type="text" class="setFocus span4" name="sampleIdFrom"> <label>TO</label>
						<input type="text" class="span4" name="sampleIdTo"
						placeholder="Optional"></td>
				</tr>
				<tr>
					<td class="form-inline"><label>External ID:</label> <input
						type="text" class="span4" name="externalIdFrom"> <label>TO</label>
						<input type="text" class="span4" name="externalIdTo"
						placeholder="Optional"></td>
				</tr>
			</table>

			<table class="table">
				<tr class=info>
					<td><b>Search by Sample List</b></td>
				</tr>
				<tr>
					<td><span style="font-size: 12px">(Separate by commas
							or new lines)</span> <br> <textarea class="span10"
							name="sampleIdsInTextArea" rows="5"></textarea> <!-- 				<tr> -->
						<!-- 					<td><input type="file" name="file" /></td> --> <!-- 				</tr> -->
						<br> <input class="btn" type="submit" name="Submit"
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
					<td><select class="span12" size="15" name="projectFilter"
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
		</div>
		<div class="span3">

			<table class="table">
				<tr class="info">
					<td><b>Filter by Sample Types</b></td>
				</tr>
				<tr>
					<td><select class="span12" size="15" name="sampleTypeFilter"
						multiple>
							<option value="" selected>All Sample Types</option>

							<c:forEach items="${LSampleTypes}" var="sampleType"
								varStatus="row">
								<option value="<c:out value="${sampleType.sampleTypeId}"/>">
									<c:out value="${sampleType.name}" />
									<c:if test="${sampleType.vials != null}">(<c:out
											value="${sampleType.vials}" />)</c:if>
								</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>

		</div>
	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>