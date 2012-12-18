<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

	<form method="post">
		<div style="display:table-cell; vertical-align:bottom"> 
			<h2 class="noMargin"> Sample List </h2>
	   		
			<a class="button" href="<c:url value="/addSample2Container.htm"></c:url>"><span>Assign Container</span></a>
			<input class="button buttonPad" type="submit" name="action" value="Export Data">
			<input class="button buttonPad" type="submit" name="action" value="Print Labels">
		</div>	
	</form>
   	
   	<p>

  <table class="details">
    <tr>
    	<th>Internal ID</th>
    	<th>External ID</th>
    	<th>Sample Type</th>
		<th>Project</th>
		<th>Note</th>
		<th>Status</th>
		<th>Location</th>
    </tr>
    
    <c:forEach items="${sampleList}" var="sample">
    <tr> 
      <c:if test="${sample.sampleId != -1}">

		<td>
    	<a href="<c:url value="/sampleDetails.htm">
    		<c:param name="sampleId" value="${sample.sampleId}"/></c:url>">
    		<c:out value="${sample.patient.intSampleId}"/></a>
    	</td>
    	<td><c:out value="${sample.patient.extSampleId}"/></td>
    	<td><c:out value="${sample.sampleType.name}"/></td>
    	<td><c:out value="${sample.patient.project.name}"/></td>	
		<td><c:out value="${sample.patient.note}"/></td>
    	<td><c:out value="${sample.status}"/></td>
    	<td></td>
	  </c:if>
	  
	  <c:if test="${sample.sampleId == -1}">
		<td>
    	<c:out value="${sample.patient.intSampleId}"/>&nbsp;
    	</td>
    	<td>&nbsp;</td>
    	<td>&nbsp;</td>
	  </c:if>
    </tr>
    </c:forEach>
   
  </table>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>