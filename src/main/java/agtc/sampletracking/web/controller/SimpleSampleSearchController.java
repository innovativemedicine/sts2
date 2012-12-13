/*
 * Created on 2005-09-09
 *
 * To change the template for this generated file go to
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
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.*;
import org.springframework.validation.BindException;
import org.springframework.web.util.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jianan Xiao 2005-09-09
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SimpleSampleSearchController extends BasicSearchController {
	private SampleManager sampleManager;
	private ProjectManager projectManager;
	private List LProjects;
	private List LSampleTypes;
	private Log log = LogFactory.getLog(SimpleSampleSearchController.class);

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		String action = RequestUtils.getStringParameter(request, "action", "");

		String sampleIdsInTextArea = RequestUtils.getStringParameter(request,
				"sampleIdsInTextArea", "");
		String sampleIdFrom = RequestUtils.getStringParameter(request,
				"sampleIdFrom", "");
		String sampleIdTo = RequestUtils.getStringParameter(request,
				"sampleIdTo", "");

		String[] sampletypes = RequestUtils.getStringParameters(request,
				"sampleTypeFilter");
		String[] projects = RequestUtils.getStringParameters(request,
				"projectFilter");

		List sampleTypeIds = String2IntList(sampletypes);
		List projectIds = String2IntList(projects);
		List<Sample> searchResults = new ArrayList();
		
		if (sampleIdFrom.isEmpty() && sampleIdsInTextArea.isEmpty()) {
			ModelAndView mav = new ModelAndView(new RedirectView(
					"simpleSamples.htm"));
			mav.addObject("message", "Please enter Sample ID");
			return mav;
		}
		// Search single Sample or range
		else if (!sampleIdFrom.isEmpty()) {
			List<Sample> simpleSearchSamples = (List<Sample>) sampleManager
					.getSampleDAO().simpleSearchSamples(sampleIdFrom, sampleIdTo, 
							sampleTypeIds, projectIds);
			
			searchResults.addAll(simpleSearchSamples);
			
		} else {
			List sampleIds = new ArrayList();

			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			MultipartFile aFile = mrequest.getFile("file");

			if (sampleIdsInTextArea.trim().length() > 0) {
				sampleIds = String2List(sampleIdsInTextArea);
			} else if (!aFile.isEmpty()) {
				InputStream is = aFile.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				br.readLine();
				String aLine = "";

				while ((aLine = br.readLine()) != null) {
					String intSampleId = aLine.trim();
					sampleIds.add(intSampleId);
				}
				is.close();
			}

			List<Sample> simpleSearchSamples = sampleManager.getSampleDAO().simpleSearchSamples(sampleIds,
							sampleTypeIds, projectIds);
			
			searchResults.addAll(simpleSearchSamples);
		}
		

		if (searchResults.size() < 1) {
			ModelAndView mav = new ModelAndView(new RedirectView(
					"simpleSamples.htm"));
			mav.addObject("message", "No results found.");
			return mav;
		}

		ModelAndView mav = new ModelAndView(new RedirectView(getSuccessView()));
		WebUtils.setSessionAttribute(request, "sampleList", searchResults);
		mav.addObject("message", "Search Completed");
		return mav;
	}

	protected List performSearch(List crtList, List lgcList) {
		return sampleManager.searchSample(crtList, lgcList);
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map models = new HashMap();
		String message = RequestUtils
				.getStringParameter(request, "message", "");

		LSampleTypes = sampleManager.getAllSampleTypes();
		LProjects = projectManager.getAllProjects();

		models.put("LSampleTypes", LSampleTypes);
		models.put("LProjects", LProjects);
		models.put("message", message);

		return models;
	}

	/**
	 * @return
	 */
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

	private static List String2IntList(String[] sArray) {
		List l = new ArrayList();

		for (int i = 0; i <= sArray.length - 1; i++) {
			l.add(Integer.parseInt(sArray[i]));
		}
		return l;
	}
}
