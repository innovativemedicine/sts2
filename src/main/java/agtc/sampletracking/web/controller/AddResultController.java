/*
 * Created on Dec 13, 2004
 *
 * TODO To change the template for this generated file go to
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
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.*;
import agtc.sampletracking.model.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddResultController extends BasicController {
	private TestManager testManager;
	private ProjectManager projectManager;
	/**
	 * @return Returns the projectManager.
	 */
	public ProjectManager getProjectManager() {
		return projectManager;
	}
	/**
	 * @param projectManager The projectManager to set.
	 */
	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	/**
	 * @return Returns the testManager.
	 */
	public TestManager getTestManager() {
		return testManager;
	}
	/**
	 * @param testManager The testManager to set.
	 */
	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}
	private Log log = LogFactory.getLog(AddResultController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("run name is " + runManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "runId"))).getName());
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
			Map myModel = view.getModel();
			myModel.put("message","Have successfully save the result !");
			log.debug("have put the message, will put resultId" );
			log.debug("resultId is " + result.getResultId());
			myModel.put("resultId",result.getResultId());
			return view;
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.getMessage());
			log.debug(e.getStackTrace());
			saveResult = "Could not load the results. Please make sure that the sample is already in the STS ! <br>";
			saveResult += e.toString();
			errors.reject("error.sampleNotExisted",new String[]{result.getIntSampleId()},saveResult);
			return showForm(request, response, errors);
		}
		
	}
	
	
	/**
	protected ModelAndView processFormSubmission(javax.servlet.http.HttpServletRequest request,
													 javax.servlet.http.HttpServletResponse response,
													 java.lang.Object command,
													 BindException errors)
											  throws java.lang.Exception
		{
			
			log.debug(errors);
			return null;
		}

	*/
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
									  java.lang.Object command,Errors errors)
							   throws java.lang.Exception
	{
		Result result = (Result)command;
		Map models = new HashMap();
		List allProjects = projectManager.getAllProjects();
		List allAssays = testManager.getAllAssays();
/* Modified by Jianan Xiao 2005-09-06
 * delete reference of study group to CGG*/
/*		List allStudyGroups = testManager.getAllCGGStudyGroups();
		CGGStudyGroup empty = new CGGStudyGroup();
		allStudyGroups.add(0,empty);
		models.put("allStudyGroups",allStudyGroups);
*/		
		models.put("allAssays",allAssays);
		models.put("allProjects",allProjects);
		
		return models;
	}
}

