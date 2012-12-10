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
import agtc.sampletracking.bus.comparator.*;

/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SatoLabelPrinter {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy ");
	
	public void printSampleLabel(List sampleList) throws Exception{
		
		Iterator ir = sampleList.iterator();
//		String filename="e:\\commandFiles\\"+UnqueString.UnqueStr()+".DAT";
		String filename="/Users/nderoo324/Documents/Workspace/sts2/label/samples" + UnqueString.UnqueStr() + ".dat";
		File outputFile = new File(filename);
        FileWriter out = new FileWriter(outputFile);
		
		StringBuffer content = new StringBuffer();
		
	      while (ir.hasNext()){
	
	        	Sample sample = (Sample)ir.next();
	        	String internalId = sample.getPatient().getIntSampleId();
	        	String externalId = sample.getPatient().getExtSampleId();
	        	String sampleTypeSuffix = sample.getSampleType().getSuffix();
	        	String sampleDupNo = sample.getSampleDupNo().toString();

	        	if(externalId==null || externalId.equals("null")){
	        		externalId = "";
	        	}
	        	// For the barcode: It should be in the format S-internalId-sampleTypeSuffix-sampleDupNo
	        	content.append("S").append("-");	
	        	content.append(internalId).append("-");
	         	content.append(sampleTypeSuffix).append("-");
	        	content.append(sampleDupNo).append(",");
	        	// Also include the external ID and internal ID as text string if there's room
	        	content.append(externalId).append(",");
	        	content.append(internalId).append(",");
	        	
	        	content.append("\n");
	     }
	      out.write(content.toString());
	      out.close();
	  
	}
	
	public void printSatoLabel(List selectList) throws Exception{
		
		Iterator ir = selectList.iterator();
//		String filename="e:\\commandFiles\\"+UnqueString.UnqueStr()+".DAT";
		String filename="/Users/nderoo324/Documents/Workspace/sts2/" + UnqueString.UnqueStr() + ".dat";
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
	           	
	            if(date != null){
	            	content.append(dateFormat.format(date)).append(",");
	            	content.append(dateFormat.format(date)).append(",");
		        
	            }else{
	            	content.append(" ").append(",");
	            	content.append(" ").append(",");
	            }
	            
	            Float concentration = sample.getOd();
	            if(concentration!=null && concentration.compareTo(new Float(0.0f))!=0 ){
	            	DecimalFormat myFormatter = new DecimalFormat("###.#");
	            	String cntS = myFormatter.format(concentration.doubleValue());

	            	content.append(cntS).append("ug/ml").append(",");
	            	content.append(cntS).append("ug/ml").append(",");
	            }else{
	            	content.append(" ").append(",");
		           	content.append(" ").append(",");
	            }
	            
	            Integer sampleDupNo = sample.getSampleDupNo();
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
