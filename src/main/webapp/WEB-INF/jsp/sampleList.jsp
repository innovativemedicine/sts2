<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<form method="post">

	<h2>Sample List</h2>

	<a class="btn" href="<c:url value="/addSample2Container.htm"></c:url>">Assign
		Container</a> <input class="btn" type="submit" name="action"
		value="Export Data"> <input class="btn" type="submit"
		name="action" value="Print Labels">

</form>

<div class="row">
	<div class="span12">
		<%@ include file="/WEB-INF/jsp/includes/sampleListBody.jsp"%>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>