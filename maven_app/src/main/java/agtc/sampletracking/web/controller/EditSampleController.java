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
import javax.servlet.http.HttpServletResponse;

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

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		Sample sample = (Sample) command;

		try {
			sampleManager.saveSample(sample);
		} catch (Exception e) {
			String err = "Sample is not unique";

			ModelAndView mav = new ModelAndView(new RedirectView("editSample.htm"));
			mav.addObject("err", err);

			return mav;

		}
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message", "Have successfully saved this sample !");
		myModel.put("sampleId", sample.getSampleId());
		return view;

	}

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) {
		Map models = new HashMap();

		String message = ServletRequestUtils.getStringParameter(request, "message", "");
		String err = ServletRequestUtils.getStringParameter(request, "err", "");
		models.put("err", err);
		models.put("message", message);

		List allStockContainers = containerManager.getAllContainers();

		List allSampleTypes = agtcManager.getSampleTypes();
		List numbers = new ArrayList();
		for (int i = 1; i <= 10; i++) {
			numbers.add(new Integer(i));
		}

		models.put("availableContainers", allStockContainers);
		models.put("availableSampleTypes", allSampleTypes);
		models.put("numbers", numbers);

		return models;
	}

	public SampleManager getSampleManager() {
		return sampleManager;
	}

	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}

	public ContainerManager getContainerManager() {
		return containerManager;
	}

	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}

	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
}
