/*
 * Created on Dec 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.comparator.AssayComparator;
import agtc.sampletracking.bus.manager.TestManager;
import agtc.sampletracking.model.Assay;

/**
 * @author Gloria Deng
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AssaysSearchController extends BasicSearchController {
	private TestManager	testManager;

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws java.lang.Exception {
		List searchResults = handleSubmit(request, command, "assay");

		if (searchResults.size() < 1) {
			ModelAndView view = new ModelAndView(new RedirectView("assays.htm"));
			putMessage(request, view.getModel());
			return view;
		}

		if (searchResults.size() > 1) {
			// multiple tests found
			return new ModelAndView("assayList", "assayList", searchResults);
		}

		// return only one test
		Assay assay = (Assay) searchResults.iterator().next();

		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("assayId", assay.getAssayId());
		return view;
	}

	protected List performSearch(List crtList, List lgcList) {
		return testManager.searchAssay(crtList, lgcList);
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {

		Map models = new HashMap();

		referenceData(request,models,"assay");
		
		List assays = testManager.getAssayDAO().getAssays();
		Collections.sort(assays, new AssayComparator());
		models.put("assayList", assays);

		return models;
	}

	/**
	 * @return
	 */
	public TestManager getTestManager() {
		return testManager;
	}

	/**
	 * @param manager
	 */
	public void setTestManager(TestManager manager) {
		testManager = manager;
	}

}
