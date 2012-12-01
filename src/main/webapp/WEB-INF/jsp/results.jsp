<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<h2>Search Results</h2>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp"%>

<div class="left">

	<form method="post" enctype="multipart/form-data">
		<table class="details">
			<tr>
				<td><input type="submit" name="action" value="AND"> <input
					type="submit" name="action" value="SEARCH"></td>
			</tr>
			<tr>
				<td><spring:bind path="command.searchField">
						<select name="searchField" size="1">
							<c:forEach items="${searchFields.list}" var="field">
								<option value="<c:out value="${field}"/>">
									<c:out value="${field}" />
								</option>
							</c:forEach>
						</select>
					</spring:bind> <spring:bind path="command.operator">
						<select name="operator" size="1">
							<c:forEach items="${operatorList.list}" var="operation">
								<option value="<c:out value="${operation}"/>">
									<c:out value="${operation}" />
								</option>
							</c:forEach>
						</select>
					</spring:bind> <spring:bind path="command.searchItem">
						<input name="searchItem" type="text">
						<FONT color="red"> <B><c:out
									value="${status.errorMessage}" /></B>
						</FONT>
					</spring:bind></td>
			</tr>
			<tr>
				<td>The search item of Date should be in format yyyy-mm-dd <br>
					For "between" operator, using "AND" between the two search items.
				</td>
			</tr>
			<tr>
				<td>Select assay(s) from the list to search.<br> <select
					name="assayIds" size="10" multiple>
						<c:forEach items="${assayList}" var="assay">
							<option value="<c:out value="${assay.assayId}"/>">
								<c:out value="${assay.name}" />
							</option>
						</c:forEach>
				</select>

				</td>
			</tr>
			<tr>
				<td>Upload a sampleId list file: <input type="file" name="file" />
				</td>
			</tr>
			<tr>
				<td>Output format:<br> <input type="radio" name="format"
					value="hyperLink"> HTML format <br> <input
					type="radio" name="format" value="plainText"> Excel format
					<br>
				</td>
			</tr>

			<tr>
				<td><input type="checkbox" name="linkageFormat" value="yes">
					Linkage format <br></td>
			</tr>
		</table>
	</form>
</div>
<div style="float: none">
	<table class="searchCriteria">
		<tr>
			<td><b>Your search criteria:</b> <br> <c:out
					value="${currentCriteria}" escapeXml="false" /></td>
		</tr>
	</table>
</div>

<div class="clear"></div>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>