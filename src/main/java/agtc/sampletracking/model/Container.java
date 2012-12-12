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

    private Integer containerId;
    private String name;
    private String extContainerId;
    private Date createdDate;
    private String comments;
    private String status;
    private Location location;
    private Project project;
    private ContainerType containerType;
    private Set samplesInContainers;
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
       
    public boolean isHasChildContainer(){
    	if(!childContainers.isEmpty()) {
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
    
    
    public boolean isPlate(){
    	if(containerType !=null && containerType.getName().startsWith(PLATE)){
    		return true;
    	}
    	return false;
    }
        
    public boolean isSampleBox(){
    	if(containerType !=null && containerType.getName().startsWith(SAMPLE_BOX)){
    		return true;
    	}
    	return false;
    }
    
    public boolean isSampleBoxOrPlate(){
    	if(containerType !=null && containerType.getName().startsWith(PLATE)){
    		return true;
    	}
    	if(containerType !=null && containerType.getName().startsWith(SAMPLE_BOX)){
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
    
    public boolean isReagentBox(){
    	if(containerType !=null && containerType.getName().startsWith(REAGENT_BOX)){
    		return true;
    	}
    	return false;
    }
    

	public int getTotalSamples() {
		return samplesInContainers.size();
	}

	public void setTotalSamples(int totalSamples) {
		this.totalSamples = totalSamples;
	}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtContainerId() {
        return this.extContainerId;
    }

    public void setExtContainerId(String extContainerId) {
        this.extContainerId = extContainerId;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ContainerType getContainerType() {
        return this.containerType;
    }

    public void setContainerType(ContainerType containerType) {
        this.containerType = containerType;
    }
    

	public Integer getContainerId() {
		return containerId;
	}

	public void setContainerId(Integer integer) {
		containerId = integer;
	}

	public Set getSamplesInContainers() {
		return samplesInContainers;
	}

	public void setSamplesInContainers(Set set) {
		samplesInContainers = set;
	}

	public Float getConcentration() {
		return concentration;
	}

	public void setConcentration(Float concentration) {
		this.concentration = concentration;
	}
	
	public Set getChildContainers() {
		return childContainers;
	}

	public void setChildContainers(Set childContainers) {
		this.childContainers = childContainers;
	}

	public Container getMotherContainer() {
		return motherContainer;
	}

	public void setMotherContainer(Container motherContainer) {
		this.motherContainer = motherContainer;
	}
}
