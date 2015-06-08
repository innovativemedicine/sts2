/*
 * Created on Sep 30, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao;

import java.util.*;
import agtc.sampletracking.model.*;
/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

public interface InstrumentDAO {
	public List getInstruments();
	public Instrument getInstrument(Integer instrumentId);
	public Instrument getInstrument(String name);
	public void saveInstrument(Instrument instrument) throws Exception;
	public void removeInstrument(Integer instrumentId); 
}
