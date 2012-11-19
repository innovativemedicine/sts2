package agtc.sampletracking.model;


import java.util.Comparator;
import java.util.Set;
import java.io.Serializable;



/** 
 *        @hibernate.class
 *         table="PRIMER"
 *     
*/
public class Reagent implements Serializable,Comparator {

    /** identifier field */
    private Integer ReagentId;

    /** persistent field */
    private String name;

    /** nullable persistent field */
    private Float concentration;

    /** nullable persistent field */
    private String manufacture;
    
    private String note;
    
    private String seq;
    
    private Integer amount;
    
    /** persistent field */
    private Container container;
    
    /** default constructor */
    public Reagent() {
    }

    public int compare(Object o1, Object o2) {
		Reagent s1 = (Reagent)o1;
		Reagent s2 = (Reagent)o2;
		String name1 = s1.getName();
		String name2 = s2.getName();
		return name1.compareTo(name2);
	}

	/**
	 * @return Returns the amount.
	 */
	public Integer getAmount() {
		return amount;
	}
	
	
	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * @return Returns the seq.
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * @param seq The seq to set.
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
   

  

    
    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="long"
     *             column="Reagent_ID"
     *         
     */
    public Integer getReagentId() {
        return this.ReagentId;
    }

    public void setReagentId(Integer ReagentId) {
        this.ReagentId = ReagentId;
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
     *             column="MANUFACTURE"
     *             length="64"
     *         
     */
    public String getManufacture() {
        return this.manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }


   
  
	/**
	 * @return
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param string
	 */
	public void setNote(String string) {
		note = string;
	}

	/**
	 * @return
	 */
	public Float getConcentration() {
		return concentration;
	}

	/**
	 * @param float1
	 */
	public void setConcentration(Float float1) {
		concentration = float1;
	}

	/**
	 * @return
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * @param container
	 */
	public void setContainer(Container container) {
		this.container = container;
	}

}
