/*
 * Created on Nov 2, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.*;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.web.searchFields.ContainerSearchFields;
import agtc.sampletracking.web.searchFields.OperatorList;
import agtc.sampletracking.web.searchFields.SampleSearchFields;
import agtc.sampletracking.bus.SampleListHolder;
import agtc.sampletracking.bus.manager.*;
import org.springframework.validation.BindException;
import java.util.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ContainersSearchController extends BasicSearchController {
	private ContainerManager containerManager;
	
	private Log log = LogFactory.getLog(ContainersSearchController.class);
	
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
	
		List searchResults = handleSubmit(request,command,"container");
	
		if (searchResults.size() < 1) {
			ModelAndView view = new ModelAndView(new RedirectView("containers.htm"));
			putMessage(request,view.getModel());
			return view;
		}
		
		String useFor = RequestUtils.getStringParameter(request, "useFor","");
		
		if(useFor.equals("useFor")){
			SampleListHolder plateListHolder = (SampleListHolder)WebUtils.getOrCreateSessionAttribute(request.getSession(),"plateListHolder",SampleListHolder.class);
			plateListHolder.setOriginalList(searchResults);
			ModelAndView view = new ModelAndView(new RedirectView("plateList4select.htm"));
			return view;
		}

		if (searchResults.size() > 1) {
			// multiple projects found
			return new ModelAndView("containerList", "containerList", searchResults);
		}

		// only one result
		Container container = (Container) searchResults.iterator().next();

		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("containerId",container.getContainerId());   ;
		return view;
	}
	
	protected  List performSearch(List crtList,List lgcList){
		return containerManager.searchContainer(crtList,lgcList);
	}

	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request)
							   throws java.lang.Exception
	{
		Map models = new HashMap();
		List containerList = containerManager.getAllContainers();
		
		referenceData(request,models,"container");
		models.put("containerList",containerList);
		return models;
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
