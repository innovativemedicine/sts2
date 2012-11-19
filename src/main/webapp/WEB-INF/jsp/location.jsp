<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
 

<tr><td>
<p><h2>Edit /New Location</h2></p>
    
    <form method="post" action="location.htm" >
          
	<table width="60%" border="0" class="details">
          
    <tr> 
        <td>Location Name</td>
        <td> 
    	<spring:bind path="command.name">
        <INPUT type="text" maxlength="64" size="30" name="name" value="<c:out value="${status.value}"/>">
		<FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
    	</spring:bind>
        </td>
    </tr>
    
    <tr> 
      <td>Building</td>
      <td> 
	<spring:bind path="command.building">
	   <INPUT type="text" maxlength="128" size="30" name="building" value="<c:out value="${status.value}"/>">
	</spring:bind>
      </td>
    </tr>
 
     <tr> 
      <td>Room</td>
      <td> 
    <spring:bind path="command.room">
       <INPUT type="text" maxlength="32" size="30" name="room" value="<c:out value="${status.value}"/>">
    </spring:bind>
      </td>
    </tr>
     <tr> 
      <td>Freezer</td>
      <td> 
    <spring:bind path="command.freezer">
       <INPUT type="text" maxlength="16" size="30" name="freezer" value="<c:out value="${status.value}"/>">
    </spring:bind>
      </td>
    </tr>
     <tr> 
      <td>Freezer Shelf</td>
      <td> 
    <spring:bind path="command.freezerShelf">
       <INPUT type="text" maxlength="16" size="30" name="freezerShelf" value="<c:out value="${status.value}"/>">
    </spring:bind>
      </td>
    </tr>
       
    <tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="Submit" value="Edit">
          <input type="submit" name="Submit" value="New">
        </p>
        </td>
    </tr>
  </table>

    </form>
    <table width="70%" border="0" class="details">
    <tr>
    	<th>Location Name</th>
    	<th>Building</th>
        <th>Room</th>
        <th>Freezer</th>
        <th>Freezer Shelf</th>
    </tr>
    
    <c:forEach items="${LLocations}" var="container">
    <tr> 
    	<td>
    	<a href="<c:url value="/location.htm">
			<c:param name="locationId" value="${container.locationId}"/></c:url>">
			<c:out value="${container.name}"/></a>
    	</td>
    	<td><c:out value="${container.building}"/></td>
        <td><c:out value="${container.room}"/></td>
        <td><c:out value="${container.freezer}"/></td>
        <td><c:out value="${container.freezerShelf}"/></td>
        
    </tr>
    </c:forEach>
   
  </table>
 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>