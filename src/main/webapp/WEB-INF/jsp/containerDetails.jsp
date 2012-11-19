<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

<p><h2> Container Details</h2>
<a href="<c:url value="/editContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>">Edit it</a><br>

<a href="<c:url value="/emptyContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>" onclick="return (confirm('Are you sure you want to empty this container? Read the warning carefully before you confirm!')) ">Empty it</a>&nbsp; Remove all samples from this container, but keep  samples in the STS <br>

<a href="<c:url value="/deleteContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete this container? Read the warning carefully before you confirm!')) " >Delete it</a> &nbsp;<font color="red">Warning:</font> You can not delete this container unless there is no samples and reagents in this container.<br>

<c:if test="${command.sampleBoxOrPlate}">
<a href="<c:url value="/deleteAllSamplesInContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete all samples in this container? Read the warning carefully before you confirm!')) " >Delete All</a> &nbsp;<font color="red">Warning:</font> Will delete all samples in this container from STS, including the sample located at other container as well as all genotype results 
related to this sample.
</p>
</c:if>

  <table width="60%" border="0" class="details">
    <tr> 
      <td>Container Name</td>
      <td> 
       <c:out value="${command.name}"/>&nbsp;
      </td>
    </tr>
    <tr> 
      <td>External Container Name</td>
      <td> 
       <c:out value="${command.extContainerId}"/>&nbsp;
      </td>
    </tr>
    <tr> 
          <td>Created Date</td>
          <td> 
          <c:out value="${command.createdDate}"/>&nbsp;
          </td>
    </tr>

	<tr> 
      <td>Concentration</td>
      <td> 
      <c:out value="${command.concentration}"/>&nbsp;
      </td>
    </tr>
    
    <tr> 
	  <td>Status</td>
	  <td> 
	  <c:out value="${command.status}"/>&nbsp;
	  </td>
    </tr>
    
    <tr> 
      <td>Comments</td>
      <td> 
      <c:out value="${command.comments}"/>&nbsp;
      </td>
    </tr>
    
    <tr> 
      <td>Container Type: </td>
      <td> 
      <c:out value="${command.containerType.name}"/>&nbsp;
   </td>
    </tr>
    
    <tr> 
          <td>Location: </td>
          <td> 
          <c:out value="${command.location.name}"/>&nbsp;
       </td>
    </tr>
    
    <tr> 
      <td>Project </td>
      <td> 
      <c:out value="${command.project.name}"/>&nbsp;
   </td>
    </tr>

	 <tr> 
      <td>Total Samples: </td>
      <td> 
      <c:out value="${command.totalSamples}"/>&nbsp;
   </td>
    </tr>
   
  </table>
  
  <!--
  <br>
  <c:if test="${command.sampleBoxOrPlate}">
   <p><a href="<c:url value="/editSample.htm"><c:param name="sampleId" value="-1"/><c:param name="containerId" value="${command.containerId}"/></c:url>" >Add A New Sample to This  Container</a>
   <p>
   <a href="<c:url value="/saveSamplesInBatch.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>">Load New Samples In Batch</a>
   <p>
   </c:if>
   -->

   <br>
  <c:if test="${command.noneSample}">
   <p><a href="<c:url value="/containerContents.htm"><c:param name="containerId" value="${command.containerId}"/><c:param name="isOrdered" value="ordered"/></c:url>" >Edit Sample Map</a> &nbsp; &nbsp; &nbsp;&nbsp;<a href="<c:url value="/containerContents.htm"><c:param name="containerId" value="${command.containerId}"/><c:param name="isOrdered" value="unOrdered"/></c:url>" >Edit Sample List</a>
   </c:if>

   <br>
   <c:if test="${command.orderedSample}">
      <h3>Sample Map</h3>
	
	 <p><a href="<c:url value="/containerContents.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>" >Edit Sample Map</a> 

	 <p>
	  <a href="<c:url value="/shuffleSamplesInContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>" >shuffle the samples</a>

      <table border="0" class="details" cellspacing="3">
         <c:forEach items="${orderedSamples}" var="oneRow">
          <tr> 
          <c:forEach items="${oneRow}" var="sample">
          <td width=60><a href="<c:url value="/sampleDetails.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>" ><c:out value="${sample.patient.intSampleId}"/><c:out value="${sample.sampleType.suffix}"/></a>&nbsp;
          </c:forEach>
          </td>
          </c:forEach>
          
          </tr>
        </table>
		Get sampleId list
	   <a href="<c:url value="/sampleIdList.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>" target="_blank">in list format</a> or 
	   <a href="<c:url value="/sampleIdMap.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>" target="_blank"> in plate format</a>
       <p>
   </c:if>


   <c:if test="${command.unorderedSample}">
   <h3>Sample List:</h3>
  
    <p><a href="<c:url value="/containerContents.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>" >Edit Sample List</a>
	<br>
   
       <table width="80%" border="0" class="details" cellspacing="3">
       <tr>
    	<th>Internal ID</th>
    	<th>External ID</th>
    	<th>Family ID</th>
    	<th>Sample Type</th>
    	<th>Status</th>
        </tr>
      
       <c:forEach items="${unOrderedSamples}" var="sample">
       
    	<td>
    	<a href="<c:url value="/sampleDetails.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>"><c:out value="${sample.patient.intSampleId}"/></a>&nbsp;
    	</td>
    	<td><c:out value="${sample.patient.extSampleId}"/>&nbsp;</td>
    	<td><c:out value="${sample.patient.familyId}"/>&nbsp;</td>
    	<td><c:out value="${sample.sampleType.name}"/>&nbsp;</td>
    	<td><c:out value="${sample.status}"/>&nbsp;</td>
    	
       
        
        </tr>
       </c:forEach>
       </td></tr>
       
       </table>     
       </c:if>

      <br>
	   <c:if test="${command.sampleBoxOrPlate}">
		<c:if test="${command.hasMotherContainer}">
		<h3>Mother Container:</h3>  <a href="<c:url value="/containerDetails.htm"><c:param name="containerId" value="${command.motherContainer.containerId}"/></c:url>" ><c:out value="${command.motherContainer.name}"/></a>
		</c:if>

      <table width="80%" border="0" class="details">
            <tr><th>Child Containers:</th></tr>
            <tr><td>
            <p><a href="<c:url value="/editContainer.htm"><c:param name="motherContainerId" value="${command.containerId}"/></c:url>" >Copy this Container to make a new plate</a>
            </td></tr>
           
              <tr>
                  <td>
                  
                  <c:forEach items="${command.childContainers}" var="container">
                  <a href="<c:url value="/containerDetails.htm"><c:param name="containerId" value="${container.containerId}"/></c:url>" ><c:out value="${container.name}"/></a>  ,  
                  </c:forEach>
                  
                  </td>
            </tr>
            
      </table>
	  </c:if>

	  <br>


    <c:if test="${command.reagentBox}">
      <h3>Reagent:</h3>
      
      <p><a href="<c:url value="/editReagent.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>">Add A New Reagent in This Container</a>
	  <table width="80%" border="0" class="details">    
			<tr>
			<th>Reagent Name</th>
			<th>Concentration</th>
			<th>Note</th>
			</tr>
            
            <c:forEach items="${command.reagents}" var="reagent">
            <tr> 
			<td>
			<a href="<c:url value="/reagentDetails.htm"><c:param name="reagentId" value="${reagent.reagentId}"/></c:url>"><c:out value="${reagent.name}"/></a>
			</td>
			<td><c:out value="${reagent.concentration}"/>&nbsp;</td>
			<td><c:out value="${reagent.note}"/> &nbsp;</td>
			
			</tr>
            </c:forEach>
      </table>
	  </c:if>
      <br><br>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>