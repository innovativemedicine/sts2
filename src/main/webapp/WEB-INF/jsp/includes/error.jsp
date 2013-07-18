<spring:bind path="command.*">
	<c:if test="${not empty status.errorMessages}">

		<div class="page-alert custom-page-alert">
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">&times;</button>


				<c:forEach var="error" items="${status.errorMessages}">

					<c:out value="${error}" />
					<br>

				</c:forEach>
			</div>
		</div>
	</c:if>
</spring:bind>
