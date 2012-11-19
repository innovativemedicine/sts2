

//  QuickMenu Pro, Copyright (c) 1998 - 2003, OpenCube Inc. - http://www.opencube.com
//  
//
//  QuickMenu Pro is Compatible With....
//
//      IE4, IE5.x, IE6 (Win 95, 98, ME, 2000, NT, XP)
//      IE4, IE5.x, &up (Mac)
//      IE4 & up (other platforms)
//      NS4.x (All Platforms)
//      NS5/6.x (All Platforms)
//      NS7 (All Platforms)
//      Opera 5,6,7 (All Platforms)
//      Mozilla 0.6 & up (All Platforms)
//      Konqueror 2.2 & up (Linux)
//      Espial Escape 4.x & up (All Platforms)
//      Ice Browser 5.x & up (All Platforms)
//      Safari 1.0 (Mac only browser)
//      Degrades gracefully in older browsers 
//
//  
//  To customize QuickMenu Pro open this file in a simple text 
//  editor (Notepad or similar). Modify and add parameters (all 
//  customizable parameters start with 'dqm__'), save this file,
//  and open 'sample.htm' in a browser to view your menu. View
//  the source for sample.htm for information on positioning your
//  menu within any web page.
//
//  QuickMenu conditionally loads the necessary JavaScript
//  files (.js) depending on the browser and platform the user
//  is viewing the menu on. The total file size for each
//  browser / platform scenario is no larger than 12K - 15K.
//
//  This sample data file contains comments and help information
//  to assist in the initial customization of your drop down 
//  menu. If you base your implementation on this documented template
//  we recommend the removal of the comments before using on the web, as 
//  to optimize the overall file size and load time of the menu for 
//  the end user.  With the comments removed this sample data files
//  size may be reduced by as much as 50%. Note: To simplify comment 
//  removal there is a uncommented version of this sample template
//  offered in the 'samples' folder.
//
//
//  NOTE: Parameters prefixed with '//' are commented out,
//        delete the '//' to activate the parameter. 
//
//        Commenting out required parameters will cause errors.
//
//        Text values, except TRUE and FALSE statements, must be
//        enclosed by double quotes (").
//  
//        Each parameter value should appear on its own line.
//
//        This data file may also be placed within your HTML page
//        by enclosing between JavaScript tags.
//
//        Due to browser limitations, DHTML menus will not appear
//        on top of Flash objects (unless the flash objects 'wmode'
//        parameter is set to transparent, however this may be buggy), 
//        across frames, or over certain form field elements. A hide 
//        and show workaround for form fields is included with this menu
//        (see the FAQ for additional information).         
//
//
//
//
//
//  Indexing Help... 
// 
//
//  ---- (X = index number) ----------
//
//  Most customizable options for the sub menus and main menu items contain
//  a default parameter setting which may be overridden with a specific parameter
//  setting.  The default settings typically apply to all the sub menu items
//  or main menu items, such as text color or font size.  When available
//  (indicated in the documentation or within this sample data file) any number
//  of specific settings may be used to uniquely format any sub menu item or main
//  menu item.  This option is noted within this document by appending an 'X' to
//  the parameter name itself.  To activate the specific parameter replace the
//  'X' with the index number of the main menu item or sub menu item you wish
//  to specifically set, this will override any default setting for the item.
//
//  ---- (Main Menu Indexing) --------
//
//  See the main menu items sub section for a working example of creating your main
//  menu bar using QuickMenu's indexing scheme.  For the main menu items its very
//  straight forward, start with 0 for the first item, 1 is your second item, 2
//  the third...  add as many as you like.
//
//  ---- (Sub Menu Indexing) --------
//
//  The sub menu indexing scheme works the same way as the main menu's, with an added
//  branching capability.  The underscore character '_' separates each sub menu level.
//  Using the example '2_0' 2 references the third main menu item (remember all indexing
//  starts at 0) the 0 references the first sub menu item associated with this third main 
//  menu.  Sub menu levels may be branched indefinitely by continually appending an
//  underscore at the desired point and starting the new sub menus item indexing with 0.
//  See the 'sub menu structure and text' sub section for a working sample.
//
//  ---- (Bullet and Icon Image Library Indexing) --------
//
//  Infinite bullet and icon images may be defined and associated with any number of sub 
//  menus and main menu items.  Indexing works the same as the main menu items
//  for both types of images and starts at 0.  A special parameter is then utilized within
//  the sub menu item customization section or main menu item customization section to reference 
//  any defined bullet or icon parameter for display with that item. 





/**********************************************************************************************
**********************************************************************************************

                              Bullet and Icon Image Library  

**********************************************************************************************
**********************************************************************************************/



