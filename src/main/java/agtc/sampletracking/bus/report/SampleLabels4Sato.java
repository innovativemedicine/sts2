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

import com.idautomation.datamatrix.*;
import java.awt.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

// this class generates PDF file for SATO printer, it is no longer used, now use SatoLabelPrinter to generate a text file
// and LabelView command file will detect any new generated files, and print label automatically
public class SampleLabels4Sato extends PdfFileHandler {
	//1cm = 28.35 point
	// one label one page
	private int templateW = 147;
	private int templateL = 68;
	
	private int topLabelX1 = 60;//relate to the temple
	private int topLabelY1 = 10;//relate to the temple
	private int topLabelX2 = 59;//relate to the temple
	private int topLabelY2 = 17;//relate to the temple
	private int topLabelX3 = 62;//relate to the temple
	private int topLabelY3 = 24;//relate to the temple
	
	private int sideLabelX = 0;//relate to the temple
	private int sideLabelYBarcode = 0;//relate to the temple
	private int sideLabelY2 = 15;//relate to the temple
	private int sideLabelY3 = 23;//relate to the temple
	private int sideLabelY4 = 31;//relate to the temple
	
	private int sideLabel2X = 100;//relate to the temple
	
	private int marginY = 0;//related to whole page
	private int marginX = 0;//related to whole page
	// for side label intSampleId and extSampleId
	private float fontSize = 7;
	
	// for top label digit
	private float fontSizeM = 8;
	//for top label text
	private float fontSizeS = 7;
	
	private float barcodeSize = 6;
	
	
	
	
	private Log log = LogFactory.getLog(SampleLabels4Sato.class);
	
	public SampleLabels4Sato(){
		super();
		totalPageY = 70;
		totalPageX =150;
	}
	protected void handleDocument (Document document,PdfWriter writer,Map model) throws DocumentException{
		
		java.util.List sampleList = (java.util.List)model.get("list");
		Integer scapeNo = (Integer)model.get("scapeNo");
		Iterator ir = sampleList.iterator();
		
		try {
		 	
            PdfContentByte cb = writer.getDirectContent();
            BaseFont bf = BaseFont.createFont("Helvetica", "winansi", false); 
     
          while (ir.hasNext()){
    
            	//log.debug("one sample");
            	Sample sample = (Sample)ir.next();
            	String internalId = sample.getPatient().getIntSampleId();
            	String externalId = sample.getPatient().getExtSampleId();
            	Integer sampleId = sample.getSampleId();
            	//log.debug("going to create Template");
            	
            	PdfTemplate template = cb.createTemplate(templateW, templateL);
            	
                
            	//the 1st line of top label
            	String topLabelLineOne = getTopLabelLineOne(internalId);
            	
            	// the 2nd line of top label will just prnt digit
            	String topLabelLineTwo = getTopLabelLineTwo(internalId);
                
               
            	
                //log.debug("have added top label to template");
            	// USING ean8 BARCODE
                /*
                BarcodeEAN codeEAN = new BarcodeEAN();
                codeEAN.setCodeType(Barcode.EAN8);
                codeEAN.setX(0.60f);
                //log.debug("the x is " + codeEAN.getX());
                codeEAN.setBarHeight(barcodeSize);
                codeEAN.setSize(0);
                codeEAN.setCode(formatBarcode(sampleId));
                Image imageBar = codeEAN.createImageWithBarcode(template, null, null);
                */
               
            	
            	DataMatrix bc=new DataMatrix();
                //bc.setSize(50,50);
                bc.setDataToEncode(internalId);
                bc.setPixelsPerCM(118);
                bc.setPreferredFormat(3);
                bc.setXDimensionCM(0.01);
                bc.setEncodingMode(1);
                
            	


                Graphics2D g2 = template.createGraphics(50f,50f);
                
                bc.paint(g2 );


                //the top label
            	//log.debug("going to add top label");
            	template.beginText();
                template.setFontAndSize(bf, fontSizeS);
                template.moveText(topLabelX1,topLabelY3);
                template.showText(topLabelLineOne);
                template.endText();
                
                template.beginText();
                template.setFontAndSize(bf, fontSizeM);
                template.moveText(topLabelX2,topLabelY2);
                template.showText(topLabelLineTwo);
                template.endText();
                
                template.beginText();
                template.setFontAndSize(bf, fontSizeS);
                template.moveText(topLabelX3,topLabelY1);
                template.showText(sample.getSampleType().getSuffix());
                template.endText();
             
          
                // the 1st side label
                template.beginText();
                template.setFontAndSize(bf, fontSize);
                template.moveText(sideLabelX,sideLabelY2);
                template.showText(externalId);
                template.endText();
                
                template.beginText();      
                template.setFontAndSize(bf, fontSize);
                template.moveText(sideLabelX,sideLabelY4);
                template.showText(internalId);
                template.endText();
                
                template.beginText();      
                template.setFontAndSize(bf, fontSize);
                template.moveText(sideLabelX,sideLabelY3);
                String suffix = sample.getSampleType().getSuffix();
                template.showText(suffix);
                template.endText();
                
                
                //log.debug("have added side label text to template");
               // template.addImage(imageBar,imageBar.width(),0,0,imageBar.height(),sideLabelX,sideLabelYBarcode);
                //log.debug("have added side label barcode to template");
                
              
                //the 2nd side label
                template.beginText();
                template.setFontAndSize(bf, fontSize);
                template.moveText(sideLabel2X,sideLabelY2);
                template.showText(externalId);
                template.endText();
                
                template.beginText();      
                template.setFontAndSize(bf, fontSize);
                template.moveText(sideLabel2X,sideLabelY4);
                template.showText(internalId);
                template.endText();
                
                template.beginText();      
                template.setFontAndSize(bf, fontSize);
                template.moveText(sideLabel2X,sideLabelY3);
                template.showText(suffix);
                template.endText();
               
               // template.addImage(imageBar,imageBar.width(),0,0,imageBar.height(),sideLabel2X,sideLabelYBarcode);

                cb.addTemplate(template,marginX,marginY);
         
    			
    		
             		//we go to a new page
    	        document.newPage();
    			
          
		   }
       
            document.close();
            log.debug("document closed");
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
