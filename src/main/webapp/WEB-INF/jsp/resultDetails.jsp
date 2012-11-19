<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

<a href="<c:url value="/editResult.htm"><c:param name="resultId" value="${result.resultId}"/></c:url>">Edit it</a>&nbsp;&nbsp;<br>

<a href="<c:url value="/deleteResult.htm"><c:param name="resultId" value="${result.resultId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete this result?')) ">Delete it</a>

<h2>Result Details</h2>
<table width="60%" border="0" class="details">
  <tr>
    <td>Sample:</td>
    <td><c:out value="${result.intSampleId}"/>&nbsp;</td>
  </tr>
  
  <tr>
      <td>Assay:</td>
      <td><c:out value="${result.assay.name}"/>&nbsp;</td>
  </tr>
  
  <tr>
      <td>Note:</td>
      <td><c:out value="${result.note}"/>&nbsp;</td>
  </tr>

  <tr>
      <td>Result:</td>
      <td><c:out value="${result.result}"/>&nbsp;</td>
  </tr>
  
  <tr>
      <td>Run:</td>
      <td>Project:<c:out value="${result.run.project.name}"/><br>
	  Date:<c:out value="${result.run.runDate}"/><br>
	  Note:<c:out value="${result.run.note}"/><br>
	  Test list:<c:out value="${result.run.testNameList}"/><br>
	  Plate list:<c:out value="${result.run.plateNameList}"/><br>	  
	  </td>
  </tr>
    
</table>
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>