/*-------------------------------------------
Bullet and Icon image library - Unlimited bullet
or icon images may be defined below and then associated
with any sub menu items within the 'Sub Menu Structure 
and Text' section of this data file.  Relative 
positioned icon images may also be associated
with any main menu item in the 'main menu items' section.
--------------------------------------------*/


    //Relative positioned icon images (flow with main menu or sub item text)

	dqm__icon_image0 = "images/bullet.gif"
	dqm__icon_rollover0 = "images/bullet_hl.gif"
	dqm__icon_image_wh0 = "13,8"

	

    //Absolute positioned icon images (coordinate positioned, sub menus only)

	dqm__2nd_icon_image0 = "images/arrow.gif"
	dqm__2nd_icon_rollover0 = "images/arrow.gif"
	dqm__2nd_icon_image_wh0 = "13,10"
	dqm__2nd_icon_image_xy0 = "0,4"





/**********************************************************************************************
**********************************************************************************************

                              Main Menu Settings  

**********************************************************************************************
**********************************************************************************************/



/*---------------------------------------------
Main Item Widths and Heights
-----------------------------------------------*/

	
	dqm__main_width = 90			//default main item widths
	dqm__main_height = 22			//default main item heights
	
	//dqm__main_widthX			//specific main item widths
	//dqm__main_heightX			//specific main item heights


	dqm__main_width0 = 70;
	dqm__main_width1 = 90;
	dqm__main_width2 = 100;
	dqm__main_width3 = 80;
	dqm__main_width4 = 120;
	dqm__main_width5 = 120; 
	dqm__main_width6 = 140;
	dqm__main_width7 = 50;
	dqm__main_width8 = 80;

/*---------------------------------------------
Main Menu Borders Dividers and Layout
-----------------------------------------------*/


	dqm__main_horizontal =true		//align menu bar horizontally or vertically

	dqm__main_border_width = 1;		//the thickness of the border in pixels, 0 = no border
	dqm__main_border_color = "#000000"	//HEX color or set to 'transparent'


	dqm__main_use_dividers = true		//When true the item gap setting is ignored
						//and the border width and color are used to
						//separate each main menu item.
						
							
	dqm__main_item_gap = 5			//the gap between main menu items in pixels
	

	dqm__align_items_bottom_and_right = true	//align items of different size to the bottom
							//or right edge of the largest main menu item
							//depending on the horizontal or vertical layout
							//of the main menu bar - false aligns items to
							//the top and left

/*---------------------------------------------
Menu Item Background and Text Colors
-----------------------------------------------*/


	dqm__main_bgcolor = "#8fbc8b"		//default color for all items, HEX or 'transparent'
	dqm__main_bgcolorX = "#eeeeee"		//specific menu item color, HEX or 'transparent'
	
	dqm__main_hl_bgcolor = "#2e8b57"	//HEX color value or set to 'transparent'
	dqm__main_hl_bgcolorX = "#ffd700"

	dqm__main_textcolor = "#ffffff"
	dqm__main_textcolorX = "#111111"

	dqm__main_hl_textcolor = "#111111"
	dqm__main_hl_textcolorX = "#ff0000"



/*---------------------------------------------
Menu Item Font Settings
-----------------------------------------------*/


	dqm__main_fontfamily = "Verdana"	//Any available system font     
	dqm__main_fontsize = 12			//Defined with pixel sizing  	
	dqm__main_textdecoration = "normal"	//set to: 'normal', or 'underline'
	dqm__main_fontweight = "bold"		//set to: 'normal', or 'bold'
	dqm__main_fontstyle = "normal"		//set to: 'normal', or 'italic' 	


	//rollover effect
	
	dqm__main_hl_textdecoration = "underline"



/*---------------------------------------------
Main Menu Margins and Text Alignment
-----------------------------------------------*/


	dqm__main_text_alignment = "left"		//set to: 'left', 'center' or 'right'
	dqm__main_margin_top = 2
	dqm__main_margin_bottom = 2
	dqm__main_margin_left = 5
	dqm__main_margin_right = 4



	//specific settings

	dqm__main_text_alignmentX = "right"		//set to: 'left', 'center' or 'right'
	dqm__main_margin_topX = 4
	dqm__main_margin_bottomX = 4



/*---------------------------------------------
Optional Status Bar Text
-----------------------------------------------*/

   
	//dqm__status_text0 = "Sample text - Main Menu Item 0"
	//dqm__status_text1 = "Sample text - Main Menu Item 1"



/*---------------------------------------------
Main Menu Items (Text, URL's, and Icons)
-----------------------------------------------*/


	
    //Main Menu 0

	dqm__maindesc0 = "Login"
	dqm__micon_index0 = 0
	dqm__url0 = "default.jsp"



    //Main Menu 1

	dqm__maindesc1 = "Indexing"
	dqm__micon_index1 = 0
	dqm__url1 = ""



    //Main Menu 2

	dqm__maindesc2 = "Retrieval"
	dqm__micon_index2 = 0
	dqm__url2 = ""



    //Main Menu 3

	dqm__maindesc3 = "Reports"
	dqm__micon_index3 = 0
	dqm__url3 = ""


    //Main Menu 4

	dqm__maindesc4 = "Import/Export"
	dqm__micon_index4 = 0
	dqm__url4 = ""

    //Main Menu 5

	dqm__maindesc5 = "Administration"
	dqm__micon_index5 = 0
	dqm__url5 = ""

    //Main Menu 6

	dqm__maindesc6 = "Working Project"
	dqm__micon_index6 = 0
	dqm__url6 = "WorkingProject.jsp"

    //Main Menu 7

	dqm__maindesc7 = "Help"
	dqm__micon_index7 = 0
	dqm__url7 = "javascript:var new_win = window.open('help/help.html');new_win.focus();"
	
    //Main Menu 8

	dqm__maindesc8 = "Logout"
	dqm__micon_index8 = 0
	dqm__url8 = "Logout.jsp"

 
