<%@page import="beans.ENS_UploadBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title>Summary Master Report</title>
<meta http-equiv="Content-Type"	content="<%response.setHeader("Content-Disposition","attachment;filename=summary_master_report.xls");%>">
</head>
<%
	String[][] arrData = null;
	if(request.getAttribute("MachnData") != null)
	{
		arrData = (String[][]) request.getAttribute("Reportuploadno");
		
		System.out.println("Array Data Size in Jsp Page" + arrData.length);
	
	}
	
	String sDate = null;
	String eDate = null;
			
	sDate = request.getAttribute("sDate").toString();
	eDate = request.getAttribute("eDate").toString(); 
	
	int mSd = Integer.parseInt(sDate.substring(8, 10));
	int mEd = Integer.parseInt(eDate.substring(8, 10));
	
	int diff = mEd - mSd;
	
	diff = diff+1;
	
	System.out.println("sDate in jsp = " + mSd);
	System.out.println("eDate in jsp = " + mEd);
%>
<body>
<div class="container">
	<h3 class="my-3 text-success">Summary Master Report</h3>
	<center>
	<h2><%= sDate %> to <%=eDate %></h2>
		<table class="table-bordered table-responsive table-hover">
		<tr>
					<th>Node Name</th>
					<th>Machine Name</th>
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
				double colTot=0.0;
				int k=2;
				int avgCount = -2;
				DecimalFormat df = new DecimalFormat("#,###,##0.00");
				for(int i=0;i<arrData.length;i++) {%>
				<tr>
					<%colTot=0; %>
					<%for(int j=0;j<arrData[i].length;j++) {%>
						<%if(arrData[i][j]!=null){ %>
						<td> <%= arrData[i][j] %> <%avgCount++; %></td>	
						<%  
						
						if(j>1 && !arrData[i][j].equalsIgnoreCase("-") && arrData[i][j]!=null)
						{
							System.out.println("In Jsp Total = " + arrData[i][j]);
							System.out.println("In Jsp Avg  = "+avgCount);
							colTot = colTot + Double.valueOf(arrData[i][j]);
						}%>
					
					<%} else{%>
						<td>-</td>
					<%}}
					%>
						<td><%=colTot %></td>
						<td><%=df.format(colTot/avgCount)%></td>	
				</tr>
				<%avgCount = -2; %>
				<%}%>
				
			     <tr>
					<%
					iCount = iCount + 2;
					double rowTot = 0.0;
					double fRowTot = 0.0;
					int arraySize = arrData.length - 1;
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
									
									<td><%=rowTot %></td>
								<% 
								fRowTot = fRowTot + rowTot; 
								rowTot=0; 
								
								}%>
							
						<%} %>
		
					<%} %>
					<td><%=fRowTot %></td>
				</tr>
		</table>
		</center>
		</div>
</body>
</html>