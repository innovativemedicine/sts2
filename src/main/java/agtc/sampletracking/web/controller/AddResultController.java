/*
 * Created on Dec 13, 2004
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;


import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.TestManager;
import agtc.sampletracking.model.Result;
import agtc.sampletracking.model.Run;


public class AddResultController extends BasicController {
	private TestManager testManager;
	private ProjectManager projectManager;
	
	public ProjectManager getProjectManager() {
		return projectManager;
	}
	
	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	public TestManager getTestManager() {
		return testManager;
	}
	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		Result result = new Result();
		result.setResultId(new Integer(-1));
		
		Run run = new Run();
		run.setRunId(new Integer(-1));
		result.setRun(run);
		Set r = new HashSet();
		r.add(result);	
		run.setResults(r);
		
		return result;
		
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
								HttpServletResponse response,
								Object command,
								BindException errors)
						 throws Exception {
		Result result = (Result) command;
		
		ValidationUtils.rejectIfEmpty(errors, "result", "required", "genotype result is required");
		ValidationUtils.rejectIfEmpty(errors, "intSampleId", "required", "sample id is required");
		String saveResult = "";
		
		try{
			testManager.saveResult(result);
			testManager.refreshResult(result);
			ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
			Map m = view.getModel();
			m.put("message","Have successfully save the result !");
			m.put("resultId",result.getResultId());
			return view;
		}catch(Exception e){
			
			String err = "Could not load the results. Please make sure that the sample is already in the STS !";
						
			ModelAndView mav = new ModelAndView(new RedirectView("addResult.htm"));
			mav.addObject("err", err);

			return mav;
		}
		
	}
	
	protected Map referenceData(HttpServletRequest request,
									 Object command,Errors errors)
							   throws Exception
	{
		Result result = (Result)command;
		Map models = new HashMap();
		List allProjects = projectManager.getAllProjects();
		List allAssays = testManager.getAllAssays();
		String err = ServletRequestUtils.getStringParameter(request, "err", "");

/* Modified by Jianan Xiao 2005-09-06
 * delete reference of study group to CGG*/
/*		List allStudyGroups = testManager.getAllCGGStudyGroups();
		CGGStudyGroup empty = new CGGStudyGroup();
		allStudyGroups.add(0,empty);
		models.put("allStudyGroups",allStudyGroups);
*/		
		models.put("allAssays",allAssays);
		models.put("allProjects",allProjects);
		models.put("err", err);
		
		return models;
	}
}