/**********************************************************************************************
**********************************************************************************************

                              Sub Menu Settings

**********************************************************************************************
**********************************************************************************************/


/*-------------------------------------------
Colors, Borders, Dividers, and more...
--------------------------------------------*/


	dqm__sub_menu_width = 130      		//default sub menu widths
	dqm__sub_xy = "0,0"            		//default sub x,y coordinates - defined relative
						//to the top-left corner of parent image or sub menu
   

	dqm__urltarget = "_self"		//default URL target: _self, _parent, _new, or "my frame name"

	dqm__border_width = 1
	dqm__divider_height = 0

	dqm__border_color = "#666666"		//Hex color or 'transparent'
	dqm__menu_bgcolor = "#8fbc8b"		//Hex color or 'transparent'
	dqm__hl_bgcolor = "#2e8b57"		

	dqm__mouse_off_delay = 150		//defined in milliseconds (activated after mouse stops)
	dqm__nn4_mouse_off_delay = 500		//defined in milliseconds (activated after leaving sub)



/*-------------------------------------------
Font settings and margins
--------------------------------------------*/
   

    //Font settings

	dqm__textcolor = "#ffffff"
	dqm__fontfamily = "Verdana"		//Any available system font     
	dqm__fontsize = 10			//Defined with pixel sizing  	
	dqm__fontsize_ie4 = 9			//Defined with point sizing
	dqm__textdecoration = "normal"		//set to: 'normal', or 'underline'
	dqm__fontweight = "bold"		//set to: 'normal', or 'bold'
	dqm__fontstyle = "normal"		//set to: 'normal', or 'italic' 	


    //Rollover font settings

	dqm__hl_textcolor = "#000000"
	dqm__hl_textdecoration = "underline"	//set to: 'normal', or 'underline'



    //Margins and text alignment

	dqm__text_alignment = "left"		//set to: 'left', 'center' or 'right'
	dqm__margin_top = 2
	dqm__margin_bottom = 3
	dqm__margin_left = 5
	dqm__margin_right = 4



/*---------------------------------------------
Optional Status Bar Text
-----------------------------------------------*/


	dqm__show_urls_statusbar = false

	//dqm__status_text1_0 = "Sample text - Main Menu Item 1, Sub Item 0"	
	//dqm__status_text1_0 = "Sample text - Main Menu Item 1, Sub Item 1"	



/*-------------------------------------------
Internet Explorer Transition Effects
--------------------------------------------*/


    //Options include - none | fade | pixelate |iris | slide | gradientwipe | checkerboard | radialwipe | randombars | randomdissolve |stretch

	dqm__sub_menu_effect = "fade"
	dqm__sub_item_effect = "fade"


    //Define the effect duration in seconds below.
   
	dqm__sub_menu_effect_duration = .4
	dqm__sub_item_effect_duration = .4


    //Specific settings for various transitions.

	dqm__effect_pixelate_maxsqare = 25
	dqm__effect_iris_irisstyle = "CIRCLE"		//CROSS, CIRCLE, PLUS, SQUARE, or STAR
	dqm__effect_checkerboard_squaresx = 14
	dqm__effect_checkerboard_squaresY = 14
	dqm__effect_checkerboard_direction = "RIGHT"	//UP, DOWN, LEFT, RIGHT


    //Opacity and drop shadows.

	dqm__sub_menu_opacity = 100			//1 to 100
	dqm__dropshadow_color = "none"			//Hex color value or 'none'
	dqm__dropshadow_offx = 5			//drop shadow width
	dqm__dropshadow_offy = 5			//drop shadow height



/*-------------------------------------------
Browser Bug fixes and Workarounds
--------------------------------------------*/


    //Mac offset fixes, adjust until sub menus position correctly.
   
	dqm__os9_ie5mac_offset_x = 5
	dqm__os9_ie5mac_offset_Y = 10

	dqm__osx_ie5mac_offset_x = 5
	dqm__osx_ie5mac_offset_Y = 10

	dqm__ie4mac_offset_x = -10
	dqm__ie4mac_offset_Y = -45


    //Mac offset fixes, adjust until main menu items line up correctly

	dqm__mainitems_os9_ie5mac_offset_x = 10
	dqm__mainitems_os9_ie5mac_offset_y = 15

	dqm__mainitems_osx_ie5mac_offset_x = 10
	dqm__mainitems_osx_ie5mac_offset_y = 15



    //Netscape 4 resize bug workaround.

	dqm__nn4_reaload_after_resize = true
	dqm__nn4_resize_prompt_user = false
	dqm__nn4_resize_prompt_message = "To reinitialize the navigation menu please click the 'Reload' button."
   

    //Opera 5 & 6, set to true if the menu is the only item on the HTML page.

	dqm__use_opera_div_detect_fix = true


    //Pre-defined sub menu item heights for the Espial Escape browser.

	dqm__escape_item_height = 20
	dqm__escape_item_height0_0 = 70
	dqm__escape_item_height0_1 = 70


