<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

<table width="70%" border="0" class="details">
    
<form method="post">
	<tr><td>
	Please enter your internal sample Id: 
	<br>
	<input type="text" name="intSampleId">
	</td></tr>

	

	<tr><td>
	<input type="submit" name="Submit" value="Submit">
 	<input type="reset" name="Reset" value="Reset">

	</td></tr>
 
    
</form> 
</table>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>