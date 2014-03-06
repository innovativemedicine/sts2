/*
 * Edited Jan 2013 - Andrew Wong
 *
 */
package agtc.sampletracking.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.web.command.ContainerContentCellUnit;
import agtc.sampletracking.web.command.ContainerContentCommand;

public class AddSample2ContainerController extends ContainerContentsController {
	public AddSample2ContainerController() {
		super();
	}

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
		Container container = containerManager.getContainer(new Integer(ServletRequestUtils.getIntParameter(request,
				"containerId", -1)));
		Map models = new HashMap();
		if (container != null) {
			models.put("container", container);
		}
		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		models.put("message", message);

		List sampleList = (List) WebUtils.getSessionAttribute(request, "sampleList");
		List containerList = containerManager.getAllContainers();
		models.put("sampleList", sampleList);
		models.put("containerList", containerList);
		return models;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		int containerId = ServletRequestUtils.getIntParameter(request, "containerId", -1);
		// String[] samplesToAdd =
		// ServletRequestUtils.getStringParameters(request, "samplesToAdd");
		// List samplesToAddList = Arrays.asList(samplesToAdd);
		List sampleList = (List) WebUtils.getSessionAttribute(request, "sampleList");

		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		String message = "";

		ContainerContentCommand containerContent = (ContainerContentCommand) command;
		ContainerContentCellUnit[][] containerCell = containerContent.getCells();

		Container container = containerManager.getContainer(new Integer(containerId));
		String isOrdered = "ordered";

		myModel.put("containerId", new Integer(containerId));
		myModel.put("isOrdered", isOrdered);

		for (int row = 0; row < containerCell.length; row++) {
			int rowASCII = row + 65;
			char rowLabel = (char) rowASCII;

			for (int col = 0; col < containerCell[row].length; col++) {
				String wellLabel = rowLabel + String.valueOf(col + 1);

				String curSampleDesc = containerCell[row][col].getSampleDesc();
				Integer curSicId = containerCell[row][col].getSicId();

				if (curSicId == null && !curSampleDesc.isEmpty()) {

					Sample curSample = getSampleFromDesc(curSampleDesc);

					if (curSample == null) {
						message = "Error: Sample " + curSampleDesc + " does not exist";
						myModel.put("message", message);
						return view;
					}

					try {
						container = sampleManager.storeSampleInContainer(container, curSample, wellLabel);
					} catch (Exception e1) {
						message = e1.getMessage();
						e1.printStackTrace();
						myModel.put("message", message);
						return view;
					}

					try {
						containerManager.saveContainer(container);
						message = "Samples saved in container successfully";
					} catch (Exception e) {
						message = e.getMessage();
						e.printStackTrace();
						myModel.put("message", message);
						return view;
					}
				}
			}
		}

		WebUtils.setSessionAttribute(request, "sampleList", sampleList);
		myModel.put("message", message.toString());

		return view;
	}

	private Sample getSampleFromDesc(String sampleDesc) {

		String[] descToken = sampleDesc.split("-");

		if (descToken.length == 3) {
			String intSampleId = descToken[0];
			String sampleTypeSuffix = descToken[1];
			Integer dupNo = Integer.parseInt(descToken[2]);

			Sample sample = sampleManager.getSample(intSampleId, sampleTypeSuffix, dupNo);

			return sample;
		} else {
			return null;
		}

	}
}