/*---------------------------------------------
Exposed menu events
----------------------------------------------*/


    //Reference additional onload statements here.

	//dqm__onload_code = "alert('custom function - onload')"


    //The 'X' indicates the index number of the sub menu group.
    //The 'X_X' indicates the index number of the sub menu item.

	dqm__showmenu_codeX = "status = 'custom show menu function call - menu0'"
	dqm__hidemenu_codeX = "status = 'custom hide menu function call - menu0'"
	dqm__clickitem_codeX_X = "alert('custom Function - Menu Item 0_0')"



/*---------------------------------------------
Specific Sub Menu Settings
----------------------------------------------*/


    //The following settings may be defined for specific sub menu groups.
    //The 'X' represents the index number of the sub menu group.

	dqm__border_widthX = 10;
	dqm__divider_heightX = 5;		
	dqm__border_colorX = "#0000ff";     
	dqm__menu_bgcolorX = "#ff0000"
	dqm__hl_bgcolorX = "#00ff00"
	dqm__hl_textcolorX = "#ff0000"
	dqm__text_alignmentX = "left"


    //The following settings may be defined for specific sub menu items.
    //The 'X_X' represents the index number of the sub menu item.

	dqm__hl_subdescX_X = "custom highlight text"
	dqm__urltargetX_X = "_new"




