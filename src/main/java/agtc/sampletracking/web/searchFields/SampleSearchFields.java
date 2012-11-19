/*
 * Created on Nov 2, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.searchFields;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.*;
import org.hibernate.*;
import agtc.sampletracking.web.command.*;
import java.sql.*;



/**
 * @author Gloria Deng
 *
 * This class provides searchfields for Samples.jsp search field, THe Samples.jsp loop through all
 * the list and display them in a drop down list, the SampleSearchController will get the value from
 * the response and pass them to SampleManager, which will manipulate the sql string, then call the
 * SampleDAO to make the query.
 */
public class SampleSearchFields extends BasicSearchFiels{
	private static Log log = LogFactory.getLog(SampleSearchFields.class);
	public SampleSearchFields(){
		super();
		loadElements();
	}

	public void loadElements(){
		add("External Id");
		add("Internal Id");
		add("Family ID");
		add("Made date");
		add("Receive date");
		add("Sample Type Suffix");
		add("Patient First Name");
		add("Patient Last Name");
		//add("Project name");
	}
	
	public static void getExpression(Criteria crt,SearchCommand searchCommand){
		log.debug("search item is " +searchCommand.getSearchItem());
	
		String field = searchCommand.getSearchField();
		String fieldExp = "";
		if(field.equals("External Id")){
			crt.createAlias("patient","patient");
			fieldExp = "patient.extSampleId";
		}else if(field.equals("Internal Id")){
			fieldExp = "patient.intSampleId";
		}else if(field.equals("Family ID")){
			crt.createAlias("patient","patient");
			fieldExp = "patient.familyId";
		}else if(field.equals("Made date")){
			String searchItemS = (String)searchCommand.getSearchItem()+ " 00:00:00.0";
			Timestamp searchItemD = Timestamp.valueOf(searchItemS);
			searchCommand.setSearchItem(searchItemD);
			fieldExp = "madeDate";
		}else if(field.equals("Receive date")){
			String searchItemS = (String)searchCommand.getSearchItem()+ " 00:00:00.0";
			Timestamp searchItemD = Timestamp.valueOf(searchItemS);
			searchCommand.setSearchItem(searchItemD);
			fieldExp = "receiveDate";
		}else if(field.equals("Sample Type Suffix")){
			crt.createAlias("sampleType","sampleType");
			fieldExp = "sampleType.suffix";
		}else if(field.equals("Patient First Name")){
			crt.createAlias("patient","patient");
			fieldExp = "patient.fname";
		}else if(field.equals("Patient Last Name")){
			crt.createAlias("patient","patient");
			fieldExp = "patient.lname";
		}
		
		addOperator(fieldExp,searchCommand,crt);
		
		crt.setFetchMode("patient",FetchMode.JOIN);
		crt.setFetchMode("sampleType",FetchMode.JOIN);
		
		
	}

}
