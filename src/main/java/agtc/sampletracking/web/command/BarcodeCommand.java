/*
 * Created on Oct 21, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.command;
import java.util.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BarcodeCommand {
	private Object barcodeItem;
	
	public BarcodeCommand(){
		barcodeItem = new Object();
	}
	
	public String toString(){
		return barcodeItem.toString();
	}
	

	public Object getBarcodeItem() {
		return barcodeItem;
	}

	public void setBarcodeItem(Object string) {
		barcodeItem = string;
	}
}
