<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

<table width="70%" border="0" class="details">
    
<form method="post" enctype="multipart/form-data">
	<tr><td>
	How many labels do you want to scape?
	<br>
	<input type="text" name="scapeNo">
	</td></tr>


	<tr><td>
          Upload patientId list file:<br>
          <input type="file" name="file"/>
          
    </td></tr>

	<tr><td>
	<input type="submit" name="Submit" value="Print">
 	<input type="reset" name="Reset" value="Reset">

	</td></tr>
 
    
</form> 
</table>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>