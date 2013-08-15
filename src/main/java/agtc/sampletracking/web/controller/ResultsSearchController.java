/*
 * Created on Dec 2, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.MiniResult4Report;
import agtc.sampletracking.bus.ResultWorker;
import agtc.sampletracking.bus.comparator.AssayComparator;
import agtc.sampletracking.bus.manager.TestManager;
import agtc.sampletracking.web.command.SearchCommand;

public class ResultsSearchController extends BasicSearchController {
	private TestManager	testManager;
	private Log			log	= LogFactory.getLog(ResultsSearchController.class);

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		String format = ServletRequestUtils.getStringParameter(request, "format", "");
		String linkageFormat = ServletRequestUtils.getStringParameter(request, "linkageFormat", "");

		List searchResults = new ArrayList();
		String[] assayIds = request.getParameterValues("assayIds");

		List sampleIds = new ArrayList();

		List crtList = new ArrayList();
		WebUtils.setSessionAttribute(request, "resultCriteriaList", crtList);
		List lgcList = new ArrayList();
		WebUtils.setSessionAttribute(request, "resultLogicalList", lgcList);

		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
		MultipartFile aFile = mrequest.getFile("file");
		if (!aFile.isEmpty()) {
			InputStream is = aFile.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			br.readLine();
			String aLine = "";

			while ((aLine = br.readLine()) != null) {
				String intSampleId = aLine.trim();
				sampleIds.add(intSampleId);
			}
			is.close();
			SearchCommand sc = new SearchCommand();
			sc.setOperator("in");
			sc.setSearchField("Internal Sample Id");
			sc.setSearchItems(sampleIds);
			crtList.add(sc);
			lgcList.add("AND");
		}

		if (assayIds != null && assayIds.length > 0) {
			List searchItems = new ArrayList();
			for (int a = 0; a < assayIds.length; a++) {
				searchItems.add(new Integer(assayIds[a]));
			}
			SearchCommand sc = new SearchCommand();
			sc.setOperator("in");
			sc.setSearchField("assayId");
			sc.setSearchItems(searchItems);
			crtList.add(sc);
			lgcList.add("AND");
		}

		if (action.equals("SEARCH")) {
			if (format == null || format.length() == 0) {

				String err = "Output format is required.";

				ModelAndView mav = new ModelAndView(new RedirectView("results.htm"));
				mav.addObject("err", err);

				return mav;
			}

		}

		searchResults = handleSubmit(request, command, "result");

		log.debug(" retrieve no is " + searchResults.size());
		if (searchResults.size() < 1) {
			ModelAndView view = new ModelAndView(new RedirectView("results.htm"));
			putMessage(request, view.getModel());
			return view;
		}

		// multiple tests found

		ResultWorker rw = new ResultWorker();
		if (linkageFormat.equals("yes")) {
			rw.setLinkageFormat(true);
		}

		if (format.equals("plainText")) {
			if (searchResults.size() > 1000000) {
				String message = "The amount of retrieved genotype" + "results is too large to be handled,"
						+ "please give more specified search " + "criteria ! <br>";
				return new ModelAndView("errorPage", "message", message);
			}

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

			response.setHeader("Content-disposition", sbContentDispValue.toString());

			Object[][] rs = null;

			rw.formatResult(searchResults);
			if (sampleIds.size() > 0) {
				rs = rw.formatResult(searchResults, sampleIds);
			} else {
				rs = rw.formatResult(searchResults);
			}
			// String fileName = "genotypeResult.txt" ;

			StringBuffer sb = new StringBuffer();
			Object[] header = rs[0];
			for (int i = 0; i < header.length; i++) {
				sb.append((String) header[i]).append("\t");
			}
			sb.append("\n");

			for (int b = 1; b < rs.length; b++) {
				sb.append((String) rs[b][0]);

				for (int c = 1; c < rs[b].length; c++) {
					MiniResult4Report rar = (MiniResult4Report) rs[b][c];
					sb.append("\t");
					if (rar != null) {
						sb.append(rar.getResult());
					}
				}
				sb.append("\n");
			}

			ServletOutputStream sos = response.getOutputStream();
			sos.print(sb.toString());
			sos.flush();
			return null;

		} else {
			if (searchResults.size() > 100000) {
				String message = "The amount of retrieved genotype" + "results is too large to be handled,"
						+ "please give more specified search criteria ! <br>";
				return new ModelAndView("errorPage", "message", message);
			}

			Object[][] rs = null;

			rw.formatResult(searchResults);
			if (sampleIds.size() > 0) {
				rs = rw.formatResult(searchResults, sampleIds);
			} else {
				rs = rw.formatResult(searchResults);
			}

			return new ModelAndView("resultList", "resultList", rs);
		}

	}

	protected List performSearch(List crtList, List lgcList) {
		return testManager.searchResult(crtList, lgcList);
	}

	protected Map referenceData(javax.servlet.http.HttpServletRequest request) throws java.lang.Exception {
		Map models = new HashMap();
		referenceData(request, models, "result");

		List assays = testManager.getAssayDAO().getAssays();
		Collections.sort(assays, new AssayComparator());

		String err = ServletRequestUtils.getStringParameter(request, "err", "");
		models.put("err", err);

		models.put("assayList", assays);

		return models;
	}

	public TestManager getTestManager() {
		return testManager;
	}

	public void setTestManager(TestManager manager) {
		testManager = manager;
	}

}
