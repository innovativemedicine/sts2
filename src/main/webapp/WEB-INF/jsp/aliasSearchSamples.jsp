<!-- Simple search "samples" infromation based on "Search Samples",
	Give user a easy using interface
	Jianan Xiao 2005-09-09
-->
<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<tr><td>
 
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<h2><fmt:message key="sample.search.title"/></h2>
<form method="post" enctype="multipart/form-data">
  <table width="70%" border="0" class="details">
    <tr><td>If you do not check sample projects, It will not search.</td></tr>
    <c:forEach items="${projectList}" var="project">
    <tr> 
    	<td><input type="checkbox" name="sampleProjects" 
    			value="<c:out value="${project.projectId}"/>">
			<c:out value="${project.name}"/>
    	</td>
    </tr>
    </c:forEach>
    <tr> 
      <td >
          <input type="submit" name="Submit" value="SEARCH">
      </td>
    </tr>
  </table>
</form> 

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>