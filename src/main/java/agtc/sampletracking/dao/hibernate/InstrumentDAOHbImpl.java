/*
 * Created on Oct 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.dao.InstrumentDAO;
import agtc.sampletracking.model.Instrument;

/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class InstrumentDAOHbImpl
	extends HibernateDaoSupport
	implements InstrumentDAO {
	private Log log = LogFactory.getLog(InstrumentDAOHbImpl.class);	
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.InstrumentDAO#getInstruments()
	 */
	public List getInstruments() {
		return  getHibernateTemplate().find("select i from Instrument i order by i.name");
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.InstrumentDAO#getInstrument(java.lang.Integer)
	 */
	public Instrument getInstrument(Integer instrumentId) {
		return (Instrument)(getHibernateTemplate().get(Instrument.class,instrumentId));
	}
	
	public Instrument getInstrument(String name) {
		return (Instrument)(getHibernateTemplate().find("from Instrument c where c.name=?",name).get(0));
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.InstrumentDAO#saveInstrument(agtc.sampletracking.model.Instrument)
	 */
	public void saveInstrument(Instrument instrument) throws Exception {
		getHibernateTemplate().saveOrUpdate(instrument);
		if(log.isDebugEnabled()){
			log.debug("instrument ID set to :" + instrument.getInstrumentId());
		}

	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.InstrumentDAO#removeInstrument(java.lang.Integer)
	 */
	public void removeInstrument(Integer instrumentId) {
		Object instrument = getHibernateTemplate().load(Instrument.class,instrumentId);
		getHibernateTemplate().delete(instrument);

	}

}
