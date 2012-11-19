/*
 * Created on Jul 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.command;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContainerContentCommand {
	private int rowNo;
	private int columnNo;
	private Integer containerId;
	private ContainerContentCellUnit[][] cells;
	private boolean emptyContainer = false;
	private boolean noneContainer = false;
	private String containerName;
	
	/**
	 * @return Returns the cells.
	 */
	public ContainerContentCellUnit[][] getCells() {
		return cells;
	}
	/**
	 * @param cells The cells to set.
	 */
	public void setCells(ContainerContentCellUnit[][] cells) {
		this.cells = cells;
	}
	/**
	 * @return Returns the columnNo.
	 */
	public int getColumnNo() {
		return columnNo;
	}
	/**
	 * @param columnNo The columnNo to set.
	 */
	public void setColumnNo(int columnNo) {
		this.columnNo = columnNo;
	}
	/**
	 * @return Returns the rowNo.
	 */
	public int getRowNo() {
		return rowNo;
	}
	/**
	 * @param rowNo The rowNo to set.
	 */
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	
	public boolean isEmptyContainer(){
		return emptyContainer;
	}
	public void setEmptyContainer(boolean empty){
		this.emptyContainer = empty;
	}
	public boolean isNoneContainer(){
		return noneContainer;
	}
	public void setNoneContainer(boolean none){
		this.noneContainer = none;
	}
	
	/**
	 * @return Returns the containerId.
	 */
	public Integer getContainerId() {
		return containerId;
	}
	/**
	 * @param containerId The containerId to set.
	 */
	public void setContainerId(Integer containerId) {
		this.containerId = containerId;
	}
	
	/**
	 * @return Returns the containerName.
	 */
	public String getContainerName() {
		return containerName;
	}
	/**
	 * @param containerName The containerName to set.
	 */
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
}
