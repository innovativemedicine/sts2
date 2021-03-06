package agtc.sampletracking.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.ConstantInterface;
import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.bus.report.SatoLabelPrinter;
import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.ContainerType;
import agtc.sampletracking.model.Project;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SampleType;
import agtc.sampletracking.model.SamplesInContainer;

public class AddPlateController extends BasicController implements ConstantInterface {

	private ContainerManager	containerManager;
	private SampleManager		sampleManager;
	private ProjectManager		projectManager;
	private AGTCManager			agtcManager;

	public AddPlateController() {
		// initialize the form from the formBackingObject
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {

		String largestPlateId = containerManager.getLargestPlateId(PLATE_PREFIX);
		Integer largestPlateNum = Integer.parseInt(largestPlateId.replace(PLATE_PREFIX, ""));
		Integer plateNum = largestPlateNum + 1;
		String formatNum = String.format("%06d", plateNum);
		String plateId = PLATE_PREFIX + formatNum;

		Container container = new Container();
		container.setName(plateId);
		container.setContainerId(new Integer(-1));
		container.setContainerType(new ContainerType());
		container.setProject(new Project());

		container.setCreatedDate(new Date());
		return container;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		Container container = (Container) command;

		String action = ServletRequestUtils.getStringParameter(request, "action", "");

		// Download Plate96 Manifest
		if (action.equals("Download")) {

			String contextPath = request.getSession().getServletContext().getRealPath("/");

			// Format Excel Document using filled in form and master manifest
			String excelFileName = "plate96master.xls";
			String fullName = contextPath + MANIFESTPATH + excelFileName;
			InputStream is = new FileInputStream(fullName);
			Workbook wb = formatPlateManifest(container, is);
			is.close();

			// Write Excel Document as an attachment
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			wb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			response.setContentType("application/ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // Disable browser caching
			String header = "attachment; filename=" + excelFileName;
			response.setHeader("Content-Disposition", header);
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();

			return null;
		} else if (action.equals("Save")) {

			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			MultipartFile aFile = mrequest.getFile("file");

			// final Map files = mrequest.getFileMap()
			// Assert.state(files.size() > 0, "0 files exist");

			if (aFile.isEmpty()) {
				String err = "No manifest file found";

				ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
				mav.addObject("err", err);

				return mav;

			}

			// Parse Manifest File HEre
			InputStream is = aFile.getInputStream();
			Workbook wb;
			try {
				wb = readPlateManifest(is);
			} catch (Exception e) {
				String err = "Failed to read manifest: " + e.toString();

				ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
				mav.addObject("err", err);

				return mav;

			}

			is.close();

			String fileName = container.getName() + "final";

			// downloadManifest(response, wb, fileName);
			// Instead of invoking a download.
			// Use JavaMail to send out an email to the order placer, to order
			// receiver
			// Attach the finalXL (with the int sample ID filled in as reference

			Container savedContainer = containerManager.getContainer(container.getName());

			List<Container> containerList = new ArrayList<Container>();
			containerList.add(savedContainer)

			; // Print
			SatoLabelPrinter satoP = new SatoLabelPrinter();
			String contextPath = request.getSession().getServletContext().getRealPath("/");
			satoP.printPlateLabel(containerList, contextPath);

			// Redirect to containerDetails view on success.
			ModelAndView mav = new ModelAndView(new RedirectView(getSuccessView()));
			mav.addObject("message", "Plate" + container.getName() + "saved. Labels have been printed. ");
			mav.addObject("containerId", savedContainer.getContainerId());

			return mav;
		} else {
			String err = "An unknown error has occured. Please resubmit.";

			ModelAndView mav = new ModelAndView(new RedirectView(getFormView() + ".htm"));
			mav.addObject("err", err);
			return mav;
		}
	}

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
		Map models = new HashMap();


		List allProjects = projectManager.getAllProjects();
		List allPlateTypes = agtcManager.getPlateTypes();

		String err = ServletRequestUtils.getStringParameter(request, "err", "");
		models.put("err", err);

		models.put("allPlateTypes", allPlateTypes);
		models.put("allProjects", allProjects);

		return models;
	}

	private Workbook formatPlateManifest(Container plate, InputStream is) throws Exception {

		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);

		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);

		// Integer numRow = plate.getContainerType().getRowNo();
		// Integer numCol = plate.getContainerType().getColumnNo();
		// Integer capacity = numRow * numCol;

		// Plate ID
		row = sheet.getRow(0);
		cell = row.getCell(1);

		// Ext Plate ID
		row = sheet.getRow(1);
		cell = row.getCell(1);
		// Plate Type
		row = sheet.getRow(2);
		cell = row.getCell(1);

		// Project
		row = sheet.getRow(3);
		cell = row.getCell(1);

		// Created Date
		row = sheet.getRow(4);
		cell = row.getCell(1);

		// Comments
		row = sheet.getRow(5);
		cell = row.getCell(1);

