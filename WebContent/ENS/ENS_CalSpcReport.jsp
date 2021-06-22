<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="beans.*"%>  
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Total Spc Group Report</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
<script type="text/javascript">
		function submitValues(element)
		{	
				element.value=="Download to Excel"
				alert("excel"+element.value);
				document.formAddNode.action.value="Download To Excel";
				alert("action"+document.getElementById("action").value);
				//document.fupForm.action = "ENS_UploadFileServlet"; 
				document.formAddNode.submit();		
			
		}
</script>

<%
				ArrayList<ENS_CalSpcGroupBean> genreport = null;
				if(request.getAttribute("Reportspc") != null)
				{
					genreport = (ArrayList<ENS_CalSpcGroupBean>) request.getAttribute("Reportspc");
					//System.out.println(genReports.size());
				}
				else
				{
					genreport = new ArrayList<ENS_CalSpcGroupBean>();
				}
			%>

<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<div class="container">

<form action="ENS_CalSpcGroupServlet" method="post" name="formAddNode">
<%-- 
	<input type="hidden" name="DPR_slctPlant" id="DPR_slctPlant" value="<%= request.getParameter("DPR_slctPlant")%>"/>
	<input type="hidden" name="FromDt" id="FromDt" value="<%= request.getParameter("FromDt")%>"/>
	<input type="hidden" name="ToDt" id="ToDt" value="<%= request.getParameter("ToDt")%>"/> --%>
	
	<center>
		<h3 class="my-3 text-success">SPC Report</h3>
		<table class="table table-responsive table-hover table-striped" border="1">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    			<th>Sr. no.</th>
        		<th>Date</th>
        		<th>Area Name</th>
        		<th>SPC in Tonnage</th>
        		<th>Total SPC</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_CalSpcGroupBean list : genreport){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
  				<%-- <%String p = request.getParameter("date1");
  				SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd"); 
  				Date d2 = sdfo.parse(p); %> --%>
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getSPC_DATE()%></td>
    				<td><%=list.getAREA_NAME()%></td>
    				<td><%=list.getTOTAL_TON()%></td>
    				<td><%=list.getTOTAL_SPC()%></td>
    				
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		
		<input type="button" class="my-3 btn btn-success btn-md" name="Excel" value="Download to Excel" id="Excel" onclick="submitValues(this)"/>
		<input type="hidden" value="" name="action" id="action" />
		</center>
		
	</form>
</div>

</body>
</html>