<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>
<h2>Barcode</h2>
<div class="row-fluid">
	<div class="span4">
		<form method="post">


			<spring:bind path="command.barcodeItem">
				<input class="barcodeInput setFocus" name="barcodeItem" type="text"
					size="30">
			</spring:bind>
			
			<table class="table">
				<tr class="info">
					<td>Completed Actions:</td>
				</tr>
				<tr>
					<td><input class="btn" type="submit" name="action"
						value="Clear"> <input class="hide" type="submit"
						name="action" value="ADD"></td>
				</tr>
				<c:forEach items="${actionedList}" var="actioned">
					<tr>
						<td><input type="checkbox"> <c:out
								value="${actioned}" escapeXml="false" /></td>
					</tr>
				</c:forEach>

			</table>
		</form>
	</div>
	<div class="span4">

		<b> Test Cases:</b> C-ttt <br> C-test <br> S-TESTID000001-SR
		<br> S-TESTID000001-DO <br> S-TESTID000002-DO <br>
		S-TESTID000003-DO <br> S-TESTID000003-ABC <br>
		S-TESTID111111-DO <br> S-TEST000001-DO <br> P-TESTPL000001 <br>
		P-TESTPL000002 <br> P-TESTPL111111 <br> P-TEST000001 <br>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>