package agtc.sampletracking.model;

import java.util.Set;
import java.io.Serializable;


/** 
 *        @hibernate.class
 *         table="SAMPLE_TYPE"
 *     
*/
public class SampleType implements Serializable {

    /** identifier field */
    private Integer sampleTypeId;

    /** persistent field */
    private String name;

    /** nullable persistent field */
    private String suffix;
    
    /** nullable persistent field */
    private String vials;
    
    /** nullable persistent field */
    private Integer initialLabelNo;
    
    //Is register required which sample type can accept external samples.
    private String isSource;

    /** persistent field */
    private Set samples;

   
    /** default constructor */
    public SampleType() {
    }


    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="long"
     *             column="SAMPLE_TYPE_ID"
     *         
     */
    public Integer getSampleTypeId() {
        return this.sampleTypeId;
    }

    public void setSampleTypeId(Integer sampleTypeId) {
        this.sampleTypeId = sampleTypeId;
    }

    /** 
     *            @hibernate.property
     *             column="NAME"
     *             length="32"
     *             not-null="true"
     *         
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** 
     *            @hibernate.property
     *             column="SUFFIX"
     *             length="2"
     *         
     */
    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    
    

	/**
	 * @return Returns the vials.
	 */
	public String getVials() {
		return vials;
	}
	/**
	 * @param vials The vials to set.
	 */
	public void setVials(String vials) {
		this.vials = vials;
	}
	
	/**
	 * @return Returns the isSource.
	 */
	public String getIsSource() {
		return isSource;
	}
	/**
	 * @param vials The isSource to set.
	 */
	public void setIsSource(String vials) {
		this.isSource = vials;
	}
	
    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     * 	       @hibernate.collection-key
     * 	        column="SAMPLE_TYPE_ID"
     *            @hibernate.collection-one-to-many
     *             class="agtc.sampletracking.model.Sample"
     *         
     */
    public Set getSamples() {
        return this.samples;
    }

    public void setSamples(Set samples) {
        this.samples = samples;
    }

	/**
	 * @return Returns the initialLabelNo.
	 */
	public Integer getInitialLabelNo() {
		return initialLabelNo;
	}
	/**
	 * @param initialLabelNo The initialLabelNo to set.
	 */
	public void setInitialLabelNo(Integer initialLabelNo) {
		this.initialLabelNo = initialLabelNo;
	}
}
