<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>      
<html>
</body>
 <c:forEach items="${orderedSamples}" var="oneRow">
  <c:forEach items="${oneRow}" var="sample"><c:out value="${sample.patient.intSampleId}"/>
  
  <c:if test="${sample==null}">
  BLANK
  </c:if>
  
  <br>
  </c:forEach>

  </c:forEach>
</body>
</html>
  

    