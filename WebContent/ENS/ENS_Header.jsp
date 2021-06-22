<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.UserBean"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title>Insert title here</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<style>
.navbar-nav li:hover .dropdown-menu {
        display: block;
        color: purple;
    }
.navbar{ 
background-color: #b366ff; #d9b3ff

}
 
.dropdown{
    border-radius:0;
    border:0; 
}
.dropdown-menu{
    background:#ffd6cc;
    border:0;
    top:80%; 
    border-radius:0px 0px 10px 10px;
}
.dropdown-item:hover{
    background: #ff3300;
    color:white;
    border-radius:10px 10px 10px 10px;
}
.dropdown-menu a{ 
    color: #5C2482;
}   
.navbar .nav-item .nav-link{
    color: purple;
    
}
.navbar .nav-item .nav-link:hover .navbar .nav-item .nav-link{
    color:white;
    
}  
</style>
</head>
<body>
<%
	HttpSession httpSession = request.getSession();
	UserBean users1 = (UserBean) httpSession.getAttribute("Users");
%>  
    

<div style="background-color: white;" class="py-1 pl-3">
	<img src="images/LOGOS/ApolloPNG3.png" alt="Distance Logo"/>
</div>    
     
<nav class="navbar navbar-expand-lg navbar-light"> 
<a class="navbar-brand" href="/EnergyManagementSystem/ENS/ENS_Welcome.jsp" style="">
  <!-- <a class="navbar-brand" href="/EMS/ENS/ENS_Welcome.jsp" style=""> -->
  <img class="img-fluid" src="images/LOGOS/ApolloPNG5.png" alt="Distance Logo"/>
  </a>
        
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>  
     
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item dropdown active">
      
      <%if(users1.gettype().equalsIgnoreCase("Sup"))
      	{
      %>
       		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Edit Master
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
 
         	<a class="dropdown-item" href="ENS_AddNodeservlet?action=load">Add Node</a>
         	<a class="dropdown-item" href="ENS_AddMachServlet?action=load">Add Feeder</a>
          	<a class="dropdown-item" href="ENS_AddEquipServlet?action=load">Add Equipment</a>          
         	<a class="dropdown-item" href="ENS_AddAreaServlet?action=load">Add Area</a>
          	<a class="dropdown-item" href="ENS_AddProductMstServlet?action=load">Add Production</a>
          	<a class="dropdown-item" href="ENS_AreaTargetServlet?action=load">Add Area Target</a>
          	<a class="dropdown-item" href="ENS_SpcTargetServlet?action=load">Add SPC Target</a>
          	<a class="dropdown-item" href="ENS_UploadReportServlet?action=Edit">Edit Readings</a>
           	<a class="dropdown-item" href="ENS_SummaryMasterReportServlet?action=Edit">Edit Summary</a>
          	<!-- <a class="dropdown-item" href="ENS_MailServlet?action=load">Send Mail</a>    -->
          	</div>       
      	</li>
      
      	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         	Edit Groups
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
        
       	 	<a class="dropdown-item" href="ENS_AddGroupServlet?action=load">Add Feeder Group</a>
         	<a class="dropdown-item" href="ENS_AreaGroupServlet?action=load">Add Area Group</a>
         	<a class="dropdown-item" href="ENS_AddSpcGroupServlet?action=load">Add SPC Group</a>
         	</div>
      	</li>
      
      	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Lists
       		</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
          	<a class="dropdown-item" href="ENS_AddNodeservlet?action=Report">Node Report</a>
          	<a class="dropdown-item" href="ENS_AddMachServlet?action=Report">Feeder Report</a>
          	<a class="dropdown-item" href="ENS_AddEquipServlet?action=Report">Equipment Report</a>
          	<a class="dropdown-item" href="ENS_AddAreaServlet?action=Report">Area Report</a>
          	<a class="dropdown-item" href="ENS_AddProductMstServlet?action=Report">Production Report</a>
          	<a class="dropdown-item" href="ENS_UploadReportServlet?action=Report">Reading Report</a>
          	<a class="dropdown-item" href="ENS_SummaryServlet?action=load">Summary Report</a>
          	<a class="dropdown-item" href="ENS_CalculateAreaGroupServlet?action=List">Area Target Master Report</a>
          	<a class="dropdown-item" href="ENS_AreaTargetServlet?action=Report">Area Target Report</a>
          	<a class="dropdown-item" href="ENS_CalSpcGroupServlet?action=List">SPC Target Master Report</a>
          	<a class="dropdown-item" href="ENS_SpcTargetServlet?action=Report">SPC Target Report</a>
        	</div>
        
        	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Lists Group
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
 
            <a class="dropdown-item" href="ENS_AddGroupServlet?action=Report">Feeder Group Report</a>
          	<a class="dropdown-item" href="ENS_AreaGroupServlet?action=Report">Area Group Report</a>
          	<a class="dropdown-item" href="ENS_AddSpcGroupServlet?action=Report">SPC Group Report</a>
          	</div>
         
      	</li>
        
        	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Power Consumption
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
 			
 		  	<a class="dropdown-item" href="ENS_SummaryMasterReportServlet?action=load">Feederwise Power Consumption</a>	
          	<a class="dropdown-item" href="ENS_CalculateGroupServlet?action=load">Equipmentwise Power Consumption</a>
          	<a class="dropdown-item" href="ENS_CalculateAreaGroupServlet?action=load">Areawise Power Consumption</a>
          	<a class="dropdown-item" href="ENS_CalSpcGroupServlet?action=Report">SPCWise Power Consumption</a>
          	</div>
          
      	</li>
        	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Charts
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
        
       	 	<a class="dropdown-item" href="ENS_ChartsServlet?action=Report">SPC Charts</a>
       	 	<a class="dropdown-item" href="ENS_ChartsServlet?action=Report_Equip">Equipment Charts</a>
       	 	<a class="dropdown-item" href="ENS_ChartsServlet?action=Report_Area">Area Charts</a>
       	 	</div>
      
      	</li>
        
        
      	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Uploads
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
          	<a class="dropdown-item" href="ENS_ExcelData?action=load">Readings</a>
          	<a class="dropdown-item" href="Mail_ExcelData?action=load">Send Mail</a>
          	</div>
          	</li>
          	
          	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Calculate Power
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
 
           	<a class="dropdown-item" href="ENS_SummaryServlet?action=Calculate">Calculate Feeder Group</a> 
         	<a class="dropdown-item" href="ENS_CalculateGroupServlet?action=Calculate">Calculate Area Group</a>
          	<a class="dropdown-item" href="ENS_CalSpcGroupServlet?action=Calculate">Calculate SPC Group</a>
          	</div>
         
      	</li>
      <%
      }
      else if(users1.gettype().equalsIgnoreCase("Key"))
      {
      %>
      		
      
      	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Lists
       		</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
          	<a class="dropdown-item" href="ENS_AddNodeservlet?action=Report">Node Report</a>
          	<a class="dropdown-item" href="ENS_AddMachServlet?action=Report">Feeder Report</a>
          	<a class="dropdown-item" href="ENS_AddEquipServlet?action=Report">Equipment Report</a>
          	<a class="dropdown-item" href="ENS_AddAreaServlet?action=Report">Area Report</a>
          	<a class="dropdown-item" href="ENS_AddProductMstServlet?action=Report">Production Report</a>
          	<a class="dropdown-item" href="ENS_UploadReportServlet?action=Report">Reading Report</a>
          	<a class="dropdown-item" href="ENS_SummaryServlet?action=load">Summary Report</a>
          	<a class="dropdown-item" href="ENS_CalculateAreaGroupServlet?action=List">Area Target Master Report</a>
          	<a class="dropdown-item" href="ENS_AreaTargetServlet?action=Report">Area Target Report</a>
          	<a class="dropdown-item" href="ENS_CalSpcGroupServlet?action=List">SPC Target Master Report</a>
          	<a class="dropdown-item" href="ENS_SpcTargetServlet?action=Report">SPC Target Report</a>
        	</div>
        
        	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Group Lists
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
 
            <a class="dropdown-item" href="ENS_AddGroupServlet?action=Report">Feeder Group Report</a>
          	<a class="dropdown-item" href="ENS_AreaGroupServlet?action=Report">Area Group Report</a>
          	<a class="dropdown-item" href="ENS_AddSpcGroupServlet?action=Report">SPC Group Report</a>
          	</div>
         
      	</li>
        
        	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Power Consumption
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
 			
 		  	<a class="dropdown-item" href="ENS_SummaryMasterReportServlet?action=load">Feederwise Power Consumption</a>	
          	<a class="dropdown-item" href="ENS_CalculateGroupServlet?action=load">Equipmentwise Power Consumption</a>
          	<a class="dropdown-item" href="ENS_CalculateAreaGroupServlet?action=load">Areawise Power Consumption</a>
          	<a class="dropdown-item" href="ENS_CalSpcGroupServlet?action=Report">SPCwise Power Consumption</a>
          	</div>
          
      	</li>
        	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Charts
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
        
       	 	<a class="dropdown-item" href="ENS_ChartsServlet?action=Report">SPC Charts</a>
       	 	<a class="dropdown-item" href="ENS_ChartsServlet?action=Report_Equip">Equipment Charts</a>
       	 	<a class="dropdown-item" href="ENS_ChartsServlet?action=Report_Area">Area Charts</a>
       	 	</div>
      
      	</li>
        
        
      	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Uploads
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
          	<a class="dropdown-item" href="ENS_ExcelData?action=load">Readings</a>
          	<a class="dropdown-item" href="Mail_ExcelData?action=load">Send Mail</a>
          	</div>
          	</li>
          	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Calculate Power
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
 
           	<a class="dropdown-item" href="ENS_SummaryServlet?action=Calculate">Calculate Feeder Group</a> 
         	<a class="dropdown-item" href="ENS_CalculateGroupServlet?action=Calculate">Calculate Area Group</a>
          	<a class="dropdown-item" href="ENS_CalSpcGroupServlet?action=Calculate">Calculate SPC Group</a>
          	</div>
         
      	</li>
      <%
      }
      else if(users1.gettype().equalsIgnoreCase("Rep"))
      {
      %>
      		<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Charts
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
        
       	 	<a class="dropdown-item" href="ENS_ChartsServlet?action=Report">SPC Charts</a>
       	 	<a class="dropdown-item" href="ENS_ChartsServlet?action=Report_Equip">Equipment Charts</a>
       	 	<a class="dropdown-item" href="ENS_ChartsServlet?action=Report_Area">Area Charts</a>
       	 	</div>
      
      		</li>
        
        	<li class="nav-item dropdown active">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Power Consumption
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
 			
 		  	<a class="dropdown-item" href="ENS_SummaryMasterReportServlet?action=load">Feederwise Power Consumption</a>	
          	<a class="dropdown-item" href="ENS_CalculateGroupServlet?action=load">Equipmentwise Power Consumption</a>
          	<a class="dropdown-item" href="ENS_CalculateAreaGroupServlet?action=load">Areawise Power Consumption</a>
          	<a class="dropdown-item" href="ENS_CalSpcGroupServlet?action=Report">SPCwise Power Consumption</a>
          	</div>
      
      <%
      } 
      %>
    </ul>
    <ul class="navbar-nav text-right">
    	<li class="nav-item active">
    		<a class="nav-link" href="#" tabindex="-1" aria-disabled="true">Logged in as <%= users1.getUsername() %></a>
    	</li>
    </ul>
  </div>
</nav>


<script type="text/javascript">
$(document).ready(function () {
	$('.navbar-light .dmenu').hover(function () {
	        $(this).find('.sm-menu').first().stop(true, true).slideDown(150);
	    }, function () {
	        $(this).find('.sm-menu').first().stop(true, true).slideUp(105)
	    });
	});
</script>
	
</body>
</html>