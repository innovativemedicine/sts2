<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
		
<tr><td>

<h2>Register Multiple Sample</h2>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

<form method="post">
	How many samples would you like to register? <input type="text" name="numOrder" />
	<input type="button" onclick="tableTester(parseInt(numOrder.value, 10), 6);" value="Create Table">	
	<div id="FormFields" style="margin: 20px 0px;"></div>
</form>

</td></tr>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>