package agtc.sampletracking.bus.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.dao.ContainerTypeDAO;
import agtc.sampletracking.dao.InstrumentDAO;
import agtc.sampletracking.dao.InvestigatorDAO;
import agtc.sampletracking.dao.LocationDAO;
import agtc.sampletracking.dao.SampleTypeDAO;
import agtc.sampletracking.model.ContainerType;
import agtc.sampletracking.model.Instrument;
import agtc.sampletracking.model.Investigator;
import agtc.sampletracking.model.Location;
import agtc.sampletracking.model.SampleType;

public class AGTCManager {
	private Log					log				= LogFactory.getLog(AGTCManager.class);
	private ContainerTypeDAO	containerTypeDAO;
	private InstrumentDAO		instrumentDAO;
	private InvestigatorDAO		investigatorDAO;
	private LocationDAO			locationDAO;
	private SampleTypeDAO		sampleTypeDAO;
	private List				sampleTypes		= new ArrayList();
	private List				locations		= new ArrayList();
	private List				containerTypes	= new ArrayList();
	private List				plateTypes		= new ArrayList();
	private List				boxTypes		= new ArrayList();
	private List				instruments		= new ArrayList();
	private List				investigators	= new ArrayList();
	private boolean				refresh			= true;

	public List getContainerTypes() {
		if (containerTypes.isEmpty() || refresh == true) {
			containerTypes = containerTypeDAO.getContainerTypes();
		}
		return containerTypes;
	}

	public List getPlateTypes() {
		if (plateTypes.isEmpty() || refresh == true) {
			plateTypes = containerTypeDAO.getPlateTypes();
		}
		return plateTypes;
	}

	public List getBoxTypes() {
		if (boxTypes.isEmpty() || refresh == true) {
			boxTypes = containerTypeDAO.getBoxTypes();
		}
		return boxTypes;
	}

	public List getInstruments() {
		if (instruments.isEmpty() || refresh == true) {
			instruments = instrumentDAO.getInstruments();
		}
		return instruments;
	}

	/**
	 * @return Returns the investigators.
	 */
	public List getInvestigators() {
		if (investigators.isEmpty() || refresh == true) {
			investigators = investigatorDAO.getInvestigators();
		}
		return investigators;
	}

	/**
	 * @return Returns the locations.
	 */
	public List getLocations() {
		if (locations.isEmpty() || refresh == true) {
			locations = locationDAO.Locations();
		}
		return locations;
	}

	public List getSampleTypes() {
		if (sampleTypes.isEmpty() || refresh == true) {
			sampleTypes = sampleTypeDAO.getSampleTypes();
		}
		return sampleTypes;
	}

	public SampleType getSampleType(Integer SampleTypeID) {
		return sampleTypeDAO.getSampleType(SampleTypeID);
	}

	public SampleType getSampleTypeBySuffix(String suffix) {
		return sampleTypeDAO.getSampleTypeBySuffix(suffix);
	}

	public void saveSampleType(SampleType stock) throws Exception {
		sampleTypeDAO.saveSampleType(stock);
	}

	public SampleTypeDAO getSampleTypeDAO() {
		return sampleTypeDAO;
	}

	public void setSampleTypeDAO(SampleTypeDAO typeDAO) {
		sampleTypeDAO = typeDAO;
	}

	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	public Location getLocation(Integer LocationID) {
		return locationDAO.getLocation(LocationID);
	}

	public void saveLocation(Location stock) throws Exception {
		locationDAO.saveLocation(stock);
		refresh = true;
	}

	public ContainerType getContainerType(String name) {
		return containerTypeDAO.getContainerType(name);
	}

	public ContainerType getContainerType(Integer ContainerTypeID) {
		return containerTypeDAO.getContainerType(ContainerTypeID);
	}

	public void saveContainerType(ContainerType stock) throws Exception {
		containerTypeDAO.saveContainerType(stock);
	}

	public ContainerTypeDAO getContainerTypeDAO() {
		return containerTypeDAO;
	}

	public void setContainerTypeDAO(ContainerTypeDAO typeDAO) {
		containerTypeDAO = typeDAO;
	}

	public Instrument getInstrument(String name) {
		return instrumentDAO.getInstrument(name);
	}

	public Instrument getInstrument(Integer InstrumentID) {
		return instrumentDAO.getInstrument(InstrumentID);
	}

	public void saveInstrument(Instrument stock) throws Exception {
		instrumentDAO.saveInstrument(stock);
	}

	public InstrumentDAO getInstrumentDAO() {
		return instrumentDAO;
	}

	public void setInstrumentDAO(InstrumentDAO typeDAO) {
		instrumentDAO = typeDAO;
	}

	public Investigator getInvestigator(Integer InvestigatorID) {
		return investigatorDAO.getInvestigator(InvestigatorID);
	}

	public void saveInvestigator(Investigator stock) throws Exception {
		investigatorDAO.saveInvestigator(stock);
	}

	public InvestigatorDAO getInvestigatorDAO() {
		return investigatorDAO;
	}

	public void setInvestigatorDAO(InvestigatorDAO typeDAO) {
		investigatorDAO = typeDAO;
	}
}