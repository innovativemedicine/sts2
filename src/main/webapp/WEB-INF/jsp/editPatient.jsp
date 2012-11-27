<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>


<tr>
	<td>

		<h2>
			Edit Patient for this sample
			<c:out value="${command.intSampleId}" />
		</h2>

		<form method="post">
			<table class="details">
				<tr>
					<td>External ID 1:</td>
					<td><spring:bind path="command.extSampleId">
							<INPUT type="text" maxlength="255" size="30" name="extSampleId"
								value="<c:out value="${status.value}"/>">
						</spring:bind></td>
				</tr>

				<tr>
					<td>External ID 2:</td>
					<td><spring:bind path="command.anotherExtSampleId">
							<INPUT type="text" maxlength="255" size="30"
								name="anotherExtSampleId"
								value="<c:out value="${status.value}"/>">
						</spring:bind></td>
				</tr>

				<tr>
					<td>Sample Source:</td>
					<td><spring:bind path="command.project">
							<select size="1" name="<c:out value="${status.expression}" />" >

								<c:forEach items="${allProjects}" var="project">
									<option
										<c:if test="${command.project != null && command.project.projectId eq project.projectId}">
			   selected
		 </c:if>
										value="<c:out value="${project.projectId}"/>">
										<c:out value="${project.investigator.fullName}" />
										&nbsp;
										<c:out value="${project.name}" />
									</option>
								</c:forEach>
							</select>
						</spring:bind></td>
				</tr>

				<tr>
					<td>Patient Note:</td>
					<td><spring:bind path="command.note">
							<INPUT type="text" maxlength="255" size="30" name="note"
								value="<c:out value="${status.value}"/>">
						</spring:bind></td>
				</tr>

				<tr>
					<td>Is control?</td>
					<td><spring:bind path="command.isControl">
							<select name="isControl" size="1">
								<option value="N">No</option>
								<option value="Y">Yes</option>


							</select>
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
	</td>
</tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>