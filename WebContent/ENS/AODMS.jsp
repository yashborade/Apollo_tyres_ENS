<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="beans.UserBean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>

<title>Welcome</title>
</head>
<body>
<%
	HttpSession httpSession = request.getSession();
	UserBean users1 = (UserBean) httpSession.getAttribute("Users");
%>
	<jsp:include page="/ENS/ENS_Header.jsp"/>
	<h2 class="text-center" style="font-family:Roboto;"><font color = " #ff3300">Apollo Online Data Mangement System</font></h1>
	<!-- <img src="/EnergyManagementSystem/ENS/images/LOGOS/image3.png" style="width:60%" "> -->
 <%--  <center><img src="images/LOGOS/images1.png" style="width:60%" "></center>

	<jsp:include page="/ENS/ENS_Footer.jsp"/> --%>
	


 	
</body>
</html>