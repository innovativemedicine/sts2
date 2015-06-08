<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<h2>Search Results</h2>

<div class="row-fluid">
	<div class="span9">
		<form method="post" enctype="multipart/form-data">
			<table class="table">
				<tr>
					<td>
						<h4>Select Assays</h4> <select name="assayIds" size="10" multiple>
							<c:forEach items="${assayList}" var="assay">
								<option value="<c:out value="${assay.assayId}"/>">
									<c:out value="${assay.name}" />
								</option>
							</c:forEach>
					</select> <br> <input type="submit" name="action" value="SEARCH"
						class="btn">
					</td>
				</tr>
				<tr>
					<td>
						<h4>Upload Sample IDs</h4> <input type="file" name="file" />
					</td>
				</tr>
				<tr>
					<td class="form-inline">
						<h4>Output Format</h4> <label class="radio inline"> <input
							class="inline" type="radio" name="format" value="hyperLink">
							HTML format
					</label> <label class="radio inline"> <input class="inline"
							type="radio" name="format" value="plainText"> Excel
							format
					</label> <label class="checkbox inline"> <input class="inline"
							type="checkbox" name="linkageFormat" value="yes"> Linkage
							format </label>
					</td>
				</tr>

				<tr>
					<td><br></td>
				</tr>
			</table>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>