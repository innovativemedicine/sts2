
	<table class="table containerMap table-condensed table-bordered">

		<c:forEach begin="0" end="${command.rowNo-1}" var="rowCount">
			<tr>
				<c:forEach begin="0" end="${command.columnNo-1}" var="columnCount">
					<td class="input-small">					
						<span class="container-header"><c:out value="${command.cells[rowCount][columnCount].well}" /></span>
						<c:if test="${!command.cells[rowCount][columnCount].notOccupied}">
							<a class="close"
								href="<c:url value="/deleteSampleFromContainer.htm"><c:param name="sicId" value="${command.cells[rowCount][columnCount].sicId}"/><c:param name="containerId" value="${container.containerId}"/></c:url>"
								onclick="return (confirm('Are you sure you want to delete the sample from this container?')) ">&times;</a>
						</c:if>
						<br>

						<c:if test="${command.cells[rowCount][columnCount].notOccupied}">
							<spring:bind path="command.cells[${rowCount}][${columnCount}].sampleDesc">
								
									<c:if test="${not empty sampleList}">
										<select class="container-well container-details" name='<c:out value="${status.expression}"/>'>
											<option value="">Empty</option>
											<c:forEach items="${sampleList}" var="sample">
												<option value="<c:out value="${sample.patient.intSampleId}"/>-<c:out value="${sample.sampleType.suffix}"/>-<c:out value="${sample.sampleDupNo}"/>"><c:out value="${sample.patient.intSampleId}"/>-<c:out value="${sample.sampleType.suffix}"/>-<c:out value="${sample.sampleDupNo}"/>
												</option>
											</c:forEach>
										</select>
									</c:if>
									
									<c:if test="${empty sampleList}">
										<input class="container-well container-details" placeholder="Empty" type="text"
										name='<c:out value="${status.expression}"/>' />
									</c:if>								
								
							</spring:bind>
						</c:if> 
						
						<c:if test="${!command.cells[rowCount][columnCount].notOccupied}">
							<p class="uneditable-input container-well container-details"><c:out value="${command.cells[rowCount][columnCount].sampleDesc}" /> </p>
						</c:if>
						
						</td>
				</c:forEach>
					
			</tr>
		</c:forEach>
	</table>
