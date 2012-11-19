<h3> Poject List </h3>
  <table width="70%" border="0" class="details">
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
    	<td><c:out value="${project.description}"/>&nbsp;</td>
    	<td><c:out value="${project.createdOn}"/>&nbsp;</td>
    	<td><c:out value="${project.investigator.name.fname}"/> &nbsp;<c:out value="${project.investigator.name.lname}"/> </td>
    	<td><c:out value="${project.status}"/>&nbsp;</td>
   
       
        
    </tr>
    </c:forEach>
   
  </table>
