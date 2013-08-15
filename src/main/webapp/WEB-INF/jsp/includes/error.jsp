<c:if test="${(err != '' &&  not empty err)}">
	<div class="page-alert custom-page-alert">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<c:out value="${err}" escapeXml="false" />
		</div>
	</div>
</c:if>

