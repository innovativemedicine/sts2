<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<p><h2>Make Plate</h2></p>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>
<form method="post" enctype="multipart/form-data">
  <table border="0" class="details">
    <tr> 
      <td>Plate Name</td>
      <td> 
         Maxmum length of name is 128 including space <br>
		<spring:bind path="command.name">
	  <INPUT type="text" maxlength="255" size="30" name="name" value="<c:out value="${status.value}"/>" > 
	  <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
	</spring:bind>
      </td>
    </tr>
   
    <tr> 
          <td>Created Date</td>
          <td> 
            <spring:bind path="command.createdDate">
    	   <INPUT type="text" maxlength="255" size="30" name="createdDate" value="<c:out value="${status.value}"/>"> (<fmt:message key="format.date"/>)
		   <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		    </FONT>
    	</spring:bind>
          </td>
    </tr>

	<tr> 
          <td>Concentration</td>
          <td> 
            <spring:bind path="command.concentration">
    	   <INPUT type="text" maxlength="255" size="30" name="concentration" value="<c:out value="${status.value}"/>"> (ug/ml)
		    <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		    </FONT>
    	</spring:bind>
          </td>
    </tr>
    
    
    <tr> 
      <td>Comments</td>
      <td> 
	<spring:bind path="command.comments">
	   <INPUT type="text" maxlength="255" size="30" name="comments" value="<c:out value="${status.value}"/>">
	</spring:bind>
      </td>
    </tr>
    

    
    <tr> 
      <td>Container Type: </td>
      <td> 
      <spring:bind path="command.containerType">
      <select size="1" name='<c:out value="${status.expression}" />' >
	 <c:forEach items="${allContainerTypes}" var="containerType">
	     <option value="<c:out value="${containerType.containerTypeId}"/>"><c:out value="${containerType.name}"/></option>
	  </c:forEach>
      </select>
      </spring:bind>
   </td>
    </tr>
    
    <tr> 
          <td>Location: </td>
          <td> 
          <spring:bind path="command.location">
	    <select size="1" name='<c:out value="${status.expression}" />'>
	  	 <c:forEach items="${allLocations}" var="location">
	  	     <option value="<c:out value="${location.locationId}"/>"><c:out value="${location.name}"/></option>
	  	  </c:forEach>
	     </select>
	  </spring:bind>
    	
       </td>
    </tr>
    
    <tr> 
      <td>Project </td>
      <td> 
      <spring:bind path="command.project">
     <select size="1" name='<c:out value="${status.expression}" />' >
	 <c:forEach items="${allProjects}" var="project">
	     <option value="<c:out value="${project.projectId}"/>"><c:out value="${project.name}"/></option>
	  </c:forEach>
     </select>
  </spring:bind>

   </td>
    </tr>

	 <tr> 
      <td>Sample Type: </td>
      <td>     
      <select size="1" name='sampleType'>
	 <c:forEach items="${availableSampleTypes}" var="sampleType">
	     <option 		 
		 value="<c:out value="${sampleType.suffix}"/>"><c:out value="${sampleType.name}"/></option>
	  </c:forEach>
      </select>    
	  </td>
    </tr>

	</tr>

	 <tr> 
          <td>Allocate well position</td>
          <td>
		  <input type="radio" name="wellPosition" value="Yes"> Yes <br>
		  <input type="radio" name="wellPosition" value="No"> No <br>
	</td>
    </tr>

	<tr> 
          <td>Plate file format</td>
          <td>
		
			<input type="radio" name="plateFormat" value="plate">SampleId In Plate Format <br>
			<input type="radio" name="plateFormat" value="sampleId">SampleId in List format <br>
			<input type="radio" name="plateFormat" value="sampleIdDupNo">SampleId,duplication number<br>
			<input type="radio" name="plateFormat" value="sampleIdWell">SampleId, sample location<br>
			<input type="radio" name="plateFormat" value="sampleIdSampleType">SampleId, sample type<br>
			<input type="radio" name="plateFormat" value="sampleIdSampleTypeWell">SampleId, sample type,sample location<br>
			
		
		  </td>
    </tr>

	<tr> 
          <td>Samples file</td>
          <td>
			
            <input type="file" name="file"/>
          </td>
    </tr>

	<tr> 
		<td colspan="2">
			<input type="checkbox" name="makePlateBlindly" value="makePlateBlindly"> Make plate without checking if samples exist. If not exist, creat new record in database for that sample.
        </td>
    </tr>
   
    <tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="Submit" value="Save">
          <input type="reset" name="Submit2" value="Reset">
        </p>
        </td>
    </tr>
  </table>
  </form>
  
  
 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>