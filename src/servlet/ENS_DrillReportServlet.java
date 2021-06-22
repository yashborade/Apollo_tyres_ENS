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
import beans.UserBean;
import dao.ENS_AddGroupDao;
import dao.ENS_DrillReportDao;
import mapper.ENS_AddGroupMapper;
import mapper.ENS_DrillReportMapper;

/**
 * Servlet implementation class ENS_DrillReportServlet
 */
@WebServlet("/ENS/ENS_DrillReportServlet")
public class ENS_DrillReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ENS_DrillReportServlet() {
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
		
		ENS_DrillReportDao drillDao = new ENS_DrillReportMapper();		
		if (action != "" && action.equalsIgnoreCase("load"))
		{
			System.out.println("inside drill report");
			
			String a = request.getParameter("ename");
			System.out.println("Request from Jsp :"+a);
			
			System.out.println("into excel");
			ArrayList<ENS_AddGroupBean> reports = drillDao.genereport(request, users);

			request.setAttribute("Reportnodeno", reports);
			
			
			forward = "./ENS_DrillReport.jsp";
			
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
