/*
 * Created on Apr 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.model.Patient;

/**
 * @author Hongjing
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class EditPatientController extends BasicController {
	private Log				log	= LogFactory.getLog(EditPatientController.class);
	private SampleManager	sampleManager;
	private ProjectManager	projectManager;

	public EditPatientController() {
		// initialize the form from the formBackingObject
		setBindOnNewForm(true);

	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		// log.debug("project name is " + projectManager.getproject(new
		// Integer(RequestUtils.getRequiredIntParameter(request,
		// "projectId"))).getName());
		String intSampleId = ServletRequestUtils.getRequiredStringParameter(request, "intSampleId");

		Patient patient = sampleManager.getPatient(intSampleId);
		return patient;

	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		Patient patient = (Patient) command;
		sampleManager.updatePatient(patient);

		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message", "Have successfully updated patient info!");
		myModel.put("intSampleId", patient.getIntSampleId());
		return view;
	}

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) {
		Map models = new HashMap();

		List allProjects = projectManager.getAllProjects();

		models.put("allProjects", allProjects);
		return models;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	public SampleManager getSampleManager() {
		return sampleManager;
	}

	public void setSampleManager(SampleManager sampleManager) {
		this.sampleManager = sampleManager;
	}
}
