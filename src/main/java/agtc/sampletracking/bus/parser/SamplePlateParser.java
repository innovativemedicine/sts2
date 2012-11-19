/*
 * Created on Feb 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.parser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.ContainerType;
import agtc.sampletracking.bus.SampleUniKey;
import agtc.sampletracking.model.SamplePrefix;
import agtc.sampletracking.model.SampleType;
import agtc.sampletracking.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SamplePlateParser extends TextFileHanlder implements ConstantInterface {
	private Log log = LogFactory.getLog(SamplePlateParser.class);
	private int plateFileFormat = -1;
	private Integer defaultSampleDupNo = new Integer(1);
	private Map samplesInWell;
	private ContainerType containerType;
	private int capacity;
	private int maxRow;
	private int maxVolume;
	private String sampleTypeSuffix;
	
	
	public SamplePlateParser(InputStream is,ContainerType containerType,int plateFileFormat,String sampleType){
		super(is);
		this.plateFileFormat = plateFileFormat;
		this.containerType = containerType;
		maxRow = containerType.getRowNo().intValue();
		maxVolume = containerType.getColumnNo().intValue();
		capacity = maxRow*maxVolume;
		samplesInWell = new HashMap();
		this.sampleTypeSuffix = sampleType;
		
	
	}
	/**
	 * return the parsing result, if parsing successed, return "";
	 */
	public String parseSample() throws Exception {
		switch (plateFileFormat) {
			case SAMPLEID_FORMAT:  return parseSampleInSampleIdFormat();
			case PLATE_FORMAT:  return parseSampleInPlateFormat(); 
			case SAMPLEID_WELL_FORMAT:  return parseSampleInSampleIdWellFormat(); 
			case SAMPLEID_SAMPLETYPE_FORMAT:  return parseSampleInSampleIdSampleTypeFormat(); 
			case SAMPLEID_SAMPLETYPE_WELL_FORMAT:  return parseSampleInSampleIdSampleTypeWellFormat(); 
			case SAMPLEID_DUPNO_FORMAT:  return parseSampleInSampleIdDupNoFormat(); 
		}
		return "";
	}
	
	/**
	 * the input file has one column, just the sampleId(the internalid without prefix),allocate the samples in plate
	 from top to bottom, from left to right the first line is title, skip it
	*/
	private String parseSampleInSampleIdFormat() throws Exception{
		String aLine = null;
		int ib = 1;
		br.readLine();
		StringBuffer sb = new StringBuffer();
		while((aLine = br.readLine()) != null) {
			String intSampleId = aLine.trim();
			String well = number2well(ib);
			intSampleId = intSampleId.replaceAll(" ","");
			
			if(validSampleId(intSampleId)){
				SampleUniKey sampleUK = new SampleUniKey(intSampleId,sampleTypeSuffix,defaultSampleDupNo);
				samplesInWell.put(well,sampleUK);
			}
			
			
			ib++;
		}
		log.debug("the number of lines is " + ib);
		if(ib>(capacity+1)){
			sb.append("there are more samples than this container can hold <BR>");
		}
		return sb.toString();
		
		
	}
	
	private String parseSampleInSampleIdDupNoFormat() throws Exception{
		String aLine = null;
		int i = 1;
		int ib = 1;
		br.readLine();
		StringBuffer sb = new StringBuffer();
		while((aLine = br.readLine()) != null ) {
			i++;
			if(aLine.trim().length()>0){
			    String[] st = aLine.split("\\t",-1);
			    String intSampleId = "";
			   
			    String dupNoS = "";
			    try{
			    	intSampleId = st[0];
			    	intSampleId = intSampleId.trim();
			    	intSampleId = intSampleId.replaceAll(" ","");
					 
			    	dupNoS = st[1];
			    	dupNoS = dupNoS.trim();
			    	Integer theDupNo = null;
			    	
				    	try{
				    		theDupNo = new Integer(dupNoS);
				    		String well = number2well(ib);
							log.debug("the well is "+well);
							
							
						 	
							if(validSampleId(intSampleId)){
								SampleUniKey sampleUK = new SampleUniKey(intSampleId,sampleTypeSuffix,theDupNo);
								samplesInWell.put(well,sampleUK);	
							}
				    	}catch(Exception e){
				    		sb.append("in the " + i + " line the duplication no format is not correct ! <br>");
							intSampleId = "";
				    	}
			    	
					
			
					
			    }catch(Exception e){
					sb.append("in the " + i + " line there is not enough column <br>");
					intSampleId = "";
				}
			}
		    ib++;
		}
		if(ib>(capacity+1)){
			sb.append("there are more samples than this container can hold <BR>");
		}
		return sb.toString();
	}
	
   /**the input file has two columns, sampleId and sampleType as suffix,allocate the samples in plate
    from top to bottom, from left to right the first line is title, skip it
    */
	private String parseSampleInSampleIdSampleTypeFormat() throws Exception{
		String aLine = null;
		int i = 1;
		int ib = 1;
		br.readLine();
		StringBuffer sb = new StringBuffer();
		while((aLine = br.readLine()) != null && aLine.trim().length()>0) {
			i++;
		    String[] st = aLine.split("\\t",-1);
		    String intSampleId = "";
		   
		    String sampleType = "";
		    try{
		    	intSampleId = st[0];
		    	intSampleId = intSampleId.trim();
				 
				sampleType = st[1];
				sampleType = sampleType.trim();
				String well = number2well(ib);
				log.debug("the well is "+well);
				
				intSampleId = intSampleId.replaceAll(" ","");
			 	
				if(validSampleId(intSampleId)){
					SampleUniKey sampleUK = new SampleUniKey(intSampleId,sampleType,defaultSampleDupNo);
					samplesInWell.put(well,sampleUK);	
				}
		
				
		    }catch(Exception e){
				sb.append("in the " + i + " line there is not enough column <br>");
				intSampleId = "";
			}
		    ib++;
		}
		
		log.debug("the number of lines is " + i);
		if(i>capacity+1){
			sb.append("there are more samples than this container can hold <BR>");
		}
		
		return sb.toString();
		
	}
	
	/**
	 * the input file has three columns, sampleId and sampleType as suffix,and sample well location
	 */
	private String parseSampleInSampleIdSampleTypeWellFormat() throws Exception{
		String aLine = null;
		int i = 1;
		int ib = 1;
		br.readLine();
		StringBuffer sb = new StringBuffer();
		while((aLine = br.readLine()) != null && aLine.trim().length()>0) {
			i++;
		    String[] st = aLine.split("\\t",-1);
		    String intSampleId = "";
		   
		    String sampleType = "";
		    String well = "";
		    try{
		    	intSampleId = st[0];
		    	intSampleId = intSampleId.trim();
				 
				sampleType = st[1];
				sampleType = sampleType.trim();
				well = st[2];
				well = well.trim();
				well = well.toUpperCase();
				 if(!isWellRightFormat(well)){
				 	sb.append("in the " + i + " line the well format is not corrected <br>");
				 }else{
				 	intSampleId = intSampleId.replaceAll(" ","");
				 	
					if(validSampleId(intSampleId)){
						
						SampleUniKey sampleUK = new SampleUniKey(intSampleId,sampleType,defaultSampleDupNo);
						samplesInWell.put(well,sampleUK);	
					}
					
				 }
				
		    }catch(Exception e){
				sb.append("in the " + i + " line there is not enough column <br>");
				intSampleId = "";
			}
		
		}
		log.debug("the number of lines is " + i);
		if(i>capacity+1){
			sb.append("there are more samples than this container can hold <BR>");
		}
		
		return sb.toString();
		
	}
	
	/**
	 * This method is for plate list,
	 * with format like: InternalSampleId, well(in the right format ("A1"))
	 * the samplesInWell will put well(String),intSampleId(String)
	 * first line is the title, skip it 
	 * 
	 * @throws Exception
	 */
	private String parseSampleInSampleIdWellFormat() throws Exception{
		String aLine = null;
		int i = 1;
		br.readLine();
		StringBuffer sb = new StringBuffer();
		while((aLine = br.readLine()) != null && aLine.trim().length()>0) {
			i++;
		    String[] st = aLine.split("\\t",-1);
		    String intSampleId = "";
		   
		    String well = "";
		    try{
		    	intSampleId = st[0];
		    	intSampleId = intSampleId.trim();
				 
				 well = st[1];
				 well = well.trim();
				 well = well.toUpperCase();
				 if(!isWellRightFormat(well)){
				 	sb.append("in the " + i + " line the well format is not corrected <br>");
				 }else{
				 	intSampleId = intSampleId.replaceAll(" ","");
				 		
					if(validSampleId(intSampleId)){
						
						SampleUniKey sampleUK = new SampleUniKey(intSampleId,sampleTypeSuffix,defaultSampleDupNo);
						samplesInWell.put(well,sampleUK);	
					}
					
				 }
		    }catch(Exception e){
				sb.append("in the " + i + " line there is not enough column <br>");
				intSampleId = "";
			}
		
		}
		log.debug("the number of lines is " + i);
		if(i>capacity+1){
			sb.append("there are more samples than this container can hold <BR>");
		}
		
		return sb.toString();
		
	}
	
