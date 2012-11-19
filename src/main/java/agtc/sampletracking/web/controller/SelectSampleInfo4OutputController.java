/*
 * Created on Apr 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;


import java.text.SimpleDateFormat;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;

import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.util.WebUtils;

import agtc.sampletracking.web.command.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.manager.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SelectSampleInfo4OutputController extends BasicController {
	private Log log = LogFactory.getLog(SelectSampleInfo4OutputController.class);
	private SampleManager sampleManager;
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new SelectSampleInfo4OutputCommand();
	}
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		SelectSampleInfo4OutputCommand ssoutC = (SelectSampleInfo4OutputCommand)command;
		log.debug("outExtSampleId is " + ssoutC.isOutExtSampleId());
		List samples = (List)WebUtils.getSessionAttribute(request,"sampleList");
		Iterator ir = samples.iterator();
		StringBuffer sbFilename = new StringBuffer();
		sbFilename.append("filename_");
		sbFilename.append(System.currentTimeMillis());
		sbFilename.append(".xls");
		
		response.setHeader("Cache-Control", "max-age=30");
		
		response.setContentType("application/xls");
		StringBuffer sbContentDispValue = new StringBuffer();
		sbContentDispValue.append("inline");
		sbContentDispValue.append("; filename=");
		sbContentDispValue.append(sbFilename);
						
		response.setHeader(
			"Content-disposition",
			sbContentDispValue.toString());
		
		StringBuffer content = new StringBuffer();
		addTitle(content,ssoutC);
		
	      while (ir.hasNext()){
	
	        	//log.debug("one sample");
	        	Sample sample = (Sample)ir.next();
	        	processingOneSample(content,sample,ssoutC);
	        	
	      }
	      ServletOutputStream sos = response.getOutputStream();
	      sos.print(content.toString());
	      sos.flush();
	      return null;
		
	}
	
	private void addTitle(StringBuffer content,SelectSampleInfo4OutputCommand ssoutC){
		content.append("sampleId").append("\t");
		if(ssoutC.isOutExtSampleId()){
			content.append("External Sample Id").append("\t");
		}
		
		if(ssoutC.isOutAnotherExtSampleId()){
			content.append("Another External Sample Id").append("\t");
		}
		if(ssoutC.isOutReceiveDate()){
			content.append("Sample Receive Date").append("\t");
		}
		if(ssoutC.isOutMadeDate()){
			content.append("Sample Made Date").append("\t");
		}
		if(ssoutC.isOutTransDate()){
			content.append("Sample Transfer Date").append("\t");
		}
		if(ssoutC.isOutRefillDate()){
			content.append("Sample Refill Date").append("\t");
		}
		if(ssoutC.isOutRemoveDate()){
			content.append("Sample Remove Date").append("\t");
		}
		if(ssoutC.isOutOd()){
			content.append("DNA Concentration").append("\t");
		}
		if(ssoutC.isOutOdDate()){
			content.append("DNA Concentration Reading Date").append("\t");
		}
		
		if(ssoutC.isOutVolumn()){
			content.append("DNA Volumn").append("\t");
		}
		if(ssoutC.isOutVolumnDate()){
			content.append("DNA Volumn Reading Date").append("\t");
		}
		
		if(ssoutC.isOutNotes()){
			content.append("Sample Note").append("\t");
		}
		
		if(ssoutC.isOutStatus()){
			content.append("Sample Status").append("\t");
		}
		
		if(ssoutC.isOutSampleDupNo()){
			content.append("Sample Duplication No").append("\t");
		}
		
		if(ssoutC.isOutSampleType()){
			content.append("Sample Type").append("\t");
		}
		
		if(ssoutC.isOutSampleLoci()){
			content.append("Sample location").append("\t");
		}
		
		if(ssoutC.isOutFname()){
			content.append("Patient First Name").append("\t");
		}
		
	
		if(ssoutC.isOutLname()){
			content.append("Patient Last Name").append("\t");
		}
		
		if(ssoutC.isOutMname()){
			content.append("Patient Middle Name").append("\t");
		}
		
		if(ssoutC.isOutAge()){
			content.append("Patient Age").append("\t");
		}
		
		if(ssoutC.isOutBirthDate()){
			content.append("Patient Birthdate").append("\t");
		}
		
		if(ssoutC.isOutGender()){
			content.append("Patient Gender").append("\t");
		}
		
		if(ssoutC.isOutPatientNote()){
			content.append("Patient Note").append("\t");
		}
		
		
		
		if(ssoutC.isOutIsControl()){
			content.append("Control Sample?").append("\t");
		
		}
		
		if(ssoutC.isOutFamilyId()){
			content.append("Patient Family ID").append("\t");
		}
		  
		content.append("\n");
	}
	
	private void processingOneSample(StringBuffer content,Sample sample,SelectSampleInfo4OutputCommand ssoutC){
		content.append(sample.getPatient().getIntSampleId()).append("\t");
		if(ssoutC.isOutExtSampleId()){
			if(sample.getPatient().getExtSampleId()!=null){
				content.append(sample.getPatient().getExtSampleId());
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutAnotherExtSampleId()){
			if(sample.getPatient().getAnotherExtSampleId()!=null){
				content.append(sample.getPatient().getAnotherExtSampleId());
			}
			content.append("\t");
		}
		if(ssoutC.isOutReceiveDate()){
			if(sample.getReceiveDate()!=null){
				content.append(formatDate(sample.getReceiveDate()));
			}
			content.append("\t");
		}
		if(ssoutC.isOutMadeDate()){
			if(sample.getMadeDate()!=null){
				content.append(formatDate(sample.getMadeDate()));
			}
			content.append("\t");
		}
		if(ssoutC.isOutTransDate()){
			if(sample.getTransDate()!=null){
				content.append(formatDate(sample.getTransDate()));
			}
			content.append("\t");
		}
		if(ssoutC.isOutRefillDate()){
			if(sample.getRefillDate()!=null){
				content.append(formatDate(sample.getRefillDate()));
			}
			content.append("\t");
		}
		if(ssoutC.isOutRemoveDate()){
			if(sample.getRemoveDate()!=null){
				content.append(formatDate(sample.getRemoveDate()));
			}
			content.append("\t");
		}
		if(ssoutC.isOutOd()){
			if(sample.getOd()!=null){
				content.append(sample.getOd());
			}
			content.append("\t");
		}
		if(ssoutC.isOutOdDate()){
			if(sample.getOdDate()!=null){
				content.append(formatDate(sample.getOdDate()));
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutVolumn()){
			if(sample.getVolumn()!=null){
				content.append(sample.getVolumn());
			}
			content.append("\t");
		}
		if(ssoutC.isOutVolumnDate()){
			if(sample.getVolumnDate()!=null){
				content.append(formatDate(sample.getVolumnDate()));
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutNotes()){
			if(sample.getNotes()!=null){
				content.append(sample.getNotes());
			}
			content.append("\t");
			
		}
		
		if(ssoutC.isOutStatus()){
			if(sample.getStatus()!=null){
				content.append(sample.getStatus());
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutSampleDupNo()){
			if(sample.getSampleDupNo()!=null){
				content.append(sample.getSampleDupNo());
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutSampleType()){
			if(sample.getSampleType()!=null){
				content.append(sample.getSampleType().getSuffix());
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutSampleLoci()){
			List sics = sampleManager.getSamplesInContainersInBySample(sample.getSampleId());
			
			Iterator i = sics.iterator();
			while (i.hasNext()){
				SamplesInContainer sic = (SamplesInContainer)i.next();
				Container container = sic.getContainer();
				content.append(container.getName()).append(" ");
				if(sic.getWell()!=null){
					content.append(sic.getWell());
				}
				content.append(";");
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutFname()){
			if(sample.getPatient().getFname()!=null){
				content.append(sample.getPatient().getFname());
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutLname()){
			if(sample.getPatient().getLname()!=null){
				content.append(sample.getPatient().getLname());
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutMname()){
			if(sample.getPatient().getMname()!=null){
				content.append(sample.getPatient().getMname());
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutAge()){
			if(sample.getPatient().getAge()!=null){
				content.append(sample.getPatient().getAge());
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutBirthDate()){
			if(sample.getPatient().getBirthDate()!=null){
				content.append(formatDate(sample.getPatient().getBirthDate()));
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutGender()){
			if(sample.getPatient().getGender()!=null){
				content.append(sample.getPatient().getGender());
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutPatientNote()){
			if(sample.getPatient().getNote()!=null){
				content.append(sample.getPatient().getNote());
			}
			content.append("\t");
		}
		
		
		
		if(ssoutC.isOutIsControl()){
			if(sample.getPatient().getIsControl()!=null){
				content.append(sample.getPatient().getIsControl());
			}
			content.append("\t");
		}
		
		if(ssoutC.isOutFamilyId()){
			if(sample.getPatient().getFamilyId()!=null){
				content.append(sample.getPatient().getFamilyId());
			}
			content.append("\t");
		}
		content.append("\n");
	}
	
	private String formatDate(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
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
