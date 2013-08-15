/*
 * Created on Nov 2, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.SamplesInContainer;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EditContainerController extends BasicController {
	
	private ContainerManager containerManager;
	private ProjectManager projectManager;
	private AGTCManager agtcManager;

	public EditContainerController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);

	}
	

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getproject(new Integer(ServletRequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		int i = ServletRequestUtils.getIntParameter(request, "containerId",-1);
		
		if(i==-1){
			Container container = new Container();
			container.setContainerId(new Integer(-1));
		
			int ip = ServletRequestUtils.getIntParameter(request, "projectId",-1);
			int im = ServletRequestUtils.getIntParameter(request, "motherContainerId",-1);
			
			if(ip != -1){
				container.setProject(projectManager.getProject(new Integer(ip)));
			}
			
			if(im != -1){
				container.setMotherContainer(containerManager.getContainer(new Integer(im)));
			}
			
			return container;
		}else{
			Container container = containerManager.getContainer(new Integer(i));
			
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
		
		if(container.getContainerType() == null){
			String err = "Container Type is required";

			ModelAndView mav = new ModelAndView(new RedirectView("editContainer.htm"));
			mav.addObject("err", err);

			return mav;
		}

		if(container.getLocation() == null){
			String err = "Container Location is required";

			ModelAndView mav = new ModelAndView(new RedirectView("editContainer.htm"));
			mav.addObject("err", err);

			return mav;
		}

		container.setSamplesInContainers(sics);
		// delegate the update to the Business layer
		try{
			containerManager.saveContainer(container);
		}catch(Exception e){
			String err = "Container Name is not unique";

			ModelAndView mav = new ModelAndView(new RedirectView("editContainer.htm"));
			mav.addObject("err", err);

			return mav;

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
		
		String err = ServletRequestUtils.getStringParameter(request, "err", "");
		
		Container container = (Container) command;
	    
	    //the allProjects, allLocations and allContainerTypes is all except the current one
		List allProjects = projectManager.getAllProjects();
		List allLocations = agtcManager.getLocations();
		List allContainerTypes = agtcManager.getBoxTypes();

		models.put("allLocations",allLocations);
		models.put("allContainerTypes",allContainerTypes);
		models.put("allProjects",allProjects);
		models.put("err", err);

		return models;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public ContainerManager getContainerManager() {
		return containerManager;
	}

	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}
	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}
	
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
}
