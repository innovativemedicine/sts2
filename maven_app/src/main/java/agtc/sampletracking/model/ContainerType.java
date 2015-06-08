package agtc.sampletracking.model;

import java.util.Set;
import java.io.Serializable;



/** 
 *        @hibernate.class
 *         table="CONTAINER_TYPE"
 *     
*/
public class ContainerType implements Serializable {

    /** identifier field */
    private Integer containerTypeId;

    /** persistent field */
    private String name;

    /** persistent field */
    private Integer columnNo;
    
    /** persistent field */
    private Integer rowNo;

    /** persistent field */
    private Set containers;
    

   

    /** default constructor */
    public ContainerType() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="long"
     *             column="CONTAINER_TYPE_ID"
     *         
     */
    
    public int getCapacity(){
    	return columnNo.intValue()*rowNo.intValue();
    }
    public Integer getContainerTypeId() {
        return this.containerTypeId;
    }

    public void setContainerTypeId(Integer containerTypeId) {
        this.containerTypeId = containerTypeId;
    }

    /** 
     *            @hibernate.property
     *             column="NAME"
     *             length="16"
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
	 * @return Returns the columnNo.
	 */
	public Integer getColumnNo() {
		return columnNo;
	}
	/**
	 * @param columnNo The columnNo to set.
	 */
	public void setColumnNo(Integer columnNo) {
		this.columnNo = columnNo;
	}
	/**
	 * @return Returns the rowNo.
	 */
	public Integer getRowNo() {
		return rowNo;
	}
	/**
	 * @param rowNo The rowNo to set.
	 */
	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
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
