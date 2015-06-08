/*
 * Created on Nov 19, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.model;

import java.io.Serializable;
import java.util.*;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SamplesInContainer implements Serializable,Comparator  {
	private Integer sicId;
	private Sample sample;
	private Container container;
	private String well;
	private String operation;
	private Date operationDate;
	private String reason;
	
	private Log log = LogFactory.getLog(SamplesInContainer.class);
	
	public int compare(Object o1, Object o2) {
		SamplesInContainer s1 = (SamplesInContainer)o1;
		SamplesInContainer s2 = (SamplesInContainer)o2;
		String interlId1 = s1.getSample().getPatient().getIntSampleId();
		String interlId2 = s2.getSample().getPatient().getIntSampleId();
		return interlId1.compareTo(interlId2);
	}
	
	/**
	 * @return
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * @return
	 */
	public Sample getSample() {
		return sample;
	}

	/**
	 * @return
	 */
	public String getWell() {
		return well;
	}

	/**
	 * @param container
	 */
	public void setContainer(Container container) {
		//log.debug("set container " + container.getName());
		this.container = container;
	}

	/**
	 * @param sample
	 */
	public void setSample(Sample sample) {
		//log.debug("set sample " + sample.getExtSampleId());
		this.sample = sample;
	}

	/**
	 * @param string
	 */
	public void setWell(String string) {
		//log.debug("set container " + string);
		well = string;
	}

	/**
	 * @return
	 */
	public Integer getSicId() {
		return sicId;
	}

	/**
	 * @param integer
	 */
	public void setSicId(Integer integer) {
		sicId = integer;
	}
	
	

	/**
	 * @return Returns the operation.
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @param operation The operation to set.
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * @return Returns the operationDate.
	 */
	public Date getOperationDate() {
		return operationDate;
	}
	/**
	 * @param operationDate The operationDate to set.
	 */
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	
	/**
	 * @return Returns the reason.
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason The reason to set.
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}
