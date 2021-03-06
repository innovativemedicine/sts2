package agtc.sampletracking.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.ConstantInterface;
import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.bus.report.SatoLabelPrinter;
import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.Project;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SampleType;

public class PatientDetailsController extends BasicController implements ConstantInterface {

	private SampleManager	sampleManager;
	private ProjectManager	projectManager;
	private AGTCManager		agtcManager;

	public PatientDetailsController() {
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// Can't use patient as is because it returns a lazy instance... figure
		// it out later. Uses referneceData to pull patient right now.
		// Patient patient =
		// sampleManager.getPatient(ServletRequestUtils.getStringParameter(request,
		// "intSampleId", ""));
		Patient patient = new Patient();
		return patient;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		String intSampleId = ServletRequestUtils.getStringParameter(request, "intSampleId", "");

		if (action.equalsIgnoreCase("addst")) {
			Patient patient = sampleManager.getPatient(ServletRequestUtils.getStringParameter(request, "intSampleId",
					""));

			String notes = ServletRequestUtils.getStringParameter(request, "addST_notes");

			Sample newSample = new Sample();
			newSample.setPatient(patient);
			// Setting Sample Attributes
			newSample.setNotes(notes);
			newSample.setStatus("Registered");

			Integer sampleTypeId = ServletRequestUtils.getIntParameter(request, "addST_sampleTypeId");
			Integer sampleNum = ServletRequestUtils.getIntParameter(request, "addST_numSamples", 0);

			SampleType curST = sampleManager.getSampleType(sampleTypeId);
			newSample.setSampleType(curST);

			List<Sample> sampleList = new ArrayList<Sample>();

			if (sampleNum > 0) {

				Integer largestDupNoInDB = sampleManager.getLargestDupNo(intSampleId, sampleTypeId);
				
				// Clone sample based on the number of sample type to add
				for (int j = 1; j <= sampleNum; j++) {
					Sample sampleClone = (Sample) newSample.clone();
					Integer newDupNo = largestDupNoInDB + j;
					newSample.setSampleDupNo(newDupNo);
					sampleList.add(sampleClone);
				}
			}

			// Save samples to DB and redirect to success view

			String message = "";

			try {
				sampleManager.saveSamples(sampleList);

				// Print Labels
				SatoLabelPrinter satoP = new SatoLabelPrinter();
				String contextPath = request.getSession().getServletContext().getRealPath("/");
				satoP.printSampleLabel(sampleList, contextPath);

				message += "Samples saved successfully. Labels have been printed";

			} catch (Exception e) {
				// Catch errors encountered when attempting to save
				message = e.getMessage();
				e.printStackTrace();

				message = e.getMessage();

				ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
				mav.addObject("err", message);

				return mav;
			}

			ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
			mav.addObject("message", "Sample Type Added");
			mav.addObject("intSampleId", intSampleId);

			return mav;
		}
		ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));

		mav.addObject("intSampleId", intSampleId);

		return mav;
	}

	protected Map<String, Object> referenceData(HttpServletRequest request, Object command, Errors errors)
			throws ServletException {

		Map<String, Object> models = new HashMap<String, Object>();

		Patient patient = sampleManager.getPatient(ServletRequestUtils.getStringParameter(request, "intSampleId", ""));
		models.put("patient", patient);

		List<SampleType> allSampleTypes = sampleManager.getAllSampleTypes();
		models.put("allSampleTypes", allSampleTypes);

		String message = ServletRequestUtils.getStringParameter(request, "message", "");
		models.put("message", message);

		return models;
	}

	public SampleManager getSampleManager() {
		return sampleManager;
	}

	public void setSampleManager(SampleManager sampleManager) {
		this.sampleManager = sampleManager;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}

}
