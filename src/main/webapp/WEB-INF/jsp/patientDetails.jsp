<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

<p><h2>Sample <c:out value="${command.intSampleId}"/>&nbsp;</h2> 


<a href="<c:url value="/editPatient.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>">Edit Patient Information</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<a href="<c:url value="/editSample.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>">Add Other Sample Types</a><br>

<a href="<c:url value="/deletePatient.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete this Patient? Read the warning carefully before you confirm!')) " >Delete Patient</a>&nbsp;<font color="red">Warning:</font> Deleting the patient will delete all the samples of this patient as well as all the genotype results of those samples<br>
</p>

  <table width="60%" border="0" class="details">
    
    <tr> 
      <td>External Id 1:</td>
      <td> 
        <c:out value="${command.extSampleId}"/>&nbsp;
      </td>
    </tr>

	<tr> 
      <td>External Id 2:</td>
      <td> 
        <c:out value="${command.anotherExtSampleId}"/>&nbsp;
      </td>
    </tr>

	<tr> 
      <td>Sample Source: </td>
      <td> 
        <c:out value="${command.project.investigator.fullName}"/>&nbsp;<c:out value="${command.project.name}"/>&nbsp;
      </td>
    </tr>
  
   <tr> 
      <td>Patient Name: </td>
      <td> 
      <c:out value="${command.fname}"/>&nbsp;<c:out value="${command.mname}"/>&nbsp;<c:out value="${command.lname}"/>&nbsp;

   </td>
   </tr>

    <tr> 
      <td>Patient Age: </td>
      <td> 
      <c:out value="${command.age}"/>&nbsp;
   </td>
   </tr>

    <tr> 
      <td>Patient Date of Birth: </td>
      <td> 
      <c:out value="${command.birthDate}"/>&nbsp;
   </td>
   </tr>

   <tr> 
      <td>Patient Gender: </td>
      <td> 
      <c:out value="${command.gender}"/>&nbsp;
   </td>
   </tr>


   <tr> 
          <td>Family Id</td>
          <td>
            <c:out value="${command.familyId}"/>&nbsp;
          </td>
    </tr>

  

    <tr> 
      <td>Control? </td>
      <td> 
      <c:out value="${command.isControl}"/>&nbsp;
   </td>
  </tr>

   <tr> 
      <td>Patient Note</td>
      <td> 
        <c:out value="${command.note}"/>&nbsp;
      </td>
    </tr>
  </table>

   <h3>Existing Sample Types </h3>

   <table width="70%" border="0" class="details">
    <tr>
    	<th>Sample Type</th>
		<th>Sample Duplication Number</th>
    	<th>Sample Receive Date</th>
		<th>Concentration</th>
		<th></th>
		<th></th>
		
		
		
    </tr>
    
    <c:forEach items="${command.samples}" var="sample">
    <tr> 
    	<td><c:out value="${sample.sampleType.name}"/>&nbsp;</td>
		<td><c:out value="${sample.sampleDupNo}"/>&nbsp;</td>
		<td><c:out value="${sample.receiveDate}"/>&nbsp;</td>
    	<td><c:out value="${sample.od}"/>&nbsp;</td>
		<td><a href="<c:url value="/sampleDetails.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>">Details</a></td>
		<td><a href="<c:url value="/editSample.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>">Edit It</a></td>
		<!--
		<td><a href="<c:url value="/editSamplesInContainer.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>">Put it into container</a></td>
		-->

    </tr>
    </c:forEach>

  </table>
  
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>