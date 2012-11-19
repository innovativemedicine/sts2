/*
 * Created on Mar 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.comparator;

import java.util.Comparator;

import agtc.sampletracking.model.Assay;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssayComparator implements Comparator {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		Assay a1 = (Assay)o1;
		Assay a2 = (Assay)o2;
		String name1 = a1.getName();
		String name2 = a2.getName();
		return name1.compareTo(name2);
	}

}
