package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ENS_AddMachBean;
import beans.ENS_SummaryBean;
import beans.ENS_UploadBean;
import beans.UserBean;
import dao.ENS_AddMachDao;
import dao.ENS_SummaryDao;
import mapper.ENS_AddMachMapper;
import mapper.ENS_SummaryReportMapper;

/**
 * Servlet implementation class ENS_SummaryMasterReport
 */
@WebServlet("/ENS/ENS_SummaryMasterReportServlet")
public class ENS_SummaryMasterReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ENS_SummaryMasterReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String forward = "";
		String msg = "";
		
		String action = request.getParameter("action");	
		System.out.println("action :"+action);
		ENS_SummaryDao summary = new ENS_SummaryReportMapper();
		
		ENS_AddMachDao summary1 = new ENS_AddMachMapper();
		
		if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("Inside Report action");
			
			String[][] arrData = summary.genreportMaster(users, request);
			
			String d1 = request.getParameter("date1");
			String d2 = request.getParameter("date2");
			
			
			System.out.println("Array Size = " + arrData.length);

			request.setAttribute("sDate", d1);
			request.setAttribute("eDate", d2);
			
			request.setAttribute("Reportuploadno", arrData);
			
			
			ArrayList<ENS_AddMachBean> rprt = summary1.genreport(users, request);
			
			request.setAttribute("MachnData", rprt);
			
			forward = "./ENS_SummaryMasterReport.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_SummaryMaster.jsp";
		}
		
		else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
		{
			System.out.println("Inside Report action");
			
			String[][] arrData = summary.genreportMaster(users, request);
			
			String d1 = request.getParameter("date1");
			String d2 = request.getParameter("date2");
			
			
			System.out.println("Array Size = " + arrData.length);

			request.setAttribute("sDate", d1);
			request.setAttribute("eDate", d2);
			
			request.setAttribute("Reportuploadno", arrData);
			
			forward = "./ENS_GenerateSummaryMasterExcel.jsp";
		}
		else if (action != "" && action.equalsIgnoreCase("Edit"))
		{
			request.setAttribute("msg", msg);
			forward = "./ENS_EditSummaryFilter.jsp";
		}
		else if(action != "" && action.equalsIgnoreCase("Modify"))
		{
			System.out.println("inside modify");
			
			String b=request.getParameter("nodnam");
			System.out.println("inside modify nodnam :"+b);
			
			String c=request.getParameter("date1");
			System.out.println("inside modify mach_nam :"+c);
	
			ENS_SummaryBean sumbean = null;
			sumbean = summary.getReadings(users, request);
			request.setAttribute("sumread", sumbean);
			
			request.setAttribute("msg", msg);
			forward = "./ENS_EditSummary.jsp";
			
		}
		
		else if(action != "" && action.equalsIgnoreCase("Update"))
		{
			synchronized (this) 
			{	
				msg = summary.editData(request);
				
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Data Updated Successfully.";
					
				}
				request.setAttribute("msg", msg);
				
				forward = "./ENS_EditSummaryFilter.jsp";
			}
			
		}
		else if (action != "" && action.equalsIgnoreCase("next"))
		{
			forward = "./ENS_GenerateSummaryMasterExcel.jsp";
		}
		
		else
		{
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
