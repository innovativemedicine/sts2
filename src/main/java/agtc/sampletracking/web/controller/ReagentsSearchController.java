/*
 * Created on Jan 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.SampleListHolder;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.model.Reagent;
import agtc.sampletracking.web.command.SearchCommand;
import agtc.sampletracking.web.searchFields.OperatorList;
import agtc.sampletracking.web.searchFields.ReagentSearchFields;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReagentsSearchController extends BasicSearchController {
	private SampleManager sampleManager;
	private Log log = LogFactory.getLog(ReagentsSearchController.class);

	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		List searchResults = handleSubmit(request,command,"reagent");
		
		if (searchResults.size() < 1) {
			ModelAndView view = new ModelAndView(new RedirectView("reagents.htm"));
			putMessage(request,view.getModel());
			return view;
		}
		
		if (searchResults.size() > 1) {
			// multiple projects found
			return new ModelAndView("reagentList", "reagentList", searchResults);
		}
		
		
		Reagent reagent = (Reagent) searchResults.iterator().next();
	
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("reagentId",reagent.getReagentId());
		return view;
	}

	protected  List performSearch(List crtList,List lgcList){
		return sampleManager.searchReagent(crtList,lgcList);
	}
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request)
							   throws java.lang.Exception
	{
	
		Map models = new HashMap();
		referenceData(request,models,"reagent");
		
		log.debug("referenceData is called");
		return models;
	}
	
	
	/**
	 * @return Returns the sampleManager.
	 */
	public SampleManager getSampleManager() {
		return sampleManager;
	}
	
	/**
	 * @param sampleManager The sampleManager to set.
	 */
	public void setSampleManager(SampleManager sampleManager) {
		this.sampleManager = sampleManager;
	}
}
