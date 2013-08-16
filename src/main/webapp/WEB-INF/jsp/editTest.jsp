<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Edit Tests</h2>

<form method="post" enctype="multipart/form-data">
	<table class="table" class="details">
		<tr>
			<td>Test Name</td>
			<td><spring:bind path="command.name">
					<input required type="text" maxlength="255" size="30" name="name"
						value="<c:out value="${status.value}"/>">

				</spring:bind></td>
		</tr>

		<tr>
			<td>Instrument:</td>
			<td><spring:bind path="command.instrument">
					<select name='<c:out value="${status.expression}" />' size="1">

						<c:forEach items="${allInstruments}" var="instrument">
							<option
								<c:if test="${command.instrument != null && command.instrument.instrumentId eq instrument.instrumentId}">
			   selected
		  </c:if>
								value="<c:out value="${instrument.instrumentId}"/>">
								<c:out value="${instrument.name}" />
							</option>
						</c:forEach>
					</select>
				</spring:bind></td>
		</tr>


		<tr>
			<td>Protocals</td>
			<td>1 <input type="file" name="file1" /> <br> 2 <input
				type="file" name="file2" /> <br> 3 <input type="file"
				name="file3" /> <br> 4 <input type="file" name="file4" />
			</td>
		</tr>

		<tr>
			<td>Assay(s):</td>
			<td><c:out value="${assayNameList}" /><br> <select
				name="assayIds" size="10" multiple>
					<c:forEach items="${allAssays}" var="assay">
						<option value="<c:out value="${assay.assayId}"/>">
							<c:out value="${assay.name}" />
						</option>
					</c:forEach>
			</select></td>
		</tr>

		<tr>
			<td>Note:</td>
			<td><spring:bind path="command.note">
					<input type="text" maxlength="255" size="30" name="note"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>


		<tr>
			<td colspan="2">
				<p>
					<input type="submit" name="Submit" value="Save"> <input
						type="reset" name="Submit2" value="Reset">
				</p>
			</td>
		</tr>
	</table>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>