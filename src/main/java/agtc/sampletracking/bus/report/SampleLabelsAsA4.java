/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.report;

import com.lowagie.text.Document;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;

import agtc.sampletracking.model.*;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SampleLabelsAsA4 extends PdfFileHandler {
	//1cm = 28.35 point
	private int rowNo = 10;
	private int columnNo = 4;
	private int templateW = 151;
	private int templateL = 76;
	
	private int topLabelX1 = 0;//relate to the temple
	private int topLabelY1 = 51;//relate to the temple
	private int topLabelX2 = 0;//relate to the temple
	private int topLabelY2 = 43;//relate to the temple
	private int topLabelX3 = 0;//relate to the temple
	private int topLabelY3 = 36;//relate to the temple
	
	private int sideLabelX = 47;//relate to the temple
	private int sideLabelYBarcode = 57;//relate to the temple
	private int sideLabelY2 = 51;//relate to the temple
	private int sideLabelY3 = 43;//relate to the temple
	private int marginY = 50;//related to whole page
	private int marginX = 10;//related to whole page
	private float fontSize = 10;
	private float fontSizeM = 9;
	private float fontSizeS = 8;
	private float barcodeSize = 7;
	
	
	
	
	private Log log = LogFactory.getLog(SampleLabelsAsA4.class);
	
	public SampleLabelsAsA4(){
		super();
		totalPageY = 842;
		totalPageX = 595;
	}
	
	protected void handleDocument (Document document,PdfWriter writer,Map model) throws DocumentException{
		
		java.util.List sampleList = (java.util.List)model.get("list");
		Integer scapeNo = (Integer)model.get("scapeNo");
		Iterator ir = sampleList.iterator();
		
		try {
		 	
            PdfContentByte cb = writer.getDirectContent();
            BaseFont bf = BaseFont.createFont("Helvetica", "winansi", false); 
            
            
            
            int scapeN = scapeNo.intValue();
    		int i = scapeN;
    		int pageI = i;
    		
    		
           
        
           while (ir.hasNext()){
    
            	
            	Sample sample = (Sample)ir.next();
            	String internalId = sample.getPatient().getIntSampleId();
            	String externalId = sample.getPatient().getExtSampleId();
            	Integer sampleId = sample.getSampleId();
            	
            	// the 1st line of top label will just print Letters
            	String topLabelLineOne = getTopLabelLineOne(internalId);
            	
            	// the 2nd line of top label will just prnt digit
            	String topLabelLineTwo = getTopLabelLineTwo(internalId);
            	
            	PdfTemplate template = cb.createTemplate(templateW, templateL);
            	
                
                //for the top label
            
            	template.beginText();
                template.setFontAndSize(bf, fontSizeS);
                template.moveText(topLabelX1,topLabelY1);
                template.showText(topLabelLineOne);
                template.endText();
                
                template.beginText();
                template.setFontAndSize(bf, fontSizeM);
                template.moveText(topLabelX2,topLabelY2);
                template.showText(topLabelLineTwo);
                template.endText();
                
                template.beginText();
                template.setFontAndSize(bf, fontSizeS);
                template.moveText(topLabelX3,topLabelY3);
                template.showText(sample.getSampleType().getSuffix());
                template.endText();
       
            	// USING ean8 BARCODE
                BarcodeEAN codeEAN = new BarcodeEAN();
                codeEAN.setCodeType(Barcode.EAN8);
                codeEAN.setX(0.75f);
                //log.debug("the x is " + codeEAN.getX());
                codeEAN.setBarHeight(barcodeSize);
                codeEAN.setSize(0);
                codeEAN.setCode(formatBarcode(sampleId));
                Image imageBar = codeEAN.createImageWithBarcode(template, null, null);
         
                template.beginText();
                template.setFontAndSize(bf, fontSize);
                template.moveText(sideLabelX,sideLabelY3);
                template.showText(externalId);
                template.endText();
                
                template.beginText();      
                template.setFontAndSize(bf, fontSize);
                template.moveText(sideLabelX,sideLabelY2);
                String sideLabelIntId = internalId+sample.getSampleType().getSuffix();
                template.showText(sideLabelIntId);
                template.endText();
               
                template.addImage(imageBar,imageBar.width(),0,0,imageBar.height(),sideLabelX,sideLabelYBarcode);
            	
            	int yNo = pageI/columnNo+1;
    			int xNo = pageI % columnNo;
    			int templateX = marginX + (xNo*templateW);
    			int templateY = totalPageY - marginY - (yNo*templateL);
    			cb.addTemplate(template,templateX,templateY);
    			//log.debug("have added one label");
    			
    			
    			
    			i++;
    			pageI++;
    			
    			if((i%(rowNo*columnNo)) ==  0){
    				pageI = 0;
    				
             		//we go to a new page
    	            document.newPage();
    			}
    			
          
			}
       
            document.close();
            System.out.println("Finished.");
        }
        catch (Exception de) {
            de.printStackTrace();
        }
       
    }
	
	
	//	to place the top label, internalId is always 12 characters
	private void placeTopLabel(PdfTemplate template, String sampleId){
		String result = sampleId.substring(0,3)+"\n"+sampleId.substring(3,9)+"\n"+sampleId.substring(9);
       		
	}
	
	private String getTopLabelLineOne(String internalId){
		for(int i=0;i<internalId.length();i++){
			char c = internalId.charAt(i);
			if(Character.isDigit(c)){
				return internalId.substring(0,i);
			}
		}
		return "";
	}
	
	private String getTopLabelLineTwo(String internalId){
		for(int i=0;i<internalId.length();i++){
			char c = internalId.charAt(i);
			if(Character.isDigit(c)){
				return internalId.substring(i);
			}
		}
		return "";
	}

}
