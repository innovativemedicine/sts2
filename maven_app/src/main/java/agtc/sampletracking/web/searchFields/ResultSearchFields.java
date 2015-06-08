/*
 * Created on Dec 2, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.searchFields;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;

import agtc.sampletracking.web.command.SearchCommand;


/**
 * @author Gloria Deng
 *
 * This class provides searchfields for Results.jsp search field, THe Results.jsp loop through all
 * the list and display them in a drop down list, the ResultSearchController.java will get the value from
 * the response and pass them to TestManager.java, which will manipulate the sql string, then call the
 * TestDAO.java to make the query.
 */
public class ResultSearchFields extends BasicSearchFiels {
	public ResultSearchFields(){
		super();
		loadElements();
	}

	public void loadElements(){
//		add("Project Name");
		add("Internal Sample Id");
//		add("Assay Name");
		add("Run Note");
		add("Run Date");
	}
	
	public static void getExpression(Criteria crt,SearchCommand searchCommand){
		
		String field = searchCommand.getSearchField();
		String fieldExp = "";
		if(field.equals("Project Name")){
			crt.createAlias("run.project","run.project");
			fieldExp = "run.project.name";
		}else 
		if(field.equals("Run Note")){
			crt.createAlias("run","run");
			fieldExp = "run.note";
		}else if(field.equals("Run Date")){
			crt.createAlias("run","run");
			fieldExp = "run.runDate";
		}else if(field.equals("runId")){
			crt.createAlias("run","run");
			fieldExp = "run.runId";
//		}else if(field.equals("Assay Name")){
//			crt.createAlias("assay","assay");
//			fieldExp = "assay.name";
		}else if(field.equals("assayId")){
			crt.createAlias("assay","assay");
			fieldExp = "assay.assayId";
		}else if(field.equals("Internal Sample Id")){
			fieldExp = "intSampleId";
		} 
		
		addOperator(fieldExp,searchCommand,crt);
	
		crt.setFetchMode("assay",FetchMode.JOIN);
		crt.setFetchMode("run",FetchMode.JOIN);

	}

}
