<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.UserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title>Send Mail</title>
</head>

<script type="text/javascript">
		function submitValues(element)
		{	
			if(element.value=="Send")
			{
				//alert("rep"+element.value);
				document.formAddNode.action.value="Send";
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
$(document).ready(function(){
	$("#nod_nam").hover(function(){
		$("#spnPatt").hide();
	});
	
	$("#nod_nam").focus(function(){
		$("#errorMsg").text("");
	});
});
</script>
<body>
<%
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");	

%>
<jsp:include page="/ENS/ENS_Header.jsp"/>


<div>
<form action="ENS_MailServlet" method="post" name="formAddNode" id="formAddNode">		
		
	<div class="container mt-3">
		<div class="row">
			<div class="col-xs-12 text-center pt-1" style="border-radius:20px 20px 0 0; font-weight :bold; font-size:16pt; letter-spacing: 1px;  width: 100%; height:40px; background-color: #a78cc2;">
				Send Mail
			</div>
			
		</div>
		<div class="row py-3" style="border: 2px solid #a78cc2;">
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="section" class="inp">
					<span class="label">From &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input type="text " id="from" name="from" placeholder="&nbsp;" >
					<span class="border"></span>
				</label>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">To &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input type="text"  "to" name="to" placeholder="&nbsp;">
					<span class="border"></span>
				</label>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Subject &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input type="text" id="sub" name="sub" placeholder="&nbsp;" >
					<span class="border"></span>
				</label>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Report Date &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input type="date" id="date1" name="date1" placeholder="&nbsp;">
					<span class="border"></span>
				</label>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Select File </span>
					<input type="file" id="filepr" name="filepr" placeholder="&nbsp;" onblur="getEmpDetail()" style=" width : 278px;">
					<!-- <span class="border"></span> -->
				</label>
			</div>
	</div>
	<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="Send" id="Send" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0  0 20px; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="cancel" id="cancel" onclick="submitValues(this)" style="  border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius: 0 20px 20px 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
			</div>		  
		</div>
		<span id="errorMsg" style="color: red; font-size: 30px; font-weight: bold; padding-top: 20px;"></span>
				
				<%
				if (request.getAttribute("msg").equals("") || request.getAttribute("msg").equals(null))
				{
					out.println(request.getAttribute("msg").toString());
				}
				else
				{
					String m = request.getAttribute("msg").toString();
					out.println("<br /><span id='spnPatt'>");
					out.println("<font color='green' style='font-size: 18pt;'><b>" + m + "</b></font>");
					out.println("</span>");
				}
			%>
	
<jsp:include page="/ENS/ENS_Footer.jsp"/>
</body>

</html>