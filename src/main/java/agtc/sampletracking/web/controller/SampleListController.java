package agtc.sampletracking.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.web.command.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.comparator.SampleComparator;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.report.SatoLabelPrinter;

public class SampleListController extends BasicController {

	private SampleManager sampleManager;

	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {
		return new SampleListController();
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String action = RequestUtils.getStringParameter(request, "action", "");
		List sampleList = (List) WebUtils.getSessionAttribute(request,
				"sampleList");
		String message = "";

		if (action.equalsIgnoreCase("Print Labels")) {
			// Print
			SatoLabelPrinter satoP = new SatoLabelPrinter();
			// Collections.sort(sampleList,new SampleComparator());
			satoP.printSampleLabel(sampleList);

			message = "Labels have been printed.";
		} else if (action.equalsIgnoreCase("Export Data")) {
			// Write Excel Document as an attachment

			// Format Excel Document using filled in form and master manifest
			String excelFileName = "blankmaster.xls";
			String fullName = MANIFESTPATH + excelFileName;
			InputStream is = new FileInputStream(fullName);

			Workbook wb = formatSampleData(sampleList, is);
			
			Sample firstSample = (Sample) sampleList.get(0);
			
			String fileName = firstSample.getPatient().getIntSampleId() + "List";
			downloadManifest(response, wb, fileName);
			
			return null;
		}

		ModelAndView mav = new ModelAndView("sampleList");
		WebUtils.setSessionAttribute(request, "sampleList", sampleList);
		mav.addObject("sampleList", sampleList);
		mav.addObject("message", message);

		return mav;
	}

	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors) {
		Map models = new HashMap();
		List<Sample> sampleList = (List<Sample>) WebUtils.getSessionAttribute(
				request, "sampleList");

		String message = RequestUtils
				.getStringParameter(request, "message", "");
		if (!message.equals("")) {
			models.put("message", message);
		}

		models.put("sampleList", sampleList);
		return models;
	}

	private Workbook formatSampleData(List sampleList, InputStream is) throws Exception {

		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Sample> sampleIterator = sampleList.iterator();

		Row curRow = sheet.getRow(0);	
		Iterator<Cell> cellIterator = curRow.cellIterator();
		
		Cell curCell = cellIterator.next();
		curCell.setCellValue("Sample Id");
		
		curCell = cellIterator.next();
		curCell.setCellValue("External Id");
		
		curCell = cellIterator.next();
		curCell.setCellValue("Sample Type");
		
		curCell = cellIterator.next();
		curCell.setCellValue("Project");
		
		curCell = cellIterator.next();
		curCell.setCellValue("Volume (mL)");
		
		curCell = cellIterator.next();
		curCell.setCellValue("Conc. (ug/mL)");
		
		curCell = cellIterator.next();
		curCell.setCellValue("Notes");

		curCell = cellIterator.next();
		curCell.setCellValue("Status");

		curCell = cellIterator.next();
		curCell.setCellValue("Location");
		
		Integer rowCounter = 1;
		
		System.out.println(curCell.getStringCellValue());

		// Iterate through the sampleList and populate excel file
		while (sampleIterator.hasNext()) {
			Sample curSample = (Sample) sampleIterator.next();
			
			curRow=sheet.getRow(rowCounter);

			cellIterator = curRow.cellIterator();
			
			curCell=cellIterator.next();		
			curCell.setCellValue(curSample.getPatient().getIntSampleId());
			
			curCell=cellIterator.next();		
			curCell.setCellValue(curSample.getPatient().getExtSampleId());

			curCell=cellIterator.next();		
			curCell.setCellValue(curSample.getSampleType().getName());

			curCell=cellIterator.next();		
			curCell.setCellValue(curSample.getPatient().getProject().getName());

			curCell=cellIterator.next();	
			try{
				curCell.setCellValue(curSample.getVolumn());
			}
			catch (NullPointerException e)
			{
				curCell.setCellValue("");
			}
			
			curCell=cellIterator.next();		
			try{
				curCell.setCellValue(curSample.getOd());
			}
			catch (NullPointerException e)
			{
				curCell.setCellValue("");
			}
			
			curCell=cellIterator.next();		
			curCell.setCellValue(curSample.getNotes());

			curCell=cellIterator.next();
			curCell.setCellValue(curSample.getStatus());
			
			List sics = sampleManager.getSamplesInContainersInBySample(curSample.getSampleId());
			
			if(!sics.isEmpty())
			{
				SamplesInContainer sic = (SamplesInContainer) sics.get(0);
				String containerName = sic.getContainer().getName();
				String containerLoc = "";
				if(sic.getContainer().getLocation() != null)
				{
					 containerLoc = "@" + sic.getContainer().getLocation().getName();
				}
				String sampleLocation = containerName + containerLoc;
				
				curCell=cellIterator.next();
				curCell.setCellValue(sampleLocation);
			}
			
			rowCounter++;
		}
		return workbook;

	}
	
	private void downloadManifest(HttpServletResponse response, Workbook wb, String fileName) throws Exception
	{
		// Write Excel Document as an attachment
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		wb.write(outByteStream);
		byte [] outArray = outByteStream.toByteArray();
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
