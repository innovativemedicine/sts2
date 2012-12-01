
<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<h2>
	<fmt:message key="sample.search.title" />
</h2>


<p>
<form method="post" enctype="multipart/form-data">
	<div class="left">
		<table>

			<tr>
				<th>Search by Sample ID</th>
			</tr>

			<tr>
				<td>Enter Internal Sample ID List to retrieve samples:<br>
					(Separate samples by commas or new lines) <br> <textarea
						name="sampleIdsInTextArea" rows="15" cols="40"></textarea>

				</td>
			</tr>
			<tr>
				<td>Upload Sample ID List file: <br> <input type="file"
					name="file" />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>

			<tr>
				<td><input type="checkbox" name="useFor" value="useFor">
					For printing labels</td>
			</tr>
			<tr>
				<td><input type="submit" name="Submit" value="SEARCH">
				<a href="<c:url value="/samples.htm"></c:url>">[Use Advanced Search]</a></td>
			</tr>
		</table>
	</div>
	<div>
		<table>
			<tr>
				<th colspan="3"><b>Filter Search by Sample Types</b></th>
			</tr>
			<tr>
				<td colspan="3">(If none are selected, all sample types will be
					retrieved.</td>
			</tr>
			<c:forEach items="${LSampleTypes}" var="container" varStatus="row">
				<c:if test="${row.count % 3 eq 1}">
					<tr>
				</c:if>
				<td><input type="checkbox" name="sampleTypes"
					value="<c:out value="${container.sampleTypeId}"/>"> <c:out
						value="${container.name}" /></td>

				<c:if test="${row.count % 3 eq 0}">
					</tr>
				</c:if>
			</c:forEach>

		</table>
	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>