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

import beans.ENS_SpcTargetBean;
import beans.UserBean;
import dao.ENS_AddSpcTagetDao;
import mapper.ENS_AddSpcTargetMapper;

/**
 * Servlet implementation class ENS_SpcTargetServlet
 */
@WebServlet("/ENS/ENS_SpcTargetServlet")
public class ENS_SpcTargetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ENS_SpcTargetServlet() {
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
		
		ENS_AddSpcTagetDao spcDao = new ENS_AddSpcTargetMapper();
		
		
		
		String action = request.getParameter("action");	
		System.out.println("actin"+action);
		
		if(action != "" && action.equalsIgnoreCase("Insert"))
		{
			synchronized (this) {
				
				msg = spcDao.insSpcTraget(request);
				
				if(msg.equals("0"))
				{
					msg = "Error in inserting data";
					
				}
				else
				{
					msg = "Data inserted successfully";
					
				}
				request.setAttribute("msg",msg);
				forward = "./ENS_AddSpcTarget.jsp";
			}
			
		}
		else if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("inside node number report");
			
			
			forward = "./ENS_AddSpcTargetFilter.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("FilterReport"))
		{
			
			System.out.println("Inside Filter Report action");
			
			ArrayList<ENS_SpcTargetBean> reports = spcDao.genereport(request, users);

			request.setAttribute("Reporttarget", reports);
			forward = "./ENS_AddSpcTargetReport.jsp";
			
		}
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_AddSpcTarget.jsp";
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
