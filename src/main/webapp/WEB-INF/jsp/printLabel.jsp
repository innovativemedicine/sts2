<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

<table width="70%" border="0" class="details">
    
<form method="post">
	<tr><td>
	How many labels do you want to scape?
	<br>
	<input type="text" name="scapeNo">
	</td></tr>

	<tr><td>
	Please select for what label?
	<br>
	 <select name="printerType" size="1">
	     <option value=plate>Plate label</option>
		 <option value=box>Box label</option>
      </select>
	</td></tr>

	<tr><td>
	<input type="submit" name="Submit" value="Print">
 	<input type="reset" name="Reset" value="Reset">

	</td></tr>
 
    
</form> 
</table>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>