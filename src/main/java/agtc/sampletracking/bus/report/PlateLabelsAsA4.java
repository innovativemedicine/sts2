/*
 * Created on Dec 21, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.report;

import java.text.SimpleDateFormat;
import java.util.*;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.Container;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 */
public class PlateLabelsAsA4 extends PdfFileHandler{
	// 1cm = 28.35 point

	private int rowNo = 20;
	private int columnNo = 2;
	private int templateW = 290;
	private int templateL = 35;
	
	private int barcodeX = 0;//relate to the temple
	private int barcodeY = 3;//relate to the temple
	
	private int textX = 55;//relate to the temple
	private int textY = 15;//relate to the temple
	
	private int textY2 = 5;//relate to the temple
	
	
	
	
	private int marginY = 14;//related to whole page
	private int marginX = 15;//related to whole page
	
	private float fontSize = 9;
	private float barcodeSize = 9;
	
	private Log log = LogFactory.getLog(PlateLabelsAsA4.class);
	
	public PlateLabelsAsA4(){
		super();
		totalPageY = 825;
		//totalPageY = 790;
		//totalPageX = 595;
		totalPageX = 610;
	}
	
	protected void handleDocument (Document document,PdfWriter writer,Map model) throws DocumentException{
		
		java.util.List plateList = (java.util.List)model.get("list");
		Integer scapeNo = (Integer)model.get("scapeNo");
		
		Iterator ir = plateList.iterator();
		 try {
		 	
            PdfContentByte cb = writer.getDirectContent();
            BaseFont bf = BaseFont.createFont("Helvetica", "winansi", false); 
            
            
            
            int scapeN = scapeNo.intValue();
    		int i = scapeN;
    		int pageI = i;
    		int tempYAdjust = 0; // to adjust the Y  position of temple along the page
    		
    		
           
          
            while (ir.hasNext()){
    	
            	
            	Container plate = (Container)ir.next();
            	String name = plate.getName();
            	String dateAndConcentration = "";
            	Date dateD = plate.getCreatedDate();
            	if(dateD != null){
            		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            		dateAndConcentration = dateFormat.format(dateD);
            	}else{
            		// to align it when date is null
            		dateAndConcentration = "          ";
            	}
            	
            	Float concentration = plate.getConcentration();
            	if(concentration != null){
            		dateAndConcentration += "   " + concentration.toString() + "ug/ml";
            	}
            	
            	Integer plateId = plate.getContainerId();
            	
            	
            	PdfTemplate template = cb.createTemplate(templateW, templateL);
            	
            	
            	BarcodeEAN codeEAN = new BarcodeEAN();
                codeEAN.setCodeType(Barcode.EAN8);
                codeEAN.setBarHeight(barcodeSize);
                codeEAN.setX(0.75f);
                codeEAN.setSize(0);
                codeEAN.setCode(formatBarcode(plateId));
                Image imageBar = codeEAN.createImageWithBarcode(template, null, null);
          
                template.beginText();
                template.setFontAndSize(bf, fontSize);
                template.moveText(textX,textY);
                template.showText(name);
                template.endText();
                
                template.beginText();
                template.setFontAndSize(bf, fontSize);
                template.moveText(textX,textY2);
                template.showText(dateAndConcentration);
                template.endText();
            
                //log.debug("going to add the barcode image to the template ");
                template.addImage(imageBar,imageBar.width(),0,0,imageBar.height(),barcodeX,barcodeY);
                
                
               // adjust the y posistion of each template on the page
                if((i%(3*columnNo))==0){
    				tempYAdjust++;
    				
    				
    				//log.debug("the i is " + i + " AdjustY is " + tempYAdjust);
    			}
    			
    			
                if((i%(6*columnNo))==0){
    				tempYAdjust++;
    				tempYAdjust++;
    				tempYAdjust++;
    				
    				//log.debug("the i is " + i + " AdjustY is " + tempYAdjust);
    			}
            	int yNo = pageI/columnNo+1;
    			int xNo = pageI % columnNo;
    			
    			int templateX = marginX + (xNo*templateW);
    			int templateY = totalPageY - marginY - (yNo*templateL)-(tempYAdjust);
    			
    			//log.debug("the i is "+i + " the adjustY is " + tempYAdjust + " the templateY is " + templateY);
    			
    			cb.addTemplate(template,templateX,templateY);
    			//log.debug("have added one label");
    			
    			
    			
    			i++;
    			pageI++;
    			
    			if((i%(rowNo*columnNo)) ==  0){
    				pageI = 0;
    				tempYAdjust = 0;
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
	
//	
	
	

}
