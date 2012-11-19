/*
 * Created on Nov 11, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.*;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.*;
import agtc.sampletracking.bus.*;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.manager.*;
import java.util.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EditReagentController extends BasicController {

	private SampleManager sampleManager;
	private ContainerManager containerManager;
	private Log log = LogFactory.getLog(EditReagentController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public EditReagentController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
	
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		int i = RequestUtils.getIntParameter(request, "reagentId",-1);
		if(i==-1){
			Reagent reagent = new Reagent();
			reagent.setReagentId(new Integer(-1));
			int ci = RequestUtils.getRequiredIntParameter(request, "containerId");
			Container container = containerManager.getContainer(new Integer(ci));
			reagent.setContainer(container);
			return reagent;
		}else{
			Reagent reagent = sampleManager.getReagent(new Integer(i));
			return reagent;
		}
	}

	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws Exception {
		Reagent reagent = (Reagent) command;
		log.debug(reagent);
		// delegate the update to the Business layer
		try{
			sampleManager.saveReagent(reagent);
		}catch(Exception e){
			errors.rejectValue( "name","error.notUnique",new String[]{reagent.getName()},"Not unique");
			return showForm(request, response, errors);
		}
		
		log.debug("success view is " + getSuccessView());
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message","Have successfully saved this reagent !");
		myModel.put("reagentId",reagent.getReagentId());
		return view;
	}
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
										  java.lang.Object command,Errors errors)
								   throws java.lang.Exception
	{
		Map models = new HashMap();
		Reagent reagent = (Reagent) command;
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
	
		List allReagentBoxes = containerManager.getAllReagentBoxes();
	
		models.put("allReagentBoxes",allReagentBoxes);
		return models;
	}

	
	/**
	protected ModelAndView processFormSubmission(javax.servlet.http.HttpServletRequest request,
												 javax.servlet.http.HttpServletResponse response,
												 java.lang.Object command,
												 BindException errors)
										  throws java.lang.Exception
	{
		Reagent reagent = (Reagent) command;
		log.debug(reagent);
		log.debug(errors);
		return null;
	}
	*/


	/**
	 * @return
	 */
	public SampleManager getSampleManager() {
		return sampleManager;
	}

	/**
	 * @param manager
	 */
	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}

	/**
	 * @return
	 */
	public ContainerManager getContainerManager() {
		return containerManager;
	}

	/**
	 * @param manager
	 */
	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}

}
