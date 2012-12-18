<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Edit Containers</h2>
<c:if test="${command.motherContainer != null}">
	Mother Container: <c:out value="${command.motherContainer.name}" />
	<br>
</c:if>
<form method="post">
	<table class="details">
		<tr>
			<td>Container Name<FONT color=RED>*</FONT></td>
			<td><spring:bind path="command.name">
					<INPUT type="text" maxlength="255" size="20" name="name"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind></td>
		</tr>
		<tr>
			<td>External Container Name</td>
			<td><spring:bind path="command.extContainerId">
					<INPUT type="text" maxlength="255" size="20" name="extContainerId"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>
		<tr>
			<td>Created Date</td>
			<td><spring:bind path="command.createdDate">
					<INPUT type="text" placeholder="DD-MM-YYYY" maxlength="255"
						size="20" name="createdDate"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind></td>
		</tr>

		<tr>
			<td>Concentration</td>
			<td><spring:bind path="command.concentration">
					<INPUT type="text" maxlength="255" size="20" name="concentration"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind><span class="label">ug/mL</span></td>
		</tr>

		<tr>
			<td>Comments</td>
			<td><spring:bind path="command.comments">
					<INPUT type="text" maxlength="255" size="20" name="comments"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>
		<tr>
			<td>Container Type<FONT color=RED>*</FONT>
			</td>
			<td><spring:bind path="command.containerType">
					<select size="1" name='<c:out value="${status.expression}" />'>
						<option value="">Select Container Type</option>
						<c:forEach items="${allContainerTypes}" var="containerType">
							<option
								<c:if test="${command.containerType != null && command.containerType.containerTypeId eq containerType.containerTypeId}">
			   selected
		  </c:if>
								value="<c:out value="${containerType.containerTypeId}"/>">
								<c:out value="${containerType.name}" />
							</option>
						</c:forEach>
					</select>
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>

				</spring:bind></td>
		</tr>

		<tr>
			<td>Location<FONT color=RED>*</FONT>
			</td>
			<td><spring:bind path="command.location">
					<select size="1" name='<c:out value="${status.expression}" />'>
						<option value="">Select Location</option>

						<c:forEach items="${allLocations}" var="location">
							<option
								<c:if test="${command.location != null && command.location.locationId eq location.locationId}">
			   selected
			 </c:if>
								value="<c:out value="${location.locationId}"/>">
								<c:out value="${location.name}" />
							</option>
						</c:forEach>
					</select>
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>

				</spring:bind></td>
		</tr>

		<tr>
			<td>Project</td>
			<td><spring:bind path="command.project">
					<select size="1" name="<c:out value="${status.expression}" />">
						<option value="">Select Project</option>
						<c:forEach items="${allProjects}" var="project">
							<option
								<c:if test="${command.project != null && command.project.projectId eq project.projectId}">
			   selected
		 </c:if>
								value="<c:out value="${project.projectId}"/>">
								<c:out value="${project.name}" />
							</option>
						</c:forEach>
					</select>
				</spring:bind></td>
		</tr>

		<tr>
			<td colspan="2"><input class="button buttonPad" type="submit"
				name="Submit" value="Save"> <input class="button buttonPad"
				type="reset" name="Submit2" value="Reset"></td>
		</tr>
	</table>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>