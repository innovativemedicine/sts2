/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.comparator;

import java.util.Comparator;

import agtc.sampletracking.model.Result;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResultComparator implements Comparator {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		Result s1 = (Result)o1;
		Result s2 = (Result)o2;
		String interlId1 = s1.getIntSampleId();
		String interlId2 = s2.getIntSampleId();
		return interlId1.compareTo(interlId2);
	}

}
