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
<title>Spc Target Report</title>
</head>
<%
				ArrayList<ENS_SpcTargetBean> genReports = null;
				if(request.getAttribute("Reporttarget") != null)
				{
					genReports = (ArrayList<ENS_SpcTargetBean>) request.getAttribute("Reporttarget");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_SpcTargetBean>();
				}
			%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<div class="container col-4">

<form action="ENS_SpcTargetServlet" method="post" name="formAddNode">
<%-- 
	<input type="hidden" name="DPR_slctPlant" id="DPR_slctPlant" value="<%= request.getParameter("DPR_slctPlant")%>"/>
	<input type="hidden" name="FromDt" id="FromDt" value="<%= request.getParameter("FromDt")%>"/>
	<input type="hidden" name="ToDt" id="ToDt" value="<%= request.getParameter("ToDt")%>"/> --%>
	
	<center>
		<h3 class="my-3 text-success">Spc Target Report</h3>
		<table class="table table-responsive table-hover table-striped" id="userTbl" border="2">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    			<th>Area Name</th>
        		<th>Month</th>
        		<th>Spc Target</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_SpcTargetBean list : genReports){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    				<td><%=list.getAREA_NAM().substring(3)%></td>
    				<td><%=list.getMONTH()%></td>
    				<td><%=list.getSPC_TARGET() %></td>
    				
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
				    filename:"Spc Target Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
</script>
</form>
</div>



<jsp:include page="/ENS/ENS_Footer.jsp"/>

</body>
</html>