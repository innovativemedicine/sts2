/*
 * Created on Dec 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.bus.MiniResultInfo;

/**
 * @author Gloria Deng
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */

//public class ResultParser extends TextFileHanlder {
public class ResultParser {

	private Log				log	= LogFactory.getLog(ResultParser.class);
	private List			results;
	// the following column is 0 based
	private int				sampleIdColumnNo;
	private int				assayNameColumnNo;
	private int				resultColumnNo;
	private BufferedReader	br;

	public ResultParser(InputStream is, int i1, int i2, int i3) {
		
		br = new BufferedReader(new InputStreamReader(is));

		sampleIdColumnNo = i1;
		assayNameColumnNo = i2;
		resultColumnNo = i3;

		results = new LinkedList();
	}

	// this method is for parse result file, the sampleId type is in
	// samplePRefix, first line is title, skip it
	// return the parse result, if success, then return a empty string
	
	public String parseResults() throws Exception {
		String aLine = null;
		br.readLine();
		StringBuffer sb = new StringBuffer();
		int il = 1;
		while ((aLine = br.readLine()) != null) {
			String[] st = aLine.split("\\t");

			il++;
			try {
				String sampleId = st[sampleIdColumnNo].trim();
				String assayName = st[assayNameColumnNo].trim();
				String result = st[resultColumnNo].trim();

				sampleId = sampleId.replaceAll(" ", "");
				sampleId = sampleId.toUpperCase();
				result = result.replaceAll("\\.", "");
				result = result.replaceAll("/", "");
				result = result.replaceAll(" ", "");
				result = result.toUpperCase();

				if (validSampleId(sampleId)) {

					if (result.length() == 1) {
						result = result + result;
					}
					if (result.indexOf("NO") >= 0) {
						result = "00";
					}
					if (result == null || result.length() == 0) {
						result = "00";
					}
					MiniResultInfo mini = new MiniResultInfo(sampleId, assayName, result);
					results.add(mini);
				} else {
					// if it is blank skip it
				}

			} catch (Exception e) {
				e.printStackTrace();
				sb.append("in the " + il + " line there is not enough column <br>");
			}
		}
		return sb.toString();
	}

	public List getResults() {
		return results;
	}

	
	public void setResults(List results) {
		this.results = results;
	}

	private boolean validSampleId(String s) {
		if (s.equalsIgnoreCase("BLK")) {
			return false;
		} else if (s.equalsIgnoreCase("blank")) {
			return false;
		} else if (s.equalsIgnoreCase("water")) {
			return false;
		} else if (s.equalsIgnoreCase("unknown")) {
			return false;
		} else if (s.equalsIgnoreCase("*")) {
			return false;
		} else if (s.equalsIgnoreCase("control")) {
			return false;
		} else if (s.equalsIgnoreCase("c")) {
			return false;
		} else if (s.equalsIgnoreCase("empty")) {
			return false;
		} else if (s.length() == 0) {
			return false;
		} else {
			return true;
		}

	}
}
