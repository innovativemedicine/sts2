<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
		
<h2>Register Samples</h2>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>
 
 <!-- 				Add File Upload option for addMultiSamples (manifest file). Look at saveSamplesInBatch for ideas -->
 
	<div>
		Number of samples?
		<input type="text" id="numSamplesText" name="numSamplesText" size="1" value="<c:out value="${param.ns}"/>"/>	
		<a class="button" href="javascript:void(0)" onclick='multiForm();'><span>Generate Form</span></a>
	</div>
	<br>

<%@ include file="/WEB-INF/jsp/includes/multiSampleForm.jsp" %>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>