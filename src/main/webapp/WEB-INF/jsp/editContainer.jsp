<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<p><h2>Edit /New Container</h2></p>

<c:if test="${command.motherContainer != null}">
	Mother Container: <c:out value="${command.motherContainer.name}"/><br>
</c:if>
<form method="post">
  <table border="0" class="details">
    <tr> 
      <td>Container Name<FONT color=RED>*</FONT></td>
      <td> 
	  Maxmum length of name is 128 including space <br>
        <spring:bind path="command.name">
	  <INPUT type="text" maxlength="255" size="30" name="name" value="<c:out value="${status.value}"/>" > 
	  <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
	</spring:bind>
      </td>
    </tr>
    <tr> 
      <td>External Container Name</td>
      <td> 
        <spring:bind path="command.extContainerId">
	   <INPUT type="text" maxlength="255" size="30" name="extContainerId" value="<c:out value="${status.value}"/>">
	</spring:bind>
      </td>
    </tr>
    <tr> 
          <td>Created Date</td>
          <td> 
            <spring:bind path="command.createdDate">
    	   <INPUT type="text" maxlength="255" size="30" name="createdDate" value="<c:out value="${status.value}"/>">
		   <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		    </FONT>
    	</spring:bind> (<fmt:message key="format.date"/>)
          </td>
    </tr>

	<tr> 
          <td>Concentration</td>
          <td> 
			
            <spring:bind path="command.concentration">
    	   <INPUT type="text" maxlength="255" size="30" name="concentration" value="<c:out value="${status.value}"/>">
		    <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		    </FONT>
    	</spring:bind> (ug/ml)
          </td>
    </tr>
    
    <tr> 
	  <td>Status</td>
	  <td> 
	    <spring:bind path="command.status">
	   <INPUT type="text" maxlength="255" size="30" name="status" value="<c:out value="${status.value}"/>">
	</spring:bind>
	  </td>
    </tr>
    
    <tr> 
      <td>Comments</td>
      <td> 
	<spring:bind path="command.comments">
	   <INPUT type="text" maxlength="255" size="30" name="comments" value="<c:out value="${status.value}"/>">
	</spring:bind>
      </td>
    </tr>
    

    
    <tr> 
      <td>Container Type<FONT color=RED>*</FONT> </td>
      <td> 
      <spring:bind path="command.containerType">
      <select size="1" name='<c:out value="${status.expression}" />' >
 
	 <c:forEach items="${allContainerTypes}" var="containerType">
	     <option 
		 
		 <c:if test="${command.containerType != null && command.containerType.containerTypeId eq containerType.containerTypeId}">
			   selected
		  </c:if>
		 
		 value="<c:out value="${containerType.containerTypeId}"/>"><c:out value="${containerType.name}"/></option>
	  </c:forEach>
      </select>
      		   <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		    </FONT>
      
      </spring:bind>

   </td>
    </tr>
    
    <tr> 
          <td>Location<FONT color=RED>*</FONT> </td>
          <td> 
          <spring:bind path="command.location">
	    <select size="1" name='<c:out value="${status.expression}" />'>
	       
	  	 <c:forEach items="${allLocations}" var="location">
	  	     <option 
			 
			 <c:if test="${command.location != null && command.location.locationId eq location.locationId}">
			   selected
			 </c:if>
			 
			 value="<c:out value="${location.locationId}"/>"><c:out value="${location.name}"/></option>
	  	  </c:forEach>
	     </select>
	        <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		    </FONT>
	     
	  </spring:bind>
    	
       </td>
    </tr>
    
    <tr> 
      <td>Project </td>
      <td> 
      <spring:bind path="command.project">
     <select size="1" name='<c:out value="${status.expression}" />'" >
    
	 <c:forEach items="${allProjects}" var="project">
	     <option 
		 
		 <c:if test="${command.project != null && command.project.projectId eq project.projectId}">
			   selected
		 </c:if>
		 
		 value="<c:out value="${project.projectId}"/>"><c:out value="${project.name}"/></option>
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