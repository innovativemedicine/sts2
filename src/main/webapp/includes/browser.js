//---- Set this variable only! ----
var buildDir = "includes/";
//---------------------------------

var dom = false, opera = false, safari = false;
var agent = navigator.userAgent.toLowerCase();

if (agent.indexOf("opera") != -1)
	opera = true;
else if (agent.indexOf("safari") != -1)
	safari = true;
else if (document.getElementById)
	dom = true;

var blank = new Image();
blank.src = buildDir + "blank.gif";

if (dom)
	document.write('<script type="text/javascript" src="' + buildDir + 'dom-build.js"></script>');
else if (opera)
	document.write('<script type="text/javascript" src="' + buildDir + 'op7-build.js"></script>');
else if (safari)
	document.write('<script type="text/javascript" src="' + buildDir + 'saf-build.js"></script>');
else
{
	alert("Your browser doesn't support this script.");
	location.href = buildDir + "upgrade.html";
	document.write('<script type="text/javascript" src="' + buildDir + 'no-build.js"></script>');
}