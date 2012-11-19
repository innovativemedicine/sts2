/*
 * Created on Mar 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus;

/**
 * @author Gloria
 *
 *THis class is for report result to presentation tier
 */
public class MiniResult4Report {
	private String resultId;
	private String result;
	
	public MiniResult4Report(String id, String r){
		resultId = id;
		result = r;
	}
	
	/**
	 * @return Returns the result.
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result The result to set.
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return Returns the resultId.
	 */
	public String getResultId() {
		return resultId;
	}
	/**
	 * @param resultId The resultId to set.
	 */
	public void setResultId(String resultId){
		this.resultId = resultId;
	}
}
