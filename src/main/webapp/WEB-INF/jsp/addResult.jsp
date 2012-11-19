<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<p><h2>Add a manually test result</h2></p>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>
<form method="post" >
  <table width="60%" border="0" class="details">
     
    <tr> 
          <td>Project: </td>
          <td> 
          <spring:bind path="command.run.project">
          <select name='<c:out value="${status.expression}" />' size="1">
       
    	 <c:forEach items="${allProjects}" var="project">
    	     <option value="<c:out value="${project.projectId}"/>"><c:out value="${project.name}"/></option>
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
              <td>Test Date:</td>
              <td> 
                <spring:bind path="command.run.runDate">
        	   <INPUT type="text" maxlength="255" size="30" name="run.runDate" value="<c:out value="${status.value}"/>"> (<fmt:message key="format.date"/>)
			    <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT> 
        	</spring:bind>
              </td>
    </tr>

	<tr> 
              <td>Sample Id:</td>
              <td> 
                <spring:bind path="command.intSampleId">
        	   <INPUT type="text" maxlength="255" size="30" name="intSampleId" value="<c:out value="${status.value}"/>">
			   <FONT color="red">
				 <B><c:out value="${status.errorMessage}"/></B>
			   </FONT> 
        	</spring:bind>
              </td>
    </tr>

	 <tr> 
	 <td>Assay:</td>
	 <td>
          <spring:bind path="command.assay">
          <select name='<c:out value="${status.expression}" />' size="1">
         
    	 <c:forEach items="${allAssays}" var="assay">
    	     <option value="<c:out value="${assay.assayId}"/>"><c:out value="${assay.name}"/></option>
    	  </c:forEach>
          </select>
          </spring:bind>
	    
	 </td>
    </tr>

	<tr> 
              <td>Result:</td>
              <td> 
                <spring:bind path="command.result">
        	   <INPUT type="text" maxlength="255" size="30" name="result" value="<c:out value="${status.value}"/>">
        	</spring:bind>
              </td>
    </tr>

	 <tr> 
          <td>CGG Study Group: </td>
         <td>
		  <spring:bind path="command.studyGroupId">
          <select name="studyGroupId" size="1">
         
    	  <c:forEach items="${allStudyGroups}" var="studyGroup">
	     <option value="<c:out value="${studyGroup.studyGroupId}"/>"><c:out value="${studyGroup.studyGroupName}"/></option>
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