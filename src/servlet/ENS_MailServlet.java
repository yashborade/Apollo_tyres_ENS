package servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import beans.ENS_CalAreaGroupBean;
import beans.ENS_CalSpcGroupBean;
import beans.UserBean;
import dao.ENS_MailDao;
import mapper.ENS_MailMapper;

/**
 * Servlet implementation class ENS_MailServlet
 */
@WebServlet("/ENS/ENS_MailServlet")
public class ENS_MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ENS_MailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello ");
		HttpSession httpSession = request.getSession();
		UserBean users = (UserBean) httpSession.getAttribute("Users");
		List<String> emaillst = new ArrayList<String>();
		String forward = "";
		String msg = "";
		
		
		
		String action = request.getParameter("action");	
		System.out.println("action :"+action);
		
		ENS_MailDao mailDao = new ENS_MailMapper();
		
		if (action != "" && action.equalsIgnoreCase("load"))
		{
			request.setAttribute("msg", "");
			forward = "./ENS_SendMail.jsp";
		}
		else if (action != "" && action.equalsIgnoreCase("Send"))
		{
			String statusMsg="blank";
			
			String mailSubject,mailText,date;
			List<String> emailToval=new ArrayList<String>();
			String emailFromval = "";
			
			date = request.getParameter("date1");
			System.out.println("Spc Date Report :"+date);
			
			String  filenm = request.getParameter("filepr");
			System.out.println("File name :"+filenm);
			
			ArrayList<ENS_CalSpcGroupBean> genemail = mailDao.getMail(users, request);
			ArrayList<ENS_CalAreaGroupBean> calArea = mailDao.getArea(users, request);
			
			mailText = "<h3><b>Dear Sir,</b></h3>";
			mailText += "<br>";
			mailText += "<h2>Reports and Trends of SPC and Energy Consumption</h2>";
			mailText += "</br>";
			mailText += "<br>";
			mailText += "<h2><center>Trends of SPC Consumption</center></h2>";
			mailText += "</br>";
			mailText += "<br>";
			mailText += "<img src=\"cid:image2\" width=\"100%\" height=\"100%\" /><br>";
			mailText += "</br>";
			mailText += "<br>";
			mailText += "<h2><b>Consumption Report</b></h2>";
			mailText += "</br>";
			mailText += "<br>";
			mailText += "<table border = '1' width = '50%' >";
			mailText += "<tr>";
			mailText += "<th>Date</th>";
			mailText += "<th>Area Name</th>";
			mailText += "<th>Total Consumption</th>";
			mailText += "</tr>";
			
			for(ENS_CalAreaGroupBean cal : calArea)
			{
				Date date1 = new Date();
  				SimpleDateFormat sdfo = new SimpleDateFormat("dd-MM-yyyy");
  				String a = sdfo.format(cal.getAREA_DATE()).toString();
  				//System.out.println("Dates = "+a);
  				
  				System.out.println("Area Date :"+a);
				System.out.println("Area name :"+ cal.getAREA_NAM());
				System.out.println("Total Consumption :"+cal.getTOTAL());
				
				mailText = mailText+"<tr align='center'>"
									+"<td>" + a + "</td>"
									+ "<td>" + cal.getAREA_NAM() + "</td>"
									+ "<td>" + cal.getTOTAL() + "</td>"
									+"</tr>";
			}
			
			mailText += "</table>";
			mailText += "</br>";
			mailText += "<h2><b>SPC Report</b></h2>";
			mailText += "<table border = '1' width = '50%' >";
			mailText += "<tr>";
			mailText += "<th>Date</th>";
			mailText += "<th>Area Name</th>";
			mailText += "<th>Total Tonnage</th>";
			mailText += "<th>Total SPC</th>";
			mailText += "</tr>";
			
			for(ENS_CalSpcGroupBean calspc : genemail)
			{
				Date date1 = new Date();
  				SimpleDateFormat sdfo = new SimpleDateFormat("dd-MM-yyyy");
  				String a = sdfo.format(calspc.getSPC_DATE()).toString();
  				//System.out.println("Dates = "+a);
  				
  				System.out.println("Spc Date :"+a);
				System.out.println("Area name :"+ calspc.getAREA_NAME());
				System.out.println("Total Tonnage :"+calspc.getTOTAL_TON());
				System.out.println("Total spc :"+ calspc.getTOTAL_SPC());
				
				mailText = mailText+"<tr align='center'>"
									+"<td>" + a + "</td>"
									+ "<td>" + calspc.getAREA_NAME() + "</td>"
									+ "<td>" + calspc.getTOTAL_TON() + "</td>"
									+ "<td>" + calspc.getTOTAL_SPC() + "</td>"
									+"</tr>";
			}
			
			mailText += "</table>";
			mailText += "</br>";
			mailText += "<h3><b>Best Regards,</b></h3>";
			mailText += "<h3><b>Head Of AODMS</b><h3>";
			mailText += "------------------------------------------------------";
			mailText += "</br>";
			mailText += "<img src=\"cid:image1\" width=\"15%\" height=\"25%\" /><br>";
			//mailText += "<img src=\"cid:image2\"/><br>";
			//images/LOGOS/ApolloPNG5.png
			
			mailSubject=request.getParameter("sub");
			System.out.println("Mail Subject :"+mailSubject);
			emailFromval = request.getParameter("from");
			System.out.println("Mail From "+emailFromval);
			emailToval.add(request.getParameter("to"));
			System.out.println("Mail To" + emailToval);
			
			// inline images
	        Map<String, String> inlineImages = new HashMap<String, String>();
	        inlineImages.put("image1", "C:/Users/tpo.limdait/Desktop/ApolloPNG5.png");
	        inlineImages.put("image2", "C:/Users/tpo.limdait/Desktop/download.png");
	        System.out.println("Inline images :"+inlineImages);
			
			boolean stat = mailDao.sendMail(mailSubject, mailText, emailToval, emailFromval,inlineImages);
			
			emaillst = mailDao.getMailID(1002, "bias", "EMS");
			
			for(int i=0;i<emaillst.size();i++)
			{
				System.out.println("Email lst :"+emaillst.get(i));
			}
			
			if(stat)
			{
				statusMsg=" <br/>  Comment Is Inserted And Mail Sent To TPO Head";
			}
			else
			{
				statusMsg=" <br/> Comment Is Inserted But Error While Sending Mail To TPO Head";							
			}
			
			
			request.setAttribute("msg", statusMsg);
			forward = "./ENS_SendMail.jsp";
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