/*
 * Created on Dec 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.manager.TestManager;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.*;


/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EditResultController extends BasicController {
	private TestManager testManager;
	private Log log = LogFactory.getLog(EditResultController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public EditResultController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		int i = RequestUtils.getRequiredIntParameter(request, "resultId");
		log.debug("the result id is " +i);
		Result result = testManager.getResult(new Integer(i));
			return result;
	
	}
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		Result result = (Result) command;
		log.debug("the note is " + result.getNote());
		testManager.saveResult4update(result);
	
		log.debug("success view is " + getSuccessView());
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		
		Map myModel = view.getModel();
		myModel.put("message","Have successfully saved the modification to this result !");
		myModel.put("resultId",result.getResultId());
		return view;
	}
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
			  java.lang.Object command,Errors errors)
	   throws java.lang.Exception
	{
		Map models = new HashMap();
		
		String message = RequestUtils.getStringParameter(request, "message","");
		ArrayList rs = (ArrayList)WebUtils.getSessionAttribute(request,"resultSearchResult");
		models.put("resultList",rs);
		if(!message.equals("")){
			models.put("message",message);
		}
	
		return models;
	}
	/**
	 * @return Returns the testManager.
	 */
	public TestManager getTestManager() {
		return testManager;
	}
	/**
	 * @param testManager The testManager to set.
	 */
	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}
}
