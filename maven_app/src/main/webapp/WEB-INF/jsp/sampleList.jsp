<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<form method="post">
	<div class="row">
		<div class="span10">
			<h2>Sample List</h2>

			<button class="btn" type="submit" name="action" value="Assign Container">Assign Container</button>
			<button class="btn" type="submit" name="action" value="Export Data">Export Data</button>
			<a href="#myModal" role="button" class="btn" data-toggle="modal">Reprint Labels</a>

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

	<!-- 	Print Label Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1">
		<div class="modal-body">
			<div class="alert alert-info">
				<h3>Print Label</h3>
			</div>

			<label># of copies:</label>
			<input required type="text" value="1" autofocus class="input-micro" name="numLabels" pattern="[0-9]{1,2}" />

		</div>

		<div class="modal-footer">
			<button class="btn" data-dismiss="modal">Close</button>
			<button class="btn btn-primary" type="submit" name="action" value="Print Labels">Print</button>
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>