/*
 * Created on Nov 4, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.bus;
import agtc.sampletracking.model.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.bus.comparator.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class PlateWorker {
	private Log log = LogFactory.getLog(PlateWorker.class);
	private Sample[][] sampleArray;
	private SamplesInContainer[][] samplesInContainerArray;
	private List unorderedSamples;
	private List unorderedSamplesInContainers;
	int capacity;
	int maxRow;
	int maxVolume;
	// if user want to get ordered samples or samples_in_container
	private boolean orderSamplesInContainer = false;
	
	
	public PlateWorker(ContainerType containerType){
		maxRow = containerType.getRowNo().intValue();
		maxVolume = containerType.getColumnNo().intValue();
		capacity = maxRow*maxVolume;
		
	}
	
	public PlateWorker(ContainerType containerType,boolean orderSamplesInContainer){
		this(containerType);
		this.orderSamplesInContainer = orderSamplesInContainer;
		
		
	}
	
	public void formatContainer(Set allSamplesInContainer){
		if(orderSamplesInContainer){
			
			samplesInContainerArray = new SamplesInContainer[maxRow][maxVolume];
			unorderedSamplesInContainers = new LinkedList();
			
		}else{
			sampleArray = new Sample[maxRow][maxVolume]; 
			unorderedSamples = new LinkedList();
		}
		
		
		
		if(allSamplesInContainer!=null){
			Iterator i = allSamplesInContainer.iterator();
			String location = "";
			while ( i.hasNext()){
				SamplesInContainer samplesInContainer=(SamplesInContainer)(i.next());
				//log.debug(samplesInContainer.toString());
	 			if(samplesInContainer!=null){
					location = samplesInContainer.getWell();
	 			}
	 			
				
				if(location==null || "".equals(location)|| "null".equals(location)){
					if(orderSamplesInContainer){
						unorderedSamplesInContainers.add(samplesInContainer);
					}else{
						unorderedSamples.add(samplesInContainer.getSample());
					}
				}else{
					//log.debug("location is" + location);
					String volume = location.substring(1);
					//log.debug("volum is " + volume);
					String row = location.substring(0,1);
					//log.debug("volum is " + row);
					try{
						int iVolume = Integer.parseInt(volume);
						int iRow = letter2Number(row);
						if(iVolume!= 0){
							if(iVolume <= maxVolume && iRow <= maxRow){
								if(orderSamplesInContainer){
									samplesInContainerArray[iRow-1][iVolume-1] = samplesInContainer;
								}else{
									sampleArray[iRow-1][iVolume-1] = samplesInContainer.getSample();
								}
								
								//log.debug("array loci is " +iVolume +"  " + iRow + " sampe is " + samplesInContainer.getSample());
							}else{
								if(orderSamplesInContainer){
									unorderedSamplesInContainers.add(samplesInContainer);
								}else{
									unorderedSamples.add(samplesInContainer.getSample());
								}
							}
						}else{
							if(orderSamplesInContainer){
								unorderedSamplesInContainers.add(samplesInContainer);
							}else{
								unorderedSamples.add(samplesInContainer.getSample());
							}
							
						}
						
					}catch(Exception e){
						if(orderSamplesInContainer){
							unorderedSamplesInContainers.add(samplesInContainer);
						}else{
							unorderedSamples.add(samplesInContainer.getSample());
						}
					}
				}
					
			
			}
		}
	}
	

	
	
	
	public Sample[][] getOrderedSamples(){
		return sampleArray;
	}
	
	public SamplesInContainer[][] getOrderedSamplesInContainer(){
		return samplesInContainerArray;
	}
	
	public List getUnOrderedSamples(){
		Collections.sort(unorderedSamples,new SampleComparator());
		return unorderedSamples;
	}
	
	public List getUnOrderedSamplesInContainer(){
		Collections.sort(unorderedSamplesInContainers,new SamplesInContainerComparator());
		return unorderedSamplesInContainers;
	}
			
	
	public int letter2Number(String letter){
		letter.toUpperCase();
		if("A".equals(letter)){
			return 1;
		}else if("B".equals(letter)){
			return 2;
		}else if("C".equals(letter)){
			return 3;
		}else if("D".equals(letter)){
			return 4;
		}else if("E".equals(letter)){
			return 5;
		}else if("F".equals(letter)){
			return 6;
		}else if("G".equals(letter)){
			return 7;
		}else if("H".equals(letter)){
			return 8;
		}else if("I".equals(letter)){
			return 9;
		}else if("J".equals(letter)){
			return 10;
		}else if("K".equals(letter)){
			return 11;
		}else if("L".equals(letter)){
			return 12;
		}else if("M".equals(letter)){
			return 13;
		}else if("N".equals(letter)){
			return 14;
		}else if("O".equals(letter)){
			return 15;
		}else if("P".equals(letter)){
			return 16;
		}else{
			return 0;
		}
	}
		
	public String number2Letter(int number){
		
        switch (number) {
            case 1:  return "A";
            case 2:  return "B";
            case 3:  return "C"; 
            case 4:  return "D"; 
            case 5:  return "E";
            case 6:  return "F";
            case 7:  return "G"; 
            case 8:  return "H";
            case 9:  return "I";
            case 10:  return "J"; 
            case 11:  return "K";
            case 12:  return "L";
            case 13:  return "M"; 
            case 14:  return "N"; 
            case 15:  return "O";
            case 16:  return "P";
          
            default:return "";

		
        }
	
	}

	
}
