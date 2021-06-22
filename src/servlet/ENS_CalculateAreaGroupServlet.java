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

import beans.ENS_AreaTargetBean;
import beans.ENS_CalAreaGroupBean;
import beans.ENS_CalculateGroupBean;
import beans.UserBean;
import dao.ENS_AddGroupDao;
import dao.ENS_CalculateAreaGroupDao;
import dao.ENS_CalculateGroupDao;
import dao.ENS_DrillReportDao;
import mapper.ENS_AddGroupMapper;
import mapper.ENS_CalculateAreaGroupMapper;
import mapper.ENS_CalculateGroupMapper;
import mapper.ENS_DrillReportMapper;

/**
 * Servlet implementation class ENS_CalculateAreaGroupServlet
 */
@WebServlet("/ENS/ENS_CalculateAreaGroupServlet")
public class ENS_CalculateAreaGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ENS_CalculateAreaGroupServlet() {
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
		
		ENS_CalculateAreaGroupDao cagDao = new ENS_CalculateAreaGroupMapper();
		ENS_DrillReportDao drillDao = new ENS_DrillReportMapper();	
		
		if (action != "" && action.equalsIgnoreCase("drill_report"))
		{
			System.out.println("inside drill report");
			
			String a = request.getParameter("ename");
			System.out.println("Request from Jsp :"+a);
			
			String b = request.getParameter("date1");
			System.out.println("Start Date :"+b);
			
			String c = request.getParameter("date2");
			System.out.println("End Date :"+c);
			
			request.setAttribute("sDate",b);
			request.setAttribute("edate",c);
			
			String [][]arrdata = drillDao.genreportArea(users, request);
			request.setAttribute("Reportuploadno", arrdata);
			
			System.out.println("Array Size in servlet := " + arrdata.length);
			
			forward = "./ENS_DrillAreaReport.jsp";
		}
		else if (action != "" && action.equalsIgnoreCase("FilterReport"))
		{
			System.out.println("inside cal area group report");
			
			String [][] arrdata = cagDao.genereportMaster(users, request);
			request.setAttribute("Reportgroup", arrdata);
			
			String a = request.getParameter("date1");
			String b = request.getParameter("date2");
			System.out.println("Date 1 :"+a);
			System.out.println("Date 2 :"+b);
			
			request.setAttribute("sdate",a);
			request.setAttribute("edate",b);
			
			forward = "./ENS_CalAreaGrpReport.jsp";
			
		}
		else if (action != "" && action.equalsIgnoreCase("List"))
		{
			System.out.println("inside Area report");
			
			forward = "./ENS_AreaReportFilter.jsp";
			
		}
		else if (action != "" && action.equalsIgnoreCase("Filter"))
		{
			System.out.println("inside Area report");
			
			String a = request.getParameter("month");
			System.out.println("Month in report :"+a);
			
			ArrayList<ENS_CalAreaGroupBean> reports = cagDao.genreport(users, request);
			request.setAttribute("areareport", reports);
			
			ArrayList<ENS_AreaTargetBean> reports1 = cagDao.getMonth(users, request);
			request.setAttribute("months", reports1);
			
			for(ENS_CalAreaGroupBean list : reports)
			{
				System.out.println("Area Name :"+list.getAREA_NAM());
				System.out.println("Area Readings :"+list.getTOTAL());
			}
			for(ENS_AreaTargetBean list1 : reports1)
			{
				System.out.println("Target area :"+list1.getAREA_TRAGET());
			}
			
			forward = "./ENS_AreaReport.jsp";
			
		}
		
		else if(action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_CalAreaGrpReportFilter.jsp";
		}
		
		/*else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
		{
			//request.getParameter("section");
			System.out.println("into excel");
			ArrayList<ENS_CalculateGroupBean> reports = calGrp.genreport(users, request);

			request.setAttribute("Reportgrpno", reports);
			forward = "./ENS_GenerateCalGrp.jsp";
		}
		else if (action != "" && action.equalsIgnoreCase("next"))
		{
			forward = "./ENS_GenerateCalGrp.jsp";
		}*/
		
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