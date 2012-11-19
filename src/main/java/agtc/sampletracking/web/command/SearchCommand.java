/*
 * Created on Oct 21, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.command;
import java.util.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SearchCommand {
	private Object searchItem;
	private String searchField;
	private String operator;
	private List searchItems;
	
	public SearchCommand(){
		
	}
	
	public SearchCommand(String sf,String op,Object si){
		searchItem = si;
		searchField = sf;
		operator = op;
		System.out.println("searchItem="+si+";searchField="+sf+";operator="+op);
	}
	public String toString(){
		if(("in").equals(operator)){
			String results = searchField + " " + operator + "{ ";
			if(searchItems!=null && searchItems.size()>0){
				Iterator i = searchItems.iterator();
				while (i.hasNext()){
					Object o = i.next();
					// o is either Integer or String
					results += o + ", ";
				}
			}
			results += "}";
			return results;
		}else{
			return searchField + " " + operator + " " + searchItem ;
		}
	}
	
	/**
	 * @return
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @return
	 */
	public String getSearchField() {
		return searchField;
	}

	/**
	 * @return
	 */
	public Object getSearchItem() {
		return searchItem;
	}

	/**
	 * @param string
	 */
	public void setOperator(String string) {
		operator = string;
	}

	/**
	 * @param string
	 */
	public void setSearchField(String string) {
		searchField = string;
	}

	/**
	 * @param string
	 */
	public void setSearchItem(Object string) {
		searchItem = string;
	}
	

	
	
	/**
	 * @return Returns the searchItems.
	 */
	public List getSearchItems() {
		return searchItems;
	}
	/**
	 * @param searchItems The searchItems to set.
	 */
	public void setSearchItems(List searchItems) {
		this.searchItems = searchItems;
	}
}
