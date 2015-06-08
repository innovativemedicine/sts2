<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Edit Sample Types</h2>

<form class="form-horizontal" method="post" action="sampleType.htm">

	<div class="control-group">
		<label class="control-label">Sample Type Name</label>
		<div class="controls">

			<spring:bind path="command.name">
				<input required type="text" maxlength="32" size="20" name="name"
					value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Suffix</label>
		<div class="controls">
			<spring:bind path="command.suffix">
				<input required type="text" maxlength="3" size="20" name="suffix"
					value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">Initial Label #</label>
		<div class="controls">

			<spring:bind path="command.initialLabelNo">
				<input required type="text" maxlength="3" size="20"
					name="initialLabelNo" value="<c:out value="${status.value}"/>">
			</spring:bind>

		</div>
	</div>

	<div class="control-group">
		<div class="controls">
			<input class="btn" type="submit" name="Submit" value="Edit">
			<input class="btn" type="submit" name="Submit" value="New">
		</div>
	</div>

</form>
<p>
<table class="table">
	<tr class="info">
		<td><b>Sample Type</b></td>
		<td><b>Suffix</b></td>
		<td><b>Initial Label No</b></td>
	</tr>

	<c:forEach items="${LSampleTypes}" var="container">
		<tr>
			<td><a class="act act-primary"
				href="<c:url value="/sampleType.htm">
			<c:param name="sampleTypeId" value="${container.sampleTypeId}"/></c:url>">
					<c:out value="${container.name}" />
			</a></td>
			<td><c:out value="${container.suffix}" /></td>
			<td><c:out value="${container.initialLabelNo}" /></td>


		</tr>
	</c:forEach>

</table>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>