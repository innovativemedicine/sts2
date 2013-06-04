/*
 * Created on Jun 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.report;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.text.*;

import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.bus.comparator.*;

/**
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SatoLabelPrinter {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy ");

	public void printSampleLabel(List sampleList) throws Exception {

		Iterator ir = sampleList.iterator();
		// String filename="e:\\commandFiles\\"+UnqueString.UnqueStr()+".DAT";
		SimpleDateFormat timeStamp = new SimpleDateFormat("ddMMMyyyykkmm");
		String fileTime = timeStamp.format(new Date());
		String filename = "/Users/nderoo324/Documents/Workspace/sts2/label/samples_"
				+ fileTime + ".dat";
		File outputFile = new File(filename);
		FileWriter out = new FileWriter(outputFile);

		StringBuffer content = new StringBuffer();

		while (ir.hasNext()) {

			Sample sample = (Sample) ir.next();
			String internalId = sample.getPatient().getIntSampleId();
			String externalId = sample.getPatient().getExtSampleId();
			String sampleTypeSuffix = sample.getSampleType().getSuffix();
			String sampleDupNo = sample.getSampleDupNo().toString();

			if (externalId == null) {
				externalId = "";
			}
			// For the barcode: It should be in the format
			// S-internalId-sampleTypeSuffix
			content.append("S").append("-");
			content.append(internalId).append("-");
			content.append(sampleTypeSuffix).append(",");
			// Also include the external ID and internal ID as text string if
			// there's room
			content.append(externalId).append(",");
			content.append(internalId).append(",");

			content.append("\n");
		}
		out.write(content.toString());
		out.close();
	}

	public void printPlateLabel(List plateList) throws Exception {
		Iterator ir = plateList.iterator();
		
		// String filename="e:\\commandFiles\\"+UnqueString.UnqueStr()+".DAT";
		SimpleDateFormat timeStamp = new SimpleDateFormat("ddMMMyyyykkmm");
		String fileTime = timeStamp.format(new Date());
		String filename = "/Users/nderoo324/Documents/Workspace/sts2/label/plates_"
				+ fileTime + ".dat";
		File outputFile = new File(filename);
		FileWriter out = new FileWriter(outputFile);

		StringBuffer content = new StringBuffer();
		
		while (ir.hasNext()) {
			Container plate = (Container) ir.next();
			printPlateLabel(plate);
			
			String internalId = plate.getName();
			String externalId = plate.getExtContainerId();

			if (externalId.isEmpty()) {
				externalId = "";
			}
			// For the barcode: It should be in the format P-internalId
			content.append("P").append("-");
			content.append(internalId).append(",");
			// Also include the external ID and internal ID as text string if
			// there's room
			content.append(externalId).append(",");
			content.append(internalId).append(",");
			content.append("\n");

		}
		
		out.write(content.toString());
		out.close();
		
	}
	public void printPlateLabel(Container plate) throws Exception {

		// String filename="e:\\commandFiles\\"+UnqueString.UnqueStr()+".DAT";
		SimpleDateFormat timeStamp = new SimpleDateFormat("ddMMMyyyykkmm");
		String fileTime = timeStamp.format(new Date());
		String filename = "/Users/nderoo324/Documents/Workspace/sts2/label/plates_"
				+ fileTime + ".dat";
		File outputFile = new File(filename);
		FileWriter out = new FileWriter(outputFile);

		StringBuffer content = new StringBuffer();

		String internalId = plate.getName();
		String externalId = plate.getExtContainerId();

		if (externalId.isEmpty()) {
			externalId = "";
		}
		// For the barcode: It should be in the format P-internalId
		content.append("P").append("-");
		content.append(internalId).append(",");
		// Also include the external ID and internal ID as text string if
		// there's room
		content.append(externalId).append(",");
		content.append(internalId).append(",");
		content.append("\n");

		out.write(content.toString());
		out.close();
	}

	public void printBloodLabel(List sampleList) throws Exception {
		Iterator ir = sampleList.iterator();
		// String filename="e:\\commandFiles\\"+UnqueString.UnqueStr()+".DAT";
		SimpleDateFormat timeStamp = new SimpleDateFormat("ddMMMyyyykkmm");
		String fileTime = timeStamp.format(new Date());
		String filename = "/Users/nderoo324/Documents/Workspace/sts2/label/bloods"
				+ fileTime + ".dat";
		File outputFile = new File(filename);
		FileWriter out = new FileWriter(outputFile);

		StringBuffer content = new StringBuffer();

		while (ir.hasNext()) {

			String sampleId = (String) ir.next();

			content.append(sampleId).append(",");
			content.append(sampleId).append(",");
			content.append("\n");
		}
		out.write(content.toString());
		out.close();
	}

}