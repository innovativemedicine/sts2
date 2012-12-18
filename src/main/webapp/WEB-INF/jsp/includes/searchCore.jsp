<div class="left">
	<div class="searchBuilder">
	<form method="post">
		<table class="searchBuilder">
			
			<tr>
				<td><input class="button buttonPad" type="submit" name="action" value="AND"> 
				<input class="button buttonPad" type="submit" name="action" value="SEARCH"></td>
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
	</form>
	</div>
</div>

<div style="float: none">
	<table class="searchCriteria" >
		<tr>
			<td ><b>Your search criteria:</b> <br> <c:out
					value="${currentCriteria}" escapeXml="false" /></td>
		</tr>
	</table>
</div>

<div class="clear"></div>