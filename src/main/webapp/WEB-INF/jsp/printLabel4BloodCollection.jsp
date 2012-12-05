<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp"%>

<form method="post" enctype="multipart/form-data">

	<table>
		<tr>
			<td>How many labels do you want to print? <br> <input
				type="text" name="scapeNo">
			</td>
		</tr>
		<tr>
			<td>Upload patientId list file:<br> <input type="file"
				name="file" />
			</td>
		</tr>
		<tr>
			<td><input type="submit" name="Submit" value="Print"> <input
				type="reset" name="Reset" value="Reset"></td>
		</tr>
	</table>
</form>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>