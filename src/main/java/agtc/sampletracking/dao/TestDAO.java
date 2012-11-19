/*
 * Created on Oct 5, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao;

import java.util.*;
import agtc.sampletracking.model.*;
/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface TestDAO {
	public List getTests();
	public List getTests(List crtList,List lgcList);
	public Test getTest(String  testName);
	public Test getTest(Integer testId);
	public void saveTest(Test test) throws Exception;
	public void removeTest(Integer testId); 
}
