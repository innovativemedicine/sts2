package agtc.sampletracking.model;

import java.io.Serializable;


/** 
 *        @hibernate.class
 *         table="INVESTIGATOR"
 *     
*/
public class Name implements Serializable {


    /** persistent field */
    private String fname;

    /** nullable persistent field */
    private String mname;

    /** persistent field */
    private String lname;

    /** full constructor */
    public Name(String fname, String mname, String lname) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
       
    }

    /** default constructor */
    public Name() {
    }

    /** 
     *            @hibernate.property
     *             column="FNAME"
     *             length="32"
     *             not-null="true"
     *         
     */
    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    /** 
     *            @hibernate.property
     *             column="MNAME"
     *             length="32"
     *         
     */
    public String getMname() {
        return this.mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    /** 
     *            @hibernate.property
     *             column="LNAME"
     *             length="32"
     *             not-null="true"
     *         
     */
    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

  
}
