<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.UserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title>AODMS</title>
</head>
<script type="text/javascript">
		function submitValues(element)
		{	
			if(element.value=="Login"){

				//alert("can"+element.value);
				document.formAddNode.action.value="Login";
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
<form action="AODMS_LoginServlet" method="get" name="formAddNode" id="formAddNode">		
				
			
				<label>
				<span>User_ID :</span>
					<input type="text" id="user" name="user">
				</label>
				<br>
				
				<label>
				<span>Password :</span>
					<input type="password" id="pass" name="pass">
				</label>
				<br>
				
				<label>
					<span>Section </span>
					<select style="width: 100%;" id="section" name="section" onchange="disForm(this)">	    	    	
			      		<option>--Select--</option>
			      		<option>Bias</option>
			      		<option>Radial</option>
			      		<option>OTR</option>
			      		<option>MCR</option>
			      		<option>MCB</option>				      					      
			    	</select>
				</label>
				<br>
				
				<label>
					<span>Financial Year </span>
					<select style="width: 100%;" id="finyr" name="finyr" onchange="disForm(this)">	    	    	
			      		<option>--Select--</option>
			      		<option>2020</option>
			      		<option>2021</option>
			      		<option>2022</option>
			      		<option>2023</option>
			      		<option>2024</option>				      					      
			    	</select>
				</label>
				<br>
			
				
				<input type="button" value="Login" id="Login" onclick="submitValues(this)" />
				<input type="button" value="Cancel" id="Cancel" onclick="submitValues(this)"/>
			</form>
	</body>

</html>