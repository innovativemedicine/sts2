/*
 * Created on Jan 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao;

import java.util.List;

import agtc.sampletracking.model.SamplesInContainer;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SamplesInContainerDAO {
	public SamplesInContainer getSamplesInContainer(Integer sicId);
	public void saveSamplesInContainer(SamplesInContainer result);
	public void removeSamplesInContainer(Integer sicId);
	public List getSamplesInContainersByContainer(Integer containerId); 
	public List getSamplesInContainersInBySample(Integer sampleId);
	public void removeSamplesInContainersByContainer(Integer containerId); 
}
