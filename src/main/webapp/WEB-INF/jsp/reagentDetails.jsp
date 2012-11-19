<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<p><h2>Reagent Details</h2></p>
<a href="<c:url value="/editReagent.htm"><c:param name="reagentId" value="${command.reagentId}"/></c:url>">Edit it</a>&nbsp;&nbsp;<br>
<a href="<c:url value="/deleteReagent.htm"><c:param name="reagentId" value="${command.reagentId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete this reagent?')) ">Delete it</a>
</p>


  <table width="60%" border="0" class="details">
    <tr> 
          <td>Reagent Name</td>
          <td> 
		  <c:out value="${command.name}"/>&nbsp;
          </td>
    </tr>

	<tr> 
          <td>Sequence</td>
          <td> 
    	<c:out value="${command.seq}"/>&nbsp;
          </td>
    </tr>
    
    <tr> 
      <td>Concentration:</td>
      <td> 
	  <c:out value="${command.concentration}"/>&nbsp;
      </td>
    </tr>
    
    <tr> 
          <td>Manufacture</td>
          <td> 
		  <c:out value="${command.manufacture}"/>&nbsp;
          </td>
    </tr>
    
    <tr> 
      <td>Note</td>
      <td> 
	  <c:out value="${command.note}"/>&nbsp;
      </td>
    </tr>
    
    <tr> 
      <td>Container: </td>
      <td> 
	   <c:out value="${command.container.name}"/>&nbsp; at 
	   <c:out value="${command.container.location.name}"/>
   
   </td>
    </tr>
   
  
  </table>
  
  



 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>