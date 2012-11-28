<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
Your search criteria:
<c:out value="${currentCriteria}" escapeXml="false" />
<br>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

<h2>Search Containers:</h2>
<form method="post" >
  <table class="details">
   <%@ include file="/WEB-INF/jsp/includes/searchCore.jsp" %>
      
    </tr>

	<tr> 
          <td>
			<input type="checkbox" name="useFor" value="useFor"> For printing labels
          </td>
    </tr>
 
</form> 
</table>
<%@ include file="/WEB-INF/jsp/includes/containerListBody.jsp" %>
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>