<div class="left">
	<form method="post">

	C-ttt<br>
	C-test<br>
	
	S-test001-ABC-1<br>
	S-test001-MST-4<br>
	S-test001-DEC-1<br>
	S-test001-BM-10<br>

	S-test002-ABC-1<br>
	S-test002-BM-1<br>
	S-test003-DEC-1<br>
	S-test004-BM-10<br>

	
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
