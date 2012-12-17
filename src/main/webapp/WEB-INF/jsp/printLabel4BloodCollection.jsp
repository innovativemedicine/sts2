<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp"%>

<h2> Labels 4 Blood:</h2>
<form method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>How many labels do you want to print?</td>
			<td> <input
				type="text" name="scapeNo">
			</td>
		</tr>
		<tr>
			<td>Upload Sample ID List:</td>
			<td> <input type="file"
				name="file" />
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" name="Submit" value="Print"> 
		</tr>
	</table>
</form>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>