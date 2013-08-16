<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<jsp:useBean id="now" class="java.util.Date" />

<h2>Register Samples</h2>
<div id="myCarousel" class="carousel slide span12" data-interval="false">
	<!-- Carousel items -->
	<div class="carousel-inner">
		<div class="item">
			<div class="carousel-html">
				<div class="alert alert-info">
					<h3>
						<a class="btn btn-large pull-right" href="#myCarousel"
							data-slide-to="1"><i class="iconic-beaker"></i></a> Multiple
						Samples
					</h3>
				</div>

				<form method="post" enctype="multipart/form-data">

					<h4>Sample ID Prefix:</h4>
					<input required type="text" class="span1" id="sampleIdPre"
						name="sampleIdPre" value="<c:out value="${param.sampleIdPre}"/>"
						pattern="[A-Za-z]{1,4}$">

					<h4>Upload Completed Manifest:</h4>
					<div class="fileupload fileupload-new " data-provides="fileupload">

						<span class="input-prepend input-append form-inline"> <span
							class="btn btn-file"> <span class="fileupload-new">Select
									Manifest</span> <span class="fileupload-exists">Change Manifest</span>
								<input type="file" name="file" />
						</span> <span class="uneditable-input fileupload-preview"></span> <input
							class="btn fileupload-exists" type="submit" name="action"
							value="Upload">
						</span>
					</div>

					<div class="alert">
						Download Manifest Template&nbsp;<a class="btn btn-mini linkButton"><i
							class="iconic-download"></i></a> <input class="hide linkedButton"
							type="submit" name="action" value="Download Manifest">
					</div>

				</form>
			</div>
		</div>
		<div class="active item">
			<div class="carousel-html">
				<div class="alert alert-info">
					<h3>
						<a class="btn btn-large pull-right" href="#myCarousel"
							data-slide-to="0"><i class="iconic-layers"></i></a> Single Sample
					</h3>
				</div>

				<form method="post" class="form-horizontal">
					<div class="row">
						<div class="span5">
							<div class="control-group">
								<label class="control-label">Sample ID Prefix</label>
								<div class="controls">

									<input autocomplete="off" required type="text"
										class="input-small" id="sampleIdPreForm"
										name="sampleIdPreForm" value="<c:out value="${param.sp}"/>"
										pattern="[A-Za-z]{1,4}$">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">External ID</label>
								<div class="controls">
									<input autocomplete="off" required class="input-small"
										type="text" name="externalId" value="">
								</div>
							</div>


							<div class="control-group">
								<label class="control-label">Birth Date</label>
								<div class="controls">
									<input autocomplete="off" class="input-small datepicker"
										type="text" placeholder="DD-MM-YYYY" name="birthDate"
										data-date-format="dd-mm-yyyy">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Received Date</label>
								<div class="controls">

									<input autocomplete="off" required
										class="input-small datepicker" type="text"
										value="<fmt:formatDate value="${now}" pattern="dd-MM-yyyy" />"
										placeholder="DD-MM-YYYY" name="receivedDate"
										data-date-format="dd-mm-yyyy">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Project</label>
								<div class="controls">

									<select required class="input" name="project">
										<option value="">Select Project</option>
										<c:forEach items="${allProjects}" var="project">
											<option value="<c:out value="${project.projectId}"/>">
												<c:out value="${project.name}" />
											</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Notes</label>
								<div class="controls">
									<textarea class="input" name="notes"></textarea>
								</div>
							</div>

							<div class="control-group">
								<div class="controls">
									<input class="btn" type="submit" name="action" value="Save">
								</div>

							</div>
						</div>

						<div class="span6">
							<h5>Sample Type</h5>
							<fieldset class="column2">

								<c:forEach items="${allSampleTypes}" var="sampleTypeOpt">
									<div class="form-inline">
										<label class="checkbox"> <input type="checkbox"
											name="st<c:out value="${sampleTypeOpt.sampleTypeId}"/>">
											<input class="input-micro clickSelect" maxlength="2" type="text"
											name="st<c:out value="${sampleTypeOpt.sampleTypeId}num"/>"
											value="<c:out value="${sampleTypeOpt.initialLabelNo}"/>"
											pattern="[0-9]{1,2}">

											<c:out value="${sampleTypeOpt.name}" />
										</label>
									</div>
								</c:forEach>

							</fieldset>


							<!-- 							<div class="control-group"> -->
							<!-- 								<label class="control-label">Sample Type</label> -->
							<!-- 								<div class="controls"> -->

							<!-- 									<select required class="input" multiple size="13" -->
							<!-- 										name="sampleType"> -->
							<%-- 										<c:forEach items="${allSampleTypes}" var="sampleTypeOpt"> --%>
							<!-- 											<option -->
							<%-- 												value="<c:out value="${sampleTypeOpt.sampleTypeId}"/>"> --%>

							<%-- 												<c:out value="${sampleTypeOpt.name}" /> --%>
							<%-- 												<c:if test="${sampleTypeOpt.vials != null}">(<c:out --%>
							<%-- 													value="${sampleTypeOpt.vials}" />)</c:if> --%>
							<!-- 											</option> -->
							<%-- 										</c:forEach> --%>
							<!-- 									</select> -->
							<!-- 								</div> -->
							<!-- 							</div> -->
						</div>
					</div>
				</form>

			</div>

		</div>
	</div>
</div>
<!-- </form> -->

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>