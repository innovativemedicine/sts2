<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<tr>
	<td><%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

		<font size="5"> Container Details</font> <a class="button"
		href="<c:url value="/editContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>">
			<span>Edit</span>
	</a> <a class="button"
		href="<c:url value="/emptyContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>"
		onclick="return (confirm('Warning: This will remove all samples from this container, but the samples information will remain in the system.\n\nAre you sure you want to empty this container?')) ">
			<span>Empty</span>
	</a> <a class="button"
		href="<c:url value="/deleteContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>"
		onclick="return (confirm('Warning: You can not delete this container unless it is empty. Are you sure you want to delete this container?')) ">
			<span>Delete</span>
	</a>
		<p>
		<div style="float: left; margin-right: 40px">
			<table class="details">
				<tr>
					<th>Container Name</th>
					<td><c:out value="${command.name}" /></td>
				</tr>
				<tr>
					<th>External Name</th>
					<td><c:out value="${command.extContainerId}" /></td>
				</tr>
				<tr>
					<th>Container Type</th>
					<td><c:out value="${command.containerType.name}" /></td>
				</tr>
				<tr>
					<th>Location:</th>
					<td><c:out value="${command.location.name}" /></td>
				</tr>
				<tr>
					<th>Project</th>
					<td><c:out value="${command.project.name}" /></td>
				</tr>
			</table>
		</div>
		<div>
			<table class="details">
				<tr>
					<th>Created Date</th>
					<td><c:out value="${command.createdDate}" /></td>
				</tr>
				<tr>
					<th>Concentration</th>
					<td><c:out value="${command.concentration}" /></td>
				</tr>
				<tr>
					<th>Status</th>
					<td><c:out value="${command.status}" /></td>
				</tr>

				<tr>
					<th>Comments</th>
					<td><c:out value="${command.comments}" /></td>
				</tr>
				<tr>
					<th>Total Samples:</th>
					<td><c:out value="${command.totalSamples}" /></td>
				</tr>

			</table>
		</div>

		<div style="clear: both;">
			<p>
				<font size="4">Sample List:</font>

				<c:if test="${command.sampleBoxOrPlate}">
					<a class="button"
						href="<c:url value="/deleteAllSamplesInContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>"
						onclick="return (confirm('Warning: Will delete all samples in this container from STS, including the duplicates stored in other containers as well as all associated results.\n\n Are you sure you want to delete all samples in this container?')) ">
						<span>Delete All</span>
					</a>
				</c:if>

				<c:if test="${command.noneSample}">
					<a class="button"
						href="<c:url value="/containerContents.htm"><c:param name="containerId" value="${command.containerId}"/><c:param name="isOrdered" value="ordered"/></c:url>">
						<span>Edit Content</span>
					</a>
				</c:if>

				<c:if test="${command.orderedSample}">
					<a class="button"
						href="<c:url value="/containerContents.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>">
						<span>Edit Content</span>
					</a>
					<p>
					<table class="details">
						<c:forEach items="${orderedSamples}" var="oneRow">
							<tr>
								<c:forEach items="${oneRow}" var="sample">
									<td><a
										href="<c:url value="/sampleDetails.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>">
											<font size="2"><c:out
													value="${sample.patient.intSampleId}" /> <c:out
													value="${sample.sampleType.suffix}" /></font>
									</a></td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
		Get sampleId list
	   <a
						href="<c:url value="/sampleIdList.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>"
						target="_blank">in list format</a> or 
	   <a
						href="<c:url value="/sampleIdMap.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>"
						target="_blank"> in plate format</a>

				</c:if>
				<c:if test="${command.unorderedSample}">
					<p>
						<a
							href="<c:url value="/containerContents.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>">Edit
							Sample List</a> <br>
					<table class="details">
						<tr>
							<th>Internal ID</th>
							<th>External ID</th>
							<th>Sample Type</th>
							<th>Status</th>
						</tr>

						<c:forEach items="${unOrderedSamples}" var="sample">
							<tr>
								<td><a
									href="<c:url value="/sampleDetails.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>"><c:out
											value="${sample.patient.intSampleId}" /></a></td>
								<td><c:out value="${sample.patient.extSampleId}" /></td>
								<td><c:out value="${sample.sampleType.name}" /></td>
								<td><c:out value="${sample.status}" /></td>

							</tr>
						</c:forEach>

					</table>
				</c:if>
				<br>
				<c:if test="${command.sampleBoxOrPlate}">
					<c:if test="${command.hasMotherContainer}">
						<h3>Mother Container:</h3>
						<a
							href="<c:url value="/containerDetails.htm"><c:param name="containerId" value="${command.motherContainer.containerId}"/></c:url>"><c:out
								value="${command.motherContainer.name}" /></a>
					</c:if>
		</div> <br> <font size="4">Child Containers:</font> <a
		href="<c:url value="/editContainer.htm"><c:param name="motherContainerId" value="${command.containerId}"/></c:url>">
			Copy this Container to make a new plate</a>

		<table class="details">
			<tr>
				<td><c:forEach items="${command.childContainers}"
						var="container">
						<a
							href="<c:url value="/containerDetails.htm"><c:param name="containerId" value="${container.containerId}"/></c:url>"><c:out
								value="${container.name}" /></a>  ,  
                  </c:forEach></td>
			</tr>

		</table> </c:if></td>
</tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>