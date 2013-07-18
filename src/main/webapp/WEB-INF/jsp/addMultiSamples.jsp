<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Register Samples</h2>
<div id="myCarousel" class="carousel slide span8" data-interval="false">
	<!-- Carousel items -->
	<div class="carousel-inner">
		<div class="active item">
			<div class="carousel-html">

				<div class="alert alert-info">
					<h3>Choose an Option</h3>
				</div>
				<div class="row-fluid">
					<div class="span5 offset1">
						<a class="btn" href="#myCarousel" data-slide-to="1">
							<h4>Sample Manifest</h4> <img src="img/excel.png" height="200"
							width="150"> <br> Register using Excel Manifest.<br>
							Suitable for batch of samples.

						</a>
					</div>
					<div class="span5 offset1">
						<a class="btn" href="#myCarousel" data-slide-to="2">
							<h4>Sample Form</h4> <img src="img/sharepoint.png" height="150"
							width="150"> <br> Register using Web Form.<br>
							Suitable for individual sample.

						</a>
					</div>
				</div>
			</div>
		</div>

		<div class="item">
			<div class="carousel-html">


				<div class="alert alert-info">
					<h3>
						<a class="btn btn-large pull-right" href="#myCarousel"
							data-slide-to="0"><i class="iconic-home"></i></a> Sample Manifest
					</h3>
				</div>

				<form method="post" enctype="multipart/form-data">

					<h4>Sample ID Prefix:</h4>
					<input required type="text" class="span1" id="sampleIdPre"
						name="sampleIdPre" value="<c:out value="${param.sampleIdPre}"/>">

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
		<div class="item">
			<div class="carousel-html">
				<div class="alert alert-info">
					<h3>
						<a class="btn btn-large pull-right" href="#myCarousel"
							data-slide-to="0"><i class="iconic-home"></i></a> Sample Form
					</h3>
				</div>

				<form method="post" class="form-horizontal">
					<div class="row">
						<div class="span2">
							<div class="control-group">
								<label class="control-label">Sample ID Prefix</label>
								<div class="controls">

									<input required type="text" class="input-small"
										id="sampleIdPreForm" name="sampleIdPreForm"
										value="<c:out value="${param.sp}"/>">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">External ID</label>
								<div class="controls">
									<input required class="input-small" type="text"
										name="externalId" value="">
								</div>
							</div>


							<div class="control-group">
								<label class="control-label">Birth Date</label>
								<div class="controls">
									<input class="input-small" type="text" maxlength="10"
										pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
										placeholder="DD-MM-YYYY" name="birthDate" value="">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Received Date</label>
								<div class="controls">

									<input class="input-small" type="text" maxlength="10"
										pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
										placeholder="DD-MM-YYYY" name="receivedDate" value="">
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
						<div class="span3 offset1">
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
								<label class="control-label">Sample Type</label>
								<div class="controls">

									<select required class="input" multiple size="13"
										name="sampleType">
										<c:forEach items="${allSampleTypes}" var="sampleTypeOpt">
											<option
												value="<c:out value="${sampleTypeOpt.sampleTypeId}"/>">

												<c:out value="${sampleTypeOpt.name}" />
												<c:if test="${sampleTypeOpt.vials != null}">(<c:out
														value="${sampleTypeOpt.vials}" />)</c:if>
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</form>

			</div>

		</div>
	</div>
	<!-- </form> -->

	<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>