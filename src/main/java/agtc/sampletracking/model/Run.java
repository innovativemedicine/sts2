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

import agtc.sampletracking.bus.manager.ContainerManager;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Run implements Serializable{
	private Integer runId;
	private String plateList;
	private String testList;
	private Date runDate;
	private String note;
	private Project project;
	private String testNameList;
	private String plateNameList;
	private Set results;
	private Log log = LogFactory.getLog(Run.class);
	
	
	/**
	 * @return
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @return
	 */
	public String getPlateList() {
		return plateList;
	}

	/**
	 * @return
	 */
	public Date getRunDate() {
		return runDate;
	}

	/**
	 * @return
	 */
	public Integer getRunId() {
		return runId;
	}


	/**
	 * @param string
	 */
	public void setNote(String string) {
		note = string;
	}

	/**
	 * @param string
	 */
	public void setPlateList(String string) {
		plateList = string;
	}

	/**
	 * @param date
	 */
	public void setRunDate(Date date) {
		runDate = date;
	}

	/**
	 * @param integer
	 */
	public void setRunId(Integer integer) {
		runId = integer;
	}

	

	/**
	 * @return
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @return
	 */
	public String getTestList() {
		return testList;
	}

	/**
	 * @param project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @param string
	 */
	public void setTestList(String string) {
		testList = string;
	}

	/**
	 * @return
	 */
	public String getPlateNameList() {
		return plateNameList;
	}

	/**
	 * @return
	 */
	public String getTestNameList() {
		return testNameList;
	}

	/**
	 * @param string
	 */
	public void setPlateNameList(String string) {
		plateNameList = string;
	}

	/**
	 * @param string
	 */
	public void setTestNameList(String string) {
		testNameList = string;
	}
	

	/**
	 * @return Returns the results.
	 */
	public Set getResults() {
		//log.debug("the number of result is" + results.size());
		return results;
	}
	/**
	 * @param results The results to set.
	 */
	public void setResults(Set results) {
		this.results = results;
	}
}
