<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>       

<html>
  <head>
    <title>
      Sample Tracking System 
    </title>
    
    <script type="text/javascript" src="js/formBuilder.js"></script>
   
   <link type="text/css" rel="stylesheet" href="css/menu.css">
   <link type="text/css" rel="stylesheet" href="css/stylesheet.css">

	<fmt:setBundle basename="agtc.sampletracking.web.MessageBundle"></fmt:setBundle>
	 
	</head>
  <body >
	
    <table width="800">
      <tr>
        <td align="center">
          <h1>
            Sample Tracking System
          </h1>
        </td>
      </tr>
	<tr><td align="center"> <div>
		<%@ include file="/WEB-INF/jsp/includes/menu.html" %>
	</div></td></tr>
	
	</table>

  </body>
</html>