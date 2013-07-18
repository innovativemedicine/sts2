/*
 * Created on Jan 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.model;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Patient implements Serializable{
	 private String intSampleId;
     private String note;
     private Date birthDate;
     private Set samples;
     private String isControl;
     private String familyId;
     /** persistent field */
     private String extSampleId;
     private Project project;
     private String anotherExtSampleId;
   
     public Patient()
     {
     }
     
     public Patient(String sampleID)
     {
    	 intSampleId = sampleID;
     }
     
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
