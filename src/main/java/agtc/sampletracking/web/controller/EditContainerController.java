/*
 * Created on Nov 2, 2004
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
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.*;
import agtc.sampletracking.*;
import java.util.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EditContainerController extends BasicController {
	private Log log = LogFactory.getLog(EditContainerController.class);
	private ContainerManager containerManager;
	private ProjectManager projectManager;
	private AGTCManager agtcManager;

	public EditContainerController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);

	}
	

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getproject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		int i = RequestUtils.getIntParameter(request, "containerId",-1);
		if(i==-1){
			Container container = new Container();
			container.setContainerId(new Integer(-1));
		
			int ip = RequestUtils.getIntParameter(request, "projectId",-1);
			int im = RequestUtils.getIntParameter(request, "motherContainerId",-1);
			if(ip != -1){
				container.setProject(projectManager.getProject(new Integer(ip)));
			}
			
			if(im != -1){
				container.setMotherContainer(containerManager.getContainer(new Integer(im)));
			}
			
			
			return container;
		}else{
			Container container = containerManager.getContainer(new Integer(i));
			log.debug("in formBackingObject");
			log.debug("container name is " + container.getName());
			log.debug("container containerType name is " + container.getContainerType().getName());
			
			return container;
		}
	}

	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws Exception {
		Container container = (Container) command;
		Container motherContainer = container.getMotherContainer();
		Set sics = new HashSet();
		if(motherContainer != null){
			List s = containerManager.getSamplesInContainerByContainer(motherContainer.getContainerId());
			if(s != null){
				Iterator i = s.iterator();
				while(i.hasNext()){
					SamplesInContainer sicMother = (SamplesInContainer)i.next();
					SamplesInContainer sic = new SamplesInContainer();
					sic.setSample(sicMother.getSample());
					sic.setWell(sicMother.getWell());
					sic.setSicId(new Integer(-1));
					sic.setContainer(container);
					sics.add(sic);
					
				}
			}
		}
		
		if(container.getContainerType().getName().equals("-")){
			errors.rejectValue( "containerType","error.required",new String[]{"Container Type"},"Container Type is required");
			return showForm(request, response, errors);
		}

		if(container.getLocation().getName().equals("-")){
			errors.rejectValue( "location","error.required",new String[]{"Container Type"},"Container Type is required");
			return showForm(request, response, errors);
		}

		container.setSamplesInContainers(sics);
		// delegate the update to the Business layer
		try{
			containerManager.saveContainer(container);
		}catch(Exception e){
			errors.rejectValue( "name","error.notUnique",new String[]{container.getName()},"Not unique");
			return showForm(request, response, errors);
		}
		
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message","Have successfully saved this container !");
		myModel.put("containerId",container.getContainerId());
		return view;
	}

	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
									  java.lang.Object command,Errors errors)
							   throws java.lang.Exception
	{
		Map models = new HashMap();
		Container container = (Container) command;
		
	    
	    //the allProjects, allLocations and allContainerTypes is all except the current one
		List allProjects = projectManager.getAllProjects();
		List allLocations = agtcManager.getLocations();
		List allContainerTypes = agtcManager.getContainerTypes();
		

		models.put("allLocations",allLocations);
		models.put("allContainerTypes",allContainerTypes);
		models.put("allProjects",allProjects);
	

		log.debug("referenceData is called");
		return models;
	}

	/**
	protected ModelAndView processFormSubmission(javax.servlet.http.HttpServletRequest request,
													 javax.servlet.http.HttpServletResponse response,
													 java.lang.Object command,
													 BindException errors)
											  throws java.lang.Exception
	{
		Sample sample = (Sample) command;
		log.debug(sample);
		log.debug(errors);
		return null;
	}
	*/
	
	/**
	 * @return
	 */
	public ProjectManager getProjectManager() {
		return projectManager;
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
	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}

	/**
	 * @param manager
	 */
	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}
	
	

	/**
	 * @return Returns the agtcManager.
	 */
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}
	/**
	 * @param agtcManager The agtcManager to set.
	 */
	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
}
