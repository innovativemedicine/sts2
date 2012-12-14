<h3>Run List</h3>
  <table>
    <tr>
    	<th></th>
		<th>Project </th>
    	<th>Date</th>
    	<th>Test(s)</th>
    	<th>Plate(s)</th>
		<th>Note</th>
    </tr>
    
    <c:forEach items="${runList}" var="run">
    <tr> 
    	<td>
    	<a href="<c:url value="/runDetails.htm"><c:param name="runId" value="${run.runId}"/></c:url>">View Details</a>
    	</td>
    	<td><c:out value="${run.project.name}"/></td>
    	<td><c:out value="${run.runDate}"/></td>
    	<td><c:out value="${run.testNameList}"/></td>
		<td><c:out value="${run.plateNameList}"/></td>
		<td><c:out value="${run.note}"/></td>
    </tr>
    </c:forEach>
   
  </table>