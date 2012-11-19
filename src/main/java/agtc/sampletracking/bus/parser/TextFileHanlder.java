/*
 * Created on Dec 7, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.bus.parser;
import java.io.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TextFileHanlder {
	protected BufferedReader br;
	
	public TextFileHanlder(InputStream is){
		br = new BufferedReader(new InputStreamReader(is));
	}
	
	
	
}
