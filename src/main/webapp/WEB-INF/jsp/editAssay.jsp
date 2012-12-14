<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Edit Assay</h2>

<form method="post">

	<table class="details">

		<tr>
			<td>Assay Name</td>
			<td><spring:bind path="command.name">
					<INPUT type="text" maxlength="255" size="30" name="name"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind></td>
		</tr>

		<tr>
			<td>Assay location in Gene</td>
			<td><spring:bind path="command.location">
					<INPUT type="text" maxlength="255" size="30" name="location"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>

		<tr>
			<td>Note</td>
			<td><spring:bind path="command.note">
					<INPUT type="text" maxlength="255" size="30" name="note"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>

		<tr>
			<td colspan="2">
				<p>
					<input type="submit" name="Submit" value="Save"> <input
						type="reset" name="Submit2" value="Reset">
				</p>
			</td>
		</tr>

	</table>

</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>