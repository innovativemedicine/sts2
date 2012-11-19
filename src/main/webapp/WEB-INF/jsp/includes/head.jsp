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
   <link title="Style" type="text/css" rel="stylesheet" href="includes/menu.css">
   <link title="Style" type="text/css" rel="stylesheet" href="stylesheet.css">


   <script type="text/javascript">

// CSS Top Menu- By JavaScriptKit.com (http://www.javascriptkit.com)
// Adopted from SuckerFish menu
// For this and over 400+ free scripts, visit JavaScript Kit- http://www.javascriptkit.com/
// Please keep this credit intact

startMenu = function() {
if (document.all&&document.getElementById) {
cssmenu = document.getElementById("csstopmenu");
for (i=0; i<cssmenu.childNodes.length; i++) {
node = cssmenu.childNodes[i];
if (node.nodeName=="LI") {
node.onmouseover=function() {
this.className+=" over";
}
node.onmouseout=function(){                  
this.className=this.className.replace(" over", "")
}
}
}
}
}

if (window.attachEvent)
window.attachEvent("onload", startMenu)
else
window.onload=startMenu;

</script>


</head>
  <body >
    <table width="800">
      <tr>
        <td align="center">
          <H1>
            Sample Tracking System
          <H1>
        </td>
      </tr>
	<tr><td align="center"> <div>
		<%@ include file="/WEB-INF/jsp/includes/menu.html" %>
	</div></td></tr>
<%--   <TR align=right>
    <TD><A href=index.htm?login=on>Login</A>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<A 
      href=index.htm?login=on>Logout</A></TD>
   </TR> 
--%>
   </table>