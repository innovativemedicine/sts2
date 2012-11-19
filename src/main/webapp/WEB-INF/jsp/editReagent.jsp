<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<p><h2>Edit /New Reagent</h2></p>


  <table border="0" class="details">
    
    <form method="post">
    
    <tr> 
          <td>Reagent Name</td>
          <td> 
    	<spring:bind path="command.name">
    	   <INPUT type="text" maxlength="255" size="30" name="name" value="<c:out value="${status.value}"/>">
		   <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
    	</spring:bind>

          </td>
    </tr>

	<tr> 
          <td>Primer Sequence</td>
          <td> 
    	<spring:bind path="command.seq">
    	   <INPUT type="text" maxlength="255" size="30" name="seq" value="<c:out value="${status.value}"/>">
    	</spring:bind>
          </td>
    </tr>
    
    <tr> 
      <td>Concentration:</td>
      <td> 
	<spring:bind path="command.concentration">
	   <INPUT type="text" maxlength="255" size="30" name="concentration" value="<c:out value="${status.value}"/>">
	</spring:bind>
      </td>
    </tr>
    
    <tr> 
          <td>Manufacture</td>
          <td> 
    	<spring:bind path="command.manufacture">
    	   <INPUT type="text" maxlength="255" size="30" name="manufacture" value="<c:out value="${status.value}"/>">
    	</spring:bind>
          </td>
    </tr>
    
    <tr> 
      <td>Note</td>
      <td> 
	<spring:bind path="command.note">
	   <INPUT type="text" maxlength="255" size="30" name="note" value="<c:out value="${status.value}"/>">
	</spring:bind>
      </td>
    </tr>
    
    <tr> 
      <td>Stock Container: </td>
      <td> 
      <spring:bind path="command.container">
     <select name='<c:out value="${status.expression}" />' size="1">
    
	 <c:forEach items="${allReagentBoxes}" var="container">
	     <option 
		 
		 <c:if test="${command.container != null && command.container.containerId eq container.containerId}">
			   selected
		  </c:if>
		 
		 value="<c:out value="${container.containerId}"/>"><c:out value="${container.name}"/></option>
	  </c:forEach>
     </select>
     </spring:bind>

   </td>
    </tr>
   
    <tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="Submit" value="Save">
          <input type="reset" name="Submit2" value="Reset">
        </p>
        </td>
    </tr>
    </form>
  </table>
  
  



 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>