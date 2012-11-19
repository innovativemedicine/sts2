/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus;
import java.util.*;
import agtc.sampletracking.model.*;

/**
 * @author Hongjing
 *
 * This class is used to hold selected list, both for samples and for plates 
 */
public class SampleListHolder {
	private List originalList;
	private List selectList;
	
	
	public SampleListHolder(){
		selectList = new ArrayList();
	}
	
	/**
	 * @return Returns the originalList.
	 */
	public List getOriginalList() {
		return originalList;
	}
	/**
	 * @param originalList The originalList to set.
	 */
	public void setOriginalList(List originalList) {
		this.originalList = originalList;
	}
	
	/**
	 * @param idx the index of the originalList to be added to the selectList.
	 */
	public void addToSelectList(int idx){
		selectList.add(originalList.get(idx));
	}
	
	public void clearSelectList(){
		selectList = new ArrayList();
	}
	
	public void reSet(){
		selectList = new ArrayList();
		originalList = new ArrayList();
	}
	

	/**
	 * @return Returns the selectList.
	 */
	public List getSelectList() {
		return selectList;
	}
	/**
	 * @param selectList The selectList to set.
	 */
	public void setSelectList(List selectList) {
		this.selectList = selectList;
	}
}
