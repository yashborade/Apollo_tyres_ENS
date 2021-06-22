<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="beans.UserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title> Add Machine</title>
</head>
<script type="text/javascript">
		function submitValues(element)
		{	
			if(element.value=="Insert"){

				if (document.getElementById("nod_nam").value=="")
				{
					document.getElementById("errorMsg").innerHTML="Please Enter Node Name.";
				}
				if (document.getElementById("mach_nam").value=="")
				{
					document.getElementById("errorMsg").innerHTML="Please Enter Machine Name.";
				}
				if (document.getElementById("nod_nam").value=="" && document.getElementById("mach_nam").value=="")
				{
					document.getElementById("errorMsg").innerHTML="Please Enter Machine Name and Node Name.";
				}
				else if (document.getElementById("nod_nam").value!="" && document.getElementById("mach_nam").value!="")
				{
					document.getElementById("errorMsg").innerHTML="";
					document.formAddMach.action.value="Insert";
					document.formAddMach.submit();
				}
					//alert("ins"+element.value);
					
					//alert("action"+document.getElementById("action").value);
					
			}
			else if(element.value=="Report")
			{
				//alert("rep"+element.value);
				document.formAddMach.action.value="Report";
				//alert("action"+document.getElementById("action").value);
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
</head>
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
<form action="ENS_AddMachServlet" method="get" name="formAddMach">		
		
	<div class="container mt-3">
		<div class="row">
			<div class="col-xs-12 text-center pt-1" style="border-radius:20px 20px 0 0; font-weight :bold; font-size:16pt; letter-spacing: 1px;  width: 100%; height:40px; background-color: #a78cc2;">
				Register Machine
			</div>
			
		</div>
		<div class="row py-3" style="border: 2px solid #a78cc2;">
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Node Name*</span>
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
							url : "ENS_HelpServlet?field=search",
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
					<input type="text" id="nod_nam" name="nod_nam" placeholder="&nbsp;" onblur="getEmpDetail()">
					<span class="border"></span>
				</label>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Machine Name* </span>
					<input type="text" id="mach_nam" name="mach_nam" placeholder="&nbsp;" onblur="getEmpDetail()">
					<span class="border"></span>
				</label>
			</div>
			
			
	</div>
	<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="Insert" id="Insert" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0  0 20px; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="Report" id="Report" onclick="submitValues(this)" style="  border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius: 0 0 0 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
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
			<font color="red"><h3>* Fields are Mandatory</h3></font>
	</div>
	</form>
	</div>	
<jsp:include page="/ENS/ENS_Footer.jsp"/>

</body>
</html>