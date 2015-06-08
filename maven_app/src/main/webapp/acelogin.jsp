<!doctype html>

<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core'%>
<%@ page import="net.sf.acegisecurity.ui.AbstractProcessingFilter"%>
<%@ page
	import="net.sf.acegisecurity.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="net.sf.acegisecurity.AuthenticationException"%>

<html>
<head>


<title>STS2: Login</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/stylesheet.css" rel="stylesheet">


</head>

<body>
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="acelogin.jsp">Sample Tracking System
					2.0&nbsp;</a>
			</div>
		</div>
	</div>
	<div class="container-fluid">

		<h2>Login</h2>

		<c:if test="${not empty param.login_error}">
			<font color="red"> Login Failed: <br> Reason: <%=((AuthenticationException) session
						.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY)).getMessage()%>
			</font>
		</c:if>


		<form class="form-inline form-horizontal" action="j_acegi_security_check"
			method="POST">

			<div class="control-group">
				<label class="control-label" for="j_username">User:</label>
				<div class="controls">
					<input type="text" name="j_username"
						<c:if test="${not empty param.login_error}">value='<%= 
        		session.getAttribute(AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY) %>'
        		</c:if>>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="j_password">Password:</label>
				<div class="controls">
					<input type="password" name="j_password" placeholder="Password">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<input class="btn" name="login" type="submit"> <input
						class="btn" name="reset" type="reset">
				</div>
			</div>
		</form>

	</div>
</body>
</html>