<div class="left">
	<form method="post">

	ttt
	test
	test001
	1
	test001-ABC-1
	test001-ABC
		<table class="searchBuilder">
			<tr>
				<th><b>Barcode:</b> <spring:bind path="command.barcodeItem">
						<input class="barcodeInput" name="barcodeItem" type="text"
							size="30">
					</spring:bind></th>
			</tr>
			<tr>
				<td><input class="barcodeAdd" type="submit" name="action"
					value="ADD"> <input type="submit" name="action"
					value="RESET"> <input type="submit" name="action"
					value="REVIEW"></td>
			</tr>
		</table>
	</form>
</div>
