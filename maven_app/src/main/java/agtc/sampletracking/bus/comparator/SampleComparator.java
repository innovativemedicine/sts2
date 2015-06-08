/*
 * Created on Jan 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.comparator;

import java.util.Comparator;
import agtc.sampletracking.model.*;

/**
 * @author Gloria
 * 
 *         compare two samples, to sort sample list
 */
public class SampleComparator implements Comparator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		Sample s1 = (Sample)o1;
		Sample s2 = (Sample)o2;
		String interlId1 = s1.getPatient().getIntSampleId();
		String interlId2 = s2.getPatient().getIntSampleId();
		int compareResult = interlId1.compareTo(interlId2);
		
		// If Int sample ID are equal, then sort by sample type suffix
		if (compareResult == 0)
		{
			String st1 = s1.getSampleType().getSuffix();
			String st2 = s2.getSampleType().getSuffix();
			
			compareResult = st1.compareTo(st2);
		}
		
		// If sample type suffix are equal, then sort by dup number
		if (compareResult == 0)
		{
			Integer num1 = s1.getSampleDupNo();
			Integer num2 = s2.getSampleDupNo();
			
			compareResult = num1.compareTo(num2);
		}
		
		return compareResult;
	}
}
