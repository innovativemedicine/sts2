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
import java.text.*;

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
	private List allContainers;
	private List allReagentBoxes;
	private List allPlates;
	private boolean refresh = false;
	

	/**
	 * @return Returns the allContainers.
	 */
	public List getAllContainers() {
		if(allContainers==null || refresh == true){
			allContainers = containerDAO.getContainers();
			refresh = false;
		}
		return allContainers;
	}
	/**
	 * @return Returns the allPlates.
	 */
	public List getAllPlates() {
		//if(allPlates==null || refresh == true ){
			allPlates = containerDAO.getAllPlates();
			//refresh = false;
		//}
		return allPlates;
	}
	/**
	 * @return Returns the allReagentBoxes.
	 */
	public List getAllReagentBoxes() {
		if(allReagentBoxes==null || refresh == true ){
			allReagentBoxes = containerDAO.getAllReagentBoxes();
			refresh = false;
		}
		return allReagentBoxes;
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
		container.getChildContainers().clear();
		containerDAO.removeContainer(containerId);
	}
	
	
	/**
	 * 
	 * see comments of ContainerSearchFields.java
	 */
	public List searchContainer(List crtList,List lgcList){
		
		return containerDAO.getContainers(crtList,lgcList);
	}
	/**
	 * @return
	 */
	public ContainerTypeDAO getContainerTypeDAO() {
		return containerTypeDAO;
	}

	/**
	 * @return
	 */
	public LocationDAO getLocationDAO() {
		return locationDAO;
	}


	/**
	 * @param typeDAO
	 */
	public void setContainerTypeDAO(ContainerTypeDAO typeDAO) {
		containerTypeDAO = typeDAO;
	}

	/**
	 * @param locationDAO
	 */
	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	/**
	 * @return
	 */
	public ContainerDAO getContainerDAO() {
		return containerDAO;
	}

	/**
	 * @param containerDAO
	 */
	public void setContainerDAO(ContainerDAO containerDAO) {
		this.containerDAO = containerDAO;
	}

	
	/**
	 * @return Returns the samplesInContainerDAO.
	 */
	public SamplesInContainerDAO getSamplesInContainerDAO() {
		return samplesInContainerDAO;
	}
	/**
	 * @param samplesInContainerDAO The samplesInContainerDAO to set.
	 */
	public void setSamplesInContainerDAO(
			SamplesInContainerDAO samplesInContainerDAO) {
		this.samplesInContainerDAO = samplesInContainerDAO;
	}
}
