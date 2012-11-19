/*
 * Created on Nov 2, 2004
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.*;

import org.springframework.validation.BindException;
import org.springframework.web.util.*;

import java.util.*;


/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SamplesSearchController extends BasicSearchController {
	private SampleManager sampleManager;
	
	private Log log = LogFactory.getLog(SamplesSearchController.class);


	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		
		String action = RequestUtils.getStringParameter(request, "action","");
		String sampleIdsInTextArea = RequestUtils.getStringParameter(request, "sampleIdsInTextArea","");
		List searchResults = new ArrayList();
		List sampleIds = new ArrayList();
		SearchCommand sc = (SearchCommand)command;
		String searchField = sc.getSearchField();
		String searchItem = (String)sc.getSearchItem();
		if(searchField.indexOf("date")>0){
			try{
				java.sql.Date.valueOf(searchItem);
			}catch(Exception e){
				errors.rejectValue( "searchItem","error.wrongFormat","Wrong Format");
				return showForm(request, response, errors);
			}
		}
		
		//log.debug("sampleIdsInTextArea is " + sampleIdsInTextArea);
		
		MultipartHttpServletRequest  mrequest = (MultipartHttpServletRequest)request;
		MultipartFile aFile = mrequest.getFile("file");
		if(sampleIdsInTextArea!=null && sampleIdsInTextArea.trim().length()>0){
			log.debug("sampleIdsInTextArea is " + sampleIdsInTextArea);
			String[] ss = sampleIdsInTextArea.trim().split("[,| |;|\\n|\\r|\\t]",0);
			log.debug("the smaple list size is " + ss.length);
			sampleIds = Arrays.asList(ss);
		}else if(!aFile.isEmpty()){
			InputStream is = aFile.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			br.readLine();
			String aLine = "";
			
			while((aLine = br.readLine()) != null) {
				String intSampleId = aLine.trim();
				sampleIds.add(intSampleId);
			}
			is.close();
		}
		
		if(action.equals("SEARCH") && sampleIds.size()>0){
			
			String sampleTypeSuffix = "";
			
			if(searchField.equals("Sample Type Suffix")){
				sampleTypeSuffix = searchItem;
			}
			log.debug("search by sampleIdList file");
			searchResults = sampleManager.searchSampleBySampleIntIdList(sampleIds,sampleTypeSuffix);
		}else{
			searchResults = handleSubmit(request,command,"sample");
		}
		log.debug("search result  no is " + searchResults.size());		
		if (searchResults.size() < 1) {
			ModelAndView view = new ModelAndView(new RedirectView("samples.htm"));
			putMessage(request,view.getModel());
			return view;
		}
		
		String useFor = RequestUtils.getStringParameter(request, "useFor","");
		
		if(useFor.equals("useFor")){
			SampleListHolder sampleListHolder = (SampleListHolder)WebUtils.getOrCreateSessionAttribute(request.getSession(),"sampleListHolder",SampleListHolder.class);
			sampleListHolder.setOriginalList(searchResults);
			ModelAndView view = new ModelAndView(new RedirectView("sampleList4select.htm"));
			return view;
		}
			
		WebUtils.setSessionAttribute(request,"sampleList",searchResults);
		return new ModelAndView("sampleList", "sampleList", searchResults);
		
	}
	
	protected  List performSearch(List crtList,List lgcList){
		return sampleManager.searchSample(crtList,lgcList);
	}


	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request)
							   throws java.lang.Exception
	{
		Map models = new HashMap();
		referenceData(request,models,"sample");
				
		log.debug("referenceData is called");
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

	

}
