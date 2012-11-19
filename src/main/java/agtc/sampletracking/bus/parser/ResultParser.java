/*
 * Created on Dec 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.parser;
import java.io.*;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.bus.MiniResultInfo;
import agtc.sampletracking.bus.manager.TestManager;
import agtc.sampletracking.model.SamplePrefix;
/**
 * @author Gloria Deng
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResultParser extends TextFileHanlder {
    private Log log = LogFactory.getLog(ResultParser.class);
    private List results;
    private SamplePrefix samplePrefix;
    // the following column is 0 based
    private int sampleIdColumnNo;
    private int assayNameColumnNo;
    private int resultColumnNo;

	public ResultParser(InputStream is,SamplePrefix sp,int i1,int i2,int i3){
		super(is);
		samplePrefix = sp;
		sampleIdColumnNo = i1;
	    assayNameColumnNo = i2;
	    resultColumnNo = i3;
		
		results = new LinkedList();
	}

	// this method is for parse result file, the sampleId type is in samplePRefix, first line is title, skip it
	// return the parse result, if successed, then return a empty string
	public String parseResults() throws Exception{
		String aLine = null;
		br.readLine();
		StringBuffer sb = new StringBuffer();
		int il = 1;
		while((aLine = br.readLine()) != null) {
			//log.debug("the line is " + aLine);
		    String[] st = aLine.split("\\t");
		   
		    il++;
		   try{
		   	String sampleId = st[sampleIdColumnNo].trim();
		   	String assayName = st[assayNameColumnNo].trim();
		   	String result = st[resultColumnNo].trim();
		   	//log.debug("the result befor convert is " + result);
		   	
		   	String tempSampleId = "";
		   	sampleId = sampleId.replaceAll(" ","");
		   	sampleId = sampleId.toUpperCase();
		   	result = result.replaceAll("\\.","");
		   	result = result.replaceAll("/","");
		   	result = result.replaceAll(" ","");
		   	result = result.toUpperCase();
//		   	System.out.println("sampleId="+sampleId+" assayName="
//		   			+ assayName+" result="+result);
		   	//log.debug("the result is " + result);
		   	if(validSampleId(sampleId)){
		   		if (samplePrefix!=null){
		   			if(samplePrefix.getDigitNumber().intValue() == -1){
		   				String prefix = samplePrefix.getPrefix();
		   				if( prefix== null || prefix.equals("null")){
		   					prefix = "";
		   				}
		   				sampleId = prefix+sampleId;
		   			}else{
		   				if(sampleId.length()>samplePrefix.getDigitNumber().intValue())
		   				{
						sb.append("in the " + il + " line the sampleId has more digits <br>");
		   				}else{
		   					for (int i=0;i<(samplePrefix.getDigitNumber().intValue()-sampleId.length());i++)
		   					{
		   						tempSampleId += "0";
		   					}
		   					sampleId = samplePrefix.getPrefix()+tempSampleId+sampleId;
		   				}
		   			}
		   		}
				if(result.length() == 1){
					result = result+result;
				}
				if(result.indexOf("NO")>=0){
					result = "00";
				}
				if(result==null || result.length() == 0){
					result = "00";
				}
				//log.debug(sampleId + " " + assayName + " " + result);
				MiniResultInfo mini = new MiniResultInfo(sampleId,assayName,result);
				results.add(mini);
		   	}else{
				//if it is blank skip it
		   		//sb.append("in the " + il + " line the sampleId is not valid <br>");
			}
		   	
		   }catch(Exception e){
			   e.printStackTrace();
		   		sb.append("in the " + il + " line there is not enough column <br>");
		   }
		}
		return sb.toString();
	}

	

    /**
     * @return Returns the results.
     */
    public List getResults() {
        return results;
    }
    /**
     * @param results The results to set.
     */
    public void setResults(List results) {
        this.results = results;
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
		}else if(s.equalsIgnoreCase("c")){
			return false;
		}else if(s.equalsIgnoreCase("empty")){
			return false;
		}else if(s.length()== 0){
			return false;
		}else {
			return true;
		}

	}
}
