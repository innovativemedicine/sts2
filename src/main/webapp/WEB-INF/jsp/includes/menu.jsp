<div class="navbar navbar-inverse navbar-static-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<button type="button" class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="brand" href="index.htm">STS 2.0&nbsp;<i class="iconic-beaker"></i></a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<!-- Sample Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Samples <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="nav-header">Samples</li>
							<li><a href="searchSamples.htm">Search Samples</a></li>
							<li><a href="addSamples.htm">Register Samples</a></li>
<!-- 							<li class="divider"></li> -->
<!-- 							<li class="nav-header">Plates</li> -->
<!-- 							<li><a href="searchPlates.htm">Search Plates (Demo) </a></li> -->
<!-- 							<li><a href="addPlate.htm">Register Plates (Demo)</a></li> -->
							<li class="divider"></li>
							<li><a href="barcode.htm">Barcode (Demo)</a></li>
						</ul></li>

					<!-- Container Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Containers <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="searchContainers.htm">Search Containers</a></li>
							<li><a href="editContainer.htm">Register Containers</a></li>

						</ul></li>

					<!-- Project Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Projects <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="nav-header">Projects</li>
							<li><a href="projects.htm">Search Projects</a></li>
							<li><a href="editProject.htm">Register Projects</a></li>
							<li class="divider"></li>
							<li class="nav-header">Tests</li>
							<li><a href="tests.htm">Search Tests</a></li>
							<li><a href="assays.htm">Search Assays</a></li>

						</ul></li>

					<!-- Results Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Results <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="nav-header">Results</li>

							<li><a href="results.htm">Search Results</a></li>
							<li><a href="addResult.htm">Add Results</a></li>
							<li class="divider"></li>
							<li class="nav-header">Runs</li>
							<li><a href="runs.htm">Show Runs</a></li>
							<li><a href="editRun.htm">Add Runs</a></li>

						</ul></li>
				</ul>
				<ul class="nav pull-right">
					<c:if test="${sessionScope.userRole != 'GUEST'}">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">STS <i class="iconic-cog"></i>
						</a>
							<ul class="dropdown-menu">
								<li><a href="sampleType.htm">Edit Sample Types</a></li>
								<li><a href="containerType.htm">Edit Container Types</a></li>
								<li><a href="location.htm">Edit Locations</a></li>
								<li><a href="instrument.htm">Edit Instruments</a></li>
								<c:if test="${sessionScope.userRole == 'ADMINISTRATOR'}">
									<li class="divider"></li>
									<li class="nav-header">Admin Options</li>
									<li><a href="investigator.htm">Edit Investigators</a></li>
									<li><a href="adminAddEditUser.htm">Edit Users</a></li>
								</c:if>

							</ul></li>
					</c:if>

					<!-- User Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><c:out value="${sessionScope.userName}" />&nbsp;<i
							class="iconic-user"></i></a>
						<ul class="dropdown-menu">
							<li><a href="editProfile.htm">Profile Settings</a></li>
							<li><a href="index.htm?login=on">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>