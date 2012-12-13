  <table style="width: 800px">
    <tr>
    	<th>Name</th>
    	<th>External Name</th>
    	<th>Location</th>
		<th>Container Type</th>
    	<th>Created Date</th>
    	<th>Project</th>
    	<th>Status</th>
    </tr>
    
    <c:forEach items="${containerList}" var="container">
    <tr> 
    	<td style="width:200px">
    	<a href="<c:url value="/containerDetails.htm"><c:param name="containerId" value="${container.containerId}"/></c:url>"><c:out value="${container.name}"/></a>&nbsp;
    	</td>
    	<td style="width:70px"><c:out value="${container.extContainerId}"/></td>
    	<td style="width:100px"><c:out value="${container.location.name}"/></td>

    	<td style="width: 70px"><c:out value="${container.containerType.name}"/></td>
    	<td style="width:100px"><fmt:formatDate pattern="yyyy-MM-dd" value="${container.createdDate}"/></td>
    	<td><c:out value="${container.project.name}"/></td>
    	<td><c:out value="${container.status}"/></td>
    	
        
    </tr>
    </c:forEach>
   
  </table>