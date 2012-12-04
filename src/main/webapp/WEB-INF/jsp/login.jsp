<%@ page import="net.sf.acegisecurity.context.Context" %>
<%@ page import="net.sf.acegisecurity.context.ContextHolder" %>
<%@ page import="net.sf.acegisecurity.context.security.SecureContext" %>
<%@ page import="net.sf.acegisecurity.Authentication" %>
<%@ page import="net.sf.acegisecurity.GrantedAuthority" %>

<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


 <tr>
 <td>
<% Context context = ContextHolder.getContext();
SecureContext sc = (SecureContext) context;
Authentication auth = sc.getAuthentication();
agtc.sampletracking.model.User user = 
	(agtc.sampletracking.model.User)auth.getPrincipal();
%>
<h2><b>Welcome <%=user.getLoginname()%> </b></h2>

<br>
<br>

<div style="width: 800px">
<h2>Update news:</h2>
<p>2012-12-05: User Interface Update
<ul>
 <li> New <b>Barcode</b> functionality. Scan <i>samples</i> to register, store, or remove them. 
 <li> New <b>Search Sample</b> interface. Replaces:
 <ul>
 	<li><i>Search Project Sample; Simple Search; Search Samples</i>: Combined into one search interface</li>
 	<li><i>Update Sample</i>: Search for the sample, and then edit.</li>
 	<li><i>Print Sample Labels</i>: Search for the sample, and then print.</li>
 </ul>
 <li> New <b>Register Sample</b> interface. </li>
</ul>
<p>2009-06-05: New search function finished. Now you can get samples by project.
<p>2006-09-07: New label format available. The previous format will not be
supported any more.
<p>2005-10-14: Web security is implemented. Please go to 
'User Management'/'Edit Profile' to change password and 
 other information about yourself. After changed your password, please close your
  browser, and login again ( with new password ).
<P>2005-09-30: Sample Type added a new attribute "Accept Source",
When the sample type's of this attribute is "on", it will become one of 
sample types when you register samples.
<P>2005-09-16: New Samples search function is developed
to instead of the old one; courrently, you can input internal
 sample Ids that separate by ",", ";", space, TAB, enter key 
or another line.
<p>2005-09-09: All the internal sample Id you typed in will be
converted to uppercase.
<p><b>Database backup time is 11PM; it will take about 2 hours. 
When database is backuping, this application will stop.</b>
</div>
</td>
</tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
