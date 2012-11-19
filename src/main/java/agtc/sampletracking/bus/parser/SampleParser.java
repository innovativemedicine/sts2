/*
 * Created on Jan 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.bus.parser;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.ContainerType;
import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SamplePrefix;


public abstract class SampleParser extends TextFileHanlder {

	protected SamplePrefix samplePrefix;
	protected String isControl;
	protected String[] fieldTitles;
	protected DateFormat df;
	protected DateFormat df1;
	protected DateFormat df2;
	protected DateFormat df3;
	private Log log = LogFactory.getLog(SampleParser.class);
	
	public SampleParser(InputStream is,SamplePrefix samplePrefix,String isControl){
		this(is,samplePrefix);
		this.isControl = isControl;
	}
	
	public SampleParser(InputStream is,SamplePrefix samplePrefix){
		super(is);
		df = new SimpleDateFormat("dd/MM/yyyy");
		df1 = new SimpleDateFormat("MMM.dd,yyyy");
		df2 = new SimpleDateFormat("MMM.d,yyyy");
		df3 = new SimpleDateFormat("yyyy/MM/dd");
		this.samplePrefix = samplePrefix;
		if(samplePrefix.getColumns()!=null){
			fieldTitles = samplePrefix.getColumns().split(",");
		}

	}
	
	/**
	 * subclass must overwrite this method to parse samples 
	 * @return the parsing result, "" if it is OK
	 */
	public abstract String parseSample() throws Exception;
	
	protected Sample parseOneLine(String[] oneSampleInfo,StringBuffer sb,int lineNumber){
		
		Sample sample = new Sample();
 		Patient patient = new Patient();
 		Integer sampleDupNo = new Integer(1);
 	
 		
 		for(int a= 0;a<oneSampleInfo.length;a++){
 			String oneSampleField = oneSampleInfo[a].trim();
 			
 			oneSampleField.replaceAll("\"","");
 			
 			if(fieldTitles[a].compareTo("Internal ID") == 0){
 				String intSampleId = "";
 				if(samplePrefix.getDigitNumber()==null || samplePrefix.getDigitNumber().intValue() == -1){
 					oneSampleField = oneSampleField.replaceAll(" ","");
 					String prefix = samplePrefix.getPrefix();
 					if( prefix== null || prefix.equals("null")){
 						prefix = "";
 					}
 					intSampleId = prefix + oneSampleField;
 					Pattern pattern = Pattern.compile("[A-Za-z]{1,4}[0-9]{1,6}");
 					Matcher matcher = pattern.matcher(intSampleId);
 					if(matcher.matches()){
 						patient.setIntSampleId(intSampleId);
 					}else{
 						sb.append("in the " + lineNumber + " the internal sample id is in wrong format <br>");
 					}
 				}else{
	 				if(oneSampleField.length()>samplePrefix.getDigitNumber().intValue()){
	 					sb.append("in the " + lineNumber + " the internal sample id has more difits <br>");
	 				}else if("*".equals(oneSampleField) && !"".equals(oneSampleField)){
	 					sb.append("in the " + lineNumber + " the internal sample id is empty <br>");
	 				}else{
						for (int i=0;i<(samplePrefix.getDigitNumber().intValue()-oneSampleField.length());i++){
							intSampleId += "0";
						}
						intSampleId = samplePrefix.getPrefix()+intSampleId+oneSampleField;
						patient.setIntSampleId(intSampleId);
	 				}
 				}
 				
 			}else if(fieldTitles[a].compareTo("External ID")== 0){
 				patient.setExtSampleId(oneSampleField);
 			}else if(fieldTitles[a].compareTo("Patient First Name")== 0){
 				patient.setFname(oneSampleField);
 			}else if(fieldTitles[a].compareTo("Patient Middle Name")== 0){
 				patient.setMname(oneSampleField);
 			}else if(fieldTitles[a].compareTo("Patient Last Name")== 0){
 				patient.setLname(oneSampleField);
 			}else if(fieldTitles[a].compareTo("Patient Gender")== 0){
 				if(oneSampleField!= null && (oneSampleField.startsWith("F") || oneSampleField.startsWith("f"))){
 					patient.setGender("F");
 				}else if(oneSampleField!= null && (oneSampleField.startsWith("M") ||oneSampleField.startsWith("m"))){
 					patient.setGender("M");	
 				}else if(oneSampleField.startsWith("1") ||oneSampleField.startsWith("2") ){
 					if(oneSampleField.startsWith("1")){
 						patient.setGender("M");
 					}else{
 						patient.setGender("F");	
 					}
 				}
 			}else if(fieldTitles[a].compareTo("Patient Age")== 0){
 				if(!oneSampleField.equals("")){		
	 				try{
			 			Integer age = new Integer(oneSampleField);
			 			patient.setAge(age);
						
			 		}catch(Exception e){
			 			sb.append("in the " +lineNumber + " line the age format is not correct <br>");
			 		}
 				}
 			}else if(fieldTitles[a].compareTo("Patient Birth Date")== 0){
 				if(!oneSampleField.equals("")){		
	 				Date date = parseDate(oneSampleField);
	 				if(date != null){
	 					patient.setBirthDate(date);
	 				}else{
	 					sb.append("in the " + lineNumber + " line the Birth date format is not correct <br>");
	 				}
 				}
 			}else if(fieldTitles[a].compareTo("Sample Receive Date")== 0){
 				if(!oneSampleField.equals("")){		
	 				Date date = parseDate(oneSampleField);
	 				if(date != null){
	 					sample.setReceiveDate(date);
	 				}else{
	 					sb.append("in the " + lineNumber + " line the sample receive date format is not correct <br>");
	 				}
 				}
 			}else if(fieldTitles[a].compareTo("Sample Made Date")== 0){
 				if(!oneSampleField.equals("")){		
	 				Date date = parseDate(oneSampleField);
	 				if(date != null){
	 					sample.setMadeDate(date);
	 				}else{
	 					sb.append("in the " +lineNumber + " line the sample made date format is not correct <br>");
	 				}
 				}
 			}else if(fieldTitles[a].compareTo("Sample Concentration")== 0){
 				if(!oneSampleField.equals("")){		
 					oneSampleField = oneSampleField.replaceAll(",","");
 					try{
			 			Float concentration = new Float(oneSampleField);
			 			sample.setOd(concentration);
						
			 		}catch(Exception e){
			 			sb.append("in the " + lineNumber + " line the concentration format is not correct <br>");
			 		}
 				}
 			}else if(fieldTitles[a].compareTo("Sample Another External ID")== 0){
 				patient.setAnotherExtSampleId(oneSampleField);
 			}else if(fieldTitles[a].compareTo("Patient Note")== 0){
 				patient.setNote(oneSampleField);
 			}else if(fieldTitles[a].compareTo("Sample Transfer Date")== 0){
 				if(!oneSampleField.equals("")){		
	 				Date date = parseDate(oneSampleField);
	 				if(date != null){
	 					sample.setTransDate(date);
	 				}else{
	 					sb.append("in the " +lineNumber + " line the sample transfer date format is not correct <br>");
	 				}
 				}
 			}else if(fieldTitles[a].compareTo("Sample Concentration Reading Date")== 0){
 				if(!oneSampleField.equals("")){		
	 				Date date = parseDate(oneSampleField);
	 				if(date != null){
	 					sample.setOdDate(date);
	 				}else{
	 					sb.append("in the " +lineNumber + " line the sample concentration reading date format is not correct <br>");
	 				}
 				}
 			}else if(fieldTitles[a].compareTo("Sample Volumn")== 0){
 				if(!oneSampleField.equals("")){		
 					oneSampleField = oneSampleField.replaceAll(",","");
 					try{
			 			Float volumn = new Float(oneSampleField);
			 			sample.setVolumn(volumn);
						
			 		}catch(Exception e){
			 			sb.append("in the " + lineNumber + " line the volumn format is not correct <br>");
			 		}
 				}
 			}else if(fieldTitles[a].compareTo("Sample Volumn Reading Date")== 0){
 				if(!oneSampleField.equals("")){		
	 				Date date = parseDate(oneSampleField);
	 				if(date != null){
	 					sample.setVolumnDate(date);
	 				}else{
	 					sb.append("in the " +lineNumber + " line the sample volumn reading date format is not correct <br>");
	 				}
 				}
 			}else if(fieldTitles[a].compareTo("Family ID")== 0){
 				patient.setFamilyId(oneSampleField);
 			}else if(fieldTitles[a].compareTo("Sample Note")== 0){
 				sample.setNotes(oneSampleField);
 			}else if(fieldTitles[a].compareTo("Sample Status")== 0){
 				sample.setStatus(oneSampleField);
 			}else if(fieldTitles[a].compareTo("Sample Duplication No.")== 0){
 				
 				if(!oneSampleField.equals("")){		
	 				try{
			 			sampleDupNo = new Integer(oneSampleField);

						
			 		}catch(Exception e){
			 			sb.append("in the " +lineNumber + " line the Sample Duplicate No format is not correct <br>");
			 		}
 				}
 			}
 			
 		}
 		sample.setPatient(patient);
 		sample.setSampleDupNo(sampleDupNo);
		//patient.setSample(sample);
		return sample;

	}

	
	protected Date parseDate(String dateS){
		Date date = null;
		dateS = dateS.replaceAll(" ","");
		//Convert dd-MM-yyyy to dd/MM/yyyy
		dateS = dateS.replaceAll("-","/");	
		try{
			date = df.parse(dateS);
 		}catch(Exception e){
 			try{
 				date = df1.parse(dateS);
 			}catch(Exception ee){
 				try{
 	 				date = df2.parse(dateS);
 	 			}catch(Exception eee){
 	 				try {
 	 					date = df3.parse(dateS);
 	 				}catch(Exception e3){
 	 					
 	 				}
 	 			}
 			}
 		}
 		return date;
	}
	
	
}
