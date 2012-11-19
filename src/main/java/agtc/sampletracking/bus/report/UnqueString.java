package agtc.sampletracking.bus.report;

import java.util.Date;

public class UnqueString {
	private static int i;
	private UnqueString()
	  {
	    i=0;
	  }
	synchronized
	public static String UnqueStr(){
		String s=null;
		Date d = new Date();
		i++;
		s= (new Long(d.getTime())).toString()
			+ i;
		return s;
	}
	
	public Object clone()
	throws CloneNotSupportedException
  {
    throw new CloneNotSupportedException(); 
    // that'll teach 'em
  }
}
