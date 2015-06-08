/*
 * Created on Jul 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;

import agtc.sampletracking.bus.PlateWorker;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.command.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContainerContentsController extends BasicController {
	private Log log = LogFactory.getLog(ContainerContentsController.class);
	protected ContainerManager containerManager;
	protected SampleManager sampleManager;
	protected AGTCManager agtcManager;
	
	public ContainerContentsController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);

	}
	

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		Container container = containerManager.getContainer(new Integer(ServletRequestUtils.getIntParameter(request, "containerId", -1)));
		String isOrdered = ServletRequestUtils.getStringParameter(request, "isOrdered", "");
		ContainerContentCommand result = new ContainerContentCommand();
		if(container != null){
			Set allSamplesInContainer = container.getSamplesInContainers();
			if(allSamplesInContainer.size()==0 && isOrdered.equals("")){
				result.setEmptyContainer(true);
				result.setContainerName(container.getName());
			}else{
				ContainerType currentContainerType = container.getContainerType();
				
					//log.debug("the length of List is " + allSamplesInContainer.size());
				PlateWorker plateWorker = new PlateWorker(currentContainerType,true);
				plateWorker.formatContainer(allSamplesInContainer);
				SamplesInContainer[][] orderedSamplesInContainer = plateWorker.getOrderedSamplesInContainer();
				List unOrderedSamplesInContainers = plateWorker.getUnOrderedSamplesInContainer();
				ContainerContentCellUnit[][] cells = null;
				int rowNo = 0;
				int columnNo = 0;
				boolean unOrdered = false;
				
				// in this case, there is no sample in the container
	
				if(!isOrdered.equals("")){
					result.setContainerId(container.getContainerId());
					if(isOrdered.equals("unOrdered")){
						unOrdered = true;
					}
					
					
				}
				if(unOrderedSamplesInContainers.size()>0 || unOrdered){ // in this case, is unorderedSamples, give a four colume array to jsp
					columnNo = container.getContainerType().getColumnNo();
					rowNo = container.getContainerType().getRowNo();
					
					cells = new ContainerContentCellUnit[rowNo][columnNo];
					int counter = 0;
					for(int a=0;a<rowNo;a++){
						for(int b=0;b<columnNo;b++){
							SamplesInContainer oneCell = null;
							if(counter<unOrderedSamplesInContainers.size()){
								oneCell = (SamplesInContainer)unOrderedSamplesInContainers.get(counter);
							}
							cells[a][b] = new ContainerContentCellUnit(oneCell);
							counter++;
						}
						
					}
					
				}else{ //in this case, is ordered samples,
					rowNo = orderedSamplesInContainer.length;
					columnNo = orderedSamplesInContainer[0].length;
					cells = new ContainerContentCellUnit[rowNo][columnNo];
					for(int a=0;a<rowNo;a++){
						for(int b=0;b<columnNo;b++){
							SamplesInContainer oneCell = orderedSamplesInContainer[a][b];
							cells[a][b] = new ContainerContentCellUnit(oneCell);
							if(oneCell==null){
								String well = plateWorker.number2Letter(a+1)+(b+1);
								cells[a][b].setWell(well);
							}else{
								
							}
							
							
						}
					}
				}
				log.debug(container.getName() + "  " + container.isNoneSample());
				result.setCells(cells);
				result.setRowNo(rowNo);
				result.setColumnNo(columnNo);
			
			}
			
		}else{
			result.setNoneContainer(true);
		}
		
		return result;
		
	}
	
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}

	public ContainerManager getContainerManager() {
		return containerManager;
	}

	public void setContainerManager(ContainerManager containerManager) {
		this.containerManager = containerManager;
	}

	public SampleManager getSampleManager() {
		return sampleManager;
	}

	public void setSampleManager(SampleManager sampleManager) {
		this.sampleManager = sampleManager;
	}
}
