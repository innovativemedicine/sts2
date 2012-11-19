/*
 * Created on Apr 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.web.command.SearchCommand;
import agtc.sampletracking.web.searchFields.OperatorList;
import agtc.sampletracking.web.searchFields.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BasicSearchController extends BasicController {
	protected BasicSearchFiels searchFields;
	protected OperatorList operatorList;

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new SearchCommand();
		
	}
	
	protected List handleSubmit(HttpServletRequest request,
						java.lang.Object command,
						String entityName){
		String criteriaListName = entityName+"CriteriaList";
		String logicalListName = entityName+"LogicalList";
		
		SearchCommand criteria = (SearchCommand)command;
		String action = RequestUtils.getStringParameter(request, "action","");
	
		List searchResults = new ArrayList();
		List crtList = (List)WebUtils.getOrCreateSessionAttribute(
								request.getSession(),
								criteriaListName,
								ArrayList.class);
		
		List lgcList = (List)WebUtils.getOrCreateSessionAttribute(
								request.getSession(),
								logicalListName,
								ArrayList.class);
		String searchField = criteria.getSearchField();
		String operator = criteria.getOperator();
		if(!searchField.equals("select to search") && 
				!operator.equals("select to search")){
			crtList.add(criteria);
		}
		
		if(action.equals("SEARCH")){
			if(crtList.size()>0){
				searchResults = performSearch(crtList,lgcList);
			}
			crtList = new ArrayList();
			lgcList = new ArrayList();
		}else{
			if(action.equals("AND")){
				lgcList.add("AND");	
			}else if(action.equals("OR")){
				lgcList.add("OR");
			}else if(action.equals("PREVIEW")){
			}else{// only left choise is clear, will clean all criteria in the session
				crtList = new ArrayList();
				lgcList = new ArrayList();
			}
			
				
		}
		WebUtils.setSessionAttribute(request,criteriaListName,crtList);
		WebUtils.setSessionAttribute(request,logicalListName,lgcList);
		return searchResults;
	}
	
	protected void putMessage(javax.servlet.http.HttpServletRequest request,Map models){
		String action = RequestUtils.getStringParameter(request, "action","");
		if(action.equals("SEARCH")){
			String message = "No record found";
			models.put("message",message);
		}
	}
	
	protected abstract List performSearch(List crtList,List lgcList);

	protected void referenceData(javax.servlet.http.HttpServletRequest request,
							Map models,String entityName)
	   						throws java.lang.Exception
	{
		String message = RequestUtils.getStringParameter(request, "message","");
		String criteriaListName = entityName+"CriteriaList";
		String logicalListName = entityName+"LogicalList";
		List crtList = (List)WebUtils.getOrCreateSessionAttribute(
								request.getSession(),
								criteriaListName,
								ArrayList.class);
		List lgcList = (List)WebUtils.getOrCreateSessionAttribute(
								request.getSession(),
								logicalListName,
								ArrayList.class);
		
		String currentCriteria = parseCriteria(crtList,lgcList);
		
		models.put("currentCriteria",currentCriteria);
		models.put("searchFields",searchFields);
		models.put("operatorList",operatorList);
	
		if(!message.equals("")){
		models.put("message",message);
		}
		
	
	}
	
	protected String parseCriteria(List crtList,List lgcList){
		String result = "";
		if(crtList.size()>0){
			
			for(int i= 0;i<crtList.size();i++){
			
				SearchCommand sc = (SearchCommand)crtList.get(i);
				result += sc.toString() + "<br>";
				if(i<=(lgcList.size()-1)){
					String operator = (String)lgcList.get(i);
					result += operator + " ";
				}
			
			}
			
		}
		return result;
	}
	
	
	/**
	 * @return Returns the operatorList.
	 */
	public OperatorList getOperatorList() {
		return operatorList;
	}
	/**
	 * @param operatorList The operatorList to set.
	 */
	public void setOperatorList(OperatorList operatorList) {
		this.operatorList = operatorList;
	}
	/**
	 * @return Returns the searchFields.
	 */
	public BasicSearchFiels getSearchFields() {
		return searchFields;
	}
	/**
	 * @param searchFields The searchFields to set.
	 */
	public void setSearchFields(BasicSearchFiels searchFields) {
		this.searchFields = searchFields;
	}
}
