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

import beans.ENS_AddGroupBean;
import beans.ENS_AddNodBean;
import beans.ENS_CalculateGroupBean;
import beans.UserBean;
import dao.ENS_AddGroupDao;
import dao.ENS_AddNode;
import dao.ENS_CalculateGroupDao;
import dao.ENS_DrillReportDao;
import mapper.ENS_AddGroupMapper;
import mapper.ENS_AddNodeMapper;
import mapper.ENS_CalculateGroupMapper;
import mapper.ENS_DrillReportMapper;

/**
 * Servlet implementation class ENS_CalculateGroupServlet
 */
@WebServlet("/ENS/ENS_CalculateGroupServlet")
public class ENS_CalculateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ENS_CalculateGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String forward = "";
		String msg = "";
		
		String action = request.getParameter("action");	
		System.out.println("actin"+action);
		ENS_CalculateGroupDao calGrp = new ENS_CalculateGroupMapper(); 	
		ENS_AddGroupDao addGrp = new ENS_AddGroupMapper();
		
		
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
			
			//System.out.println("into excel");
			
			request.setAttribute("sDate",b);
			request.setAttribute("edate",c);
			
			String [][]arrdata = drillDao.genreportMaster(users, request);
			request.setAttribute("Reportuploadno", arrdata);
			
			System.out.println("Array Size in servlet := " + arrdata.length);
			
			forward = "./ENS_DrillReport.jsp";
		}
			
		else if (action != "" && action.equalsIgnoreCase("FilterReport"))
		{
			System.out.println("inside cal group report");
			
			String [][] arrdata = calGrp.genereportMaster(users, request);
			request.setAttribute("Reportgroup", arrdata);
			
			String a = request.getParameter("date1");
			String b = request.getParameter("date2");
			System.out.println("Date 1 :"+a);
			System.out.println("Date 2 :"+b);
			
			request.setAttribute("sdate",a);
			request.setAttribute("edate",b);
			
			/*ArrayList<ENS_CalculateGroupBean> reports = calGrp.genreport(users, request);

			request.setAttribute("Reportgrpno", reports);*/
			
			forward = "./ENS_CalGroupReport.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_CalReportFilter.jsp";
		}
		else if(action != "" && action.equalsIgnoreCase("Calculate"))
		{
			System.out.println("into Calculate");
			msg = calGrp.insAreagroup(users,request);
			
			forward = "./ENS_CalculateAreaGroup.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
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
