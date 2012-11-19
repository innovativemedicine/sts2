/*
 * Created on Dec 7, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.command;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.*;
import agtc.sampletracking.model.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SaveSamplesCommand {
	private Log log = LogFactory.getLog(LoginFormCommand.class);
	
   	
   	private String isControl;
   	private String project;
	private SampleType sampleType;
	
	
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
	 * @return Returns the sampleType.
	 */
	public SampleType getSampleType() {
		return sampleType;
	}
	/**
	 * @param sampleType The sampleType to set.
	 */
	public void setSampleType(SampleType sampleType) {
		this.sampleType = sampleType;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
}
