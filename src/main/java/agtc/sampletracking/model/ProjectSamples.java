package agtc.sampletracking.model;


import java.util.Date;
import java.util.Set;
import java.io.Serializable;


/** 
 *        @hibernate.class
 *         table="PROJECT"
 *     
*/
public class ProjectSamples implements Serializable {

    /** identifier field */
    private Integer projectId;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private Date createdOn;

    /** persistent field */
    private Investigator investigator;

    /** persistent field */
    private Set assays;

    /** persistent field */
    private Set containers;
   
   private Set tests;
   private Set runs;
   private Set patients;
    /** default constructor */
    public ProjectSamples() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="long"
     *             column="PROJECT_ID"
     *         
     */
    public Integer getProjectId() {
        if(projectId!=null){
			return this.projectId;
        }else{
        	return new Integer(-1);
        }       
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /** 
     *            @hibernate.property
     *             column="NAME"
     *             length="32"
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
     *             column="DESCRIPTION"
     *             length="256"
     *         
     */
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /** 
     *            @hibernate.property
     *             column="COMPLETED"
     *             length="22"
     *         
     */
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /** 
     *            @hibernate.property
     *             column="CREATED_ON"
     *             length="7"
     *         
     */
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="INVESTIGATOR_ID"         
     *         
     */
    public Investigator getInvestigator() {
        return this.investigator;
    }

    public void setInvestigator(Investigator investigator) {
        this.investigator = investigator;
    }

  
	/**
	 * @return
	 */
	public Set getAssays() {
		return assays;
	}

	/**
	 * @return
	 */
	public Set getContainers() {
		return containers;
	}

	/**
	 * @param set
	 */
	public void setAssays(Set set) {
		assays = set;
	}

	/**
	 * @param set
	 */
	public void setContainers(Set set) {
		containers = set;
	}

	/**
	 * @return
	 */
	public Set getRuns() {
		return runs;
	}
	
	/**
	 * @return
	 */
	public Set getTests() {
		return tests;
	}
	
	/**
	 * @param set
	 */
	public void setRuns(Set set) {
		runs = set;
	}
	
	/**
	 * @param set
	 */
	public void setTests(Set set) {
		tests = set;
	}
	
	/**
	 * @return Returns the patients.
	 */
	public Set getPatients() {
		return patients;
	}
	/**
	 * @param patients The patients to set.
	 */
	public void setPatients(Set patients) {
		this.patients = patients;
	}
}
