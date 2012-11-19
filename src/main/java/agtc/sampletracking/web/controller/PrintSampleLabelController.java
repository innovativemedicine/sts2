/*
 * Created on Jul 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.comparator.SampleComparator;
import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.bus.report.SatoLabelPrinter;
import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SampleType;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PrintSampleLabelController extends BasicController {
	private Log log = LogFactory.
						getLog(PrintSampleLabelController.class);
	private SampleManager sampleManager;
	private AGTCManager agtcManager;
	
	public PrintSampleLabelController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
	}
	
	protected Object formBackingObject(HttpServletRequest request)
	throws ServletException {
		return new Object();
	}

	protected ModelAndView onSubmit(
			javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response,
			java.lang.Object command,BindException errors) 
	throws Exception {
		
		String sampleIdsInTextArea = RequestUtils.
										getStringParameter(request, 
												"sampleIdsInTextArea",
												"");
		String labelNo = RequestUtils.getStringParameter(request, 
										"labelNo","");
		int sampleDupNo =  RequestUtils.getIntParameter(request, 
										"sampleDupNo",1);
		log.debug("sampleDupNo is "+ sampleDupNo);
		List sampleIds = new ArrayList();
		List sampleTypeSuffixes = new ArrayList();
		
		if(sampleIdsInTextArea==null || 
				sampleIdsInTextArea.trim().length()== 0){
			errors.reject("error.required",
					new String[]{"Internal SampleId"},
					"Internal SampleId is required");
			return showForm(request, response, errors);
		}
		
		int labels = 0;
		try{
			labels = Integer.parseInt(labelNo);
		}catch(Exception e){
			errors.reject( "error.general","Invalid number input ! ");
			return showForm(request, response, errors);
		}
		
		String[] ss = sampleIdsInTextArea.trim().split("[,| |;|\\n|\\r|\\t]",0);
		for(int a=0;a<ss.length;a++){
			sampleIds.add(ss[a].trim().toUpperCase());
		}
 		
	
		List allSampleTypes = agtcManager.getSampleTypes();
		Iterator ist = allSampleTypes.iterator();
		int counter = 0;
		
		while(ist.hasNext()){
			SampleType st = (SampleType)ist.next();
			String suffix = st.getSuffix();
			String submitted = RequestUtils.getStringParameter(request, suffix,"");
			if(!submitted .equals("")){
				sampleTypeSuffixes.add(suffix);
				counter++;
			
			}
		}
		
		if(counter==0){
			errors.reject("error.required",
					new String[]{"Sample Type"},
					"Sample Type is required");
			return showForm(request, response, errors);
		}
		
		List printSampleList = new ArrayList();
		List sampleList = null;
		
		try{
			sampleList = sampleManager.
						getSamples(sampleIds,sampleTypeSuffixes,
								new Integer(sampleDupNo));
			if(sampleList == null || sampleList.size() == 0){
				errors.reject( "error.noneSample",
						"No sample existed in STS ! ");
				return showForm(request, response, errors);
			}
			
		}catch(Exception e){
			log.debug(e.getMessage());
			errors.reject( "error.general",
					"Errors occur when processing your request ! ");
			return showForm(request, response, errors);
		}
		
		if(labels>0){
			for(int i=1;i<=labels;i++){
				printSampleList.addAll(sampleList);
			}
		}
		Collections.sort(printSampleList,new SampleComparator());
		SatoLabelPrinter satoP = new SatoLabelPrinter();
		satoP.printSatoLabel(printSampleList);
		String message = 
			"<br> Please pick up your labels at the Label printer !";
		String sampleIdsNotInSTS = getSampleIdsNoInSTS
									(sampleIds,sampleList,
											sampleTypeSuffixes,
											sampleDupNo);
		if(sampleIdsNotInSTS != null){
			message += sampleIdsNotInSTS;
		}
		
		Map myModel = new HashMap();
	
		myModel.put("message",message);
	
		return new ModelAndView("successComplete",myModel);
	}

	protected java.util.Map referenceData(
			javax.servlet.http.HttpServletRequest request,
				java.lang.Object command,Errors errors)
							   
	{
		Map models = new HashMap();
		List numbers = new ArrayList();
		for(int i = 1;i<11;i++){
			numbers.add(new Integer(i));
			log.debug("add one number " + i);
		}
	
		List allSampleTypes = agtcManager.getSampleTypes();
		
		models.put("availableSampleTypes",allSampleTypes);
		models.put("numbers",numbers);
		return models;
	}
	
	private String  getSampleIdsNoInSTS(List sampleIds,
			List sampleList,List sampleTypeSuffixes,int sampleDupNo){
		//put String intSampleId + sample type suffix  of actural sample List in the list
		List realList = new ArrayList();
		StringBuffer result = new StringBuffer();
		for(int i=0;i<sampleList.size();i++){
			Sample s = (Sample)sampleList.get(i);
			realList.add(s.getPatient().getIntSampleId()
					+s.getSampleType().getSuffix());
		}
		
			
		for(int a=0;a<sampleIds.size();a++){
			for(int b=0;b<sampleTypeSuffixes.size();b++){
				String temps = (String)sampleIds.get(a);
				
				if (temps==null || temps.length()<1 ) continue;
				String theS = temps+(String)sampleTypeSuffixes.get(b);
				
				//if (sampleDupNo>1){
				//	theS += "(" + sampleDupNo+")";
				//}
				if(!realList.contains(theS)){
					result.append(theS).append("<br>");
				}
			}
		}
		if(result.length()>0){
			result.insert(0,
					"<br> The following samples are not in STS."
					+"Can not print label for them! <br>");
			return result.toString();
		}else{
			return null;
		}
	}

	/**
	 * @return Returns the agtcManager.
	 */
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}
	/**
	 * @param agtcManager The agtcManager to set.
	 */
	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
	/**
	 * @return Returns the sampleManager.
	 */
	public SampleManager getSampleManager() {
		return sampleManager;
	}
	/**
	 * @param sampleManager The sampleManager to set.
	 */
	public void setSampleManager(SampleManager sampleManager) {
		this.sampleManager = sampleManager;
	}
}
