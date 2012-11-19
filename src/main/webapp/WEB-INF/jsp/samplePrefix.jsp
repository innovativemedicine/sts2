<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
 
<tr><td>
<p><h2>Edit /New Sample Source Type</h2></p>
    
    <form method="post" >
          
	<table width="60%" border="0" class="details">
          
    <tr> 
        <td>Prefix</td>
        <td> 
    	<spring:bind path="command.prefix">
        <INPUT type="text" maxlength="32" size="30" name="prefix" value="<c:out value="${status.value}"/>">
    	</spring:bind>
        </td>
    </tr>

	<tr> 
        <td>Description <FONT color=RED>*</FONT></td>
        <td> 
    	<spring:bind path="command.description">
        <INPUT type="text" maxlength="32" size="30" name="description" value="<c:out value="${status.value}"/>">
        <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
    	</spring:bind>
        </td>
    </tr>

	<tr> 
        <td>Note </td>
        <td> 
    	<spring:bind path="command.note">
        <INPUT type="text" maxlength="32" size="30" name="note" value="<c:out value="${status.value}"/>">
    	</spring:bind>
        </td>
    </tr>

	<tr> 
        <td>Digit Number </td>
        <td> 
    	<spring:bind path="command.digitNumber">
        <INPUT type="text" maxlength="32" size="30" name="digitNumber" value="<c:out value="${status.value}"/>">
    	</spring:bind>
        </td>
    </tr>

	<tr> 
        <td>Columns </td>
        <td> 
    	<c:out value="${command.columns}"/>
        </td>
    </tr>

	<c:forEach items="${command.columnArray}" var="oneColumn" varStatus="loopStatus"> 
	<tr><td>
	Column <c:out value="${loopStatus.index}"/>
	</td>
	<td>
	<spring:bind path="command.columnArray[${loopStatus.index}]"> 
	  <select name="<c:out value="${status.expression}" />"   size="1" >
	   <c:forEach items="${allFields}" var="field">
    	     <option 
			   <c:if test="${command.columnArray[loopStatus.index] eq field}">
			   selected
			   </c:if>
			 
			 value="<c:out value="${field}"/>"><c:out value="${field}"/></option>
       </c:forEach>
	   </select>
	</spring:bind> 

    </td></tr>

	</c:forEach> 



    
   
       
    <tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="Submit" value="Edit">
          <input type="submit" name="Submit" value="New">
        </p>
        </td>
    </tr>
  </table>

    </form>


    <table width="70%" border="0" class="details">
    <tr>
    	<th>Description</th>
    	<th>Prefix</th>
		<th>Digit Number</th>
		<th>Note</th>
		<th>Columns</th>
    </tr>
    
    <c:forEach items="${allSamplePrefixes}" var="sp">
    <tr> 
    	<td>
    	<a href="<c:url value="/samplePrefix.htm">
			<c:param name="samplePrefixId" value="${sp.samplePrefixId}"/></c:url>">
			<c:out value="${sp.description}"/> &nbsp </a>
    	</td>
    	<td><c:out value="${sp.prefix}"/></td>
		<td><c:out value="${sp.digitNumber}"/></td>
		<td><c:out value="${sp.note}"/></td>
		<td><c:out value="${sp.columns}"/></td>
        
    </tr>
    </c:forEach>
   
  </table>
 
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>