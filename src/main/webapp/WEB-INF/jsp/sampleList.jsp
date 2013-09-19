<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<form method="post">
	<div class="row">
		<div class="span10">
			<h2>Sample List</h2>

			<button class="btn" type="submit" name="action" value="Assign Container">Assign Container</button>
			<button class="btn" type="submit" name="action" value="Export Data">Export Data</button>
			<button class="btn" type="submit" name="action" value="Print Labels">Reprint Labels</button>

			<div class="pull-right">

				<a class="btn btn-primary" href="<c:url value="/addSamples.htm"></c:url>">Register New Sample</a>
			</div>
		</div>
	</div>

	<p>
	<div class="row">
		<div class="span10">
			<%@ include file="/WEB-INF/jsp/includes/sampleListBody.jsp"%>
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>