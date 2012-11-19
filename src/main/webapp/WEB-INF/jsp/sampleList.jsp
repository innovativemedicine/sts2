<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>


<a href="<c:url value="/selectSampleInfo4Output.htm"></c:url>">
	Export data for these samples</a>&nbsp;

<a href="<c:url value="/addSample2Container.htm"></c:url>">
	Add these samples to containers</a>&nbsp;

<h2>Sample List </h2>
  <table width="80%" border="0" class="details">
    <tr>
    	<th>Internal ID</th>
    	<th>External ID</th>
    	<th>Sample Type</th>
		<th>Sample Duplicaton Number</th>
    </tr>
    
    <c:forEach items="${sampleList}" var="sample">
    <tr> 
      <c:if test="${sample.sampleId != -1}">

		<td>
    	<a href="<c:url value="/sampleDetails.htm">
    		<c:param name="sampleId" value="${sample.sampleId}"/></c:url>">
    		<c:out value="${sample.patient.intSampleId}"/></a>&nbsp;
    	</td>
    	<td><c:out value="${sample.patient.extSampleId}"/>&nbsp;</td>
    	<td><c:out value="${sample.sampleType.name}"/>&nbsp;</td>
		<td><c:out value="${sample.sampleDupNo}"/>&nbsp;</td>
	  </c:if>
	  
<!-- 
sample.sampleId==-1 mean: this sample does not exist!
Thanks, Gloria!
Jianan Xiao 2005-09-12
 -->
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