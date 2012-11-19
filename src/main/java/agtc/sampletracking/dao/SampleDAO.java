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
public interface SampleDAO {
	public List getSamples();
	public List getSamples(List sampleIds,List sampleTypeSuffixes,Integer sampleDupNo);

	public Sample getSample(Integer sampleId);
	public void saveSample(Sample sample) throws Exception;
	public void removeSample(Integer sampleId);
	public List searchSamples(List crtList,List lgcList);
	public Sample getSampleByIntSampleIdUniKey(String intId,SampleType sampleType,Integer sampleDupNo);
	public List getSampleByIntSampleIdSampleType(String intId,SampleType sampleType);
	public List getSampleByIntSampleIdSampleType(String intId,String[] sampleType);
	public List getSampleByIntSampleIdSampleProject(String[] sampleProject);
	public List getSampleByIntSampleId(String intId);
	public boolean containsIntSampleId(String intSampleId);
	public List getExistingSampleTypes(String intSampleId);
}
