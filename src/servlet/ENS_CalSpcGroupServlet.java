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

import beans.ENS_AddSpcBean;
import beans.ENS_CalSpcGroupBean;
import beans.ENS_SpcTargetBean;
import beans.UserBean;
import dao.ENS_CalSpcGroupDao;
import dao.ENS_DrillReportDao;
import mapper.ENS_CalSpcGroupMapper;
import mapper.ENS_DrillReportMapper;

/**
 * Servlet implementation class ENS_CalSpcGroupServlet
 */
@WebServlet("/ENS/ENS_CalSpcGroupServlet")
public class ENS_CalSpcGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ENS_CalSpcGroupServlet() {
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
		System.out.println("actin"+action);
		
		ENS_CalSpcGroupDao spcDao = new ENS_CalSpcGroupMapper();
		
		ENS_DrillReportDao drillDao = new ENS_DrillReportMapper();		
		if (action != "" && action.equalsIgnoreCase("drill_report"))
		{
			System.out.println("inside SPC drill report");
			
			String a = request.getParameter("ename");
			System.out.println("Request from Jsp :"+a);
			
			String b = request.getParameter("date1");
			System.out.println("Start Date :"+b);
			
			String c = request.getParameter("date2");
			System.out.println("End Date :"+c);
			
			//System.out.println("into excel");
			
			request.setAttribute("sDate",b);
			request.setAttribute("edate",c);
			
			String [][]arrdata = drillDao.genreportSpc(users, request);
			request.setAttribute("Reportuploadno", arrdata);
			
			System.out.println("Array Size in servlet := " + arrdata.length);
			
			forward = "./ENS_DrillSpcReport.jsp";
		}
		else if(action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_CalSpcGroup.jsp";
		}
		else if(action != "" && action.equalsIgnoreCase("Report"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_CalculateSpcReportFilter.jsp";
		}
		
		
		else if(action != "" && action.equalsIgnoreCase("Calculate"))
		{
			System.out.println("into Calculate");
			synchronized (this) {
				
				msg = spcDao.insData(request);
				
				if(msg.equals(0))
				{
					msg = "error in inserting data"; 				
				}
				else
				{
					msg = "Data inserted successfully";
				}
				
				request.setAttribute("msg", msg);
				forward = "./ENS_CalSpcGroup.jsp";
				
			}
		}
		else if (action != "" && action.equalsIgnoreCase("FilterReport"))
		{
			System.out.println("inside SPC group report");
			
			String [][] arrdata = spcDao.genereportMaster(users, request);
			request.setAttribute("Reportspc", arrdata);
			
			String a = request.getParameter("date1");
			String b = request.getParameter("date2");
			System.out.println("Date 1 :"+a);
			System.out.println("Date 2 :"+b);
			
			request.setAttribute("sdate",a);
			request.setAttribute("edate",b);
			
			ArrayList<ENS_CalSpcGroupBean> reports = spcDao.genreport(users, request);

			request.setAttribute("Reportgrpno", reports);
			
			forward = "./ENS_CalculateSpcReport.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("List"))
		{
			System.out.println("Inside Calculate Spc Report action");
			
			forward = "./ENS_SpcReportFilter.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Filter"))
		{
			System.out.println("Inside Spc Report");
			
			String a = request.getParameter("date1");
			
			System.out.println("Date in filter :"+a);
			
			ArrayList<ENS_CalSpcGroupBean> reports = spcDao.genreport(users, request);
			
			request.setAttribute("Reportspc", reports);
			
			ArrayList<ENS_SpcTargetBean> reports1 = spcDao.getMonth(users, request);
			
			request.setAttribute("reportmonth", reports1);
			
			for(ENS_CalSpcGroupBean list : reports)
			{
				System.out.println("Area name :"+list.getAREA_NAME());
				System.out.println("Total SPC Readings :"+list.getTOTAL_SPC());
			}
			
			for(ENS_SpcTargetBean list1 : reports1)
			{
				System.out.println("Target SPC :"+list1.getSPC_TARGET());
			}
			
			forward = "./ENS_SpcReport.jsp";
			
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
