<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
 

<tr><td>
<h2>Edit /New Container Type</h2>
	Note:Container type name must start with "Sample","Reagent" or "Plate" in order for the system to get containers by container type.
    
    <form method="post" action="containerType.htm" >
          
	<table class="details">
      
    <tr> 
        <td>Name</td>
        <td> 
    	<spring:bind path="command.name">
        <INPUT type="text" maxlength="16" size="30" name="name" value="<c:out value="${status.value}"/>">
  <FONT color="red">
    <B><c:out value="${status.errorMessage}"/></B>
  </FONT>
    	</spring:bind>
        </td>
    </tr>
    
    <tr> 
      <td>Column Number</td>
      <td> 
	<spring:bind path="command.columnNo">
	   <INPUT type="text" maxlength="38" size="30" name="columnNo" value="<c:out value="${status.value}"/>">
	     <FONT color="red">
    <B><c:out value="${status.errorMessage}"/></B>
  </FONT>
	</spring:bind>
      </td>
    </tr>

	 <tr> 
      <td>Row Number</td>
      <td> 
	<spring:bind path="command.rowNo">
	   <INPUT type="text" maxlength="38" size="30" name="rowNo" value="<c:out value="${status.value}"/>">
	     <FONT color="red">
    <B><c:out value="${status.errorMessage}"/></B>
  </FONT>
	</spring:bind>
      </td>
    </tr>
       
    <tr> 
      <td colspan="2">
          <input type="submit" name="Submit" value="Edit">
          <input type="submit" name="Submit" value="New">
        </td>
    </tr>
  </table>

	<p>
	
    </form>
    <table class="details">
    <tr>
    	<th>Container Type Name</th>
    	<th>Column Number</th>
		<th>Row Number</th>
    </tr>
    
    <c:forEach items="${containerTypes}" var="container">
    <tr> 
    	<td>
    	<a href="<c:url value="/containerType.htm">
			<c:param name="containerTypeId" value="${container.containerTypeId}"/></c:url>">
			<c:out value="${container.name}"/></a>
    	</td>
    	<td><c:out value="${container.columnNo}"/></td>
		<td><c:out value="${container.rowNo}"/></td>
        
    </tr>
    </c:forEach>
   
  </table>
 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>