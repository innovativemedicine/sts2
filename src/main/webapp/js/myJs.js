$(document).ready(function() {
	$('.setFocus').focus();
});

var oldVal;

$('.barcodeInput').bind('input', function(event) {
	var val = this.value;
	if (val !== oldVal) {
	    oldVal = val;
	    $('.barcodeAdd').trigger('click');
	}
});

function multiForm() {
	var ns = document.getElementById("numSamplesText").value;
	if (ns >= 10) {
		ns = 10;
	}
	url = window.location.search = "?ns=" + ns;
	window.location = url
}

$('.unhider').click(function() {
	unhide();
});

function unhide() {
	if ($('.hide').is(':visible')) {
		$('.hide').fadeToggle();
		if($('.unhider').text() == '[Hide]' )
		{
			$('.unhider').text('[Show]');
		}
	} else {
		$('.hide').fadeToggle();
		if($('.unhider').text() == '[Show]' )
		{
			$('.unhider').text('[Hide]');
		}
	}
}

$('.unhider2').click(function() {
	unhide2();
});

function unhide2() {
	if (!($('.hide2').is(':visible'))) {
		$('.hide2').fadeIn();
	}
}
