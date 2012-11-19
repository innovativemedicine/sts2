<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

    
<form method="post">
<table width="70%" border="0" class="details">
	 
	 <tr> 

	 <td colspan="2"> Enter or paste your Internal sample Id here, 
	 delimited by comma, or one sample one line <br>
	<TEXTAREA name="sampleIdsInTextArea" rows="10" cols="60">
  
   </TEXTAREA>
    </td>
    </tr>

   <tr>
    <td >Sample Type: </td>
    <td >
	  <c:forEach items="${availableSampleTypes}" var="sampleType">
	    <input type="checkbox" name="<c:out value="${sampleType.suffix}"/>" >  
	    	<c:out value="${sampleType.name}"/>  &nbsp;&nbsp;
	  </c:forEach>
    </td>
  </tr>
  
<!--  
  <tr>
    <td  >Sample Dup No:<FONT color=RED></FONT> </td><td>
	   <select size="1" name="sampleDupNo">
	 <c:forEach items="${numbers}" var="aNumber">
	     <option value="<c:out value="${aNumber}"/>">
	     	<c:out value="${aNumber}"/></option>
	  </c:forEach>
      </select>
    </td>
  </tr>
-->

	<tr><td colspan="2">
	How many labels do you want to print for each sample?&nbsp;&nbsp;
	<input type="text" name="labelNo">
	</td></tr>


	<tr><td colspan="2">
	<input type="submit" name="Submit" value="Print">
 	<input type="reset" name="Reset" value="Reset">

	</td></tr>

</table>    
</form> 

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>