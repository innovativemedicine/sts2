<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<tr><td>

<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<p><h2>Change container for sample </h2></p>

<form method="post" >
  <table width="60%" border="0" class="details">
	<tr> 
      <td>
        Sample Internal SampleId: <c:out value="${command.sample.patient.intSampleId}"/> <br>
		Sample Type: <c:out value="${command.sample.sampleType.name}"/> 
		 <c:if test="${command.sample.sampleDupNo != 1}">
			<c:out value="${command.sample.sampleDupNo}"/> 
		 </c:if>
        </td>
    </tr>

	<tr> 
	  <td>Container Name:<c:out value="${command.container.name}"/> 
	  </td>
    </tr>

	<tr> 
      <td>Well Location: <c:out value="${command.well}"/> 
	  </td>
    </tr>

	<tr>
    <td >Why remove it? 
	 <spring:bind path="command.reason">
     <select size="1" name='<c:out value="${status.expression}" />'" >
      <c:forEach items="${allReasons}" var="oneReason">
	     <option 
		 value="<c:out value="${oneReason}"/>"><c:out value="${oneReason}"/></option>
	  </c:forEach>
     </select>
  </spring:bind>
    </td>
  </tr>
	

	<tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="action" value="Save">
          <input type="reset" name="Submit2" value="Reset">
        </p>
        </td>
    </tr>

	</table>
  </form>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>