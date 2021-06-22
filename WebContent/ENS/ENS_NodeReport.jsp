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
<title>Node Report</title>
</head>
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
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<div class="container col-4">

<form action="ENS_AddNodeservlet" method="post" name="formAddNode">
<%-- 
	<input type="hidden" name="DPR_slctPlant" id="DPR_slctPlant" value="<%= request.getParameter("DPR_slctPlant")%>"/>
	<input type="hidden" name="FromDt" id="FromDt" value="<%= request.getParameter("FromDt")%>"/>
	<input type="hidden" name="ToDt" id="ToDt" value="<%= request.getParameter("ToDt")%>"/> --%>
	
	<center>
		<h3 class="my-3 text-success">Node Report</h3>
		<table class="table table-responsive table-hover table-striped" id="userTbl" border="1">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    			<th>Sr. no</th>
        		<th>Section</th>
        		<th>Node Name</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AddNodBean list : genReports){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
<!--   					<td> -->
<%--                      <a href="FS_OhcFACServlet?action=Edit&EdtSrno=<%= list.getSrNo() %>">Edit</a> --%>
<!--                      &nbsp;&nbsp;&nbsp;&nbsp; -->
<%--                      <a href="FS_OhcFACServlet?action=Reopen&EdtSrno=<%= list.getSrNo() %>">Reopen</a>                     --%>
<!--                     </td> -->
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getSEC()%></td>
    				<td><%=list.getNOD_NAM() %></td>
    				
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
				    filename:"Node Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
		</script>



<jsp:include page="/ENS/ENS_Footer.jsp"/>

</body>
</html>