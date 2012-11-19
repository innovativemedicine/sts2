 package agtc.sampletracking.model;


import java.util.Date;
import java.util.*;
import java.io.Serializable;
import agtc.sampletracking.*;


/** 
 *        @hibernate.class
 *         table="STOCK_CONTAINER"
 *     
*/
public class Container  implements Serializable,ConstantInterface {

    /** identifier field */
    private Integer containerId;

    /** persistent field */
    private String name;

    /** nullable persistent field */
    private String extContainerId;

    /** nullable persistent field */
    private Date createdDate;

    /** nullable persistent field */
    private String comments;

    /** nullable persistent field */
    private String status;

    /** persistent field */
    private Location location;

    /** persistent field */
    private Project project;

    /** persistent field */
    private ContainerType containerType;

    /** persistent field */
    private Set samplesInContainers;

    /** persistent field */
    private Set reagents;
    
    private Container motherContainer;
    
    private Set childContainers;
    
    private Float concentration;
    
    private boolean noneSample;
    
    private boolean orderedSample;
    
    private boolean unorderedSample;

    private int totalSamples;
  
    /** default constructor */
    public Container() {
    }
    
    /** in containerDetails.jsp, call this to check the containerType */
    
    public boolean isPlate(){
    	if(containerType !=null && containerType.getName().startsWith(PLATE)){
    		return true;
    	}
    	return false;
    }
    
    public boolean isNoneSample(){
    	return noneSample;
    }
    
    public boolean isOrderedSample(){
    	return orderedSample;
    }
    
    public boolean isUnorderedSample(){
    	return unorderedSample;
    }
    
    public void setNoneSample(boolean b){
    	noneSample = b;
    }
    
    public void setOrderedSample(boolean b){
    	orderedSample = b;
    }
    
    public void setUnorderedSample(boolean b){
    	unorderedSample = b;
    }
    
    /** in containerDetails.jsp, call this to check the containerType */
    
    public boolean isSampleBox(){
    	if(containerType !=null && containerType.getName().startsWith(SAMPLE_BOX)){
    		return true;
    	}
    	return false;
    }
    
    /** in containerDetails.jsp, call this to check the containerType */
    
    public boolean isSampleBoxOrPlate(){
    	if(containerType !=null && containerType.getName().startsWith(PLATE)){
    		return true;
    	}
    	if(containerType !=null && containerType.getName().startsWith(SAMPLE_BOX)){
    		return true;
    	}
    	return false;
    }
    
    /** in containerDetails.jsp, call this to check the containerType */
    
    public boolean isReagentBox(){
    	if(containerType !=null && containerType.getName().startsWith(REAGENT_BOX)){
    		return true;
    	}
    	return false;
    }
    
    public boolean isHasMotherContainer(){
    	if(motherContainer != null){
    		return true;
    	}else{
    		return false;
    	}
    }
    
   
	/**
	 * @return Returns the totalSamples.
	 */
	public int getTotalSamples() {
		return samplesInContainers.size();
	}
	/**
	 * @param totalSamples The totalSamples to set.
	 */
	public void setTotalSamples(int totalSamples) {
		this.totalSamples = totalSamples;
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
     *             column="EXT_CONTAINER_ID"
     *             length="32"
     *         
     */
    public String getExtContainerId() {
        return this.extContainerId;
    }

    public void setExtContainerId(String extContainerId) {
        this.extContainerId = extContainerId;
    }

    /** 
     *            @hibernate.property
     *             column="CREATED_DATA"
     *             length="7"
     *         
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /** 
     *            @hibernate.property
     *             column="COMMENTS"
     *             length="256"
     *         
     */
    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    /** 
     *            @hibernate.property
     *             column="STATUS"
     *             length="10"
     *         
     */
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="LOCATION_ID"         
     *         
     */
    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="PROJECT_ID"         
     *         
     */
    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="CONTAINER_TYPE_ID"         
     *         
     */
    public ContainerType getContainerType() {
        return this.containerType;
    }

    public void setContainerType(ContainerType containerType) {
        this.containerType = containerType;
    }


    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     * 	       @hibernate.collection-key
     * 	        column="STOCK_ID"
     *            @hibernate.collection-one-to-many
     *             class="agtc.sampletracking.model.Reagent"
     *         
     */
    public Set getReagents() {
        return this.reagents;
    }

    public void setReagents(Set reagents) {
        this.reagents = reagents;
    }
    
	public void setReagentsFromApp(Set reagents) {
		this.reagents = reagents;
	}

  
	/**
	 * @return
	 */
	public Integer getContainerId() {
		return containerId;
	}

	/**
	 * @param integer
	 */
	public void setContainerId(Integer integer) {
		containerId = integer;
	}

	/**
	 * @return
	 */
	public Set getSamplesInContainers() {
		return samplesInContainers;
	}

	/**
	 * @param set
	 */
	public void setSamplesInContainers(Set set) {
		samplesInContainers = set;
	}


	/**
	 * @return Returns the concentration.
	 */
	public Float getConcentration() {
		return concentration;
	}
	/**
	 * @param concentration The concentration to set.
	 */
	public void setConcentration(Float concentration) {
		this.concentration = concentration;
	}
	
	
	/**
	 * @return Returns the childContainers.
	 */
	public Set getChildContainers() {
		return childContainers;
	}
	/**
	 * @param childContainers The childContainers to set.
	 */
	public void setChildContainers(Set childContainers) {
		this.childContainers = childContainers;
	}
	/**
	 * @return Returns the motherContainer.
	 */
	public Container getMotherContainer() {
		return motherContainer;
	}
	/**
	 * @param motherContainer The motherContainer to set.
	 */
	public void setMotherContainer(Container motherContainer) {
		this.motherContainer = motherContainer;
	}
}
