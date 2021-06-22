<%@page import="beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Mail</title>
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
</head>
<script type="text/javascript">
    function selectAll() {
        var items = document.getElementsByName('to');
        for (var i = 0; i < items.length; i++) {
            if (items[i].type == 'checkbox')
                items[i].checked = true;
        }
    }

    function UnSelectAll() {
        var items = document.getElementsByName('to');
        for (var i = 0; i < items.length; i++) {
            if (items[i].type == 'checkbox')
                items[i].checked = false;
        }
    }			
</script>
<script type="text/javascript">
function checkfile(sender) {
    var validExts = new Array(".png",".jpg",".jpeg");
    var fileExt = sender.value;
    fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
    if (validExts.indexOf(fileExt) < 0) {
      alert("Invalid file selected, valid files are of " +
               validExts.toString() + " types.");
      return false;
    }
    else 
    {
            return true;
    }        
}
</script>
<script type="text/javascript">
		function submitValues(element)
		{	
					element.value == "Upload"
					//alert("Element is "+element.value);
					document.getElementById("action").innerHTML="";
					//alert("action"+document.getElementById("action").value);
					document.fupForm.action.value="Upload";
					document.fupForm.action = "Mail_AddFromFileServlet"; 
					document.fupForm.submit();
		}
			
</script>
<%
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");	

%>

<%
	List<String> mails = null;
	if(request.getAttribute("email")!= null)
	{
		mails = (List<String>)request.getAttribute("email");
	}
	else
	{
		mails = new ArrayList<String>();
	}
	System.out.println("emails size in jsp :"+mails.size());
%>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<form action="Mail_ExcelData" method="post" name="fupForm" enctype="multipart/form-data">
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
					<input type="text " value="<%=users.getEmail()%>" id="from" name="from" placeholder="&nbsp;" readonly="readonly" style=" width : 237px;">
					<span class="border"></span>
				</label>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				 <label for="nod_nam" class="inp">
				<span class="label">To &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					
					<%for(String val : mails){ %>
						<tr>
							<br>
							<td><input type="checkbox" name="to" value="<%=val%>"></td>
							<td><%=val%></td>
						</tr>	
					<%} %>
					<p>
					<input type="button" onclick='selectAll()' value="Select All"/>
					<input type="button" onclick='UnSelectAll()' value="Unselect All"/>
					</p>
				</label>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Subject &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input type="text" id="sub" name="sub" placeholder="&nbsp;" style=" width : 251px;">
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
				<span class="label">Select Image </span>
					<input type="file" id="filepr" name="filepr" placeholder="&nbsp;" onblur="getEmpDetail()" style=" width : 278px;">
				</label>
				</div>
			</div>
		</div>
	</form>
			<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="Send" id="Upload" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0  0 20px; background-color: #e94e0f; color:white; font-size: 14pt; " />
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
</body>

</html>