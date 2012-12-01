<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core' %>
<%@ page import="net.sf.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page import="net.sf.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="net.sf.acegisecurity.AuthenticationException" %>

<html>
  <head>   
    <title>Login</title>  
  </head>

  <body>
    <h1>Sample Tracking System</h1>
    
    <h3>Login</h3>
	
    <c:if test="${not empty param.login_error}">
      <font color="red">
        Your login attempt was not successful, try again.<BR><BR>
        Reason: <%= ((AuthenticationException) 
        	session.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
      </font>
    </c:if>

    <form action="<c:url value='j_acegi_security_check'/>" method="POST">
      <table>
        <tr><td>User:</td><td>
        	<input type='text' name='j_username' 
        	<c:if test="${not empty param.login_error}">value='<%= 
        		session.getAttribute(AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY) %>'
        		</c:if>>
        	</td></tr>
        <tr><td>Password:</td><td><input type='password' name='j_password'></td></tr>

        <tr><td colspan='2'><input name="login" type="submit"><input name="reset" type="reset"></td></tr>
      </table>

    </form>


  </body>
</html>