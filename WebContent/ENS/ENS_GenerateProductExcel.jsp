<%@page import="beans.ENS_AddEquipBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title>Product Excel</title>
<meta http-equiv="Content-Type"	content="<%response.setHeader("Content-Disposition","attachment;filename=product_report.xls");%>">
</head>
<body>
<%
				ArrayList<ENS_AddProductBean> genReports = null;
				if(request.getAttribute("Reportproduct") != null)
				{
					genReports = (ArrayList<ENS_AddProductBean>) request.getAttribute("Reportproduct");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_AddProductBean>();
				}
			%>
<div class="container">
	<h3 class="my-3 text-success">Product Report</h3>
	<center>
		<table class="table-bordered table-responsive table-hover">
		<thead>
    		<tr>
        		<th>Sr. no.</th>
        		<th>Date</th>
        		<th>Equipment Name</th>
        		<th>Total Readings</th>
        		<th>Bias Batches</th>
        		<th>Radial Batches</th>
        		<th>Total Batches</th>
        		<th>Bias Consumption</th>
        		<th>Radial Consumption</th>
        		<th>Total Consumption</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AddProductBean list : genReports){ %>
  				<tr>
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getBTCH_DATE()%></td>
    				<td><%=list.getEQUIP_NAM()%></td>
    				<td><%=list.getEQUIP_TOTAL()%></td>
    				<td><%=list.getBIAS_BTCH()%></td>
    				<td><%=list.getRADIAL_BTCH()%></td>
    				<td><%=list.getTOT_BTCH()%></td>
    				<td><%=list.getBIAS_CONSUP()%></td>
    				<td><%=list.getRADIAL_CONSUP()%></td>
    				<td><%=list.getTOTAL()%></td>
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		</center>
</div>
</body>
</html>