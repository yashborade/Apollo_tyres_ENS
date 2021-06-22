<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.ENS_CalculateGroupBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Total Group Report</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
			<%
	String arrData[][] = null;
	if(request.getAttribute("Reportgroup") != null)
	{
		arrData = (String[][]) request.getAttribute("Reportgroup");
		
		System.out.println("Array Data Size in Jsp Page" + arrData.length);
	
	}
	
	String sDate = null;
	String eDate = null;
			
	sDate = request.getAttribute("sdate").toString();
	eDate = request.getAttribute("edate").toString(); 
	
	int mSd = Integer.parseInt(sDate.substring(8, 10));
	int mEd = Integer.parseInt(eDate.substring(8, 10));
	
	int diff = mEd - mSd;
	
	diff = diff+1;
	
	System.out.println("sDate in jsp = " + mSd);
	System.out.println("eDate in jsp = " + mEd);
%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>

<form action="ENS_CalculateGroupServlet" method="post" name="formAddEquip">
	
	<center>
		<h3 class="my-3 text-success">Total Group Report</h3>
		<h2><%= sDate %> to <%=eDate %></h2>
		<table class="table table-responsive table-hover table-striped" id="userTbl" border="5">
		<tr>		
					<th>Action</th>
					<th>Section</th>
					<th>Equipment Name</th>
					<%int iCount = 0; %>
					<%for(int i=mSd; i<=mEd; i++){
						++iCount;
						%>
						<td><b>Date &nbsp;&nbsp;<%=i %></b></td>
					<%} %>
					<td><b>Total</b></td>
					<td><b>Avg.</b></td>
		</tr>
		<%
				
				System.out.println("iCount = " + iCount);
				double colTot=0;
				int k=2;
				int avgCount = -2;
				DecimalFormat df = new DecimalFormat("#,###,##0.00");
				for(int i=0;i<arrData.length;i++) {%>
				
				<tr>
					<%colTot=0; %>
					<td><a href="ENS_CalculateGroupServlet?action=drill_report&ename=<%= arrData[i][1]%>
																			  &date1=<%=sDate%>
																			  &date2=<%=eDate%>">Show</a></td>
					
					<%for(int j=0;j<arrData[i].length;j++) {%>
						<%if(arrData[i][j]!=null){ %>
							
							<td><%= arrData[i][j] %> <%avgCount++; %></td>
							
							
						<%  
						if(j>1 && !arrData[i][j].equalsIgnoreCase("-") && arrData[i][j]!=null)
						{
							//System.out.println("In Jsp Total = " + arrData[i][j]);
							//System.out.println("In Jsp Avg  = "+avgCount);
							colTot = colTot + Double.valueOf(arrData[i][j]);
						}%>
					
					<%} else{%>
						<td>-</td>
					<%}}%>
					
					<td><%=df.format(colTot) %></td>
					<td><%=df.format(colTot/avgCount)%></td>
				</tr>
				<%avgCount = -2; %>
				<%}%>
				 <tr>
				 <td></td>
					<%
					iCount = iCount + 2;
					double rowTot = 0;
					double fRowTot = 0;
					int arraySize = arrData.length-1;
					System.out.println("Array Size = " + arraySize);
					for(int i=0;i<iCount;i++) {%>
						<%for(int j=0;j<arrData.length;j++) {%>
							<%if(arrData[j][i]!=null){ %>
								<% 
								if(i>1 && !arrData[j][i].equalsIgnoreCase("-") && arrData[j][i]!=null)
								{
									rowTot = rowTot + Double.valueOf(arrData[j][i]);
									
									
								}%> 
							<%} %>
							
							<%if(j==arraySize)
								{
									System.out.println("Row Tot = " + rowTot);%>
									
									<td><%=df.format(rowTot)%></td>
									
								<% 
								fRowTot = fRowTot + rowTot; 
								rowTot=0; 
								
								}%>
							
							
						<%} %>
		
					<%} %>
					<td><%= df.format(fRowTot) %></td>
				</tr>
				
					
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
				    filename:"Group Master Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
</script>
	</form>



<jsp:include page="/ENS/ENS_Footer.jsp"/>

</body>
</html>