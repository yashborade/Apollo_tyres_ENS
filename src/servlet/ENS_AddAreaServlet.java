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

import beans.ENS_AddAreaBean;
import beans.ENS_AddEquipBean;
import beans.ENS_AddGroupBean;
import beans.UserBean;
import dao.ENS_AddAreaDao;
import dao.ENS_AddEquipDao;
import mapper.ENS_AddAreaMapper;
import mapper.ENS_AddEquipMapper;

/**
 * Servlet implementation class ENS_AddAreaServlet
 */
@WebServlet("/ENS/ENS_AddAreaServlet")
public class ENS_AddAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ENS_AddAreaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String forward = "";
		String msg = "";
		
		
		
		String action = request.getParameter("action");	
		System.out.println("action"+action);
		
		ENS_AddAreaDao addArea = new ENS_AddAreaMapper();
		
		if (action != "" && action.equalsIgnoreCase("Insert")){
			
			synchronized (this) 
			{	
				msg = addArea.insArea(request);
			
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Data Inserted Successfully.";
					
				}
				request.setAttribute("msg", msg);
				forward = "./ENS_AddArea.jsp";
			}
			
		}
		
		
		else if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("inside Area report");

			ArrayList<ENS_AddAreaBean> reports = addArea.genreport(users, request);
			
			request.setAttribute("Reportarea", reports);
			
			forward = "./ENS_AddAreaReport.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		 {
			request.setAttribute("msg", "");
			forward = "./ENS_AddArea.jsp";
		 }
		
		else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
		{
			System.out.println("into excel");
			ArrayList<ENS_AddAreaBean> reports = addArea.genreport(users, request);

			request.setAttribute("Reportarea", reports);
			forward = "./ENS_GenerateAreaReportExcel.jsp";
		}
		else if (action != "" && action.equalsIgnoreCase("next"))
		{
			forward = "./ENS_GenerateEquipExcel.jsp";
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
