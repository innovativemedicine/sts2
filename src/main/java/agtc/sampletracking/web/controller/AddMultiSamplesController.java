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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationConstraint.OperatorType;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestBindingException;
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
import agtc.sampletracking.model.Project;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SampleType;

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

		Integer numSamples = ServletRequestUtils.getIntParameter(request, "ns");
		String samplePrefix = ServletRequestUtils.getStringParameter(request,
				"sp");

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
		String action = ServletRequestUtils.getStringParameter(request,
				"action", "");

		// Output variables
		List<Sample> finalSampleList = new ArrayList<Sample>();
		String message = "";

		if (action.equals("Download")) {
			String contextPath = request.getSession().getServletContext()
					.getRealPath("/");

			// Format Excel Document using filled in form and master manifest
			String excelFileName = "samplemaster.xls";
			String fullName = contextPath + MANIFESTPATH + excelFileName;
			InputStream is = new FileInputStream(fullName);
			Workbook wb = formatSampleManifest(is);
			is.close();

			// Write Excel Document as an attachment
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			wb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			response.setContentType("application/ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // Disable browser caching
			String header = "attachment; filename=samplemaster.xls";
			response.setHeader("Content-Disposition", header);
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();

			return null;
		} else {

			if (action.equals("Upload")) {

				MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
				MultipartFile aFile = mrequest.getFile("file");

				// Format Excel Document using filled in form and master
				// manifest
				// String fullName = MANIFESTPATH + "samplemaster.xls";

				String samplePrefix = "";
				Integer sampleTypeID = -1;
				Integer projectID = -1;

				// Get Sample Prefix
//				try {
					samplePrefix = ServletRequestUtils.getStringParameter(
							request, "sampleIdPre");
					samplePrefix = samplePrefix.toUpperCase();
//				} catch (ServletRequestBindingException e) {
//					errors.reject("error.required",
//							new String[] { "samplePrefix" },
//							"SampleID Prefix is required");
//					return showForm(request, response, errors);
//				}

				if (samplePrefix.isEmpty())
				{
					errors.reject("error.required",
							new String[] { "samplePrefix" },
							"SampleID Prefix is required");
					return showForm(request, response, errors);
				}
				// // Get Sample Type
				// try {
				// sampleTypeID = ServletRequestUtils.getIntParameter(request,
				// "sampleTypeID");
				//
				// } catch (ServletRequestBindingException e) {
				// errors.reject("error.required",
				// new String[] { "sampleTypeID" },
				// "Sample Type is required");
				// return showForm(request, response, errors);
				// }
				//
				// // Get Project
				// try {
				// projectID = ServletRequestUtils.getIntParameter(request,
				// "projectID");
				//
				// } catch (ServletRequestBindingException e) {
				// errors.reject("error.required",
				// new String[] { "projectID" }, "Project is required");
				// return showForm(request, response, errors);
				// }

				if (aFile.isEmpty()) {
					errors.reject("error.emptyFile", "Uploaded file is empty");
					return showForm(request, response, errors);
				}

				InputStream is = aFile.getInputStream();
				try {

					finalSampleList = readSampleManifest(is, samplePrefix,
							sampleTypeID, projectID);
				} catch (Exception e) {
					errors.reject("error.invalidFile", e.getMessage());
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
					String[] sampletypes = ServletRequestUtils
							.getStringParameters(request, sampleTypeField);

					List sampleTypeIds = String2IntList(sampletypes);

					if (sample.getPatient().getProject() == null) {
						errors.reject("error.required",
								new String[] { "project" },
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
						SampleType st = sampleManager
								.getSampleType(sampleTypeId);
						sample.setSampleType(st);

						Integer sampleNum = st.getInitialLabelNo();

						for (int j = 1; j <= sampleNum; j++) {
							Sample sampleClone = (Sample) sample.clone();

							finalSampleList.add(sampleClone);
						}
					}
				}
			}

			// Save samples to DB
			try {
				sampleManager.saveSamples(finalSampleList);

				// Print
				SatoLabelPrinter satoP = new SatoLabelPrinter();
				String contextPath = request.getSession().getServletContext()
						.getRealPath("/");
				satoP.printSampleLabel(finalSampleList, contextPath);

				message += "Samples saved successfully. Labels have been printed";
			} catch (Exception e) {
				message = e.getMessage();
				e.printStackTrace();

				errors.reject("error.required", new String[] { "projectID" },
						"An error has occured while trying to print labels");

				return showForm(request, response, errors);
			}

			ModelAndView mav = new ModelAndView(new RedirectView(
					getSuccessView()));
			WebUtils.setSessionAttribute(request, "sampleList", finalSampleList);
			mav.addObject("message", message);

			return mav;
		}
	}

	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors)

	{
		Map models = new HashMap();
		String message = ServletRequestUtils.getStringParameter(request,
				"message", "");
		if (!message.isEmpty()) {
			models.put("message", message);
		}

		List allSampleTypes = sampleManager.getAllSampleTypes();
		List allProjects = projectManager.getAllProjects();

		models.put("allSampleTypes", allSampleTypes);
		models.put("allProjects", allProjects);
		return models;
	}

	private static List<Integer> String2IntList(String[] sArray) {
		List<Integer> l = new ArrayList<Integer>();

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

	private List<Sample> readSampleManifest(InputStream is,
			String samplePrefix, Integer sampleTypeID, Integer projectID)
			throws Exception {

		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);
		sheet.protectSheet("");

		Iterator<Row> ri = sheet.rowIterator();
		List<Sample> readSamples = new ArrayList<Sample>();

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

			// Eternal Id
			cell = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
			String externalId = cell.getStringCellValue();
			newSample.getPatient().setExtSampleId(externalId);

			if (externalId.isEmpty()) {
				return readSamples;
			}

			// Project
			cell = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
			String project = cell.getStringCellValue();

			// Catch empty Project
			if (project.isEmpty()) {
				throw new Exception(
						"Manifest Error: Project is missing from one or more samples.");
			}

			// Catch unrecognized Project
			try {
				Project pj = projectManager.getProject(project);
				newSample.getPatient().setProject(pj);
			} catch (Exception e) {
				throw new Exception("Manifest Error: Project " + project
						+ " is not recognized. Try downloading new manifest");
			}

			// Birth Date
			cell = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
			Date birthDate = cell.getDateCellValue();
//			Date birthDate = new SimpleDateFormat("yyyy-MM-dd")
//					.parse(birthDateString);
			newSample.getPatient().setBirthDate(birthDate);

			// Received Date
			cell = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
			Date recDate = cell.getDateCellValue();
//			Date recDate = new SimpleDateFormat("yyyy-MM-dd")
//					.parse(recDateString);
			newSample.setReceiveDate(recDate);

			// Notes
			cell = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
			String notes = "";

			notes = cell.getStringCellValue();

			newSample.setNotes(notes);
			newSample.setStatus("Registered");

			// Sample Type
			cell = row.getCell(1, Row.CREATE_NULL_AS_BLANK);

			String sampleType = cell.getStringCellValue();

			// Catch empty Sample Type
			if (sampleType.isEmpty()) {
				throw new Exception(
						"Manifest Error: Sample Type is missing from one or more samples.");
			}

			// Catch unrecognized Sample Type
			try {
				SampleType st = sampleManager.getSampleTypeDAO()
						.getSampleTypeByName(sampleType);
				newSample.setSampleType(st);

				// Set number of duplicates for sample Type
				Integer sampleNum = st.getInitialLabelNo();

				for (int i = 1; i <= sampleNum; i++) {
					Sample sampleClone = (Sample) newSample.clone();
					readSamples.add(sampleClone);
					samplesRead++;
				}

			} catch (Exception e) {
				throw new Exception("Manifest Error: Sample Type " + sampleType
						+ " is not recognized. Try downloading new manifest");
			}

			// Project
			// Project project = projectManager.getProject(projectID);
			// newSample.getPatient().setProject(project);
			//
			// SampleType tempST =
			// sampleManager.getSampleTypeDAO().getSampleType(
			// sampleTypeID);
			// newSample.setSampleType(tempST);

		}
		return readSamples;
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
		dvConstraint = dvHelper.createFormulaListConstraint("hiddenST!$A$1:$A$"
				+ String.valueOf(allSampleTypes.size()));
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
		dvConstraint = dvHelper
				.createFormulaListConstraint("hiddenProject!$A$1:$A$"
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
		dvConstraint = dvHelper.createDateConstraint(OperatorType.BETWEEN,
				"1900-01-01s", dateText, "yyyy-MM-dd");
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
			// Birth Date
			cell = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
			cell.setCellStyle(cs);
			// Received Date
			cell = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
			cell.setCellStyle(cs);
		}
		// Received Date

		return workbook;
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
