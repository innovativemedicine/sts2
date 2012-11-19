/*
 * Created on Feb 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.parser;

import java.io.InputStream;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.ContainerType;
import agtc.sampletracking.model.Project;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SamplePrefix;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SamplesNoWellParser extends SampleParser {
	private List sampleList;
	private Project project;
	private Log log = LogFactory.getLog(SamplesNoWellParser.class);
	public SamplesNoWellParser(InputStream is,SamplePrefix samplePrefix,String isControl){
		super(is,samplePrefix,isControl);
		sampleList = new ArrayList();
	}

	public SamplesNoWellParser(InputStream is,
			SamplePrefix samplePrefix,
			String isControl,
			Project project){
		super(is,samplePrefix,isControl);
		sampleList = new ArrayList();
		this.project = project;
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
		
			i++;
			log.debug("one sample is " + aLine);
		    String[] st = aLine.split("\\t",-1);
		    if(st.length == fieldTitles.length){
			    Sample sample = parseOneLine(st,sb,i);	
			    sample.getPatient().setIsControl(isControl);
			    if (project!=null)
			    	sample.getPatient().setProject(project);
			    sampleList.add(sample);
		    }else{
		    	sb.append("in the " + i + " line the number of column does not match <br>");
		    }		    
		}
		String s=sb.toString();
		return s;
	}
	

	/**
	 * @return Returns the sampleList.
	 */
	public List getSampleList() {
		return sampleList;
	}
	/**
	 * @param sampleList The sampleList to set.
	 */
	public void setSampleList(List sampleList) {
		this.sampleList = sampleList;
	}
}