/*---------------------------------------------
Specific Sub Menu Settings
----------------------------------------------*/
  


    //Sub Menu 0 - Login

	/*dqm__sub_xy0 = "-100,20"
	dqm__sub_menu_width0 = 175

	dqm__subdesc0_0 = "<font color='#000000'><u>Login</u></font><br>Open sample_settings.js in a text editor to modify the menus settings."
	dqm__subdesc0_1 = "<font color='#000000'><u>Indexing</u></font><br>View the source of this HTML page for additional help with page integration."

	dqm__icon_index0_0 = 0
	dqm__icon_index0_1 = 0

	dqm__url0_0 = "documents/sample_link.htm"
	dqm__url0_1 = "documents/sample_link.htm"*/



    //Sub Menu 1 - Indexing

	dqm__sub_xy1 = "-100,20"
	dqm__sub_menu_width1 = 180

	dqm__subdesc1_0 = "Project"
	dqm__subdesc1_1 = "Container"
	dqm__subdesc1_2 = "Sample"
	dqm__subdesc1_3 = "Project Test"
	dqm__subdesc1_4 = "Run"
	dqm__subdesc1_5 = "New test plate(96)"
	dqm__subdesc1_6 = "New test plate(384)"
	dqm__subdesc1_7 = "Copy stock plate"
	dqm__subdesc1_8 = "New Test Plate by exsiting samples"
	
	dqm__icon_index1_0 = 0
	dqm__icon_index1_1 = 0
	dqm__icon_index1_2 = 0
	dqm__icon_index1_3 = 0
	dqm__icon_index1_4 = 0
	dqm__icon_index1_5 = 0
	dqm__icon_index1_6 = 0
	dqm__icon_index1_7 = 0
	dqm__icon_index1_8 = 0

	dqm__2nd_icon_index1_0 = 0
	dqm__2nd_icon_index1_1 = 0
	dqm__2nd_icon_index1_2 = 0
	dqm__2nd_icon_index1_3 = 0
	dqm__2nd_icon_index1_4 = 0
     
	dqm__url1_5 = "Generate96WellPlate.jsp"
	dqm__url1_6 = "Generate384WellPlate.jsp"
	dqm__url1_7 = "CopyPlate.jsp"
	dqm__url1_8 = "GeneratedByExistingSamples.jsp"

    //Sub Menu 1_0 - Projects

	dqm__sub_xy1_0 = "-4,2"
	dqm__sub_menu_width1_0 = 120

	dqm__subdesc1_0_0 = "List"
	dqm__subdesc1_0_1 = "Create"
	dqm__subdesc1_0_2 = "Update/Delete"
	
	dqm__icon_index1_0_0 = 0
	dqm__icon_index1_0_1 = 0
	dqm__icon_index1_0_2 = 0
	
	dqm__url1_0_0 = "listProject.jsp?menuChoice=query"
	dqm__url1_0_1 = "createProject.jsp"
	dqm__url1_0_2 = "listProject.jsp?menuChoice=UpdateDelete"



    //Sub Menu 1_1 - Container

	dqm__sub_xy1_1 = "-4,2"
	dqm__sub_menu_width1_1 = 200

	dqm__subdesc1_1_0 = "List"
	dqm__subdesc1_1_1 = "Create 96 Stock Plate"
	dqm__subdesc1_1_2 = "Create Box"
	dqm__subdesc1_1_3 = "Create Tube"
	dqm__subdesc1_1_4 = "Create 384 Stock Plate"
	dqm__subdesc1_1_5 = "Update/Delete"
	dqm__subdesc1_1_6 = "Change Status into Used_up"
	
	dqm__icon_index1_1_0 = 0
	dqm__icon_index1_1_1 = 0
	dqm__icon_index1_1_2 = 0
	dqm__icon_index1_1_3 = 0
	dqm__icon_index1_1_4 = 0
	dqm__icon_index1_1_5 = 0
	dqm__icon_index1_1_6 = 0


	dqm__url1_1_0 = "listContainer.jsp?menuChoice=query"
	dqm__url1_1_1 = "createContainer.jsp?ctid=2"
	dqm__url1_1_2 = "createContainer.jsp?ctid=7"
	dqm__url1_1_3 = "createTube.jsp?"
	dqm__url1_1_4 = "createContainer.jsp?ctid=6"
	dqm__url1_1_5 = "listContainer.jsp?menuChoice=UpdateDelete"
	dqm__url1_1_6 = "ContainerUsedup.jsp"

   //Sub Menu 1_2 - Samples

	dqm__sub_xy1_2 = "-4,2"
	dqm__sub_menu_width1_2 = 180

	dqm__subdesc1_2_0 = "List Container Samples"
	dqm__subdesc1_2_1 = "Create"
	dqm__subdesc1_2_2 = "Update/Delete"
	
	dqm__icon_index1_2_0 = 0
	dqm__icon_index1_2_1 = 0
	dqm__icon_index1_2_2 = 0
	
	dqm__url1_2_0 = "ListContainerSamples4Edit.jsp?menuChoice=query"
	dqm__url1_2_1 = "createSample.jsp"
	dqm__url1_2_2 = "ListContainerSamples4Edit.jsp?menuChoice=UpdateDelete"

   //Sub Menu 1_3 - Project Test

	dqm__sub_xy1_3 = "-4,2"
	dqm__sub_menu_width1_3 = 180

	dqm__subdesc1_3_0 = "List Tests of project"
	dqm__subdesc1_3_1 = "Add existing test to project"
	dqm__subdesc1_3_2 = "Update/Delete"
	
	dqm__icon_index1_3_0 = 0
	dqm__icon_index1_3_1 = 0
	dqm__icon_index1_3_2 = 0
	dqm__url1_3_0 = "ProjectTestList.jsp?menuChoice=query"
	dqm__url1_3_1 = "ProjectTestCreate.jsp"
	dqm__url1_3_2 = "ProjectTestList.jsp?menuChoice=UpdateDelete"
	

   //Sub Menu 1_3 - Run

	dqm__sub_xy1_4 = "-4,2"
	dqm__sub_menu_width1_4 = 200

	dqm__subdesc1_4_0 = "List Run(tests of container)"
	dqm__subdesc1_4_1 = "Add a test to run"
	dqm__subdesc1_4_2 = "Update/Delete"
	
	dqm__icon_index1_4_0 = 0
	dqm__icon_index1_4_1 = 0
	dqm__icon_index1_4_2 = 0
	dqm__url1_4_0 = "RunList.jsp?menuChoice=query"
	dqm__url1_4_1 = "RunCreate.jsp"
	dqm__url1_4_2 = "RunList.jsp?menuChoice=UpdateDelete"
  
    //Sub Menu 2 - Retrieval

	dqm__sub_xy2 = "-100,20"
	dqm__sub_menu_width2 = 160

	dqm__subdesc2_0 = "Container Hierarchy" 
	dqm__subdesc2_1 = "Container Details"
	dqm__subdesc2_2 = "Container Location"
	dqm__subdesc2_3 = "Result By AccessionId"
	dqm__subdesc2_4 = "Result By ExtSampleId"
	dqm__subdesc2_5 = "Result By Test"
	dqm__subdesc2_6 = "Primer Location"
	
	dqm__icon_index2_0 = 0
	dqm__icon_index2_1 = 0
	dqm__icon_index2_2 = 0
	dqm__icon_index2_3 = 0
	dqm__icon_index2_4 = 0
	dqm__icon_index2_5 = 0
	dqm__icon_index2_6 = 0
	
	dqm__url2_0 = "ContainerHierarchy.jsp"
	dqm__url2_1 = "ContainerDetails.jsp"
	dqm__url2_2 = "ContainerLocation.jsp"
	dqm__url2_3 = "SampleResultFindByAccessionId.jsp"
	dqm__url2_4 = "SampleResultFindByExtSampleId.jsp"
	dqm__url2_5 = "SampleResultFindByTestName.jsp"
	dqm__url2_6 = "PrimerLocation.jsp"



    //Sub Menu 3 - reports

	dqm__sub_xy3 = "-100,20"
	dqm__sub_menu_width3 = 180

	dqm__subdesc3_0 = "Project Result Report"
	dqm__subdesc3_1 = "Container Result Report"
	
	dqm__icon_index3_0 = 0
	dqm__icon_index3_1 = 0
	
	dqm__url3_0 = "ProjectResultReport.jsp"
	dqm__url3_1 = "FindContainer4ReportResult.jsp"


    //Sub Menu 4 - Export/Import

	dqm__sub_xy4 = "-120,20"
	dqm__sub_menu_width4 = 210

	dqm__subdesc4_0 = "Import Samples From Text File"
	dqm__subdesc4_1 = "Export Container Samples"
	dqm__subdesc4_2 = "Import Container Results"
	dqm__subdesc4_3 = "Export Container Results"
	dqm__subdesc4_4 = "Export Project Results"
	dqm__subdesc4_5 = "Manual Call"
	
	dqm__icon_index4_0 = 0
	dqm__icon_index4_1 = 0
	dqm__icon_index4_2 = 0
	dqm__icon_index4_3 = 0
	dqm__icon_index4_4 = 0
	dqm__icon_index4_5 = 0
	
	dqm__url4_0 = "TextSampleIDsImport.jsp"
	dqm__url4_1 = "exportSample.jsp"
	dqm__url4_2 = "ImportResult.jsp"
	dqm__url4_3 = "ExportResult.jsp"
	dqm__url4_4 = "ProjectResultExport.jsp"
	dqm__url4_5 = "Find4EnterMutation.jsp"
	
	
    //Sub Menu 5 - Administration

	dqm__sub_xy5 = "-100,20"
	dqm__sub_menu_width5 = 140

	dqm__subdesc5_0 = "Test"
	dqm__subdesc5_1 = "Test Type"
	dqm__subdesc5_2 = "Primer"
	dqm__subdesc5_3 = "Reserved"
	dqm__subdesc5_4 = "Instrument"
	dqm__subdesc5_5 = "Reagent"
	dqm__subdesc5_6 = "Container Type"
	dqm__subdesc5_7 = "Sample Type"
	dqm__subdesc5_8 = "Investigator"
	dqm__subdesc5_9 = "Location"
	
	dqm__subdesc5_10 = "User Role"
	dqm__subdesc5_11 = "User"
	dqm__subdesc5_12 = "User Project"
	dqm__subdesc5_13 = "Box for Primer"
	
	dqm__icon_index5_0 = 0
	dqm__icon_index5_1 = 0
	dqm__icon_index5_2 = 0
	dqm__icon_index5_3 = 0
	dqm__icon_index5_4 = 0
	dqm__icon_index5_5 = 0
	dqm__icon_index5_6 = 0
	dqm__icon_index5_7 = 0
	dqm__icon_index5_8 = 0
	dqm__icon_index5_9 = 0
	dqm__icon_index5_10 = 0
	dqm__icon_index5_11 = 0
	dqm__icon_index5_12 = 0
	dqm__icon_index5_13 = 0

	dqm__2nd_icon_index5_0 = 0
	dqm__2nd_icon_index5_1 = 0
	dqm__2nd_icon_index5_2 = 0
	dqm__2nd_icon_index5_3 = 0
	dqm__2nd_icon_index5_4 = 0
	dqm__2nd_icon_index5_5 = 0
	dqm__2nd_icon_index5_6 = 0
	dqm__2nd_icon_index5_7 = 0
	dqm__2nd_icon_index5_8 = 0
	dqm__2nd_icon_index5_9 = 0
	dqm__2nd_icon_index5_10 = 0
	dqm__2nd_icon_index5_11 = 0
	dqm__2nd_icon_index5_12 = 0
	dqm__2nd_icon_index5_13 = 0
    

    //Sub Menu 5_0 - Test

	dqm__sub_xy5_0 = "-4,2"
	dqm__sub_menu_width5_0 = 110

	dqm__subdesc5_0_0 = "List"
	dqm__subdesc5_0_1 = "Create"
	dqm__subdesc5_0_2 = "Update/Delete"
	dqm__subdesc5_0_3 = "Display All"
	
	dqm__icon_index5_0_0 = 0
	dqm__icon_index5_0_1 = 0
	dqm__icon_index5_0_2 = 0
	dqm__icon_index5_0_3 = 0
	
	dqm__url5_0_0 = "TestList.jsp?menuChoice=query"
	dqm__url5_0_1 = "TestCreate.jsp"
	dqm__url5_0_2 = "TestList.jsp?menuChoice=UpdateDelete"
	dqm__url5_0_3 = "TestDisplayAll.jsp?menuChoice=query"



    //Sub Menu 5_1 - Test Type

	dqm__sub_xy5_1 = "-4,2"
	dqm__sub_menu_width5_1 = 110

	dqm__subdesc5_1_0 = "List"
	dqm__subdesc5_1_1 = "Create"
	dqm__subdesc5_1_2 = "Update/Delete"
	
	dqm__icon_index5_1_0 = 0
	dqm__icon_index5_1_1 = 0
	dqm__icon_index5_1_2 = 0
	
	dqm__url5_1_0 = "TestTypeList.jsp?menuChoice=query"
	dqm__url5_1_1 = "TestTypeCreate.jsp"
	dqm__url5_1_2 = "TestTypeList.jsp?menuChoice=UpdateDelete"

   //Sub Menu 5_2 - Primer

	dqm__sub_xy5_2 = "-4,2"
	dqm__sub_menu_width5_2 = 120

	dqm__subdesc5_2_0 = "List"
	dqm__subdesc5_2_1 = "Create"
	dqm__subdesc5_2_2 = "Update/Delete"
	dqm__subdesc5_2_3 = "Release Primer"
	
	dqm__icon_index5_2_0 = 0
	dqm__icon_index5_2_1 = 0
	dqm__icon_index5_2_2 = 0
	dqm__icon_index5_2_3 = 0
	
	dqm__url5_2_0 = "PrimerList.jsp?menuChoice=query"
	dqm__url5_2_1 = "PrimerCreate.jsp"
	dqm__url5_2_2 = "PrimerList.jsp?menuChoice=UpdateDelete"
	dqm__url5_2_3 = "PrimerList4Release.jsp"

   //Sub Menu 5_3 - Reserved

	dqm__sub_xy5_3 = "-4,2"
	dqm__sub_menu_width5_3 = 110

	dqm__subdesc5_3_0 = "List"
	dqm__subdesc5_3_1 = "Create"
	dqm__subdesc5_3_2 = "Update/Delete"
	
	dqm__icon_index5_3_0 = 0
	dqm__icon_index5_3_1 = 0
	dqm__icon_index5_3_2 = 0
	
