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

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.json.JSONArray;

import com.google.gson.Gson;

import beans.ENS_AddEquipBean;
import beans.ENS_AddGroupBean;
import beans.ENS_CalculateGroupBean;
import beans.ENS_SummaryBean;
import beans.UserBean;
import dao.ENS_AddEquipDao;
import dao.ENS_AddGroupDao;
import dao.ENS_CalculateGroupDao;
import dao.ENS_SummaryDao;
import mapper.ENS_AddEquipMapper;
import mapper.ENS_AddGroupMapper;
import mapper.ENS_CalculateGroupMapper;
import mapper.ENS_SummaryReportMapper;

@WebServlet("/ENS/ENS_AddGroupServlet")
public class ENS_AddGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ENS_AddGroupServlet() {
        super();
       
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
		
		ENS_AddGroupDao addgrp = new ENS_AddGroupMapper();
		
		
		 if (action != "" && action.equalsIgnoreCase("Insert")){
			
			synchronized (this) 
			{	
				msg = addgrp.insData(request);
				
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Group Inserted Successfully.";
					
				}
				request.setAttribute("msg", msg);
				forward = "./ENS_AddGroup.jsp";
			}
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("inside node number report");
			
			ArrayList<ENS_AddGroupBean> reports = addgrp.genreport(users, request);
			
			request.setAttribute("Reportequipno", reports);
			
			forward = "./ENS_GroupReport.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Edit"))
		{
			System.out.println("inside Edit");
			
			request.setAttribute("msg", msg);
			
			forward = "./ENS_Editgroup.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Delete"))
		{
			System.out.println("inside Delete");
			
			request.setAttribute("msg", msg);
			
			forward = "./ENS_DeleteAddGroup.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Confirm"))
		{
			System.out.println("inside modify");
			
			String a=request.getParameter("equip_nam");
			System.out.println("inside modify equipment :"+a);
			
			ENS_AddGroupBean groupBean = null;
			groupBean = addgrp.deletedata(request);
			//request.setAttribute("groupupdate", groupBean);
			
			request.setAttribute("msg", msg);
			forward = "./ENS_AddGroup.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Modify"))
		{
			System.out.println("inside modify");
			
			String a=request.getParameter("equip_nam");
			System.out.println("inside modify equipment :"+a);
			
			ENS_AddGroupBean groupBean = null;
			groupBean = addgrp.getEquip(request);
			request.setAttribute("groupupdate", groupBean);
			
			request.setAttribute("msg", msg);
			forward = "./ENS_EditGroupFilter.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Update"))
		{
			synchronized (this) 
			{	
				msg = addgrp.editData(request);
				
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Data Updated Successfully.";
					
				}
				request.setAttribute("msg", msg);
				
				forward = "./ENS_AddGroup.jsp";
			}
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_AddGroup.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("Download to Excel"))
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
