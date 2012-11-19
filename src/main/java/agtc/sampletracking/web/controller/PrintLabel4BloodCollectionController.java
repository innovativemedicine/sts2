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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

import agtc.sampletracking.bus.SampleListHolder;
import agtc.sampletracking.bus.comparator.ContainerComparator;
import agtc.sampletracking.bus.comparator.SampleComparator;
import agtc.sampletracking.bus.report.BoxLabelsAsA4;
import agtc.sampletracking.bus.report.Label4BloodCollection;
import agtc.sampletracking.bus.report.PdfFileHandler;
import agtc.sampletracking.bus.report.PlateLabelsAsA4;
import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.Sample;

import com.lowagie.text.DocumentException;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PrintLabel4BloodCollectionController extends SimpleFormController {
	private Log log = LogFactory.getLog(PrintLabel4BloodCollectionController.class);
	
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new Object();
	}
	
	
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		
		List selectList =  new ArrayList();;
		String scapeNo = RequestUtils.getStringParameter(request, "scapeNo","");
		PdfFileHandler pdf = new Label4BloodCollection();
		
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
		
		ByteArrayOutputStream baosPDF = null;
		
		try
		{
			baosPDF = pdf.generatePDFDocumentBytes(model);
			
			StringBuffer sbFilename = new StringBuffer();
			sbFilename.append("filename_");
			sbFilename.append(System.currentTimeMillis());
			sbFilename.append(".pdf");

			////////////////////////////////////////////////////////
			// Note: 
			//
			// It is important to set the HTTP response headers 
			// before writing data to the servlet's OutputStream 
			//
			////////////////////////////////////////////////////////
			//
			//
			// Read the HTTP 1.1 specification for full details
			// about the Cache-Control header
			//
			response.setHeader("Cache-Control", "max-age=30");
			
			response.setContentType("application/pdf");
			//
			//
			// The Content-disposition header is explained
			// in RFC 2183
			//
			//    http://www.ietf.org/rfc/rfc2183.txt
			//
			// The Content-disposition value will be in one of 
			// two forms:
			//
			//   1)  inline; filename=foobar.pdf
			//   2)  attachment; filename=foobar.pdf
			//
			// In this servlet, we use "inline"
			//
			StringBuffer sbContentDispValue = new StringBuffer();
			sbContentDispValue.append("inline");
			sbContentDispValue.append("; filename=");
			sbContentDispValue.append(sbFilename);
							
			response.setHeader(
				"Content-disposition",
				sbContentDispValue.toString());

			response.setContentLength(baosPDF.size());

			ServletOutputStream sos = response.getOutputStream();
			
			baosPDF.writeTo(sos);
			
			sos.flush();
			
			return null; 
		}
		catch (DocumentException dex)
		{
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.println(
					this.getClass().getName() 
					+ " caught an exception: " 
					+ dex.getClass().getName()
					+ "<br>");
			writer.println("<pre>");
			dex.printStackTrace(writer);
			writer.println("</pre>");
		}
		finally
		{
			if (baosPDF != null)
			{
				baosPDF.reset();
			}
			
		}
		
		return null;	
	}
	
	
}
