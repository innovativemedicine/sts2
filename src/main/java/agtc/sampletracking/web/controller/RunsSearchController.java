/*
 * Created on Dec 3, 2004
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
import agtc.sampletracking.web.searchFields.OperatorList;
import agtc.sampletracking.web.searchFields.RunSearchFields;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.*;
import org.springframework.validation.BindException;
import java.util.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RunsSearchController extends BasicSearchController {
	private TestManager testManager;
	private IdListResolver idListResolver;
	private Log log = LogFactory.getLog(ResultsSearchController.class);

	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		List searchResults = handleSubmit(request,command,"run");
	
		if (searchResults.size() < 1) {
			ModelAndView view = new ModelAndView(new RedirectView("runs.htm"));
			Map models = view.getModel();
			List runList = testManager.getAllRuns();
			models.put("runList",runList);
			putMessage(request,models);
			return view;
		}
		
		//		>=1 runs found
		List runList = new LinkedList();
		Iterator i = searchResults.iterator();
		while(i.hasNext()){
			Run  run = (Run)i.next();
			String plateNameList = idListResolver.resolvePlateIdList(run.getPlateList());
			String testNameList = idListResolver.resolveTestIdList(run.getTestList());
			run.setPlateNameList(plateNameList);
			run.setTestNameList(testNameList);
			runList.add(run);
		}
		return new ModelAndView("runList", "runList", runList);
	}
	
	protected  List performSearch(List crtList,List lgcList){
		return testManager.searchRun(crtList,lgcList);
	}
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request)
	   throws java.lang.Exception
	{
		Map models = new HashMap();
		//referenceData(request,models,"run");
		List runList = testManager.getAllRuns();
		models.put("runList",runList);
		log.debug("referenceData is called");
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

	/**
	 * @return
	 */
	public IdListResolver getIdListResolver() {
		return idListResolver;
	}

	/**
	 * @param resolver
	 */
	public void setIdListResolver(IdListResolver resolver) {
		idListResolver = resolver;
	}

}
