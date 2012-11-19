package agtc.sampletracking.model;

import java.util.Set;
import java.io.Serializable;


/** 
 *        @hibernate.class
 *         table="ASSAY"
 *     
*/
public class Assay implements Serializable{

    /** identifier field */
    private Integer assayId;

    /** nullable persistent field */
    private String name;
    
	/** nullable persistent field */
	private String note;
	
	private String location;

    /** persistent field */
    private Set results;

    /** persistent field */
    private Set projects;


    /** default constructor */
    public Assay() {
    }

  
    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="long"
     *             column="ASSAY_ID"
     *         
     */
    public Integer getAssayId() {
        return this.assayId;
    }

    public void setAssayId(Integer assayId) {
        this.assayId = assayId;
    }

    
	public String getNote() {
			return this.note;
		}

		public void setNote(String note) {
			this.note = note;
		}

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     * 	       @hibernate.collection-key
     * 	        column="ASSAY_ID"
     *            @hibernate.collection-one-to-many
     *             class="agtc.sampletracking.model.Result"
     *         
     */
    public Set getResults() {
        return this.results;
    }

    public void setResults(Set results) {
        this.results = results;
    }
   
	/**
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param string
	 */
	public void setLocation(String string) {
		location = string;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	

	/**
	 * @return
	 */
	public Set getProjects() {
		return projects;
	}

	/**
	 * @param set
	 */
	public void setProjects(Set set) {
		projects = set;
	}

}
