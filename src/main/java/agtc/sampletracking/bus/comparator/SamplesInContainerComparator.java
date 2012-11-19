/*
 * Created on Jul 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.comparator;

import java.util.Comparator;

import agtc.sampletracking.model.SamplesInContainer;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SamplesInContainerComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		SamplesInContainer s1 = (SamplesInContainer)o1;
		SamplesInContainer s2 = (SamplesInContainer)o2;
		String interlId1 = "";
		String interlId2 = "";
		if(s1.getSample()!=null){
			interlId1 = s1.getSample().getPatient().getIntSampleId();
		}
		if(s2.getSample()!=null){
			interlId2 = s2.getSample().getPatient().getIntSampleId();
		}
		return interlId1.compareTo(interlId2);
	}
}
