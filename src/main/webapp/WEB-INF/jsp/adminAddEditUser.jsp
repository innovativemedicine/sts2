 <%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
 
<tr><td>
<p><h2>Add / Edit User Information</h2></p>

    <spring:bind path="command.*">
        <FONT color="red">
            <c:forEach items="${status.errorMessages}" var="container">
            <B><c:out value="${container}" /></B> <br>
            </c:forEach>  </FONT>
        </spring:bind>

    <form method="post" action="adminAddEditUser.htm" >
          
	<table width="60%" border="0" class="details">
          
    <tr> 
        <td>Login Name<FONT color=RED>*</FONT></td>
        <td> 
    	<spring:bind path="command.loginname">
        <INPUT type="text" maxlength="32" size="30" name="loginname" value="<c:out value="${status.value}"/>">
		<FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
    	</spring:bind>
        </td>
    </tr>
    
    <tr> 
      <td>Password<FONT color=RED>*</FONT></td>
      <td> 
	<spring:bind path="command.password">
	   <INPUT type="password" maxlength="32" size="30" 
	   	name="password" value="<c:out value="${status.value}"/>">
        <FONT color="red">
            <B><c:out value="${status.errorMessage}"/></B>
        </FONT>
	</spring:bind>
      </td>
    </tr>
       
    <tr> 
      <td>Activity</td>
      <td> 
	<spring:bind path="command.activity">
	   <INPUT type="checkbox" maxlength="3" size="3" name="activity" 
			<c:if test="${status.value=='on'}">
				checked value="on"
			</c:if>

		/>

        <FONT color="red">
            <B><c:out value="${status.errorMessage}"/></B>
        </FONT>
	</spring:bind>
      </td>
    </tr>
    
    <tr> 
      <td>First Name</td>
      <td> 
	<spring:bind path="command.firstName">
	   <INPUT type="text" maxlength="32" size="30" 
	   	name="firstName" value="<c:out value="${status.value}"/>">
        <FONT color="red">
            <B><c:out value="${status.errorMessage}"/></B>
        </FONT>
	</spring:bind>
      </td>
    </tr>

    <tr> 
      <td>Last Name</td>
      <td> 
	<spring:bind path="command.lastName">
	   <INPUT type="text" maxlength="32" size="30" 
	   	name="lastName" value="<c:out value="${status.value}"/>">
        <FONT color="red">
            <B><c:out value="${status.errorMessage}"/></B>
        </FONT>
	</spring:bind>
      </td>
    </tr>

    <tr> 
      <td>address</td>
      <td> 
	<spring:bind path="command.address">
	   <INPUT type="text" maxlength="32" size="30" 
	   	name="address" value="<c:out value="${status.value}"/>">
        <FONT color="red">
            <B><c:out value="${status.errorMessage}"/></B>
        </FONT>
	</spring:bind>
      </td>
    </tr>

    <tr> 
      <td>Home Phone</td>
      <td> 
	<spring:bind path="command.homePhone">
	   <INPUT type="text" maxlength="32" size="30" 
	   	name="homePhone" value="<c:out value="${status.value}"/>">
        <FONT color="red">
            <B><c:out value="${status.errorMessage}"/></B>
        </FONT>
	</spring:bind>
      </td>
    </tr>

    <tr> 
      <td>Office Phone</td>
      <td> 
	<spring:bind path="command.officePhone">
	   <INPUT type="text" maxlength="32" size="30" 
	   	name="officePhone" value="<c:out value="${status.value}"/>">
        <FONT color="red">
            <B><c:out value="${status.errorMessage}"/></B>
        </FONT>
	</spring:bind>
      </td>
    </tr>

    <tr> 
      <td>Email Address</td>
      <td> 
	<spring:bind path="command.emailAddress">
	   <INPUT type="text" maxlength="32" size="30" 
	   	name="emailAddress" value="<c:out value="${status.value}"/>">
        <FONT color="red">
            <B><c:out value="${status.errorMessage}"/></B>
        </FONT>
	</spring:bind>
      </td>
    </tr>
	
	<tr> <td>Role<FONT color=RED>*</FONT></td>
	<td> 
      <spring:bind path="command.ROLESs">	  
	  <select NAME="roles" SIZE="3"  multiple="true" >
              <c:forEach var="witem" items="${RolesList}">
                <c:set value="false" var="mytest" scope="page" />
                <c:forEach var="cc" items="${command.ROLESs}">
                  <c:if test="${witem.id==cc.id}">
                    <c:set value="true" var="mytest" scope="page" />
                  </c:if>
                </c:forEach>
                <option  <c:if test="${mytest=='true'}"> selected </c:if>
                  value='<c:out value="${witem.id}" />' >
                   <c:out value="${witem.name}" />
                </option>
              </c:forEach>
     </select>
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
    
    <table width="60%" border="0" class="details">
    <tr>
    	<th>Login Name</th>
    	<th>First Name</th>
    	<th>Last Name</th>
    	<th>Role</th>
    </tr>
    
    <c:forEach items="${LUsers}" var="container">
    <tr> 
    	<td>
    	<a href="<c:url value="adminAddEditUser.htm">
			<c:param name="userId" value="${container.userId}"/></c:url>">
			<c:out value="${container.loginname}"/></a>
    	</td>
    	<td><c:out value="${container.firstName}"/></td>
    	<td><c:out value="${container.lastName}"/></td>
    	<td> <c:forEach items="${container.ROLESs}" var="role">
    			<c:out value="${role.name}"/>;
    			</c:forEach>
    	</td>
        
    </tr>
    </c:forEach>
   
  </table>
 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>