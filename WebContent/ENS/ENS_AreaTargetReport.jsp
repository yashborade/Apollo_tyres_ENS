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
<title>Area Target Report</title>
</head>
<%
				ArrayList<ENS_AreaTargetBean> genReports = null;
				if(request.getAttribute("Reporttarget") != null)
				{
					genReports = (ArrayList<ENS_AreaTargetBean>) request.getAttribute("Reporttarget");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_AreaTargetBean>();
				}
			%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<div class="container col-4">

<form action="ENS_SpcTargetServlet" method="post" name="formAddNode">
	
	<center>
		<h3 class="my-3 text-success">Area Target Report</h3>
		<table class="table table-responsive table-hover table-striped" id="userTbl" border="1">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    			<th>Area Name</th>
        		<th>Month</th>
        		<th>Area Target</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AreaTargetBean list : genReports){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
<!--   					<td> -->
<%--                      <a href="FS_OhcFACServlet?action=Edit&EdtSrno=<%= list.getSrNo() %>">Edit</a> --%>
<!--                      &nbsp;&nbsp;&nbsp;&nbsp; -->
<%--                      <a href="FS_OhcFACServlet?action=Reopen&EdtSrno=<%= list.getSrNo() %>">Reopen</a>                     --%>
<!--                     </td> -->
    				<td><%=list.getAREA_NAM().substring(3)%></td>
    				<td><%=list.getMONTH()%></td>
    				<td><%=list.getAREA_TRAGET() %></td>
    				
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		
		<input type="button" class="my-3 btn btn-success btn-md" value="Download to Excel" onclick="exportExl()" value="Export">
		<input type="hidden" value="" name="action" id="action" />
		</center>
		
<script type="text/javascript">
			function exportExl()
			{				
				$("#userTbl").table2excel({
				    exclude:".noExl",
				    name:"Worksheet Name",
				    filename:"Area Target Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
</script>
	</form>
</div>



<jsp:include page="/ENS/ENS_Footer.jsp"/>

</body>
</html>