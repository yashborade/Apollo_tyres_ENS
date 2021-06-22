<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.ENS_AddNodBean"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title>Node Excel</title>
<meta http-equiv="Content-Type"	content="<%response.setHeader("Content-Disposition","attachment;filename=node_report.xls");%>">
</head>
<body>
<%
				ArrayList<ENS_AddNodBean> genReports = null;
				if(request.getAttribute("Reportnodeno") != null)
				{
					genReports = (ArrayList<ENS_AddNodBean>) request.getAttribute("Reportnodeno");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_AddNodBean>();
				}
			%>
<div class="container">
	<h3 class="my-3 text-success">Node Report</h3>
	<center>
		<table class="table-bordered table-responsive table-hover">
		<thead>
    		<tr>
        		<th>Sr. no.</th>
        		<th>Section</th>
        		<th>Node Number</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AddNodBean list : genReports){ %>
  				<tr>
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getSEC()%></td>
    				<td><%=list.getNOD_NAM()%></td>
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		</center>
</div>
</body>
</html>