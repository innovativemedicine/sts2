<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<!-- 
Your search criteria:
<c:out value="${currentCriteria}" escapeXml="false" />
-->
<br>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<h2><fmt:message key="result.search.title"/></h2>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>
<form method="post" enctype="multipart/form-data">
  <table width="70%" class="details">
    <%@ include file="/WEB-INF/jsp/includes/searchCore.jsp" %>
	 <tr> 
	 <td>
		Select assays from the list to search. Use the "Ctrl" key to do multiple selection <br>
	    <select name="assayIds" size="10" multiple>
	     <c:forEach items="${assayList}" var="assay">
		 <option value="<c:out value="${assay.assayId}"/>"><c:out value="${assay.name}"/></option>
	     </c:forEach>
	   </select>
	    
	 </td>
    </tr>

	<tr> 
	 <td>
		Upload a sampleId list file:
           <input type="file" name="file"/>
      </td>
    </tr>


	  <tr> 
          <td>
			Output format:<br>
			 <input type="radio" name="format" value="hyperLink"> HTML format <br>
			 <input type="radio" name="format" value="plainText"> Excel format <br>
          </td>
      </tr>

	  <tr> 
          <td>
			 <input type="checkbox" name="linkageFormat" value="yes"> Linkage format <br>
          </td>
      </tr>


    
  

    </table>
</form>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>