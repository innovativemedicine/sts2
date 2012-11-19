<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<p><h2>Edit /New project</h2></p>

<form method="post">
  <table border="0" class="details">
    <tr> 
      <td>Project Name:</td>
      <td> 
        <spring:bind path="command.name">
	  <INPUT type="text" maxlength="255" size="30" name="name" value="<c:out value="${status.value}"/>" > 
	  <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
	</spring:bind>
	
      </td>
    </tr>
    <tr> 
      <td>Description:</td>
      <td> 
        <spring:bind path="command.description">
	   <INPUT type="text" maxlength="255" size="30" name="description" value="<c:out value="${status.value}"/>">
	</spring:bind>
      </td>
    </tr>
    <tr> 
      <td>Status: </td>
      <td> 
        <spring:bind path="command.status">
           <INPUT type="text" maxlength="255" size="30" name="status" value="<c:out value="${status.value}"/>">
	</spring:bind>
      </td>
    </tr>
    <tr> 
      <td>Created On: </td>
      <td> 
        <spring:bind path="command.createdOn">
	   <INPUT type="text" maxlength="255" size="30" name="createdOn" value="<c:out value="${status.value}"/>">
	    <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
	</spring:bind>(<fmt:message key="format.date"/>)
      </td>
    </tr>
    <tr> 
          <td>Investigator: </td>
          <td> 
          <spring:bind path="command.investigator">
	     <select size="1" name='<c:out value="${status.expression}" />'>
	  	 
	  	 <c:forEach items="${availableIvgs}" var="inv">
	  	     <option 
			 
			 <c:if test="${command.investigator != null && command.investigator.investigatorId eq inv.investigatorId}">
			   selected
		     </c:if>
			 
			 value="<c:out value="${inv.investigatorId}"/>">
	  	     <c:out value="${inv.name.fname}"/> <c:out value="${inv.name.lname}"/></option>
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
  </table>
  </form>
  

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>