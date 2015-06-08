<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<a href="<c:url value="/editResult.htm"><c:param name="resultId" value="${result.resultId}"/></c:url>">Edit it</a><br>

<a href="<c:url value="/deleteResult.htm"><c:param name="resultId" value="${result.resultId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete this result?')) ">Delete it</a>

<h2>Result Details</h2>
<table class="table">
  <tr>
    <td>Sample:</td>
    <td><c:out value="${result.intSampleId}"/></td>
  </tr>
  
  <tr>
      <td>Assay:</td>
      <td><c:out value="${result.assay.name}"/></td>
  </tr>
  
  <tr>
      <td>Note:</td>
      <td><c:out value="${result.note}"/></td>
  </tr>

  <tr>
      <td>Result:</td>
      <td><c:out value="${result.result}"/></td>
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

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>