<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
  <head>
    <title>
      Sample Tracking System v2
    </title>
    
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js" defer></script>
    <script type="text/javascript" src="js/myJs.js" defer></script>

   	<link href="css/stylesheet.css" rel="stylesheet" type="text/css" >
	<fmt:setBundle basename="agtc.sampletracking.web.MessageBundle"></fmt:setBundle>
	 
	</head>
  <body>
	
	<div class="header">
          <h1>
            Sample Tracking System
          </h1>
    
<%--            <c:if test="${fn:contains(header['User-Agent'],'MSIE')}">MSIE</c:if> --%>
<%--            <c:if test="${fn:contains(header['User-Agent'],'Chrome')}">Chrome</c:if> --%>
          
		  <c:out value="${browser}"></c:out>
		<%@ include file="/WEB-INF/jsp/includes/menu.html" %>
	</div>
	
	
	
    <div class="content">