package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;

/**
 * Servlet implementation class welcomemain
 */
@WebServlet("/Welcome_main")
public class Welcome_main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome_main() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//HttpSession session = request.getSession();
		System.out.println("Servlet...");
		
		HttpSession session = request.getSession();
		UserBean users = new UserBean();
		String redirectTo = null;
		
		if (request.getParameter("appl") != null) {

			users.setUid(Integer.parseInt(request.getParameter("uid")));
			users.setPlt(Integer.parseInt(request.getParameter("plt")));
			users.setUsername(request.getParameter("usrnam"));
			users.settype(request.getParameter("usrtyp"));
			users.setEmail(request.getParameter("usrmail"));
			users.setSec(request.getParameter("sec"));
			users.setPlantName(request.getParameter("pltnam"));
			users.setFinyear(request.getParameter("fin"));
			if (request.getParameter("appl").equalsIgnoreCase("bldmg")) {
				redirectTo = "Bldmg/Bldmg_WelcomePage.jsp";
			}
			if (request.getParameter("appl").equalsIgnoreCase("vdcost")) {
				redirectTo = "VDCOST/VDCOST_welcomepage.jsp";
			}
			if (request.getParameter("appl").equalsIgnoreCase("rt")) {
				redirectTo = "RT/TEST.jsp";
			}
		}
		else
		{
		
			users.setFinyear("/19");
			users.setPlt(1002);
			users.setSec("PCR");

			users.setUid(101);
			//users.settype("Rep");
			users.settype("Sup");
			//users.settype("Key");
			users.setUsername("yash");
			users.setEmail("yashborade15@gmail.com");
		
			session.setAttribute("Users", users);
			System.out.println("In the Login Servlet");
		
			System.out.println("In servlet");
			//redirectTo="ENS/AODMS_Login.jsp";
			redirectTo="ENS/ENS_Welcome.jsp";
		}
		
			RequestDispatcher dp = request.getRequestDispatcher(redirectTo);
			response.sendRedirect(redirectTo);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
