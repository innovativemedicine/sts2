<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
 
<tr><td>
<p><h2>Update Test Result to CGG System</h2></p>
    
    <form method="post" action="updateResultToCGG.htm" >
          
	<table width="60%" border="0" class="details">
          
    <tr> 
        <td>Do you want to update test result to CGG system?</td>
    </tr>
    
       
    <tr> 
      <td>
          <input type="submit" name="Submit" value="UPDATE">
        </td>
    </tr>
  </table>

    </form>
    <table width="70%" border="0" class="details">
    
    <c:if test="${ListData!=null}">
    <tr>
    	<th>Result</th>
    	<th>Assay Name</th>
        <th>Project</th>
        <th>Container</th>
        <th>Result</th>
        <th>External Sample</th>
        <th>Study Group</th>
    </tr>
    </c:if>
    
    <c:forEach items="${ListData}" var="i" step="7">
    <tr> 
    	<td><c:out value="${i}"/> </td>
    	<td><c:out value="${i+1}"/></td>
        <td><c:out value="${i+2}"/></td>
        <td><c:out value="${i+3}"/></td>
        <td><c:out value="${i+4}"/></td>
        <td><c:out value="${i+5}"/></td>
        <td><c:out value="${i+6}"/></td>
    </tr>
    </c:forEach>
       
  </table>
 
 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>