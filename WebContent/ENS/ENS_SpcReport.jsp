<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="beans.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SPC Report</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
<%
				ArrayList<ENS_CalSpcGroupBean> genReports = null;
				if(request.getAttribute("Reportspc") != null)
				{
					genReports = (ArrayList<ENS_CalSpcGroupBean>) request.getAttribute("Reportspc");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_CalSpcGroupBean>();
				}
			%>
			<%
				ArrayList<ENS_SpcTargetBean> genReports1 = null;
				if(request.getAttribute("reportmonth") != null)
				{
					genReports1 = (ArrayList<ENS_SpcTargetBean>) request.getAttribute("reportmonth");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports1 = new ArrayList<ENS_SpcTargetBean>();
				}
			%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<form action="ENS_CalSpcGroupServlet" method="post" name="formAddNode">
<div class="container col-4">
	
	<center>
		<h3 class="my-3 text-success">SPC Report</h3>
		<table class="table table-responsive table-hover table-striped" id="userTbl" border="1">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    		
        		<th>Area Name</th>
        		<th>Target SPC</th>
        		<th>Total SPC</th>
        		
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_CalSpcGroupBean list : genReports){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
					<% DecimalFormat df = new DecimalFormat("#,###,##0.00");%>
						<td><%=list.getAREA_NAME().substring(3) %></td>
    					
    					
    					<%for(ENS_SpcTargetBean list1 : genReports1){ %>
    					
    						<%if(list.getAREA_NAME().equalsIgnoreCase(list1.getAREA_NAM())) {%>
    							<td><%=list1.getSPC_TARGET() %></td>
    							
    						<%} %>
  					<%} %>
  					<td><%=df.format(list.getTOTAL_SPC())%></td>
  					</tr>
  				<%} %>
      	</tbody>
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
				    filename:"Spc Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
</script>
	</form>
</body>
</html>