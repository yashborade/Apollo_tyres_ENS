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
import beans.ENS_AddMachBean;
import beans.ENS_AddNodBean;
import beans.ENS_AddProductBean;
import beans.ENS_CalculateGroupBean;
import beans.UserBean;
import dao.ENS_AddGroupDao;
import dao.ENS_AddProductDao;
import dao.ENS_CalculateGroupDao;
import mapper.ENS_AddGroupMapper;
import mapper.ENS_AddProductMapper;
import mapper.ENS_CalculateGroupMapper;

/**
 * Servlet implementation class ENS_AddGrpMstServlet
 */
@WebServlet("/ENS/ENS_AddProductMstServlet")
public class ENS_AddProductMstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ENS_AddProductMstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String forward = "";
		String msg = "";
		
		
		
		String action = request.getParameter("action");	
		System.out.println("action"+action);
		ENS_AddGroupDao addgrp = new ENS_AddGroupMapper();
		ENS_CalculateGroupDao calDao = new ENS_CalculateGroupMapper();
		ENS_AddProductDao proDao = new ENS_AddProductMapper();
		
		 if (action != "" && action.equalsIgnoreCase("Enter"))
			{
			 	System.out.println("inside modify");
				
				String a=request.getParameter("equip_nam");
				System.out.println("inside modify equipment :"+a);
				
				ENS_AddGroupBean groupBean = null;
				groupBean = addgrp.getEquip(request);
				request.setAttribute("groupupdate", groupBean);
				
				ENS_CalculateGroupBean calGrpBean= null;
				calGrpBean = calDao.getReadings(request);
				request.setAttribute("groupcalculate", calGrpBean);
				
				
			 request.setAttribute("msg",msg);
				forward = "./ENS_AddProductMstFilter.jsp";
			}
		 else if (action != "" && action.equalsIgnoreCase("Insert"))
		 {
			/*String a=request.getParameter("equip_nam");
			System.out.println("inside insert equipment :"+a);
			
			String b=request.getParameter("date1");
			System.out.println("inside insert date :"+b);
			
			String c=request.getParameter("total");
			System.out.println("inside insert total :"+c);
			
			String d=request.getParameter("batch1");
			System.out.println("inside insert total batch :"+d);
			
			String e=request.getParameter("batch2");
			System.out.println("inside insert bias batch :"+e);*/
			
			synchronized (this) {
				
				msg = proDao.insProduct(request);
				
				if(msg.equals(0))
				{
					msg = "Error in inserting data";
				}
				else
				{
					msg = "Data inserted successfully";
				}
				
				request.setAttribute("msg", msg);
				forward = "./ENS_AddProductMst.jsp";
			}
		 }
		
		 else if (action != "" && action.equalsIgnoreCase("Report"))
		 {
			 
			System.out.println("Inside Product Report action");
				
			
			forward = "./ENS_ProductMstReportFilter.jsp";
		 }
		 else if (action != "" && action.equalsIgnoreCase("FilterReport"))
		{
				System.out.println("Inside Filter Report action");
				
				ArrayList<ENS_AddProductBean> reports = proDao.genreport(users, request);

				request.setAttribute("Reportproduct", reports);
				forward = "./ENS_ProductMstReport.jsp";
				
		}
		else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
		{
				//request.getParameter("section");
				System.out.println("into excel");
				ArrayList<ENS_AddProductBean> reports = proDao.genreport(users, request);

				request.setAttribute("Reportproduct", reports);
				forward = "./ENS_GenerateProductExcel.jsp";
		}
		 else if (action != "" && action.equalsIgnoreCase("load"))
		 {
			request.setAttribute("msg", msg);
			forward = "./ENS_AddProductMst.jsp";
		 }
		 
		 else
		 {
			 request.setAttribute("msg", msg);
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
