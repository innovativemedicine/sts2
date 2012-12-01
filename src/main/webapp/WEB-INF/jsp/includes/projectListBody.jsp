<h3> Project List </h3>

  <table class="details">
    <tr>
    	<th>Project Name</th>
    	<th>Description</th>
    	<th>Created Date</th>
    	<th>Investigator</th>
    	<th>Status</th>
    </tr>
    
    <c:forEach items="${projectList}" var="project">
    <tr> 
    	<td>
    	<a href="<c:url value="/projectDetails.htm"><c:param name="projectId" value="${project.projectId}"/></c:url>"><c:out value="${project.name}"/></a>
    	</td>
    	<td><c:out value="${project.description}"/></td>
    	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${project.createdOn}"/></td>
    	<td><c:out value="${project.investigator.name.fname}"/> &nbsp;<c:out value="${project.investigator.name.lname}"/> </td>
    	<td><c:out value="${project.status}"/>&nbsp;</td>
   
       
        
    </tr>
    </c:forEach>
   
  </table>