package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ibm.db2.jcc.resources.SqljResources_no_NO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.MultipartParser;

import beans.ENS_SummaryBean;
import beans.ENS_UploadBean;
import beans.UserBean;
import dao.ENS_SummaryDao;
import dao.ENS_UploadDao;
import dao.ENS_UploadReportDao;
import mapper.ENS_SummaryReportMapper;
import mapper.ENS_UploadFileMapper;
import mapper.ENS_UploadReportMapper;

/**
 * Servlet implementation class ENS_UploadFileServlet
 */
@WebServlet("/ENS/ENS_UploadFileServlet")
public class ENS_UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ENS_UploadFileServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		System.out.println("--insise xls---");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession httpSession=request.getSession();
		UserBean users=(UserBean) httpSession.getAttribute("Users");
		String action = request.getParameter("action");
		String forward="";
		String msg="";
		
		
		ENS_UploadDao IEDED=new ENS_UploadFileMapper();
		ENS_UploadReportDao IEDED1 = new ENS_UploadReportMapper();
		
		//ENS_UploadReportDao upload = new ENS_UploadReportMapper();
		
		ArrayList<ENS_UploadBean> reports = IEDED1.genreport(users, request);
		
		
		//System.out.println("My Date = " + request.getParameter("FinalDate"));
		//System.out.println("Input Value = " + request.getParameter("xyz"));
		
		System.out.println("--insise xlsx---");
		String saveDirectory = this.getServletContext().getRealPath("/ENS/excel/");
		String sz="90000000";
		int fileSize = 90000000+50000;
	
		MultipartRequest mrequest = new MultipartRequest(request,saveDirectory,fileSize);
		String action1 = mrequest.getParameter("action");
		String filenm = mrequest.getParameter("filepr");
		String section = mrequest.getParameter("section1");
		request.setAttribute("section1", section);
		//System.out.println(action1);
		//System.out.println(filenm);
		//System.out.println(section);
		
		String dt = mrequest.getParameter("FinalDate");
		//System.out.println("Final Date" + dt);
		request.setAttribute("FinalDate", dt);
		
		String fileOrigPath ="";
		File fileToUpload = mrequest.getFile("filepr");

		String fileName = fileToUpload.getName();
		//System.out.println("file name="+fileName);
		String fileExt = fileName.substring(fileName.lastIndexOf("."));

			fileOrigPath = fileToUpload.getAbsolutePath().substring(0, fileToUpload.getAbsolutePath().lastIndexOf("\\"));

			//System.out.println("---"+fileOrigPath);
			String newFileName = fileOrigPath+"\\" +fileName;
			//System.out.println("newFileName="+newFileName);
			request.setAttribute("newFileName", newFileName);
			msg=IEDED.getDetailsxlsx(request,users);
			//msg=IEDED.getDetailsxls(request);
			File newFile = new File(newFileName);
			FileInputStream fis = new FileInputStream(fileToUpload);
			FileOutputStream fout = new FileOutputStream(newFile);
		
			byte[] buffer = new byte[1024]; // 1 KB
			int len;
			while ((len = fis.read(buffer)) > 0) 
			{
				fout.write(buffer, 0, len);
			}							
			fis.close();
			fout.close();
			fileToUpload.delete();
			newFile.setReadOnly();
		
			forward = "./ENS_UploadFile.jsp";
			
			Calendar cal = Calendar.getInstance();
			
			SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd"); 
			//Date d1 = sdfo.parse("2020-01-01");
			
			//Date d1;
			String DprDate = mrequest.getParameter("FinalDate");
			
			Date d2 = new Date();
			try
			{
				d2 = sdfo.parse(mrequest.getParameter("FinalDate"));
			} 
			catch (ParseException e) {
				
				// TODO Auto-generated catch block
				//e.printStackTrace();
				forward = "./ENS_Welcome.jsp";
			}
			String newDate2 = sdfo.format(d2);
			//System.out.println("Date 2 = " + newDate2);
			//int maxday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			int maxday = cal.getActualMinimum(Calendar.DAY_OF_WEEK);
			
			for(int i=0;i<maxday;i++)
			{
				cal.setTime(d2);
				int daysToDecrement = -1;
				cal.add(Calendar.DATE, daysToDecrement);
				d2 = cal.getTime();
				newDate2 = sdfo.format(d2);
				//System.out.println("Date 1 = " + newDate2);
			}
			
			//ENS_UploadReportDao da = new ENS_UploadReportMapper();
			
			ENS_SummaryDao sum = new ENS_SummaryReportMapper();
			
			double diff = 0;
			ArrayList<ENS_UploadBean> val = IEDED.getNode(users, request);
			for(ENS_UploadBean en : val){
				
				//System.out.println("1 :"+en.getSEC());
				//System.out.println("2 :"+en.getNOD_NAM());
				//System.out.println("3 :"+en.getMACH_NAM());
				//System.out.println("4 :"+en.getREADINGS());
				
				
				ENS_UploadBean val2 = IEDED1.getReadings(users, en.getNOD_NAM(), en.getMACH_NAM() ,newDate2 );
				//System.out.println("1 :"+val2.getNOD_NAM());
				//System.out.println("1 :"+val2.getMACH_NAM());
				//System.out.println("1 :"+newDate2);
				double redVal = en.getREADINGS()-val2.getREADINGS();
				
				diff = redVal;
				
				
				//System.out.println("Read "+redVal);
				
				sum.insSummary(users, en.getSEC(), en.getNOD_NAM(), en.getMACH_NAM(), diff, newDate2);
			}
			
			if (msg.equals("0"))
			{
				System.out.println("data not inserted");
				msg = "Error in Inserting Data.";
			}
			else
			{
				System.out.println("data inserted");
				msg	="Data Inserted SuccessFully";
			}
			
			request.setAttribute("msg", msg);
			forward = "./ENS_Welcome.jsp";
	
		
		RequestDispatcher dispatchForward = request.getRequestDispatcher(forward);
		dispatchForward.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}