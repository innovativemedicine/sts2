/*
 * Created on Oct 18, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;

import agtc.sampletracking.ConstantInterface;
import agtc.sampletracking.model.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.web.editor.AssayEditor;
import agtc.sampletracking.web.editor.ContainerEditor;
import agtc.sampletracking.web.editor.ContainerTypeEditor;
import agtc.sampletracking.web.editor.InstrumentEditor;
import agtc.sampletracking.web.editor.InvestigatorEditor;
import agtc.sampletracking.web.editor.LocationEditor;
import agtc.sampletracking.web.editor.ProjectEditor;
import agtc.sampletracking.web.editor.SampleTypeEditor;
import agtc.sampletracking.web.*;
import agtc.sampletracking.bus.manager.*;
import org.springframework.validation.BindException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import java.text.*;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.DefaultMessageCodesResolver;
import java.beans.*;
import org.springframework.web.context.support.*;
import org.springframework.web.context.*;



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
