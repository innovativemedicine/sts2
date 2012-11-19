/*
 * Created on Apr 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SampleUniKey {
	String intSampleId;
	String sampleTypeSuffix;
	Integer sampleDupNo;
	
	
	public SampleUniKey(String intSampleId,String sampleTypeSuffix,Integer sampleDupNo){
		this.intSampleId = intSampleId;
		this.sampleDupNo = sampleDupNo;
		this.sampleTypeSuffix = sampleTypeSuffix;
	}
	/**
	 * @return Returns the intSampleId.
	 */
	public String getIntSampleId() {
		return intSampleId;
	}
	/**
	 * @param intSampleId The intSampleId to set.
	 */
	public void setIntSampleId(String intSampleId) {
		this.intSampleId = intSampleId;
	}
	
	/**
	 * @return Returns the sampleDupNo.
	 */
	public Integer getSampleDupNo() {
		return sampleDupNo;
	}
	/**
	 * @param sampleDupNo The sampleDupNo to set.
	 */
	public void setSampleDupNo(Integer sampleDupNo) {
		this.sampleDupNo = sampleDupNo;
	}
	
	/**
	 * @return Returns the sampleTypeSuffix.
	 */
	public String getSampleTypeSuffix() {
		return sampleTypeSuffix;
	}
	/**
	 * @param sampleTypeSuffix The sampleTypeSuffix to set.
	 */
	public void setSampleTypeSuffix(String sampleTypeSuffix) {
		this.sampleTypeSuffix = sampleTypeSuffix;
	}
}
