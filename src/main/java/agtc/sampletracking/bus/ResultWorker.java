/*
 * Created on Jan 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus;
import agtc.sampletracking.ConstantInterface;
import agtc.sampletracking.model.Assay;
import agtc.sampletracking.model.Result;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.web.*;
import agtc.sampletracking.web.controller.SamplesSearchController;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.comparator.*;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResultWorker {
	/**
	* Chang ResultList's data structor, so it will be easy 
	* to display on HTML page table 
	* Jianan Xiao, 2004-12-03
	*/
	
	private boolean linkageFormat = false;
	private Log log = LogFactory.getLog(ResultWorker.class);
	
	// resturn the results in the order of sampleIds given by user, for the sampleId that is not in STS, will give empty string
	public Object[][] formatResult(Collection sr,List sampleIds){
//		 assayName as key, index as value starting from 1, the 0 is for head, "SampleId"
		List assays = new ArrayList();
		
		
     
		Iterator it = sr.iterator();
	
		String assayName = null;
		String result = null;
		Integer resultId = null;
		
	
		while (it.hasNext()){
			Result r  = (Result)it.next();
			assayName = r.getAssay().getName();
		
			if(!assays.contains(assayName)){
				assays.add(assayName);
			}
		
		}
	
		Object[][] results = new Object[sampleIds.size()+1][assays.size()+1];
		
		Collections.sort(assays);
	
		// set the head of the result table
		results[0][0] = "sampleId";
		Iterator assayI = assays.iterator();
		while(assayI.hasNext()){
			String name = (String)assayI.next();
			int idx = assays.indexOf(name)+1;
			results[0][idx] = name;
		}
		
		// set the sampleId of the result table
		Iterator sampleI = sampleIds.iterator();
		while(sampleI.hasNext()){
			String sId = (String)sampleI.next();
			int idx = sampleIds.indexOf(sId)+1;
			results[idx][0] = sId;
		}
		it = sr.iterator();
		while (it.hasNext()){
			Result r  = (Result)it.next();
			String sampleId = r.getIntSampleId();
			if(sampleIds.contains(sampleId)){
				assayName = r.getAssay().getName();
				resultId = r.getResultId();
				result =  r.getResult();
				if(linkageFormat){
					result = linkageFormatResult(result);
				}
				MiniResult4Report miniR = new MiniResult4Report(resultId.toString(),result);
				int assayIdx =assays.indexOf(assayName)+1;
				int sampleIdx = sampleIds.indexOf(sampleId)+1;
				results[sampleIdx][assayIdx] = miniR;
			}
		}
		return results;
	}
	
	public Object[][] formatResult(Collection sr){
		
		// assayName as key, index as value starting from 1, the 0 is for head, "SampleId"
		List assays = new ArrayList();
		
		
        //sample's intSampleId as key, index as value starting from 1, the 0 is for head of the table
		List samples = new ArrayList();
		
		Iterator it = sr.iterator();
		String sampleId = null;
		String assayName = null;
		String result = null;
		Integer resultId = null;
		
	
		while (it.hasNext()){
			Result r  = (Result)it.next();
			assayName = r.getAssay().getName();
			sampleId = r.getIntSampleId();
			if(!assays.contains(assayName)){
				assays.add(assayName);
			}
			if(!samples.contains(sampleId)){
				samples.add(sampleId);
			}
		}
	
		Object[][] results = new Object[samples.size()+1][assays.size()+1];
		
		Collections.sort(assays);
		Collections.sort(samples);
		
		// set the head of the result table
		results[0][0] = "sampleId";
		Iterator assayI = assays.iterator();
		while(assayI.hasNext()){
			String name = (String)assayI.next();
			int idx = assays.indexOf(name)+1;
			results[0][idx] = name;
		}
		
		// set the sampleId of the result table
		Iterator sampleI = samples.iterator();
		while(sampleI.hasNext()){
			String sId = (String)sampleI.next();
			int idx = samples.indexOf(sId)+1;
			results[idx][0] = sId;
		}
		
		
		
		it = sr.iterator();
		while (it.hasNext()){
			Result r  = (Result)it.next();
			sampleId = r.getIntSampleId();
			assayName = r.getAssay().getName();
			resultId = r.getResultId();
			result =  r.getResult();
			if(linkageFormat){
				result = linkageFormatResult(result);
			}
			MiniResult4Report miniR = new MiniResult4Report(resultId.toString(),result);
			int assayIdx =assays.indexOf(assayName)+1;
			int sampleIdx = samples.indexOf(sampleId)+1;
			results[sampleIdx][assayIdx] = miniR;
			
			
		}
		return results;
	}
	
	

	/**
	 * @return Returns the linkageFormat.
	 */
	public boolean isLinkageFormat() {
		return linkageFormat;
	}
	/**
	 * @param linkageFormat The linkageFormat to set.
	 */
	public void setLinkageFormat(boolean linkageFormat) {
		this.linkageFormat = linkageFormat;
	}
	
	/**
	 * change the result to linkageFormat
	 */
	private String linkageFormatResult(String originalResult){
		
		String firstS = originalResult.substring(0,1);
		String secondS = originalResult.substring(1);
		//log.debug("the first is " + firstS);
		//log.debug("the secondS is " + secondS);
		
		int first = convert2LinkageFormat(firstS);
		int second = convert2LinkageFormat(secondS);
		if(second < first){
			return (second + " " + first);
		}else{
			return (first + " " + second);
		}
	}
	
	private int convert2LinkageFormat(String s){
		if(s.compareTo("0") == 0){
			return ConstantInterface.GENOTYPE_NO;
		}else if(s.compareTo("A") == 0){
			return ConstantInterface.GENOTYPE_A;
		}else if(s.compareTo("C") == 0){
			return ConstantInterface.GENOTYPE_C;
		}else if(s.compareTo("G") == 0){
			return ConstantInterface.GENOTYPE_G;
		}else{
			return ConstantInterface.GENOTYPE_T;
		}
	}
}
