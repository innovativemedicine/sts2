<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>       

<html>
  <head>
    <title>
      Sample Tracking System v2
    </title>
    
    <script type="text/javascript" src="js/formBuilder.js"></script>
   	<link type="text/css" rel="stylesheet" href="css/menu.css">
   	<link type="text/css" rel="stylesheet" href="css/stylesheet.css">
	<fmt:setBundle basename="agtc.sampletracking.web.MessageBundle"></fmt:setBundle>
	 
	</head>
  <body>
	
	<div class="header">
          <h1>
            Sample Tracking System
          </h1>
		<%@ include file="/WEB-INF/jsp/includes/menu.html" %>
	</div>
	
    <div class="content">