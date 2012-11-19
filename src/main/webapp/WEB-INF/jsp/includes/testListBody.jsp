  <h3>Test List</h3>
  <table width="70%" border="0" class="details">
    <tr>
    	<th>Name </th>
    	<th>Instrument</th>
    	<th>Protocal</th>
    	<th>Project</th>
    </tr>
    
    <c:forEach items="${testList}" var="test">
    <tr> 
    	<td>
    	<a href="<c:url value="/testDetails.htm"><c:param name="testId" value="${test.testId}"/></c:url>"><c:out value="${test.name}"/></a>&nbsp;
    	</td>
    	<td><c:out value="${test.instrument.name}"/>&nbsp;</td>
    	<td><c:out value="${test.protocal}"/>&nbsp;</td>
    	<td><c:out value="${test.project.name}"/>&nbsp;</td>
    </tr>
    </c:forEach>
   
  </table>