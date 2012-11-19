<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

<p><h2>Register Sample</h2></p>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>


<form method="post">


  <table border="0" class="details">
   

	<tr> 
      <td>Internal Id <FONT color=RED>*</FONT> </td>
	  
      <td> 
	 
	 
        <spring:bind path="command.patient.intSampleId">
	   <INPUT type="text" maxlength="255" size="30" 
	   	name="patient.intSampleId" 
	   	value="<c:out value="${status.value}"/>">
	   <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
	</spring:bind>
	
      </td>
	 
    </tr>

	 <tr> 
      <td>External ID 1:</td>
      <td> 
        <spring:bind path="command.patient.extSampleId">
	  <INPUT type="text" maxlength="255" size="30" 
	  	name="patient.extSampleId" 
	  	value="<c:out value="${status.value}"/>" > 
	</spring:bind>
      </td>
    </tr>

	<tr> 
      <td>External ID 2:</td>
      <td> 
        <spring:bind path="command.patient.anotherExtSampleId">
	  <INPUT type="text" maxlength="255" size="30" 
	  	name="patient.anotherExtSampleId" 
	  	value="<c:out value="${status.value}"/>" > 
	</spring:bind>
      </td>
    </tr>
	
  <tr>
  <c:set var="counter" value="0" />
    <td>Sample Type:<FONT color=RED>*</FONT></td><td> 
	  <c:forEach items="${availableSampleTypes}" var="sampleType" >	
	  	   
	    <input type="checkbox" name="<c:out value="${sampleType.suffix}"/>">
	     <c:out value="${sampleType.vials}"/> 
	     ( <c:out value="${sampleType.name}"/> ) &nbsp;&nbsp;
	     <c:set var="counter" value="${counter+1}" />
	      
	     <c:if test="${counter%3==0}">
        	<br>
         </c:if>
	  </c:forEach>
    </td>
  </tr>

  
    
    

	<tr> 
      <td>Sample Source:<FONT color=RED>*</FONT> </td>
      <td> 
      <spring:bind path="command.patient.project">
     <select size="1" name='<c:out value="${status.expression}" />'" >
       <option value="">select sample source</option>
	 <c:forEach items="${allProjects}" var="project">
	     <option 
		 
		 <c:if test="${command.patient.project != null && command.patient.project.projectId eq project.projectId}">
			   selected
		 </c:if>
		 
		 value="<c:out value="${project.projectId}"/>"><c:out value="${project.investigator.fullName}"/>&nbsp; <c:out value="${project.name}"/></option>
	  </c:forEach>
     </select>
  </spring:bind>

   </td>
    </tr>

	


   <tr> 
      <td>Patient First Name: </td>
      <td> 
	   <spring:bind path="command.patient.fname">
    	   <INPUT type="text" maxlength="255" size="30" name="patient.fname" value="<c:out value="${status.value}"/>">
    	</spring:bind>
   </td>
   </tr>



   <tr> 
      <td>Patient Middle Name: </td>
      <td> 
	   <spring:bind path="command.patient.mname">
    	   <INPUT type="text" maxlength="255" size="30" name="patient.mname" value="<c:out value="${status.value}"/>">
    	</spring:bind>
   </td>
   </tr>

   <tr> 
      <td>Patient Last Name: </td>
      <td> 
	   <spring:bind path="command.patient.lname">
    	   <INPUT type="text" maxlength="255" size="30" name="patient.lname" value="<c:out value="${status.value}"/>">
    	</spring:bind>
   </td>
   </tr>

   <tr> 
          <td>Family Id</td>
          <td> 
            <spring:bind path="command.patient.familyId">
    	   <INPUT type="text" maxlength="255" size="30" name="patient.familyId" value="<c:out value="${status.value}"/>">
    	</spring:bind>
          </td>
    </tr>

	<tr> 
      <td>Is control? </td>
      <td> 
	   <spring:bind path="command.patient.isControl">
      <select name="patient.isControl" size="1">
		 <option value="N">No</option>
	     <option value="Y">Yes</option>
		
	 
      </select>
      </spring:bind>
      </td>
    </tr>

   <tr> 
      <td>Patient Age: </td>
      <td> 
	  <spring:bind path="command.patient.age">
    	   <INPUT type="text" maxlength="255" size="30" name="patient.age" value="<c:out value="${status.value}"/>">
		    <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
    	</spring:bind>
   </td>
   </tr>

   <tr> 
      <td>Patient Date of Birth: </td>
      <td> 
	  <spring:bind path="command.patient.birthDate">
    	   <INPUT type="text" maxlength="255" size="30" name="patient.birthDate" value="<c:out value="${status.value}"/>">
		    <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
    	</spring:bind> (<fmt:message key="format.date"/>)
   </td>
   </tr>

   <tr> 
      <td>Patient Gender: </td>
      <td> 
	  <spring:bind path="command.patient.gender">
    	   <select name="patient.gender" size="1">
		 <option value="N">Unknown</option>
		 <option value="M">Male</option>
	     <option value="F">Female</option>
		
	 
      </select>
    	</spring:bind> 
   </td>
   </tr>
    
	 <tr> 
      <td>Sample Received On: </td>
      <td> 
        <spring:bind path="command.receiveDate">
	   <INPUT type="text" maxlength="255" size="30" name="receiveDate" value="<c:out value="${status.value}"/>">
	    <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
	</spring:bind> (<fmt:message key="format.date"/>)
      </td>
    </tr>

	<tr> 
      <td>Notes</td>
      <td> 
	<spring:bind path="command.patient.note">
	   <TEXTAREA rows="3" cols="30" name="patient.note"><c:out value="${status.value}"/></TEXTAREA> 
	</spring:bind>
      </td>
    </tr>
 
 <tr>
    <td>If print labels:</td>
    <td>    
     <input type="checkbox" name="labelNo" value="1" checked>
    </td>
  </tr>
  
    <tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="Submit" value="Save"  onclick="return (confirm('Are you sure all the data you entered are correct?')) " >
          <input type="reset" name="Submit2" value="Reset">
        </p>
        </td>
    </tr>
  </table>
  </form>
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>