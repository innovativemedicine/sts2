/*
 * Created on Oct 27, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web;
import java.util.*;
import agtc.sampletracking.ConstantInterface;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class StsUtil implements ConstantInterface  {
	public static Map actionLevelMaps = new HashMap();
	public StsUtil(){
		actionLevelMaps.put("/SampleTracking/index.htm",new Integer(NO_DEF));
		actionLevelMaps.put("/SampleTracking/projects.htm",new Integer(MANAGER_LEVEL));
		actionLevelMaps.put("/SampleTracking/editProject",new Integer(MANAGER_LEVEL));
	}
	public static int getActionLevel(String uri){
		Integer integer = (Integer)(actionLevelMaps.get(uri));
		if(integer != null){
			return integer.intValue();
		}else{
			return -1;
		}
	}
	
	
}
