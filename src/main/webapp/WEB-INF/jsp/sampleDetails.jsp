<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<p><h2>Sample Details</h2> 
<a href="<c:url value="/editSample.htm"><c:param name="sampleId" value="${command.sampleId}"/></c:url>">Edit Sample Information</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


<a href="<c:url value="/deleteSample.htm"><c:param name="sampleId" value="${command.sampleId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete this sample? Read the warning carefully before you confirm!')) " >Delete it</a>&nbsp;<font color="red">Warning:</font> Deleting the sample will delete it from all the containers !<br>


</p>

<table>
<tr><td width="40%">Sample information</td>
	<td width="1%"> &nbsp</td>
	<td width="30%">	<a href="<c:url value="/editPatient.htm">
	<c:param name="intSampleId" value="${command.patient.intSampleId}"/></c:url>">
	Patient information</a>	</td>
</tr>

<tr><td>
  <table border="0" class="details">
    
    <tr> 
      <td>Internal Id:</td><td>
        <c:out value="${command.patient.intSampleId}"/>&nbsp;
      </td>
	</tr>
        
	 <tr> 
      <td>Sample Type: </td><td>
      <c:out value="${command.sampleType.name}"/>&nbsp;
	  </td>
   </tr>

   <tr>
     <td>Sample Received on: </td><td>
      <c:out value="${command.receiveDate}"/>&nbsp;
      </td>
   </tr>
    
    <tr> 
      <td>Concentration: </td><td>
        <c:out value="${command.od}"/>&nbsp;
      </td>
    </tr>

	<tr>
      <td>Reading On:</td><td>
          <c:out value="${command.odDate}"/>&nbsp;
          </td>
    </tr>
    
    <tr> 
	  <td>Volumn: </td><td>
	  <c:out value="${command.volumn}"/>&nbsp;
	  </td>
	</tr>

	<tr>
      <td>Reading on:</td><td>
    	  <c:out value="${command.volumnDate}"/>&nbsp;
    	  </td>
    </tr>
    
    <tr> 
      <td>Notes:</td><td>
      <c:out value="${command.notes}"/>&nbsp;
      </td>
	</tr>
    
     <tr>
	  <td>Status:</td><td>
      <c:out value="${command.status}"/>&nbsp;
      </td>
    </tr>
    
    <tr> 
      <td>Sample Made On: </td><td>
      <c:out value="${command.madeDate}"/>&nbsp;
      </td>
	</tr>

	<tr>
	   <td>Transformation On: </td><td>
	  <c:out value="${command.transDate}"/>&nbsp;
	  </td>
   </tr>
    
    <tr> 
 	  <td>Remove On: </td><td>
      <c:out value="${command.removeDate}"/>&nbsp;
      </td>
	</tr>
	<tr>
	  <td>Refill On: </td><td>
          <c:out value="${command.refillDate}"/>&nbsp;
          </td>
    </tr>
	<tr>
	  <td>Duplication Number:</td><td>
          <c:out value="${command.sampleDupNo}"/>&nbsp;
          </td>
    </tr>
    
 </table>
 </td>
 <td> &nbsp</td>
 <td valign="top">
 <table border="0" class="details">
 <tr><td>First Name</td>
 	<td><c:out value="${command.patient.fname }"/></td></tr>
 <tr><td>Middle Name</td>
 	<td><c:out value="${command.patient.mname }"/></td></tr>
 <tr><td>Last Name</td>
 	<td><c:out value="${command.patient.lname }"/></td></tr>
 <tr><td>Age</td>
 	<td><c:out value="${command.patient.age }"/></td></tr>
 <tr><td>Birthday</td>
 	<td><c:out value="${command.patient.birthDate }"/></td></tr>
 <tr><td>Gender</td>
 	<td><c:out value="${command.patient.gender }"/></td></tr>
 <tr><td>Family ID</td>
 	<td><c:out value="${command.patient.familyId }"/></td></tr>
 <tr><td>Is Controlled</td>
 	<td><c:out value="${command.patient.isControl }"/></td></tr>
    <tr> 
 <td>External Id 1:</td><td>
    <c:out value="${command.patient.extSampleId}"/>      
        </td></tr>
 <tr> <td>External Id 2:</td><td>
    <c:out value="${command.patient.anotherExtSampleId}"/>;
      </td>	</tr>

 <tr><td>Note</td>
 	<td><c:out value="${command.patient.note }"/></td></tr>

 </table>
 </td></tr>
</table>

   <p>
   <h3> Container(s) currently containing this sample: </h3>
      <font color="red">Warning:</font> Deleting this sample from container will remove  it the container but keep the sample in STS !
     <table width="60%" border="0" class="details">
       <tr>
        <td>Delete this sample from container</td>
	   	<td>Container Name</td>
       	<td>Container Type</td>
       	<td>Location in the Container</td>
       </tr>
       
       <c:forEach items="${command.samplesInContainersIn}" var="siContainer">

       <tr>
	    <td>
       	<a href="<c:url value="/deleteSampleFromContainer.htm"><c:param name="sicId" value="${siContainer.sicId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete the sample from this container? Read the warning carefully before you confirm!')) " >Delete</a>
       	</td>
       	<td>
       	<a href="<c:url value="/containerDetails.htm"><c:param name="containerId" value="${siContainer.container.containerId}"/></c:url>"><c:out value="${siContainer.container.name}"/></a>
       	</td>
       	<td><c:out value="${siContainer.container.containerType.name}"/>&nbsp;</td>
       	<td><c:out value="${siContainer.well}"/>&nbsp;</td>           
       </tr>
       </c:forEach>
      
  </table>

   <p>
   <h3> Sample Location History: </h3>
     
     <table width="60%" border="0" class="details">
       <tr>
       	<td>Container Name</td>
       	<td>Container Type</td>
       	<td>Location in the Container</td>
		<td>Remove Date</td>
		<td>Reason</td>
       </tr>
       
       <c:forEach items="${command.samplesInContainersOut}" var="siContainer">

       <tr>
	    
       	<td>
       	<c:out value="${siContainer.container.name}"/>
       	</td>
       	<td><c:out value="${siContainer.container.containerType.name}"/>&nbsp;</td>
       	<td><c:out value="${siContainer.well}"/>&nbsp;</td> 
		<td><c:out value="${siContainer.operationDate}"/>&nbsp;</td> 
		<td><c:out value="${siContainer.reason}"/>&nbsp;</td> 
       </tr>
       </c:forEach>
      
  </table>
  
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>