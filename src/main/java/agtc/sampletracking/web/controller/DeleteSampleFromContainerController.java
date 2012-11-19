/*
 * Created on Jan 21, 2005
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

import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.model.*;


/**
 * @author Glroia
 *
 * This Class is for remove sample from container, not really delete the records in DB
 * just mark the operation as "O" (out), current date as operationDate, input the reason
 * the user give
 */
public class DeleteSampleFromContainerController extends BasicController {
	private Log log = LogFactory.getLog(DeleteSampleFromContainerController.class);
	
	private SampleManager sampleManager;
	private ContainerManager containerManager;
	
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public DeleteSampleFromContainerController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		int i = RequestUtils.getRequiredIntParameter(request, "sicId");
		SamplesInContainer sic = sampleManager.getSamplesInContainer(new Integer(i));
		return sic;
		
	
	}
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		SamplesInContainer sic = (SamplesInContainer) command;
		sic.setOperation("O");
		sic.setOperationDate(new Date());
		
		//Changed by Jianan Xiao 2006-01-11
		//Old version is: sampleManager.saveSamplesInContainer(sic);
		sampleManager.removeSamplesInContainer(sic.getSicId());
		
		String message = "Have successfully removed the sample from the container !";
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message",message);
		myModel.put("containerId",sic.getContainer().getContainerId());
		return view;
	}
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
			  java.lang.Object command,Errors errors)
	   throws java.lang.Exception
	{
		
		
		Map models = new HashMap();
		
	    List allReasons = new ArrayList();
	    allReasons.add("to make transformed cell line");
	    allReasons.add("to make transformed DNA");
	    allReasons.add("Sample used up");
	    allReasons.add("Mistake");
	    allReasons.add("other");
	    
		
		models.put("allReasons",allReasons);
		
	
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
