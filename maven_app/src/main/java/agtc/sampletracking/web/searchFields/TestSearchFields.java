/*
 * Created on Nov 3, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.searchFields;

import org.hibernate.Criteria;

import agtc.sampletracking.web.command.SearchCommand;


/**
 * @author Gloria Deng
 *
 This class provides searchfields for Tests.jsp search field, THe Tests.jsp loop through all
 * the list and display them in a drop down list, the TestSearchController will get the value from
 * the response and pass them to TestManager, which will manipulate the sql string, then call the
 * TestDAO to make the query.
 */
public class TestSearchFields extends BasicSearchFiels {
	public TestSearchFields(){
		super();
		loadElements();
	}
	
	public void loadElements(){
		add("Name");
		add("Project Name");
	}
	
	public static void getExpression(Criteria crt,SearchCommand searchCommand){
		
		String field = searchCommand.getSearchField();
		String fieldExp = "";
		if(field.equals("Project Name")){
			crt.createAlias("project","project");
			fieldExp = "project.name";
		}else if(field.equals("Name")){
			fieldExp = "name";
		}
		
		addOperator(fieldExp,searchCommand,crt);
	
	}
		
}