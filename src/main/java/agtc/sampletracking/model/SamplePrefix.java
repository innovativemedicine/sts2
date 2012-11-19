/*
 * Created on Jan 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.model;

import java.io.Serializable;

/**
 * @author Gloria
 *
 */
public class SamplePrefix implements Serializable{
	private Integer samplePrefixId;
	private String prefix;
	private String description;
	private String note;
	private String columns;
	private Integer digitNumber;
	private String[] columnArray;
	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the note.
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note The note to set.
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return Returns the prefix.
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * @param prefix The prefix to set.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * @return Returns the samplePrefixId.
	 */
	public Integer getSamplePrefixId() {
		return samplePrefixId;
	}
	/**
	 * @param samplePrefixId The samplePrefixId to set.
	 */
	public void setSamplePrefixId(Integer samplePrefixId) {
		this.samplePrefixId = samplePrefixId;
	}
	
	
	/**
	 * @return Returns the columns.
	 */
	public String getColumns() {
		return columns;
	}
	/**
	 * @param columns The columns to set.
	 */
	public void setColumns(String columns) {
		this.columns = columns;
	}
	
	
	/**
	 * @return Returns the digitNumber.
	 */
	public Integer getDigitNumber() {
		return digitNumber;
	}
	/**
	 * @param digitNumber The digitNumber to set.
	 */
	public void setDigitNumber(Integer digitNumber) {
		this.digitNumber = digitNumber;
	}
	
	
	/**
	 * @return Returns the columnArray.
	 */
	public String[] getColumnArray() {
		return columnArray;
	}
	/**
	 * @param columnArray The columnArray to set.
	 */
	public void setColumnArray(String[] columnArray) {
		this.columnArray = columnArray;
	}
}
