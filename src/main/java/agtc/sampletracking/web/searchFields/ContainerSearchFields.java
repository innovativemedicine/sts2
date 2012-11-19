/*
 * Created on Nov 2, 2004
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
  This class provides searchfields for Containers.jsp search field, THe Containers.jsp loop through all
 * the list and display them in a drop down list, the ContainerSearchController will get the value from
 * the response and pass them to ContainerManager, which will manipulate the sql string, then call the
 * ContainerDAO to make the query.
 */
public class ContainerSearchFields extends BasicSearchFiels {
	public ContainerSearchFields(){
		super();
		loadElements();
	}

	public void loadElements(){
		add("Name");
		add("External Container Id");
		add("Status");
		add("Created date");
		add("Location Name");
		add("Container Type Name");
		add("Project Name");
		add("Comments");
	}
	
	public static void getExpression(Criteria crt,SearchCommand searchCommand){
	
		
		String field = searchCommand.getSearchField();
		String fieldExp = "";
		if(field.equals("Name")){
			fieldExp = "name";
		}else if(field.equals("External Container Id")){
			fieldExp = "extContainerId";
		}else if(field.equals("Status")){
			fieldExp = "status";
		}else if(field.equals("Comments")){
			fieldExp = "comments";
		}else if(field.equals("Created date")){
			fieldExp = "createdDate";
		}else if(field.equals("Container Type Name")){
			crt.createAlias("containerType","containerType");
			fieldExp = "containerType.name";
		}else if(field.equals("Location Name")){
			crt.createAlias("location","location");
			fieldExp = "location.name";
		}else if(field.equals("Project Name")){
			crt.createAlias("project","project");
			fieldExp = "project.name";
		}
		
		addOperator(fieldExp,searchCommand,crt);
		crt.setFetchMode("containerType",FetchMode.EAGER);
		crt.setFetchMode("location",FetchMode.EAGER);
		crt.setFetchMode("project",FetchMode.EAGER);
		
		
	}

	
}
