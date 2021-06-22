package mapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_Control_No;
import beans.Mail_EmailMasterBean;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class Mail_EmailMasterMapper implements dao.Mail_EmailMasterDAO{
	
	int i;
	public Mail_EmailMasterMapper()
	{
		ENS_hibernateFactory.buildIfNeeded();
	}
	
	private ENS_Control_No obj = null;
	
	private  void getControlsno(int plt, String table) 
	{
		Session session = null;
		session = ENS_hibernateFactory.openSession();
		Query query = session.createQuery("from ENS_Control_No "
				+ " where PLT = " + plt + " and CTRLNO_DOCUMENT='" + table + "'");
		obj = (ENS_Control_No) query.uniqueResult();
	}

	@Override
	public String getDetailsxlsx(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		
		List list = null;
		String fileName =(String) request.getAttribute("newFileName");
		List sheetData = new ArrayList(); 
		Mail_EmailMasterBean emailBean = new Mail_EmailMasterBean();
		
		Session session = null;
		Transaction tx = null;
		String msg = "";
		
		
		FileInputStream fis = null;
		
		try 
		{ 
			
			fis = new FileInputStream(fileName); 
			// 
			// Create an excel workbook from the file system. 
			// 
			XSSFWorkbook workbook = new XSSFWorkbook(fis); 
			// 
			// Get the first sheet on the workbook. 
			// 
			XSSFSheet sheet = workbook.getSheetAt(0); 
			// 
			// When we have a sheet object in hand we can iterator on 
			// each sheet's rows and on each row's cells. We store the 
			// data read on an ArrayList so that we can printed the 
			// content of the excel to the console. 
			// 
			Iterator rows = sheet.rowIterator();
			
			while (rows.hasNext()) 
			{ 
				XSSFRow row = (XSSFRow) rows.next(); 
				Iterator cells = row.cellIterator(); 
				
				List data = new ArrayList(); 
				while (cells.hasNext()) 
				{ 
					XSSFCell cell = (XSSFCell) cells.next(); 
					data.add(cell); 
				} 
				sheetData.add(data); 
			} 
			
		} 
		
		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		} 
		
		finally 
		{ 
			if (fis != null) 
			{ 
				try
				{
					fis.close();
				}
				catch(Exception e)
				{
					e.printStackTrace(); 
				}
			} 
		}
		System.out.println("sheet :"+sheetData.size());		
		for (i=1;i<sheetData.size();i++)
		{
			try
			{
				System.out.println("i"+i);
				list = (List) sheetData.get(i);
				
				System.out.println("list value 0"+list.get(0));
				XSSFCell VEN_NM = (XSSFCell) list.get(0);
			
				System.out.println("list value 1"+list.get(1));
				XSSFCell EMAIL = (XSSFCell) list.get(1);
			
				System.out.println("list value 2"+list.get(2));
				XSSFCell DEPT_NAME = (XSSFCell) list.get(2);
			
				System.out.println("list value 3"+list.get(3));
				XSSFCell VEN_CODE = (XSSFCell) list.get(3);
				
				System.out.println("list value 2"+list.get(4));
				XSSFCell FILE_NAME = (XSSFCell) list.get(4);
				
				System.out.println("list value 2"+list.get(5));
				XSSFCell VEN_ID = (XSSFCell) list.get(5);
				
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				HttpSession httpSession = request.getSession();
				UserBean user = (UserBean) httpSession.getAttribute("Users");
				getControlsno(users.getPlt(),"mail_master");
				
				if (obj == null)
				{
					session = ENS_hibernateFactory.openSession();
					tx = (Transaction) session.beginTransaction();
					System.out.println("into obj...");
					obj = new ENS_Control_No();
					obj.setPLT(users.getPlt());
					obj.setFIN_YR(users.getFinyear());
					obj.setCTRLNO_DOCUMENT("mail_master");
					obj.setCTRLNO_NEXT_NO(0);
				}
				
				emailBean.setPLT(users.getPlt());
				emailBean.setEMAIL(EMAIL.getStringCellValue());
				emailBean.setEMAIL_FOR("abc@gmail.com");
				emailBean.setDEPT_NAME(DEPT_NAME.getStringCellValue());
				emailBean.setVEN_CODE(VEN_CODE.getStringCellValue());
				emailBean.setVEN_ID(VEN_ID.getStringCellValue());
				emailBean.setVEN_NM(VEN_NM.getStringCellValue());
				emailBean.setFLG("Y");
				emailBean.setUP_BY(new Date());
				emailBean.setUP_WHO("yash");
				emailBean.setUSER_DISP("abc@gmail.com"+"~"+DEPT_NAME.getStringCellValue()+"~"+VEN_NM.getStringCellValue());
				emailBean.setFILE_NAME(FILE_NAME.getStringCellValue());
				
				session.save(emailBean);
				session.flush();
				tx.commit();
				
				msg="<font color='green' style='font-size: 14pt;'><b>Data Inserted Successfully.</b></font>";
				obj.setCTRLNO_NEXT_NO(obj.getCTRLNO_NEXT_NO()+1);
				session.saveOrUpdate(obj);
				session.flush();
				
				
			}
			catch(Exception e)
			{
				msg="0";
				System.out.println("Erorr = "+e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				try
				{
					ENS_hibernateFactory.close(session);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			String rec=String.valueOf(i-1);
			request.setAttribute("record",rec);
		}
		return msg;
	}

}
