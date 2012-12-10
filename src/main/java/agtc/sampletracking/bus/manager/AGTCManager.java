package agtc.sampletracking.bus.manager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.dao.ContainerTypeDAO;
import agtc.sampletracking.dao.InstrumentDAO;
import agtc.sampletracking.dao.InvestigatorDAO;
import agtc.sampletracking.dao.LocationDAO;
import agtc.sampletracking.dao.SamplePrefixDAO;
import agtc.sampletracking.dao.SampleTypeDAO;
import agtc.sampletracking.dao.UpdateResultToCGGDAO;
import agtc.sampletracking.model.ContainerType;
import agtc.sampletracking.model.Instrument;
import agtc.sampletracking.model.Investigator;
import agtc.sampletracking.model.Location;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SamplePrefix;
import agtc.sampletracking.model.SampleType;

public class AGTCManager{
	private Log log = LogFactory.getLog(AGTCManager.class);
	private ContainerTypeDAO containerTypeDAO;
	private InstrumentDAO instrumentDAO;
	private InvestigatorDAO investigatorDAO;
	private LocationDAO locationDAO;
	private SampleTypeDAO sampleTypeDAO;
	private UpdateResultToCGGDAO updateResultToCGGDAO;
	private SamplePrefixDAO samplePrefixDAO;
	private List sampleTypes;
	private List locations;
	private List containerTypes;
	private List plateTypes;
	private List instruments;
	private List investigators;
	private List allSamplePrefixes;
	private boolean refresh = false;
	
	/**
	 * @return Returns the allSamplePrefixes.
	 */
	public List getAllSamplePrefixes() {
		if(allSamplePrefixes==null || refresh == true ){
			allSamplePrefixes = samplePrefixDAO.getSamplePrefixes();
			refresh = false;
		}
		return allSamplePrefixes;
	}

	public List getContainerTypes() {
		if(containerTypes==null || refresh == true){
			containerTypes = containerTypeDAO.getContainerTypes();
			refresh = false;
		}
		return containerTypes;
	}
	
	public List getPlateTypes() {
		if(plateTypes==null || refresh == true){
			plateTypes = containerTypeDAO.getPlateTypes();
			refresh = false;
		}
		return plateTypes;
	}


	public List getInstruments() {
		if(instruments==null || refresh == true ){
			instruments = instrumentDAO.getInstruments();
			refresh = false;
		}
		return instruments;
	}
	/**
	 * @return Returns the investigators.
	 */
	public List getInvestigators() {
		if(investigators==null || refresh == true ){
			investigators = investigatorDAO.getInvestigators();
			refresh = false;
		}
		return investigators;
	}
	/**
	 * @return Returns the locations.
	 */
	public List getLocations() {
		if(locations==null || refresh == true ){
			locations = locationDAO.Locations();
			refresh = false;
		}
		return locations;
	}
	
	public List getSampleTypes() {
		if(sampleTypes==null || refresh == true){
			sampleTypes = sampleTypeDAO.getSampleTypes();
			refresh = false;
		}
		return sampleTypes;
	}
	
	public UpdateResultToCGGDAO getUpdateResultToCGGDAO(){
		return this.updateResultToCGGDAO;
	}
	public void setUpdateResultToCGGDAO(UpdateResultToCGGDAO stock){
	    updateResultToCGGDAO = stock;
	}
	
	public String updateResultToCGG(){
		return updateResultToCGGDAO.updateResult();
	}
	
	public SampleType getSampleType(Integer SampleTypeID){
		return sampleTypeDAO.getSampleType(SampleTypeID);
	}
	
	public SampleType getSampleTypeBySuffix(String suffix){
		return sampleTypeDAO.getSampleTypeBySuffix(suffix);
	}
	
	public void saveSampleType(SampleType stock) throws Exception {
		sampleTypeDAO.saveSampleType(stock);
		refresh = true;
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
	

	public Location getLocation(Integer LocationID){
		return locationDAO.getLocation(LocationID);
	}
		
	public void saveLocation(Location stock) throws Exception {
		locationDAO.saveLocation(stock);
		refresh = true;
	}
	
	public ContainerType getContainerType(String name){
		return containerTypeDAO.getContainerType(name);
	}
	
	public ContainerType getContainerType(Integer ContainerTypeID){
		return containerTypeDAO.getContainerType(ContainerTypeID);
	}

	public void saveContainerType(ContainerType stock) throws Exception {
		containerTypeDAO.saveContainerType(stock);
		refresh = true;
	}
	
	public ContainerTypeDAO getContainerTypeDAO() {
		return containerTypeDAO;
	}

	public void setContainerTypeDAO(ContainerTypeDAO typeDAO) {
		containerTypeDAO = typeDAO;
	}
	
	
	public Instrument getInstrument(String name){
		return instrumentDAO.getInstrument(name);
	}
	
	public Instrument getInstrument(Integer InstrumentID){
		return instrumentDAO.getInstrument(InstrumentID);
	}

	public void saveInstrument(Instrument stock) throws Exception {
		instrumentDAO.saveInstrument(stock);
		refresh = true;
	}
	
	public InstrumentDAO getInstrumentDAO() {
		return instrumentDAO;
	}

	public void setInstrumentDAO(InstrumentDAO typeDAO) {
		instrumentDAO = typeDAO;
	}
	
	public Investigator getInvestigator(Integer InvestigatorID){
		return investigatorDAO.getInvestigator(InvestigatorID);
	}

	public void saveInvestigator(Investigator stock) throws Exception {
		investigatorDAO.saveInvestigator(stock);
		refresh = true;
	}
	
	public InvestigatorDAO getInvestigatorDAO() {
		return investigatorDAO;
	}
	public void setInvestigatorDAO(InvestigatorDAO typeDAO) {
		investigatorDAO = typeDAO;
	}
	

	public SamplePrefix getSamplePrefix(Integer id){
		return samplePrefixDAO.getSamplePrefix(id);
	}
	
	public void saveSamplePrefix(SamplePrefix o) throws Exception {
		samplePrefixDAO.saveSamplePrefix(o);
		refresh = true;
	}
	
	public SamplePrefix getSamplePrefixByDescription(String des){
		return samplePrefixDAO.getSamplePrefixByDescription(des);
	}

	public SamplePrefixDAO getSamplePrefixDAO() {
		return samplePrefixDAO;
	}

	public void setSamplePrefixDAO(SamplePrefixDAO samplePrefixDAO) {
		this.samplePrefixDAO = samplePrefixDAO;
	}
	

}