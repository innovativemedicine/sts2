/*
 * Created on Nov 2, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.Sample;

/**
 * @author Gloria Deng
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EditSampleController extends BasicController {
	private Log					log	= LogFactory.getLog(EditSampleController.class);
	private SampleManager		sampleManager;
	private ContainerManager	containerManager;
	private AGTCManager			agtcManager;

	public EditSampleController() {
		// initialize the form from the formBackingObject
		setBindOnNewForm(true);

	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		// log.debug("project name is " + projectManager.getproject(new
		// Integer(RequestUtils.getRequiredIntParameter(request,
		// "projectId"))).getName());
		int i = ServletRequestUtils.getIntParameter(request, "sampleId", -1);

		if (i == -1) {

			String intSampleId = ServletRequestUtils.getStringParameter(request, "intSampleId", "");
			Sample sample = new Sample();

			Patient patient = null;
			if (intSampleId.equals("")) {
				patient = new Patient();
			} else {
				patient = sampleManager.getPatient(intSampleId);
			}

			sample.setPatient(patient);

			sample.setSampleId(new Integer(-1));
			return sample;
		} else {
			Sample sample = sampleManager.getSample(new Integer(i));
			// sample.setTempIntSampleId(sample.getPatient().getIntSampleId());
			return sample;
		}
	}

	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, java.lang.Object command, BindException errors)
			throws Exception {
		Sample sample = (Sample) command;

		log.debug(sample);
		// delegate the update to the Business layer
		log.debug("sample type is " + sample.getSampleType().getName());
		try {
			sampleManager.saveSample(sample);
		} catch (Exception e) {
			errors.rejectValue("patient.intSampleId", "error.notUnique", new String[] { sample.getPatient()
					.getIntSampleId() + sample.getSampleType().getSuffix() + sample.getSampleDupNo() }, "Not unique");
			return showForm(request, response, errors);
		}
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message", "Have successfully saved this sample !");
		myModel.put("sampleId", sample.getSampleId());
		return view;

	}

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors)
	{
		Map models = new HashMap();
		// Sample sample = (Sample) command;
		// List existingSampleTypes =
		// sampleManager.getExistingSampleTypes(sample.getPatient().getIntSampleId());
		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		models.put("message", message);

		List allStockContainers = containerManager.getAllContainers();

		List allSampleTypes = agtcManager.getSampleTypes();
		List numbers = new ArrayList();
		for (int i = 1; i < 11; i++) {
			numbers.add(new Integer(i));
		}

		models.put("availableContainers", allStockContainers);
		models.put("availableSampleTypes", allSampleTypes);
		models.put("numbers", numbers);

		return models;
	}

	/*
	 * protected ModelAndView
	 * processFormSubmission(javax.servlet.http.HttpServletRequest request,
	 * javax.servlet.http.HttpServletResponse response, java.lang.Object
	 * command, BindException errors) throws java.lang.Exception { Sample sample
	 * = (Sample) command; log.debug(sample); log.debug(errors); return null; }
	 */

	/**
	 * @return
	 */
	public SampleManager getSampleManager() {
		return sampleManager;
	}

	/**
	 * @param manager
	 */
	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}

	/**
	 * @return
	 */
	public ContainerManager getContainerManager() {
		return containerManager;
	}

	/**
	 * @param manager
	 */
	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}

	/**
	 * @return Returns the agtcManager.
	 */
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	/**
	 * @param agtcManager
	 *            The agtcManager to set.
	 */
	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
}
