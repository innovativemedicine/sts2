<c:if test="${(message != '' &&  not empty message)}">
	<div class="page-alert custom-page-alert">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<c:out value="${message}" escapeXml="false" />
		</div>
	</div>
</c:if>