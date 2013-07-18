$(window).load(function() {
	$('.setFocus').focus();
	$('.autoSubmit').trigger('click');
});

var oldVal;

$('.barcodeInput').bind('input', function(event) {
	var val = this.value;
	if (val != oldVal) {
		oldVal = val;
		$('.barcodeAdd').trigger('click');
	}
});

$('.linkButton').click(function() {
	$('.linkedButton').click();
});

$('.selectNav').change(function() {
	location.search = this.value;
});

//$('.generateForm').bind('input', function(event) {
//	var ns = 1;
//
//	location.search = $.param({
//		'ns' : ns
//	});
//});

$('.unhider').click(function() {
	if ($('.hide').is(':visible')) {
		$('.hide').fadeToggle();
		if ($('.unhider').text() == '[Hide]') {
			$('.unhider').text('[Show]');
		}
	} else {
		$('.hide').fadeToggle();
		if ($('.unhider').text() == '[Show]') {
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