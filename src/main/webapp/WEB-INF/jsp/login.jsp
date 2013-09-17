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

		<div class="span10">
		
			<div class="alert alert-info">
				Welcome
				<c:out value="${sessionScope.userName}" />
			</div>




			<h2>Updates:</h2>

			<div class="accordion" id="accordion2">
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="act act-primary accordion-toggle" data-toggle="collapse"
							data-parent="#accordion2" href="#collapseOne"> September 2013
						</a>
					</div>
					<div id="collapseOne" class="accordion-body collapse in">
						<div class="accordion-inner">
							<ul>
								<li>Initial Launch of STS2.0 with an updated interface</li>
								<li>Removed support for searching and registering plates.<br>
									(To be implemented as a separate system)
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="act act-primary accordion-toggle" data-toggle="collapse"
							data-parent="#accordion2" href="#collapseTwo"> August 2013 </a>
					</div>
					<div id="collapseTwo" class="accordion-body collapse">
						<div class="accordion-inner">
							<ul>
								<li>Initial Launch of STS2.0 with an updated interface</li>
								<li>Added support for barcodes</li>
								<li>Added support for searching and registering plates</li>
								<li>Simplified search interface</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="alert">Database backup time is 11PM and lasts about
				2 hours. During this time, STS will be unavailable.</div>

		</div>
	</div>
</body>