/*
 * Created on Jan 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.model.SamplesInContainer;


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
		int i = RequestUtils.getRequiredIntParameter(request, "sicId");
		SamplesInContainer sic = sampleManager.getSamplesInContainer(new Integer(i));
		return sic;
	}
	
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		SamplesInContainer sic = (SamplesInContainer) command;
		sic.setOperation("O");
		sic.setOperationDate(new Date());

		sampleManager.removeSamplesInContainer(sic.getSicId());
		
		String message = "Sample successfully retrieved from container.";
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("containerId",sic.getContainer().getContainerId());
		myModel.put("message",message);
		return view;
	}
	
	public ContainerManager getContainerManager() {
		return containerManager;
	}

	public void setContainerManager(ContainerManager containerManager) {
		this.containerManager = containerManager;
	}

	public SampleManager getSampleManager() {
		return sampleManager;
	}

	public void setSampleManager(SampleManager sampleManager) {
		this.sampleManager = sampleManager;
	}
}
