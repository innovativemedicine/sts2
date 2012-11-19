<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
 

<tr><td>
<p><h2>Edit /New Investigator</h2></p>
    
    <form method="post" action="investigator.htm" >
       
	<table width="60%" border="0" class="details">
          
    <tr> 
        <td>First Name</td>
        <td> 
    	<spring:bind path="command.name.fname">
        <INPUT type="text" maxlength="32" size="15" name="name.fname" value="<c:out value="${status.value}"/>">
    	</spring:bind>
        </td>
        <td>Middile Name</td>
        <td> 
        <spring:bind path="command.name.mname">
        <INPUT type="text" maxlength="32" size="15" name="name.mname" value="<c:out value="${status.value}"/>">
        </spring:bind>
        </td>
        <td>Last Name</td>
        <td> 
        <spring:bind path="command.name.lname">
        <INPUT type="text" maxlength="32" size="15" name="name.lname" value="<c:out value="${status.value}"/>">
        </spring:bind>
        </td>

    </tr>
    <tr>
        <td>Address</td>
        <td colspan="0" > 
        <spring:bind path="command.contact.address">
        <INPUT type="text" maxlength="128" size="60" name="contact.address" value="<c:out value="${status.value}"/>">
        </spring:bind>
        </td>
    </tr>

    <tr> 
        <td>City</td>
        <td> 
        <spring:bind path="command.contact.city">
        <INPUT type="text" maxlength="32" size="15" name="contact.city" value="<c:out value="${status.value}"/>">
        </spring:bind>
        </td>
        <td>Province</td>
        <td> 
        <spring:bind path="command.contact.provice">
        <INPUT type="text" maxlength="10" size="15" name="contact.provice" value="<c:out value="${status.value}"/>">
        </spring:bind>
        </td>
        <td>Postal Code</td>
        <td> 
        <spring:bind path="command.contact.postalCode">
        <INPUT type="text" maxlength="6" size="15" name="contact.postalCode" value="<c:out value="${status.value}"/>">
        </spring:bind>
        </td>

    </tr>

    <tr> 
        <td>Phone</td>
        <td> 
        <spring:bind path="command.contact.phone">
        <INPUT type="text" maxlength="10" size="15" name="contact.phone" value="<c:out value="${status.value}"/>">
        </spring:bind>
        </td>
        <td>fax</td>
        <td> 
        <spring:bind path="command.contact.fax">
        <INPUT type="text" maxlength="10" size="15" name="contact.fax" value="<c:out value="${status.value}"/>">
        </spring:bind>
        </td>
        <td>E-Mail</td>
        <td> 
        <spring:bind path="command.contact.email">
        <INPUT type="text" maxlength="32" size="15" name="contact.email" value="<c:out value="${status.value}"/>">
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
        <td colspan="2"> <spring:bind path="command.*">
        <FONT color="red">
            <c:forEach items="${status.errorMessages}" var="container">
            <B><c:out value="${container}" /></B> <br>
            </c:forEach>  </FONT>
        </spring:bind>  </td>
    </tr>
  </table>

    </form>
    <table width="70%" border="0" class="details">
    <tr>
    	<th>Investigator Name</th>
    	<th>Telephone Number</th>
    </tr>
    
    <c:forEach items="${LInvestigators}" var="container">
    <tr> 
    	<td>
    	<a href="<c:url value="/investigator.htm">
			<c:param name="investigatorId" value="${container.investigatorId}"/></c:url>">
			<c:out value="${container.name.fname}"/>&nbsp;
			<c:out value="${container.name.lname}"/></a>
    	</td>
    	<td><c:out value="${container.contact.phone}"/></td>
        
    </tr>
    </c:forEach>
   
  </table>
 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>