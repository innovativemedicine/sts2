<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
Your search criteria:
<c:out value="${currentCriteria}" escapeXml="false" />
<br>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<h2><fmt:message key="test.search.title"/></h2>
<form method="post">
  <table width="70%" class="details">
   <%@ include file="/WEB-INF/jsp/includes/searchCore.jsp" %>
    </table>
</form> 
<%@ include file="/WEB-INF/jsp/includes/testListBody.jsp" %></td></tr>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>