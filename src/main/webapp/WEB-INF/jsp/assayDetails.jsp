<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<h2>Assay Details</h2>
<a href="<c:url value="/editAssay.htm"><c:param name="assayId" value="${command.assayId}"/></c:url>">Edit it</a><br>
<a href="<c:url value="/deleteAssay.htm"><c:param name="assayId" value="${command.assayId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete this assay? Read the warning carefully before you confirm!')) ">Delete it</a><font color="red">Warning:</font> You can not delete this assay unless there is not any genotype result for this assay.
<p>
  <table>
    
    <tr> 
          <td>Assay Name</td>
          <td> 
    	   <c:out value="${command.name}"/>&nbsp;
          </td>
    </tr>
    
    <tr> 
      <td>Assay location in Gene</td>
      <td> 
       <c:out value="${command.location}"/>&nbsp;
      </td>
    </tr>
    
    <tr> 
      <td>Note</td>
      <td> 
       <c:out value="${command.note}"/>&nbsp;
      </td>
    </tr>
  </table>
  
  <p>
  
  <table>
	<tr><th>Projects:</th></tr>
	  <tr>
	      <td>

	      <c:forEach items="${command.projects}" var="project">
	      <a href="<c:url value="/projectDetails.htm"><c:param name="projectId" value="${project.projectId}"/></c:url>" target="_blank"><c:out value="${project.name}"/></a>  ,  
	      </c:forEach>

	      </td>
	</tr>
 </table>
  
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>