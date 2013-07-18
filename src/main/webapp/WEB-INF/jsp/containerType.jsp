<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Edit Container Types</h2>
<p class="alert">Note: Container type name must start with "Sample","Reagent" or "Plate".</p>
<form class="form-horizontal form-inline" method="post">

	<div class="control-group">
		<label class="control-label">Container Type:</label>
		<div class="controls">
			<spring:bind path="command.name">
				<INPUT type="text" maxlength="16" size="30" name="name"
					value="<c:out value="${status.value}"/>">
				<FONT color="red"> <B><c:out
							value="${status.errorMessage}" /></B>
				</FONT>
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Num. of Columns:</label>
		<div class="controls">
			<spring:bind path="command.columnNo">
				<INPUT type="text" maxlength="38" size="30" name="columnNo"
					value="<c:out value="${status.value}"/>">
				<FONT color="red"> <B><c:out
							value="${status.errorMessage}" /></B>
				</FONT>
			</spring:bind>
		</div>
	</div>


	<div class="control-group">
		<label class="control-label">Num. of Rows:</label>
		<div class="controls">
			<spring:bind path="command.rowNo">
				<INPUT type="text" maxlength="38" size="30" name="rowNo"
					value="<c:out value="${status.value}"/>">
				<FONT color="red"> <B><c:out
							value="${status.errorMessage}" /></B>
				</FONT>
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<div class="controls">
			<input class="btn" type="submit" name="Submit" value="Edit">
			<input class="btn" type="submit" name="Submit" value="New">
		</div>
	</div>

	<p>
</form>
<table class="table">
	<tr class="info">
		<td><b>Container Type Name</b></td>
		<td><b>Column Number</b></td>
		<td><b>Row Number</b></td>
	</tr>

	<c:forEach items="${containerTypes}" var="container">
		<tr>
			<td><a class="act act-primary"
				href="<c:url value="/containerType.htm">
			<c:param name="containerTypeId" value="${container.containerTypeId}"/></c:url>">
					<c:out value="${container.name}" />
			</a></td>
			<td><c:out value="${container.columnNo}" /></td>
			<td><c:out value="${container.rowNo}" /></td>

		</tr>
	</c:forEach>

</table>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>