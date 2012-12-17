<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<h2>Search Sample:</h2>

<form method="post" enctype="multipart/form-data">
	<div class="left">
		<table style="width: 280px">
			<tr>
				<th colspan="2">Search by Sample ID</th>
			</tr>

			<tr>
				<td>Sample ID:</td>
				<td><input class="setFocus" name="sampleIdFrom"></td>
			</tr>
			<tr>
				
				<td>to</td>
				<td><input name="sampleIdTo"><span class="label">Optional</span></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="Submit"
					value="SEARCH">
			</tr>
		</table>
		<br>
		<table style="width: 280px">
			<tr>
				<th>Search by Sample List <a class="unhider" href="#">[Show]</a></th>
			</tr>
			<tr class="hide">
				<td>Enter/Upload list of Sample ID:</td>
			<tr class="hide">
				<td><span style="font-size: 12px">(Separate by commas or
						new lines)</span> <br> <textarea name="sampleIdsInTextArea"
						rows="10" cols="30"></textarea></td>
			</tr>
			<tr class="hide">
				<td><input type="file" name="file" /></td>
			</tr>
			<tr class="hide">
				<td><input type="submit" name="Submit" value="SEARCH">
					<%-- 					<a href="<c:url value="/samples.htm"></c:url>">[Use Advanced --%>
					<!-- 						Search]</a></td> -->
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
				<th><b>Filter by Sample Types</b></th>
			</tr>
			<tr>
				<td><select size="15" name="sampleTypeFilter" multiple>
						<option value="" selected>All Sample Types</option>

						<c:forEach items="${LSampleTypes}" var="sampleType"
							varStatus="row">
							<option value="<c:out value="${sampleType.sampleTypeId}"/>">
								<c:out value="${sampleType.name}" /> <c:if test="${sampleType.vials != null}">(<c:out value="${sampleType.vials}" />)</c:if>
							</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>

	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>