/*
 * Created on Apr 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.Sample;

public class PrintLabel4BloodCollectionController extends SimpleFormController {
	private Log log = LogFactory.getLog(PrintLabel4BloodCollectionController.class);
	
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new Object();
	}
	
	
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		
		List selectList =  new ArrayList();;
		String scapeNo = RequestUtils.getStringParameter(request, "scapeNo","");
		
		//rewrite using SatoLabelPrinter

//		PdfFileHandler pdf = new Label4BloodCollection();

		MultipartHttpServletRequest  mrequest = (MultipartHttpServletRequest)request;
		MultipartFile aFile = mrequest.getFile("file");
		if(aFile.isEmpty()){
			errors.reject( "error.emptyFile","Uploaded file is empty");
			return showForm(request, response, errors);
		}
	
		InputStream is = aFile.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		br.readLine();
		String aLine = "";
		while((aLine = br.readLine()) != null) {
			String intSampleId = aLine.trim();
			Sample sample = new Sample();
			Patient patient = new Patient();
			patient.setIntSampleId(intSampleId);
			sample.setPatient(patient);
			selectList.add(sample);
		}
		is.close();
		
	
		Map model = new HashMap();
		model.put("list",selectList);
		if("".equals(scapeNo)){
			scapeNo = "0";
		}
		model.put("scapeNo",Integer.valueOf(scapeNo));
		

		
		return null;	
	}
	
	
}
