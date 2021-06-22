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
import beans.ENS_AddMachBean;
import beans.ENS_CalculateGroupBean;
import beans.ENS_Control_No;
import beans.ENS_SummaryBean;
import beans.ENS_UploadBean;
import beans.UserBean;
import dao.ENS_SummaryDao;
import utility.ENS_hibernateFactory;

public class ENS_SummaryReportMapper implements ENS_SummaryDao{
	
	public String msg = "";
	
	public ENS_SummaryReportMapper() {
		// TODO Auto-generated constructor stub
		
		ENS_hibernateFactory.buildIfNeeded();
	}
	
	private ENS_Control_No obj = null;
	
	private  void getControlsno(int plt, String table) 
	{
		Session session = null;
		session = ENS_hibernateFactory.openSession();
		Query query = session.createQuery("from ENS_Control_No where "
				+ " PLT = " + plt + " and CTRLNO_DOCUMENT='" + table + "'");
		obj = (ENS_Control_No) query.uniqueResult();
	}

	@Override
	public ArrayList<ENS_SummaryBean> genreport(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_SummaryBean> lstNodeno = null;
		
		String a = request.getParameter("date");
		String b = request.getParameter("sec");
		String c = request.getParameter("nod_nam");
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();
			
			if(a.equalsIgnoreCase("") && b.equalsIgnoreCase("--Select--") && c.equalsIgnoreCase(""))
			{
				Query query = session.createQuery("from ENS_SummaryBean "
						+ "where PLT= '"+users.getPlt()+"' "
						+ "order by SEC,NOD_NAM");
				lstNodeno = (ArrayList<ENS_SummaryBean>) query.list();
			}
			else if(c.equalsIgnoreCase(""))
			{
				Query query = session.createQuery("from ENS_SummaryBean "
						+ "where PLT= '"+users.getPlt()+"' "
						+ "and DATE = '"+request.getParameter("date")+"'"
						+ "and SEC = '"+request.getParameter("sec")+"' "
						+ "order by SEC,NOD_NAM");
				lstNodeno = (ArrayList<ENS_SummaryBean>) query.list();
			}
			else if(a.equalsIgnoreCase(""))
			{
				Query query = session.createQuery("from ENS_SummaryBean "
						+ "where PLT= '"+users.getPlt()+"' "
						+ "and SEC = '"+request.getParameter("sec")+"' "
						+ "and NOD_NAM = '"+request.getParameter("nod_nam")+"'"
						+ "order by SEC,NOD_NAM");
				lstNodeno = (ArrayList<ENS_SummaryBean>) query.list();
			}
			else
			{
				Query query = session.createQuery("from ENS_SummaryBean "
						+ "where PLT= '"+users.getPlt()+"' "
						+ "and DATE = '"+request.getParameter("date")+"'"
						+ "and SEC = '"+request.getParameter("sec")+"' "
						+ "and NOD_NAM = '"+request.getParameter("nod_nam")+"'"
						+ "order by SEC,NOD_NAM");
				lstNodeno = (ArrayList<ENS_SummaryBean>) query.list();
			}

		}
		catch(Exception ex)
		{
			System.out.println("Summary Method --> Exception" + ex.getMessage());
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
		
		return lstNodeno;
	}

