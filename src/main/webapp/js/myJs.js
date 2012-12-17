$(document).ready(function() {
	$('.setFocus').focus();
});

var oldVal;

$('.barcodeInput').bind('input', function(event) {
	var val = this.val();
	if (val != oldVal) {
	    oldVal = val;
	    $('.barcodeAdd').trigger('click');
	}
});

$('.generateForm').bind('input', function(event) {
	var ns = $('.generateForm').val();
	var sp = $('#sampleIdPreForm').val().toUpperCase();
	
	if(ns.match(/^\d+$/)) {
		if (ns >= 3) {
			ns = 3;
		}
	}
	else {
		ns = 0;
	}
	
	if(!$.trim(sp).length) {
		location.search = $.param({'ns':ns});
	}
	else {
	//	reload page with attached param
		location.search = $.param({'ns':ns, 'sp':sp});
	}
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
		$('.hide1').hide();
		$('.hide2').fadeIn();
	

	}
});