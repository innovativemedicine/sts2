<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
<tr><td>

<c:if test="${message != '' }">
<font color="green"><h2>
<c:out value="${message}" escapeXml="false" />
<br>
</c:if>
</h2></font>
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>