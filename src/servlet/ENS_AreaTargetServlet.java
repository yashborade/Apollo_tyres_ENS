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
import beans.ENS_SpcTargetBean;
import beans.UserBean;
import dao.ENS_AddAreaTargetDao;
import mapper.ENS_AddAreaTargetMapper;

/**
 * Servlet implementation class ENS_AreaTargetServlet
 */
@WebServlet("/ENS/ENS_AreaTargetServlet")
public class ENS_AreaTargetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ENS_AreaTargetServlet() {
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
		
		ENS_AddAreaTargetDao areaDao = new ENS_AddAreaTargetMapper();
		
		String action = request.getParameter("action");	
		System.out.println("actin"+action);
		
		if(action != "" && action.equalsIgnoreCase("Insert"))
		{
			synchronized (this) {
				
				msg = areaDao.insAreaTarget(request);
				
				if(msg.equals("0"))
				{
					msg = "Error in inserting data";
					
				}
				else
				{
					msg = "Data inserted successfully";
					
				}
				request.setAttribute("msg",msg);
				forward = "./ENS_AddAreaTarget.jsp";
			}
			
		}
		else if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("inside report");
			
			
			forward = "./ENS_AreaTargetReportFilter.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("FilterReport"))
		{
			
			System.out.println("Inside Filter Report action");
			
			ArrayList<ENS_AreaTargetBean> reports = areaDao.genereport(request, users);

			request.setAttribute("Reporttarget", reports);
			forward = "./ENS_AreaTargetReport.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_AddAreaTarget.jsp";
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
