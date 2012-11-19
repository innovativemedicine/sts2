/*
 * Created on Jan 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CGGStudyGroup {
	private Integer studyGroupId;
	private String studyGroupName;
	
	public CGGStudyGroup(){
		studyGroupId = new Integer(-1);
		studyGroupName = "";
	}
	
	public CGGStudyGroup(Integer id, String name){
		studyGroupId = id;
		studyGroupName = name;
	}
	
	
	/**
	 * @return Returns the studyGroupId.
	 */
	public Integer getStudyGroupId() {
		return studyGroupId;
	}
	/**
	 * @param studyGroupId The studyGroupId to set.
	 */
	public void setStudyGroupId(Integer studyGroupId) {
		this.studyGroupId = studyGroupId;
	}
	/**
	 * @return Returns the studyGroupName.
	 */
	public String getStudyGroupName() {
		return studyGroupName;
	}
	/**
	 * @param studyGroupName The studyGroupName to set.
	 */
	public void setStudyGroupName(String studyGroupName) {
		this.studyGroupName = studyGroupName;
	}
}
