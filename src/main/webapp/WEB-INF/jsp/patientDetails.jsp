<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

<h2>Sample <c:out value="${command.intSampleId}"/></h2> 

	<div style="display:table-cell; vertical-align:bottom"> 
		<font style="vertical-align:bottom"size=5> Patient Information </font>
   		<a class="button" href="<c:url value="/editPatient.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>"><span>Edit Patient</span></a>
   		<a class="button" href="<c:url value="/deletePatient.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>" onclick="return (confirm('Warning: Deleting the patient will delete all the samples of this patient as well as all the genotype results of those samples. \n\nAre you sure you want to delete this Patient?')) " ><span>Delete Patient</span></a>
   	</div>
<p>
  
  <table width = "40%" border="0" class="details">
    
    <tr> 
      <th>External ID 1:</th>
      <td> 
        <c:out value="${command.extSampleId}"/>&nbsp;
      </td>
    </tr>

	<tr> 
      <th>External ID 2:</th>
      <td> 
        <c:out value="${command.anotherExtSampleId}"/>&nbsp;
      </td>
    </tr>

	<tr> 
      <th>Project: </th>
      <td> 
        <c:out value="${command.project.investigator.fullName}"/>&nbsp;<c:out value="${command.project.name}"/>&nbsp;
      </td>
    </tr>
 
    <tr> 
      <th>Control? </th>
      <td> 
      <c:out value="${command.isControl}"/>&nbsp;
   </td>
  </tr>

   <tr> 
      <th>Note:</th>
      <td> 
        <c:out value="${command.note}"/>&nbsp;
      </td>
    </tr>
  </table>

  
	<p>
	<div> 	
  		<font style="vertical-align: bottom" size=5>Existing Sample Types </font>      	
   		<a class="button" href="<c:url value="/editSample.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>"><span>Add Sample</span></a>
    </div>
	<p>
	
   <table width="70%" border="0" class="details">
    <tr>
    	<th>Sample Type</th>
		<th>Sample Duplication Number</th>
    	<th>Sample Receive Date</th>
		<th>Concentration</th>
    </tr>
    
    <c:forEach items="${command.samples}" var="sample">
    <tr> 
    	<td><c:out value="${sample.sampleType.name}"/>&nbsp;</td>
		<td><c:out value="${sample.sampleDupNo}"/>&nbsp;</td>
		<td><c:out value="${sample.receiveDate}"/>&nbsp;</td>
    	<td><c:out value="${sample.od}"/>&nbsp;</td>
		<td>
			<a class="button" href="<c:url value="/sampleDetails.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>"><span>Details</span></a>
			<a class="button" href="<c:url value="/editSample.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>"><span>Edit</span></a>
		</td>

    </tr>
    </c:forEach>

  </table>
  
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>