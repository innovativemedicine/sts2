<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
<c:if test="${entityName != '' }">
You have successfully deleted this <c:out value="${entityName}"/>! 
<br>
</c:if>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>