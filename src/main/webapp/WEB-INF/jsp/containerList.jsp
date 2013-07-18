<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Container List</h2>
<form method="post">

	<input disabled class="btn" type="submit" name="action"
		value="Export Data"> <input class="btn"
		type="submit" name="action" value="Print Labels">
</form>
<p>

<%@ include file="/WEB-INF/jsp/includes/containerListBody.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>