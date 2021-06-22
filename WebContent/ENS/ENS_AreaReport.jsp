<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="beans.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Area Report</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
<%
				ArrayList<ENS_AreaTargetBean> genReports1 = null;
				
				if(request.getAttribute("months") != null)
				{
					genReports1 = (ArrayList<ENS_AreaTargetBean>) request.getAttribute("months");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports1 = new ArrayList<ENS_AreaTargetBean>();
				}
			%>
			<%
				ArrayList<ENS_CalAreaGroupBean> genReports = null;
				
				if(request.getAttribute("areareport") != null)
				{
					genReports = (ArrayList<ENS_CalAreaGroupBean>) request.getAttribute("areareport");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_CalAreaGroupBean>();
				}
			%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<form action="ENS_CalculateAreaGroupServlet" method="post" name="formAddNode">
<div class="container col-5">
	
	<center>
		<h3 class="my-3 text-success">Area Report</h3>
		<table class="table table-responsive table-hover table-striped" id="userTbl" border="1">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    		
        		<th>Area Name</th>
        		<th>Target Consumption</th>
        		<th>Total Area Consumption</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_CalAreaGroupBean list : genReports){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							
					<% DecimalFormat df = new DecimalFormat("#,###,##0.00");%>
    					<td><%=list.getAREA_NAM().substring(3) %></td>
    					
    					
    					<%for(ENS_AreaTargetBean list1 : genReports1){ %>
    						<%if(list.getAREA_NAM().equalsIgnoreCase(list1.getAREA_NAM())){ %>
    								<td><%=list1.getAREA_TRAGET() %></td>
    						<%} %>
    					<%} %>
    					<td><%=df.format(list.getTOTAL())%></td>
  					</tr>	
  					<%} %>

      	</tbody>
      	
      	<thead>
      	
      	</thead>
      	</table>
		
		<input type="button" class="my-3 btn btn-success btn-md" value="Download to Excel" onclick="exportExl()" value="Export">
		<input type="hidden" value="" name="action" id="action" />
		</center>
		</div>
<script type="text/javascript">
			function exportExl()
			{				
				$("#userTbl").table2excel({
				    exclude:".noExl",
				    name:"Worksheet Name",
				    filename:"Area Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
</script>
	</form>
	
</body>
</html>