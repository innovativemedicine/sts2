/*
 * Created on Oct 21, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.searchFields;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OperatorList extends BasicSearchFiels{
	public OperatorList(){
		super();
		loadElements();
	}

	public void loadElements(){
		add("contains");
//		add("between");
//		add("after");
		
	}
	
	

}
