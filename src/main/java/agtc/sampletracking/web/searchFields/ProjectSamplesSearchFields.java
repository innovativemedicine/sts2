/*
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.searchFields;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;

import agtc.sampletracking.web.command.SearchCommand;


/**
 * @author Bei Jin
 *
 This class provides searchfields for ProjectSamples.jsp search field, THe ProjectSamples.jsp loop through all
 * the list and display them in a drop down list, the ProjectSamplesSearchController will get the value from
 * the response and pass them to ProjectSamplesManager, which will manipulate the sql string, then call the
 * ProjectSamplesDAO to make the query.
 */
public class ProjectSamplesSearchFields extends BasicSearchFiels {
	public ProjectSamplesSearchFields(){
		super();
		loadElements();
	}
	
	public void loadElements(){
		add("Name");
		add("Description");
		add("Status");
		add("Created date");
		add("Investigator first name");
		add("Investigator last name");
	}
	
	public static void getExpression(Criteria crt,SearchCommand searchCommand){
		
		String field = searchCommand.getSearchField();
		String fieldExp = "";
		if(field.equals("Name")){
			fieldExp = "name";
		}else if(field.equals("Description")){
			fieldExp = "description";
		}else if(field.equals("Status")){
			fieldExp = "status";
		}else if(field.equals("Created date")){
			fieldExp = "createdOn";
		}else if(field.equals("Investigator first name")){
			crt.createAlias("investigator","investigator");
			fieldExp = "investigator.name.fname";
		}else if(field.equals("Investigator last name")){
			crt.createAlias("investigator","investigator");
			fieldExp = "investigator.name.lname";
		}
	
		addOperator(fieldExp,searchCommand,crt);
	
	}
	
}
