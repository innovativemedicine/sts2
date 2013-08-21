<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<h2>Register Plates</h2>

<form method="post" enctype="multipart/form-data">
	<div class="row-fluid">
		<div class="span8">

			<div class="alert alert-info">
				<h2>Plate Manifest</h2>
			</div>

			<table class="table">
				<tr>
					<td class="form-inline"><label>Plate ID *</label> <input
						required class="span3" type="text" name="platePrefix" value=""></td>
					<td>
						<!-- 					Upload File Manifest -->
						<div class="fileupload fileupload-new " data-provides="fileupload">
							<span class="input-prepend input-append form-inline"> 
							<span class="btn btn-file"> <span class="fileupload-new">Select
										Manifest</span> <span class="fileupload-exists">Change
										Manifest</span> <input type="file" name="file" />
							</span> <span class="uneditable-input fileupload-preview"></span> <input
								class="btn fileupload-exists" type="submit" name="action"
								value="Upload">
							</span>
						</div>
					</td>

				</tr>
				<tr>
					<td colspan="2"><input class="btn" type="submit" name="action"
						value="Save"></td>
				</tr>
			</table>
			<div class="alert">
				Download Plate Manifest <input class="btn unhider2" type="submit"
					name="action" value="Download">
			</div>

			<p>
			<table class="table">

			</table>
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>