<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<tr><td>
<h2>Edit Instruments</h2>
    
    <form method="post" action="instrument.htm" >
          
	<table class="table" class="details">
          
    <tr> 
        <td>Instrument Name</td>
        <td> 
    	<spring:bind path="command.name">
        <INPUT type="text" maxlength="64" size="20" name="name" value="<c:out value="${status.value}"/>">
		<FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
    	</spring:bind>
        </td>
    </tr>
    
    <tr> 
      <td>Note</td>
      <td> 
	<spring:bind path="command.note">
	   <INPUT type="text" maxlength="100" size="20" name="note" value="<c:out value="${status.value}"/>">
	</spring:bind>
      </td>
    </tr>
       
    <tr> 
      <td colspan="2">
          <input class="btn" type="submit" name="Submit" value="Edit">
          <input class="btn" type="submit" name="Submit" value="New">
        </td>
    </tr>
  </table>

	<p>
    </form>
    <table class="table" class="details">
    <tr class="info">
    	<td><b>Instrument Name</b></td>
    	<td><b>Note</b></td>
    </tr>
    
    <c:forEach items="${LInstruments}" var="container">
    <tr> 
    	<td>
    	<a href="<c:url value="/instrument.htm">
			<c:param name="instrumentId" value="${container.instrumentId}"/></c:url>">
			<c:out value="${container.name}"/></a>
    	</td>
    	<td><c:out value="${container.note}"/></td>
        
    </tr>
    </c:forEach>
   
  </table>
 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>