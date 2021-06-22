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
<title>Area Excel</title>
<meta http-equiv="Content-Type"	content="<%response.setHeader("Content-Disposition","attachment;filename=Area_report.xls");%>">
</head>
<body>
<%
				ArrayList<ENS_AddAreaBean> genReports = null;
				if(request.getAttribute("Reportarea") != null)
				{
					genReports = (ArrayList<ENS_AddAreaBean>) request.getAttribute("Reportarea");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_AddAreaBean>();
				}
			%>
<div class="container">
	<h3 class="my-3 text-success">Area Report</h3>
	<center>
		<table class="table-bordered table-responsive table-hover">
		<thead>
    		<tr>
        		<th>Sr. no.</th>
        		<th>Section</th>
        		<th>Area Name</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AddAreaBean list : genReports){ %>
  				<tr>
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getSEC()%></td>
    				<td><%=list.getAREA_NAME()%></td>
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		</center>
</div>
</body>
</html>