<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Edit Samples</h2>


<form method="post">
	<div class="row-fluid">
		<div class="span4">
			<div class="form-horizontal form-inline">
				<div class="control-group">
					<label class="control-label">Internal ID:</label>
					<div class="controls">

						<c:if test="${!command.patient.newPatient}">
							<input class="span8" disabled type="text"
								value="<c:out value="${command.patient.intSampleId}" />" />
						</c:if>
						<c:if test="${command.patient.newPatient}">
							<spring:bind path="command.patient.intSampleId">
								<input class="span8" type="text" maxlength="255" size="20"
									name="patient.intSampleId"
									value="<c:out value="${status.value}"/>">
								<FONT color="red"> <B><c:out
											value="${status.errorMessage}" /></B>
								</FONT>
							</spring:bind>
						</c:if>
					</div>

				</div>

				<div class="control-group">
					<label class="control-label">Sample Type:</label>
					<div class="controls">
						<spring:bind path="command.sampleType">
							<select class="span8"
								name='<c:out value="${status.expression}" />'>
								<c:forEach items="${availableSampleTypes}" var="sampleType">
									<option
										<c:if test="${command.sampleType != null && command.sampleType.sampleTypeId eq sampleType.sampleTypeId}">
			   selected
		  </c:if>
										value="<c:out value="${sampleType.sampleTypeId}"/>">
										<c:out value="${sampleType.name}" />
									</option>
								</c:forEach>
							</select>
						</spring:bind>
					</div>
				</div>


				<div class="control-group">
					<label class="control-label">Dupe No.:</label>
					<div class="controls">
						<spring:bind path="command.sampleDupNo">
							<select class="span8"
								name='<c:out value="${status.expression}" />'>
								<c:forEach items="${numbers}" var="number">
									<option
										<c:if test="${command.sampleDupNo != null && command.sampleDupNo eq number}">
			   selected
		  </c:if>
										value="<c:out value="${number}"/>">
										<c:out value="${number}" />
									</option>
								</c:forEach>
							</select>
						</spring:bind>
					</div>
				</div>


				<div class="control-group">
					<label class="control-label">Received Date:</label>
					<div class="controls">
						<spring:bind path="command.receiveDate">
							<input class="span8" type="text" placeholder="DD-MM-YYYY"
								maxlength="255" size="20" name="receiveDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Status:</label>
					<div class="controls">
						<spring:bind path="command.status">
							<input class="span8" type="text" maxlength="255" size="20"
								name="status" value="<c:out value="${status.value}"/>">
						</spring:bind>
					</div>
				</div>


				<div class="control-group">
					<label class="control-label">Notes:</label>
					<div class="controls">
						<spring:bind path="command.notes">
							<input class="span8" type="text" maxlength="255" size="20"
								name="notes" value="<c:out value="${status.value}"/>">
						</spring:bind>
					</div>
				</div>




			</div>
		</div>
		<div class="span4">
			<div class="form-horizontal form-inline">

				<div class="control-group">
					<label class="control-label">Concentration:</label>
					<div class="controls">
						<spring:bind path="command.od">
							<input class="span8" type="text" maxlength="255" size="20"
								name="od" value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
							<span class="label">ug/mL</span>
						</spring:bind>
					</div>
				</div>


				<div class="control-group">
					<label class="control-label">Conc. Read Date:</label>
					<div class="controls">
						<spring:bind path="command.odDate">
							<input class="span8" type="text" maxlength="255" size="20"
								name="odDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
								placeholder="DD-MM-YYYY"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>


				<div class="control-group">
					<label class="control-label">Volume:</label>
					<div class="controls">
						<spring:bind path="command.volumn">
							<input class="span8" type="text" maxlength="255" size="20"
								name="volumn" value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
						<span class="label">mL</span>
					</div>
				</div>



				<div class="control-group">
					<label class="control-label">Vol. Read Date:</label>
					<div class="controls">
						<spring:bind path="command.volumnDate">
							<input class="span8" type="text" placeholder="DD-MM-YYYY"
								maxlength="255" size="20" name="volumnDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>


				<div class="control-group">
					<label class="control-label">Created Date:</label>
					<div class="controls">
						<spring:bind path="command.madeDate">
							<input class="span8" type="text" placeholder="DD-MM-YYYY"
								maxlength="255" size="20" name="madeDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Transformed Date:</label>
					<div class="controls">
						<spring:bind path="command.transDate">
							<input class="span8" type="text" placeholder="DD-MM-YYYY"
								maxlength="255" size="20" name="transDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>



				<div class="control-group">
					<label class="control-label">Refill Date:</label>
					<div class="controls">
						<spring:bind path="command.refillDate">
							<input class="span8" type="text" placeholder="DD-MM-YYYY"
								maxlength="255" size="20" name="refillDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Removed Date:</label>
					<div class="controls">
						<spring:bind path="command.removeDate">
							<input class="span8" type="text" placeholder="DD-MM-YYYY"
								maxlength="255" size="20" name="removeDate"
								pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
								value="<c:out value="${status.value}"/>">
							<FONT color="red"> <B><c:out
										value="${status.errorMessage}" /></B>
							</FONT>
						</spring:bind>
					</div>
				</div>

				<div class="control-group">
					<div class="controls">
						<input class="btn" type="submit" name="Submit" value="Save">
						<input class="btn" type="reset" name="Submit2" value="Reset">
					</div>
				</div>
			</div>
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>