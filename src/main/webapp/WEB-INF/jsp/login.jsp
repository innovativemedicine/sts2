<%@ page import="net.sf.acegisecurity.context.Context" %>
<%@ page import="net.sf.acegisecurity.context.ContextHolder" %>
<%@ page import="net.sf.acegisecurity.context.security.SecureContext" %>
<%@ page import="net.sf.acegisecurity.Authentication" %>
<%@ page import="net.sf.acegisecurity.GrantedAuthority" %>

<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


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
 <li> New barcode label format for Samples, Plates, and Containers.
 <li> New <b>Barcode</b> functionality. Scan <i>samples</i> to register, store, or remove them. 
 <li> New <b>Search Sample</b> interface. Replaces:
 <ul>
 	<li><i>Search Project Sample; Simple Search; Search Samples</i>: Combined into one search interface</li>
 	<li><i>Update Sample</i>: Search for the sample, and then edit.</li>
 	<li><i>Print Labels</i>: Search for the sample, and then print.</li>
 </ul>
 <li> New <b>Register Sample</b> interface. </li>
</ul>
<P>2005-09-30: Sample Type added a new attribute "Accept Source",
When the sample type's of this attribute is "on", it will become one of 
sample types when you register samples.
<p>2005-09-09: All the internal sample Id you typed in will be
converted to uppercase.
<p><b>Database backup time is 11PM; it will take about 2 hours. 
During database backup, this application will stop.</b>
</div>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
