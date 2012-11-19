<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

Select the field you want to export
<form method="post" >
  <table width="70%" class="details">

<tr><td>
<spring:bind path="command.outExtSampleId"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
External SampleId
</td></tr>



<tr><td>
<spring:bind path="command.outAnotherExtSampleId"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Another External SampleId
</td></tr>

<tr><td>
<spring:bind path="command.outOd"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
DNA Concentration
</td></tr>

<tr><td>
<spring:bind path="command.outOdDate"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
DNA Concentration reading date
</td></tr>

<tr><td>
<spring:bind path="command.outVolumn"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
DNA Volumn
</td></tr>

<tr><td>
<spring:bind path="command.outVolumnDate"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
DNA Volumn reading date
</td></tr>


<tr><td>
<spring:bind path="command.outReceiveDate"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Receive date
</td></tr>


<tr><td>
<spring:bind path="command.outMadeDate"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
DNA made date
</td></tr>

<tr><td>
<spring:bind path="command.outTransDate"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Transfer date
</td></tr>


<tr><td>
<spring:bind path="command.outRefillDate"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Refill date
</td></tr>

<tr><td>
<spring:bind path="command.outSampleType"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Sample Type Suffix
</td></tr>

<tr><td>
<spring:bind path="command.outSampleDupNo"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Sample Dup No
</td></tr>

<tr><td>
<spring:bind path="command.outNotes"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Sample Note
</td></tr>

<tr><td>
<spring:bind path="command.outStatus"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Sample Status
</td></tr>


<tr><td>
<spring:bind path="command.outSampleLoci"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Sample location
</td></tr>

<tr><td>
<spring:bind path="command.outFname"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Patient First Name
</td></tr>

<tr><td>
<spring:bind path="command.outLname"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Patient Last Name
</td></tr>

<tr><td>
<spring:bind path="command.outMname"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Patient Middle Name
</td></tr>

<tr><td>
<spring:bind path="command.outAge"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Patient Age
</td></tr>

<tr><td>
<spring:bind path="command.outBirthDate"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Patient Birthdate
</td></tr>

<tr><td>
<spring:bind path="command.outGender"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Patient Gender
</td></tr>

<tr><td>
<spring:bind path="command.outPatientNote"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Patient Note
</td></tr>

<tr><td>
<spring:bind path="command.outIsControl"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Is control Sample?
</td></tr>

<tr><td>
<spring:bind path="command.outFamilyId"> 
<%@ include file="/WEB-INF/jsp/includes/checkBoxCore.jsp" %>
</spring:bind> 
Patient family ID
</td></tr>


 <tr> 
      <td>
          <input type="submit" name="Submit" value="Export">
          <input type="reset" name="Submit2" value="Reset">
        </p>
        </td>
    </tr>

</form> 
</table>


</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>