package servlet;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import dao.Mail_EmailMasterDAO;
import mapper.ENS_MailMapper;
import mapper.Mail_EmailMasterMapper;

/**
 * Servlet implementation class Mail_AddFromFileServlet
 */
@WebServlet("/ENS/Mail_AddFromFileServlet")
public class Mail_AddFromFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mail_AddFromFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("--insise xls---");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession httpSession=request.getSession();
		UserBean users=(UserBean) httpSession.getAttribute("Users");
		String action = request.getParameter("action");
		String forward="";
		String msg="";
		String statusMsg="blank";
		BufferedImage image = null;
		
		ENS_MailDao mailDao = new ENS_MailMapper();
		System.out.println("--insise xlsx---");
		String saveDirectory = this.getServletContext().getRealPath("/ENS/images/LOGOS/");
		/*String sz="90000000";
		int fileSize = 90000000+50000;*/
		
		MultipartRequest mrequest = new MultipartRequest(request,saveDirectory);
		String fileOrigPath ="";
		File fileToUpload = mrequest.getFile("filepr");
		image = ImageIO.read(fileToUpload);
				
		String fileName = fileToUpload.getName();
		System.out.println("file name="+fileName);
		
		
		String fileExt = fileName.substring(fileName.lastIndexOf("."));

		fileOrigPath = fileToUpload.getAbsolutePath().substring(0, fileToUpload.getAbsolutePath().lastIndexOf("\\"));
		System.out.println("File Original Path :"+fileOrigPath);
		String newFileName = fileOrigPath+"\\" +fileName;
		System.out.println("newFileName = "+newFileName);
		request.setAttribute("newFileName", newFileName);
		
		//msg = emailDao.getDetailsxlsx(request, users);
		
		String mailSubject,mailText,date,emailFromval;
		String[] mails;
		List<String> emailToval=new ArrayList<String>();
		List<String> emaillst = new ArrayList<String>();
		
		date = mrequest.getParameter("date1");
		System.out.println("Spc Date Report :"+date);
		
		
		request.setAttribute("spc_date",date);
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
		//mailText += "<img src=\"cid:image1\" width=\"30%\" height=\"50%\" /><br>";
		
		
		mails = mrequest.getParameterValues("to");
		for(String value : mails)
		{
			System.out.println("Mails from request :"+value);
			emailToval.add(value);
		}
		
		mailSubject=mrequest.getParameter("sub");
		System.out.println("Mail Subject :"+mailSubject);
		
		emailFromval = mrequest.getParameter("from");
		System.out.println("Mail From "+emailFromval);
		
		System.out.println("Mail To" + emailToval);
		
		Map<String, String> inlineImages = new HashMap<String, String>();
	    // inlineImages.put("image1", "E:/00_ENS_FinalProject/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/EnergyManagementSystem/ENS/images/LOGOS/Apollo.jpg");
	    inlineImages.put("image2", newFileName);
	    System.out.println("Inline images :"+inlineImages);
	        
		boolean stat = mailDao.sendMail(mailSubject, mailText, emailToval, emailFromval,inlineImages);
		
		fileToUpload.delete();
		  
		if(stat)
		{
			statusMsg=" <br/>  Comment Is Inserted And Mail Sent To TPO Head";
		}
		else
		{
			statusMsg=" <br/> Comment Is Inserted But Error While Sending Mail To TPO Head";							
		}
		
		request.setAttribute("msg", statusMsg);
		forward = "./Mail_AddFromFileJsp.jsp";
		
		RequestDispatcher dispatchForward = request.getRequestDispatcher(forward);
		dispatchForward.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