	@Override
	public ArrayList<ENS_SummaryBean> getNode(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_SummaryBean> lstCat = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();

			Query query = session.createQuery("from ENS_SummaryBean "
					+ "where PLT=" + users.getPlt() + "and FIN_YR = "+users.getFinyear()+" and "
					+ " DATE='" + request.getParameter("date")+"'");
			lstCat = (ArrayList<ENS_SummaryBean>) query.list();
		}
		catch(Exception ex)
		{
			System.out.println("Summary method --> Exception" + ex.getMessage());
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

	@Override
	public String insSummary(UserBean users, String sec, String nName, String mName, double diff, String dt) {
		// TODO Auto-generated method stub
		
		ENS_SummaryBean sumBean = new ENS_SummaryBean();
		
		Session session = null;
		Transaction tx = null;
		String msg = "";
		try
		{
			SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd"); 
			Date d2 = sdfo.parse(dt);
			session = ENS_hibernateFactory.openSession();
			tx = (Transaction) session.beginTransaction();
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_SummaryMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			sumBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			sumBean.setPLT(users.getPlt());
			sumBean.setFIN_YR(users.getFinyear());
			sumBean.setSEC(sec);
			sumBean.setNOD_NAM(nName);
			sumBean.setMACH_NAM(mName);
			sumBean.setDIFF(diff);
			sumBean.setDATE(d2);
			sumBean.setFLAG("Y");
			sumBean.setUPD_ON(new Date());
			sumBean.setUPD_BY(users.getUid());
			
			session.saveOrUpdate(sumBean);
			session.flush();
			tx.commit();
			msg = "<font color='green' style='font-size: 14pt;'><b>Data Inserted Successfully.</b></font>";
			
			obj.setCTRLNO_NEXT_NO(obj.getCTRLNO_NEXT_NO()+1);
			session.saveOrUpdate(obj);
			session.flush();
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception fired" + ex.getMessage());
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

	@Override
	public String[][] genreportMaster(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_SummaryBean> sList = null;
		ArrayList<ENS_AddMachBean> mList = null;
		int noMachin = 0;
		int dateDiff = 0;
		
		String[][] arrData=null;
		
		
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date firstDate = sdf.parse(request.getParameter("date1"));
		    Date secondDate = sdf.parse(request.getParameter("date2"));
		 
		    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		    
		    //System.out.println("Diff Dates = " + diff);		    
		    
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();
			
			Query query1 = session.createQuery("from ENS_AddMachBean where PLT='"+users.getPlt()+"'and SEC='"+request.getParameter("section1")+"' order by nod_nam,mach_nam");
			
			mList = (ArrayList<ENS_AddMachBean>) query1.list();
			noMachin = mList.size();
			
			int b=1;
			for (ENS_AddMachBean lst : mList)
			{
				System.out.println(b + "Node = " + lst.getNOD_NAM() + "Machine = " + lst.getMACH_NAM());
				b++;
			}
						
			//System.out.println("Number of Machines = " + noMachin);
			
			Query query = session.createQuery("from ENS_SummaryBean where PLT = '"+users.getPlt()+"' and"
					+ " SEC = '"+request.getParameter("section1")+"' and "
					+ "DATE between '"+request.getParameter("date1")+"' "
					+ "and '"+request.getParameter("date2")+"' order by NOD_NAM, MACH_NAM, DATE ");
			sList = (ArrayList<ENS_SummaryBean>) query.list();	
			
			//System.out.println("S list Size = " + sList.size());
			
			dateDiff = (int) diff+3;
			
			int dateD = (int)diff+1;
			
			//System.out.println("New Date Diff = " + dateDiff );
			//System.out.println("Date D = "+dateD);
			
			int row=0,column=0;
			String temp = "";
			String nodeTemp ="";
			String tempDt ="";
			
			String[] arrDate = new String[dateD];
			String dateCk = "";
			int rowDt=0;
			
			arrData = new String[noMachin][dateDiff];
			
			for(ENS_SummaryBean sMstr1 : sList){
				if(!dateCk.equalsIgnoreCase(sMstr1.getDATE().toString())){ 
					dateCk = sMstr1.getDATE().toString();
					
					if(dateD>rowDt)
					{
						arrDate[rowDt] = dateCk;
						++rowDt;
					}
				}
			}
			
			String currentDate="";
			String found = "N";
			for(ENS_AddMachBean mMstr : mList)
			{
				//System.out.println("Machine From mMaster = " + mMstr.getMACH_NAM());
				found = "N";
				for(ENS_SummaryBean sMstr : sList)
				{	
					//System.out.println("Machine from sMaster = " + sMstr.getMACH_NAM());
					
					if(sMstr.getMACH_NAM().equalsIgnoreCase(mMstr.getMACH_NAM()))
					{
						found = "Y";
						if(!temp.equalsIgnoreCase(sMstr.getMACH_NAM()))
						{	
							temp = sMstr.getMACH_NAM();
							
							arrData[row][column]=sMstr.getNOD_NAM();
							arrData[row][++column]=sMstr.getMACH_NAM();
							
							currentDate = sMstr.getDATE().toString();
							
							for(int k=0;k<arrDate.length;k++)
							{
								//System.out.println("C_Date = " + currentDate);
								//System.out.println("arrDate = " + arrDate[k]);
								//System.out.println("K = " + k);
								
								if(currentDate.equalsIgnoreCase(arrDate[k]))
								{
									arrData[row][++column]=sMstr.getDIFF()+"";
									tempDt = sMstr.getDATE().toString();
									break;
								}
								else
								{
									arrData[row][++column]="-";
								}
							}
							
						}
						
						else if(!tempDt.equalsIgnoreCase(sMstr.getDATE().toString()))
						{
							tempDt = sMstr.getDATE().toString();
							//System.out.println("tempDt = " + tempDt);
						
							arrData[row][++column]=sMstr.getDIFF()+"";	
						}
						//System.out.println("Temp date = " + tempDt);
						//System.out.println("S Date = " + sMstr.getDATE().toString());
					}
				}
				if(found.equalsIgnoreCase("N"))
				{
					//System.out.println("Not Found = " + mMstr.getMACH_NAM());
					arrData[row][column]=mMstr.getNOD_NAM();
					arrData[row][++column]=mMstr.getMACH_NAM();
					for(int k=0;k<arrDate.length;k++)
					{
						arrData[row][++column]="-";
					}
				}
				column=0;
				++row;
			}
			/*for(int r=0;r<arrData.length;r++)
			{
				for(int c=0;c<arrData[r].length;c++)
				{
					System.out.println(arrData[r][c]);
				}
			}*/
		}
		catch(Exception ex)
		{
			System.out.println("Generate Report Master  --> Exception" + ex.getMessage());
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
	public ArrayList<ENS_SummaryBean> getDiff(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_SummaryBean> lstgrp = null;

		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_AddGroupBean "
						+ "where PLT= "+ users.getPlt() + ""
						+ "and MACH_NAM("+request.getAttribute("mixerplus")+")");
				lstgrp = (ArrayList<ENS_SummaryBean>) query.list();
				
		}
		catch(Exception ex)
		{
			System.out.println("get mixer 1 Method --> Exception" + ex.getMessage());
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
	public String insGroup(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session = null;
		String msg = "";
		int plusnam = 0;
		String c1 = "";
		String c2 = "";
		String c3 = "";
		Transaction tx = null;
		
		ArrayList<ENS_AddGroupBean> pList = null;
		
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
			
			Query pquery = session.createQuery(" from ENS_AddGroupBean "
					+ " where PLT= '"+ users.getPlt() +"'");
			pList = (ArrayList<ENS_AddGroupBean>) pquery.list();
			plusnam  = pList.size();
			
			System.out.println("Mixers of plus Groups :"+plusnam);
			
			for(int i=0;i<dates.size();i++)
			{
				 Date lDate =(Date)dates.get(i);
		         String ds = formatter.format(lDate);   
		         System.out.println(" Date is ..." + ds);
		
				for(ENS_AddGroupBean val : pList)
				{	
				
					c1 = val.getNAM_PLUS();
					c2 = val.getNAM_MINUS();
					c3 = val.getEQUIP_NAM();
					System.out.println("Equipment name :-"+c3);
					System.out.println("nam plus :-"+c1);
					System.out.println("nam minus :-"+c2);
			
					Query q1 = session.createQuery("select sum(DIFF) from ENS_SummaryBean where "
							+ "MACH_NAM in ("+c1+") "
							+ "and DATE = '"+ds+"'");
					
					List<Double> list1 = q1.list();
					Double add = list1.get(0);
					
					if(add == null)
					{
						add = 0.0;
					}
					System.out.println("addd : "+add);

					Query q2 = session.createQuery("select sum(DIFF) from ENS_SummaryBean where "
							+ "MACH_NAM in ("+c2+")"
							+ "and DATE = '"+ds+"'");
				
					List<Double> list2 = q2.list();
					Double sub = list2.get(0);
					
					if(sub == null)
					{
						sub=0.0;
					}
					System.out.println("subtract : "+sub);
					
					Double ans = add - sub;
					System.out.println("total :"+ans);
					
					
					ENS_CalculateGroupBean calBean = new ENS_CalculateGroupBean();
					
					
					tx = (Transaction) session.beginTransaction();	
					HttpSession httpSession = request.getSession();
					UserBean user = (UserBean) httpSession.getAttribute("Users");
					getControlsno(users.getPlt(),"ENS_CalGroupMst");
					
					if (obj == null)
					{
						session = ENS_hibernateFactory.openSession();
						tx = (Transaction) session.beginTransaction();
						System.out.println("into obj...");
						obj = new ENS_Control_No();
						obj.setPLT(users.getPlt());
						obj.setFIN_YR(users.getFinyear());
						obj.setCTRLNO_DOCUMENT("ENS_CalGroupMst");
						obj.setCTRLNO_NEXT_NO(0);
					}
					
					calBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
					calBean.setPLT(users.getPlt());
					calBean.setSEC(users.getSec());
					calBean.setEQUIP_NAM(c3);
					calBean.setTOTAL(ans);
					calBean.setGRP_DATE(dates.get(i));
					calBean.setFLAG("y");
					calBean.setUPD_BY(users.getUid());
					calBean.setUPD_ON(new Date());
					session.saveOrUpdate(calBean);
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
			System.out.println("insert group --> Exception" + ex.getMessage());
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
		return msg;
	}

	@Override
	public ENS_SummaryBean getReadings(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ENS_SummaryBean lst = null;
		
		String a = request.getParameter("nodnam");
		System.out.println("name in mapper :"+a);
		
		String arg[] = a.split("~");
	
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
			Query query = session.createQuery("from ENS_SummaryBean where "
					+ "NOD_NAM ='" + arg[0] + "'"
					+ "and MACH_NAM = '"+arg[1]+"'"
					+ "and DATE = '"+request.getParameter("date1")+"'");
			lst = (ENS_SummaryBean) query.uniqueResult();
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
		return lst;
	}

	@Override
	public String editData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		String msg = "";
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			tx = (Transaction) session.beginTransaction();
	
			ENS_SummaryBean sumBean = new ENS_SummaryBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
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
			
			String b = request.getParameter("sec");
			System.out.println("Section :"+b);
			
			String c = request.getParameter("nod_nam");
			System.out.println("Node name :"+c);
			
			String d = request.getParameter("mach_nam");
			System.out.println("Machine name :"+d);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date firstDate = sdf.parse(request.getParameter("date1"));
		    System.out.println("Date "+firstDate);
			
			
			String a = request.getParameter("read");
			double r = Double.parseDouble(a);
			System.out.println("Value of Readings :"+r);
			
			sumBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			sumBean.setPLT(users.getPlt());
			sumBean.setSEC(request.getParameter("sec"));
			sumBean.setNOD_NAM(request.getParameter("nod_nam"));
			sumBean.setMACH_NAM(request.getParameter("mach_nam"));
			sumBean.setDATE(firstDate);
			sumBean.setDIFF(r);
			sumBean.setFLAG("Y");
			sumBean.setUPD_ON(new Date());
			sumBean.setUPD_BY(users.getUid());
			
			session.update(sumBean);
			session.flush();
			tx.commit();
			msg = "Records Updated Successfully";
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception fired" + ex.getMessage());
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
		return msg;
	}
}
