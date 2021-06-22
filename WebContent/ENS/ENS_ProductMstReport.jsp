<%@page import="com.oreilly.servlet.DaemonHttpServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title>Production Report</title>
</head>
<%
				ArrayList<ENS_AddProductBean> genReports = null;
				if(request.getAttribute("Reportproduct") != null)
				{
					genReports = (ArrayList<ENS_AddProductBean>) request.getAttribute("Reportproduct");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_AddProductBean>();
				}
			%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<div class="container">

<form action="ENS_AddProductMstServlet" method="post" name="formAddNode">
	<center>
		<h3 class="my-3 text-success">Product Report</h3>
		<table class="table table-responsive table-hover table-striped" id="userTbl" border="1">
		<thead>
    		<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
    			<th>Sr. no</th>
        		<th>Date</th>
        		<th>Equipment Name</th>
        		<th>Total Readings</th>
        		<th>Bias Batches</th>
        		<th>Radial Batches</th>
        		<th>Total Batches</th>
        		<th>Bias Consumption</th>
        		<th>Radial Consumption</th>
        		<th>Total Consumption</th>
    		</tr>
   		</thead>
   		<tbody>
        		<%for(ENS_AddProductBean list : genReports){ %>
  				<tr style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
  				
  				<%
  				Date date = new Date();
  				SimpleDateFormat sdfo = new SimpleDateFormat("dd-MM-yyyy");
  				String a = sdfo.format(list.getBTCH_DATE()).toString();
  				System.out.println("Dates = "+a);
  				%>
   					
    				<td><%=list.getSRNO()%></td>
    				<td><%=a%></td>
    				<td><%=list.getEQUIP_NAM()%></td>
    				<td><%=list.getEQUIP_TOTAL() %></td>		
    				<td><%=list.getBIAS_BTCH()%></td>
    				<td><%=list.getRADIAL_BTCH()%></td>
    				<td><%=list.getTOT_BTCH()%></td>
    				<td><%=list.getBIAS_CONSUP()%></td>
    				<td><%=list.getRADIAL_CONSUP()%></td>
    				<td><%=list.getTOTAL()%></td>
  				</tr>
  				<%} %>
      	</tbody>
      	</table>
		
		<input type="button" class="my-3 btn btn-success btn-md" value="Download to Excel" onclick="exportExl()" value="Export">
		<input type="hidden" value="" name="action" id="action" />
		
<script type="text/javascript">
			function exportExl()
			{				
				$("#userTbl").table2excel({
				    exclude:".noExl",
				    name:"Worksheet Name",
				    filename:"Production Report",//do not include extension
				    fileext:".xls" // file extension
				  });							
			}
</script>
			<div class="container">
    <div class="row my-3">
        <div class="col">
            <h4>Bootstrap 4 Chart.js</h4>
        </div>
    </div>
    <div class="row my-2">
        <div class="col-md-6 py-1">
            <div class="card">
                <div class="card-body">
                    <canvas id="chLine1"></canvas>
                </div>
            </div>
        </div>
        <div class="col-md-6 py-1">
            <div class="card">
                <div class="card-body">
                    <canvas id="chBar1"></canvas>
                </div>
            </div>
        </div>
     </div>        
</div>
<%
	ArrayList<String> list11=new ArrayList<String>();
	ArrayList<String> list111=new ArrayList<String>();
	ArrayList<String> list1111=new ArrayList<String>();
	List<Double> list22=new ArrayList<Double>();
	List<Double> list33=new ArrayList<Double>();
	List<Double> list44=new ArrayList<Double>();


	for(ENS_AddProductBean lst1 : genReports)
	{
		list11.add(lst1.getEQUIP_NAM());
		/* list111.add(lst1.getEQUIP_NAM().toString().substring(5,7));
		list1111.add(lst1.getB.toString().substring(8,10)); */
		
		list22.add(lst1.getRADIAL_CONSUP());
		list33.add(lst1.getBIAS_CONSUP());
		list44.add(lst1.getTOTAL());
		
		System.out.println("lst11 = " + list11);
		System.out.println("lst111 = " + list111);
		System.out.println("lst1111 = " + list1111);
		System.out.println("lst22 = " + list22);
	}
	System.out.println("Out lst11 = " + list11);

%>

<script type="text/javascript">

/* chart.js chart examples */

//chart colors
var colors = ['#007bff','#28a745','#333333','#c3e6cb','#dc3545','#6c757d'];
/* var timeFormat = 'YYYY/MM/DD'; */
/* bar chart */
/* var date1=[]; */
var rdngs = [];
<%
System.out.println("Jsp List Size = " + list11.size());
for(int i=0;i<list11.size();i++)
{%>

	var xy = '<%=list11.get(i)%>';
	rdngs.push(xy);
	
	/* alert("rdngs="); */
	
<%}%>
<%-- <%
for(int i=0;i<list11.size();i++)
{%>
	
	date1.push(<%=list1111.get(i)%> + "-" + <%=list111.get(i)%> + "-" + <%=list11.get(i)%>);
	//alert("Date=" + date1);
	
<%}%> --%>

var chBar = document.getElementById("chBar1");
	if (chBar) {
		new Chart(chBar, {
		type: 'bar',
		data: {
		 labels: rdngs,
		 datasets: [{
			 label: "Radial Consumption",
		   data: <%=list22%>,
		   backgroundColor: colors[0]
		 },
		 {
			 label: "Bias Consumption",
		   data: <%=list33%>,
		   backgroundColor: colors[1]
		 },
		 {
			 label: "Total Consumption",
			   data: <%=list44%>,
			   backgroundColor: colors[2]
		 }]
		},
		options: {
		 legend: {
		   display: true
		 },
		 scales: {
		   xAxes: [{	
			 beginAtZero: true,		   
		     barPercentage: 0.4,
		     categoryPercentage: 0.5
		   }]
		 }
	}
});

}
	
/* large line chart */
	var chLine = document.getElementById("chLine1");
		var chartData = {
		labels: rdngs,
		
		datasets: [{
			label: "Radial Consumption",
			data: <%=list22%>,
			backgroundColor: 'transparent',
			borderColor: colors[0],
			borderWidth: 4,
			pointBackgroundColor: colors[0]
		},
		{
			label: "Bias Consumption",
			data: <%=list33%>,
			backgroundColor: 'transparent',
			//backgroundColor: colors[3],
			borderColor: colors[1],
			borderWidth: 4,
	  		pointBackgroundColor: colors[1]
		},
		{  
			label: "Total Consumption",
			data: <%=list44%>,
			backgroundColor: 'transparent',
			//backgroundColor: colors[3],
			borderColor: colors[2],
			borderWidth: 4,
	  		pointBackgroundColor: colors[2]
		}]
		};
		if (chLine) {
		new Chart(chLine, {
		type: 'line',
		data: chartData,
		options: {
		 scales: {
		   xAxes: [{
		     ticks: {
		       beginAtZero: true
		     }
		   }]
		 },
		 legend: {
		   display: true
		 },
		 responsive: true
		}
	});
}


</script>
		</center>
		
	</form>
</div>



<jsp:include page="/ENS/ENS_Footer.jsp"/>

</body>
</html>