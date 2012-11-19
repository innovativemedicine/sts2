 <h3>Container List </h3>
  <table width="70%" border="0" class="details">
    <tr>
    	<th>Name ID</th>
    	<th>External Name</th>
    	<th>Location</th>
		<th>Container Type</th>
    	<th>Created Date Type</th>
    	<th>Project</th>
    </tr>
    
    <c:forEach items="${containerList}" var="container">
    <tr> 
    	<td>
    	<a href="<c:url value="/containerDetails.htm"><c:param name="containerId" value="${container.containerId}"/></c:url>"><c:out value="${container.name}"/></a>&nbsp;
    	</td>
    	<td><c:out value="${container.extContainerId}"/>&nbsp;</td>
    	<td><c:out value="${container.location.name}"/>&nbsp;</td>

    	<td><c:out value="${container.containerType.name}"/>&nbsp;</td>
    	<td><c:out value="${container.createdDate}"/>&nbsp;</td>
    	<td><c:out value="${container.project.name}"/>&nbsp;</td>
    	
       
        
    </tr>
    </c:forEach>
   
  </table>