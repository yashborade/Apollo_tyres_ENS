<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Area Report</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
<script type="text/javascript">
		function submitValues(element)
		{
			if(element.value=="Filter")	
			{
				//alert("rep"+element.value);
				document.formAddNode.action.value="Filter";
				//alert("action"+document.getElementById("action").value);
				document.formAddNode.submit();
			}
			else if(element.value=="cancel")
			{
				//alert("can"+element.value);
				document.formAddNode.action.value="cancel";
				//alert("action"+document.getElementById("action").value);
				document.formAddNode.submit();
			}
		}
		
</script>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<form action="ENS_CalculateAreaGroupServlet" method="post" name="formAddNode">		
		
	<div class="container mt-3">
		<div class="row">
			<div class="col-xs-12 text-center pt-1" style="border-radius:20px 20px 0 0; font-weight :bold; font-size:16pt; letter-spacing: 1px;  width: 100%; height:40px; background-color: #a78cc2;">
				Area Report
			</div>
			
		</div>
		<div class="row py-3" style="border: 2px solid #a78cc2;">
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Date* &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input type="date" id="date1" name="date1" placeholder="&nbsp;" onblur="getEmpDetail()">
					<span class="border"></span>
				</label>
			</div>
			
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="section" class="inp">
					<span class="label">Month* </span>
					<select class="form-control form-control-sm col-md-12"  style=" width : 220px;" id="month" name="month" onchange="disForm(this)">	    	    	
			      		<option>--Select--</option>
			      		<option>JANUARY</option>
			      		<option>FEBUARY</option>
			      		<option>MARCH</option>
			      		<option>APRIL</option>
			      		<option>MAY</option>
			      		<option>JUNE</option>
			      		<option>JULY</option>
			      		<option>AUGUST</option>
			      		<option>SEPTEMBER</option>
			      		<option>OCTOBER</option>
			      		<option>NOVEMBER</option>
			      		<option>DECEMBER</option>				      					      
			    	</select>	
				</label>
			</div>
			
	</div>
	<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="Filter" id="Filter" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0  0 20px; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="cancel" id="cancel" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:0 20px 20px 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
			</div>		  
		</div>
		<font color="red"><h3>* Fields are Mandatory</h3></font>


</div>
</form>
<jsp:include page="/ENS/ENS_Footer.jsp"/>
</body>
</html>