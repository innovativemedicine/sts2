package agtc.sampletracking.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.model.Sample;

@SuppressWarnings("deprecation")
public class SampleSearchController extends BasicSearchController
// extends BasicSearchController
{
	private SampleManager	sampleManager;
	private ProjectManager	projectManager;
	private List			LProjects;
	private List			LSampleTypes;
	private Log				log	= LogFactory.getLog(SampleSearchController.class);

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		// String action = RequestUtils.getStringParameter(request, "action",
		// "");

		String sampleIdsInTextArea = ServletRequestUtils.getStringParameter(request, "sampleIdsInTextArea", "");
		String sampleIdFrom = ServletRequestUtils.getStringParameter(request, "sampleIdFrom", "");
		String sampleIdTo = ServletRequestUtils.getStringParameter(request, "sampleIdTo", "");
		String externalIdFrom = ServletRequestUtils.getStringParameter(request, "externalIdFrom", "");
		String externalIdTo = ServletRequestUtils.getStringParameter(request, "externalIdTo", "");
		String[] sampletypes = ServletRequestUtils.getStringParameters(request, "sampleTypeFilter");
		String[] projects = ServletRequestUtils.getStringParameters(request, "projectFilter");

		List sampleTypeIds = String2IntList(sampletypes);
		List projectIds = String2IntList(projects);
		List<Sample> searchResults = new ArrayList<Sample>();

		if (sampleIdFrom.isEmpty() && sampleIdsInTextArea.isEmpty() && externalIdFrom.isEmpty() && projectIds.isEmpty()) {
			ModelAndView mav = new ModelAndView(new RedirectView("searchSamples.htm"));
			mav.addObject("message", "Error: Must enter value for either Sample ID, External ID, or Project ID.");
			return mav;
		}
		// Search single Sample or range
		else if (!sampleIdFrom.isEmpty() || !externalIdFrom.isEmpty()) {

			List simpleSearchSamples = sampleManager.getSampleDAO().simpleSearchSamples(sampleIdFrom, sampleIdTo,
					externalIdFrom, externalIdTo, sampleTypeIds, projectIds);

			searchResults.addAll(simpleSearchSamples);

		} // file with SampleIDs detected
		else {

			List<String> sampleIds = new ArrayList<String>();
			List<String> extSampleIds = new ArrayList<String>();

			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			MultipartFile aFile = mrequest.getFile("file");

			String optionsID = ServletRequestUtils.getStringParameter(request, "optionsID", "");

			if (sampleIdsInTextArea.trim().length() > 0) {
				if (optionsID.equalsIgnoreCase("internal")) {
					sampleIds = String2List(sampleIdsInTextArea);
				} else if (optionsID.equalsIgnoreCase("external")) {
					extSampleIds = String2List(sampleIdsInTextArea);
				}
			}

			List<Sample> simpleSearchSamples = sampleManager.getSampleDAO().simpleSearchSamples(sampleIds,
					extSampleIds, sampleTypeIds, projectIds);

			searchResults.addAll(simpleSearchSamples);
		}

		if (searchResults.size() < 1) {
			ModelAndView mav = new ModelAndView(new RedirectView("searchSamples.htm"));
			mav.addObject("message", "No results found.");
			return mav;
		}

		ModelAndView mav = new ModelAndView(new RedirectView(getSuccessView()));
		WebUtils.setSessionAttribute(request, "sampleList", searchResults);
		mav.addObject("message", "Search Completed");

		return mav;
	}

	// protected List performSearch(List crtList, List lgcList) {
	// List dummyList = new ArrayList();
	// return dummyList;
	// }

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map models = new HashMap();
		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		LSampleTypes = sampleManager.getAllSampleTypes();
		LProjects = projectManager.getAllProjects();

		models.put("LSampleTypes", LSampleTypes);
		models.put("LProjects", LProjects);
		models.put("message", message);

		WebUtils.setSessionAttribute(request, "sampleList", null);

		return models;
	}

	/*
	 * Change input "String" to "List" that delimited by non-word boundary
	 * Jianan Xiao 2005-09-12
	 */
	private static List<String> String2List(String s) {
		List<String> l = new ArrayList<String>();
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

	public SampleManager getSampleManager() {
		return sampleManager;
	}

	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}

	@Override
	protected List performSearch(List crtList, List lgcList) {
		return null;
	}

}