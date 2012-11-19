<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
  <table>
    <tr VALIGN="top">
	<td width="15%">
		<h3>Samples</h3>
		<c:forEach items="${sampleList}" var="sample">
		<c:out value="${sample.patient.intSampleId}"/><c:out value="${sample.sampleType.suffix}"/>&nbsp;
    	<c:out value="${sample.sampleDupNo}"/> <br>
		</c:forEach>

		<h3>Containers</h3>
		<c:forEach items="${containerList}" var="aContainer">
		<a href="<c:url value="/addSample2Container.htm"><c:param name="containerId" value="${aContainer.containerId}"/></c:url>"><c:out value="${aContainer.name}"/></a><br>
		</c:forEach>
	</td>

	<td width="85%">
		<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

		<c:if test="${command.noneContainer}">
		  Please select the container from the list.
		</c:if>

		<c:if test="${!command.noneContainer}">
			<c:if test="${command.emptyContainer}">
			  <h3><c:out value="${command.containerName}"/><br></h3>
			  <p><a href="<c:url value="/addSample2Container.htm"><c:param name="containerId" value="${command.containerId}"/><c:param name="isOrdered" value="ordered"/></c:url>" >Edit Sample Map</a> &nbsp; &nbsp; &nbsp;&nbsp;<a href="<c:url value="/addSample2Container.htm"><c:param name="containerId" value="${command.containerId}"/><c:param name="isOrdered" value="unOrdered"/></c:url>" >Edit Sample List</a>
			</c:if>

			<c:if test="${!command.emptyContainer}">
			 <%@ include file="/WEB-INF/jsp/includes/containerContentsBody.jsp" %>
			</c:if>
		  
		</c:if>
	</td>
	</tr>
  </table>


</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>