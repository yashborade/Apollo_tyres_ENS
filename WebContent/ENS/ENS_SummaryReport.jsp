 <%@page import="beans.ENS_SummaryBean"%>
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
<title>Excel File Report</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
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

<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<div class="container col-6">

<form action="ENS_SummaryServlet" method="post" name="formAddNode">
	
	<center>
		<h3 class="my-3 text-success">Summary Report</h3>
		<table class="table table-responsive table-hover table-striped" id="userTbl" border="1">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    			<th>Sr. no.</th>
        		<th>Date</th>
        		<th>Section</th>
        		<th>Node Number</th>
        		<th>Machine Number</th>
        		<th>Difference</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_SummaryBean list : genreport){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
  				<%
  				Date date = new Date();
  				SimpleDateFormat sdfo = new SimpleDateFormat("dd-MM-yyyy");
  				String a = sdfo.format(list.getDATE()).toString();
  				System.out.println("Dates = "+a);
  				%>

    				<td><%=list.getSRNO()%></td>
    				<td><%=a%></td>
    				<td><%=list.getSEC()%></td>
    				<td><%=list.getNOD_NAM() %></td>
    				<td><%=list.getMACH_NAM()%></td>
    				<td><%=list.getDIFF()%></td>
    				
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		
		<input type="button" class="my-3 btn btn-success btn-md" value="Download to Excel" onclick="exportExl()" value="Export">
		<input type="hidden" value="" name="action" id="action" />
		</center>
		
	</form>
</div>
<script type="text/javascript">
			function exportExl()
			{				
				$("#userTbl").table2excel({
				    exclude:".noExl",
				    name:"Worksheet Name",
				    filename:"Summary Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
</script>

</body>
</html>