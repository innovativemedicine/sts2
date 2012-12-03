<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp"%>

<h2>Print Labels</h2>
<form method="post" enctype="multipart/form-data">
	<div class="left">
		<table class="details">
			<tr>
				<th>Sample ID</th>
			</tr>
			<tr>
				<td colspan="2">Enter Sample ID List:<br> (Separate
					samples by commas or new lines)<br> <TEXTAREA
						name="sampleIdsInTextArea" rows="10" cols="40">
  
   </TEXTAREA>
				</td>
			</tr>
			
					<tr>
				<td colspan="3">How many labels to print for each
					sample? <input type="text" name="labelNo" size="5">
				</td>
			</tr>
			
			<tr>
				<td><input type="submit" name="Submit"
					value="Print"> <input type="reset" name="Reset"
					value="Reset"></td>
			</tr>

		</table>
	</div>
	<div>
		<table>
			<tr>
				<th colspan="3"><b>Filter Search by Sample Types</b></th>
			</tr>
			<c:forEach items="${availableSampleTypes}" var="sampleType"
				varStatus="row">
				<c:if test="${row.count % 3 eq 1}">
					<tr>
				</c:if>
				<td style="font-size: 10pt"><input type="checkbox"
					name="<c:out value="${sampleType.suffix}"/>"> 
					<c:out value="${sampleType.name}" />
				</td>
				<c:if test="${row.count % 3 eq 0}">
					</tr>
				</c:if>

			</c:forEach>
			</td>
			</tr>

	




		</table>
	</div>
</form>
</td>
</tr>
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>