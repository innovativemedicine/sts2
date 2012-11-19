<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<p><h2>Project Details</h2>
<a href="<c:url value="/editProject.htm"><c:param name="projectId" value="${command.projectId}"/></c:url>">Edit it</a> <br>
<a href="<c:url value="/deleteProject.htm"><c:param name="projectId" value="${command.projectId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete this project? Read the warning carefully before you confirm!')) ">Delete it</a> &nbsp;<font color="red">Warning:</font> You can not delete this project unless there is no containers, assays, tests and runs related to this project.
<p>

  <table width="60%" border="0" class="details">
    <tr> 
      <td>Project Name:</td>
      <td> 
      <c:out value="${command.name}"/>&nbsp;
      </td>
    </tr>
    <tr> 
      <td>Description:</td>
      <td> 
      <c:out value="${command.description}"/>&nbsp;
      </td>
    </tr>
    <tr> 
      <td>Status: </td>
      <td> 
      <c:out value="${command.status}"/>&nbsp;
      </td>
    </tr>
    <tr> 
      <td>Created On: </td>
      <td> 
      <c:out value="${command.createdOn}"/>&nbsp;
      </td>
    </tr>
    <tr> 
          <td>Investigator: </td>
          <td> 
          <c:out value="${command.investigator.name.fname}"/>&nbsp;
          <c:out value="${command.investigator.name.lname}"/>&nbsp;
    	
          </td>
    </tr>
   </table>
	<p>
   <a href="<c:url value="/editContainer.htm"><c:param name="containerId" value="-1"/><c:param name="projectId" value="${command.projectId}"/></c:url>" target="_blank">Add A New Container For This Project</a><br>

   <a href="<c:url value="/editAssay.htm"><c:param name="assayId" value="-1"/><c:param name="projectId" value="${command.projectId}"/></c:url>" target="_blank">Add A New Assay For This Project</a><br>

   <a href="<c:url value="/editTest.htm"><c:param name="testId" value="-1"/><c:param name="projectId" value="${command.projectId}"/></c:url>" target="_blank">Add A New Test For This Project</a><br>

   <a href="<c:url value="/editRun.htm"><c:param name="projectId" value="${command.projectId}"/></c:url>" target="_blank">Add A New Run For This Project</a><br>
   
  
 
      
      <c:if test="${command.containers != null}">
      <%@ include file="/WEB-INF/jsp/includes/containerListBody.jsp" %>
      </c:if>
 

	 <c:if test="${command.assays != null}">
	 <%@ include file="/WEB-INF/jsp/includes/assayListBody.jsp" %>
	 </c:if>
         
	<c:if test="${command.tests != null}">
	<%@ include file="/WEB-INF/jsp/includes/testListBody.jsp" %>
	</c:if>
        
   <c:if test="${command.runs != null}">
   <%@ include file="/WEB-INF/jsp/includes/runListBody.jsp" %>
	</c:if>
         
  
  

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>