/*
 * Created on Dec 3, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.searchFields;

import java.util.List;

import org.hibernate.Criteria;

import agtc.sampletracking.web.command.SearchCommand;


/**
 * @author Gloria Deng
 *
  This class provides searchfields for Runs.jsp search field, THe Runs.jsp loop through all
 * the list and display them in a drop down list, the RunSearchController will get the value from
 * the response and pass them to TestManager, which will manipulate the sql string, then call the
 * RunDAO to make the query.
 */
public class RunSearchFields extends BasicSearchFiels {
	public RunSearchFields(){
		super();
		loadElements();
	}

	public void loadElements(){
		add("Project Name");
		add("Date");
		add("Note");
	}
	
	public static void getExpression(Criteria crt,SearchCommand searchCommand){
		
		String field = searchCommand.getSearchField();
		String fieldExp = "";
		if(field.equals("Project Name")){
			crt.createAlias("project","project");
			fieldExp = "project.name";
		}else if(field.equals("Date")){
			fieldExp = "runDate";
		}else if(field.equals("Note")){
			fieldExp = "note";
		}
		addOperator(fieldExp,searchCommand,crt);
	
	}

}
