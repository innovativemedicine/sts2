<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<tr><td>
Your search criteria:
<c:out value="${currentCriteria}" escapeXml="false" />
<br>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<h2><fmt:message key="project.search.title"/></h2>
<form method="post" action="<c:url value="/projects.htm"/>">
  <table width="70%" class="details">
  <%@ include file="/WEB-INF/jsp/includes/searchCore.jsp" %>
    </table>
</form> 
<%@ include file="/WEB-INF/jsp/includes/projectListBody.jsp" %>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
