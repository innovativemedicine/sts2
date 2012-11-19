<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

<p><h2>Edit Patient for this sample <c:out value="${command.intSampleId}"/> </h2></p>

<form method="post">
  <table border="0" class="details">
   
   

   <tr> 
      <td>External ID 1:</td>
      <td> 
        <spring:bind path="command.extSampleId">
	  <INPUT type="text" maxlength="255" size="30" name="extSampleId" value="<c:out value="${status.value}"/>" > 
	</spring:bind>
      </td>
    </tr>

	<tr> 
      <td>External ID 2:</td>
      <td> 
        <spring:bind path="command.anotherExtSampleId">
	  <INPUT type="text" maxlength="255" size="30" name="anotherExtSampleId" value="<c:out value="${status.value}"/>" > 
	</spring:bind>
      </td>
    </tr>

	<tr> 
      <td>Sample Source: </td>
      <td> 
      <spring:bind path="command.project">
     <select size="1" name='<c:out value="${status.expression}" />'" >
    
	 <c:forEach items="${allProjects}" var="project">
	     <option 
		 
		 <c:if test="${command.project != null && command.project.projectId eq project.projectId}">
			   selected
		 </c:if>
		 
		 value="<c:out value="${project.projectId}"/>"><c:out value="${project.investigator.fullName}"/>&nbsp; <c:out value="${project.name}"/></option>
	  </c:forEach>
     </select>
  </spring:bind>

   </td>
    </tr>

	<tr> 
      <td>Patient Note: </td>
      <td> 
	   <spring:bind path="command.note">
    	   <INPUT type="text" maxlength="255" size="30" name="note" value="<c:out value="${status.value}"/>">
    	</spring:bind>
   </td>
   </tr>


   <tr> 
      <td>Patient First Name: </td>
      <td> 
	   <spring:bind path="command.fname">
    	   <INPUT type="text" maxlength="255" size="30" name="fname" value="<c:out value="${status.value}"/>">
    	</spring:bind>
   </td>
   </tr>



   <tr> 
      <td>Patient Middle Name: </td>
      <td> 
	   <spring:bind path="command.mname">
    	   <INPUT type="text" maxlength="255" size="30" name="mname" value="<c:out value="${status.value}"/>">
    	</spring:bind>
   </td>
   </tr>

   <tr> 
      <td>Patient Last Name: </td>
      <td> 
	   <spring:bind path="command.lname">
    	   <INPUT type="text" maxlength="255" size="30" name="lname" value="<c:out value="${status.value}"/>">
    	</spring:bind>
   </td>
   </tr>

   <tr> 
          <td>Family Id</td>
          <td> 
            <spring:bind path="command.familyId">
    	   <INPUT type="text" maxlength="255" size="30" name="familyId" value="<c:out value="${status.value}"/>">
    	</spring:bind>
          </td>
    </tr>

	<tr> 
      <td>Is control? </td>
      <td> 
	   <spring:bind path="command.isControl">
      <select name="isControl" size="1">
		<option value="N">No</option>
	     <option value="Y">Yes</option>
		 
	 
      </select>
      </spring:bind>
      </td>
    </tr>

   <tr> 
      <td>Patient Age: </td>
      <td> 
	  <spring:bind path="command.age">
    	   <INPUT type="text" maxlength="255" size="30" name="age" value="<c:out value="${status.value}"/>">
		    <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
    	</spring:bind>
   </td>
   </tr>

   <tr> 
      <td>Patient Date of Birth: </td>
      <td> 
	  <spring:bind path="command.birthDate">
    	   <INPUT type="text" maxlength="255" size="30" name="birthDate" value="<c:out value="${status.value}"/>">
		    <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
    	</spring:bind> (<fmt:message key="format.date"/>)
   </td>
   </tr>

   <tr> 
      <td>Patient Gender: </td>
      <td> 
	  <spring:bind path="command.gender">
    	   <INPUT type="text" maxlength="255" size="30" name="gender" value="<c:out value="${status.value}"/>">
		    <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
    	</spring:bind> (F or M)
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