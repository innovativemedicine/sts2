package agtc.sampletracking.model;

import java.util.Set;
import java.io.Serializable;


/** 
 *        @hibernate.class
 *         table="TEST"
 *     
*/
public class Test  implements Serializable {

    /** identifier field */
    private Integer testId;

    /** persistent field */
    private String name;
    
    private String note;

    /** nullable persistent field */
    private String protocal;
    
    private String assayList;

    /** persistent field */
    private Instrument instrument;
    
    private Project project;

 


    /** default constructor */
    public Test() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="long"
     *             column="TEST_ID"
     *         
     */
    public Integer getTestId() {
        return this.testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    /** 
     *            @hibernate.property
     *             column="NAME"
     *             length="128"
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
     *             column="PROTOCAL"
     *             length="10"
     *         
     */
    public String getProtocal() {
        return this.protocal;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="INSTRUMENT_ID"         
     *         
     */
    public Instrument getInstrument() {
        return this.instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

 
	/**
	 * @return
	 */
	public String getAssayList() {
		return assayList;
	}


	/**
	 * @param string
	 */
	public void setAssayList(String string) {
		assayList = string;
	}

	/**
	 * @return
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project
	 */
	public void setProject(Project project) {
		this.project = project;
	}


	
	/**
	 * @return
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param string
	 */
	public void setNote(String string) {
		note = string;
	}


}
