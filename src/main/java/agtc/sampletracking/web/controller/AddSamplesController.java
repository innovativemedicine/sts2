/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationConstraint.OperatorType;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.ConstantInterface;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.bus.report.SatoLabelPrinter;
import agtc.sampletracking.model.MultiSamples;
import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.Project;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SampleType;

public class AddSamplesController extends BasicController implements ConstantInterface {
	private SampleManager	sampleManager;
	private ProjectManager	projectManager;

	public AddSamplesController() {
		// initialize the form from the formBackingObject
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {

		MultiSamples multiSampleClass = new MultiSamples();

		// Integer numSamples = ServletRequestUtils.getIntParameter(request,
		// "ns");
		// String samplePrefix = ServletRequestUtils.getStringParameter(request,
		// "sampleIdPreForm");

		// List autoSampleHolder = new ArrayList();

		// if (samplePrefix == null) {
		// samplePrefix = SAMPLE_PREFIX;
		// samplePrefix = "";
		// }

		// if (numSamples != null) {

		// samplePrefix = samplePrefix.toUpperCase();
		//
		// String largestSampleId =
		// sampleManager.getLargestSampleId(samplePrefix);
		// Integer largestSampleNum =
		// Integer.parseInt(largestSampleId.replace(samplePrefix, ""));
		//
		// for (int i = 1; i <= numSamples; i++) {
		// Integer intSampleNum = largestSampleNum + i;
		// String formatNum = String.format("%04d", intSampleNum);
		// String intSampleId = samplePrefix + formatNum;
		// Sample sample = new Sample(intSampleId);
		//
		// autoSampleHolder.add(sample);
		// }
		// }

		// multiSampleClass.setMultiSamples(autoSampleHolder);

		return multiSampleClass;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		// Input variables
		String action = ServletRequestUtils.getStringParameter(request, "action", "");

		// Output variables
		List<Sample> finalSampleList = new ArrayList<Sample>();
		String message = "";

		/**
		 * Download Manifest
		 */

		if (action.equals("Download Manifest")) {
			String contextPath = request.getSession().getServletContext().getRealPath("/");

			// Format Excel Document using filled in form and master manifest
			String excelFileName = "samplemaster.xls";
			String fullName = contextPath + MANIFESTPATH + excelFileName;
			InputStream is = new FileInputStream(fullName);
			Workbook wb = formatSampleManifest(is);
			is.close();

			// Download Manifest
			downloadManifest(response, wb, excelFileName);

			return null;
		}

		/**
		 * Upload Manifest To Save Batch Samples
		 */

		else if (action.equals("Upload")) {

			String samplePrefix = ServletRequestUtils.getStringParameter(request, "sampleIdPre");
			samplePrefix = samplePrefix.trim();
			samplePrefix = samplePrefix.toUpperCase();

			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			MultipartFile aFile = mrequest.getFile("file");

			if (samplePrefix.isEmpty()) {
				message = "SampleID Prefix is required";

				ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
				mav.addObject("err", message);

				return mav;
			}

			if (aFile.isEmpty()) {
				message = "Uploaded file is empty";

				ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
				mav.addObject("err", message);

				return mav;
			}

			InputStream is = aFile.getInputStream();
			try {

				finalSampleList = readSampleManifest(is, samplePrefix);

				if (finalSampleList.isEmpty()) {
					message = "Error: Manifest File does not contain any samples";

					ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
					mav.addObject("err", message);

					return mav;
				}
			} catch (Exception e) {
				message = e.getMessage();

				ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
				mav.addObject("err", message);

				return mav;

			}

			is.close();
		}

		/**
		 * Upload Form To Save Individual Samples
		 */

		else if (action.equals("Save")) {

			try {
				finalSampleList = readSampleForm(request);
			} catch (Exception e) {
				message = e.getMessage();

				ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
				mav.addObject("err", message);

				return mav;
			}
		}

		// Save samples to DB
		try {
			sampleManager.saveSamples(finalSampleList);

			// Print Labels
			SatoLabelPrinter satoP = new SatoLabelPrinter();
			String contextPath = request.getSession().getServletContext().getRealPath("/");
			satoP.printSampleLabel(finalSampleList, contextPath);

			message += "Samples saved successfully. Labels have been printed";

		} catch (Exception e) {
			message = e.getMessage();
			e.printStackTrace();
			errors.reject("error.required", new String[] { "PrintError" },
					"An error has occured while trying to print labels");

			message = e.getMessage();

			ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
			mav.addObject("err", message);

			return mav;
		}

		// Goes through the samples and extract unique set of patient.
		// This list is used to generate a list of patients in the sample list
		// view.

		Iterator<Sample> sampleIter = finalSampleList.iterator();

		ModelAndView mav = new ModelAndView(new RedirectView(getSuccessView()));
		WebUtils.setSessionAttribute(request, "sampleList", finalSampleList);
		mav.addObject("message", message);

		return mav;
	}

	protected Map<String, Object> referenceData(HttpServletRequest request, Object command, Errors errors)

	{
		Map<String, Object> models = new HashMap<String, Object>();
		String message = ServletRequestUtils.getStringParameter(request, "message", "");
		String err = ServletRequestUtils.getStringParameter(request, "err", "");

		models.put("message", message);
		models.put("err", err);

		List<SampleType> allSampleTypes = sampleManager.getAllSampleTypes();
		List<Project> allProjects = projectManager.getAllProjects();

		models.put("allSampleTypes", allSampleTypes);
		models.put("allProjects", allProjects);
				
		return models;
	}

	private Workbook formatSampleManifest(InputStream is) throws Exception {

		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);

		Row row;
		Cell cell;
		DataValidationHelper dvHelper;
		DataValidationConstraint dvConstraint;
		CellRangeAddressList addressList;
		DataValidation validation;
		// Set SampleType Constraint

		// Write list of sample type into separate hidden sheet
		List<SampleType> allSampleTypes = sampleManager.getAllSampleTypes();
		Sheet hiddenST = workbook.createSheet("hiddenST");
		int index = 0;
		for (Object st : allSampleTypes) {
			String stName = String.valueOf(st);
			row = hiddenST.createRow(index);
			cell = row.createCell(0);
			cell.setCellValue(stName);
			index++;
		}
		workbook.setSheetHidden(1, true);

		// SampleType Validation
		dvHelper = sheet.getDataValidationHelper();
		dvConstraint = dvHelper
				.createFormulaListConstraint("hiddenST!$A$1:$A$" + String.valueOf(allSampleTypes.size()));
		addressList = new CellRangeAddressList(1, 500, 1, 1);
		validation = dvHelper.createValidation(dvConstraint, addressList);
		validation.setSuppressDropDownArrow(false);
		validation.setShowErrorBox(true);
		sheet.addValidationData(validation);

		// Set Project Constraint
		// Write list of projects into separate hidden sheet
		List<Project> allProjects = projectManager.getAllProjects();
		Sheet hiddenProject = workbook.createSheet("hiddenProject");
		index = 0;
		for (Object project : allProjects) {
			String projectName = String.valueOf(project);
			row = hiddenProject.createRow(index);
			cell = row.createCell(0);
			cell.setCellValue(projectName);
			index++;
		}
		workbook.setSheetHidden(2, true);

		// Project Validation
		dvHelper = sheet.getDataValidationHelper();
		dvConstraint = dvHelper.createFormulaListConstraint("hiddenProject!$A$1:$A$"
				+ String.valueOf(allProjects.size()));
		addressList = new CellRangeAddressList(1, 500, 2, 2);
		validation = dvHelper.createValidation(dvConstraint, addressList);
		validation.setSuppressDropDownArrow(false);
		validation.setShowErrorBox(true);
		sheet.addValidationData(validation);

		// Birth Date
		DateFormat dateformatting = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateText = dateformatting.format(date);

		dvHelper = sheet.getDataValidationHelper();
		dvConstraint = dvHelper.createDateConstraint(OperatorType.BETWEEN, "1900-01-01s", dateText, "yyyy-MM-dd");
		addressList = new CellRangeAddressList(1, 500, 3, 4);
		validation = dvHelper.createValidation(dvConstraint, addressList);
		validation.setShowErrorBox(true);
		sheet.addValidationData(validation);

		// Formatting For Date
		CellStyle cs = workbook.createCellStyle();
		short dateFormat = workbook.createDataFormat().getFormat("yyyy-MM-dd");
		cs.setDataFormat(dateFormat);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);

