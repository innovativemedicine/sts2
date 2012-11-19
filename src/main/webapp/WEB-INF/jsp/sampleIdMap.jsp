 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>      
<html>
</body>
		<table border="1" >
         <c:forEach items="${orderedSamples}" var="oneRow">
          <tr> 
          <c:forEach items="${oneRow}" var="sample">
          <td width=60><c:out value="${sample.patient.intSampleId}"/>
		  <c:if test="${sample==null}">
			BLANK
		  </c:if>
          </c:forEach>
          </td>
          </c:forEach>
          
          </tr>
        </table>
</body>
</html>