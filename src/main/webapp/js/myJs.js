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

// Universal setting for datepicker object. 
// The Date format of datepicker is specified in the textbox using data-date-format="dd-mm-yyyy" 
$('.datepicker').datepicker(
{
	autoclose: true
});

// Linked button is used in registerMultiple Samples to link the button to a submit button
// Use <button> instead of <input> to fix this later
$('.linkButton').click(function() {
	$('.linkedButton').click();
});

// selectNav is used in addSample2Container which changes the select box into URL navigation
$('.selectNav').change(function() {
	location.search = this.value;
});

// Helper function to return hex instead of RGB
$.fn.getHexBgColour = function() {
    var rgb = $(this).css('background-color');
    rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
    function hex(x) {return ("0" + parseInt(x).toString(16)).slice(-2);}
    return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
};

//clickSelect is used for Sample Type Number and Selection in Register Sample
$('.clickSelect').click(function() {
	this.select();
});

$('.clickSelect').change(function() {
	
	var selectValue = this.value;
	var labelId = '#' + this.name + '_label';
		
	if(selectValue > 0) {
		$(labelId).css('background-color', '#ffffff');
	}
	else {
		$(labelId).css('background-color', '#eeeeee');
	}
});