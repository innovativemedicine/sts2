<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<p><h2>Edit /New Run</h2></p>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

<form method="post" enctype="multipart/form-data">
  <table width="60%" border="0" class="details">
     
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
              <td>Note:</td>
              <td> 
                <spring:bind path="command.note">
        	   <INPUT type="text" maxlength="255" size="30" name="note" value="<c:out value="${status.value}"/>">
        	</spring:bind>
              </td>
    </tr>

	<tr> 
              <td>Run Date:</td>
              <td> 
                <spring:bind path="command.runDate">
        	   <INPUT type="text" maxlength="255" size="30" name="runDate" value="<c:out value="${status.value}"/>">
			    <FONT color="red">
		         <B><c:out value="${status.errorMessage}"/></B>
		        </FONT>
        	</spring:bind> (<fmt:message key="format.date"/>)
              </td>
    </tr>

    <tr> 
	 <td>Test(s):</td>
	 <td>
	    <c:out value="${command.testNameList}"/><br>
	    <select name="testIds" size="10" multiple>
	     <c:forEach items="${allTests}" var="test">
	     
		 <option value="<c:out value="${test.testId}"/>"><c:out value="${test.name}"/></option>

	     </c:forEach>
	   </select>
	    
	 </td>
    </tr>

	 <tr> 
	 <td>Plate(s):</td>
	 <td>
	    <c:out value="${command.plateNameList}"/><br>
	    <select name="plateIds" size="10" multiple>
	     <c:forEach items="${allPlates}" var="plate">
	     <option value="<c:out value="${plate.containerId}"/>"><c:out value="${plate.name}"/></option>
	     </c:forEach>
	   </select>
	    
	 </td>
    </tr>


	<tr> 
          <td>Result file format</td>
          <td> SampleId column number:
			<select name="sampleIdColumnNo" >
			    <c:forEach items="${allColumnNumbers}" var="colNo">
	              <option value="<c:out value="${colNo}"/>"><c:out value="${colNo}"/></option>
	            </c:forEach>
	       </select>  <br>
		   Assay Name column number:
			<select name="assayNameColumnNo" >
			    <c:forEach items="${allColumnNumbers}" var="colNo">
	              <option value="<c:out value="${colNo}"/>"><c:out value="${colNo}"/></option>
	            </c:forEach>
	       </select>  <br>
		   Result column number:
			<select name="resultColumnNo" >
			    <c:forEach items="${allColumnNumbers}" var="colNo">
	              <option value="<c:out value="${colNo}"/>"><c:out value="${colNo}"/></option>
	            </c:forEach>
	       </select>
			
		  </td>
    </tr>

	 <tr> 
          <td>Upload Result file:</td>
          <td>
             <input type="file" name="file"/> <br>
			 (tab delimited text file)
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