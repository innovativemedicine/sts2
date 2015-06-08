/*
 * Created on Oct 21, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.searchFields;
import java.util.*;

import org.hibernate.criterion.*;

import org.hibernate.*;
import agtc.sampletracking.web.command.*;

/**
 * @author Gloria Deng
 * 
 * Modified by Jianan Xiao 2005-09-07
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BasicSearchFiels {
	protected List list;

	public BasicSearchFiels(){
		list = new LinkedList();
		/*Modified by Jianan Xiao 2005-09-07*/
//		list.add("select to search");
	}
	public List getList(){
		return list;
	}
		
	public void add(Object o){
		list.add(o);
	}
	
	protected static void addOperator(String fieldExp,SearchCommand searchCommand,Criteria crt){
		String operator = searchCommand.getOperator();
		if(operator.equals("contains")){
			crt.add(Restrictions.ilike(fieldExp,"%"+searchCommand.getSearchItem()+"%"));
		}else if(operator.equals("is")){
			crt.add(Restrictions.eq(fieldExp,searchCommand.getSearchItem()));
		}
		else if(operator.equals("in")){
			List c = searchCommand.getSearchItems();
			int size = c.size();
			if(size>1000){
				Criterion one = Restrictions.in(fieldExp,c.subList(0,1000));
				int i = 0;
				for(i=1;i<=(size/1000)-1;i++){
					one = Restrictions.or(one,Restrictions.in(fieldExp,c.subList(i*1000,(i+1)*1000)));
				}
				one = Restrictions.or(one,Restrictions.in(fieldExp,c.subList(i*1000,size)));
			}else{
				crt.add(Restrictions.in(fieldExp,searchCommand.getSearchItems()));
			}
		}else if(operator.equals("between")){
			String s = (String)searchCommand.getSearchItem();
			String[] cs = s.split("AND");
			crt.add(Restrictions.between(fieldExp,cs[0].trim(),cs[1].trim()));
		}else if(operator.equals("after")){
			crt.add(Restrictions.gt(fieldExp,searchCommand.getSearchItem()));
		}
	}
	
	/**subclass should implement this method to load all elements in key/value pairs
		key is the web presentation of the field name, value is the search field in HSL
	*/
	public void loadElements(){
	}
	

	
}
