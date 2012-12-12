<spring:bind path="command.*">
	<c:forEach var="error" items="${status.errorMessages}">
		<b><span style="color: red"> <br>
			<c:out value="${error}" />
		</span></b>
	</c:forEach>
</spring:bind>
