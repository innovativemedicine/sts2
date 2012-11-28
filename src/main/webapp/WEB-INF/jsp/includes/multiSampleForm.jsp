<c:if test="${param.ns >= 0 }">
	<form method="post">
	
	<table class="details">
	
		<tr>
			<th>Barcode ID</th>
			<th>External ID</th>
			<th>External ID2</th>
			<th>Sample Type</th>
			<th>Project</th>
			<th>Notes</th>
		<tr>
		
		<c:forEach var="row" begin="0" end="${param.ns}" step="1" varStatus="rowStatus">
	
		<tr> 
	      	<td> 
	      
	        	<spring:bind path="command.multiSamples[${row}].patient.intSampleId">
				   		<INPUT type="text" maxlength="255" size="15" 
				   		name="<c:out value="${status.expression}"/>" 
				   		value="<c:out value="${status.value}"/>">
				   		
				   		<c:if test="${status.errorMessage != ''}">
					   		<FONT color="red">*</FONT>
						</c:if>
				</spring:bind>
	      	</td>
		 
	     	<td> 
	        	<spring:bind path="command.multiSamples[${row}].patient.extSampleId">
			  		<INPUT type="text" maxlength="64" size="15" 
			  			name="<c:out value="${status.expression}"/>" 
			  			value="<c:out value="${status.value}"/>" > 
					   	
					   	<c:if test="${status.errorMessage != ''}">
					   		<FONT color="red">*</FONT>
						</c:if>
				</spring:bind>
	      	</td>
	    
	        <td> 
	        	<spring:bind path="command.multiSamples[${row}].patient.anotherExtSampleId">
			  		<INPUT type="text" maxlength="64" size="15" 
			  		name="<c:out value="${status.expression}"/>" 
			  		value="<c:out value="${status.value}"/>" > 
				</spring:bind>
			</td>
			
	    	<td>
		    	<spring:bind path="command.multiSamples[${row}].sampleType">
		
			    	<select size="1" name='<c:out value="${status.expression}" />' >
			       		<option value="">Select Sample Type</option>
					       
			       		<c:forEach items="${allSampleTypes}" var="sampleTypeOpt" >
			       	
			       		<option 
			       		
			       		  	<c:if test="${command.multiSamples[0].sampleType != null && command.multiSamples[0].sampleType.sampleTypeId eq sampleTypeOpt.sampleTypeId}">
				   				selected
			 				</c:if>
			 				
			 				value="<c:out value="${sampleTypeOpt.sampleTypeId}"/>">
			 				
							<c:out value="${sampleTypeOpt.name}"/>
					 	</option>
					 	
				  		</c:forEach>
				  		  
			    	</select> 
		    	</spring:bind>
	    	</td>
	  
		 	<td> 
	      		<spring:bind path="command.multiSamples[${row}].patient.project">
	     			<select size="1" name='<c:out value="${status.expression}" />' >
	       				<option value="">Select Project</option>
						 	<c:forEach items="${allProjects}" var="project">
							     <option 
							    
								    <c:if test="${command.multiSamples[row].patient.project != null && command.multiSamples[row].patient.project.projectId eq project.projectId}">
						   				selected
					 				</c:if>
				 				
									 value="<c:out value="${project.projectId}"/>"><c:out value="${project.name}"/>
								 
								</option>
						  	
						  	</c:forEach>
	     			</select>
	  			</spring:bind>
	
	   		</td>
	
		    <td> 
				<spring:bind path="command.multiSamples[${row}].patient.note">
				   	<textarea rows="1" cols="15" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}"/></textarea> 
				</spring:bind>
	      	</td>
	      	
	    </c:forEach>
	    
	    		 <tr>
			    <td>If print labels: <input type="checkbox" name="labelNo" value="1" checked>
			    </td>
			  </tr>
			  
			      <tr> 
	      <td >
	        <p>
	          <input type="submit" name="Submit" value="Save">
	          <input type="reset" name="Submit2" value="Reset">
	        </p>
	        </td>
	    </tr>
			  
	    </table>
	</form>
</c:if>