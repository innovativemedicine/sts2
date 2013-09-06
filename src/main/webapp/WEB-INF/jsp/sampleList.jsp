<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<div class="row">
	<div class="span12">
		<h2>Sample List</h2>

			<form method="post">
				<a class="btn"
					href="<c:url value="/addSample2Container.htm"></c:url>">Assign
					Container</a>
				<button class="btn" type="submit" name="action" value="Export Data">Export
					Data</button>
				<button class="btn" type="submit" name="action" value="Print Labels">Reprint
					Labels</button>
				<a class="btn"
					href="<c:url value="/addSamples.htm"></c:url>">Add New Sample</a>

			</form>

	</div>
</div>
<div class="row">
	<div class="span12">
		<%@ include file="/WEB-INF/jsp/includes/sampleListBody.jsp"%>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>