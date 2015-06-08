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
	
	public ContainerContentCellUnit[][] getCells() {
		return cells;
	}

	public void setCells(ContainerContentCellUnit[][] cells) {
		this.cells = cells;
	}

	public int getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(int columnNo) {
		this.columnNo = columnNo;
	}

	public int getRowNo() {
		return rowNo;
	}

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
	
	public Integer getContainerId() {
		return containerId;
	}

	public void setContainerId(Integer containerId) {
		this.containerId = containerId;
	}
	
	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
}
