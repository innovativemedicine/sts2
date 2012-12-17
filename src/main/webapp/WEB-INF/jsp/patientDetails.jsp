<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

<h2>Sample <c:out value="${command.intSampleId}"/></h2> 

	<div style="display:table-cell; vertical-align:bottom"> 
		<font style="vertical-align:bottom"size=5> Patient Information </font>
   		<a class="button" href="<c:url value="/editPatient.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>"><span>Edit Patient</span></a>
<%--    		<a class="button" href="<c:url value="/deletePatient.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>" onclick="return (confirm('Warning: Deleting the patient will delete all the samples of this patient as well as all the genotype results of those samples. \n\nAre you sure you want to delete this Patient?')) " ><span>Delete Patient</span></a> --%>
   	</div>
<p>
  
  <table class="details">
    
    <tr> 
      <th>External ID 1</th>
      <th>External ID 2</th>
      <th>Project </th>
      <th>Control? </th>
      <th>Note </th>
     </tr>
     <tr>
      	<td> 
        	<c:out value="${command.extSampleId}"/>
      	</td> 
      	<td>
        	<c:out value="${command.anotherExtSampleId}"/>
      	</td>
      	<td> 
        	<c:out value="${command.project.investigator.firstName}"/><c:out value="${command.project.name}"/>
      	</td>
      	<td> 
   	  		<c:out value="${command.isControl}"/>
	  	</td>
  		<td> 
        	<c:out value="${command.note}"/>
      	</td>
    </tr>
  </table>

  
	<p>
	<div> 	
  		<font style="vertical-align: bottom" size=5>Existing Sample Types </font>      	
   		<a class="button" 
   			href="<c:url value="/editSample.htm"><c:param name="intSampleId" value="${command.intSampleId}"/></c:url>">
   			<span>Add Sample Type</span>
   		</a>
    </div>
	<p>
	
   <table>
    <tr>
    	<th>Sample Type</th>
		<th>Duplication No.</th>
    	<th>Receive Date</th>
		<th>Concentration</th>
    </tr>
    
    <c:forEach items="${command.samples}" var="sample">
    <tr> 
    	<td><c:out value="${sample.sampleType.name}"/></td>
		<td><c:out value="${sample.sampleDupNo}"/></td>
		<td><c:out value="${sample.receiveDate}"/></td>
    	<td><c:out value="${sample.od}"/></td>
		<td>
			<a class="button" href="<c:url value="/sampleDetails.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>"><span>Details</span></a>
			<a class="button" href="<c:url value="/editSample.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>"><span>Edit</span></a>
		</td>

    </tr>
    </c:forEach>

  </table>
  
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>