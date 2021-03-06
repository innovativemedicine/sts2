package agtc.sampletracking.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.bus.report.SatoLabelPrinter;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SamplesInContainer;

public class SampleListController extends BasicController {

	private SampleManager	sampleManager;

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new SampleListController();
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		List searchResults = (List) WebUtils.getSessionAttribute(request, "sampleList");
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		List<Sample> sampleList = new ArrayList<Sample>();

		// Update the sample list based on checked boxes
		for (int i = 0; i < searchResults.size(); i++) {
			// System.out.println(i);
			Boolean checkBox = ServletRequestUtils.getBooleanParameter(request, "chk" + i, false);

			if (checkBox) {
				Sample selectedSample = (Sample) searchResults.get(i);
				sampleList.add(selectedSample);
			}
		}

		String message = "";

		// Abort and prompt when sample size is zero

		if (sampleList.size() == 0) {
			message = "Warning: No sample selected";
			ModelAndView mav = new ModelAndView("sampleList");

			mav.addObject("message", message);

			return mav;
		}

		WebUtils.setSessionAttribute(request, "sampleList", sampleList);

		if (action.equalsIgnoreCase("Print Labels")) {
			// Queue the number of samples to print
			Integer numLabels = ServletRequestUtils.getIntParameter(request, "numLabels");

			List<Sample> printList = new ArrayList<Sample>();

			if (numLabels > 0) {
				for (int i = 0; i < sampleList.size(); i++) {
					Sample eachSample = (Sample) sampleList.get(i);
					for (int j = 1; j <= numLabels; j++) {
						printList.add(eachSample);
					}

				}
			}

			// Print
			SatoLabelPrinter satoP = new SatoLabelPrinter();

			satoP.printSampleLabel(printList, contextPath);

			message = "Labels have been printed.";
		} else if (action.equalsIgnoreCase("Assign Container")) {
			// ModelAndView mav = new ModelAndView("addSample2Container");
			ModelAndView mav = new ModelAndView(new RedirectView("addSample2Container.htm"));

			return mav;
		} else if (action.equalsIgnoreCase("Export Data")) {
			// Write Excel Document as an attachment

			// Format Excel Document using filled in form and master manifest
			String excelFileName = "blankmaster.xls";
			String fullName = contextPath + MANIFESTPATH + excelFileName;
			InputStream is = new FileInputStream(fullName);

			Workbook wb = formatSampleData(sampleList, is);
			Sample firstSample = (Sample) sampleList.get(0);
			String fileName = firstSample.getPatient().getIntSampleId() + "List";
			exportSampleData(response, wb, fileName);

			return null;
		}

		ModelAndView mav = new ModelAndView("sampleList");
		WebUtils.setSessionAttribute(request, "sampleList", sampleList);

		mav.addObject("sampleList", sampleList);
		mav.addObject("message", message);

