<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

		Select the field you want to export
		<form method="post">
			<table>

				<tr>
					<td><spring:bind path="command.outExtSampleId">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> External SampleId</td>
				</tr>



				<tr>
					<td><spring:bind path="command.outAnotherExtSampleId">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> External SampleId 2</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outOd">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> DNA Concentration</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outOdDate">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> DNA Concentration reading date</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outVolumn">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> DNA Volumn</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outVolumnDate">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> DNA Volumn reading date</td>
				</tr>


				<tr>
					<td><spring:bind path="command.outReceiveDate">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> Receive date</td>
				</tr>


				<tr>
					<td><spring:bind path="command.outMadeDate">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> DNA made date</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outTransDate">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> Transfer date</td>
				</tr>


				<tr>
					<td><spring:bind path="command.outRefillDate">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> Refill date</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outSampleType">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> Sample Type Suffix</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outSampleDupNo">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> Sample Dup No</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outNotes">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> Sample Note</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outStatus">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> Sample Status</td>
				</tr>


				<tr>
					<td><spring:bind path="command.outSampleLoci">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> Sample location</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outPatientNote">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> Patient Note</td>
				</tr>

				<tr>
					<td><spring:bind path="command.outIsControl">
							<input type="hidden"
								name="<c:out value="_${status.expression}"/>">
							<input type="checkbox"
								name="<c:out value="${status.expression}"/>" value="true"
								id="<c:out value="${status.expression}"/>">
						</spring:bind> Is control Sample?</td>
				</tr>

				<tr>
					<td><input type="submit" name="Submit" value="Export">
						<input type="reset" name="Submit2" value="Reset">
						</td>
				</tr>
			</table>
			</form>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>