<%@page import="beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Summary</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
<script type="text/javascript">
		function submitValues(element)
		{	
			if(element.value=="Report")
			{
				//alert("rep"+element.value);
				document.formAddNode.action.value="Report";
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
<script>
					$(document).ready(function() {
					$("#nod_nam").autocomplete({
					width : 250,
					max : 10,
					delay : 100,
					minLength : 1,
					autoFocus : true,
					cacheLength : 1,
					scroll : true,
					highlight : false,
					source : function(request, response) {
						$.ajax({
							url : "ENS_HelpServlet?field=search_callNode",
							data : request,
							dataType : "json",
							success : function(data, textStatus, jqXHR) {
								console.log(data);
								response(data);
							},
							error : function(jqXHR, textStatus, errorThrown) {
								console.log(textStatus);
							}
						});
					}
				});
			});
</script>
<%
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");	

%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<form action="ENS_SummaryServlet" method="get" name="formAddNode">		
		
	<div class="container mt-3">
		<div class="row">
			<div class="col-xs-12 text-center pt-1" style="border-radius:20px 20px 0 0; font-weight :bold; font-size:16pt; letter-spacing: 1px;  width: 100%; height:40px; background-color: #a78cc2;">
				Summary Report
			</div>
			
		</div>
		<div class="row py-3" style="border: 2px solid #a78cc2;">
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Date* &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input type="date" id="date" name="date" placeholder="&nbsp;" onblur="getEmpDetail()">
					<span class="border"></span>
				</label>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="section" class="inp">
					<span class="label">Section* </span>
					<select class="form-control form-control-sm col-md-12"  style="width: 100%;" id="sec" name="sec" onchange="disForm(this)">	    	    	
			      		<option>--Select--</option>
			      		<option>Bias</option>
			      		<option>Radial</option>
			      		<option>OTR</option>
			      		<option>MCR</option>
			      		<option>MCB</option>			      					      
			    	</select>	
				</label>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Node Name* </span>
					<input type="text" id="nod_nam" name="nod_nam" placeholder="&nbsp;" onblur="getEmpDetail()">
					<span class="border"></span>
				</label>
			</div>
	</div>
	<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="Report" id="Report" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0  0 20px; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="cancel" id="cancel" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:0 20px 20px 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
			</div>		  
		</div>
		<font color="red"><h3>* Fields are Mandatory</h3></font>
		</div>
		</form>
<jsp:include page="/ENS/ENS_Footer.jsp"/>
</body>
</html>