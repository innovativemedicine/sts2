/*
 * Created on Jul 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.command;
import agtc.sampletracking.model.*;
/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContainerContentCellUnit {
	private String sampleDesc;
	private Integer sicId;
	private String sampleTypeSuffix;
	private String well;
	private String dupNo;
	private boolean notOccupied;
	
	public ContainerContentCellUnit(SamplesInContainer samplesInContainer){
		if(samplesInContainer!=null){
			Sample sample = samplesInContainer.getSample();
			if(sample!=null){
				sampleDesc = sample.getPatient().getIntSampleId() + " "+ sample.getSampleType().getSuffix();
				if(sample.getSampleDupNo()!=null && sample.getSampleDupNo().intValue()!=1){
					sampleDesc += "("+sample.getSampleDupNo().toString()+")";
				}
				sicId = samplesInContainer.getSicId();
				well = samplesInContainer.getWell();
				notOccupied = false;
			}else{
				notOccupied = true;
			}
		}else{
			notOccupied = true;
		}
	}
	public boolean isNotOccupied(){
		return notOccupied;
	}
	
	/**
	 * @return Returns the sampleDesc.
	 */
	public String getSampleDesc() {
		return sampleDesc;
	}
	/**
	 * @param sampleDesc The sampleDesc to set.
	 */
	public void setSampleDesc(String sampleDesc) {
		this.sampleDesc = sampleDesc;
	}
	
	/**
	 * @return Returns the sicId.
	 */
	public Integer getSicId() {
		return sicId;
	}
	/**
	 * @param sicId The sicId to set.
	 */
	public void setSicId(Integer sicId) {
		this.sicId = sicId;
	}
	/**
	 * @return Returns the sampleTypeSuffix.
	 */
	public String getSampleTypeSuffix() {
		return sampleTypeSuffix;
	}
	/**
	 * @param sampleTypeSuffix The sampleTypeSuffix to set.
	 */
	public void setSampleTypeSuffix(String sampleTypeSuffix) {
		this.sampleTypeSuffix = sampleTypeSuffix;
	}
	
	/**
	 * @return Returns the well.
	 */
	public String getWell() {
		return well;
	}
	/**
	 * @param well The well to set.
	 */
	public void setWell(String well) {
		this.well = well;
	}
	
	
	/**
	 * @return Returns the dupNo.
	 */
	public String getDupNo() {
		return dupNo;
	}
	/**
	 * @param dupNo The dupNo to set.
	 */
	public void setDupNo(String dupNo) {
		this.dupNo = dupNo;
	}
}
