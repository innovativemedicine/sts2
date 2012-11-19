/*
 * Created on Apr 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao;

import java.util.List;

import agtc.sampletracking.model.Patient;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface PatientDAO {
	public Patient getPatient(String intSampleId);
	public boolean containsPatient(String intSampleId);
	public void savePatient(Patient patient);
	public void updatePatient(Patient patient);
	public void removePatient(String intSampleId); 
}
