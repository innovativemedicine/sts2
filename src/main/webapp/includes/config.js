
function init()
{
	//Main Menu items:
	menus[0] = new menu(22, "horizontal", 40, 80, -2, -2, "#003366", "#00A000", "Verdana,Helvetica", 9, 
		"bold", "bold", "white", "black", 1, "gray", 2, "", false, true, true, false, 12, false, 4, 4, "black");
	menus[0].addItem("#", "", 140, "center", "Sample", 1);
	menus[0].addItem("#", "", 140, "center", "Reagent & Container", 2);
	menus[0].addItem("#", "", 100, "center", "Project", 3);
	menus[0].addItem("#", "", 100, "center", "Result", 4);
	menus[0].addItem("#", "", 140, "center", "AGTC Management", 5);
	menus[0].addItem("#", "", 160, "center", "Database Management", 6);

	//Sub Menu for 1st Main Menu Item ("Sample"):
	menus[1] = new menu(175, "vertical", 0, 0, -5, -5, "#003366", "#00A000", "Verdana,Helvetica", 9, "bold", 
		"bold", "white", "black", 1, "gray", 2, 62, false, true, false, true, 6, true, 4, 4, "black");
	menus[1].addItem("addSingleSample.htm", "", 22, "left", "Register sample", 0);
	menus[1].addItem("searchPatient.htm", "", 22, "left", "Update sample", 0);
	menus[1].addItem("samples.htm", "", 22, "left", "Search samples", 0);
	menus[1].addItem("printSampleLabel.htm", "", 22, "left", "Print Sample Labels", 0);
	menus[1].addItem("saveSamplesInBatch.htm", "", 22, "left", "Samples in batch", 0);

//Sub Menu for 2nd Main Menu Item ("Reagent & Container"):
	menus[2] = new menu(175, "vertical", 0, 0, -5, -5, "#003366", "#00A000", "Verdana,Helvetica", 9, "bold", 
		"bold", "white", "black", 1, "gray", 2, 62, false, true, false, true, 6, true, 4, 4, "black");
	menus[2].addItem("reagents.htm", "", 22, "left", "Search reagents", 0);
	menus[2].addItem("containers.htm", "", 22, "left", "Search containers", 0);
	menus[2].addItem("editContainer.htm", "", 22, "left", "Add new container", 0);
	menus[2].addItem("makePlate.htm", "", 22, "left", "Make Container", 0);
	menus[2].addItem("printLabel4BloodCollection.htm", "", 22, "left", "Print Label for blood collection", 0);

//Sub Menu for 3rd Main Menu Item ("Project"):
	menus[3] = new menu(135, "vertical", 0, 0, 0, 0, "#003366", "#00A000", "Verdana,Helvetica", 9, "bold", 
		"bold", "white", "black", 1, "gray", 2, "", false, true, false, false, 0, true, 4, 4, "black");
	menus[3].addItem("projects.htm", "", 22, "left", "Search projects", 0);
	menus[3].addItem("tests.htm", "", 22, "left", "Search tests", 0);
	menus[3].addItem("assays.htm", "", 22, "left", "Search assays", 0);
	menus[3].addItem("projectsamples.htm", "", 22, "left", "Search project samples", 0);
	menus[3].addItem("editProject.htm", "", 22, "left", "Add new project", 0);


//Sub Menu for 4th Main Menu Item ("Result"):
	menus[4] = new menu(175, "vertical", 0, 0, 0, 0, "#003366", "#00A000", "Verdana,Helvetica", 9, "bold", 
		"bold", "white", "black", 1, "gray", 2, ">>", false, true, false, false, 0, true, 4, 4, "black");
	menus[4].addItem("results.htm", "", 22, "left", "Search results", 0);
	menus[4].addItem("runs.htm", "", 22, "left", "Search runs", 0);
	menus[4].addItem("editRun.htm", "", 22, "left", "Load results", 0);
	menus[4].addItem("addResult.htm", "", 22, "left", "Add a manualy test result", 0);

//Sub Menu for 4th Main Menu Item ("AGTC Management"):
	menus[5] = new menu(135, "vertical", 0, 0, 0, 0, "#003366", "#00A000", "Verdana,Helvetica", 9, "bold", 
		"bold", "white", "black", 1, "gray", 2, ">>", false, true, false, false, 0, true, 4, 4, "black");
	menus[5].addItem("containerType.htm", "", 22, "left", "Container Type", 0);
	menus[5].addItem("instrument.htm", "", 22, "left", "Instrument", 0);
	menus[5].addItem("investigator.htm", "", 22, "left", "Investigator", 0);
	menus[5].addItem("location.htm", "", 22, "left", "Location", 0);
	menus[5].addItem("sampleType.htm", "", 22, "left", "Sample Type", 0);
	menus[5].addItem("samplePrefix.htm", "", 22, "left", "Sample Prefix", 0);
	menus[5].addItem("updateResultToCGG.htm", "", 22, "left", "Update CGG", 0);
	
//Sub Menu for 4th Main Menu Item (Database Management)
	menus[6] = new menu(135, "vertical", 0, 0, 0, 0, "#003366", "#00A000", "Verdana,Helvetica", 9, "bold", 
		"bold", "white", "black", 1, "gray", 2, ">>", false, true, false, false, 0, true, 4, 4, "black");
	menus[6].addItem("http://www.google.com", "", 22, "left", "Google", 0);
	menus[6].addItem("http://www.yahoo.com", "", 22, "left", "Yahoo", 0);
	menus[6].addItem("javascript:alert('AlltheWeb')", "", 22, "left", "AlltheWeb", 0);
	menus[6].addItem("javascript:alert('hi,Teoma')", "", 22, "left", "Teoma", 0);


} //OUTER CLOSING BRACKET. EVERYTHING ADDED MUST BE ABOVE THIS LINE.
