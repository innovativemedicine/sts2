$(document).ready(function() {
	$('.setFocus').focus();
});

var oldVal;

$('.barcodeInput').bind('input', function(event) {
	var val = this.value;
	if (val != oldVal) {
	    oldVal = val;
	    $('.barcodeAdd').trigger('click');
	}
});

$('.generateForm').click(function() {
	var ns = $('#numSamplesText').val();
	if (ns >= 10) {
		ns = 10;
	}
//	reload page with attached param
	location.search = $.param({'ns':ns});
});

$('.unhider').click(function() {
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
});

$('.unhider2').click(function() {
	if (!($('.hide2').is(':visible'))) {
		$('.hide2').fadeIn();
	}
});