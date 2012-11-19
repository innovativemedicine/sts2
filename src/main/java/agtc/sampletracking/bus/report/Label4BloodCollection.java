/*
 * Created on Mar 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.report;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.Sample;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Label4BloodCollection extends PdfFileHandler {
	//1cm = 28.35 point
	private int rowNo = 13;
	private int columnNo = 4;
	private int templateW = 140;
	private int templateL = 59;
	
	private int x = 0;//relate to the temple
	private int y1 = 10;//relate to the temple
	private int y2 = 35;//relate to the temple
	
	private int marginY = 10;//related to whole page
	private int marginX = 40;//related to whole page
	private float fontSize = 12;
	private float barcodeSize = 12;
	
	
	
	
	private Log log = LogFactory.getLog(Label4BloodCollection.class);
	
	public Label4BloodCollection(){
		super();
		totalPageY = 825;
		//totalPageY = 790;
		//totalPageX = 595;
		totalPageX = 610;
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
            	
            	PdfTemplate template = cb.createTemplate(templateW, templateL);
            	
                
              
       
            	// USING ean8 BARCODE
                Barcode128 code = new Barcode128();
                code.setCodeType(Barcode.CODE128);
                code.setX(0.75f);
                //log.debug("the x is " + codeEAN.getX());
                code.setBarHeight(barcodeSize);
                code.setSize(0);
                //code.setCode(formatBarcode(sampleId));
                code.setCode(internalId);
                Image imageBar = code.createImageWithBarcode(template, null, null);
         
            
                template.beginText();      
                template.setFontAndSize(bf, fontSize);
                template.moveText(x,y2);
                template.showText(internalId);
                template.endText();
               
                template.addImage(imageBar,imageBar.width(),0,0,imageBar.height(),x,y1);
            	
                int yNo = pageI/columnNo+1;
    			int xNo = pageI % columnNo;
    			
    			int templateX = marginX + (xNo*templateW);
    			int templateY = totalPageY - marginY - (yNo*templateL);
    			
    			//log.debug("the i is "+i + " the adjustY is " + tempYAdjust + " the templateY is " + templateY);
    			
    			cb.addTemplate(template,templateX,templateY);
               
    			
    			
    			
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
	
	
	

}

