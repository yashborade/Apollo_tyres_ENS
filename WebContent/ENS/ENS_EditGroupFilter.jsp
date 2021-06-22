<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filter group</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<script type="text/javascript">
		function submitValues(element)
		{	
			if(element.value=="Update"){
					//alert("ins"+element.value);
					document.formEdit.action.value="Update";
					//alert("action"+document.getElementById("action").value);
					document.formEdit.submit();
			}
			else if(element.value=="cancel")
			{
				//alert("can"+element.value);
				document.formEdit.action.value="cancel";
				//alert("action"+document.getElementById("action").value);
				document.formEdit.submit();

			}
		}
</script>
<script type="text/javascript">
	$(function() {   
	    function split( val ) {
	      return val.split( /,\s*/ );
	    }
	    function extractLast( term ) {
	      return split( term ).pop();
	    }
	 
	    $("#fiddadd,#fiddsub")
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
</head>
<body>
<%
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");	

%>
<%
	ENS_AddGroupBean groupBean = null;
	groupBean = (ENS_AddGroupBean) request.getAttribute("groupupdate");
	
	System.out.println("group Data :"+groupBean);
	
	ENS_AddGroupBean groupData = null;
	if(request.getAttribute("groupupdate") != null)
	{
		System.out.println("into groupdata");
		groupData = (ENS_AddGroupBean) request.getAttribute("groupupdate");
		//System.ot.println("sz"+ftData.size());
	}
	else
	{
		groupData = new ENS_AddGroupBean();
		//out.println("no records to display...");	
	} 
%>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<form action="ENS_AddGroupServlet" method="get" name="formEdit">		
		
	<div class="container mt-3">
		<div class="row">
			<div class="col-xs-12 text-center pt-1" style="border-radius:20px 20px 0 0; font-weight :bold; font-size:16pt; letter-spacing: 1px;  width: 100%; height:40px; background-color: #a78cc2;">
				Update Group
			</div>
			<table>
    		<tr>
    		<tr>
    		<th>Equipment Name :-</font> <input type="text" name="equip_nam" id="equip_nam" value= "<%=groupData.getEQUIP_NAM() %>" placeholder="Equipment " style="height: 50px; width: 1135px"/></p></th>
    		</tr>
    		<%-- <th>Groups Add</font> <input type="text" name="groupadd" id="groupadd" value= "<%=groupData.getNAM_PLUS() %>" placeholder="Enter Add Group "  readonly/></p></th>
			<th>Groups Subtract</font> <input type="text" name="groupminus" id="groupminus" value= "<%=groupData.getNAM_MINUS() %>" placeholder="Enter Subtract Group" readonly/></p></th>   --%> 
			<tr>    
    		<th>Fidder Name Addition :- </font> <input type="text" name="fiddadd" id="fiddadd" value= "<%=groupData.getFIDD_NAM_PLUS() %>" placeholder="Fidder Added " style="height: 50px; width: 1135px"/></p></th>
    		</tr>
    		<th>Fidder Name Subtraction :-</font> <input type="text" name="fiddsub" id="fiddsub" value= "<%=groupData.getFIDD_NAM_MINUS() %>" placeholder="Fidder Subtract" style="height: 50px; width:1135px" /></p></th>
			</tr>
			</table>
	</div>
</div>
	<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="Update" id="Update" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0  0 20px; background-color: #e94e0f; color:white; font-size: 14pt; " />
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
</body>
</html>