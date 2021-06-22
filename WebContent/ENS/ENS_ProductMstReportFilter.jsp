<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.UserBean"%>
<%@page import="beans.ENS_AddMachBean" %>
<%@page import="dao.ENS_AddMachDao" %>
<%@page import="mapper.ENS_AddMachMapper" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Report</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
<script type="text/javascript">
		function submitValues(element)
		{	
			if(element.value=="FilterReport")
			{
				document.formAddMach.action.value="FilterReport";
				document.formAddMach.submit();	
			}	
			else if(element.value=="cancel")
			{
				//alert("can"+element.value);
				document.formAddMach.action.value="cancel";
				//alert("action"+document.getElementById("action").value);
				document.formAddMach.submit();
			}
		
		}
</script>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>

<form action="ENS_AddProductMstServlet" method="get" name="formAddMach" id="formAddMach">		
		
	<div class="container mt-3">
		<div class="row">
			<div class="col-xs-12 text-center pt-1" style="border-radius:20px 20px 0 0; font-weight :bold; font-size:16pt; letter-spacing: 1px;  width: 100%; height:40px; background-color: #a78cc2;">
				Product Report
			</div>
			
		</div>
		<div class="row py-3" style="border: 2px solid #a78cc2;">
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="date1" class="inp">
				<span class="label">Enter Date* </span>
					<input type="date" id="date1" name="date1" placeholder="&nbsp;" onblur="getEmpDetail()">
					<span class="border"></span>
				</label>
			</div>
			
	</div>
	<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="FilterReport" id="FilterReport" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0  0 20px; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="cancel" id="cancel" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:0 20px 20px 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
			</div>		  
		</div>
		<font color="red"><h3>* Fields are Mandatory</h3></font>
		</div>
		</form>
		<jsp:include page="/ENS/ENS_Footer.jsp"/>
</body>
</html>