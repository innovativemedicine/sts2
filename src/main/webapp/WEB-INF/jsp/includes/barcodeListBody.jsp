  <table class="searchCriteria">
    <tr>
    	<th>Completed Actions:</th>
    </tr>
    <c:forEach items="${actionedList}" var="actioned">
    <tr> 
    	<td><input type="checkbox"><c:out value="${actioned}"/></td>        
    </tr>
    </c:forEach>
   
  </table>