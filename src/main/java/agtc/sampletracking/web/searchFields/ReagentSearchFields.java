/*
 * Created on Jan 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.searchFields;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;

import agtc.sampletracking.web.command.SearchCommand;


/**
 * @author Gloria Deng
 *
 This class provides searchfields for Reagents.jsp search field, THe Reagents.jsp loop through all
 * the list and display them in a drop down list, the ReagentSearchController will get the value from
 * the response and pass them to SampleManager, which will manipulate the sql string, then call the
 * ReagentDAO to make the query.
 */
public class ReagentSearchFields extends BasicSearchFiels {
	public ReagentSearchFields(){
		super();
		loadElements();
	}
	
	public void loadElements(){
		add("Name");
		add("Sequence");
		add("Note");
		add("Container name");
	}
	
	public static void getExpression(Criteria crt,SearchCommand searchCommand){
		
		String field = searchCommand.getSearchField();
		String fieldExp = "";
		if(field.equals("Name")){
			fieldExp = "name";
		}else if(field.equals("Sequence")){
			fieldExp = "seq";
		}else if(field.equals("Note")){
			fieldExp = "note";
		}
		
		addOperator(fieldExp,searchCommand,crt);
	
	}
	
}
