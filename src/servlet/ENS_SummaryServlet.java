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

import beans.ENS_CalculateGroupBean;
import beans.ENS_SummaryBean;
import beans.ENS_UploadBean;
import beans.UserBean;
import dao.ENS_CalculateGroupDao;
import dao.ENS_SummaryDao;
import dao.ENS_UploadReportDao;
import mapper.ENS_CalculateGroupMapper;
import mapper.ENS_SummaryReportMapper;
import mapper.ENS_UploadReportMapper;

/**
 * Servlet implementation class ENS_SummaryServlet
 */
@WebServlet("/ENS/ENS_SummaryServlet")
public class ENS_SummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ENS_SummaryServlet() {
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
		ENS_SummaryDao summary = new ENS_SummaryReportMapper();
		ENS_CalculateGroupDao calDao = new ENS_CalculateGroupMapper();
		ENS_UploadReportDao da = new ENS_UploadReportMapper();
		//ENS_UploadReportDao upload = new ENS_UploadReportMapper();
	
		if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("Inside Report action");
			
			
			ArrayList<ENS_SummaryBean> reports = summary.genreport(users, request);

			request.setAttribute("Reportuploadno", reports);
			forward = "./ENS_SummaryReport.jsp";
			
		}
		else if (action != "" && action.equalsIgnoreCase("rep"))
		{
			System.out.println("Inside Report 1 action");
			
			
			ArrayList<ENS_CalculateGroupBean> reports1 = calDao.genreport(users, request);
			request.setAttribute("Reportgrpno", reports1);
			
			forward = "./ENS_CalReportFilter.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Calculate"))
		{
			System.out.println("into Calculate");
			
			
			synchronized (this) {
				
				msg = summary.insGroup(users, request);
			
				if(msg.equals("0"))
				{
					msg = "Error in inserting data";
				
				}
				else
				{
					msg = "Data inserted successfully";
				
				}
			}
			request.setAttribute("msg",msg);
			forward = "./ENS_CalculateGroup.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_Summary.jsp";
		}
		
		else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
		{
			//request.getParameter("section");
			System.out.println("into excel");
			ArrayList<ENS_SummaryBean> reports = summary.genreport(users, request);

			request.setAttribute("Reportuploadno", reports);
			forward = "./ENS_GenerateSummaryExcel.jsp";
		}
		
		else if (action != "" && action.equalsIgnoreCase("next"))
		{
			forward = "./ENS_GenerateSummaryExcel.jsp";
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
