<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
		
<tr><td>

<h2>Register Multiple Sample</h2>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>
 
	<div>
		How many samples to register? 
		<input type="text" id="numSamplesText" name="numSamplesText" />	
		<a class="button" href="javascript:void(0)" onclick='multiForm();'><span>Generate Form</span></a>
	</div>
	<br>

<%@ include file="/WEB-INF/jsp/includes/multiSampleForm.jsp" %>

</td></tr>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>