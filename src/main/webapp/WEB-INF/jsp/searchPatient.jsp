<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

<h2>Update Sample</h2>
	<form method="post">
		<table class="details">

				<tr><td>
				Please enter your internal sample Id: 
				<br>
				<input type="text" name="intSampleId">
				</td></tr>
			
				<tr><td>
				<input type="submit" name="Submit" value="Submit">
			 	<input type="reset" name="Reset" value="Reset">
			
				</td></tr>
		 
		</table>
	</form>
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>