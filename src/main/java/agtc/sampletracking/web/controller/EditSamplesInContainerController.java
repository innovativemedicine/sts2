/*
 * Created on Jan 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.model.*;


/**
 * @author Glroia
 *
 * This Class is for 
 */
public class EditSamplesInContainerController extends BasicController {
	private Log log = LogFactory.getLog(EditSamplesInContainerController.class);
	
	private SampleManager sampleManager;
	private ContainerManager containerManager;
	
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public EditSamplesInContainerController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		int i = RequestUtils.getIntParameter(request, "sicId",-1);
		if(i!= -1){
			log.debug("the result id is " +i);
			SamplesInContainer sic = sampleManager.getSamplesInContainer(new Integer(i));
			return sic;
		}else{
			int is = RequestUtils.getIntParameter(request, "sampleId",-1);
			Sample sample = sampleManager.getSample(new Integer(is));
			SamplesInContainer sic = new SamplesInContainer();
			sic.setSample(sample);
			sic.setSicId(new Integer(-1));
			return sic;
		}
	
	}
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		SamplesInContainer sic = (SamplesInContainer) command;
		sampleManager.saveSamplesInContainer(sic);
		log.debug("success view is " + getSuccessView());
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message","Have successfully changed the container for this sample !");
		myModel.put("sampleId",sic.getSample().getSampleId());
		return view;
	}
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
			  java.lang.Object command,Errors errors)
	   throws java.lang.Exception
	{
		
		SamplesInContainer sic = (SamplesInContainer)command;
		Map models = new HashMap();
		
		String message = RequestUtils.getStringParameter(request, "message","");
		List containerList = containerManager.getAllContainers();
		Container container = sic.getContainer();
		containerList.remove(container);
		models.put("containerList",containerList);
		if(!message.equals("")){
			models.put("message",message);
		}
	
		return models;
	}
	
	
	
	/**
	 * @return Returns the containerManager.
	 */
	public ContainerManager getContainerManager() {
		return containerManager;
	}
	/**
	 * @param containerManager The containerManager to set.
	 */
	public void setContainerManager(ContainerManager containerManager) {
		this.containerManager = containerManager;
	}
	/**
	 * @return Returns the sampleManager.
	 */
	public SampleManager getSampleManager() {
		return sampleManager;
	}
	/**
	 * @param sampleManager The sampleManager to set.
	 */
	public void setSampleManager(SampleManager sampleManager) {
		this.sampleManager = sampleManager;
	}
}
