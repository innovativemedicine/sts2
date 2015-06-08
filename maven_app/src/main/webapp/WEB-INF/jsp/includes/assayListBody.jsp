  <h3>Assay List</h3>
  <table class="table">
    <tr class="info">
    	<td><b>Name </b></td>
    	<td><b>Gene Location</b></td>
    	<td><b>Note</b></td>
    </tr>
    
    <c:forEach items="${assayList}" var="assay">
    <tr> 
    	<td>
    	<a class="act act-primary" href="<c:url value="/assayDetails.htm"><c:param name="assayId" value="${assay.assayId}"/></c:url>"><c:out value="${assay.name}"/></a>&nbsp;
    	</td>
    	<td><c:out value="${assay.location}"/>&nbsp;</td>
    	<td><c:out value="${test.note}"/>&nbsp;</td>
    </tr>
    </c:forEach>
   
  </table>
