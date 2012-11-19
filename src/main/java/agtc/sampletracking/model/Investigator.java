package agtc.sampletracking.model;


import java.util.Set;
import java.io.Serializable;



/** 
 *        @hibernate.class
 *         table="INVESTIGATOR"
 *     
*/
public class Investigator implements Serializable {

    /** identifier field */
    private Integer investigatorId;
    
    /** persistent field */
    private Name name;

    /** nullable persistent field */
    private Contact contact;

    /** persistent field */
    private Set projects;
    
    private String fullName;

 

    /** default constructor */
    public Investigator() {
    	name = new Name();
    	contact = new Contact();
    }


    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="long"
     *             column="INVESTIGATOR_ID"
     *         
     */
    public Integer getInvestigatorId() {
        return this.investigatorId;
    }

    public void setInvestigatorId(Integer investigatorId) {
        this.investigatorId = investigatorId;
    }

    /** 
     *            @hibernate.property
     *             Name
     */
    public Name getName() {
        return this.name;
    }
    
	public void setName(Name name) {
		this.name = name;
	}

    /** 
     *            @hibernate.property
     *             Contact
     *         
     */
    public Contact getContact() {
        return this.contact;
    }

	public void setContact(Contact contact) {
			this.contact = contact;
		}
    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     * 	       @hibernate.collection-key
     * 	        column="INVESTIGATOR_ID"
     *            @hibernate.collection-one-to-many
     *             class="agtc.sampletracking.model.Project"
     *         
     */
    public Set getProjects() {
        return this.projects;
    }

    public void setProjects(Set projects) {
        this.projects = projects;
    }
    
    
    
     
	/**
	 * @return Returns the fullName.
	 */
	public String getFullName() {
		String result = "";
		if(name.getFname()!=null && !name.getFname().equals("-")){
			result+= name.getFname()+" ";
		}
		if(name.getMname()!=null && !name.getFname().equals("-")){
			result+= name.getMname()+" ";
		}
		
		if(name.getLname()!=null && !name.getFname().equals("-")){
			result+= name.getLname();
		}
		return result;
	}
	/**
	 * @param fullName The fullName to set.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
