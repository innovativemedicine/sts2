<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
<tr><td>



<c:if test="${entityName != '' }">
You have successfully deleted this <c:out value="${entityName}"/>! 

<br>
</c:if>
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>