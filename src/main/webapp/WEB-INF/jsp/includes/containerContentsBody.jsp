<p><h2> <c:out value="${container.name}"/></h2>

 <form method="post">
       <p>
          <input type="submit" name="Submit" value="Save">
		  <input type="reset" name="Submit2" value="Reset">
        </p>
      <table border="0" class="details" cellspacing="3">

	  <c:forEach begin="0" end="${command.rowNo-1}"  var="rowCount">
		 <tr>
		 <c:forEach begin="0" end="${command.columnNo-1}"  var="columnCount">
			<td>
			<font color="green"><c:out value="${command.cells[rowCount][columnCount].well}"/><br></font>
            <c:if test="${command.cells[rowCount][columnCount].notOccupied}">
				<spring:bind path="command.cells[${rowCount}][${columnCount}].sampleDesc">
				  <INPUT type="text" maxlength="255" size="15" name='<c:out value="${status.expression}" />' />
				  <spring:bind path="command.cells[${rowCount}][${columnCount}].dupNo">
				 <select size="1" name='<c:out value="${status.expression}" />'>
				 <c:forEach items="${numbers}" var="number">
					 <option 
					  value="<c:out value="${number}"/>"><c:out value="${number}"/></option>
				  </c:forEach>
				  </select>
				  </spring:bind>
	  
			  
			  <br>
			   <FONT color="red">
					<B><c:out value="${status.errorMessage}"/></B>
				</FONT>
			</spring:bind>

			<spring:bind path="command.cells[${rowCount}][${columnCount}].sampleTypeSuffix">
			 <select size="1" name='<c:out value="${status.expression}" />'>
			 <c:forEach items="${availableSampleTypes}" var="sampleType">
				 <option 
				  value="<c:out value="${sampleType.suffix}"/>"><c:out value="${sampleType.name}"/></option>
			  </c:forEach>
			  </select>
			  </spring:bind>

			</c:if>
			
		    <c:if test="${!command.cells[rowCount][columnCount].notOccupied}">
					<c:out value="${command.cells[rowCount][columnCount].sampleDesc}"/><br>
					<a href="<c:url value="/deleteSampleFromContainer.htm"><c:param name="sicId" value="${command.cells[rowCount][columnCount].sicId}"/><c:param name="containerId" value="${container.containerId}"/></c:url>" onclick="return (confirm('Are you sure you want to delete the sample from this container?')) " >Remove</a>
		    </c:if>

		 


		    </td>
          </c:forEach>
            </tr>
          </c:forEach>
          
          
        </table>
	 </form>