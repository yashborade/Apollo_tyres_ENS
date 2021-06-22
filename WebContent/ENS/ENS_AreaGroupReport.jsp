<%@page import="beans.ENS_AreaGroupBean"%>
<%@page import="beans.ENS_AddGroupBean"%>
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
<title>Area Group Report</title>
</head>
<%
				ArrayList<ENS_AreaGroupBean> genReports = null;
				if(request.getAttribute("Reportequipno") != null)
				{
					genReports = (ArrayList<ENS_AreaGroupBean>) request.getAttribute("Reportequipno");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_AreaGroupBean>();
				}
			%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<div class="container col-7">

<form action="ENS_AreaGroupServlet" method="post" name="formAddEquip">
	
	<center>
		<h3 class="my-3 text-success">Area Group Report</h3>
		<table class="table table-responsive table-hover table-striped" id="userTbl" border="1">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    			<th>Sr. no</th>
        		<th>Area Name</th>
        		<th>Equipment Group</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AreaGroupBean list : genReports){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getAREA_NAME() %></td>
    				<td><%=list.getEQUIP_NAME() %></td>
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
				    filename:"Area Group Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
</script>
		
	</form>
</div>



<jsp:include page="/ENS/ENS_Footer.jsp"/>

</body>
</html>