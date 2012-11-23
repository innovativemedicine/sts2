<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
		
<tr><td>

<h2>Register Multiple Sample</h2>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>
 
<form method="post">
	How many samples? <input type="text" name="numOrder" />
	<input type="button" onclick="tableTester(parseInt(numOrder.value, 10), 6);" value="Create Table">	
	<div id="FormFields" style="margin: 20px 0px;"></div>
</form>

<form method="post">

  <div id="wrapper" style="margin: 20px 0px;"></div>
<table class="details">

	<tr>
		<th>Barcode ID</th>
		<th>External ID</th>
		<th>External ID2</th>
		<th>Sample Type</th>
		<th>Project</th>
		<th>Notes</th>
	<tr>
	
	 <c:forEach var="row" begin="1" end="1" step="1" varStatus="rowStatus">
	
	<tr> 
      	<td> 	 
        	<spring:bind path="command.patient.intSampleId">
			   		<INPUT type="text" maxlength="255" size="20" 
			   		name="patient.intSampleId" 
			   		value="<c:out value="${status.value}"/>">
			   		<FONT color="red">
				    	<B><c:out value="${status.errorMessage}"/></B>
					</FONT>
			</spring:bind>
      	</td>
	 
     	<td> 
        	<spring:bind path="command.patient.extSampleId">
		  		<INPUT type="text" maxlength="64" size="20" 
		  			name="patient.extSampleId" 
		  			value="<c:out value="${status.value}"/>" > 
			</spring:bind>
      	</td>
    
        <td> 
        	<spring:bind path="command.patient.anotherExtSampleId">
		  		<INPUT type="text" maxlength="64" size="20" 
		  		name="patient.anotherExtSampleId" 
		  		value="<c:out value="${status.value}"/>" > 
			</spring:bind>
		</td>
		
    	<td>
	    	<spring:bind path="command.sampleType.sampleTypeId">

	    	<select size="1" name='<c:out value="${status.expression}" />' >
	       		<option value="">Select Sample Type</option>
			       
	       		<c:forEach items="${allSampleTypes}" var="sampleTypeOpt" >
	       	
	       		<option value="<c:out value="${sampleTypeOpt.sampleTypeId}"/>">
					<c:out value="${sampleTypeOpt.name}"/> - 
					<c:out value="${sampleTypeOpt.sampleTypeId}"/>
			 	</option>
			 	
		  		</c:forEach>
		  		  
	    	</select> 
	    	</spring:bind>
    	</td>
  
	 	<td> 
      		<spring:bind path="command.patient.project">
     			<select size="1" name='<c:out value="${status.expression}" />' >
       				<option value="">Select Project</option>
					 	<c:forEach items="${allProjects}" var="project">
					     <option 
					     
							 value="<c:out value="${project.projectId}"/>"><c:out value="${project.name}"/>
						 
						</option>
					  	
<%-- <option	<c:if test="${command.patient.project != null && command.patient.project.projectId eq project.projectId}">selected</c:if> value="<c:out value="${project.projectId}"/>"><c:out value="${project.investigator.firstName}"/>&nbsp; <c:out value="${project.name}"/></option> --%>
					
					  	
					  	</c:forEach>
     			</select>
  			</spring:bind>

   		</td>

	    <td> 
			<spring:bind path="command.patient.note">
			   	<TEXTAREA rows="1" cols="20" name="patient.note"><c:out value="${status.value}"/></TEXTAREA> 
			</spring:bind>
      	</td>
      	
    </c:forEach>
    
    		 <tr>
		    <td>If print labels: <input type="checkbox" name="labelNo" value="1" checked>
		    </td>
		  </tr>
		  
		      <tr> 
      <td >
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