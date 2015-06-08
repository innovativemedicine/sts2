package agtc.sampletracking.model;


import java.util.Set;
import java.io.Serializable;



/** 
 *        @hibernate.class
 *         table="INSTRUMENT"
 *     
*/
public class Instrument implements Serializable {

    /** identifier field */
    private Integer instrumentId;

    /** persistent field */
    private String name;

    /** nullable persistent field */
    private String note;

    /** persistent field */
    private Set tests;

  
    /** default constructor */
    public Instrument() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="long"
     *             column="INSTRUMENT_ID"
     *         
     */
    public Integer getInstrumentId() {
        return this.instrumentId;
    }

    public void setInstrumentId(Integer instrumentId) {
        this.instrumentId = instrumentId;
    }

    /** 
     *            @hibernate.property
     *             column="NAME"
     *             length="64"
     *             not-null="true"
     *         
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** 
     *            @hibernate.property
     *             column="NOTE"
     *             length="128"
     *         
     */
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     * 	       @hibernate.collection-key
     * 	        column="INSTRUMENT_ID"
     *            @hibernate.collection-one-to-many
     *             class="agtc.sampletracking.model.Test"
     *         
     */
    public Set getTests() {
        return this.tests;
    }

    public void setTests(Set tests) {
        this.tests = tests;
    }
 
}
