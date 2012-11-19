/*
 * Created on Dec 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.searchFields;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

import agtc.sampletracking.web.command.SearchCommand;



/**
 * @author Gloria Deng
 *
 * This class provides searchfields for Assays.jsp search field, THe Assays.jsp loop through all
 * the list and display them in a drop down list, the AssaySearchController will get the value from
 * the response and pass them to TestManager, which will manipulate the sql string, then call the
 * AssayDAO to make the query.
 */
public class AssaySearchFields extends BasicSearchFiels {
	public AssaySearchFields(){
		super();
		loadElements();
	}

	public void loadElements(){
		add("Name");
		add("Note");
	}
	public static void getExpression(Criteria crt,SearchCommand searchCommand){

		String field = searchCommand.getSearchField();
		String fieldExp = "";
		if(field.equals("Name")){
			fieldExp = "name";
		}else if(field.equals("Note")){
			fieldExp = "note";
		}
		
		addOperator(fieldExp,searchCommand,crt);
	}

}
