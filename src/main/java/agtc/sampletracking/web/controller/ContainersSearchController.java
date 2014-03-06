/*
 * Created on 2005-09-09
 *
 * To change the temcontainer for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.model.Container;

public class ContainersSearchController extends BasicSearchController {
	private ContainerManager	containerManager;
	private ProjectManager		projectManager;
	private AGTCManager			agtcManager;
	private List				LProjects;
	private List				LContainerTypes;

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		String containerIdFrom = ServletRequestUtils.getStringParameter(request, "containerIdFrom", "");

		List<Container> searchResults = new ArrayList<Container>();

		if (action.equalsIgnoreCase("Show")) {
			// Returning null would cause the page to reload with all boxes
			WebUtils.setSessionAttribute(request, "containerList", null);
			ModelAndView mav = new ModelAndView(new RedirectView(getSuccessView()));
			mav.addObject("message", "Search Completed");
			return mav;
		} else {
			if (containerIdFrom.isEmpty()) {
				ModelAndView mav = new ModelAndView(new RedirectView("searchContainers.htm"));
				mav.addObject("message", "Please enter Container ID");
				return mav;
			}
			// Search single Container or range
			else if (!containerIdFrom.isEmpty()) {

				List<Container> simpleSearchContainers = (List<Container>) containerManager.getContainerDAO()
						.simpleSearchContainers(containerIdFrom, "", "", "", new ArrayList<Object>(),
								new ArrayList<Object>());

				searchResults.addAll(simpleSearchContainers);

			}

			if (searchResults.size() < 1) {
				ModelAndView mav = new ModelAndView(new RedirectView("searchContainers.htm"));
				mav.addObject("message", "No results found.");
				return mav;
			}

			ModelAndView mav = new ModelAndView(new RedirectView(getSuccessView()));
			WebUtils.setSessionAttribute(request, "containerList", searchResults);
			mav.addObject("message", "Search Completed");
			return mav;
		}
	}

	protected List performSearch(List crtList, List lgcList) {
		return containerManager.searchContainer(crtList, lgcList);
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map models = new HashMap();
		String message = ServletRequestUtils.getStringParameter(request, "message", "");
		String err = ServletRequestUtils.getStringParameter(request, "err", "");

		LContainerTypes = agtcManager.getBoxTypes();

		List<Container> containerList = (List<Container>) WebUtils.getSessionAttribute(request, "containerList");

		if (containerList == null) {
			containerList = containerManager.getAllContainers();
			WebUtils.setSessionAttribute(request, "containerList", containerList);
		}

		LProjects = projectManager.getAllProjects();

		models.put("LContainerTypes", LContainerTypes);
		models.put("LProjects", LProjects);
		models.put("message", message);
		models.put("err", err);

		WebUtils.setSessionAttribute(request, "sampleList", null);

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

	private static List<Integer> String2IntList(String[] sArray) {
		List<Integer> l = new ArrayList<Integer>();

		for (int i = 0; i <= sArray.length - 1; i++) {
			if (!sArray[i].isEmpty()) {
				l.add(Integer.parseInt(sArray[i]));
			}
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
