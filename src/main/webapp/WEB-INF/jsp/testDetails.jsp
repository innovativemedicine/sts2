<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<p><h2>Test Details</h2></p>
<a href="<c:url value="/editTest.htm"><c:param name="testId" value="${command.testId}"/></c:url>">Edit it</a> &nbsp;&nbsp;
<a href="<c:url value="/deleteTest.htm"><c:param name="testId" value="${command.testId}"/></c:url>"  onclick="return (confirm('Are you sure you want to delete this test?)) " >Delete it</a>
</p>

  <table width="60%" border="0" class="details">
    <tr> 
      <td>Test Name</td>
      <td> 
        <c:out value="${command.name}"/>&nbsp;
      </td>
    </tr>
    
    
    <tr> 
          <td>Instrument: </td>
          <td> 
          <c:out value="${command.instrument.name}"/>&nbsp;
       </td>
    </tr>
    
    
    <tr> 
          <td>Protocal</td>
          <td> 
		   <c:forEach items="${protocals}" var="protocalName">
           <a href="<c:url value="protocals/${protocalName}"></c:url>"><c:out value="${protocalName}"/></a> ,
		    </c:forEach>
          </td>
    </tr>
    
    <tr> 
	 <td>Assay(s):</td>
	 <td>
	    <c:out value="${assayNameList}"/>
	 </td>
    </tr>
    
    <tr> 
    	 <td>Note:</td>
    	 <td>
    	    <c:out value="${command.note}"/>
    	 </td>
    </tr>
    
    <tr> 
	 <td>Project:</td>
	 <td>
	    <c:out value="${command.project.name}"/>
	 </td>
    </tr>

    
  </table>

  
 
  
 
 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>