		return workbook;
	}

	private Workbook readPlateManifest(InputStream is) throws Exception {
		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);
		sheet.protectSheet("");

		// Check to make sure this is a legit Manifest file.
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(1);

		Container container = new Container();
		// Plate Name
		row = sheet.getRow(0);
		cell = row.getCell(1);
		String plateName = cell.getStringCellValue();
		container.setName(plateName);

		if (containerManager.getContainer(plateName) != null) {
			throw new Exception("Plate ID is no longer unique! Please reload form to update Manifest with new ID.");
		}

		// Plate Type
		row = sheet.getRow(2);
		cell = row.getCell(1);
		String plateTypeName = cell.getStringCellValue();

		ContainerType plateType = agtcManager.getContainerType(plateTypeName);
		container.setContainerType(plateType);

		Integer numRow = plateType.getRowNo();
		Integer numCol = plateType.getColumnNo();
		Integer capacity = numRow * numCol;

		// Double check to make sure Plate ID doesn't exist OR ASSIGN WHEN
		// SAVING FROM UPLOAD

		// Ext Plate ID
		row = sheet.getRow(1);
		cell = row.getCell(1);
		String extPlateId = cell.getStringCellValue();
		container.setExtContainerId(extPlateId);

		// Project
		row = sheet.getRow(3);
		cell = row.getCell(1);
		String projectName = cell.getStringCellValue();
		Project project = projectManager.getProject(projectName);
		container.setProject(project);

		// Created Date
		row = sheet.getRow(4);
		cell = row.getCell(1);
		String createdDate = cell.getStringCellValue();
		Date date = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(createdDate);
		container.setCreatedDate(date);

		// Comments
		row = sheet.getRow(5);
		cell = row.getCell(1);
		String comments = cell.getStringCellValue();
		container.setComments(comments);

		Set<SamplesInContainer> sics = new HashSet<SamplesInContainer>();

		for (int i = 1; i < capacity; i++) {
			// SampleID is in 9D - Auto Add this during save instead of writing
			// into manifest.
			row = sheet.getRow(7 + i);
			cell = row.getCell(3);
			// Auto assign next available sample Id;
			String largestSampleId = sampleManager.getLargestSampleId(SAMPLE_PREFIX);
			Integer largestSampleNum = Integer.parseInt(largestSampleId.replace(SAMPLE_PREFIX, ""));

			Integer intSampleNum = largestSampleNum + 1;
			String formatNum = String.format("%06d", intSampleNum);
			String intSampleId = SAMPLE_PREFIX + formatNum;

			cell.setCellValue(intSampleId);

			Sample newSample = new Sample(intSampleId);

			// First cell for Well is 9C
			row = sheet.getRow(7 + i);
			cell = row.getCell(2);
			String well = cell.getStringCellValue();

			// extSampleId
			row = sheet.getRow(7 + i);
			cell = row.getCell(4);
			String extSampleId = cell.getStringCellValue();
			newSample.getPatient().setExtSampleId(extSampleId);
			if (extSampleId.isEmpty()) {
				System.out.println("External Sample is empty");
			}

			// vol
			row = sheet.getRow(7 + i);
			cell = row.getCell(5);
			Double vol = cell.getNumericCellValue();
			newSample.setVolumn(vol.floatValue());

			// conc
			row = sheet.getRow(7 + i);
			cell = row.getCell(6);
			Double conc = cell.getNumericCellValue();
			newSample.setOd(conc.floatValue());

			// DNA source - Not saved yet.
			row = sheet.getRow(7 + i);
			cell = row.getCell(7);
			String source = cell.getStringCellValue();

			// DNA type - Not saved yet.
			row = sheet.getRow(7 + i);
			cell = row.getCell(8);
			String type = cell.getStringCellValue();

			// DNA extractMethod - Not saved yet.
			row = sheet.getRow(7 + i);
			cell = row.getCell(9);
			String extractMethod = cell.getStringCellValue();

			// comments
			row = sheet.getRow(7 + i);
			cell = row.getCell(10);
			String sampleComments = cell.getStringCellValue();

			// Temporary placeholder for SampleType as "DNA"
			SampleType tempST = sampleManager.getSampleTypeDAO().getSampleTypeByName("DNA");
			newSample.setSampleType(tempST);
			newSample.setStatus("Stored");
			newSample.getPatient().setProject(project);

			// Try to save samples. The function will check for duplicate ID and
			// throw exception.
			sampleManager.saveSample(newSample);

			// Update Samples In Container
			SamplesInContainer newSic = new SamplesInContainer();
			newSic.setSicId(new Integer(-1));
			newSic.setContainer(container);
			newSic.setSample(newSample);
			newSic.setWell(well);
			newSic.setOperation("I");
			newSic.setOperationDate(new Date());
			sics.add(newSic);

		}

		// Save container if it does not exist.
		container.setSamplesInContainers(sics);
		container.setStatus("Registered");
		containerManager.saveContainer(container);

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

	public void setSampleManager(SampleManager sampleManager) {
		this.sampleManager = sampleManager;
	}

	public ContainerManager getContainerManager() {
		return containerManager;
	}

	public void setContainerManager(ContainerManager containerManager) {
		this.containerManager = containerManager;
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
