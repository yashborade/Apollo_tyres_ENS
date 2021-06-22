<%@page import="beans.UserBean"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/ENS/ENS_CSS_Container.jsp" %> 
<%@ include file="/ENS/ENS_JS_Container.jsp"  %>
<title>Upload File</title>
</head>
<script type="text/javascript">
function checkfile(sender) {
    var validExts = new Array(".xlsx",".xls");
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
			if(element.value == "Upload")
			{
				if (document.getElementById("section1").value=="--Select--")
				{
					document.getElementById("errorMsg").innerHTML="Please Select Section Name.";
				}

				else if (document.getElementById("section1").value!="" )
				{
					document.getElementById("errorMsg").innerHTML="";
					document.fupForm.action.value="Upload";
					document.fupForm.action = "ENS_UploadFileServlet"; 
					document.fupForm.submit();
				}
			//element.value=="Upload"
			//alert("upl"+element.value);
			
			//alert("action"+document.getElementById("action").value);
			//document.formAddMach.submit();
			//alert("Element is "+element.value);			
			//document.fupForm.action.value="upload";
			
			//alert("is "+document.fupForm.action.value);
			
			}
			else if(element.value == "Report")
			{
				element.value=="Report";
				//alert("rep"+element.value);
				document.formAddNode.action.value="Report";
				//alert("action"+document.getElementById("action").value);
				document.formAddNode.submit();
				
			}	
		}
</script>
<body>
<jsp:include page="/ENS/ENS_Header.jsp"/>
<div>
<form action="ENS_ExcelData" method="post" name="fupForm" enctype="multipart/form-data">		
		
	<div class="container mt-3">
		<div class="row">
			<div class="col-xs-12 text-center pt-1" style="border-radius:20px 20px 0 0; font-weight :bold; font-size:16pt; letter-spacing: 1px;  width: 100%; height:40px; background-color: #a78cc2;">
				Upload File
			</div>
			
		</div>
		<div class="row py-3" style="border: 2px solid #a78cc2;">
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-5 my-1 align-bottom">
				<label for="section" class="inp">
					<span class="label">Section </span>
					<select class="form-control form-control-sm col-md-12"  style="width: 100%;" id="section1" name="section1" onchange="disForm(this)">	    	    	
			      		<option>--Select--</option>
			      		<option>Bias</option>
			      		<option>Radial</option>
			      		<option>OTR</option>
			      		<option>MCR</option>
			      		<option>MCB</option>			      					      
			    	</select>
<!-- 			    	<span class="border"></span>	 -->
				</label>
			</div>
			<div class="col-lg-4 col-md-5 col-sm-6 col-xs-7 my-1 align-bottom">
				<label for="nod_nam" class="inp">
				<span class="label">Date :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
					<input type="date" id="date" name="FinalDate" placeholder="&nbsp;" onblur="getEmpDetail()" style=" width : 273px;">
					
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
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<input class="my-3 py-2" type="button" value="Upload" id="upload" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0 20px 0; background-color: #e94e0f; color:white; font-size: 14pt; " />
			</div>		  
		</div>
		</form>
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
		
	<form action="ENS_UploadReportServlet" method="get" name="formAddNode">		
	<div class="row">
			<div class="col-md-12 text-center">
				<input type="hidden" value="" name="action" id="action" />
				<!--  <input class="my-3 py-2" type="button" value="Report" id="Report" onclick="submitValues(this)" style=" border:2px solid black; letter-spacing: 2px; font-weight:300; border-radius:20px 0 20px 0; background-color: #e94e0f; color:white; font-size: 14pt; " />-->
			</div>		  
		</div>
	</form>

</body>
</html>