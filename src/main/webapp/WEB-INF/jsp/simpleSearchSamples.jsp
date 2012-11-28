
<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<br>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<h2>
	<fmt:message key="sample.search.title" />
</h2>
<form method="post" enctype="multipart/form-data">
	<table class="details">

		<a href="<c:url value="/samples.htm"></c:url>">Advanced Sample
			Search</a>
		<tr>
			<td>Upload or enter a list of internal sample Id, will give you
				all samples with these sample ids. If you specify the sample type at
				the same time, will give you all samples with these sample ids of
				that sample type.</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>Enter or paste your Internal sample Id here, delimited by
				comma, or one sample one line <textarea name="sampleIdsInTextArea"
					rows="15" cols="40"></textarea>

			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Upload a sampleId list file: <input type="file" name="file" />
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>If you do not check sample types, It means all types.</td>
		</tr>
		<c:forEach items="${LSampleTypes}" var="container">
			<tr>
				<td><input type="checkbox" name="sampleTypes"
					value="<c:out value="${container.sampleTypeId}"/>"> <c:out
						value="${container.name}" /></td>
			</tr>
		</c:forEach>
		<tr>
			<td><input type="checkbox" name="useFor" value="useFor">
				For printing labels</td>
		</tr>
		<tr>
			<td><input type="submit" name="Submit" value="SEARCH"></td>
		</tr>
	</table>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>