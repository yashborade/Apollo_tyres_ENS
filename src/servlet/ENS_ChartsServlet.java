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
import beans.ENS_AreaTargetBean;
import beans.ENS_CalAreaGroupBean;
import beans.ENS_CalSpcGroupBean;
import beans.ENS_CalculateGroupBean;
import beans.ENS_SpcTargetBean;
import beans.UserBean;
import dao.ENS_AddEquipDao;
import dao.ENS_ChartDao;
import mapper.ENS_AddEquipMapper;
import mapper.ENS_ChartsMapper;

/**
 * Servlet implementation class ENS_ChartsServlet
 */
@WebServlet("/ENS/ENS_ChartsServlet")
public class ENS_ChartsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ENS_ChartsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String forward = "";
		String msg = "";
		
		String action = request.getParameter("action");	
		System.out.println("action"+action);
		ENS_ChartDao charts = new ENS_ChartsMapper();
		
		 	if (action != "" && action.equalsIgnoreCase("Report"))
		 	{
			 	System.out.println("inside charts report");
			
			
			 	forward = "./ENS_ChartsFilter.jsp";
			
		 	}
		 	else if (action != "" && action.equalsIgnoreCase("Filter"))
		 	{
		 		System.out.println("inside Charts ");
		 		
		 		ArrayList<ENS_SpcTargetBean> target = charts.getspctarget(request, users);
				request.setAttribute("target", target);
			
		 		ArrayList<ENS_CalSpcGroupBean> reports = charts.genereport(request, users);
			
		 		request.setAttribute("charts", reports);
			
		 		forward = "./ENS_SpcCharts.jsp";
			}
		 	else if (action != "" && action.equalsIgnoreCase("Report_Area"))
		 	{
			 	System.out.println("inside charts report");
			
			
			 	forward = "./ENS_AreaChartFilter.jsp";
			
		 	}
		 	else if (action != "" && action.equalsIgnoreCase("Filter_Area"))
		 	{
		 		System.out.println("inside Charts ");
		 		
		 		String a = request.getParameter("area");
		 		System.out.println("area in filter area :"+a);
				
				ArrayList<ENS_CalAreaGroupBean> reports = charts.getArea(request, users);
				request.setAttribute("charts", reports);
			
		 		ArrayList<ENS_AreaTargetBean> target = charts.gettarget(request, users);
		 		request.setAttribute("target", target);
		
		 		forward = "./ENS_AreaChart.jsp";
			}
		 	else if (action != "" && action.equalsIgnoreCase("Report_Equip"))
			{
				System.out.println("inside charts report");
				
				
				forward = "./ENS_ChartsEquipFilter.jsp";
				
			}
			 else if (action != "" && action.equalsIgnoreCase("Filter_Equip"))
			{
				System.out.println("inside Charts ");
				
				String b = request.getParameter("month");				
				System.out.println("month :"+b);
				
				String C = request.getParameter("equip");
				System.out.println("Equipment :"+C);

				
				//ArrayList<ENS_AreaTargetBean> target = charts.gettarget(request, users);
				//request.setAttribute("target", target);
		 		
				ArrayList<ENS_CalculateGroupBean> reports = charts.getEquip(request, users);
				
				request.setAttribute("charts", reports);
				
				forward = "./ENS_EquipCharts.jsp";
			}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_Welcome.jsp";
		}
		else
		{
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
