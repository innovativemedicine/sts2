/*
 * Created on Jun 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.report;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.text.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.Sample;
import agtc.sampletracking.web.controller.AddSingleSampleController;
import agtc.sampletracking.bus.comparator.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SatoLabelPrinter {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy ");
	private Log log = LogFactory.getLog(AddSingleSampleController.class);
	
	public void printSatoLabel(List selectList) throws Exception{
		
		Iterator ir = selectList.iterator();
		String filename="e:\\commandFiles\\"+UnqueString.UnqueStr()+".DAT";
		File outputFile = new File(filename);
        FileWriter out = new FileWriter(outputFile);
        
		
		
		StringBuffer content = new StringBuffer();
		
		
	      while (ir.hasNext()){
	
	        	//log.debug("one sample");
	        	Sample sample = (Sample)ir.next();
	        	String internalId = sample.getPatient().getIntSampleId();
	        	String externalId = sample.getPatient().getExtSampleId();
	        	if(externalId==null || externalId.equals("null")){
	        		externalId = "";
	        	}
	        	String sampleTypeSuffix = sample.getSampleType().getSuffix();
	        	//Integer sampleDupNo = sample.getSampleDupNo();
	        	content.append(getExtIdFirstLine(externalId)).append(",");
	        	content.append(getExtIdSecondLine(externalId)).append(",");
	        	content.append(internalId).append(",");
	        	content.append(sampleTypeSuffix).append(",");
	        	content.append(internalId).append(",");
	        	content.append(getTopLabelLineOne(internalId)).append(",");
	        	content.append(getTopLabelLineTwo(internalId)).append(",");
	        	content.append(sampleTypeSuffix).append(",");
	        	content.append(getTopLabelLineOne(internalId)).append(",");
	        	content.append(getTopLabelLineTwo(internalId)).append(",");
	        	content.append(sampleTypeSuffix).append(",");
	        	content.append(getExtIdFirstLine(externalId)).append(",");
	        	content.append(getExtIdSecondLine(externalId)).append(",");
	           	content.append(internalId).append(",");
	           	content.append(sampleTypeSuffix).append(",");
	           	content.append(internalId).append(",");
	           	Date date = sample.getMadeDate();
	           	
	           	if(date==null){
	           		date = sample.getReceiveDate();
	           	}
	           	
	           	//log.debug("the date is " + date);	
	           	//log.debug("formated date is "+ dateFormat.format(date));
				
	            if(date != null){
	            	content.append(dateFormat.format(date)).append(",");
	            	content.append(dateFormat.format(date)).append(",");
		        
	            }else{
	            	content.append(" ").append(",");
	            	content.append(" ").append(",");
	            }
	            
	            Float concentration = sample.getOd();
	            if(concentration!=null && concentration.compareTo(new Float(0.0f))!=0 ){
	            	log.debug("the original concentration is " + concentration);
	            	DecimalFormat myFormatter = new DecimalFormat("###.#");
	            	String cntS = myFormatter.format(concentration.doubleValue());
	            	log.debug("the formatted concentration is " + cntS);

	            	content.append(cntS).append("ug/ml").append(",");
	            	content.append(cntS).append("ug/ml").append(",");
	            }else{
	            	content.append(" ").append(",");
		           	content.append(" ").append(",");
	            }
	            
	            Integer sampleDupNo = sample.getSampleDupNo();
	            log.debug("sample dup no is "+ sampleDupNo);
	            if(sampleDupNo!=null && sampleDupNo.intValue()!=1){
	            	String dupNoS = "("+sampleDupNo.intValue()+")";
	            	content.append(dupNoS).append(",");
	            	content.append(dupNoS).append("\n");
	            }else{
	            	content.append(" ").append(",");
		           	content.append(" ").append("\n");
	            }
	     }
	      out.write(content.toString());
	      out.close();
	  
	}
	
	private String getExtIdFirstLine(String extId){
		if(extId.length()>=13){
			return extId.substring(0,13);
		}else{
			return extId;
		}
	}
	
	private String getExtIdSecondLine(String extId){
		if(extId.length()>13){
			return extId.substring(13);
		}else{
			return "";
		}
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
