<h3>Run List</h3>
  <table class="table table-condensed">
    <tr class="info">
    	<td><b>Run</b></td>
		<td><b>Project </b></td>
    	<td><b>Date</b></td>
    	<td><b>Test(s)</b></td>
    	<td><b>Plate(s)</b></td>
		<td><b>Note</b></td>
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