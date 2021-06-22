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

import beans.ENS_AddSpcGroupBean;
import beans.ENS_AreaGroupBean;
import beans.UserBean;
import dao.ENS_AddSpcGroupDao;
import mapper.ENS_AddSpcGroupMapper;

/**
 * Servlet implementation class ENS_AddSpcGroupServlet
 */
@WebServlet("/ENS/ENS_AddSpcGroupServlet")
public class ENS_AddSpcGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ENS_AddSpcGroupServlet() {
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
		
		List<String> find = new ArrayList<String>();
		
		String action = request.getParameter("action");	
		System.out.println("action "+action);
		
		ENS_AddSpcGroupDao spcDao = new ENS_AddSpcGroupMapper();
		
		if (action != "" && action.equalsIgnoreCase("Insert")){
			
			synchronized (this) 
			{	
				msg = spcDao.insData(request);
				
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Group Inserted Successfully.";
					
				}
				request.setAttribute("msg", msg);
				forward = "./ENS_AddSpcGroup.jsp";
			}
			
		}
		else if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("inside node number report");
			
			ArrayList<ENS_AddSpcGroupBean> reports = spcDao.genereport(request, users); 
			
			request.setAttribute("Reportequipno", reports);
			
			forward = "./ENS_SpcGroupReport.jsp";
			
		}
		else if(action != "" && action.equalsIgnoreCase("Modify"))
		{
			System.out.println("inside modify");
			
			String a=request.getParameter("equip_nam");
			System.out.println("inside modify equipment :"+a);
			
			ENS_AddSpcGroupBean groupBean = null;
			groupBean = spcDao.getSpcgroup(request);
			request.setAttribute("groupupdate", groupBean);
			
			request.setAttribute("msg", msg);
			forward = "./ENS_EditSpcGroupFilter.jsp";
			
		}
		
		else if(action != "" && action.equalsIgnoreCase("Update"))
		{
			synchronized (this) 
			{	
				msg = spcDao.editData(request);
				
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Data Updated Successfully.";
					
				}
				request.setAttribute("msg", msg);
				
				forward = "./ENS_AddSpcGroup.jsp";
			}
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_AddSpcGroup.jsp";
			
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
