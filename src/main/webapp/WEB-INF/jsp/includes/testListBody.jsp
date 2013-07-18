  <h3>Test List</h3>
  <table class="table">
    <tr class="info">
    	<td><b>Name </b></td>
    	<td><b>Instrument</b></td>
    	<td><b>Protocol</b></td>
    	<td><b>Project</b></td>
    </tr>
    
    <c:forEach items="${testList}" var="test">
    <tr> 
    	<td>
    	<a class="act act-primary" href="<c:url value="/testDetails.htm"><c:param name="testId" value="${test.testId}"/></c:url>"><c:out value="${test.name}"/></a>&nbsp;
    	</td>
    	<td><c:out value="${test.instrument.name}"/>&nbsp;</td>
    	<td><c:out value="${test.protocal}"/>&nbsp;</td>
    	<td><c:out value="${test.project.name}"/>&nbsp;</td>
    </tr>
    </c:forEach>
   
  </table>