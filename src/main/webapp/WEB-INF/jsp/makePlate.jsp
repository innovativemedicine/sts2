<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp"%>

<h2>Register Plate</h2>

<form method="post" enctype="multipart/form-data">
	<div class="left">
		<table>
			<tr>
				<th colspan="2">Step 1: Fill In Plate Info</th>
			</tr>
			<tr>
				<td>Plate ID *</td>
				<td><spring:bind path="command.name">
						<INPUT disabled class="one" type="text" maxlength="60" size="20"
							name='<c:out value="${status.expression}" />'
							value="<c:out value="${status.value}"/>">
								<c:if test="${status.errorMessage != ''}">
									<FONT color="red">*</FONT>
								</c:if>
					</spring:bind></td>
			</tr>
			<tr>
				<td>External Plate ID *</td>
				<td><spring:bind path="command.extContainerId">
						<INPUT class="one" type="text" maxlength="60" size="20"
							name='<c:out value="${status.expression}" />'
							value="<c:out value="${status.value}"/>">
								<c:if test="${status.errorMessage != ''}">
									<FONT color="red">*</FONT>
								</c:if>
					</spring:bind></td>
			</tr>
			<tr>
				<td>Plate Type: *</td>
				<td><spring:bind path="command.containerType">
						<select class="one" size="1" name='<c:out value="${status.expression}" />'>
							<option value="">Select Plate Type</option>
							<c:forEach items="${allPlateTypes}" var="plateType">
								<option 
								<c:if test="${command.containerType != null && command.containerType.containerTypeId eq plateType.containerTypeId}">
				   					selected
			 					</c:if>
			 					value="<c:out value="${plateType.containerTypeId}"/>">
									<c:out value="${plateType.name}" />
								</option>
							</c:forEach>
						</select>
					</spring:bind></td>
			</tr>
			<tr>
				<td>Project *</td>
				<td><spring:bind path="command.project">
						<select class="one"class="one" size="1" name='<c:out value="${status.expression}" />'>
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
				<td>Created Date *</td>
				<td><spring:bind
						path="command.createdDate">
						<INPUT class="one" type="text" maxlength="10" size="20" name="createdDate"
							value="<c:out value="${status.value}"/>"><span class="label">DD-MM-YYYY</span>
								<c:if test="${status.errorMessage != ''}">
									<FONT color="red">*</FONT>
								</c:if>
					</spring:bind></td>
			</tr>
			<tr>
				<td>Comments</td>
				<td><spring:bind path="command.comments">
						<INPUT class="one" type="text" maxlength="255" size="20" name="comments"
							value="<c:out value="${status.value}"/>">
					</spring:bind></td>
			</tr>
			<tr><td colspan = "2">
			<span style="font-size: 10pt">* Required Fields</span>
			</td>
			</tr>
			<tr>
				<td colspan = "2"><a class="button unhider" ><span>Next</span></a></td>
			</tr>
		</table>

	</div>
	<div>
		<table class="hide">
			<tr>
				<th colspan="2">Step 2: Download and Complete Manifest File</th>
			</tr>
			<tr>
				<td>Manifest file</td>
				<td><div id="dlManifest">				
				<input class="unhider2" type="submit" name="action" value="Download">
				
</div></td>
			</tr>
		</table>
		<p>
		<table class="hide2">
			<tr>
				<th colspan="2">Step 3: Upload Manifest File</th>
			</tr>
			<tr>
				<td>Upload Manifest file</td>
				<td><input type="file" name="file" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="action" value="Save">
				</td>
			</tr>
		</table>
	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>