/*
 * Created on Apr 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import agtc.sampletracking.bus.report.SatoLabelPrinter;

public class PrintLabel4BloodCollectionController extends SimpleFormController {	
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new Object();
	}
	
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,BindException errors) throws java.lang.Exception{
		
		String scapeNo = RequestUtils.getStringParameter(request, "scapeNo","");
		
		if(scapeNo.isEmpty()){
			scapeNo = "0";
		}
		
		Integer scapeNum = Integer.parseInt(scapeNo);
		
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
		String aLine = "";
		
		List sampleList =  new ArrayList();;

		while((aLine = br.readLine()) != null) {
			String intSampleId = aLine.trim();
			for(int i=1; i<=scapeNum; i++)
			{
				sampleList.add(intSampleId);
			}
		}
		
		is.close();
		
		SatoLabelPrinter satoP = new SatoLabelPrinter();
		satoP.printBloodLabel(sampleList);		
		
//		ModelAndView mav = new ModelAndView("");
//		mav.addObject("message", "Labels have been printed.");
		
		return showForm(request, response, errors);		
	}
	
	
}
