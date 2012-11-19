/*
 * Created on Dec 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus;

/**
 * @author Gloria Deng
 *
 * This class is for upload test result
 */
public class MiniResultInfo {
    private String sampleId;
    private String assayName;
    private String result;
    
    public MiniResultInfo(String s1,String s2,String s3){
        sampleId = s1;
        assayName = s2;
        result = s3;
    }
    
    /**
     * @return Returns the assayName.
     */
    public String getAssayName() {
        return assayName;
    }
    /**
     * @param assayName The assayName to set.
     */
    public void setAssayName(String assayName) {
        this.assayName = assayName;
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
     * @return Returns the sampleId.
     */
    public String getSampleId() {
        return sampleId;
    }
    /**
     * @param sampleId The sampleId to set.
     */
    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }
}
