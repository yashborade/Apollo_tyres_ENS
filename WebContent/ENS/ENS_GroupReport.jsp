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
<title>Group Report</title>
</head>
<%
				ArrayList<ENS_AddGroupBean> genReports = null;
				if(request.getAttribute("Reportequipno") != null)
				{
					genReports = (ArrayList<ENS_AddGroupBean>) request.getAttribute("Reportequipno");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_AddGroupBean>();
				}
			%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>

<div class="container col-11">
<form action="ENS_AddGroupServlet" method="post" name="formAddEquip" id="userTbl">
	
	<center>
		<h3 class="my-3 text-success">Group Report</h3>
		<table class="table table-responsive table-hover table-striped" border="1">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    			<th>Sr. no</th>
        		<th>Equipment Name</th>
        		<th>Fidder Name In Addition</th>
        		<th>Fidder Name In Subtract</th>
        		
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AddGroupBean list : genReports){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    				<td><%=list.getSRNO()%></td>
    				<td><%=list.getEQUIP_NAM() %></td>
    				<td><%=list.getFIDD_NAM_PLUS() %></td>
    				<td><%=list.getFIDD_NAM_MINUS() %></td>
    				
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
				    filename:"Fedder Group Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
</script>
		
	</form>
	</div>



<jsp:include page="/ENS/ENS_Footer.jsp"/>

</body>
</html>