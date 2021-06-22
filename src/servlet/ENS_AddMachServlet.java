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

import org.hibernate.Query;

import beans.ENS_AddMachBean;
import beans.ENS_AddNodBean;
import beans.UserBean;
import dao.ENS_AddMachDao;
import mapper.ENS_AddMachMapper;

/**
 * Servlet implementation class ENS_AddMachServlet
 */
@WebServlet("/ENS/ENS_AddMachServlet")
public class ENS_AddMachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ENS_AddMachServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String forward = "";
		String msg = "";
		
		String action = request.getParameter("action");
		System.out.println("actin"+action);
		ENS_AddMachDao addMach = new ENS_AddMachMapper();
		
		if(action != "" && action.equalsIgnoreCase("Insert"))
		{
			synchronized (this) {
				
				msg = addMach.insMachine(request);
				
				if(msg.equals("0"))
				{
					msg = "Error in inserting data";
					
				}
				else
				{
					msg = "Data inserted successfully";
					
				}
				request.setAttribute("msg",msg);
				forward = "./ENS_AddMachine.jsp";
			}
			
		}
		else if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("inside machine number report");
			
			forward = "./ENS_MachNoReportFilter.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("FilterReport"))
		{
			System.out.println("Inside Filter Report action");
			
			ArrayList<ENS_AddMachBean> reports = addMach.genreport(users, request);

			request.setAttribute("Reportmachno", reports);
			forward = "./ENS_MachReport.jsp";
			
		}
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_AddMachine.jsp";
		}
		else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
		{
			System.out.println("into excel");
			ArrayList<ENS_AddMachBean> reports = addMach.genreport(users, request);

			request.setAttribute("Reportmachno", reports);
			forward = "./ENS_GenerateMachExcel.jsp";
		}
		else
		{
			forward = "./ENS_home.jsp";
		}
		
		RequestDispatcher dispatchForward = request.getRequestDispatcher(forward);
		dispatchForward.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
