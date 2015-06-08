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

import agtc.sampletracking.dao.LocationDAO;
import agtc.sampletracking.model.Location;

/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class LocationDAOHbImpl
	extends HibernateDaoSupport
	implements LocationDAO {
	private Log log = LogFactory.getLog(LocationDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.LocationDAO#Locations()
	 */
	public List Locations() {
		return  getHibernateTemplate().find("select l from Location l order by l.name");
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.LocationDAO#getLocation(java.lang.Integer)
	 */
	public Location getLocation(Integer locationId) {
		return (Location)(getHibernateTemplate().get(Location.class,locationId));
	}
	
	public Location getLocation(String name) {
		return (Location)(getHibernateTemplate().find("from Location l where l.name=?",name).get(0));
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.LocationDAO#saveLocation(agtc.sampletracking.model.Location)
	 */
	public void saveLocation(Location location) throws Exception {
		getHibernateTemplate().saveOrUpdate(location);
		if(log.isDebugEnabled()){
			log.debug("Location ID set to :" + location.getLocationId());
		}

	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.LocationDAO#removeLocation(java.lang.Integer)
	 */
	public void removeLocation(Integer locationId) {
		Object location = getHibernateTemplate().load(Location.class,locationId);
		getHibernateTemplate().delete(location);

	}

}
