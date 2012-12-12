$(document).ready(function() {
	$('.barcodeInput').focus();
});

var oldVal;

$('.barcodeInput').bind('input', function(event) {
	var val = this.value;
	if (val !== oldVal) {
	    oldVal = val;
	    $('.barcodeAdd').trigger('click');
	}
});


//	$('#oneNext').click(function() {
//		nextStep(".one",".two");
//	});

//
//function nextStep(a, b) {
//	$(a).attr("disabled", true);
//	$(b).attr("disabled", false);
//}
//
//function backStep(a, b) {
//	$(a).attr("disabled", false);
//	$(b).attr("disabled", true);
//}

function multiForm() {
	var ns = document.getElementById("numSamplesText").value;
	if (ns >= 10) {
		ns = 10;
	}
	url = window.location.search = "?ns=" + ns;
	window.location = url
}

function expandable() {
	var expandable = document.getElementById('expandable');
	var expander = document.getElementById('expander');
	if (expandable.style.display == 'none') {
		expandable.style.display = 'block';
		expander.innerHTML = '[Hide Concentration]';
	} else {
		expandable.style.display = 'none';
		expander.innerHTML = '[Show Concentration]';
	}
}
