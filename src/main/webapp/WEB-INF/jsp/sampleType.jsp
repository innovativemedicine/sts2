<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<tr>
	<td>
		<h2>Edit Sample Types</h2>

		<form method="post" action="sampleType.htm">

			<table class="details">

				<tr>
					<td>Sample Type Name</td>
					<td><spring:bind path="command.name">
							<INPUT type="text" maxlength="32" size="20" name="name"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind></td>
				</tr>

				<tr>
					<td>Suffix</td>
					<td><spring:bind path="command.suffix">
							<INPUT type="text" maxlength="3" size="20" name="suffix"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind></td>
				</tr>

				<tr>
					<td>Accept Source</td>
					<td><spring:bind path="command.isSource">
							<INPUT type="checkbox" maxlength="1" size="3" name="isSource"
								<c:if test="${status.value=='on'}">
				checked value="on"
			</c:if> />

							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" name="Submit"
						value="Edit"> <input type="submit" name="Submit"
						value="New"></td>
				</tr>
			</table>

		</form>
		<p>
		<table class="details">
			<tr>
				<th>Sample Type</th>
				<th>Suffix</th>
				<th>Is Source</th>
			</tr>

			<c:forEach items="${LSampleTypes}" var="container">
				<tr>
					<td><a
						href="<c:url value="/sampleType.htm">
			<c:param name="sampleTypeId" value="${container.sampleTypeId}"/></c:url>">
							<c:out value="${container.name}" />
					</a></td>
					<td><c:out value="${container.suffix}" /></td>
					<td><c:out value="${container.isSource}" /></td>

				</tr>
			</c:forEach>

		</table>

	</td>
</tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>