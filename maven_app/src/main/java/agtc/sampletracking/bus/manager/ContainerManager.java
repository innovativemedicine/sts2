/*
 * Created on Nov 2, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.bus.manager;

import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.*;

/**
 * @author Gloria Deng
 *
 * This is the middle class between web object and DAO object, it is also the target class 
 * that spring framework provide the transaction management
 */
public class ContainerManager {
	private Log log = LogFactory.getLog(ContainerManager.class);
	private ContainerDAO containerDAO;
	private LocationDAO locationDAO;
	private ContainerTypeDAO containerTypeDAO;
	private SamplesInContainerDAO samplesInContainerDAO;
	private List<Container> allContainers;
	private List<Container> allBoxes;
	private List<Container> allPlates;
	private boolean refresh = false;
	
	public List<Container> getAllContainers() {
		if(allContainers==null || refresh == true){
			allContainers = containerDAO.getContainers();
			refresh = false;
		}
		return allContainers;
	}

	public List<Container> getAllPlates() {
		if(allPlates==null || refresh == true ){
			allPlates = containerDAO.getAllPlates();
			refresh = false;
		}
		return allPlates;
	}
	
	public List<Container> getAllBoxes() {
		if(allBoxes==null || refresh == true ){
			allBoxes = containerDAO.getAllBoxes();
			refresh = false;
		}
		return allBoxes;
	}
	
	public String getLargestPlateId(String platePrefix){
		String largestId = containerDAO.getLargestPlateId(platePrefix);
		
		return largestId;
	}

	public Container getContainer(Integer stockId){
		return containerDAO.getContainer(stockId);
	}
	
	public Container getContainer(String name){
		return containerDAO.getContainer(name);
	}
	
	
	public List getSamplesInContainerByContainer(Integer containerId){
		return samplesInContainerDAO.getSamplesInContainersByContainer(containerId);
	}
	
	
	public ContainerType getContainerType(String name){
		return containerTypeDAO.getContainerType(name);
	}
	
	public ContainerType getContainerType(Integer ContainerTypeID){
		return containerTypeDAO.getContainerType(ContainerTypeID);
	}

	public Location getLocation(String name){
		return locationDAO.getLocation(name);
	}
	

	
	public void saveContainer(Container stock) throws Exception {
		containerDAO.saveContainer(stock);
		refresh = true;
	}
	
	//if there is samples in this container, could not remove it, in Container.hbm.cml, the assocations 
	// with samplesInContainer is set to cascade="save-update", if there is child containers in the removed container
	// will set the motherContainer of all its child container to null;
	public void removeContainer(Integer containerId){
		Container container = getContainer(containerId);
		Set kids = container.getChildContainers();
		if(kids!=null && kids.size()>0){
			Iterator i = kids.iterator();
			while(i.hasNext()){
				Container kid = (Container)i.next();
				kid.setMotherContainer(null);
			}
		}
		
		refresh = true;
		
		container.getChildContainers().clear();
		containerDAO.removeContainer(containerId);
	}
	
	public List searchContainer(List crtList,List lgcList){
		
		return containerDAO.getContainers(crtList,lgcList);
	}

	public ContainerTypeDAO getContainerTypeDAO() {
		return containerTypeDAO;
	}

	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	public void setContainerTypeDAO(ContainerTypeDAO typeDAO) {
		containerTypeDAO = typeDAO;
	}

	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	public ContainerDAO getContainerDAO() {
		return containerDAO;
	}

	public void setContainerDAO(ContainerDAO containerDAO) {
		this.containerDAO = containerDAO;
	}
	
	public SamplesInContainerDAO getSamplesInContainerDAO() {
		return samplesInContainerDAO;
	}

	public void setSamplesInContainerDAO(
			SamplesInContainerDAO samplesInContainerDAO) {
		this.samplesInContainerDAO = samplesInContainerDAO;
	}
}
