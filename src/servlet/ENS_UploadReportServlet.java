package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ENS_AddGroupBean;
import beans.ENS_UploadBean;
import beans.UserBean;
import dao.ENS_UploadDao;
import dao.ENS_UploadReportDao;
import mapper.ENS_UploadReportMapper;

/**
 * Servlet implementation class ENS_UploadReportServlet
 */
@WebServlet("/ENS/ENS_UploadReportServlet")
public class ENS_UploadReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ENS_UploadReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String forward = "";
		String msg = "";
		
		String action = request.getParameter("action");	
		System.out.println("action :"+action);
		ENS_UploadReportDao upload = new ENS_UploadReportMapper();
		
		if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("inside report");
			
			
			forward = "./ENS_UploadFileReportFilter.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("FilterReport"))
		{
			System.out.println("Inside Filter Report action");
			
			
			ArrayList<ENS_UploadBean> reports = upload.genreport(users, request);

			request.setAttribute("Reportuploadno", reports);
			forward = "./ENS_UploadFileReport.jsp";
		
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_UploadFile.jsp";
		}
		else if (action != "" && action.equalsIgnoreCase("Edit"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_EditReadingsFilter.jsp";
		}
		else if(action != "" && action.equalsIgnoreCase("Modify"))
		{
			System.out.println("inside modify");
			
			String a=request.getParameter("mach_nam");
			System.out.println("inside modify mach_nam :"+a);
			
			String b=request.getParameter("nod_nam");
			System.out.println("inside modify mach_nam :"+b);
			
			String c=request.getParameter("date1");
			System.out.println("inside modify mach_nam :"+c);
			
			ENS_UploadBean uploadBean = null;
			uploadBean = upload.getReadings(users, request);
			request.setAttribute("uploadread", uploadBean);
			
			request.setAttribute("msg", msg);
			forward = "./ENS_EditReadings.jsp";
			
		}
		
		else if(action != "" && action.equalsIgnoreCase("Update"))
		{
			synchronized (this) 
			{	
				msg = upload.editData(request);
				
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Data Updated Successfully.";
					
				}
				request.setAttribute("msg", msg);
				
				forward = "./ENS_EditReadingsFilter.jsp";
			}
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
		{
			//request.getParameter("section");
			System.out.println("into excel");
			ArrayList<ENS_UploadBean> reports = upload.genreport(users, request);

			request.setAttribute("Reportuploadno", reports);
			forward = "./ENS_GenerateUploadExcel.jsp";
		}
		else if (action != "" && action.equalsIgnoreCase("next"))
		{
			forward = "./ENS_GenerateUploadExcel.jsp";
		}
		
		else {
			forward = "./ENS_Welcome.jsp";
		}
		
		RequestDispatcher dispatchForward = request.getRequestDispatcher(forward);
		dispatchForward.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
