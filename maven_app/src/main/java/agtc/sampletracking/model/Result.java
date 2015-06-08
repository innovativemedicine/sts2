package agtc.sampletracking.model;

 
import java.io.Serializable;
/** 
 *        @hibernate.class
 *         table="RESULT"
 *     
*/
public class Result implements Serializable{

    /** identifier field */
    private Integer resultId;

    /** nullable persistent field */
    private String result;

    /** nullable persistent field */
    private String note;
    
    /** nullable persistent field */
    private String intSampleId;
    
    private String useThis;
    private String export2CGG;
    
    /** persistent field */
    private Assay assay;
    
    private Run run;
    
	private byte[] file;
	
	private Integer studyGroupId;
	
	public int compare(Object o1, Object o2) {
		Result s1 = (Result)o1;
		Result s2 = (Result)o2;
		String interlId1 = s1.getIntSampleId();
		String interlId2 = s2.getIntSampleId();
		if(interlId1.compareTo(interlId2)!=0){
			return interlId1.compareTo(interlId2);
		}else{
			return s1.getAssay().getName().compareTo(s2.getAssay().getName());
		}
	}
	
	/**
	 * @return Returns the studyGroupId.
	 */
	public Integer getStudyGroupId() {
		return studyGroupId;
	}
	/**
	 * @param studyGroupId The studyGroupId to set.
	 */
	public void setStudyGroupId(Integer studyGroupId) {
		this.studyGroupId = studyGroupId;
	}
    /** default constructor */
    public Result() {
    }

  

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="SAMPLE_WORKING_ID"
     *         
     */
    public Integer getResultId() {
        return this.resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }


    /** 
     *            @hibernate.property
     *             column="RESULT"
     *             length="10"
     *         
     */
    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /** 
     *            @hibernate.property
     *             column="NOTE"
     *             length="64"
     *         
     */
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="ASSAY_ID"         
     *         
     */
    public Assay getAssay() {
        return this.assay;
    }

    public void setAssay(Assay assay) {
        this.assay = assay;
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
	 * @return
	 */
	public Run getRun() {
		return run;
	}

	/**
	 * @param run
	 */
	public void setRun(Run run) {
		this.run = run;
	}

	/**
	 * @return
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * @param bs
	 */
	public void setFile(byte[] bs) {
		file = bs;
	}
	
	

	/**
	 * @return Returns the export2CGG.
	 */
	public String getExport2CGG() {
		return export2CGG;
	}
	/**
	 * @param export2CGG The export2CGG to set.
	 */
	public void setExport2CGG(String export2CGG) {
		this.export2CGG = export2CGG;
	}
	
	/**
	 * @return Returns the useThis.
	 */
	public String getUseThis() {
		return useThis;
	}
	/**
	 * @param useThis The useThis to set.
	 */
	public void setUseThis(String useThis) {
		this.useThis = useThis;
	}
}
