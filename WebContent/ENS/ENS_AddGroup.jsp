<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.UserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title> Add Group</title>

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
			if(element.value=="Insert"){

				if (document.getElementById("eqp_nam").value=="")
				{
					document.getElementById("errorMsg").innerHTML="Please Enter Equipment Name.";
				}
				else if (document.getElementById("eqp_nam").value!="")
				{
					document.getElementById("errorMsg").innerHTML="";
					document.formAddGroup.action.value="Insert";
					document.formAddGroup.submit();
				}
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
			else if(element.value=="Edit")
			{
				//alert("rep"+element.value);
				document.formAddGroup.action.value="Edit";
				//alert("action"+document.getElementById("action").value);
				document.formAddGroup.submit();
			}
			else if(element.value=="Delete")
			{
				//alert("rep"+element.value);
				document.formAddGroup.action.value="Delete";
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

		<script type="text/javascript">
	$(function() {   
	    function split( val ) {
	      return val.split(/,s*/);
	    }
	    function extractLast( term ) {
	      return split( term ).pop();
	    }
	 
	    $("#search,#search1")
	      .autocomplete({
	        minLength: 0,
	        source: function( request, response ) {
	        	$.getJSON('ENS_HelpServlet?field=search_mach',{
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
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<form action="ENS_AddGroupServlet" method="get" name="formAddGroup" id="formAddGroup">

		<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="Insert" id="Insert" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0  0 20px; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="Report" id="report" onclick="submitValues(this)" style="  border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius: 0 0 0 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="Edit" id="Edit" onclick="submitValues(this)" style="  border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius: 0 0 0 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="Delete" id="Delete" onclick="submitValues(this)" style="  border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius: 0 0 0 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
				<input class="my-3 py-2" type="button" value="cancel" id="cancel" onclick="submitValues(this)" style="  border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius: 0 20px 20px 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
			</div>		
		
		<div class="container mt-3">
		<div class="row">
			<div class="col-xs-12 text-center pt-1" style="border-radius:20px 20px 0 0; font-weight :bold; font-size:16pt; letter-spacing: 1px;  width: 100%; height:40px; background-color: #a78cc2;">
				Add Group
			</div>
			
			<table>
    		<tr>
    		<tr>
    		<th>Equipment Name*</font> <input type="text" name="eqp_nam" id="eqp_nam" placeholder="&nbsp;" style="height: 50px; width: 1135px"/></p></th>
			<script>
				$(document).ready(function() {
					$("#eqp_nam").autocomplete({
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
							url : "ENS_HelpServlet?field=search_group",
							data : request,
							dataType : "json",
							success : function(data, textStatus, jqXHR) {
								//alert("success");
								console.log(data);
								response(data);
							},
							error : function(jqXHR, textStatus, errorThrown) {
								//alert("error");
								console.log(textStatus);
							}
						});
					}
				});
			});
		</script>
    		</tr>
    		<%-- <th>Groups Add</font> <input type="text" name="groupadd" id="groupadd" value= "<%=groupData.getNAM_PLUS() %>" placeholder="Enter Add Group "  readonly/></p></th>
			<th>Groups Subtract</font> <input type="text" name="groupminus" id="groupminus" value= "<%=groupData.getNAM_MINUS() %>" placeholder="Enter Subtract Group" readonly/></p></th>   --%> 
			<tr>    
    		<th>Fidder Name Addition*</font> <input type="text" name="search" id="search"  placeholder="&nbsp;" style="height: 50px; width: 1135px"/></p></th>
    		</tr>
    		<th>Fidder Name Subtraction*</font> <input type="text" name="search1" id="search1"  placeholder="&nbsp;" style="height: 50px; width:1135px" /></p></th>
			</tr>
			</table>
			 <h3><font color="red"><h3>* Fields are Mandatory</h3></font></h3> 
		</div>
	
		
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