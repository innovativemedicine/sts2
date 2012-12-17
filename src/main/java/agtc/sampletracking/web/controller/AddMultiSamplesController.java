/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.RequestUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import agtc.sampletracking.ConstantInterface;
import agtc.sampletracking.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.bus.report.SatoLabelPrinter;

/**
 * @author Hongjing
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class AddMultiSamplesController extends BasicController implements
		ConstantInterface {
	private Log log = LogFactory.getLog(AddMultiSamplesController.class);
	private SampleManager sampleManager;

	private ProjectManager projectManager;

	public AddMultiSamplesController() {
		// initialize the form from the formBackingObject
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {

		MultiSamples multiSampleClass = new MultiSamples();

		Integer numSamples = RequestUtils.getIntParameter(request, "ns");
		String samplePrefix = RequestUtils.getStringParameter(request, "sp");

		List autoSampleHolder = new ArrayList();

		if (samplePrefix == null) {
			samplePrefix = SAMPLE_PREFIX;
		}

		if (numSamples != null) {

			samplePrefix = samplePrefix.toUpperCase();

			String largestSampleId = sampleManager
					.getLargestSampleId(samplePrefix);
			Integer largestSampleNum = Integer.parseInt(largestSampleId
					.replace(samplePrefix, ""));

			for (int i = 1; i <= numSamples; i++) {
				Integer intSampleNum = largestSampleNum + i;
				String formatNum = String.format("%06d", intSampleNum);
				String intSampleId = samplePrefix + formatNum;
				Sample sample = new Sample(intSampleId);

				// Sample sample = new Sample(largestSampleId);

				autoSampleHolder.add(sample);
			}
		}

		multiSampleClass.setMultiSamples(autoSampleHolder);

		return multiSampleClass;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		// Input variables
		MultiSamples multiSamples = (MultiSamples) command;
		String action = RequestUtils.getStringParameter(request, "action", "");

		// Output variables
		List<Sample> finalSampleList = new ArrayList<Sample>();
		String message = "";

		if (action.equals("Upload")) {

			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			MultipartFile aFile = mrequest.getFile("file");

			// Format Excel Document using filled in form and master manifest
			// String fullName = MANIFESTPATH + "samplemaster.xls";
			// Parse Manifest File HEre
			String samplePrefix = "";

			try {
				samplePrefix = RequestUtils.getStringParameter(request,
						"sampleIdPre");
				samplePrefix = samplePrefix.toUpperCase();
			} catch (Exception e) {
				errors.reject("error.required",
						new String[] { "samplePrefix" },
						"SampleID Prefix is required");
				return showForm(request, response, errors);
			}

			if (aFile.isEmpty()) {
				errors.reject("error.emptyFile", "Uploaded file is empty");
				return showForm(request, response, errors);
			}

			InputStream is = aFile.getInputStream();

			try {
				finalSampleList = readSampleManifest(is, samplePrefix);
			} catch (Exception e) {
				errors.reject("error.required",
						new String[] { "readManifest" }, e.getMessage());
				return showForm(request, response, errors);
			}

			is.close();

		} else if (action.equals("Save")) {

			// List to store results.
			List<Sample> sampleList = (List<Sample>) multiSamples
					.getMultiSamples();

			for (int i = 0; i < sampleList.size(); i++) {
				Sample sample = sampleList.get(i);

				// associate sample with its sample type.
				String sampleTypeField = "sampleType[" + i + "]";
				String[] sampletypes = RequestUtils.getStringParameters(
						request, sampleTypeField);

				List sampleTypeIds = String2IntList(sampletypes);
				System.out.println(sampleTypeIds);

				if (sample.getPatient().getProject() == null) {
					errors.reject("error.required", new String[] { "project" },
							"Project is required");
					return showForm(request, response, errors);
				}

				if (sampleTypeIds.isEmpty()) {
					errors.reject("error.required",
							new String[] { "sample type" },
							"Sample Type is required");
					return showForm(request, response, errors);
				}

				Iterator ir = sampleTypeIds.iterator();

				while (ir.hasNext()) {
					Integer sampleTypeId = (Integer) ir.next();
					SampleType st = sampleManager.getSampleType(sampleTypeId);

					Sample sampleClone = (Sample) sample.clone();
					sampleClone.setSampleType(st);

					finalSampleList.add(sampleClone);
				}
			}
		}

		// Save samples to DB
		try {
			sampleManager.saveSamples(finalSampleList);

			// Print
			SatoLabelPrinter satoP = new SatoLabelPrinter();
			satoP.printSampleLabel(finalSampleList);

			message += "Samples saved successfully. Labels have been printed";
		} catch (Exception e) {
			message = e.getMessage();
			e.printStackTrace();

			return showForm(request, response, errors);
		}

		ModelAndView mav = new ModelAndView(new RedirectView(getSuccessView()));
		WebUtils.setSessionAttribute(request, "sampleList", finalSampleList);
		mav.addObject("message", message);

		return mav;
	}

	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors)

	{
		Map models = new HashMap();
		String message = RequestUtils
				.getStringParameter(request, "message", "");
		if (!message.isEmpty()) {
			models.put("message", message);
		}

		List allSampleTypes = sampleManager.getAllSampleTypes();
		List allProjects = projectManager.getAllProjects();

		models.put("allSampleTypes", allSampleTypes);
		models.put("allProjects", allProjects);
		return models;
	}

	private static List String2IntList(String[] sArray) {
		List l = new ArrayList();

		for (int i = 0; i <= sArray.length - 1; i++) {
			l.add(Integer.parseInt(sArray[i]));
		}
		return l;
	}

	private Workbook retrieveSampleManifest(InputStream is) throws Exception {

		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);

		// Populate samplemanifest if necessary

		sheet.protectSheet("");

		return workbook;
	}

	private List readSampleManifest(InputStream is, String samplePrefix)
			throws Exception {
		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);
		sheet.protectSheet("");

		Iterator<Row> ri = sheet.rowIterator();
		List<Sample> readSamples = new ArrayList();

		// First row is heading
		Row row = ri.next();
		Cell cell = row.getCell(0);

		int samplesRead = 0;

		while (ri.hasNext()) {

			String largestSampleId = sampleManager
					.getLargestSampleId(samplePrefix);
			Integer largestSampleNum = Integer.parseInt(largestSampleId
					.replace(samplePrefix, ""));

			Integer intSampleNum = largestSampleNum + 1 + samplesRead;
			String formatNum = String.format("%06d", intSampleNum);
			String intSampleId = samplePrefix + formatNum;

			cell.setCellValue(intSampleId);

			Sample newSample = new Sample(intSampleId);

			row = ri.next();
			Iterator<Cell> ci = row.cellIterator();

			// Eternal Id
			cell = ci.next();
			try {
				String externalId = cell.getStringCellValue();
				newSample.getPatient().setExtSampleId(externalId);

				if (externalId.isEmpty()) {
					return readSamples;
				}
			} catch (NullPointerException e) {
				return readSamples;
			}

			// Project
			cell = ci.next();
			try {
				String projectName = cell.getStringCellValue();
				Project project = projectManager.getProject(projectName);
				if (project == null) {
					throw new Exception(
							"Invalid project name" + projectName + "for one or more of the samples.");
				}
				newSample.getPatient().setProject(project);
			} catch (Exception e) {
				throw new Exception(
						"Invalid/Missing project name for one or more of the samples.");
			}
			// ST
			cell = ci.next();
			try {
				String st = cell.getStringCellValue();
				SampleType tempST = sampleManager.getSampleTypeDAO()
						.getSampleTypeByName(st);
				if (tempST == null) {
					throw new Exception(
							"Invalid sample type" + st + "for one or more of the samples.");
				}
				newSample.setSampleType(tempST);

			} catch (Exception e) {
				throw new Exception(
						"Invalid/Missing Sample Type for one or more of the samples.");
			}

			// Notes
			cell = ci.next();
			String notes = "";
			try {
				notes = cell.getStringCellValue();
			} catch (Exception e) {
				notes = "";
			}

			newSample.setNotes(notes);
			newSample.setStatus("Registered");

			readSamples.add(newSample);

			samplesRead++;
		}

		return readSamples;
	}

	private void downloadManifest(HttpServletResponse response, Workbook wb,
			String fileName) throws Exception {
		// Write Excel Document as an attachment
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		wb.write(outByteStream);
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/ms-excel");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // Disable browser caching
		String header = "attachment; filename=" + fileName + ".xls";
		response.setHeader("Content-Disposition", header);
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		response.flushBuffer();
	}

	public SampleManager getSampleManager() {
		return sampleManager;
	}

	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
}