		return mav;
	}

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) {
		Map models = new HashMap();
		List<Sample> sampleList = (List<Sample>) WebUtils.getSessionAttribute(request, "sampleList");

		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		models.put("message", message);
		models.put("sampleList", sampleList);

		return models;
	}

	private Workbook formatSampleData(List sampleList, InputStream is) throws Exception {

		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);

		Iterator<Sample> sampleIterator = sampleList.iterator();

		CreationHelper createHelper = workbook.getCreationHelper();

		CellStyle cellDateStyle = workbook.createCellStyle();
		cellDateStyle.setDataFormat(createHelper.createDataFormat().getFormat("mmm/dd/yy"));

		CellStyle cellDecimalStyle = workbook.createCellStyle();
		cellDecimalStyle.setDataFormat(createHelper.createDataFormat().getFormat("0.00"));

		sheet.createRow(0);
		Row curRow = sheet.getRow(0);

		Cell curCell = curRow.getCell(0, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Sample Id");

		curCell = curRow.getCell(1, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("External Id");

		curCell = curRow.getCell(2, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Sample Type");

		curCell = curRow.getCell(3, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Project");

		curCell = curRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Volume");

		curCell = curRow.getCell(5, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Birth Date");

		curCell = curRow.getCell(6, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Received Date");

		curCell = curRow.getCell(7, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Notes");

		curCell = curRow.getCell(8, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Status");

		curCell = curRow.getCell(9, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("# Aliquots");

		curCell = curRow.getCell(10, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Container Loc (Well@Box@Freezer)");

		curCell = curRow.getCell(11, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Conc. (ng/uL)");

		curCell = curRow.getCell(12, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Conc. Date");

		curCell = curRow.getCell(13, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("A260");

		curCell = curRow.getCell(14, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("A280");

		curCell = curRow.getCell(15, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("260/280");

		curCell = curRow.getCell(16, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("260/230");

		curCell = curRow.getCell(17, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Conc. Factor (ng/uL");

		curCell = curRow.getCell(18, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Cursor Pos.");

		curCell = curRow.getCell(19, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("Cursor Abs.");

		curCell = curRow.getCell(20, Row.CREATE_NULL_AS_BLANK);
		curCell.setCellValue("340 Raw");
		Integer rowCounter = 1;

		// Iterate through the sampleList and populate excel file
		while (sampleIterator.hasNext()) {
			Sample curSample = (Sample) sampleIterator.next();

			sheet.createRow(rowCounter);
			curRow = sheet.getRow(rowCounter);

			curCell = curRow.getCell(0, Row.CREATE_NULL_AS_BLANK);
			curCell.setCellValue(curSample.getPatient().getIntSampleId());

			curCell = curRow.getCell(1, Row.CREATE_NULL_AS_BLANK);
			curCell.setCellValue(curSample.getPatient().getExtSampleId());

			curCell = curRow.getCell(2, Row.CREATE_NULL_AS_BLANK);
			curCell.setCellValue(curSample.getSampleType().getName());

			curCell = curRow.getCell(3, Row.CREATE_NULL_AS_BLANK);
			curCell.setCellValue(curSample.getPatient().getProject().getName());

			curCell = curRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDecimalStyle);
				curCell.setCellValue(curSample.getVolumn());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(5, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDateStyle);
				curCell.setCellValue(curSample.getPatient().getBirthDate());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(6, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDateStyle);
				curCell.setCellValue(curSample.getPatient().getReceiveDate());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}
			curCell = curRow.getCell(7, Row.CREATE_NULL_AS_BLANK);
			curCell.setCellValue(curSample.getNotes());

			curCell = curRow.getCell(8, Row.CREATE_NULL_AS_BLANK);
			curCell.setCellValue(curSample.getStatus());

			List sics = sampleManager.getSamplesInContainersInBySample(curSample.getSampleId());

			curCell = curRow.getCell(9, Row.CREATE_NULL_AS_BLANK);
			if (!sics.isEmpty()) {
				curCell.setCellValue(sics.size());
			} else {
				curCell.setCellValue("-");
			}

			curCell = curRow.getCell(10, Row.CREATE_NULL_AS_BLANK);
			// Location is available
			if (!sics.isEmpty()) {

				String sampleLocation = "";

				for (Object o : sics) {
					SamplesInContainer sic = (SamplesInContainer) o;

					String containerName = sic.getContainer().getName();

					String sampleLoc = "";
					String containerLoc = "";

					if (sic.getWell() != null) {
						sampleLoc = sic.getWell() + "@";
					}

					if (sic.getContainer().getLocation() != null) {
						containerLoc = "@" + sic.getContainer().getLocation().getName();
					}

					sampleLocation = sampleLocation + sampleLoc + containerName + containerLoc + ";";
				}

				curCell.setCellValue(sampleLocation);

			} else // Location not specified
			{
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(11, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDecimalStyle);
				curCell.setCellValue(curSample.getOd());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(12, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDateStyle);
				curCell.setCellValue(curSample.getOdDate());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(13, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDecimalStyle);
				curCell.setCellValue(curSample.getOdA260());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(14, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDecimalStyle);
				curCell.setCellValue(curSample.getOdA280());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(15, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDecimalStyle);
				curCell.setCellValue(curSample.getOd260280());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(16, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDecimalStyle);
				curCell.setCellValue(curSample.getOd260230());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(17, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellValue(curSample.getOdFactor());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(18, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellValue(curSample.getOdCursorPos());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(19, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDecimalStyle);
				curCell.setCellValue(curSample.getOdCursorAbs());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			curCell = curRow.getCell(20, Row.CREATE_NULL_AS_BLANK);
			try {
				curCell.setCellStyle(cellDecimalStyle);
				curCell.setCellValue(curSample.getOd340Raw());
			} catch (NullPointerException e) {
				curCell.setCellValue("");
			}

			rowCounter++;
		}
		return workbook;
	}

	private void exportSampleData(HttpServletResponse response, Workbook wb, String fileName) throws Exception {
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

}