//	dqm__url5_3_0 = "PrimerSetList.jsp?menuChoice=query"
//	dqm__url5_3_1 = "PrimerSetCreate.jsp"
//	dqm__url5_3_2 = "PrimerSetList.jsp?menuChoice=UpdateDelete"

   //Sub Menu 5_4 - Instrument

	dqm__sub_xy5_4 = "-4,2"
	dqm__sub_menu_width5_4 = 110

	dqm__subdesc5_4_0 = "List"
	dqm__subdesc5_4_1 = "Create"
	dqm__subdesc5_4_2 = "Update/Delete"
	
	dqm__icon_index5_4_0 = 0
	dqm__icon_index5_4_1 = 0
	dqm__icon_index5_4_2 = 0
	
	dqm__url5_4_0 = "InstrumentList.jsp?menuChoice=query"
	dqm__url5_4_1 = "InstrumentCreate.jsp"
	dqm__url5_4_2 = "InstrumentList.jsp?menuChoice=UpdateDelete"
	
   //Sub Menu 5_5 - Reagent

	dqm__sub_xy5_5 = "-4,2"
	dqm__sub_menu_width5_5 = 120

	dqm__subdesc5_5_0 = "List"
	dqm__subdesc5_5_1 = "Create"
	dqm__subdesc5_5_2 = "Update/Delete"

	dqm__icon_index5_5_0 = 0
	dqm__icon_index5_5_1 = 0
	dqm__icon_index5_5_2 = 0

	dqm__url5_5_0 = "ReagentList.jsp?menuChoice=query"
	dqm__url5_5_1 = "ReagentCreate.jsp"
	dqm__url5_5_2 = "ReagentList.jsp?menuChoice=UpdateDelete"

   //Sub Menu 5_6 - Container Type

	dqm__sub_xy5_6 = "-4,2"
	dqm__sub_menu_width5_6 = 120

	dqm__subdesc5_6_0 = "List"
	dqm__subdesc5_6_1 = "Create"
	dqm__subdesc5_6_2 = "Update/Delete"

	dqm__icon_index5_6_0 = 0
	dqm__icon_index5_6_1 = 0
	dqm__icon_index5_6_2 = 0

	dqm__url5_6_0 = "ContainerTypeList.jsp?menuChoice=query"
	dqm__url5_6_1 = "ContainerTypeCreate.jsp"
	dqm__url5_6_2 = "ContainerTypeList.jsp?menuChoice=UpdateDelete"


   //Sub Menu 5_7 - Sample Type

	dqm__sub_xy5_7 = "-4,2"
	dqm__sub_menu_width5_7 = 110

	dqm__subdesc5_7_0 = "List"
	dqm__subdesc5_7_1 = "Create"
	dqm__subdesc5_7_2 = "Update/Delete"
	
	dqm__icon_index5_7_0 = 0
	dqm__icon_index5_7_1 = 0
	dqm__icon_index5_7_2 = 0
	
	dqm__url5_7_0 = "SampleTypeList.jsp?menuChoice=query"
	dqm__url5_7_1 = "SampleTypeCreate.jsp"
	dqm__url5_7_2 = "SampleTypeList.jsp?menuChoice=Update/Delete"

   //Sub Menu 5_8 - Investigator

	dqm__sub_xy5_8 = "-4,2"
	dqm__sub_menu_width5_8 = 110

	dqm__subdesc5_8_0 = "List"
	dqm__subdesc5_8_1 = "Create"
	dqm__subdesc5_8_2 = "Update/Delete"
	
	dqm__icon_index5_8_0 = 0
	dqm__icon_index5_8_1 = 0
	dqm__icon_index5_8_2 = 0
	
	dqm__url5_8_0 = "InvestigatorList.jsp?menuChoice=query"
	dqm__url5_8_1 = "InvestigatorCreate.jsp"
	dqm__url5_8_2 = "InvestigatorList.jsp?menuChoice=Update/Delete"

   //Sub Menu 5_9 - Location

	dqm__sub_xy5_9 = "-4,2"
	dqm__sub_menu_width5_9 = 110

	dqm__subdesc5_9_0 = "List"
	dqm__subdesc5_9_1 = "Create"
	dqm__subdesc5_9_2 = "Update/Delete"
	
	dqm__icon_index5_9_0 = 0
	dqm__icon_index5_9_1 = 0
	dqm__icon_index5_9_2 = 0
	
	dqm__url5_9_0 = "LocationList.jsp?menuChoice=query"
	dqm__url5_9_1 = "LocationCreate.jsp"
	dqm__url5_9_2 = "LocationList.jsp?menuChoice=UpdateDelete"

  //Sub Menu 5_10 - User Role

	dqm__sub_xy5_10 = "-4,2"
	dqm__sub_menu_width5_10 = 110

	dqm__subdesc5_10_0 = "List"
	dqm__subdesc5_10_1 = "Create"
	dqm__subdesc5_10_2 = "Update/Delete"
	
	dqm__icon_index5_10_0 = 0
	dqm__icon_index5_10_1 = 0
	dqm__icon_index5_10_2 = 0
	
	dqm__url5_10_0 = "RoleList.jsp?menuChoice=query"
	dqm__url5_10_1 = "RoleCreate.jsp"
	dqm__url5_10_2 = "RoleList.jsp?menuChoice=UpdateDelete"
 
 //Sub Menu 5_11 - User 

	dqm__sub_xy5_11 = "-4,2"
	dqm__sub_menu_width5 = 120

	dqm__subdesc5_11_0 = "List"
	dqm__subdesc5_11_1 = "Create"
	dqm__subdesc5_11_2 = "Update/Delete"
	dqm__subdesc5_11_3 = "Change Password"
	
	dqm__icon_index5_11_0 = 0
	dqm__icon_index5_11_1 = 0
	dqm__icon_index5_11_2 = 0
	dqm__icon_index5_11_3 = 0
	
	dqm__url5_11_0 = "PersonnelList.jsp?menuChoice=query"
	dqm__url5_11_1 = "PersonnelCreate.jsp"
	dqm__url5_11_2 = "PersonnelList.jsp?menuChoice=UpdateDelete"
        dqm__url5_11_3 = "PersonnelChangePassword.jsp"
 

	
   //Sub Menu 5_12 - User Project

	dqm__sub_xy5_12 = "-4,2"
	dqm__sub_menu_width5_12 = 110

	dqm__subdesc5_12_0 = "List"
	dqm__subdesc5_12_1 = "Create"
	dqm__subdesc5_12_2 = "Update/Delete"
	dqm__subdesc5_12_3 = "Display"
	
	dqm__icon_index5_12_0 = 0
	dqm__icon_index5_12_1 = 0
	dqm__icon_index5_12_2 = 0
	dqm__icon_index5_12_3 = 0
	
	dqm__url5_12_0 = "ProjectPersonnelList.jsp?menuChoice=query"
	dqm__url5_12_1 = "ProjectPersonnelCreate.jsp"
	dqm__url5_12_2 = "ProjectPersonnelList.jsp?menuChoice=UpdateDelete"
	dqm__url5_12_3 = "PersonnelProjectChoose.jsp"
 
  
     //Sub Menu 5_13 - Box for Primer
  
  	dqm__sub_xy5_13 = "-4,2"
  	dqm__sub_menu_width5_13 = 180
  
  	dqm__subdesc5_13_0 = "List"
  	dqm__subdesc5_13_1 = "Create"
  	dqm__subdesc5_13_2 = "Change Location/Delete"
  	
  	dqm__icon_index5_13_0 = 0
  	dqm__icon_index5_13_1 = 0
  	dqm__icon_index5_13_2 = 0
  	
   	dqm__url5_13_0 = "BoxList.jsp?menuChoice=query"
  	dqm__url5_13_1 = "BoxCreate.jsp"
  	dqm__url5_13_2 = "BoxList.jsp?menuChoice=UpdateDelete"
   
   