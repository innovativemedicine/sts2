package agtc.sampletracking.model;


import java.util.Set;
import java.io.Serializable;


/** 
 *        @hibernate.class
 *         table="LOCATION"
 *     
*/
public class Location implements Serializable {

    /** identifier field */
    private Integer locationId;
    /** nullable persistent field */
    private String building;

    private String name;
    /** nullable persistent field */
    private String room;

    /** nullable persistent field */
    private String freezer;

    /** nullable persistent field */
    private String freezerShelf;


    /** persistent field */
    private Set containers;
    
    
    /** default constructor */
    public Location() {
    }


    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="long"
     *             column="LOCATION_ID"
     *         
     */
    public Integer getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
    /** 
     *            @hibernate.property
     *             column="BUILDING"
     *             length="128"
     *         
     */
    public String getBuilding() {
        return this.building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    /** 
     *            @hibernate.property
     *             column="ROOM"
     *             length="32"
     *         
     */
    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    /** 
     *            @hibernate.property
     *             column="FREEZER"
     *             length="16"
     *         
     */
    public String getFreezer() {
        return this.freezer;
    }

    public void setFreezer(String freezer) {
        this.freezer = freezer;
    }

    /** 
     *            @hibernate.property
     *             column="FREEZER_SHELF"
     *             length="16"
     *         
     */
    public String getFreezerShelf() {
        return this.freezerShelf;
    }

    public void setFreezerShelf(String freezerShelf) {
        this.freezerShelf = freezerShelf;
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
	public void setContainers(Set set) {
		containers = set;
	}

	

}
