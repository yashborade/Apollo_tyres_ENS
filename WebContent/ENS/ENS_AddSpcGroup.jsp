<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.UserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title> Add SPC Group</title>

<!-- CSS AND JS files for multiselect -->
<link href="assets/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="assets/css/normalize.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/ui-lightness/jquery-ui.css"/>
<link rel="stylesheet" href="assets/css/main.css">
<script src="assets/js/vendor/modernizr-2.6.2.min.js"></script>
<!-- END -->

</head>
<script type="text/javascript">
		function submitValues(element)
		{	
			if(element.value=="Insert")
			{
					document.formAddGroup.action.value="Insert";
					document.formAddGroup.submit();
				
					//alert("ins"+element.value);
					
					//alert("String = "+document.getElementById("search").value);
					//alert("action"+document.getElementById("action").value);
					
			}
			else if(element.value=="Report")
			{
				//alert("rep"+element.value);
				document.formAddGroup.action.value="Report";
				//alert("action"+document.getElementById("action").value);
				document.formAddGroup.submit();
			}
			else if(element.value=="Modify")
			{
				//alert("rep"+element.value);
				document.formAddGroup.action.value="Modify";
				//alert("action"+document.getElementById("action").value);
				document.formAddGroup.submit();
			}
			else if(element.value=="cancel")
			{
				//alert("can"+element.value);
				document.formAddGroup.action.value="cancel";
				//alert("action"+document.getElementById("action").value);
				document.formAddGroup.submit();
			}
		}
</script>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<form action="ENS_AddSpcGroupServlet" method="get" name="formAddGroup" id="formAddGroup">		
		
		<div class="container mt-3">
		<div class="row">
			<div class="col-xs-12 text-center pt-1" style="border-radius:20px 20px 0 0; font-weight :bold; font-size:16pt; letter-spacing: 1px;  width: 100%; height:40px; background-color: #a78cc2;">
				Add SPC Group
			</div>
		</div>
		<div class="row py-3" style="border: 2px solid #a78cc2;">
			<tr>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Areas to be Added* </span>
					<input type="text" id="search" name="search" placeholder="&nbsp;">
					
					<!-- CSS AND JS files for multiselect -->
					<script src="assets/js/jquery.min.js"></script> 
					<script src="assets/js/jquery-ui.min.js"></script>
					<script src="assets/js/jquery.autocomplete.multiselect.js"></script>
					<!-- END --> 
					
					<span class="border"></span>
				</label>
			</div>
			</tr>
	<script src="assets/js/jquery.min.js"></script> 
	<script src="assets/js/jquery-ui.min.js"></script>
	<script src="assets/js/jquery.autocomplete.multiselect.js"></script>
	
	<script type="text/javascript">
	$(function() {   
	    function split( val ) {
	      return val.split(/,s*/);
	    }
	    function extractLast( term ) {
	      return split( term ).pop();
	    }
	 
	    $("#search")
	      .autocomplete({
	        minLength: 0,
	        source: function( request, response ) {
	        	$.getJSON('ENS_HelpServlet?field=search_area',{
					term: extractLast(request.term)
					}, response);
	        },
	        focus: function() {
	          return false;
	        },
	        select: function( event, ui ) {
	          var terms = split( this.value );
	          // remove the current input
	          terms.pop();
	          // add the selected item
	          terms.push( ui.item.value );
	          // add placeholder to get the comma-and-space at the end
	          terms.push( "" );
	          this.value = terms.join( "," );
	          return false;
	        }
	      });
	  });
	  </script>
	  </div>
	  <font color="red"><h2>* Fields are Mandatory</h2></font>
	  </div>
	  
	
	<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="Insert" id="Insert" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0  0 20px; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="Report" id="report" onclick="submitValues(this)" style="  border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius: 0 0 0 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="Modify" id="Modify" onclick="submitValues(this)" style="  border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius: 0 0 0 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
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
			

</form>
<jsp:include page="/ENS/ENS_Footer.jsp"/>
</body>
</html>