<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

<a href="<c:url value="/modifyRun.htm">
	<c:param name="runId" value="${command.runId}"/></c:url>">Edit it</a>&nbsp;<br>

<a href="<c:url value="/deleteRun.htm">
	<c:param name="runId" value="${command.runId}"/></c:url>" 
	 onclick="return (confirm('Are you sure you want to delete this run? 
	 	Read the warning carefully before you confirm!')) " >
	 Delete it</a>
	 <font color="red">Warning:</font> 
	 Deleting a run will also delete the results of that run from the 
	 database ! <p>
<p><h2>Run Details</h2></p>


  <table width="60%" border="0" class="details">
     
    <tr> 
          <td>Project: </td>
          <td> 
		  <c:out value="${command.project.name}"/>
       </td>
    </tr>

	<tr> 
              <td>Note:</td>
              <td> 
			  <c:out value="${command.note}"/>
              </td>
    </tr>

	<tr> 
              <td>Run Date:</td>
              <td> 
			  <c:out value="${command.runDate}"/>
              </td>
    </tr>

    <tr> 
	 <td>Test(s):</td>
	 <td>
	    <c:out value="${command.testNameList}"/>
	    
	 </td>
    </tr>

	 <tr> 
	 <td>Plate(s):</td>
	 <td>
	    <c:out value="${command.plateNameList}"/>
	
	 </td>
    </tr>

	
  </table>

  <br>

  <a href="<c:url value="/resultList.htm">
  	<c:param name="runId" value="${command.runId}"/></c:url>">
  	Genotype result of this run </a><br>
  <a href="<c:url value="/resultList.htm">
  	<c:param name="runId" value="${command.runId}"/>
  	<c:param name="linkageFormat" value="yes"/></c:url>">
  	Genotype result of this run in linkage output format</a><br>
	
	<br><br>
	 <h3>Assay List</h3>
  <form action="deleteRunAssay.htm" method="post" 
  	onsubmit="return validateAndSubmit(); ">
  	
  	<SCRIPT LANGUAGE="javascript">
		function validateAndSubmit(){	
 			return confirm("Are you sure you want to delete this assay's results in this RUN?");
 		}
	</SCRIPT>

  	<input type="hidden" name="runId"
           	value="<c:out value="${command.runId}"/>" />
  	<table width="70%" border="0" class="details">
  		<tr><th>Assay Name</th><th></th></tr>
	  	<c:forEach items="${uniAssayList}" var="assay">
    		<tr> 	<td>
    			<c:out value="${assay.name}"/>    		 
    		</td>   <td>
    	 		<input type="checkbox" name="assay" 
    	 			value="<c:out value="${assay.assayId}"/>"/>
    		</td>   </tr>
    	</c:forEach>
    	<tr>       <td colspan="2">
          <input type="submit" name="delete" value="Delete Assay Result">
        </td>      </tr>
  	</table>
  </form>
 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>