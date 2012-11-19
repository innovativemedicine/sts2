<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<tr><td>

<p><h2>Edit Result </h2></p>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

<form method="post" >
  <table width="60%" border="0" class="details">
	<tr> 
      <td colspan="2">
        Sample: <c:out value="${command.intSampleId}"/><br>
		Assay: <c:out value="${command.assay.name}"/><br>
		Run: <c:out value="${command.run.note}"/><br>
        </td>
    </tr>

	<tr> 
	  <td>Result:</td>
	  <td> 
		<spring:bind path="command.result">
	   <INPUT type="text" maxlength="255" size="30" name="result" value="<c:out value="${status.value}"/>">
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
      <td colspan="2">
        <p>
          <input type="submit" value="Save">
          <input type="reset"  value="Reset">
        </p>
        </td>
    </tr>

	</table>
  </form>


</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>