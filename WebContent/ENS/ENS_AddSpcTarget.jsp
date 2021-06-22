<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.UserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add SPC Target</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
<script type="text/javascript">
		function submitValues(element)
		{	
			if(element.value=="Insert"){
				
				document.formAddNode.action.value="Insert";
				document.formAddNode.submit();	
			}
			else if(element.value=="Report")
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
<body>
<%
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");	

%>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<form action="ENS_SpcTargetServlet" method="get" name="formAddNode" id="formAddNode">		
		
	<div class="container mt-3">
		<div class="row">
			<div class="col-xs-12 text-center pt-1" style="border-radius:20px 20px 0 0; font-weight :bold; font-size:16pt; letter-spacing: 1px;  width: 100%; height:40px; background-color: #a78cc2;">
				Add SPC Target
			</div>
			
		</div>
		<div class="row py-3" style="border: 2px solid #a78cc2;">
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="section" class="inp">
					<span class="label">SPC Area Name*</span>
					<select class="form-control form-control-sm col-md-12"  style="width: 100%;" id="area_nam" name="area_nam" onchange="disForm(this)">	    	    	
			      		<option>--Select--</option>
			      		<option>020 VARIABLE POWER</option>
			      		<option>040 MIXERS</option>
			      		<option>050 EXTRUDERS</option>
			      		<option>060 CALENDERS</option>			      					      
			    	</select>	
				</label>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="section" class="inp">
					<span class="label">Month* </span>
					<select class="form-control form-control-sm col-md-12"  style="width: 100%;" id="month" name="month" onchange="disForm(this)">	    	    	
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

			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Target* &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input type="text" id="nod_nam" name="target" placeholder="&nbsp;" onblur="getEmpDetail()">
				</label>
			</div>
			
			
	</div>
	<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="Insert" id="insert" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0  0 20px; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="Report" id="report" onclick="submitValues(this)" style="  border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius: 0 0 0 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
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
<jsp:include page="/ENS/ENS_Footer.jsp"/>
</body>
</html>