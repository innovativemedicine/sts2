/*
 * Created on Mar 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.Container;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BoxLabelsAsA4 extends PdfFileHandler{
	// 1cm = 28.35 point

	private int rowNo = 10;
	private int columnNo = 3;
	private int templateW = 197;
	private int templateL = 71;
	
	// for print starting point
	private int x = 0;//relate to the temple
	
	// for the end x of columnText for name 
	private int x1 = 185;//relate to the temple
	
	// for barcode and date and concentration
	private int y = 3;//relate to the temple
	
	
	private int textY1 = 15;//relate to the temple
	
	private int textY2 = 48;//relate to the temple
	
//	 for the date and concentration the same line with barcode
	private int x2 = 55;//relate to the temple
	
	
	private int marginY = 25;//related to whole page
	private int marginX = 5;//related to whole page
	
	private float fontSize = 10;
	private float barcodeSize = 10;
	
	private Log log = LogFactory.getLog(BoxLabelsAsA4.class);
	
	public BoxLabelsAsA4(){
		super();
		//totalPageY = 842;
		totalPageY = 790;
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
            		dateAndConcentration += "      " + concentration.toString() + "ug/ml";
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
                
                // for name
                ColumnText ct = new ColumnText(template); 
                ct.setSimpleColumn(x, textY1, x1, textY2, fontSize, Element.ALIGN_LEFT);
            	ct.addText(new Phrase(name));
            	ct.go();


          
      
                template.beginText();
                template.setFontAndSize(bf, fontSize);
                template.moveText(x2,y+10);
                template.showText(dateAndConcentration);
                template.endText();
            
                //log.debug("going to add the barcode image to the template ");
                template.addImage(imageBar,imageBar.width(),0,0,imageBar.height(),x,y);
                
                
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
