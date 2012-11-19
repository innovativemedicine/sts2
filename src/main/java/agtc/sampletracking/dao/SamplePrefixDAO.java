/*
 * Created on Jan 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao;

import java.util.List;

import agtc.sampletracking.model.SamplePrefix;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SamplePrefixDAO {
	public List getSamplePrefixes();
	public SamplePrefix getSamplePrefix(Integer samplePrefixId);
	public SamplePrefix getSamplePrefixByDescription(String prefix);
	public void saveSamplePrefix(SamplePrefix o)throws Exception;
	public void removeSamplePrefix(Integer samplePrefixId); 
}
