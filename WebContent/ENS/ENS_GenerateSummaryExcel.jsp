<%@page import="java.util.ArrayList"%>
<%@page import="beans.ENS_SummaryBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title>Summary Report</title>
<meta http-equiv="Content-Type"	content="<%response.setHeader("Content-Disposition","attachment;filename=summary_report.xls");%>">
</head>
<body>
<%
				ArrayList<ENS_SummaryBean> genreport = null;
				if(request.getAttribute("Reportuploadno") != null)
				{
					genreport = (ArrayList<ENS_SummaryBean>) request.getAttribute("Reportuploadno");
					//System.out.println(genReports.size());
				}
				else
				{
					genreport = new ArrayList<ENS_SummaryBean>();
				}
			%>
			
			<div class="container">
	<h3 class="my-3 text-success">Summary Report</h3>
	<center>
		<table class="table-bordered table-responsive table-hover">
		<thead>
    		<tr>
        		<th>Sr. no.</th>
        		<th>Date</th>
        		<th>Section</th>
        		<th>Node Name</th>
        		<th>Machine Name</th>
        		<th>Difference</th>
        		
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_SummaryBean list : genreport){ %>
  				<tr>
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getDATE() %></td>
    				<td><%=list.getSEC()%></td>
    				<td><%=list.getNOD_NAM()%></td>
    				<td><%=list.getMACH_NAM() %></td>
    				<td><%=list.getDIFF() %></td>
    				
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		</center>
</div>

</body>
</html>