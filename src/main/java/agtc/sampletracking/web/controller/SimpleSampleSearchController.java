/*
 * Created on 2005-09-09
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;


import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.*;
import org.springframework.validation.BindException;
import org.springframework.web.util.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Jianan Xiao 2005-09-09
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SimpleSampleSearchController 
					extends BasicSearchController {
	private SampleManager sampleManager;
	private List LSampleTypes;
	private Log log = LogFactory.getLog
						(SimpleSampleSearchController.class);


	protected ModelAndView onSubmit(
				javax.servlet.http.HttpServletRequest request, 
				javax.servlet.http.HttpServletResponse response, 
				java.lang.Object command,BindException errors) 
			throws java.lang.Exception{
		
		String action = RequestUtils.getStringParameter
							(request, "action","");
		String sampleIdsInTextArea = RequestUtils.
										getStringParameter(request, 
										"sampleIdsInTextArea","");
		String[] sampletypes = RequestUtils.
								getStringParameters(request, 
								"sampleTypes");
		List searchResults = new ArrayList();
		List sampleIds = new ArrayList();
 		
		MultipartHttpServletRequest  
			mrequest = (MultipartHttpServletRequest)request;
		MultipartFile aFile = mrequest.getFile("file");
		if(sampleIdsInTextArea!=null 
				&& sampleIdsInTextArea.trim().length()>0){
			log.debug("sampleIdsInTextArea is " + sampleIdsInTextArea);
			String[] ss = sampleIdsInTextArea.trim().split(",|\n",-1);
			log.debug("the smaple list size is " + ss.length);
			sampleIds = String2List(sampleIdsInTextArea);
		}else if(!aFile.isEmpty()){
			InputStream is = aFile.getInputStream();
			BufferedReader br = 
				new BufferedReader(new InputStreamReader(is));
			br.readLine();
			String aLine = "";
			
			while((aLine = br.readLine()) != null) {
				String intSampleId = aLine.trim();
				sampleIds.add(intSampleId);
			}
			is.close();
		}
		
		/*
		 * Search samples by internal sample ID and save it 
		 *  into "searchResults"
		 * 
		 * Jianan Xiao 2005-09-09
		 * */
		if( sampleIds.size()>0){
			for(int i=0;i<sampleIds.size();i++){
				String c = (String) sampleIds.get(i);
				List l = sampleManager.
						getSampleDAO().
						getSampleByIntSampleIdSampleType
												(c,sampletypes);
				if (l==null || l.size()<=0){ //no this sample
					searchResults.add((Sample)fakeSample(c));
					continue;
				}
				Iterator it = l.iterator();
				while(it.hasNext()){
					searchResults.add((Sample)it.next());
				}
			}		 
		} 
		
		log.debug("search result  no is " + searchResults.size());		
		if (searchResults.size() < 1) {
			ModelAndView view = 
				new ModelAndView(new RedirectView("simpleSamples.htm"));
			putMessage(request,view.getModel());
			return view;
		}
		
		String useFor = 
			RequestUtils.getStringParameter(request, "useFor","");
		
		if(useFor.equals("useFor")){
			SampleListHolder sampleListHolder = 
				(SampleListHolder)WebUtils.
				getOrCreateSessionAttribute(
						request.getSession(),
						"sampleListHolder",
						SampleListHolder.class);
			sampleListHolder.setOriginalList(searchResults);
			ModelAndView view = 
				new ModelAndView(new RedirectView("sampleList4select.htm"));
			return view;
		}
			
		WebUtils.setSessionAttribute(request,"sampleList",searchResults);
		return new ModelAndView("sampleList", "sampleList", searchResults);
		
	}
	
	protected  List performSearch(List crtList,List lgcList){
		return sampleManager.searchSample(crtList,lgcList);
	}


	protected java.util.Map referenceData(
						javax.servlet.http.HttpServletRequest request)
						throws java.lang.Exception
	{
		Map models = new HashMap();
		LSampleTypes = sampleManager.
						getSampleTypeDAO().
						getSampleTypes();
		models.put("LSampleTypes",LSampleTypes);		
	//	referenceData(request,models,"sample");
		return models;
	}	
	 
	
	/**
	 * @return
	 */
	public SampleManager getSampleManager() {
		return sampleManager;
	}
	

	/**
	 * @param manager
	 */
	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}	

	/*Create fake sample to repsented a internale sample ID
	 * without searching result
	 * Jianan Xiao 2005-09-12
	 * */
	private Sample fakeSample(String intSampleId){
		Patient p = new Patient();
		p.setIntSampleId(intSampleId);
		Sample s=new Sample();
		s.setPatient(p);
		s.setSampleId(new Integer(-1));
		
		return s;
	}
	/*
	 * Change input "String" to "List" that delimited
	 * by non-word boundary
	 * Jianan Xiao 2005-09-12
	 * */
	public static List String2List(String s){
		List l = new ArrayList();
		String regularEx = "[,| |;|\\n|\\r|\\t]";
		String[] sa = s.split(regularEx,0);
		for(int i=0;i<sa.length;i++){
			String st = sa[i].trim();
			if (st.length()>0) 
				l.add(st);
		}
		
		if(l.size()<1) l=null;
		return l;
	}
}
