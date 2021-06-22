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

import beans.ENS_AddEquipBean;
import beans.ENS_AddNodBean;
import beans.UserBean;
import dao.ENS_AddEquipDao;
import dao.ENS_AddNode;
import mapper.ENS_AddEquipMapper;
import mapper.ENS_AddNodeMapper;

/**
 * Servlet implementation class ENS_AddEquipServlet
 */
@WebServlet("/ENS/ENS_AddEquipServlet")
public class ENS_AddEquipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ENS_AddEquipServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String forward = "";
		String msg = "";
		
		
		
		String action = request.getParameter("action");	
		System.out.println("action"+action);
		ENS_AddEquipDao addequip = new ENS_AddEquipMapper();
		
		if (action != "" && action.equalsIgnoreCase("Insert")){
			
			synchronized (this) 
			{	
				msg = addequip.insEquip(request);
			
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Data Inserted Successfully.";
					
				}
				request.setAttribute("msg", msg);
				forward = "./ENS_AddEquip.jsp";
			}
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("inside node number report");
			
			
			
			ArrayList<ENS_AddEquipBean> reports = addequip.genreport(users, request);
			
			request.setAttribute("Reportequipno", reports);
			
			forward = "./ENS_EquipReport.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_AddEquip.jsp";
		}
		
		else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
		{
			System.out.println("into excel");
			ArrayList<ENS_AddEquipBean> reports = addequip.genreport(users, request);

			request.setAttribute("Reportequipno", reports);
			forward = "./ENS_GenerateEquipExcel.jsp";
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
