
<h3>Results:</h3>
  <table width="70%" border="0" class="details">
    <tr>
		<c:forEach items="${resultList[0]}" var="head">
		<th><c:out value="${head}" /></th>
		</c:forEach>
    </tr>
    
    <c:forEach items="${resultList}" var="row" begin="1"  >
    <tr> 
    	<td><c:out  value="${row[0]}" /> </td>
		<c:forEach items="${row}" var="oneResult" begin="1"  >
    		<td>
			<c:if test="${oneResult != null}">
				<a href="<c:url value="/resultDetails.htm"><c:param name="resultId" value="${oneResult.resultId}"/></c:url>"><c:out  value="${oneResult.result}"/>
			</c:if>
			&nbsp;

			
			</td>
		</c:forEach>
    </tr>
    </c:forEach>
   
  </table>