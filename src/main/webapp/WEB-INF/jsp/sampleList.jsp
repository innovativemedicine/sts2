<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

	<div style="display:table-cell; vertical-align:bottom"> 
		<font style="vertical-align:bottom"size=5> Sample List </font>
   		<a class="button" href="<c:url value="/selectSampleInfo4Output.htm"></c:url>"><span>Export Data</span></a>
		<a class="button" href="<c:url value="/addSample2Container.htm"></c:url>"><span>Assign Container</span></a>

   	</div>	
   	<p>
   	<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

  <table class="details">
    <tr>
    	<th>Internal ID</th>
    	<th>External ID</th>
    	<th>External ID 2</th>
    	<th>Sample Type</th>
		<th>Project</th>
		<th>Note</th>
		<th>Sample Type Name</th>
		<th>ST Suffix</th>
		<th>ST Vial</th>
		
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
    	<td><c:out value="${sample.patient.anotherExtSampleId}"/></td>
    	<td><c:out value="${sample.sampleType.sampleTypeId}"/></td>
    	<td><c:out value="${sample.patient.project.name}"/></td>	
		<td><c:out value="${sample.patient.note}"/></td>
    	<td><c:out value="${sample.sampleType.name}"/></td>
 	    <td><c:out value="${sample.sampleType.suffix}"/></td>
 	    <td><c:out value="${sample.sampleType.vials}"/></td>
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
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>