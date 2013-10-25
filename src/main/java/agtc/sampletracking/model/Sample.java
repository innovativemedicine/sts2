package agtc.sampletracking.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @hibernate.class table="SAMPLE"
 * 
 */
public class Sample implements Serializable, Comparator, Cloneable {
	private Log			log	= LogFactory.getLog(Sample.class);
	private Integer		sampleId;
	private Float		od;
	private Float		volumn;
	private Date		madeDate;
	private Date		refillDate;
	private String		notes;

	/** nullable persistent field */
	private String		tempIntSampleId;
	private Date		transDate;
	private String		status;
	private Date		removeDate;
	private Date		odDate;
	private Date		volumnDate;

	private Integer		sampleDupNo;

	private Float		odA260;
	private Float		odA280;
	private Float		od260280;
	private Float		od260230;
	private Integer		odFactor;
	private Integer		odCursorPos;
	private Float		odCursorAbs;
	private Float		od340Raw;

	/** persistent field */
	private SampleType	sampleType;

	private Set			samplesInContainersIn;
	private Set			samplesInContainersOut;

	private Patient		patient;

	/** default constructor */
	public Sample() {
		patient = new Patient();
		sampleType = new SampleType();
		sampleId = new Integer(-1);
	}

	public Sample(String intSampleId) {
		patient = new Patient(intSampleId);
		sampleType = new SampleType();
		sampleId = new Integer(-1);
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}

	public int compare(Object o1, Object o2) {
		Sample s1 = (Sample) o1;
		Sample s2 = (Sample) o2;
		String interlId1 = s1.getPatient().getIntSampleId();
		String interlId2 = s2.getPatient().getIntSampleId();
		return interlId1.compareTo(interlId2);
	}

	public boolean isHasPatient() {
		if (patient == null) {
			return false;
		} else {
			return true;
		}
	}

	public Integer getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}

	public Float getOd() {
		return this.od;
	}

	public void setOd(Float od) {
		this.od = od;
	}

	public Float getVolumn() {
		return this.volumn;
	}

	public void setVolumn(Float volumn) {
		this.volumn = volumn;
	}

	public Date getMadeDate() {
		return this.madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public Date getRefillDate() {
		return this.refillDate;
	}

	public void setRefillDate(Date refillDate) {
		this.refillDate = refillDate;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getTransDate() {
		return this.transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRemoveDate() {
		return this.removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}

	/**
	 * @return
	 */
	public Date getOdDate() {
		return odDate;
	}

	/**
	 * @return
	 */
	public Date getVolumnDate() {
		return volumnDate;
	}

	/**
	 * @param date
	 */
	public void setOdDate(Date date) {
		odDate = date;
	}

	/**
	 * @param date
	 */
	public void setVolumnDate(Date date) {
		volumnDate = date;
	}

	/**
	 * @return Returns the samplesInContainersIn.
	 */
	public Set getSamplesInContainersIn() {
		return samplesInContainersIn;
	}

	/**
	 * @param samplesInContainersIn
	 *            The samplesInContainersIn to set.
	 */
	public void setSamplesInContainersIn(Set samplesInContainersIn) {
		this.samplesInContainersIn = samplesInContainersIn;
	}

	/**
	 * @return Returns the samplesInContainersOut.
	 */
	public Set getSamplesInContainersOut() {
		return samplesInContainersOut;
	}

	/**
	 * @param samplesInContainersOut
	 *            The samplesInContainersOut to set.
	 */
	public void setSamplesInContainersOut(Set samplesInContainersOut) {
		this.samplesInContainersOut = samplesInContainersOut;
	}

	/**
	 * @return Returns the patient.
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 *            The patient to set.
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * @return Returns the sampleDupNo.
	 */
	public Integer getSampleDupNo() {
		return sampleDupNo;
	}

	/**
	 * @param sampleDupNo
	 *            The sampleDupNo to set.
	 */
	public void setSampleDupNo(Integer sampleDupNo) {
		this.sampleDupNo = sampleDupNo;
	}

	/**
	 * @return Returns the tempIntSampleId.
	 */
	public String getTempIntSampleId() {
		return tempIntSampleId;
	}

	/**
	 * @param tempIntSampleId
	 *            The tempIntSampleId to set.
	 */
	public void setTempIntSampleId(String tempIntSampleId) {
		this.tempIntSampleId = tempIntSampleId;
	}

	/**
	 * @return Returns the sampleType.
	 */
	public SampleType getSampleType() {
		return sampleType;
	}

	/**
	 * @param sampleType
	 *            The sampleType to set.
	 */
	public void setSampleType(SampleType sampleType) {
		this.sampleType = sampleType;
	}

	public Float getOdA260() {
		return odA260;
	}

	public void setOdA260(Float odA260) {
		this.odA260 = odA260;
	}

	public Float getOdA280() {
		return odA280;
	}

	public void setOdA280(Float odA280) {
		this.odA280 = odA280;
	}

	public Float getOd260280() {
		return od260280;
	}

	public void setOd260280(Float od260280) {
		this.od260280 = od260280;
	}

	public Float getOd260230() {
		return od260230;
	}

	public void setOd260230(Float od260230) {
		this.od260230 = od260230;
	}

	public Integer getOdFactor() {
		return odFactor;
	}

	public void setOdFactor(Integer odFactor) {
		this.odFactor = odFactor;
	}

	public Integer getOdCursorPos() {
		return odCursorPos;
	}

	public void setOdCursorPos(Integer odCursorPos) {
		this.odCursorPos = odCursorPos;
	}

	public Float getOdCursorAbs() {
		return odCursorAbs;
	}

	public void setOdCursorAbs(Float odCursorAbs) {
		this.odCursorAbs = odCursorAbs;
	}

	public Float getOd340Raw() {
		return od340Raw;
	}

	public void setOd340Raw(Float od340Raw) {
		this.od340Raw = od340Raw;
	}

}
