/*
 * Created on Dec 3, 2004
 * 
 *Modified by Jianan Xiao 2005-09-06
 * delete reference of study group to CGG
 * 
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.validation.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.*;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.parser.ResultParser;
import java.util.*;
import org.springframework.web.multipart.*;
import java.io.*;

/**
 * @author Gloria Deng
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EditRunController extends BasicController {
	private TestManager			testManager;
	private ProjectManager		projectManager;
	private ContainerManager	containerManager;
	private AGTCManager			agtcManager;
	private IdListResolver		idListResolver;
	private Log					log	= LogFactory.getLog(EditRunController.class);

	public EditRunController() {
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {

		int i = ServletRequestUtils.getIntParameter(request, "runId", -1);
		if (i == -1) {
			Run run = new Run();
			run.setRunId(new Integer(-1));

			int ip = ServletRequestUtils.getIntParameter(request, "projectId", -1);

			if (ip != -1) {
				run.setProject(projectManager.getProject(new Integer(ip)));
			}

			return run;
		} else {
			Run run = testManager.getRun(new Integer(i));
			run.setTestNameList(idListResolver.resolveTestIdList(run.getTestList()));
			run.setPlateNameList(idListResolver.resolvePlateIdList(run.getPlateList()));

			return run;
		}
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		Run run = (Run) command;
		log.debug("the runid is " + run.getRunId());

		int sampleIdColumnNo = ServletRequestUtils.getIntParameter(request, "sampleIdColumnNo", -1);
		int assayNameColumnNo = ServletRequestUtils.getIntParameter(request, "assayNameColumnNo", -1);
		int resultColumnNo = ServletRequestUtils.getIntParameter(request, "resultColumnNo", -1);

		String[] pIds = request.getParameterValues("plateIds");
		String plateIdList = "";
		if (pIds != null) {
			for (int i = 0; i < pIds.length; i++) {
				plateIdList += "," + pIds[i] + ",";
			}

		}

		String[] tIds = request.getParameterValues("testIds");
		String testIdList = "";

		if (tIds != null) {
			for (int i = 0; i < tIds.length; i++) {
				testIdList += "," + tIds[i] + ",";
			}
		}
		run.setPlateList(plateIdList);
		run.setTestList(testIdList);

		log.debug(run);

		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
		MultipartFile aFile = mrequest.getFile("file");
		if (aFile.isEmpty()) {
			String err = "Uploaded File is empty";

			ModelAndView mav = new ModelAndView(new RedirectView("editRun.htm"));
			mav.addObject("err", err);

			return mav;

		}
		String note = run.getNote();
		if (note == null || note.trim().length() == 0) {
			note = aFile.getOriginalFilename();
			run.setNote(note);
		}
		InputStream is = aFile.getInputStream();

		/*
		 * Modified by Jianan Xiao 2005-09-06 Because all sample id have prefix
		 * now, so we need not them from this time, and alos deleted
		 * "sample file type" and "study group id"
		 */
		ResultParser rp = new ResultParser(is, sampleIdColumnNo - 1, assayNameColumnNo - 1, resultColumnNo - 1);
		String parseResult = rp.parseResults();
		List results = rp.getResults();
		/*
		 * Integer studyGroupIdO = null; if(studyGroupId != -1){ studyGroupIdO =
		 * new Integer(studyGroupId); }
		 */
		is.close();

		if (parseResult.length() == 0) { // parsing success, save the samples
			try {
				testManager.saveResults(results, run, null);
				// ModelAndView view = new ModelAndView(new
				// RedirectView(getSuccessView()));

				ModelAndView mav = new ModelAndView("editRun");
				String message = "Have successfully save the run and load the result !";
				mav.addObject("message", message);
				// myModel.put("runId",run.getRmyModunId());

				return mav;
			} catch (Exception e) {
				parseResult = "Could not load the results. Please make sure that all samples and assays in your file are already in the STS ! <br>";
				parseResult += e.toString();
			}
		}

		// there is some correctable error in the file, let user correct it and
		// upload again
		ModelAndView mav = new ModelAndView("editRun");
		String message = "Please correct the following error and upload your file again ! <br>";
		mav.addObject("message", message);

		return mav;
	}

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws java.lang.Exception {
		Map models = new HashMap();

		String message = ServletRequestUtils.getStringParameter(request, "message", "");
		String err = ServletRequestUtils.getStringParameter(request, "err", "");
		models.put("err", err);
		models.put("message", message);

		List allProjects = projectManager.getAllProjects();
		List allTests = testManager.getAllTests();
		List allPlates = containerManager.getAllPlates();

		List allColumnNumbers = new ArrayList();

		CGGStudyGroup empty = new CGGStudyGroup();
		// allStudyGroups.add(0,empty);

		models.put("allTests", allTests);
		models.put("allPlates", allPlates);
		models.put("allProjects", allProjects);
		return models;
	}

	public ContainerManager getContainerManager() {
		return containerManager;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public TestManager getTestManager() {
		return testManager;
	}

	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}

	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}

	public void setTestManager(TestManager manager) {
		testManager = manager;
	}

	public IdListResolver getIdListResolver() {
		return idListResolver;
	}

	public void setIdListResolver(IdListResolver resolver) {
		idListResolver = resolver;
	}

	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
}
