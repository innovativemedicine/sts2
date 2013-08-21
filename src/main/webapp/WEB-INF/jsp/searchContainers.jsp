<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Search Containers:</h2>

<form method="post" enctype="multipart/form-data">
	<div class="row-fluid">
		<div class="input-append">
		 	<input type="text" class="setFocus" name="containerIdFrom"> 
			<button class="btn" type="submit" name="action" value="Search">Search</button>
			<button class="btn" type="submit" name="action" value="Show">Show All</button>
			
		</div>
	</div>
</form>

<div class="row-fluid">
	<div class="span9">
		<%@ include file="/WEB-INF/jsp/includes/containerListBody.jsp"%>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>