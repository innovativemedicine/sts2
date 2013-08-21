<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<tr>
	<td>
		<h2>Edit Instruments</h2>

		<form class="form-horizontal" method="post" action="instrument.htm">


			<div class="control-group">
				<label class="control-label">Instrument Name</label>
				<div class="controls">

					<spring:bind path="command.name">
						<INPUT required type="text" maxlength="64" size="20" name="name"
							value="<c:out value="${status.value}"/>">

					</spring:bind>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Note</label>
				<div class="controls">

					<spring:bind path="command.note">
						<INPUT type="text" maxlength="100" size="20" name="note"
							value="<c:out value="${status.value}"/>">
					</spring:bind>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<input class="btn" type="submit" name="Submit" value="Edit">
					<input class="btn" type="submit" name="Submit" value="New">
				</div>
			</div>




			<p>
		</form>
		<table class="table" class="details">
			<tr class="info">
				<td><b>Instrument Name</b></td>
				<td><b>Note</b></td>
			</tr>

			<c:forEach items="${LInstruments}" var="container">
				<tr>
					<td><a class="act act-primary"
						href="<c:url value="/instrument.htm">
			<c:param name="instrumentId" value="${container.instrumentId}"/></c:url>">
							<c:out value="${container.name}" />
					</a></td>
					<td><c:out value="${container.note}" /></td>

				</tr>
			</c:forEach>

		</table>

	</td>
</tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>