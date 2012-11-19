<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<tr><td>

<h2>Select the samples from the <c:out value="${totalSamples}"/> samples </h2>

<c:if test="${totalPage >= 1}">
<c:forEach begin="0" end="${totalPage-1}"  var="count">
<c:if test="${count != currentPage}">
<a href="<c:url value="/sampleList4select.htm"><c:param name="pageNo" value="${count}"/></c:url>">
</c:if>

<c:out value="${count*40}"/>~<c:out value="${(count+1)*40-1}"/>

<c:if test="${count != currentPage}">
</a>
</c:if>
&nbsp;
</c:forEach>
</c:if>

<!-- for the last page -->
<c:if test="${currentPage != totalPage}">
<a href="<c:url value="/sampleList4select.htm"><c:param name="pageNo" value="${totalPage}"/></c:url>">
</c:if>
<c:out value="${totalPage*40}"/>~<c:out value="${totalSamples}"/>

<c:if test="${currentPage != totalPage}">
</a>
</c:if>
&nbsp;
<br>
<br>

<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>



<form method="post" >
  <input type="submit" name="action" value="Put into cart">
  <input type="submit" name="action" value="Print label">
  <input type="submit" name="action" value="clear cart">
  <input type="reset" name="Reset" value="Reset">
  
  <table width="100%" border="0" class="details">
   <tr>
    	<th>&nbsp;</th>
		<th>Internal ID</th>
    	<th>External ID</th>
    	<th>Sample Type</th>
    	<th>Status</th>
    </tr>

	<c:set var="indx" value="0"/> 
	<c:forEach items="${sample4ThisPage}" var="sample">
    <tr> 
    	<c:if test="${sample.sampleId != -1}">

		<td><INPUT type="checkbox" 
         value='<c:out value="${indx}" />' 
         name='<c:out value="${indx}" />' checked>                        
          </td>
		<td><c:out value="${sample.patient.intSampleId}"/>&nbsp;</td>
    	<td><c:out value="${sample.patient.extSampleId}"/>&nbsp;</td>
    	<td><c:out value="${sample.sampleType.name}"/>&nbsp;</td>
    	<td><c:out value="${sample.status}"/>&nbsp;</td>
		 </c:if>
    	
       
    </tr>
	<c:set var="indx" value="${indx+1}"/> 
    </c:forEach>

</form> 
</table>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>