		for (int i = 1; i <= 500; i++) {
			row = sheet.getRow(i);
			// External ID
			cell = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			// Birth Date
			cell = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
			cell.setCellStyle(cs);
			// Received Date
			cell = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
			cell.setCellStyle(cs);
			// Notes
			cell = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}

		return workbook;
	}

	private void downloadManifest(HttpServletResponse response, Workbook wb, String fileName) throws Exception {

		// Write Excel Document as an attachment
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		wb.write(outByteStream);
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/ms-excel");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // Disable browser caching
		String header = "attachment; filename=" + fileName;
		response.setHeader("Content-Disposition", header);
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		response.flushBuffer();
	}

	private List<Sample> readSampleManifest(InputStream is, String samplePrefix) throws Exception {

		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);
		sheet.protectSheet("");

		Iterator<Row> ri = sheet.rowIterator();
		List<Sample> readSamples = new ArrayList<Sample>();
		Map<String, Sample> curSampleSet = new HashMap<String, Sample>();

		// First row is heading
		Row row = ri.next();

		Cell cell = row.getCell(0);

		int samplesRead = 0;

		while (ri.hasNext()) {

			row = ri.next();

			/**
			 * 1. Read all the values from Excel first. 2. Perform validation.
			 * 3. Assign SampleID.
			 */

			/*
			 * 1. Reading Values
			 */

			// Eternal Id
			cell = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
			String externalId = "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				externalId = cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				DecimalFormat nodecimal = new DecimalFormat("#");
				externalId = nodecimal.format(cell.getNumericCellValue());
			}

			// Stop reading if external ID in the next row is empty!
			if (externalId.isEmpty()) {
				return readSamples;
			}

			// Sample Type
			cell = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
			String sampleType = cell.getStringCellValue();
			// Project
			cell = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
			String project = cell.getStringCellValue();
			// Birth Date
			cell = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
			Date birthDate = cell.getDateCellValue();
			// Received Date
			cell = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
			Date recDate = cell.getDateCellValue();
			// Notes
			cell = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
			String notes = cell.getStringCellValue();

			/*
			 * 2. Validation
			 */

			// Catch empty Project
			if (project.isEmpty()) {
				throw new Exception("Manifest Error: Project is missing from one or more samples.");
			}
			// Catch empty Sample Type
			if (sampleType.isEmpty()) {
				throw new Exception("Manifest Error: Sample Type is missing from one or more samples.");
			}
			// Catch empty Sample Type
			if (recDate == null) {
				throw new Exception("Manifest Error: Received Date is missing from one or more samples.");
			}
			// Catch unrecognized SampleType
			SampleType st = sampleManager.getSampleTypeDAO().getSampleTypeByName(sampleType);
			if (st == null) {
				throw new Exception("Manifest Error: Sample Type " + sampleType
						+ " is not recognized. Try downloading new manifest");
			}
			// Catch unrecognized Project
			Project pj = projectManager.getProject(project);
			if (pj == null) {
				throw new Exception("Manifest Error: Project " + project
						+ " is not recognized. Try downloading new manifest");
			}

			/*
			 * 3. Assign ID
			 */

			// Check if the externalId already exists in the current set of
			// samples
			String extIdKey = externalId + "-" + pj.getName() + "-" + recDate.toString();
			Sample curSampleDupe = curSampleSet.get(extIdKey);
			// Check if a previous sample already exists by the same
			// external ID, project, and birthdate.
			Sample prevSampleDupe = sampleManager.getSampleByExtId(externalId, recDate, pj.getProjectId());

			Sample newSample = null;
			String intSampleId = "";

			// If the sample doesn't exist in current session.
			if (curSampleDupe == null) {

				// If sample exists previously and recDate is not null
				if (prevSampleDupe != null && recDate != null) {
					intSampleId = prevSampleDupe.getPatient().getIntSampleId();
					newSample = new Sample(intSampleId);
				} else {
					String largestSampleId = sampleManager.getLargestSampleId(samplePrefix);
					Integer largestSampleNum = Integer.parseInt(largestSampleId.replace(samplePrefix, ""));

					Integer intSampleNum = largestSampleNum + 1 + samplesRead;
					String formatNum = String.format("%04d", intSampleNum);
					intSampleId = samplePrefix + formatNum;

					newSample = new Sample(intSampleId);

					samplesRead++;
				}

			} else {
				String curDupeProject = curSampleDupe.getPatient().getProject().getName();
				Date curDupeRecDay = curSampleDupe.getReceiveDate();

				// If project and birthDate are the same
				if (project.equals(curDupeProject)) {
					if (recDate != null && recDate.equals(curDupeRecDay)) {
						intSampleId = curSampleDupe.getPatient().getIntSampleId();
						newSample = new Sample(intSampleId);
					} else {

						String largestSampleId = sampleManager.getLargestSampleId(samplePrefix);
						Integer largestSampleNum = Integer.parseInt(largestSampleId.replace(samplePrefix, ""));

						Integer intSampleNum = largestSampleNum + 1 + samplesRead;
						String formatNum = String.format("%04d", intSampleNum);
						intSampleId = samplePrefix + formatNum;

						newSample = new Sample(intSampleId);

						samplesRead++;
					}
				} else {
					String largestSampleId = sampleManager.getLargestSampleId(samplePrefix);
					Integer largestSampleNum = Integer.parseInt(largestSampleId.replace(samplePrefix, ""));

					Integer intSampleNum = largestSampleNum + 1 + samplesRead;
					String formatNum = String.format("%04d", intSampleNum);
					intSampleId = samplePrefix + formatNum;

					newSample = new Sample(intSampleId);

					samplesRead++;

				}
			}

			/*
			 * 4. Add New Sample To FinalList and CurSession List
			 */

			newSample.getPatient().setExtSampleId(externalId);
			newSample.getPatient().setBirthDate(birthDate);
			newSample.setReceiveDate(recDate);
			newSample.getPatient().setProject(pj);
			newSample.setNotes(notes);
			newSample.setStatus("Registered");
			newSample.setSampleType(st);

			// Catch unrecognized Sample Type

			// Set number of duplicates for sample Type
			Integer sampleNum = st.getInitialLabelNo();

			for (int i = 1; i <= sampleNum; i++) {
				Sample sampleClone = (Sample) newSample.clone();
				readSamples.add(sampleClone);
			}

			curSampleSet.put(extIdKey, newSample);

		}

		return readSamples;
	}

	private List<Sample> readSampleForm(HttpServletRequest request) throws Exception {

		List<Sample> sampleList = new ArrayList<Sample>();
		String samplePrefix = ServletRequestUtils.getStringParameter(request, "sampleIdPreForm");
		samplePrefix = samplePrefix.trim();
		samplePrefix = samplePrefix.toUpperCase();

		String externalId = ServletRequestUtils.getStringParameter(request, "externalId");
		String birthDateString = ServletRequestUtils.getStringParameter(request, "birthDate");
		String recDateString = ServletRequestUtils.getStringParameter(request, "receivedDate");
		Integer projectId = ServletRequestUtils.getIntParameter(request, "project");
		String notes = ServletRequestUtils.getStringParameter(request, "notes");

		Date birthDate = null;
		Date recDate = null;

		// Birth Date
		if (!birthDateString.isEmpty()) {
			birthDate = new SimpleDateFormat("dd-MM-yyyy").parse(birthDateString);
		}
		// Received Date
		if (!recDateString.isEmpty()) {
			recDate = new SimpleDateFormat("dd-MM-yyyy").parse(recDateString);
		}

		// Project
		Project project = projectManager.getProject(projectId);

		// Validation

		// Catch empty Project
		if (projectId < 0) {
			throw new Exception("Error: Project required");
		}

		// Check if external ID + project + birthDate ALREADY exists.
		Sample prevSampleDupe = sampleManager.getSampleByExtId(externalId, recDate, project.getProjectId());

		Sample newSample = null;
		String intSampleId = "";

		// If not, assign a new sampleId (using the next largest number)
		if (prevSampleDupe != null && recDate != null) {
			intSampleId = prevSampleDupe.getPatient().getIntSampleId();
			newSample = new Sample(intSampleId);

		} else {
			String largestSampleId = sampleManager.getLargestSampleId(samplePrefix);
			Integer largestSampleNum = Integer.parseInt(largestSampleId.replace(samplePrefix, ""));
			Integer intSampleNum = largestSampleNum + 1;
			String formatNum = String.format("%04d", intSampleNum);
			intSampleId = samplePrefix + formatNum;
			newSample = new Sample(intSampleId);
		}

		// Setting Sample Attributes
		newSample.getPatient().setExtSampleId(externalId);
		newSample.getPatient().setProject(project);
		newSample.getPatient().setBirthDate(birthDate);
		newSample.setReceiveDate(recDate);
		newSample.setNotes(notes);
		newSample.setStatus("Registered");

		// Sample Types
		List<SampleType> sampleTypes = sampleManager.getSampleTypeDAO().getSampleTypes();
		Iterator<SampleType> stIter = sampleTypes.iterator();
		Boolean noST = true;

		while (stIter.hasNext()) {
			SampleType curST = stIter.next();
			Integer sampleTypeId = curST.getSampleTypeId();
			// String stChkboxName = "st" + sampleTypeId;
			String stChkboxNum = "st" + sampleTypeId + "num";

			// Boolean stChk = ServletRequestUtils.getBooleanParameter(request,
			// stChkboxName);

			// if (stChk != null) {

			// Integer sampleNum = curST.getInitialLabelNo();
			Integer sampleNum = ServletRequestUtils.getIntParameter(request, stChkboxNum, 0);

			if (sampleNum > 0) {
				noST = false;
				newSample.setSampleType(curST);
				// Clone sample based on the number of sample type to add
				for (int j = 1; j <= sampleNum; j++) {
					Sample sampleClone = (Sample) newSample.clone();
					sampleList.add(sampleClone);
				}
			}
		}

		// Sample Type
		// List<Integer> sampleTypeIds = String2IntList(sampletypes);
		// Iterator<Integer> ir = sampleTypeIds.iterator();
		//
		// while (ir.hasNext()) {
		// Integer sampleTypeId = ir.next();
		// SampleType st = sampleManager.getSampleType(sampleTypeId);
		// newSample.setSampleType(st);
		//
		// Integer sampleNum = st.getInitialLabelNo();
		//
		// for (int j = 1; j <= sampleNum; j++) {
		// Sample sampleClone = (Sample) newSample.clone();
		//
		// sampleList.add(sampleClone);
		// }
		// }

		if (noST) {
			throw new Exception("Error: Sample Type required");
		}

		return sampleList;
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
