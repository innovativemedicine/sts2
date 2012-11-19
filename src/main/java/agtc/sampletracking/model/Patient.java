/*
 * Created on Jan 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.model;
import java.util.*;

import java.io.Serializable;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Patient implements Serializable{
	 private String intSampleId;
     private String fname;
     private String lname;
     private String mname;
     private Integer age;
     private String gender;
     private String note;
     private Date birthDate;
     private Set samples;
     private String isControl;
     private String familyId;
     /** persistent field */
     private String extSampleId;
     private Project project;
     private String anotherExtSampleId;
     
	/**
	 * @return Returns the project.
	 */
	public Project getProject() {
		return project;
	}
	/**
	 * @param project The project to set.
	 */
	public void setProject(Project project) {
		this.project = project;
	}
    
     
     public boolean isNewPatient(){
     	if(intSampleId == null){
     		return true;
     	}else{
     		return false;
     	}
     }
	
	/**
	 * @return Returns the intSampleId.
	 */
	public String getIntSampleId() {
		return intSampleId;
	}
	/**
	 * @param intSampleId The intSampleId to set.
	 */
	public void setIntSampleId(String intSampleId) {
		this.intSampleId = intSampleId;
	}
	/**
	 * @return Returns the birthDate.
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate The birthDate to set.
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return Returns the gender.
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender The gender to set.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	/**
	 * @return Returns the fname.
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname The fname to set.
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return Returns the lname.
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname The lname to set.
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return Returns the mname.
	 */
	public String getMname() {
		return mname;
	}
	/**
	 * @param mname The mname to set.
	 */
	public void setMname(String mname) {
		this.mname = mname;
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
	 * @return Returns the age.
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age The age to set.
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	/**
	 * @return Returns the samples.
	 */
	public Set getSamples() {
		return samples;
	}
	/**
	 * @param samples The samples to set.
	 */
	public void setSamples(Set samples) {
		this.samples = samples;
	}
	
	
	/**
	 * @return Returns the isControl.
	 */
	public String getIsControl() {
		return isControl;
	}
	/**
	 * @param isControl The isControl to set.
	 */
	public void setIsControl(String isControl) {
		this.isControl = isControl;
	}
	
	
	/**
	 * @return Returns the familyId.
	 */
	public String getFamilyId() {
		return familyId;
	}
	/**
	 * @param familyId The familyId to set.
	 */
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	
	
	/**
	 * @return Returns the anotherExtSampleId.
	 */
	public String getAnotherExtSampleId() {
		return anotherExtSampleId;
	}
	/**
	 * @param anotherExtSampleId The anotherExtSampleId to set.
	 */
	public void setAnotherExtSampleId(String anotherExtSampleId) {
		this.anotherExtSampleId = anotherExtSampleId;
	}
	/**
	 * @return Returns the extSampleId.
	 */
	public String getExtSampleId() {
		return extSampleId;
	}
	/**
	 * @param extSampleId The extSampleId to set.
	 */
	public void setExtSampleId(String extSampleId) {
		this.extSampleId = extSampleId;
	}
}
