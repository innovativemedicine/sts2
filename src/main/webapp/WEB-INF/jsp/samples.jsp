<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<tr><td>
Your search criteria:
<c:out value="${currentCriteria}" escapeXml="false" />
<br>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<h2><fmt:message key="sample.search.title"/></h2>
<form method="post" enctype="multipart/form-data">
  <table width="70%" class="details">
    <%@ include file="/WEB-INF/jsp/includes/searchCore.jsp" %>
   
	<tr> 
	 <td>
		 &nbsp;
      </td>
    </tr> 

   <tr> 
	 <td>
		Upload or enter a list of internal sample Id, will give 
		you all samples with these sample ids. If you specify 
		the sample type at the same time, will give you all 
		samples with these sample ids of that sample type.
      </td>
    </tr>

	 <tr> 
	 <td>
		 &nbsp;
      </td>
    </tr> 
	 <tr> 

	 <td> Enter or paste your Internal sample Id here, delimited
	  by comma, or one sample one line
	<TEXTAREA name="sampleIdsInTextArea" rows="20" cols="80">
  
   </TEXTAREA>
    </td>
    </tr>
    
	 <tr> 
	 <td>
		&nbsp;		 
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
		 &nbsp;
      </td>
    </tr> 
  


	<tr> 
		<td>
			<input type="checkbox" name="useFor" value="useFor"> 
			For printing labels
        </td>
    </tr>
    
   </table>
   
</form> 

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
