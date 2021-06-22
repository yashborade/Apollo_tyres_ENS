package mapper;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import beans.ENS_SummaryBean;
import beans.ENS_UploadBean;
import beans.UserBean;
import dao.ENS_UploadDao;
import utility.ENS_hibernateFactory;

public class ENS_UploadFileMapper implements ENS_UploadDao{
	
	
	int i;
	public ENS_UploadFileMapper()
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
		
		
		List list = null;
		String fileName =(String) request.getAttribute("newFileName");
		List sheetData = new ArrayList(); 
		ENS_UploadBean uplBean = new ENS_UploadBean();
		ENS_SummaryBean sumBean = new ENS_SummaryBean();
		
		List<Integer> list1=new ArrayList<Integer>();
		List<Integer> list2=new ArrayList<Integer>();
		
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
		
		//System.out.println("sheet :"+sheetData.size());		
		for (i=1;i<sheetData.size();i++) 
		{ 	
			
			try{
				
				uplBean = new ENS_UploadBean();
				
				System.out.println("i"+i);
				list = (List) sheetData.get(i);
				
				Calendar cal = Calendar.getInstance();
				
				SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd"); 
				//Date d1 = sdfo.parse("2020-01-01");
				
				//Date d1;
				String DprDate = request.getAttribute("FinalDate").toString();
				Date d2 = sdfo.parse(request.getAttribute("FinalDate").toString());
				String newDate2 = sdfo.format(d2);
				//System.out.println("Date 2 = " + newDate2);
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
				
				Date dprDate = new SimpleDateFormat("yyyy-MM-dd").parse(DprDate);
				//System.out.println("Final Date In Mapper = " + DprDate);
				
				String day = DprDate.substring(8,10);
				//System.out.println("Day from Date = " + day);
			
				String sec = request.getAttribute("section1").toString();
				//System.out.println("section :"+sec);
				
				int dResult = Integer.parseInt(day);
			
				int rdngNo = dResult+2;
			
				//System.out.println("list value 0"+list.get(0));
				XSSFCell NOD_NAM = (XSSFCell) list.get(0);
			
				//System.out.println("list value 1"+list.get(1));
				XSSFCell SRNO = (XSSFCell) list.get(1);
			
				//System.out.println("list value 2"+list.get(2));
				XSSFCell MACH_NAM = (XSSFCell) list.get(2);
			
				//System.out.println("list value 3"+list.get(rdngNo));
				XSSFCell READINGS = (XSSFCell) list.get(rdngNo);
				 
			
				
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				HttpSession httpSession = request.getSession();
				UserBean user = (UserBean) httpSession.getAttribute("Users");
				getControlsno(users.getPlt(),"ENS_UploadMst");
				
				if (obj == null)
				{
					session = ENS_hibernateFactory.openSession();
					tx = (Transaction) session.beginTransaction();
					System.out.println("into obj...");
					obj = new ENS_Control_No();
					obj.setPLT(users.getPlt());
					obj.setFIN_YR(users.getFinyear());
					obj.setCTRLNO_DOCUMENT("ENS_UploadMst");
					obj.setCTRLNO_NEXT_NO(0);
				}
				
				
				//System.out.println("Integer Number Of Day = " + dResult);
				uplBean.setSRNO((int)SRNO.getNumericCellValue());
				uplBean.setPLT(users.getPlt());
				uplBean.setSEC(sec);
				uplBean.setNOD_NAM(NOD_NAM.getStringCellValue());
				uplBean.setMACH_NAM(MACH_NAM.getStringCellValue());
				uplBean.setREADINGS((double)READINGS.getNumericCellValue());
				uplBean.setDATE(dprDate);
				uplBean.setFLAG("Y");
				uplBean.setUPD_ON(new Date());
				uplBean.setUPD_BY(users.getUid());
				
				session.save(uplBean);
				session.flush();
				tx.commit();	
								
				msg="<font color='green' style='font-size: 14pt;'><b>Data Inserted Successfully.</b></font>";
				obj.setCTRLNO_NEXT_NO(obj.getCTRLNO_NEXT_NO()+1);
				session.saveOrUpdate(obj);
				session.flush();
				}
			
				catch(Exception e){
					
					msg="0";
					System.out.println("Erorr = "+e.getMessage());
					e.printStackTrace();
						
				}
				finally{
				
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
	

	@Override
	public ArrayList<ENS_UploadBean> getNode(UserBean users, HttpServletRequest request) {
		Session session = null;
		ArrayList<ENS_UploadBean> lstCat = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();

			Query query = session.createQuery("from ENS_UploadBean where PLT=" + users.getPlt() + " "
					+ " and DATE='" + request.getAttribute("FinalDate")+"'");
			lstCat = (ArrayList<ENS_UploadBean>) query.list();
		}
		catch(Exception ex)
		{
			System.out.println("ENS_get node --> Exception" + ex.getMessage());
			ex.printStackTrace();
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
		
		return lstCat;
	}

	
}