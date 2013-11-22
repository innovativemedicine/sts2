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

			<h2>Updates:</h2>
			<div class="accordion" id="accordion2">

				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="act act-primary accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#november">
							November 2013 </a>
					</div>
					<div id="november" class="accordion-body collapse in">
						<div class="accordion-inner">

							<h4>
								<span class="label label-info">New</span> Sample Details Interface
							</h4>

							<ul>
								<li>Swapped location of Patient and Sample Info</li>
								<li>Differentiate Patient and Sample links from Search Sample Results</li>
								<li>Improved Delete Sample Redirection</li>
							</ul>

						</div>
					</div>
				</div>
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="act act-primary accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#september">
							September 2013 </a>
					</div>
					<div id="september" class="accordion-body collapse">
						<div class="accordion-inner">
							<h4>
								<span class="label label-info">New</span> Minor UI Improvements
							</h4>
							<ul>
								<li>Sample Search Results now ordered by IntID in descending order</li>
								<li>Patient Details and Sample Details are now merged into the same page</li>
								<li>Updating ReceivedDate now updates ReceivedDate of all samples types with same Internal ID</li>
							</ul>

							<h4>
								<span class="label label-info">New</span> Toggle buttons for Sample Types in Register Sample
							</h4>
							<ul>
								<li>This allows user to add sample types using buttons while choosing 0 to skip printing labels</li>
								<li>Sorry Kyle, still no checkboxes</li>
							</ul>

							<h4>
								<span class="label label-success">Add</span> Reprint multiple copies of same label
							</h4>

							<ul>
								<li>Search Sample -> Select samples to reprint -> Click on Reprint Label -> Enter # of copies to reprint.</li>
							</ul>
							<h4>
								<span class="label label-success">Add</span> Add new sample types to existing patient
							</h4>

							<ul>
								<li>Search Sample -> Click on the Sample ID to see Patient Info -> Click on Add Sample Type.</li>
							</ul>

							<h4>
								<span class="label label-important">Del</span> Search Plates and Register plates.
							</h4>
							<ul>
								<li>Plate tracking will be implemented as a separate system.</li>
							</ul>

							<h4>
								<span class="label label-important">Del</span> Register Multiple Samples
							</h4>
							<ul>
								<li>This function is rarely used and it needs to be redefined b/c Register Single Samples changed a bit since
									release.</li>
							</ul>

						</div>
					</div>
				</div>
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="act act-primary accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#august"> August
							2013 </a>
					</div>
					<div id="august" class="accordion-body collapse">
						<div class="accordion-inner">

							<h4>
								<span class="label label-success">Add</span> Initial Launch of STS2.0 with an updated interface
							</h4>

							<ul>
								<li>Added support for barcodes</li>
								<li>Added support for searching and registering plates</li>
								<li>Simplified search interface</li>
							</ul>

						</div>
					</div>
				</div>
			</div>

			<div class="alert">Database backup time is 11PM and lasts about 2 hours. During this time, STS will be unavailable.</div>

		</div>
	</div>
</body>