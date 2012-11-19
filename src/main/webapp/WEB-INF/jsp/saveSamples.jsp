<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<p><h2>Upload Samples In Batch</h2></p>

<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

<form method="post" enctype="multipart/form-data">
  <table width="60%" border="0" class="details">
	  <tr> 
      <td>Sample Type: </td>
      <td> 
      <spring:bind path="command.sampleType">
      <select size="1" name='<c:out value="${status.expression}" />'>
	 <c:forEach items="${availableSampleTypes}" var="sampleType">
	     <option 
		  
		  
		  <c:if test="${command.sampleType != null && command.sampleType.sampleTypeId eq sampleType.sampleTypeId}">
			   selected
		  </c:if>
		 
		 value="<c:out value="${sampleType.sampleTypeId}"/>"><c:out value="${sampleType.name}"/></option>
	  </c:forEach>
      </select>
      </spring:bind>
	  </td>
    </tr>

    <tr> 
          <td>Project: </td>
          <td> 
          <spring:bind path="command.project">
          <select name='<c:out value="${status.expression}" />' size="1">
          
    	 <c:forEach items="${allProjects}" var="project">
    	     <option 
			 
			 <c:if test="${command.project != null && command.project.projectId eq project.projectId}">
			   selected
		  </c:if>

			 value="<c:out value="${project.projectId}"/>"><c:out value="${project.name}"/></option>
    	  </c:forEach>
          </select>
          </spring:bind>
    
       </td>
    </tr>
    
	<tr> 
      <td>Is these samples the control samples?: </td>
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
          <td>Samples file type</td>
          <td>
			<c:forEach items="${allSamplePrefixes}" var="samplePrefix">
			<input type="radio" name="format" value="<c:out value="${samplePrefix.description}"/>"><c:out value="${samplePrefix.description}"/> <br>
			
		    </c:forEach>
		  </td>
    </tr>

	

	<tr> 
          <td>Data operation:</td>
          <td>
		  <input type="radio" name="operation" value="update"> Update existing samples <br>
		  <input type="radio" name="operation" value="new"> Insert new samples <br>
	</td>
    </tr>

			
	<tr> 
          <td>Samples file</td>
          <td>
			
            <input type="file" name="file"/>
          </td>
    </tr>

	 <tr>
    <td>How many labels you want to print: &nbsp;&nbsp; </td>   
     <td><input type="text" name="labelNo">
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