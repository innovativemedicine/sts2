<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<p><h2>Edit Run</h2></p>

<form method="post">
  <table width="60%" border="0" class="details">
     
    <tr> 
          <td>Project: </td>
          <td> 
          <spring:bind path="command.project">
          <select name='<c:out value="${status.expression}" />' size="1">
          
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
              <td>Note:</td>
              <td> 
                <spring:bind path="command.note">
        	   <INPUT type="text" maxlength="255" size="30" name="note" value="<c:out value="${status.value}"/>">
        	</spring:bind>
              </td>
    </tr>

	<tr> 
              <td>Run Date:</td>
              <td> 
                <spring:bind path="command.runDate">
        	   <INPUT type="text" maxlength="255" size="30" name="date" value="<c:out value="${status.value}"/>">
        	</spring:bind>
              </td>
    </tr>

    <tr> 
	 <td>Test(s):</td>
	 <td>
	    <c:out value="${command.testNameList}"/><br>
	    <select name="testIds" size="5" multiple>
	     <c:forEach items="${allTests}" var="test">
	     
		 <option value="<c:out value="${test.testId}"/>"><c:out value="${test.name}"/></option>

	     </c:forEach>
	   </select>
	    
	 </td>
    </tr>

	 <tr> 
	 <td>Plate(s):</td>
	 <td>
	    <c:out value="${command.plateNameList}"/><br>
	    <select name="plateIds" size="5" multiple>
	     <c:forEach items="${allPlates}" var="plate">
	     <option value="<c:out value="${plate.containerId}"/>"><c:out value="${plate.name}"/></option>
	     </c:forEach>
	   </select>
	    
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