<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<p>
<h2> Poject List </h2>
  <table width="70%" border="0" class="details">
    <tr>
    	<th>Reagent Name</th>
    	<th>Concentration</th>
    	<th>Note</th>
    </tr>
    
    <c:forEach items="${reagentList}" var="reagent">
    <tr> 
    	<td>
    	<a href="<c:url value="/reagentDetails.htm"><c:param name="reagentId" value="${reagent.reagentId}"/></c:url>"><c:out value="${reagent.name}"/></a>
    	</td>
    	<td><c:out value="${reagent.concentration}"/>&nbsp;</td>
    	<td><c:out value="${reagent.note}"/> &nbsp;</td>
        
    </tr>
    </c:forEach>
   
  </table>
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>