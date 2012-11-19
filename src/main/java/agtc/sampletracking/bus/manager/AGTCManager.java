package agtc.sampletracking.bus.manager;

import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;

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
	private List sampleTypesWithVials;
	private List sampleTypesWOVials;
	private List locations;
	private List containerTypes;
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
	/**
	 * @return Returns the containerTypes.
	 */
	public List getContainerTypes() {
		if(containerTypes==null || refresh == true){
			containerTypes = containerTypeDAO.getContainerTypes();
			refresh = false;
		}
		return containerTypes;
	}
	/**
	 * @return Returns the instruments.
	 */
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
	/**
	 * @return Returns the sampleTypes.
	 */
	public List getSampleTypesWithVials() {
		if(sampleTypesWithVials==null || refresh == true){
			sampleTypesWithVials = sampleTypeDAO.getSampleTypesWithVials();
			refresh = false;
		}
		return sampleTypesWithVials;
	}
	
	/**
	 * @return Returns the sampleTypes.
	 */
	public List getSampleTypesWOVials() {
		if(sampleTypesWOVials==null || refresh == true){
			sampleTypesWOVials = sampleTypeDAO.getSampleTypesWOVials();
			refresh = false;
		}
		return sampleTypesWOVials;
	}
	
	/**
	 * @return Returns the sampleTypes.
	 */
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
	
	/**
	public SampleType getSampleType(String name){
		return sampleTypeDAO.getSampleType(name);
	}
	*/
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
	
	/**
	 * @return
	 */
	public SampleTypeDAO getSampleTypeDAO() {
		return sampleTypeDAO;
	}


	/**
	 * @param typeDAO
	 */
	public void setSampleTypeDAO(SampleTypeDAO typeDAO) {
		sampleTypeDAO = typeDAO;
	}
/*End Sample Type*/

	/*********For Location
	 * @return Returns the locationDAO.
	 */
	public LocationDAO getLocationDAO() {
		return locationDAO;
	}
	/**
	 * @param locationDAO The locationDAO to set.
	 */
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
	/******End Location	 */
	
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
	
	/**
	 * @return
	 */
	public ContainerTypeDAO getContainerTypeDAO() {
		return containerTypeDAO;
	}

	/**
	 * @param typeDAO
	 */
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
	
	/**
	 * @return
	 */
	public InstrumentDAO getInstrumentDAO() {
		return instrumentDAO;
	}


	/**
	 * @param typeDAO
	 */
	public void setInstrumentDAO(InstrumentDAO typeDAO) {
		instrumentDAO = typeDAO;
	}

	/**
	 * For Investigator
	 */
	
	public Investigator getInvestigator(Integer InvestigatorID){
		return investigatorDAO.getInvestigator(InvestigatorID);
	}

	public void saveInvestigator(Investigator stock) throws Exception {
		investigatorDAO.saveInvestigator(stock);
		refresh = true;
	}
	
	/**
	 * @return
	 */
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

	/**
	 * @return Returns the samplePrefixDAO.
	 */
	public SamplePrefixDAO getSamplePrefixDAO() {
		return samplePrefixDAO;
	}
	/**
	 * @param samplePrefixDAO The samplePrefixDAO to set.
	 */
	public void setSamplePrefixDAO(SamplePrefixDAO samplePrefixDAO) {
		this.samplePrefixDAO = samplePrefixDAO;
	}
	

}