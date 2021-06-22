<%@page import="beans.ENS_AddSpcGroupBean"%>
<%@page import="beans.ENS_AddSpcBean"%>
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
<title>SPC Group Report</title>
</head>
<%
				ArrayList<ENS_AddSpcGroupBean> genReports = null;
				if(request.getAttribute("Reportequipno") != null)
				{
					genReports = (ArrayList<ENS_AddSpcGroupBean>) request.getAttribute("Reportequipno");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_AddSpcGroupBean>();
				}
			%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<div class="container col-5">

<form action="ENS_AddSpcGroupServlet" method="post" name="formAddEquip">
	
	<center>
		<h3 class="my-3 text-success">SPC Group Report</h3>
		<table class="table table-responsive table-hover table-striped" id="userTbl" border="1">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    			<th>Sr. no</th>
        		<th>SPC Group</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AddSpcGroupBean list : genReports){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getSPC_GRP() %></td>
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
				    filename:"Spc Group Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
</script>
	</form>
</div>



<jsp:include page="/ENS/ENS_Footer.jsp"/>

</body>
</html>