//	 the input file is like plate  not head 
	private String parseSampleInPlateFormat() throws Exception {
		String aLine = null;
		int row = 1;
		StringBuffer sb = new StringBuffer();
		while((aLine = br.readLine()) != null) {

			String rowS = number2Letter(row);
			String[] st = aLine.split("\\t",-1);
			
			for(int i=0;i<st.length;i++){
				String thisSampleSuffix = sampleTypeSuffix;
				 String intSampleId = st[i];
				 intSampleId = intSampleId.trim();
				 // well is in the format of !A
				 String well = rowS + (i+1);
				 
				 intSampleId = intSampleId.replaceAll(" ","");
				 if(intSampleId.indexOf("|")>0){
				 	thisSampleSuffix = intSampleId.substring(intSampleId.indexOf("|")+1);
				 	intSampleId = intSampleId.substring(0,intSampleId.indexOf("|"));
				 	
				 }
				
					
				if(validSampleId(intSampleId)){
					
					SampleUniKey sampleUK = new SampleUniKey(intSampleId,thisSampleSuffix,defaultSampleDupNo);
					samplesInWell.put(well,sampleUK);	
				}
				
				
					 
			}
		
			row++;
		}
		log.debug("the number of row is " + row);
		if(row>maxRow+1){
			sb.append("there are more samples than that of this tpe of container <BR>");
		}
		return sb.toString();
		
	}
	
	private boolean validSampleId(String s){
		if(s.equalsIgnoreCase("BLK")){
			return false;
		}else if(s.equalsIgnoreCase("blank")){
			return false;
		}else if(s.equalsIgnoreCase("water")){
			return false;
		}else if(s.equalsIgnoreCase("unknown")){
			return false;
		}else if(s.equalsIgnoreCase("*")){
			return false;
		}else if(s.equalsIgnoreCase("control")){
			return false;
		}else if(s.length()== 0){
			return false;
		}else {
			return true;
		}

	}
	
	/**
	 * 
	 * @param i is the row number of the input file
	 * @return in format of A1
	 */
	protected String number2well(int i){
		
		int row = (i-1)/maxVolume;
		int column = ((i-1) % maxVolume)+1;
	
		return number2Letter(row+1)+column;
	
	}

	protected String number2Letter(int i){
		switch (i) {
			case 1:  return "A";
			case 2:  return "B"; 
			case 3:  return "C"; 
			case 4:  return "D"; 
			case 5:  return "E";
			case 6:  return "F"; 
			case 7:  return "G";
			case 8:  return "H"; 
			case 9:  return "I";
			case 10: return "J"; 
			case 11: return "K"; 
			case 12: return "L"; 
			case 13: return "M";
			case 14: return "N";
			case 15: return "O"; 
			case 16: return "P"; 
		}
		return "";

	}
	
	//check if well is in right fromat like A1
	protected boolean isWellRightFormat(String s){
		return s.matches("[A-J]{1}[0-9]{1,2}");

	}
	


	/**
	 * @return Returns the containerType.
	 */
	public ContainerType getContainerType() {
		return containerType;
	}
	/**
	 * @param containerType The containerType to set.
	 */
	public void setContainerType(ContainerType containerType) {
		this.containerType = containerType;
	}
	/**
	 * @return Returns the samplesInWell.
	 */
	public Map getSamplesInWell() {
		return samplesInWell;
	}
	/**
	 * @param samplesInWell The samplesInWell to set.
	 */
	public void setSamplesInWell(Map samplesInWell) {
		this.samplesInWell = samplesInWell;
	}
}
