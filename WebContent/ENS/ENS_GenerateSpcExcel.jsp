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
<title>SPC Excel</title>
<meta http-equiv="Content-Type"	content="<%response.setHeader("Content-Disposition","attachment;filename=spc.xls");%>">
</head>
<body>
<%
				ArrayList<ENS_AddSpcBean> genReports = null;
				if(request.getAttribute("Reportspc") != null)
				{
					genReports = (ArrayList<ENS_AddSpcBean>) request.getAttribute("Reportspc");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_AddSpcBean>();
				}
			%>
<div class="container">
	<h3 class="my-3 text-success">SPC Report</h3>
	<center>
		<table class="table-bordered table-responsive table-hover">
		<thead>
    		<tr>
        		<th>Sr. no.</th>
        		<th>Date</th>
        		<th>Equipment Name</th>
        		<th>Total Readings</th>
        		<th>SPC in Tonnage</th>
        		<th>Total SPC</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AddSpcBean list : genReports){ %>
  				<tr>
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getSPC_DATE()%></td>
    				<td><%=list.getEQUIP_NAM()%></td>
    				<td><%=list.getTOTAL_READINGS() %></td>
    				<td><%=list.getSPC()%></td>
    				<td><%=list.getTOTAL_SPC()%></td>
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		</center>
</div>
</body>
</html>