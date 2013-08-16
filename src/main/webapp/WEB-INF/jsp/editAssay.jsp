<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Edit Assay</h2>

<form class="form-horizontal form-inline" method="post">


	<div class="control-group">
		<label class="control-label"> Assay Name:</label>
		<div class="controls">

			<spring:bind path="command.name">
				<input required type="text" maxlength="255" size="30" name="name"
					value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label"> Location in Gene:</label>
		<div class="controls">

			<spring:bind path="command.location">
				<input type="text" maxlength="255" size="30" name="location"
					value="<c:out value="${status.value}"/>">
			</spring:bind>
		</div>
	</div>


	<div class="control-group">
		<label class="control-label"> Note:</label>
		<div class="controls">
			<spring:bind path="command.note">
				<input type="text" maxlength="255" size="30" name="note"
					value="<c:out value="${status.value}"/>">
			</spring:bind>


		</div>
	</div>

	<p>
	<div class="control-group">

		<div class="controls">

			<input class="btn" type="submit" name="Submit" value="Save">
			<input class="btn" type="reset" name="Submit2" value="Reset">
		</div>

	</div>

</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>