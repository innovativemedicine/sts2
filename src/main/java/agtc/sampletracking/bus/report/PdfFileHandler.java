/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.report;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 * @author Gloria Deng
 *
 * This class is the super class of various pdf handlers. 
 */
public class PdfFileHandler {
	protected  int totalPageY;
	protected int totalPageX;
	
	// the general operation for all sub class
	public ByteArrayOutputStream generatePDFDocumentBytes(Map model)
			throws DocumentException
			
	{	
		Document doc = new Document(new Rectangle(totalPageX,totalPageY));
		
		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		PdfWriter docWriter = null;

		try
		{
			docWriter = PdfWriter.getInstance(doc, baosPDF);
			doc.open();
			handleDocument(doc,docWriter,model);
			
		}
		catch (DocumentException dex)
		{
			baosPDF.reset();
			throw dex; 
		}
		finally
		{
			if (doc != null)
			{
				doc.close();
			}
			if (docWriter != null)
			{
				docWriter.close();
			}
		}

		if (baosPDF.size() < 1)
		{
			throw new DocumentException(
				"document has "
				+ baosPDF.size()
				+ " bytes");		
		}
		return baosPDF;
	}
	
	//sub class should overwrite this method to do real work
	protected void handleDocument (Document doc,PdfWriter docWriter,Map model) throws DocumentException{
		
	}
	 
	//to give a 8 numeric character, the 8th character is the checksum number
	protected String formatBarcode(Integer sampleId){
		String s = "";
		String originalS = sampleId.toString();
		int evenSum = 0;
		int oddSum = 0;
		boolean isEvenPosition = true;
		//log.debug("the sampleId is " + sampleId);
		
		// get the checksum number, start from the last digit, 12th
		for (int i=(originalS.length()-1);i>=0;i--){
			int number = Integer.parseInt(originalS.substring(i,i+1));
			//log.debug("i is " + i + "is EvenPosion is " + isEvenPosition + " current number is " + number);
			if(isEvenPosition){
				evenSum += number;
				isEvenPosition = false;
			}else{
				oddSum += number;
				isEvenPosition = true;
			}
		}
		//log.debug("the evenSum is "+ evenSum + " the oddSum is  " + oddSum);
		int checkSum = evenSum*3+oddSum;
		checkSum = 10 - (checkSum % 10);
		if(checkSum == 10){
			checkSum = 0;
		}
		String checkSumS = Integer.toString(checkSum);
		
		for (int i=0;i<(7-originalS.length());i++){
			s += "0";
		}
		
		//log.debug("the sampleId is " + s+sampleId.toString());
		return s+originalS+checkSumS;
	}

}
