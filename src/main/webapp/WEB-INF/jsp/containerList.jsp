<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<h2 class="noMargin">Container List</h2>
<form method="post">

	<input class="button buttonPad" type="submit" name="action"
		value="Export Data"> <input class="button buttonPad"
		type="submit" name="action" value="Print Labels">
</form>
<p>

<%@ include file="/WEB-INF/jsp/includes/containerListBody.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>