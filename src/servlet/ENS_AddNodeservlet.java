package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;

import beans.ENS_AddNodBean;
import beans.UserBean;
import dao.ENS_AddNode;
import dao.ENS_MailDao;
import mapper.ENS_AddNodeMapper;
import mapper.ENS_MailMapper;
import utility.ENS_hibernateFactory;

/**
 * Servlet implementation class ENS_AddMachineservlet
 */
@WebServlet("/ENS/ENS_AddNodeservlet")
public class ENS_AddNodeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ENS_AddNodeservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		String forward = "";
		String msg = "";
		
		
		
		String action = request.getParameter("action");	
		System.out.println("actin"+action);
		ENS_AddNode addNode = new ENS_AddNodeMapper();
		ENS_MailDao mailDao = new ENS_MailMapper();
		
		if (action != "" && action.equalsIgnoreCase("Insert")){
			
			String statusMsg="blank";
			
			synchronized (this) 
			{	
				msg = addNode.insMachine(request);
				String mailSubject,mailText;
				List<String> emailToval=new ArrayList<String>();
			
				if (msg.equals("0"))
				{
					msg = "Error in Inserting Data.";
					
				}
				else
				{
					mailSubject="Mail Check";
					mailText="Hello Amit Sir, Demo Mail From Yash Borade";
					mailText += "<h2>Table Creating Demo</h2>";
					mailText += "<table border = '5' width = '50%' >";
					mailText += "<tr>";
					mailText += "<th>Node Name</th>";
					mailText += "<th>Section</th>";
					//mailText += "<th>Name</th>";
					mailText += "</tr>";
					
					ArrayList<ENS_AddNodBean> reports = addNode.genreport(users, request);
					for(ENS_AddNodBean nod : reports)
					{
						System.out.println("Node name :"+ nod.getNOD_NAM());
						System.out.println("Section :"+ nod.getSEC());
						
						mailText = mailText+"<tr align='center'>"+"<td>" +nod.getNOD_NAM() + "</td>"
                                + "<td>" + nod.getSEC() + "</td>"+"</tr>";

					}
					
					/*mailText += "<tr>";
					mailText += "<td>Bias</td>";
					mailText += "<td>1001</td>";
					mailText += "<td>Yash</td>";
					mailText += "</tr>";
					mailText += "<tr>";
					mailText += "<td>Radail</td>";
					mailText += "<td>1002</td>";
					mailText += "<td>Bajrang</td>";
					mailText += "</tr>";
					mailText += "</table>";*/
					  
					
					emailToval.add("tpo.iedamit@apollotyres.com");
					/*//boolean stat = mailDao.sendMail(mailSubject, mailText, emailToval, users.getEmail());
					
					if(stat)
					{
						statusMsg=" <br/>  Comment Is Inserted And Send Mail To TPO Head";
					}
					else
					{
						statusMsg=" <br/> Comment Is Inserted But Error While Send Mail To Tyre Engg";							
					}*/
					
					msg = "Data Inserted Successfully.";
					
				}
				request.setAttribute("msg", statusMsg);
				forward = "./ENS_add.jsp";
			}
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("Report"))
		{
			System.out.println("inside node number report");
			
			
			forward = "./ENS_NodeNoReportFilter.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("FilterReport"))
		{
			
			System.out.println("Inside Filter Report action");
			
			ArrayList<ENS_AddNodBean> reports = addNode.genreport(users, request);
			
			ArrayList<ENS_AddNodBean> reports1 = addNode.gExcel(users, request);
			
			for(ENS_AddNodBean nod : reports1)
			{
				System.out.println("Into gExcel :"+nod.getNOD_NAM());
				System.out.println("Into gExcel :"+nod.getSEC());
			}

			request.setAttribute("Reportnodeno", reports);
			forward = "./ENS_NodeReport.jsp";
			
		}
		
		else if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_add.jsp";
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
