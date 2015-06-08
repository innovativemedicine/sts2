/*
 * Created on Nov 11, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.view.*;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.manager.*;
import java.util.*;

/**
 * @author Gloria Deng
 */
public class EditAssayController extends BasicController {
	private TestManager		testManager;
	private ProjectManager	projectManager;
	private Log				log	= LogFactory.getLog(EditAssayController.class);

	public EditAssayController() {
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {

		int i = ServletRequestUtils.getRequiredIntParameter(request, "assayId");

		if (i == -1) {
			Assay assay = new Assay();
			assay.setAssayId(new Integer(-1));
			int ip = ServletRequestUtils.getRequiredIntParameter(request, "projectId");
			Project project = projectManager.getProject(new Integer(ip));
			Set projects = new HashSet();
			assay.setProjects(projects);
			assay.getProjects().add(project);

			return assay;
		} else {
			Assay assay = testManager.getAssay(new Integer(i));
			return assay;
		}
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		Assay assay = (Assay) command;

		try {
			testManager.saveAssay(assay);
		} catch (Exception e) {

			String err = "Assay name not unique";
			ModelAndView mav = new ModelAndView(new RedirectView("editAssay.htm"));
			mav.addObject("err", err);

			return mav;
		}

		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message", "Have successfully saved this assay !");
		myModel.put("assayId", assay.getAssayId());
		return view;
	}

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
		Map models = new HashMap();

		String message = ServletRequestUtils.getStringParameter(request, "message", "");
		String err = ServletRequestUtils.getStringParameter(request, "err", "");

		models.put("message", message);
		models.put("err", err);

		return models;
	}


	public TestManager getTestManager() {
		return testManager;
	}

	public void setTestManager(TestManager manager) {
		testManager = manager;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}

}
