/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.*;

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

import agtc.sampletracking.bus.*;

import agtc.sampletracking.bus.report.*;
import agtc.sampletracking.bus.comparator.*;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.Patient;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PrintLabelController extends SimpleFormController {
	private Log log = LogFactory.getLog(PrintLabelController.class);
	
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new Object();
	}
	
	
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		
		String scapeNo = RequestUtils.getStringParameter(request, "scapeNo","");
		String labelType = RequestUtils.getStringParameter(request, "labelType","");
		String printerType = RequestUtils.getStringParameter(request, "printerType","");
		log.debug("scapeNo is  " + scapeNo);
		log.debug("labelType is  " + labelType);
		log.debug("printerType is  " + printerType);
		

	
		//return pdf view with selectList
		PdfFileHandler pdf = null;
		
		SampleListHolder listHolder = (SampleListHolder)WebUtils.getOrCreateSessionAttribute(request.getSession(),"plateListHolder",SampleListHolder.class);
		List selectList = listHolder.getSelectList();
		if(selectList==null || selectList.size()==0){
			errors.reject("error.noContainerSelected","No container selected for printing");
			return showForm(request, response, errors);
		
		}
		Collections.sort(selectList,new ContainerComparator());
		if(printerType.equals("plate")){
			pdf = new PlateLabelsAsA4();
		}else{// only left choice as Box
			pdf = new BoxLabelsAsA4();
		}
			
			
	
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
			listHolder.reSet();
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
