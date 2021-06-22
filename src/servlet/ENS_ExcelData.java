package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import beans.ENS_UploadBean;
import beans.UserBean;
import dao.ENS_UploadDao;
import mapper.ENS_UploadFileMapper;

/**
 * Servlet implementation class ENS_ExcelData
 */
@WebServlet("/ENS/ENS_ExcelData")
public class ENS_ExcelData extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
   
    public ENS_ExcelData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		doPost(request, response);
		
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String action = request.getParameter("action");
		String forward = "";
		String msg = "";
		
		ENS_UploadDao IEDED=new ENS_UploadFileMapper();
	
		if (action != "" && action.equalsIgnoreCase("Upload")){
			
			System.out.println("Inside Upload Action = ==========");
			
			System.out.println("My Date = " + request.getParameter("FinalDate"));
			
			synchronized (this) 
			{	
				msg = IEDED.getDetailsxlsx(request,users);
			
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					msg = "Data Inserted Successfully.";
					
				}
				request.setAttribute("msg", msg);
				forward = "./ENS_Welcome.jsp";
			}
			
		}
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_UploadFile.jsp";
		}
		else
		{
			forward = "./ENS_Welcome.jsp";
		}
		
		
		RequestDispatcher dispatchForward = request.getRequestDispatcher(forward);
		dispatchForward.forward(request, response);
	}
}
