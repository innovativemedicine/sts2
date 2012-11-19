/*
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
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AliasSampleSearchController 
					extends BasicSearchController {
	private AliasManager aliasManager;
	private ProjectManager projectManager;
	private List LSampleProjects;
	private Log log = LogFactory.getLog
						(AliasSampleSearchController.class);


	protected ModelAndView onSubmit(
				javax.servlet.http.HttpServletRequest request, 
				javax.servlet.http.HttpServletResponse response, 
				java.lang.Object command,BindException errors) 
			throws java.lang.Exception{
		
		String action = RequestUtils.getStringParameter
							(request, "action","");
		String[] sampleprojects = RequestUtils.
										getStringParameters(request, 
										"sampleProjects");
		
		List searchResults = new ArrayList();
		List sampleIds = new ArrayList();
		
		/*
		 * Search samples by projects and save it 
		 *  into "searchResults"
		 * 
		 * */
		List l = aliasManager.
				getSampleDAO().
				getSampleByIntSampleIdSampleProject
										(sampleprojects);
		if (l!=null && l.size()>0){
			Iterator it = l.iterator();
			Sample holder=null;
			while(it.hasNext()){
				holder=(Sample)it.next();
				if(holder!=null)
				{
					if(holder.getPatient().getIntSampleId()!=null)
					{
						searchResults.add((Sample)holder);
					}
				}
			}
		}
		
		log.debug("search result  no is " + searchResults.size());		
		if (searchResults.size() < 1) {
			ModelAndView view = 
				new ModelAndView(new RedirectView("aliasSamples.htm"));
			putMessage(request,view.getModel());
			return view;
		}
			
		WebUtils.setSessionAttribute(request,"sampleList",searchResults);
		return new ModelAndView("sampleList", "sampleList", searchResults);
		
	}
	
	protected  List performSearch(List crtList,List lgcList){
		return aliasManager.searchSample(crtList,lgcList);
	}


	protected java.util.Map referenceData(
						javax.servlet.http.HttpServletRequest request)
						throws java.lang.Exception
	{
		Map models = new HashMap();
		List projectList = projectManager.getAllProjects();
		models.put("projectList",projectList);
		return models;
	}	
	 
	
	/**
	 * @return
	 */
	public AliasManager getAliasManager() {
		return aliasManager;
	}
	

	/**
	 * @param manager
	 */
	public void setAliasManager(AliasManager manager) {
		aliasManager = manager;
	}	
	/**
	 * @return Returns the projectManager.
	 */
	public ProjectManager getProjectManager() {
		return projectManager;
	}
	/**
	 * @param projectManager The projectManager to set.
	 */
	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	/*Create fake sample to repsented a internale sample ID
	 * without searching result
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
