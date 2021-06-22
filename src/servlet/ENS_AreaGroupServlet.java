package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ENS_AddGroupBean;
import beans.ENS_AreaGroupBean;
import beans.UserBean;
import dao.ENS_AddGroupDao;
import dao.ENS_AreaGroupDao;
import mapper.ENS_AddGroupMapper;
import mapper.ENS_AreaGroupMapper;

/**
 * Servlet implementation class ENS_AreaGroupServlet
 */
@WebServlet("/ENS/ENS_AreaGroupServlet")
public class ENS_AreaGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ENS_AreaGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String forward = "";
		String msg = "";
		
		List<String> find = new ArrayList<String>();
		
		String action = request.getParameter("action");	
		System.out.println("action "+action);
		
		ENS_AreaGroupDao argrpDao = new ENS_AreaGroupMapper();
		
		
		if (action != "" && action.equalsIgnoreCase("Insert")){
			
			synchronized (this) 
			{	
				msg = argrpDao.insData(request);
				
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Group Inserted Successfully.";
					
				}
				request.setAttribute("msg", msg);
				forward = "./ENS_AddAreaGroup.jsp";
			}
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("inside node number report");
			
			ArrayList<ENS_AreaGroupBean> reports = argrpDao.genereport(users, request);
			
			request.setAttribute("Reportequipno", reports);
			
			forward = "./ENS_AreaGroupReport.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Edit"))
		{
			System.out.println("inside Edit");
			
			request.setAttribute("msg", msg);
			
			forward = "./ENS_EditAreaGroup.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Modify"))
		{
			System.out.println("inside modify");
			
			String a=request.getParameter("equip_nam");
			System.out.println("inside modify equipment :"+a);
			
			ENS_AreaGroupBean groupBean = null;
			groupBean = argrpDao.getArea(request);
			request.setAttribute("groupupdate", groupBean);
			
			request.setAttribute("msg", msg);
			forward = "./ENS_EditAreaGroupFilter.jsp";
			
		}
		
		else if(action != "" && action.equalsIgnoreCase("Update"))
		{
			synchronized (this) 
			{	
				msg = argrpDao.editData(request);
				
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Data Updated Successfully.";
					
				}
				request.setAttribute("msg", msg);
				
				forward = "./ENS_AddAreaGroup.jsp";
			}
			
		}
		else if(action != "" && action.equalsIgnoreCase("Delete"))
		{
			System.out.println("inside Delete");
			
			request.setAttribute("msg", msg);
			
			forward = "./ENS_DeleteAddAreaGroup.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Confirm"))
		{
			System.out.println("inside confirm");
			
			String a=request.getParameter("area_nam");
			System.out.println("Area Name :"+a);
			
			ENS_AreaGroupBean groupBean = null;
			groupBean = argrpDao.deletedata(request);
			
			
			request.setAttribute("msg", msg);
			forward = "./ENS_AddAreaGroup.jsp";
			
		}
		
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_AddAreaGroup.jsp";
			
		}
		
		/*else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
		{
			//request.getParameter("section");
			System.out.println("into excel");
			
			ArrayList<ENS_AddGroupBean> reports = addgrp.genreport(users, request);
			request.setAttribute("Reportgroupno", reports);
			forward = "./ENS_GenerateGroupExcel.jsp";
		}
		else if (action != "" && action.equalsIgnoreCase("next"))
		{
			forward = "./ENS_GenerateGroupExcel.jsp";
		}*/

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
