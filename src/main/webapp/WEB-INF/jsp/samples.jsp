<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<h2>
	<fmt:message key="sample.search.title" />
</h2>

<form method="post" enctype="multipart/form-data">
	<div class="left">

		<table>

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

		</table>
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

	<p>
	
	<table>


		<tr>
			<th>Search by Sample Id</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>

			<td>Enter or paste your Internal sample Id here, delimited by
				comma, or one sample one line <br><TEXTAREA name="sampleIdsInTextArea"
					rows="10" cols="60">
  
   </TEXTAREA>
</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>


		<tr>
			<td>Upload a sampleId list file: <input type="file" name="file" />
			</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>



		<tr>
			<td><input type="checkbox" name="useFor" value="useFor">
				For printing labels</td>
		</tr>

	</table>
</form>

</td>
</tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>
