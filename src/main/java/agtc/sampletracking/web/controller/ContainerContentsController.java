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
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
		Container container = containerManager.getContainer(new Integer(RequestUtils.getIntParameter(request, "containerId", -1)));
		String isOrdered = RequestUtils.getStringParameter(request, "isOrdered", "");
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
					columnNo = 4;
					rowNo = (container.getContainerType().getCapacity())/columnNo;
					
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

	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws Exception {
		int containerId = RequestUtils.getIntParameter(request, "containerId", -1);
		Container container = containerManager.getContainer(new Integer(containerId));
		ContainerContentCommand ccCommand = (ContainerContentCommand)command;
		ContainerContentCellUnit[][] cells = ccCommand.getCells();
		List samplesInContainers = new ArrayList();
		StringBuffer message = new StringBuffer();
		boolean ordered = true;
		// unordered sample is in 4 columns for easy display
		if(ccCommand.getColumnNo()==4){
			ordered = false;
		}
		
		for(int a=0;a<ccCommand.getRowNo();a++){
			for(int b=0;b<ccCommand.getColumnNo();b++){
				
				ContainerContentCellUnit oneCell = cells[a][b];
				// the sampleDesc can be intSampleId(in case user type the internal sample id or intSampleId+sampleTypeSuffix (in case user scanning)
				String sampleDesc = oneCell.getSampleDesc().trim();
				String dupNo = oneCell.getDupNo();
				Integer dupNoI = new Integer(1);
				if (dupNo!=null && !dupNo.equals("")){
					dupNoI = new Integer(dupNo);
				}
				//log.debug("sampleDesc is " + sampleDesc + " well is " + oneCell.getWell());
				if(oneCell.isNotOccupied() && sampleDesc.length()>0){
					String intSampleId = "";
					String sampleTypeSuffix = getSampleTypeSuffixFromSampleDesc(sampleDesc);
					if(sampleTypeSuffix.equals("")){  // user type intSampleId
						sampleTypeSuffix = oneCell.getSampleTypeSuffix();
						intSampleId = sampleDesc;
					}else{ //user scaning
						intSampleId = getIntSampleIdFromSampleDesc(sampleDesc,sampleTypeSuffix);
					}
					log.debug("intSampleId is " + intSampleId + " sampleTypeSuffix is " + sampleTypeSuffix);
					Sample sample = sampleManager.getSample(intSampleId,sampleTypeSuffix,dupNoI);
					log.debug("got one sample " + sample);
					if(sample == null){
						if(message.length()>0){
							message.append(",");
						}
						message.append(intSampleId+sampleTypeSuffix+dupNo);
						
					}else{
						String well = oneCell.getWell();
					
						SamplesInContainer sic = new SamplesInContainer();
						sic.setSicId(new Integer(-1));
						sic.setContainer(container);
						sic.setSample(sample);
						sic.setWell(well);
						sic.setOperation("I");
						sic.setOperationDate(new Date());
						samplesInContainers.add(sic);
					}
				}
				
			}
			
		}
		
		if(message.length()>0){
			message.insert(0,"The following samples are not in STS yet: ");
		}
		message.append("<br>");
		//log.debug("going to save SamplesInContainer " + samplesInContainers.size());
		//	if it comes to here, means all samples are in STS
		if(samplesInContainers.size()>0){
			sampleManager.saveSamplesInContainerList(samplesInContainers);
			message.insert(0,"Have successfully put the samples ! <br>");
		}
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message",message.toString());
		myModel.put("containerId",new Integer(containerId));
		String isOrdered = "";
		if(ordered){
			isOrdered = "ordered";
		}else{
			isOrdered = "unOrdered";
		}
		myModel.put("isOrdered",isOrdered);
		return view;
	}

	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
									  java.lang.Object command,Errors errors)
							   throws java.lang.Exception
	{
		Container container = containerManager.getContainer(new Integer(RequestUtils.getIntParameter(request, "containerId", -1)));
		Map models = new HashMap();
		if(container!=null){
			models.put("container", container);
		}
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		List allSampleTypes = agtcManager.getSampleTypes();
		List numbers = new ArrayList();
		for(int i = 1;i<11;i++){
			numbers.add(new Integer(i));
		}
		models.put("availableSampleTypes",allSampleTypes);
		models.put("numbers",numbers);
		return models;
	}
	
	protected String getSampleTypeSuffixFromSampleDesc(String originalS){
		List allSampleTypes = agtcManager.getSampleTypes();
		for(int i=0;i<allSampleTypes.size();i++){
			SampleType sampleType = (SampleType)allSampleTypes.get(i);
			if(originalS.indexOf(sampleType.getSuffix())>0){
				return sampleType.getSuffix();
			}
		}
		return "";
	}
	
	protected String getIntSampleIdFromSampleDesc(String originalS,String sampleTypeSuffix){
		
		return originalS.substring(0,originalS.indexOf(sampleTypeSuffix));
	}
	

	/**
	 * @return Returns the agtcManager.
	 */
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}
	/**
	 * @param agtcManager The agtcManager to set.
	 */
	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
	/**
	 * @return Returns the containerManager.
	 */
	public ContainerManager getContainerManager() {
		return containerManager;
	}
	/**
	 * @param containerManager The containerManager to set.
	 */
	public void setContainerManager(ContainerManager containerManager) {
		this.containerManager = containerManager;
	}
	/**
	 * @return Returns the sampleManager.
	 */
	public SampleManager getSampleManager() {
		return sampleManager;
	}
	/**
	 * @param sampleManager The sampleManager to set.
	 */
	public void setSampleManager(SampleManager sampleManager) {
		this.sampleManager = sampleManager;
	}
}
