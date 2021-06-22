package mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.ENS_AreaGroupBean;
import beans.ENS_AreaTargetBean;
import beans.ENS_CalAreaGroupBean;
import beans.ENS_Control_No;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_CalculateAreaGroupMapper implements dao.ENS_CalculateAreaGroupDao {
	
	public String msg = "";
	private ENS_Control_No obj = null;
	
	private  void getControlsno(int plt, String table) 
	{
		Session session = null;
		session = ENS_hibernateFactory.openSession();
		Query query = session.createQuery("from ENS_Control_No where "
				+ " PLT = " + plt + " and CTRLNO_DOCUMENT='" + table + "'");
		obj = (ENS_Control_No) query.uniqueResult();
	}
	
	public ENS_CalculateAreaGroupMapper() {
		// TODO Auto-generated constructor stub
		
		ENS_hibernateFactory.buildIfNeeded();
	}

	@Override
	public String[][] genereportMaster(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session = null;
		ArrayList<ENS_AreaGroupBean> sList = null;
		ArrayList<ENS_CalAreaGroupBean> mList = null;
		int noMachin = 0;
		int dateDiff = 0;
		
		String[][] arrData=null;
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date firstDate = sdf.parse(request.getParameter("date1"));
		    Date secondDate = sdf.parse(request.getParameter("date2"));
		 
		    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		    
		    System.out.println("Diff Dates = " + diff);	
		    
		    session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();
			
			Query query1 = session.createQuery("from ENS_AreaGroupBean where PLT='"+users.getPlt()+"'order by AREA_NAME");
			sList = (ArrayList<ENS_AreaGroupBean>)query1.list();
			noMachin = sList.size();
			
			int b=1;
			for (ENS_AreaGroupBean lst : sList)
			{
				System.out.println(b + "Area Name = " + lst.getAREA_NAME());
				b++;
			}
			System.out.println("Number of Areas = " + noMachin);
			
			Query query = session.createQuery("from ENS_CalAreaGroupBean where PLT = '"+users.getPlt()+"' and "
					+ "AREA_DATE between '"+request.getParameter("date1")+"' "
					+ "and '"+request.getParameter("date2")+"' order by AREA_DATE,AREA_NAM ");
			mList = (ArrayList<ENS_CalAreaGroupBean>) query.list();
			
			System.out.println("S list Size = " + mList.size());
			
			dateDiff = (int) diff+3;
			
			int dateD = (int)diff+1;
			
			System.out.println("New Date Diff = " + dateDiff );
			System.out.println("Date d :"+dateD);
			
			int row=0,column=0;
			String temp = "";
			String nodeTemp ="";
			String tempDt ="";
			
			String[] arrDate = new String[dateD];
			String dateCk = "";
			int rowDt=0;
			
			arrData = new String[noMachin][dateDiff];
			
			for(ENS_CalAreaGroupBean sMstr1 : mList){
				if(!dateCk.equalsIgnoreCase(sMstr1.getAREA_DATE().toString())){ 
					dateCk = sMstr1.getAREA_DATE().toString();
					
					if(dateD>rowDt)
					{
						arrDate[rowDt] = dateCk;
						++rowDt;
					}
				}
			}
			String currentDate="";
			String found = "N";
			
			for(ENS_AreaGroupBean mMstr : sList)
			{
				System.out.println("Areas From Master = " + mMstr.getAREA_NAME());
				found = "N";
				for(ENS_CalAreaGroupBean sMstr : mList)
				{
					System.out.println("Areas from Master = " + sMstr.getAREA_NAM());
					if(sMstr.getAREA_NAM().equalsIgnoreCase(mMstr.getAREA_NAME()))
					{
						found = "Y";
						if(!temp.equalsIgnoreCase(sMstr.getAREA_NAM()))
						{	
							temp = sMstr.getAREA_NAM();
							
							arrData[row][column]=sMstr.getSEC();
							arrData[row][++column]=sMstr.getAREA_NAM();//.substring(3)
							
							currentDate = sMstr.getAREA_DATE().toString();
							
							for(int k=0;k<arrDate.length;k++)
							{
								System.out.println("C_Date = " + currentDate);
								System.out.println("arrDate = " + arrDate[k]);
								System.out.println("K = " + k);
								
								if(currentDate.equalsIgnoreCase(arrDate[k]))
								{
									arrData[row][++column]=sMstr.getTOTAL()+"";
									tempDt = sMstr.getAREA_DATE().toString();
									break;
								}
								else
								{
									arrData[row][++column]="-";
								}
							}
							
						}
						
						else if(!tempDt.equalsIgnoreCase(sMstr.getAREA_DATE().toString()))
						{
							tempDt = sMstr.getAREA_DATE().toString();
							System.out.println("tempDt = " + tempDt);
						
							arrData[row][++column]=sMstr.getTOTAL()+"";	
						}
						System.out.println("Temp date = " + tempDt);
						System.out.println("S Date = " + sMstr.getAREA_DATE().toString());
					}
				}
				if(found.equalsIgnoreCase("N"))
				{
					System.out.println("Not Found = " + mMstr.getAREA_NAME());
					arrData[row][column]=mMstr.getSEC();
					arrData[row][++column]=mMstr.getAREA_NAME();
					for(int k=0;k<arrDate.length;k++)
					{
						arrData[row][++column]="-";
					}
				}
				column=0;
				++row;
				
			}
			for(int r=0;r<arrData.length;r++)
			{
				for(int c=0;c<arrData[r].length;c++)
				{
					System.out.println(arrData[r][c]);
				}
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Generate Area Group Report Master  --> Exception" + ex.getMessage());
			ex.printStackTrace();
			
		}
		finally {
			
			try
			{
				ENS_hibernateFactory.close(session);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		return arrData;
	}

	@Override
	public ENS_CalAreaGroupBean getTotal(HttpServletRequest request) {
		Session session = null;
		ENS_CalAreaGroupBean llst = null;
		
		String equip = request.getParameter("equip_nam");
		String date1 = request.getParameter("date1");
		System.out.println("into group mapper get equipment :"+equip);
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
			Query query = session.createQuery("from ENS_CalAreaGroupBean where "
					+ "AREA_NAM ='" + request.getParameter("equip_nam") + "'"
					+ "and AREA_DATE = '"+request.getParameter("date1")+"'");
			llst = (ENS_CalAreaGroupBean) query.uniqueResult();
		}
		catch(Exception ex)
		{
			System.out.println("Get Area--> Exception" + ex.getMessage());
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
		return llst;
	}

	@Override
	public ArrayList<ENS_CalAreaGroupBean> genreport(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_CalAreaGroupBean> lstEquip = null;
		
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_CalAreaGroupBean "
						+ "where PLT= "+ users.getPlt() + ""
						+ "and AREA_DATE = '"+request.getParameter("date1")+"'");
				lstEquip = (ArrayList<ENS_CalAreaGroupBean>) query.list();
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Area Group Method --> Exception" + ex.getMessage());
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
		
		return lstEquip;
	}

	@Override
	public ArrayList<ENS_AreaTargetBean> getMonth(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AreaTargetBean> lstEquip = null;
		
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_AreaTargetBean "
						+ "where PLT= "+ users.getPlt() + ""
						+ "and MONTH = '"+request.getParameter("month")+"'");
				lstEquip = (ArrayList<ENS_AreaTargetBean>) query.list();
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Area Group Method --> Exception" + ex.getMessage());
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
		
		return lstEquip;
	}

}
