<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<h2>Project Details</h2>
<a class="btn" href="<c:url value="/editProject.htm"><c:param name="projectId" value="${command.projectId}"/></c:url>">
Edit</a>
<a class="btn" href="<c:url value="/deleteProject.htm"><c:param name="projectId" value="${command.projectId}"/></c:url>" 
	onclick="return (confirm('You can not delete this project unless there are no containers, assays, tests and runs related to this project.\n\nAre you sure you want to delete this project?')) ">
	Delete</a> 

<p>

  <table class="table">
    <tr> 
      <td>Project Name:</td>
      <td> 
      <c:out value="${command.name}"/>
      </td>
    </tr>
    <tr> 
      <td>Description:</td>
      <td> 
      <c:out value="${command.description}"/>
      </td>
    </tr>
    <tr> 
      <td>Status: </td>
      <td> 
      <c:out value="${command.status}"/>
      </td>
    </tr>
    <tr> 
      <td>Created On: </td>
      <td> 
      <c:out value="${command.createdOn}"/>
      </td>
    </tr>
    <tr> 
          <td>Investigator: </td>
          <td> 
          <c:out value="${command.investigator.name.fname}"/>&nbsp;
          <c:out value="${command.investigator.name.lname}"/>
          </td>
    </tr>
   </table>
	<p>
   <a class="btn" href="<c:url value="/editContainer.htm">
   			<c:param name="containerId" value="-1"/><c:param name="projectId" value="${command.projectId}"/>
   		</c:url>" 
   		target="_blank">
   		<span>Add Container</span>
   	</a>

   <a class="btn" href="<c:url value="/editAssay.htm">
   			<c:param name="assayId" value="-1"/><c:param name="projectId" value="${command.projectId}"/>
   		</c:url>" 
   		target="_blank">
   		<span>Add Assay</span>
   	</a>

   <a class="btn" href="<c:url value="/editTest.htm">
   			<c:param name="testId" value="-1"/><c:param name="projectId" value="${command.projectId}"/>
   		</c:url>" 
   		target="_blank">
   		<span>Add Test</span>
   	</a>
  

   <a class="btn" href="<c:url value="/editRun.htm">
   			<c:param name="projectId" value="${command.projectId}"/>
   		</c:url>" 
   		target="_blank">
   		<span>Add Run</span>
   	</a>
   
  
 	<h3> Container List</h3>
      
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
         
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>