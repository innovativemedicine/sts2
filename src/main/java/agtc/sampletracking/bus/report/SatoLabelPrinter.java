/*
 * Created on Jun 22, 2005
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.report;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.*;

import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.ConstantInterface;

public class SatoLabelPrinter implements ConstantInterface {

	public void printSampleLabel(List sampleList, String contextPath) throws Exception {

		Iterator ir = sampleList.iterator();
		// String filename="e:\\commandFiles\\"+UnqueString.UnqueStr()+".DAT";
		SimpleDateFormat timeStamp = new SimpleDateFormat("ddMMMyyyykkmmss");
		String fileTime = timeStamp.format(new Date());
		String filename = contextPath + LABELPATH + "samples_" + fileTime + ".cmd";
		File outputFile = new File(filename);
		FileWriter out = new FileWriter(outputFile);

		StringBuffer content = new StringBuffer();

		content.append("LABELNAME=\"C:\\LVWIN70\\SAMPLES\\SAMPLE2.LBL\"\r\n");
		content.append("PRINTER=\"SATO CL-412e - (V) on COM1:\"\r\n");
		content.append("DATATYPE=DELIMITED\r\n");
		content.append("DELIMITER=,\r\n");
		content.append("FIELD=EXTID11\r\n");
		content.append("FIELD=EXTID12\r\n");
		content.append("FIELD=INTID1\r\n");
		content.append("FIELD=SUFFIX1\r\n");
		content.append("FIELD=BARCODE1\r\n");
		content.append("FIELD=TOPLINE12\r\n");
		content.append("FIELD=TOPLINE22\r\n");
		content.append("FIELD=SUFFIX2\r\n");
		content.append("FIELD=TOPLINECD12\r\n");
		content.append("FIELD=TOPLINECD22\r\n");
		content.append("FIELD=SUFFIX4\r\n");
		content.append("FIELD=EXTID21\r\n");
		content.append("FIELD=EXTID22\r\n");
		content.append("FIELD=INTID\r\n");
		content.append("FIELD=SUFFIX3\r\n");
		content.append("FIELD=BARCODE2\r\n");
		content.append("FIELD=DATE1\r\n");
		content.append("FIELD=DATE2\r\n");
		content.append("FIELD=CONCENTRATION1\r\n");
		content.append("FIELD=CONCENTRATION2\r\n");
		content.append("FIELD=dupNo1\r\n");
		content.append("FIELD=dupNo2\r\n");
		content.append("LABELDATA=THISFILE");

		while (ir.hasNext()) {

			Sample sample = (Sample) ir.next();
			String internalId = sample.getPatient().getIntSampleId();
			String externalId = sample.getPatient().getExtSampleId();
			String sampleTypeSuffix = sample.getSampleType().getSuffix();
			String sampleDupNo = sample.getSampleDupNo().toString();
			SimpleDateFormat receivedDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			String receivedDate = "";
			
			if (sample.getPatient().getReceiveDate() != null)
			{
				receivedDate = receivedDateFormat.format(sample.getPatient().getReceiveDate()).toUpperCase();
			}
			
			String intIdPre = "";
			String intIdNum = "";

			Pattern pattern = Pattern.compile("([a-zA-Z]+?)(\\d+)");

			Matcher m = pattern.matcher(internalId);
			while (m.find()) {
				intIdPre = m.group(1);
				intIdNum = m.group(2);
			}

			if (externalId == null) {
				externalId = "";
			}

			content.append("\r\n");

			// content.append("FIELD=EXTID11\n");
			content.append(externalId).append(",");
			// content.append("FIELD=EXTID12\n");
			content.append("").append(",");
			// content.append("FIELD=INTID1\n");
			content.append(internalId).append(",");
			// content.append("FIELD=SUFFIX1\n");
			content.append(sampleTypeSuffix).append(",");
			// content.append("FIELD=BARCODE1\n");
			content.append(internalId).append(",");
			// content.append("FIELD=TOPLINE12\n");
			content.append(intIdPre).append(",");
			// content.append("FIELD=TOPLINE22\n");
			content.append(intIdNum).append(",");
			// content.append("FIELD=SUFFIX2\n");
			content.append(sampleTypeSuffix).append(",");
			// content.append("FIELD=TOPLINECD12\n");
			content.append(intIdPre).append(",");
			// content.append("FIELD=TOPLINECD22\n");
			content.append(intIdNum).append(",");
			// content.append("FIELD=SUFFIX4\n");
			content.append(sampleTypeSuffix).append(",");
			// content.append("FIELD=EXTID21\n");
			content.append(externalId).append(",");
			// content.append("FIELD=EXTID22\n");
			content.append("").append(",");
			// content.append("FIELD=INTID\n");
			content.append(internalId).append(",");
			// content.append("FIELD=SUFFIX3\n");
			content.append(sampleTypeSuffix).append(",");
			// content.append("FIELD=BARCODE2\n");
			content.append("S-").append(internalId).append(",");
			// content.append("FIELD=DATE1\n");
			content.append(receivedDate).append(",");
			// content.append("FIELD=DATE2\n");
			content.append(receivedDate).append(",");
			// content.append("FIELD=CONCENTRATION1\n");
			content.append("").append(",");
			// content.append("FIELD=CONCENTRATION2\n");
			content.append("").append(",");
			// content.append("FIELD=dupNo1\n");
			content.append(sampleDupNo).append(",");
			// content.append("FIELD=dupNo2\n");
			content.append(sampleDupNo);
		}
		out.write(content.toString());
		out.close();
	}

	/*
	 * Probably have to update this to printContainerLabel. Then during the
	 * ir.hasNext() loop, check if the container is a plate or a box. If it is a
	 * box, then print out labels for actual containers (C-prefix) If it is a
	 * plate, then print out labels for plates (P-prefix)
	 */

	public void printPlateLabel(List<Container> plateList, String contextPath) throws Exception {
		Iterator<Container> ir = plateList.iterator();

		// String filename="e:\\commandFiles\\"+UnqueString.UnqueStr()+".DAT";
		SimpleDateFormat timeStamp = new SimpleDateFormat("ddMMMyyyykkmmss");
		String fileTime = timeStamp.format(new Date());
		String filename = contextPath + LABELPATH + "plates_" + fileTime + ".cmd";
		File outputFile = new File(filename);
		FileWriter out = new FileWriter(outputFile);

		StringBuffer content = new StringBuffer();

		content.append("LABELNAME=\"C:\\LVWIN70\\SAMPLES\\PLATETEST2.LBL\"\r\n");
		content.append("PRINTER=\"ZDesigner ZM400 200 dpi (ZPL) on Ne00:\"\r\n");
		content.append("DATATYPE=DELIMITED\r\n");
		content.append("DELIMITER=,\r\n");
		content.append("FIELD=2DBAR1\r\n");
		content.append("FIELD=INTID1\r\n");
		content.append("FIELD=EXTID1\r\n");
		content.append("LABELDATA=THISFILE\r\n");

		while (ir.hasNext()) {
			Container plate = (Container) ir.next();
			// printPlateLabel(plate, contextPath);

			String internalId = plate.getName();
			String externalId = plate.getExtContainerId();

			try {
				if (externalId.isEmpty()) {
					externalId = "";
				}
			} catch (NullPointerException e) {
				externalId = "";
			}

			// FIELD=2DBAR1
			// For the barcode: It should be in the format P-internalId
			content.append(internalId).append(",");
			// FIELD=INTID1
			content.append(internalId).append(",");
			// FIELD=EXTID1
			content.append(externalId).append("\r\n");
		}

		out.write(content.toString());
		out.close();

	}
}