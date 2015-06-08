/*
 * Created on 2005-09-09
 *
 * To change the template for this generated file go to
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.model.Container;

/**
 * @author Jianan Xiao 2005-09-09
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class PlatesSearchController extends BasicSearchController {
	private ContainerManager	containerManager;
	private ProjectManager		projectManager;
	private AGTCManager			agtcManager;
	private List				LProjects;
	private List				LPlateTypes;

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		String plateIdsInTextArea = ServletRequestUtils.getStringParameter(request, "plateIdsInTextArea", "");
		String plateIdFrom = ServletRequestUtils.getStringParameter(request, "plateIdFrom", "");
		String plateIdTo = ServletRequestUtils.getStringParameter(request, "plateIdTo", "");
		String externalIdFrom = ServletRequestUtils.getStringParameter(request, "externalIdFrom", "");
		String externalIdTo = ServletRequestUtils.getStringParameter(request, "externalIdTo", "");
		String[] platetypes = ServletRequestUtils.getStringParameters(request, "plateTypeFilter");
		String[] projects = ServletRequestUtils.getStringParameters(request, "projectFilter");

		List plateTypeIds = String2IntList(platetypes);
		List projectIds = String2IntList(projects);
		List<Container> searchResults = new ArrayList();

		if (plateIdFrom.isEmpty() && plateIdsInTextArea.isEmpty() && externalIdFrom.isEmpty()) {
			ModelAndView mav = new ModelAndView(new RedirectView("searchPlates.htm"));
			mav.addObject("message", "Error: Must enter value for either Plate ID or External ID");
			return mav;
		}
		// Search single Container or range
		else if (!plateIdFrom.isEmpty() || !externalIdFrom.isEmpty()) {
			List<Container> simpleSearchContainers = containerManager.getContainerDAO().simpleSearchContainers(
					plateIdFrom, plateIdTo, externalIdFrom, externalIdTo, plateTypeIds, projectIds);

			searchResults.addAll(simpleSearchContainers);

		} else {
			List plateIds = new ArrayList();

			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			MultipartFile aFile = mrequest.getFile("file");

			if (plateIdsInTextArea.trim().length() > 0) {
				plateIds = String2List(plateIdsInTextArea);
			}
			// } else if (!aFile.isEmpty()) {
			// InputStream is = aFile.getInputStream();
			// BufferedReader br = new BufferedReader(
			// new InputStreamReader(is));
			// br.readLine();
			// String aLine = "";
			//
			// while ((aLine = br.readLine()) != null) {
			// String intPlateId = aLine.trim();
			// plateIds.add(intPlateId);
			// }
			// is.close();
			// }

			List<Container> simpleSearchContainers = containerManager.getContainerDAO().simpleSearchContainers(
					plateIds, plateTypeIds, projectIds);

			searchResults.addAll(simpleSearchContainers);
		}

		if (searchResults.size() < 1) {
			ModelAndView mav = new ModelAndView(new RedirectView("searchPlates.htm"));
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
		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		LPlateTypes = agtcManager.getPlateTypes();
		LProjects = projectManager.getAllProjects();

		models.put("LPlateTypes", LPlateTypes);
		models.put("LProjects", LProjects);
		models.put("message", message);

		return models;
	}

	/*
	 * Change input "String" to "List" that delimited by non-word boundary
	 * Jianan Xiao 2005-09-12
	 */
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
