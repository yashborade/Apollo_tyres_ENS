<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.ENS_CalculateGroupBean"%>
<%@page import="java.util.ArrayList"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calculate Group Report</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<meta http-equiv="Content-Type"	content="<%response.setHeader("Content-Disposition","attachment;filename=Calgrp_report.xls");%>">
</head>
<%
				ArrayList<ENS_CalculateGroupBean> genReports = null;
				if(request.getAttribute("Reportgrpno") != null)
				{
					genReports = (ArrayList<ENS_CalculateGroupBean>) request.getAttribute("Reportgrpno");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_CalculateGroupBean>();
				}
			%>
<body>
<div class="container">
	<h3 class="my-3 text-success">Calculate Group Report</h3>
	<center>
		<table class="table-bordered table-responsive table-hover">
		<thead>
    		<tr>
        		<th>Sr. no.</th>
        		<th>Date</th>
        		<th>Equipment Name</th>
        		<th>Total</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_CalculateGroupBean list : genReports){ %>
  				<tr>
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getGRP_DATE() %></td>
    				<td><%=list.getEQUIP_NAM()%></td>
    				<td><%=list.getTOTAL() %></td>
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		</center>
</div>

</body>
</html>