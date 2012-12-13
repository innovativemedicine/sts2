/*
 * Created on 2005-09-09
 *
 * To change the temcontainer for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.Container;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.*;
import org.springframework.validation.BindException;
import org.springframework.web.util.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContainersSearchController extends BasicSearchController {
	private ContainerManager containerManager;
	private ProjectManager projectManager;
	private AGTCManager agtcManager;
	private List LProjects;
	private List LContainerTypes;

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		String action = RequestUtils.getStringParameter(request, "action", "");

		String containerIdsInTextArea = RequestUtils.getStringParameter(request,
				"containerIdsInTextArea", "");
		String containerIdFrom = RequestUtils.getStringParameter(request,
				"containerIdFrom", "");
		String containerIdTo = RequestUtils.getStringParameter(request,
				"containerIdTo", "");

		String[] containertypes = RequestUtils.getStringParameters(request,
				"containerTypeFilter");
		String[] projects = RequestUtils.getStringParameters(request,
				"projectFilter");

		List containerTypeIds = String2IntList(containertypes);
		List projectIds = String2IntList(projects);
		List<Container> searchResults = new ArrayList();
		
		if (containerIdFrom.isEmpty() && containerIdsInTextArea.isEmpty()) {
			ModelAndView mav = new ModelAndView(new RedirectView(
					"searchContainers.htm"));
			mav.addObject("message", "Please enter Container ID");
			return mav;
		}
		// Search single Container or range
		else if (!containerIdFrom.isEmpty()) {
			List<Container> simpleSearchContainers = (List<Container>) containerManager
					.getContainerDAO().simpleSearchContainers(containerIdFrom, containerIdTo, 
							containerTypeIds, projectIds);
			
			searchResults.addAll(simpleSearchContainers);
			
		} else {
			List containerIds = new ArrayList();

			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			MultipartFile aFile = mrequest.getFile("file");

			if (containerIdsInTextArea.trim().length() > 0) {
				containerIds = String2List(containerIdsInTextArea);
			} else if (!aFile.isEmpty()) {
				InputStream is = aFile.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				br.readLine();
				String aLine = "";

				while ((aLine = br.readLine()) != null) {
					String intContainerId = aLine.trim();
					containerIds.add(intContainerId);
				}
				is.close();
			}

			List<Container> simpleSearchContainers = containerManager.getContainerDAO().simpleSearchContainers(containerIds,
							containerTypeIds, projectIds);
			
			searchResults.addAll(simpleSearchContainers);
		}
		

		if (searchResults.size() < 1) {
			ModelAndView mav = new ModelAndView(new RedirectView(
					"searchContainers.htm"));
			mav.addObject("message", "No results found.");
			return mav;
		}

		ModelAndView mav = new ModelAndView(new RedirectView(getSuccessView()));
		WebUtils.setSessionAttribute(request, "containerList", searchResults);
		mav.addObject("message", "Search Completed");
		return mav;
	}

	protected List performSearch(List crtList, List lgcList) {
		return containerManager.searchContainer(crtList, lgcList);
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map models = new HashMap();
		String message = RequestUtils
				.getStringParameter(request, "message", "");

		LContainerTypes = agtcManager.getContainerTypes();
		List LPlateTypes = agtcManager.getPlateTypes();
		LContainerTypes.removeAll(LPlateTypes);
		LProjects = projectManager.getAllProjects();

		models.put("LContainerTypes", LContainerTypes);
		models.put("LProjects", LProjects);
		models.put("message", message);

		return models;
	}


	private static List String2List(String s) {
		List l = new ArrayList();
		String regularEx = "[,| |;|\\n|\\r|\\t]";
		String[] sa = s.split(regularEx, 0);
		for (int i = 0; i < sa.length; i++) {
			String st = sa[i].trim();
			if (st.length() > 0)
				l.add(st);
		}

		if (l.size() < 1)
			l = null;
		return l;
	}

	private static List String2IntList(String[] sArray) {
		List l = new ArrayList();

		for (int i = 0; i <= sArray.length - 1; i++) {
			l.add(Integer.parseInt(sArray[i]));
		}
		return l;
	}
	
	public ContainerManager getContainerManager() {
		return containerManager;
	}

	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}
	
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager manager) {
		agtcManager = manager;
	}


}
