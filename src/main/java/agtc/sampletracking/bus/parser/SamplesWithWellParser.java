/*
 * Created on Feb 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.parser;

import java.io.InputStream;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SamplesWithWellParser extends SampleParser {
	protected Map samplesInWell;
	protected ContainerType containerType;
	private Log log = LogFactory.getLog(SamplesWithWellParser.class);
	protected int capacity;
	protected int maxRow;
	protected int maxVolume;
	
	public SamplesWithWellParser(InputStream is,ContainerType containerType,SamplePrefix samplePrefix,String isControl){
		super(is,samplePrefix,isControl);
		this.containerType = containerType;
		maxRow = containerType.getRowNo().intValue();
		maxVolume = containerType.getColumnNo().intValue();
		capacity = maxRow*maxVolume;
		samplesInWell = new HashMap();
	}
	
	public SamplesWithWellParser(InputStream is,ContainerType containerType,SamplePrefix samplePrefix){
		super(is,samplePrefix);
		this.containerType = containerType;
		maxRow = containerType.getRowNo().intValue();
		maxVolume = containerType.getColumnNo().intValue();
		capacity = maxRow*maxVolume;
		samplesInWell = new HashMap();
	}
	
	/**
	 * This method is for parse sampleInfo, the sampleprefix, digitNumber of internalId, 
	 * and inputFile format is in samplePrefix
	 * 
	 * @throws Exception
	 */
	public String parseSample() throws Exception {
		String result = "";
		String aLine = null;
		StringBuffer sb = new StringBuffer();
		// first line is the title, skip it
		br.readLine();
		int i = 1;
		while((aLine = br.readLine()) != null && aLine.trim().length()>0) {
			String well = number2well(i);
			i++;
			log.debug("one sample is " + aLine);
		    String[] st = aLine.split("\\t",-1);
		    if(st.length == fieldTitles.length){
			    Sample sample = parseOneLine(st,sb,i);
			    sample.getPatient().setIsControl(isControl);
				samplesInWell.put(well,sample);
		    }else{
		    	sb.append("in the " + i + " line the number of column does not match <br>");
		    }
		    
		}
		return sb.toString();
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
