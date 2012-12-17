<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>

<div class="left" style="height: 800px">
	<form method="post">

		<table style="width: 500px; text-align: left">
			<tr>
				<th><b>Barcode:</b> <spring:bind path="command.barcodeItem">
						<input class="barcodeInput setFocus" name="barcodeItem"
							type="text" size="30">
					</spring:bind></th>
			</tr>
			<tr>
				<th>Completed Actions:</th>
			</tr>
			<tr>
				<td><input type="submit" name="action" value="Clear"> <input
					class="barcodeAdd" type="submit" name="action" value="ADD">
				</td>
			</tr>
			<c:forEach items="${actionedList}" var="actioned">
				<tr>
					<td><input type="checkbox"> <c:out value="${actioned}"
							escapeXml="false" /></td>
				</tr>
			</c:forEach>

		</table>
	</form>
</div>
<div>
	<p>
		<b> Test Cases:</b>
	</p>
	C-ttt <br> C-test <br> S-TESTID000001-SR <br>
	S-TESTID000001-DO <br> S-TESTID000002-DO <br>
	S-TESTID000003-DO <br> S-TESTID000003-ABC <br>
	S-TESTID111111-DO <br> S-TEST000001-DO <br> P-TESTPL000001 <br>
	P-TESTPL000002 <br> P-TESTPL111111 <br> P-TEST000001 <br>
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>