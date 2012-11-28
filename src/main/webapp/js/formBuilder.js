			
	function multiForm(){
		var ns = document.getElementById("numSamplesText").value;
		
		if (ns > 10)
			{
				ns = 10;
			}
		
		ns=ns-1;
		
		url = window.location.search = "?ns=" + ns; 
		
		window.location=url
		
	}
	
	function expandable(){
		var expandable = document.getElementById('expandable');
		var expander = document.getElementById('expander');
		if (expandable.style.display == 'none') {
			expandable.style.display = 'block';
			expander.innerHTML='[Hide Concentration]';
		} 
		else {
			expandable.style.display = 'none';
			expander.innerHTML='[Show Concentration]';
		}		
	}
	