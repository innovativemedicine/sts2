/*
 * Created on Mar 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.comparator;

import java.util.Comparator;

import agtc.sampletracking.model.Run;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RunComparator implements Comparator {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		Run a1 = (Run)o1;
		Run a2 = (Run)o2;
		String name1 = a1.getNote();
		String name2 = a2.getNote();
		if(name1!=null && name2!=null){
			return name1.compareTo(name2);
		}else{
			return 0;
		}
	}

}
