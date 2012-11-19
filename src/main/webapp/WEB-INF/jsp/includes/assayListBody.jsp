  <h3>Assay List</h3>
  <table width="70%" border="0" class="details">
    <tr>
    	<th>Name </th>
    	<th>Gene Location</th>
    	<th>Note</th>
    </tr>
    
    <c:forEach items="${assayList}" var="assay">
    <tr> 
    	<td>
    	<a href="<c:url value="/assayDetails.htm"><c:param name="assayId" value="${assay.assayId}"/></c:url>"><c:out value="${assay.name}"/></a>&nbsp;
    	</td>
    	<td><c:out value="${assay.location}"/>&nbsp;</td>
    	<td><c:out value="${test.note}"/>&nbsp;</td>
    </tr>
    </c:forEach>
   
  </table>
