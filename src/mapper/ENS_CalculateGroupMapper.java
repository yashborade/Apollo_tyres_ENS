package mapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_AddGroupBean;
import beans.ENS_AreaGroupBean;
import beans.ENS_CalAreaGroupBean;
import beans.ENS_CalculateGroupBean;
import beans.ENS_Control_No;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_CalculateGroupMapper implements dao.ENS_CalculateGroupDao{
	
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
	
	public ENS_CalculateGroupMapper() {
		// TODO Auto-generated constructor stub
		
		ENS_hibernateFactory.buildIfNeeded();
	}

	@Override
	public ArrayList<ENS_CalculateGroupBean> genreport(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_CalculateGroupBean> lstgrp = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();

			Query query = session.createQuery("from ENS_CalculateGroupBean "
					+ "where PLT= '"+users.getPlt()+"' "
					+ "and GRP_DATE between '"+request.getParameter("date1")+"' "
					+ "and '"+request.getParameter("date2")+"'");
			lstgrp = (ArrayList<ENS_CalculateGroupBean>) query.list();
		}
		catch(Exception ex)
		{
			System.out.println("Calclate group Report --> Exception" + ex.getMessage());
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
		
		return lstgrp;
	}
	
	@Override
	public String[][] genereportMaster(UserBean users, HttpServletRequest request) {
		
		Session session = null;
		ArrayList<ENS_AddGroupBean> sList = null;
		ArrayList<ENS_CalculateGroupBean> mList = null;
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
			
			Query query1 = session.createQuery("from ENS_AddGroupBean where PLT='"+users.getPlt()+"'order by EQUIP_NAM");
			sList = (ArrayList<ENS_AddGroupBean>)query1.list();
			noMachin = sList.size();
			
			int b=1;
			for (ENS_AddGroupBean lst : sList)
			{
				System.out.println(b + "Mixer = " + lst.getEQUIP_NAM());
				b++;
			}
			System.out.println("Number of Mixer = " + noMachin);
			
			Query query = session.createQuery("from ENS_CalculateGroupBean where PLT = '"+users.getPlt()+"' and "
					+ "GRP_DATE between '"+request.getParameter("date1")+"' "
					+ "and '"+request.getParameter("date2")+"' order by GRP_DATE,EQUIP_NAM ");
			mList = (ArrayList<ENS_CalculateGroupBean>) query.list();
			
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
			
			for(ENS_CalculateGroupBean sMstr1 : mList){
				if(!dateCk.equalsIgnoreCase(sMstr1.getGRP_DATE().toString())){ 
					dateCk = sMstr1.getGRP_DATE().toString();
					
					if(dateD>rowDt)
					{
						arrDate[rowDt] = dateCk;
						++rowDt;
					}
				}
			}
			
			String currentDate="";
			String found = "N";
			for(ENS_AddGroupBean mMstr : sList)
			{
				System.out.println("Machine From mMaster = " + mMstr.getEQUIP_NAM());
				found = "N";
				for(ENS_CalculateGroupBean sMstr : mList)
				{	
					System.out.println("Machine from sMaster = " + sMstr.getEQUIP_NAM());
					
					if(sMstr.getEQUIP_NAM().equalsIgnoreCase(mMstr.getEQUIP_NAM()))
					{
						found = "Y";
						if(!temp.equalsIgnoreCase(sMstr.getEQUIP_NAM()))
						{	
							temp = sMstr.getEQUIP_NAM();
							
							arrData[row][column]=sMstr.getSEC();
							arrData[row][++column]=sMstr.getEQUIP_NAM();
							
							currentDate = sMstr.getGRP_DATE().toString();
							
							for(int k=0;k<arrDate.length;k++)
							{
								System.out.println("C_Date = " + currentDate);
								System.out.println("arrDate = " + arrDate[k]);
								System.out.println("K = " + k);
								
								if(currentDate.equalsIgnoreCase(arrDate[k]))
								{
									arrData[row][++column]=sMstr.getTOTAL()+"";
									tempDt = sMstr.getGRP_DATE().toString();
									break;
								}
								else
								{
									arrData[row][++column]="-";
								}
							}
							
						}
						
						else if(!tempDt.equalsIgnoreCase(sMstr.getGRP_DATE().toString()))
						{
							tempDt = sMstr.getGRP_DATE().toString();
							System.out.println("tempDt = " + tempDt);
						
							arrData[row][++column]=sMstr.getTOTAL()+"";	
						}
						System.out.println("Temp date = " + tempDt);
						System.out.println("S Date = " + sMstr.getGRP_DATE().toString());
					}
				}
				if(found.equalsIgnoreCase("N"))
				{
					System.out.println("Not Found = " + mMstr.getEQUIP_NAM());
					arrData[row][column]=mMstr.getSEC();
					arrData[row][++column]=mMstr.getEQUIP_NAM();
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
			System.out.println("Generate Group Report Master  --> Exception" + ex.getMessage());
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
		
		System.out.println("Array Size in Mapper's End = " + arrData.length);
		return arrData;
	}

	@Override
	public ENS_CalculateGroupBean getReadings(HttpServletRequest request) {
		Session session = null;
		ENS_CalculateGroupBean llst = null;
		
		String equip = request.getParameter("equip_nam");
		String date1 = request.getParameter("date1");
		System.out.println("into group mapper get equipment :"+equip);
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
			Query query = session.createQuery("from ENS_CalculateGroupBean where "
					+ "EQUIP_NAM ='" + request.getParameter("equip_nam") + "'"
					+ "and GRP_DATE = '"+request.getParameter("date1")+"'");
			llst = (ENS_CalculateGroupBean) query.uniqueResult();
		}
		catch(Exception ex)
		{
			System.out.println("Get Equipment --> Exception" + ex.getMessage());
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
	public String insAreagroup(UserBean users,HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session = null;
		String msg = "";
		int plusnam = 0;
		String c1 = "";
		String c2 = "";
		String c3 = "";
		Transaction tx = null;
		
		ArrayList<ENS_AreaGroupBean> pList = null;
		
		try
		{
			
			Calendar cal = Calendar.getInstance();
		    
		    List<Date> dates = new ArrayList<Date>();

		    String str_date = request.getParameter("date1");
		    String end_date = request.getParameter("date2");
		    
		    System.out.println(str_date);
		    System.out.println(end_date);

		    DateFormat formatter ; 
		    
		    formatter = new SimpleDateFormat("yyyy-MM-dd");
		    Date startDate = null;
		    try {
		     startDate = (Date)formatter.parse(str_date);
		    } catch (ParseException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    } 
		    Date endDate = null;
		    try {
		     endDate = (Date)formatter.parse(end_date);
		    } catch (ParseException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		    long interval = 24*1000 * 60 * 60; // 1 hour in millis
		    long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
		    long curTime = startDate.getTime();
		    while (curTime <= endTime) {
		        dates.add(new Date(curTime));
		        curTime += interval;
		    }
		    
		    session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();
			
			Query pquery = session.createQuery(" from ENS_AreaGroupBean "
					+ " where PLT= '"+ users.getPlt() +"'");
			pList = (ArrayList<ENS_AreaGroupBean>) pquery.list();
			plusnam  = pList.size();
			
			System.out.println("Mixers of plus Groups :"+plusnam);
			
			for(int i=0;i<dates.size();i++)
			{
				 Date lDate =(Date)dates.get(i);
		         String ds = formatter.format(lDate);   
		         System.out.println(" Date is ..." + ds);
		         
		         for(ENS_AreaGroupBean val : pList)
		         {
		        	 c1 = val.getAREA_NAME();
		        	 c2 = val.getEQUIP_NAME_COR();
		        	 
		        	 System.out.println("Area Name :"+c1);
		        	 System.out.println("Equipment Names :"+c2);
		        	 
		        	 Query q1 = session.createQuery("select sum(TOTAL) from ENS_CalculateGroupBean where "
								+ "EQUIP_NAM in ("+c2+") "
								+ "and GRP_DATE = '"+ds+"'");
						
						List<Double> list1 = q1.list();
						double add = list1.get(0);
						System.out.println("addd : "+add);
		        	 
						
						ENS_CalAreaGroupBean calArgrp = new ENS_CalAreaGroupBean();
						tx = (Transaction) session.beginTransaction();	
						HttpSession httpSession = request.getSession();
						UserBean user = (UserBean) httpSession.getAttribute("Users");
						getControlsno(users.getPlt(),"ENS_CalAreaGroupMst");
						
						if (obj == null)
						{
							session = ENS_hibernateFactory.openSession();
							tx = (Transaction) session.beginTransaction();
							System.out.println("into obj...");
							obj = new ENS_Control_No();
							obj.setPLT(users.getPlt());
							obj.setFIN_YR(users.getFinyear());
							obj.setCTRLNO_DOCUMENT("ENS_CalAreaGroupMst");
							obj.setCTRLNO_NEXT_NO(0);
						}
						
						calArgrp.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
						calArgrp.setPLT(users.getPlt());
						calArgrp.setSEC(users.getSec());
						calArgrp.setAREA_DATE(dates.get(i));
						calArgrp.setAREA_NAM(c1);
						calArgrp.setTOTAL(add);
						calArgrp.setFLAG("Y");
						calArgrp.setUPD_ON(new Date());
						calArgrp.setUPD_BY(users.getUid());
						session.saveOrUpdate(calArgrp);
						session.flush();
						tx.commit();
		         }
			}
			
		
		msg = "<font color='green' style='font-size: 14pt;'><b>Data Inserted Successfully.</b></font>";
		obj.setCTRLNO_NEXT_NO(obj.getCTRLNO_NEXT_NO()+1);
		session.saveOrUpdate(obj);
		session.flush();
			
		}
		catch(Exception ex)
		{
			System.out.println("insert Area group --> Exception" + ex.getMessage());
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
		return msg;
	}
	
}
