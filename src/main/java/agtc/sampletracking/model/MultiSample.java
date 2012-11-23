package agtc.sampletracking.model;

import java.util.Date;
import java.util.*;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MultiSample implements Serializable,Cloneable {
	private List<Sample> multiSamples;
	
	public List<Sample> getMultiSamples() {
		return multiSamples;
	}

	public void setMultiSamples(List<Sample> multiSamples) {
		this.multiSamples = multiSamples;
	}
}
