<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
		
<tr><td>

<h2>Show Multiple Sample</h2>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>
 
 <h3>Successfully clicked added without error</h3>
<form method="post">

<table class="details">

	<tr>
		<th>Barcode ID</th>
		<th>External ID</th>
		<th>External ID2</th>
		<th>Sample Type</th>
		<th>Project</th>
		<th>Notes</th>
	<tr>
	
<%-- 	<c:forEach items="${contactForm.contacts}" var="contact" varStatus="status"> --%>
<!-- 		<tr> -->
<%-- 			<td>${contact.firstname}</td> --%>
<%-- 			<td>${contact.lastname}</td> --%>
<%-- 			<td>${contact.email}</td> --%>
<%-- 			<td>${contact.phone}</td> --%>
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
		  
    </table>
</form>

</td></tr>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>