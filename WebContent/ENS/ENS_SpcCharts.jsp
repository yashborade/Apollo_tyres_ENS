<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "beans.*" %>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.*"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SPC Charts</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
<%
				ArrayList<ENS_CalSpcGroupBean> genReports = null;
				if(request.getAttribute("charts") != null)
				{
					genReports = (ArrayList<ENS_CalSpcGroupBean>) request.getAttribute("charts");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports = new ArrayList<ENS_CalSpcGroupBean>();
				}
				
				String b = request.getAttribute("target").toString();
				System.out.println("Actual target in jsp :"+b);
%>
<%
				ArrayList<ENS_SpcTargetBean> genReports1 = null;
				if(request.getAttribute("target") != null)
				{
					genReports1 = (ArrayList<ENS_SpcTargetBean>) request.getAttribute("target");
					//System.out.println(genReports.size());
				}
				else
				{
					genReports1 = new ArrayList<ENS_SpcTargetBean>();
				}
				
%>
<%
	ArrayList<String> lst_date=new ArrayList<String>();
	ArrayList<String> lst_nam=new ArrayList<String>();
	List<Double> lst_tot=new ArrayList<Double>();
	List<Double> lst_target=new ArrayList<Double>();


	for(ENS_CalSpcGroupBean lst1 : genReports)
	{
		for(ENS_SpcTargetBean lst2 : genReports1)
		{
			lst_date.add(lst1.getSPC_DATE().toString().substring(0,10));
			lst_nam.add(lst1.getAREA_NAME());
			/* list111.add(lst1.getEQUIP_NAM().toString().substring(5,7));
			list1111.add(lst1.getB.toString().substring(8,10)); */
		
			lst_tot.add(lst1.getTOTAL_SPC());
			lst_target.add(lst2.getSPC_TARGET());
			/* list33.add(lst1.getBIAS_CONSUP());
			list44.add(lst1.getTOT_BTCH()); */
		
			System.out.println("list Date = " + lst_date);
			System.out.println("list Total = " + lst_tot);
			System.out.println("list Target = " + lst_target);
		}
	}
	System.out.println("list Date = " + lst_date);
	
	String a = lst_nam+"";
	String arg[] = a.split(",");
	System.out.print("Split Area Name ="+arg[1]);

%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<div class="container col-11">

<form action="ENS_ChartsServlet" method="post" name="formAddNode">
	
	<center>
		<input type="hidden" value="" name="action" id="action" />
		
	<div class="container">
    <div class="row my-3">
        <div class="col">
            <h4><%=arg[1] %> SPC Chart</h4>
        </div>
    </div>
    <canvas id="chLine1" style="width : 150px; height: 50px;"></canvas>     
</div>


<script type="text/javascript">

/* chart.js chart examples */

//chart colors
var colors = ['#007bff','#28a745','#333333','#c3e6cb','#dc3545','#6c757d'];
/* var timeFormat = 'YYYY/MM/DD'; */
/* bar chart */
/* var date1=[]; */
var rdngs = [];
<%
System.out.println("Jsp List Size = " + lst_date.size());
for(int i=0;i<lst_date.size();i++)
{%>

	var xy = '<%=lst_date.get(i)%>';
	rdngs.push(xy);
	
	/* alert("rdngs="); */	
<%}%>
/* large line chart */
	var chLine = document.getElementById("chLine1");
		var chartData = {
		labels: rdngs,
		
		datasets: [{
			label: "SPC Consumption Daywise",
			data: <%=lst_tot%>,
			backgroundColor: 'transparent',
			borderColor: colors[1],
			borderWidth: 4,
			pointBackgroundColor: colors[1]
		},
		{
			label: "Actual Target",
			data: <%=lst_target%>,
			backgroundColor: 'transparent',
			borderColor: colors[0],
			borderWidth: 4,
	  		pointBackgroundColor: colors[0]
		},

		],
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