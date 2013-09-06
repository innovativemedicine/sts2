<!doctype html>

<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Sample Tracking System v2</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/jasny-bootstrap.min.css" rel="stylesheet">
<link href="css/jasny-bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/stylesheet.css" rel="stylesheet">


</head>
<body>
	<script type="text/javascript" src="js/jquery-1.10.2.min.js" defer></script>
	<script type="text/javascript" src="js/bootstrap.min.js" defer></script>
	<script type="text/javascript" src="js/jasny-bootstrap.min.js" defer></script>
	<script type="text/javascript" src="js/myJs.js" defer></script>

	<%@ include file="/WEB-INF/jsp/includes/menu.jsp"%>
	<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

	<div class="container-fluid">

		<h2>
			Welcome
			<c:out value="${sessionScope.userName}" />
		</h2>

		<br>

		<div>
			<h3>Update news:</h3>
			<ul>
				<li>UI Revamp</li>
				<li>Search and Register Sample Reworked</li>
				<li>Search functionality for Projects, Results, Tests, Assays temporarily disabled.</li>
				
				<li>Search Plate, Register Plate, Barcode Functionality coming soon.</li>
			</ul>

		</div>

		<div class="alert">Database backup time is 11PM and lasts about
			2 hours. During this time, STS will be unavailable.</div>

	</div>
</body>