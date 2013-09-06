<%@ include file="/WEB-INF/jsp/includes/head.jsp"%>

<div id="myCarousel" class="carousel slide span12" data-interval="false">
	<!-- Carousel items -->
	<div class="carousel-inner">
		<div class="active item span10">
			<div class="carousel-html">
				<form method="post" enctype="multipart/form-data">


					<div class="alert alert-info">
						<h2>Register Plates</h2>
					</div>


					<label>Plate ID Prefix</label> 
					<input required class="input-mini"
						type="text" name="platePrefix" value="">
				
					<!-- 					Upload File Manifest -->
					<div class="fileupload fileupload-new " data-provides="fileupload">
						<span class="input-prepend input-append form-inline"> <span
							class="btn btn-file"> <span class="fileupload-new">Select
									Manifest</span> <span class="fileupload-exists">Change Manifest</span>
								<input type="file" name="file" />
						</span> <span class="uneditable-input fileupload-preview"></span> <input
							class="btn fileupload-exists" type="submit" name="action"
							value="Upload">
						</span>
					</div>
				
					<input class="btn" type="submit" name="action" value="Save">

					<div class="alert">
						Download Plate Manifest 
						<button class="btn btn-mini" type="submit" name="action" value="Download"><i class="iconic-download"></i></button>
					</div>

					<p>
					<table class="table">

					</table>

				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp"%>