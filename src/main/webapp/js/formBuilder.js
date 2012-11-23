
			function buildFormFields($amount)
			{
				var
					$container = document.getElementById('FormFields'),
					$item, $field, $i;

				$container.innerHTML = '';
				
				for ($i = 0; $i < $amount; $i++) {
					$item = document.createElement('div');
					$item.style.margin = '3px';

					$field = document.createElement('span');
					$field.innerHTML = 'Internal ID';
					$field.style.marginRight = '10px';
					$item.appendChild($field);

					$field = document.createElement('input');
					$field.name = 'Design[' + $i + ']';
					$field.type = 'text';
					$item.appendChild($field);

					$field = document.createElement('span');
					$field.innerHTML = 'Quantity of Design';
					$field.style.margin = '0px 10px';
					$item.appendChild($field);

					$field = document.createElement('input');
					$field.name = 'Quantity[' + $i + ']';
					$field.type = 'text';
					$item.appendChild($field);

					$container.appendChild($item);
				}
			}

			function tableTester(num_rows, num_cols){  
			    var t  = document.createElement("table");  
			        tb = document.createElement("tbody");  
			        t.setAttribute("border","0");  
			        t.setAttribute("class","details");  
			    
			    var tr;
			    var th;
			    
			    	tr= document.createElement("tr"); 
			    	
			    	th = document.createElement("th"); 
					th.innerHTML = 'Internal ID';
			    	tr.appendChild(th);
			
			    	th = document.createElement("th");  
					th.innerHTML = 'External ID';
			    	tr.appendChild(th);
			    	
			    	th = document.createElement("th");  
					th.innerHTML = 'External ID 2';
			    	tr.appendChild(th);
			    	
			    	th = document.createElement("th");  
					th.innerHTML = 'Sample Type';
			    	tr.appendChild(th);
			    	
			    	th = document.createElement("th");  
					th.innerHTML = 'Project';
			    	tr.appendChild(th);
			    	
			    	th = document.createElement("th");  
					th.innerHTML = 'Notes';
			    	tr.appendChild(th);
			    	
			        tb.appendChild(tr);  
			        t.appendChild(tb);  
			        
				for( var i=0; i<num_rows;i++)
				    {
					    tr = document.createElement("tr");  
					    
				        for( var j=0; j<num_cols;j++)
				        {
						    var td, d, field;  
						  		    
					        td = document.createElement("td");  

					        d = document.createElement("div");  
							d.style.margin = '1px';
					        
						    if (j == 0) {
								field = document.createElement('input');
								field.name = 'intSample[' + i + ']';
								field.type = 'text';
						    }
						    else if (j == 1) {
								field = document.createElement('input');
								field.name = 'extSample[' + i + ']';
								field.type = 'text';
						    }
						    else if (j == 2) {
								field = document.createElement('input');
								field.name = 'extSampleTwo[' + i + ']';
								field.type = 'text';
						    }
						    else if (j == 3) {
								field = document.createElement('select');
								field.name = 'sampleType[' + i + ']';
								field.type = 'select';
								
								var option = document.createElement('option');
								option.value = 'null';
								option.appendChild(document.createTextNode('Select Sample Type'));
								field.appendChild(option);

								option = document.createElement('option');
								option.value = '1';
								option.appendChild(document.createTextNode('PM'));
								field.appendChild(option);
								
						    }
						    else if (j == 4) {
								field = document.createElement('select');
								field.name = 'project[' + i + ']';
								
								var option = document.createElement('option');
								option.value = 'null';
								option.appendChild(document.createTextNode('Select Project'));
								field.appendChild(option);

								option = document.createElement('option');
								option.value = '3';
								option.appendChild(document.createTextNode('DM'));
								field.appendChild(option);
								
						    }
						    else if (j == 5) {
								field = document.createElement('input');
								field.name = 'notes[' + i + ']';
								field.type = 'text';
						    }
						    else
						    {   	
								field = document.createElement('input');
								field.name = 'blank[' + i + ']';
								field.type = 'text';
						    }
						    
						    	d.appendChild(field);
						        td.appendChild(d);  
						        tr.appendChild(td);  
				        } 
					        tb.appendChild(tr);  
					        t.appendChild(tb);  
				    }
		       document.getElementById("wrapper").appendChild(t);  
				  
			}  
			
			function testPrint(object){  
				document.write(object);
			}