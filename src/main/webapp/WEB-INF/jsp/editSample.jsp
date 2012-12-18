<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Edit Samples</h2>

<form method="post">


	<table class="details">


		<tr>
			<td>Internal Id</td>

			<td><c:if test="${!command.patient.newPatient}">
					<c:out value="${command.patient.intSampleId}" />
				</c:if> <c:if test="${command.patient.newPatient}">
					<spring:bind path="command.patient.intSampleId">
						<INPUT type="text" maxlength="255" size="20"
							name="patient.intSampleId"
							value="<c:out value="${status.value}"/>">
						<FONT color="red"> <B><c:out
									value="${status.errorMessage}" /></B>
						</FONT>
					</spring:bind>
				</c:if></td>

		</tr>


		<tr>
			<td>Sample Type:</td>
			<td><spring:bind path="command.sampleType">
					<select size="1" name='<c:out value="${status.expression}" />'>
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
				</spring:bind></td>
		</tr>

		<tr>
			<td>Concentration :</td>
			<td><spring:bind path="command.od">
					<INPUT type="text" maxlength="255" size="20" name="od"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
					<span class="label">ug/mL</span>
				</spring:bind></td>
		</tr>

		<tr>
			<td>Concentration Reading on:</td>
			<td><spring:bind path="command.odDate">
					<INPUT type="text" maxlength="255" size="20" name="odDate"
						pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
						placeholder="DD-MM-YYYY" value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind></span></td>
		</tr>

		<tr>
			<td>Volume</td>
			<td><spring:bind path="command.volumn">
					<INPUT type="text" maxlength="255" size="20" name="volumn"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind><span class="label">mL</span></td>
		</tr>

		<tr>
			<td>Volume Reading On</td>
			<td><spring:bind path="command.volumnDate">
					<INPUT type="text" placeholder="DD-MM-YYYY" maxlength="255"
						size="20" name="volumnDate"
						pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind></td>
		</tr>

		<tr>
			<td>Notes</td>
			<td><spring:bind path="command.notes">
					<INPUT type="text" maxlength="255" size="20" name="notes"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>

		<tr>
			<td>Status:</td>
			<td><spring:bind path="command.status">
					<INPUT type="text" maxlength="255" size="20" name="status"
						value="<c:out value="${status.value}"/>">
				</spring:bind></td>
		</tr>

		<tr>
			<td>Sample Received On:</td>
			<td><spring:bind path="command.receiveDate">
					<INPUT type="text" placeholder="DD-MM-YYYY" maxlength="255"
						size="20" name="receiveDate"
						pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind></td>
		</tr>

		<tr>
			<td>Sample Made On:</td>
			<td><spring:bind path="command.madeDate">
					<INPUT type="text" placeholder="DD-MM-YYYY" maxlength="255"
						size="20" name="madeDate"
						pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind></td>
		</tr>

		<tr>
			<td>Transformed On:</td>
			<td><spring:bind path="command.transDate">
					<INPUT type="text" placeholder="DD-MM-YYYY" maxlength="255" size="20" name="transDate"
						pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind></td>
		</tr>

		<tr>
			<td>Refill On:</td>
			<td><spring:bind path="command.refillDate">
					<INPUT type="text" placeholder="DD-MM-YYYY" maxlength="255" size="20" name="refillDate"
						pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind></td>
		</tr>



		<tr>
			<td>Remove On:</td>
			<td><spring:bind path="command.removeDate">
					<INPUT type="text" placeholder="DD-MM-YYYY" maxlength="255" size="20" name="removeDate"
						pattern="(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20|21)\d\d"
						value="<c:out value="${status.value}"/>">
					<FONT color="red"> <B><c:out
								value="${status.errorMessage}" /></B>
					</FONT>
				</spring:bind></td>
		</tr>

		<tr>
			<td>Sample Duplicate No.:</td>
			<td><spring:bind path="command.sampleDupNo">
					<select size="1" name='<c:out value="${status.expression}" />'>
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

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>