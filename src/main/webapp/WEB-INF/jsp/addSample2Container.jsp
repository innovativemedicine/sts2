<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Add Samples To Container</h2>

<form method="post" enctype="multipart/form-data">
	<div class="row-fluid">
		<c:choose>
			<c:when test="${not empty sampleList}">
				<div class="span2">

					<h3>Samples:</h3>
					<select class="span12" size="25" name="samplesToAdd" multiple>
						<c:forEach items="${sampleList}" var="sample">
							<option value="<c:out value="${sample.sampleId}"/>">
								<c:out value="${sample.patient.intSampleId}" />
								-
								<c:out value="${sample.sampleType.suffix}" />
							</option>
						</c:forEach>
					</select>
				</div>

				<div class="span10">
			</c:when>
			<c:otherwise>
				<div class="span12">
			</c:otherwise>
		</c:choose>

		<h3>Container:</h3>

		<div class="form-inline">
			<select class="selectNav" name="container">
				<option value="">--- Select Container ---</option>
				<c:forEach items="${containerList}" var="aContainer">
					<option
						<c:if test="${command.containerId != null && command.containerId eq aContainer.containerId}">
						selected
					</c:if>
						value="?containerId=<c:out value="${aContainer.containerId}"/>&isOrdered=ordered">
						<c:out value="${aContainer.name}" />
					</option>

				</c:forEach>
			</select> <input class="btn" type="submit" name="Submit" value="Save">
		</div>
		<c:if test="${!command.noneContainer}">

			<c:if test="${command.emptyContainer}">
				<p>
					<a class="btn"
						href="<c:url value="/addSample2Container.htm"><c:param name="containerId" value="${command.containerId}"/><c:param name="isOrdered" value="ordered"/></c:url>">
						<span>Edit Content</span>
					</a>
			</c:if>

			<c:if test="${!command.emptyContainer}">
				<%@ include file="/WEB-INF/jsp/includes/containerContentsBody.jsp"%>
			</c:if>

		</c:if>

	</div>
	</div>
</form>


<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>