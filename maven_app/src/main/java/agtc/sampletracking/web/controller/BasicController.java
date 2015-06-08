/*
 * Created on Oct 18, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;

import agtc.sampletracking.ConstantInterface;
import agtc.sampletracking.dao.AssayDAO;
import agtc.sampletracking.dao.ContainerDAO;
import agtc.sampletracking.dao.ContainerTypeDAO;
import agtc.sampletracking.dao.InstrumentDAO;
import agtc.sampletracking.dao.InvestigatorDAO;
import agtc.sampletracking.dao.LocationDAO;
import agtc.sampletracking.dao.ProjectDAO;
import agtc.sampletracking.dao.SampleTypeDAO;
import agtc.sampletracking.model.Assay;
import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.ContainerType;
import agtc.sampletracking.model.Instrument;
import agtc.sampletracking.model.Investigator;
import agtc.sampletracking.model.Location;
import agtc.sampletracking.model.Project;
import agtc.sampletracking.model.SampleType;
import agtc.sampletracking.web.editor.AssayEditor;
import agtc.sampletracking.web.editor.ContainerEditor;
import agtc.sampletracking.web.editor.ContainerTypeEditor;
import agtc.sampletracking.web.editor.InstrumentEditor;
import agtc.sampletracking.web.editor.InvestigatorEditor;
import agtc.sampletracking.web.editor.LocationEditor;
import agtc.sampletracking.web.editor.ProjectEditor;
import agtc.sampletracking.web.editor.SampleTypeEditor;



/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class BasicController extends SimpleFormController implements ConstantInterface {
	private Log log = LogFactory.getLog(BasicController.class);
	
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat,true));
		binder.registerCustomEditor(Float.class, null, new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, true));
		WebApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		ContainerTypeDAO containerTypeDAO = (ContainerTypeDAO) ctx.getBean("containerTypeDAO");
		LocationDAO locationDAO = (LocationDAO) ctx.getBean("locationDAO");
		ProjectDAO projectDAO = (ProjectDAO) ctx.getBean("projectDAO");
		InstrumentDAO instrumentDAO = (InstrumentDAO)ctx.getBean("instrumentDAO");
		ContainerDAO containerDAO = (ContainerDAO)ctx.getBean("containerDAO");
		AssayDAO assayDAO = (AssayDAO)ctx.getBean("assayDAO");
		InvestigatorDAO investigatorDAO = (InvestigatorDAO)ctx.getBean("investigatorDAO");
		SampleTypeDAO sampleTypeDAO = (SampleTypeDAO)ctx.getBean("sampleTypeDAO");
		
		InvestigatorEditor investigatorEditor = new InvestigatorEditor();
		investigatorEditor.setInvestigatorDAO(investigatorDAO);
		
		ContainerTypeEditor containerTypeEditor = new ContainerTypeEditor();
		containerTypeEditor.setContainerTypeDAO(containerTypeDAO);
		
		LocationEditor locationEditor = new LocationEditor();
		locationEditor.setLocationDAO(locationDAO);
		
		ProjectEditor projectEditor = new ProjectEditor();
		projectEditor.setProjectDAO(projectDAO);
		
		InstrumentEditor instrumentEditor = new InstrumentEditor();
		instrumentEditor.setInstrumentDAO(instrumentDAO);
		
		ContainerEditor containerEditor = new ContainerEditor();
		containerEditor.setContainerDAO(containerDAO);
		
		AssayEditor assayEditor = new AssayEditor();
		assayEditor.setAssayDAO(assayDAO);
		
		SampleTypeEditor sampleTypeEditor = new SampleTypeEditor();
		sampleTypeEditor.setSampleTypeDAO(sampleTypeDAO);

		binder.registerCustomEditor(Investigator.class, null, investigatorEditor);
		binder.registerCustomEditor(Location.class, null,locationEditor );
		binder.registerCustomEditor(Project.class, null, projectEditor);
		binder.registerCustomEditor(ContainerType.class, null, containerTypeEditor);
		binder.registerCustomEditor(Instrument.class, null, instrumentEditor);
		binder.registerCustomEditor(Container.class, null, containerEditor);
		binder.registerCustomEditor(Assay.class, null, assayEditor);
		binder.registerCustomEditor(SampleType.class, null, sampleTypeEditor);
		binder.setMessageCodesResolver(new DefaultMessageCodesResolver());
	}	

	
}
