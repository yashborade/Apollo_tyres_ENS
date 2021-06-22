<%@page import="beans.ENS_AddGroupBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title>Group Excel</title>
<meta http-equiv="Content-Type"	content="<%response.setHeader("Content-Disposition","attachment;filename=Group_report.xls");%>">
</head>
<body>
<%
				ArrayList<ENS_AddGroupBean> genReports = null;
				if(request.getAttribute("Reportgroupno") != null)
				{
					genReports = (ArrayList<ENS_AddGroupBean>) request.getAttribute("Reportgroupno");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_AddGroupBean>();
				}
			%>
<div class="container">
	<h3 class="my-3 text-success">Group Report</h3>
	<center>
		<table class="table-bordered table-responsive table-hover">
		<thead>
    		<tr>
        		<th>Sr. no.</th>
        		<th>Section</th>
        		<th>Equipment Name</th>
        		<th>Fidder to be Added</th>
        		<th>Fidder to be Subtracted</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AddGroupBean list : genReports){ %>
  				<tr>
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getSEC()%></td>
    				<td><%=list.getEQUIP_NAM()%></td>
    				<td><%=list.getFIDD_NAM_PLUS()%></td>
    				<td><%=list.getFIDD_NAM_MINUS()%></td>
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		</center>
</div>
</body>
</html>