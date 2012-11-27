package agtc.sampletracking.model;

import java.util.*;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;

public class MultiSamples implements Serializable,Cloneable {
//	private Log log = LogFactory.getLog(Sample.class);
   
    private List multiSamples;
    
    public MultiSamples() {
    	multiSamples = LazyList.decorate(new ArrayList(), FactoryUtils.instantiateFactory(Sample.class));
    }
    
    public List getMultiSamples() {
    	return multiSamples;
    }
    
    public void setMultiSamples(List list) {
    	multiSamples = list;
    }
    
}
