package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import dao.EMP_INFODao;
import dao.ENS_MailDao;
import dao.Mail_EmailMasterDAO;
import mapper.EMP_INFOMapper;
import mapper.ENS_MailMapper;
import mapper.Mail_EmailMasterMapper;

/**
 * Servlet implementation class Mail_ExcelData
 */
@WebServlet("/ENS/Mail_ExcelData")
public class Mail_ExcelData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mail_ExcelData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stud
		response.setContentType("text/html");
		
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String action = request.getParameter("action");
		String forward = "";
		String msg = "";
		
		EMP_INFODao empdao = new EMP_INFOMapper();
		List<String> emaillst = new ArrayList<String>();
		
		if (action != "" && action.equalsIgnoreCase("Upload")){
			
			
			synchronized (this) 
			{	
				//msg = email.getDetailsxlsx(request, users);
			
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Data Inserted Successfully.";
					
				}
				request.setAttribute("msg", msg);
				forward = "./Mail_AddFromFileJsp.jsp";
			}
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			emaillst = empdao.getMailID(users.getPlt(),users.getSec(), "PWR");
			
			request.setAttribute("email", emaillst);
			
			for(int i=0;i<emaillst.size();i++)
			{
				System.out.println("Email lst :"+emaillst.get(i));
			}
			request.setAttribute("msg", "");
			
			forward = "./Mail_AddFromFileJsp.jsp";
		}
		else
		{
			forward = "./ENS_Welcome.jsp";
		}
		
		RequestDispatcher dispatchForward = request.getRequestDispatcher(forward);
		dispatchForward.forward(request, response);
	}

}
