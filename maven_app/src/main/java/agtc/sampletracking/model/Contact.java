package agtc.sampletracking.model;

import java.io.Serializable;


/** 
 *        @hibernate.class
 *         table="INVESTIGATOR"
 *     
*/
public class Contact implements Serializable {

   
    /** nullable persistent field */
    private String address;

    /** nullable persistent field */
    private String city;

    /** nullable persistent field */
    private String provice;

    /** nullable persistent field */
    private String phone;

    /** nullable persistent field */
    private String fax;

    /** nullable persistent field */
    private String postalCode;

    /** nullable persistent field */
    private String email;

    /** full constructor */
    public Contact(String address, String city, String provice, String phone, String fax, String postalCode, String email) {
        this.address = address;
        this.city = city;
        this.provice = provice;
        this.phone = phone;
        this.fax = fax;
        this.postalCode = postalCode;
        this.email = email;
    }

    /** default constructor */
    public Contact() {
    }

    /** 
     *            @hibernate.property
     *             column="ADDRESS"
     *             length="128"
     *         
     */
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /** 
     *            @hibernate.property
     *             column="CITY"
     *             length="32"
     *         
     */
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /** 
     *            @hibernate.property
     *             column="PROVICE"
     *             length="10"
     *         
     */
    public String getProvice() {
        return this.provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    /** 
     *            @hibernate.property
     *             column="PHONE"
     *             length="10"
     *         
     */
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** 
     *            @hibernate.property
     *             column="FAX"
     *             length="10"
     *         
     */
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    /** 
     *            @hibernate.property
     *             column="POSTAL_CODE"
     *             length="6"
     *         
     */
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** 
     *            @hibernate.property
     *             column="EMAIL"
     *             length="32"
     *         
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

}
