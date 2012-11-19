/*
 * Created on Dec 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.bus;

import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.manager.*;

import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class IdListResolver {
	private TestManager testManager;
	private ContainerManager containerManager;
	private Log log = LogFactory.getLog(IdListResolver.class);
	
	public String resolveAssayIdList(String assayIdList){
		String assayNameList="";
		//log.debug("the assayId List is " + assayIdList);
		int i = 0;
		if(assayIdList != null && assayIdList.length() >0 ){
			String ss = assayIdList.substring(1,assayIdList.length()-1);
			StringTokenizer st = new StringTokenizer(ss,",,");
			 while (st.hasMoreTokens()) {
				String s = st.nextToken();
				//log.debug("the token is " + s);
				String name = "";
				try{
					name = testManager.getAssay(Integer.valueOf(s)).getName();
				}catch(Exception e){
					i++;
				}
				 assayNameList += name +" , ";
			 }

		}
		 if(i>0){
		 	assayNameList += i + " unknown ";
		}
		return assayNameList;
	}
	
	public String resolveTestIdList(String testIdList){
		String testNameList="";
		// for unknown test most probaly deleted
		int i = 0;
		
		if(testIdList != null && testIdList.length() >0 ){
			String ss = testIdList.substring(1,testIdList.length()-1);
			StringTokenizer st = new StringTokenizer(ss,",,");
			 while (st.hasMoreTokens()) {
				String s = st.nextToken();
				log.debug("the token is " + s);
				String name = "";
				try{
					name = testManager.getTest(Integer.valueOf(s)).getName();
				}catch(Exception e){
					i++;
				}
				 testNameList += name +" , ";
			 }

		}
		if(i>0){
			testNameList += i + " unknown ";
		}
		return testNameList;
	}
	
	public String resolvePlateIdList(String plateIdList){
		String plateNameList="";
		log.debug("the plateId List is " + plateIdList);
		int i = 0;
		if(plateIdList != null && plateIdList.length() >0 ){
			String ss = plateIdList.substring(1,plateIdList.length()-1);
			StringTokenizer st = new StringTokenizer(ss,",,");
			 while (st.hasMoreTokens()) {
				String s = st.nextToken();
				 log.debug("the token is " + s);
				String name = "";
				try{
					name = containerManager.getContainer(Integer.valueOf(s)).getName();
				}catch(Exception e){
					i++;
				}
				plateNameList += name +" , ";
				
			 }

		}
		if(i>0){
			plateNameList += i + " unknown ";
		}
		return plateNameList;
	}

			
	/**
	 * @return
	 */
	public TestManager getTestManager() {
		return testManager;
	}

	/**
	 * @param manager
	 */
	public void setTestManager(TestManager manager) {
		testManager = manager;
	}

	/**
	 * @return
	 */
	public ContainerManager getContainerManager() {
		return containerManager;
	}

	/**
	 * @param manager
	 */
	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}

}
