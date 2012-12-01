  <h3>Run List</h3>
  <table class="details">
    <tr>
    	<th>&nbsp;</th>
		<th>Project </th>
    	<th>Date</th>
    	<th>Test(s)</th>
    	<th>Plate(s)</th>
		<th>Note</th>
    </tr>
    
    <c:forEach items="${runList}" var="run">
    <tr> 
    	<td>
    	<a class="button"
    		href="<c:url value="/runDetails.htm"><c:param name="runId" value="${run.runId}"/></c:url>">
    		<span>Details</span>
    	</a>
    	</td>
    	<td><c:out value="${run.project.name}"/></td>
    	<td style="width: 90px"><fmt:formatDate pattern="yyyy-MM-dd" value="${run.runDate}"/></td>
    	<td><c:out value="${run.testNameList}"/></td>
		<td><c:out value="${run.plateNameList}"/></td>
		<td><c:out value="${run.note}"/></td>
    </tr>
    </c:forEach>
   
  </table>
