<%@page import="beans.ENS_UploadBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title>Upload Excel</title>
<meta http-equiv="Content-Type"	content="<%response.setHeader("Content-Disposition","attachment;filename=upload_report.xls");%>">
</head>
<body>
<%
				ArrayList<ENS_UploadBean> genReports = null;
				if(request.getAttribute("Reportuploadno") != null)
				{
					genReports = (ArrayList<ENS_UploadBean>) request.getAttribute("Reportuploadno");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_UploadBean>();
				}
			%>
<div class="container">
	<h3 class="my-3 text-success">Upload Report</h3>
	<center>
		<table class="table-bordered table-responsive table-hover">
		<thead>
    		<tr>
        		<th>Sr. no.</th>
        		<th>Date</th>
        		<th>Section</th>
        		<th>Node Name</th>
        		<th>Machine Name</th>
        		<th>Readings</th>
        		
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_UploadBean list : genReports){ %>
        		<%
  				Date date = new Date();
  				SimpleDateFormat sdfo = new SimpleDateFormat("dd-MM-yyyy");
  				String a = sdfo.format(list.getDATE()).toString();
  				System.out.println("Dates = "+a);
  				%>
  				<tr>
    				<td><%=list.getSRNO()%></td>
    				<td><%=a%></td>
    				<td><%=list.getSEC()%></td>
    				<td><%=list.getNOD_NAM()%></td>
    				<td><%=list.getMACH_NAM() %></td>
    				<td><%=list.getREADINGS() %></td>
    				
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		</center>
</div>

</body>
</html>