<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2 class="noMargin">Test Details</h2>
<a class="btn"
	href="<c:url value="/editTest.htm"><c:param name="testId" value="${command.testId}"/></c:url>">
	<span>Edit it</span>
</a>
<a class="btn"
	href="<c:url value="/deleteTest.htm"><c:param name="testId" value="${command.testId}"/></c:url>"
	onclick="return (confirm('Are you sure you want to delete this test?)) ">
	<span>Delete it</span>
</a>
<p>
<table class="table">
	<tr>
		<td>Test Name</td>
		<td><c:out value="${command.name}" /></td>
	</tr>


	<tr>
		<td>Instrument:</td>
		<td><c:out value="${command.instrument.name}" /></td>
	</tr>


	<tr>
		<td>Protocal</td>
		<td><c:forEach items="${protocals}" var="protocalName">
				<a href="<c:url value="protocals/${protocalName}"></c:url>"><c:out
						value="${protocalName}" /></a> ,
		    </c:forEach></td>
	</tr>

	<tr>
		<td>Assay(s):</td>
		<td><c:out value="${assayNameList}" /></td>
	</tr>

	<tr>
		<td>Note:</td>
		<td><c:out value="${command.note}" /></td>
	</tr>

	<tr>
		<td>Project:</td>
		<td><c:out value="${command.project.name}" /></td>
</table>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>