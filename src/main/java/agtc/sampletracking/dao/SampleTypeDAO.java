/*
 * Created on Sep 30, 2004
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

public interface SampleTypeDAO {
	public List getSampleTypesWithVials();
	public List getSampleTypes();
	public List getSampleTypesWOVials();
	public SampleType getSampleType(Integer sampleTypeId);
	public SampleType getSampleTypeBySuffix(String suffix);
	public void saveSampleType(SampleType sampelType) throws Exception;
	public void removeSampleType(Integer sampleTypeId); 
}
