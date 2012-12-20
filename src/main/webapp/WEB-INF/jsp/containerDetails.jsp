<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<tr>
	<td><%@ include file="/WEB-INF/jsp/includes/success.jsp"%>


		<h2 class="noMargin">Container Details</h2> <c:if
			test="${command.sampleBox}">
			<a class="button"
				href="<c:url value="/editContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>">
				<span>Edit Container</span>

			</a>
			<a class="button"
				href="<c:url value="/emptyContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>"
				onclick="return (confirm('Warning: This will remove all samples from this container, but the samples information will remain in the system.\n\nAre you sure you want to empty this container?')) ">
				<span>Empty Container</span>
			</a>
		</c:if> <a class="button"
		href="<c:url value="/deleteContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>"
		onclick="return (confirm('Warning: This will remove all information associated with samples from this container.\n\nAre you sure you want to delete this container?')) ">
			<span>Delete Container</span>
	</a>
		<p>
		<div class="left">
			<table>
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
					<td><c:if test="${command.sampleBox}">
							<c:out value="${command.location.name}" />
						</c:if> <c:if test="${command.plate && command.hasMotherContainer}">
							<a
								href="<c:url value="/containerDetails.htm"><c:param name="containerId" value="${command.motherContainer.containerId}"/></c:url>">
								<c:out value="${command.motherContainer.name}" />@<c:out
									value="${command.motherContainer.location.name}" />
							</a>
						</c:if></td>
				</tr>
				<tr>
					<th>Project</th>
					<td><c:out value="${command.project.name}" /></td>
				</tr>
			</table>
		</div>
		<div>
			<table>
				<tr>
					<th>Created Date</th>
					<td><fmt:formatDate pattern="dd-MM-yyyy"
							value="${command.createdDate}" /></td>
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
		</div> <br>
		<div class="clear">
			<h3 class="noMargin">Sample List:</h3>

			<c:if test="${command.sampleBox}">
				<a class="button"
					href="<c:url value="/deleteAllSamplesInContainer.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>"
					onclick="return (confirm('Warning: Will delete all samples in this container from STS, including the duplicates stored in other containers as well as all associated results.\n\n Are you sure you want to delete all samples in this container?')) ">
					<span>Delete All</span>
				</a>
			</c:if>

			<c:if test="${command.orderedSample}">
				<p>
				<table class="containerMap">
					<c:forEach items="${orderedSamples}" var="oneRow">
						<tr>
							<c:forEach items="${oneRow}" var="sample">
								<td><a class="details"
									href="<c:url value="/sampleDetails.htm"><c:param name="sampleId" value="${sample.sampleId}"/></c:url>">
										<c:out value="${sample.patient.intSampleId}" /> <c:out
											value="${sample.sampleType.suffix}" />
								</a></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${command.unorderedSample}">
				<a class="button"
					href="<c:url value="/containerContents.htm"><c:param name="containerId" value="${command.containerId}"/></c:url>">
					<span>Edit Sample List</span>
				</a>
				<p>
				<table>
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

			<c:if test="${command.hasChildContainer}">
				<p>
				<h3>Container List:</h3>
				<table>
					<tr>
						<th>Child Container</th>
					</tr>
					<c:forEach items="${childContainers}" var="child">
						<tr>
							<td><c:out value="${child.name}" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div> <%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>