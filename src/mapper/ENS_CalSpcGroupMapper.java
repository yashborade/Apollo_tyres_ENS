package mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_AddAreaBean;
import beans.ENS_AddSpcGroupBean;

import beans.ENS_CalAreaGroupBean;
import beans.ENS_CalSpcGroupBean;
import beans.ENS_Control_No;
import beans.ENS_SpcTargetBean;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_CalSpcGroupMapper implements dao.ENS_CalSpcGroupDao{
	
	public String msg="";
	
	
	  public ENS_CalSpcGroupMapper() {
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
	public String insData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session = null;
		Transaction tx = null;
		String msg = "";
		String a ="";
		String b = "";
		double x = 0;
		double y = 0;
		double ans = 0;
		double ans1 = 0;
		ArrayList<ENS_AddSpcGroupBean> spcList = null;
		ArrayList<ENS_CalAreaGroupBean> calList = null;
		
		
		try
		{
					

			ENS_CalSpcGroupBean spcBean = new ENS_CalSpcGroupBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_CalSpcGroupMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_CalSpcGroupMst");
				obj.setCTRLNO_NEXT_NO(0);
			}

			session = ENS_hibernateFactory.openSession();
			
			String z = request.getParameter("ton");
			System.out.println("Total ton "+z);
			
			y = Double.parseDouble(z);
			
			String p = request.getParameter("date1");
			SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd"); 
			Date d2 = sdfo.parse(p);
			
			int plt = users.getPlt();
			
			Query q1 = session.createQuery("from ENS_AddSpcGroupBean where "
					+ "PLT = '"+users.getPlt()+"'");
			spcList = (ArrayList<ENS_AddSpcGroupBean>) q1.list();
			int plusnam  = spcList.size();
			System.out.println("Size of Group :"+plusnam);
			
			for(ENS_AddSpcGroupBean val : spcList)
			{
				a = val.getSPC_GRP();
				System.out.println("Groups :"+a);
				
			}
			String arg[] = a.split(",");
			for(int i=0;i<arg.length;i++)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();	
				
				System.out.println("after splitting "+arg[i]);
				
				Query q2 = session.createQuery("from ENS_CalAreaGroupBean where "
						+ "PLT = '"+users.getPlt()+"' and "
						+ "AREA_DATE = '"+request.getParameter("date1")+"' "
						+ "and AREA_NAM = '"+arg[i]+"'");
				calList = (ArrayList<ENS_CalAreaGroupBean>)q2.list();
				int nam = calList.size();
				System.out.println("Array size in cal area :"+nam);
				
				for(ENS_CalAreaGroupBean val1 : calList)
				{
					 b = val1.getAREA_NAM();
					 x = val1.getTOTAL();
					System.out.println("Area name :"+b);
					System.out.println("Area Total :"+x);
					ans = x/y;
					ans1 = ans/1000;
					System.out.println("Total spc :"+ans1);
				}
				
				
				spcBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
				spcBean.setPLT(users.getPlt());
				spcBean.setSEC(users.getSec());
				spcBean.setAREA_NAME(b);
				spcBean.setSPC_DATE(d2);
				spcBean.setTOTAL_TON(y);
				spcBean.setTOTAL_SPC(ans1);
				spcBean.setFLAG("Y");
				spcBean.setUPD_ON(new Date());
				spcBean.setUPD_BY(users.getUid());
				session.save(spcBean);
				session.flush();
				tx.commit();
				
			}
			
			/*msg = "<font color='green' style='font-size: 14pt;'><b>Data Inserted Successfully.</b></font>";
			obj.setCTRLNO_NEXT_NO(obj.getCTRLNO_NEXT_NO()+1);
			session.save(obj);
			session.flush();	*/
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

	@Override
	public ArrayList<ENS_CalSpcGroupBean> genreport(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_CalSpcGroupBean> lstEquip = null;
		
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_CalSpcGroupBean "
						+ "where PLT= "+ users.getPlt() + ""
						+ "and SPC_DATE = '"+request.getParameter("date1")+"'");
				lstEquip = (ArrayList<ENS_CalSpcGroupBean>) query.list();
			
			
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
	public String[][] genereportMaster(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session = null;
		ArrayList<ENS_AddAreaBean> sList = null;
		ArrayList<ENS_CalSpcGroupBean> mList = null;
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
		    
		    System.out.println("Diff Dates = " + diff);	
		    
		    session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();
			
			Query query1 = session.createQuery("from ENS_AddAreaBean where PLT = "+users.getPlt()+" order by AREA_NAME");
			sList = (ArrayList<ENS_AddAreaBean>)query1.list();
			noMachin = sList.size();
			
			int b=1;
			for (ENS_AddAreaBean lst : sList)
			{
				System.out.println(b + "Mixer = " + lst.getAREA_NAME());
				b++;
			}
			System.out.println("Number of Mixer = " + noMachin);
			
			Query query = session.createQuery("from ENS_CalSpcGroupBean where PLT = '"+users.getPlt()+"' and "
					+ "SPC_DATE between '"+request.getParameter("date1")+"' "
					+ "and '"+request.getParameter("date2")+"' order by SPC_DATE,AREA_NAME ");
			mList = (ArrayList<ENS_CalSpcGroupBean>) query.list();
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
			
			for(ENS_CalSpcGroupBean sMstr1 : mList){
				if(!dateCk.equalsIgnoreCase(sMstr1.getSPC_DATE().toString())){ 
					dateCk = sMstr1.getSPC_DATE().toString();
					
					if(dateD>rowDt)
					{
						arrDate[rowDt] = dateCk;
						++rowDt;
					}
				}
			}
			
			String currentDate="";
			String found = "N";
			for(ENS_AddAreaBean mMstr : sList)
			{
				System.out.println("Machine From mMaster = " + mMstr.getAREA_NAME());
				found = "N";
				
				for(ENS_CalSpcGroupBean sMstr : mList)
				{
					System.out.println("Machine from sMaster = " + sMstr.getAREA_NAME());
					
					if(sMstr.getAREA_NAME().equalsIgnoreCase(mMstr.getAREA_NAME()))
					{
						found = "Y";
						if(!temp.equalsIgnoreCase(sMstr.getAREA_NAME()))
						{	
							temp = sMstr.getAREA_NAME();
							
							arrData[row][column]=sMstr.getSEC();
							arrData[row][++column]=sMstr.getAREA_NAME().substring(3);
							
							currentDate = sMstr.getSPC_DATE().toString();
							
							for(int k=0;k<arrDate.length;k++)
							{
								System.out.println("C_Date = " + currentDate);
								System.out.println("arrDate = " + arrDate[k]);
								System.out.println("K = " + k);
								
								if(currentDate.equalsIgnoreCase(arrDate[k]))
								{
									arrData[row][++column]=sMstr.getTOTAL_SPC()+"";
									tempDt = sMstr.getSPC_DATE().toString();
									break;
								}
								else
								{
									arrData[row][++column]="-";
								}
							}
							
						}
						
						else if(!tempDt.equalsIgnoreCase(sMstr.getSPC_DATE().toString()))
						{
							tempDt = sMstr.getSPC_DATE().toString();
							System.out.println("tempDt = " + tempDt);
						
							arrData[row][++column]=sMstr.getTOTAL_SPC()+"";	
						}
						System.out.println("Temp date = " + tempDt);
						System.out.println("S Date = " + sMstr.getSPC_DATE().toString());
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
		return arrData;
	}

	@Override
	public ArrayList<ENS_SpcTargetBean> getMonth(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_SpcTargetBean> lstEquip = null;
		
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_SpcTargetBean "
						+ "where PLT= "+ users.getPlt() + ""
						+ "and MONTH = '"+request.getParameter("month")+"'");
				lstEquip = (ArrayList<ENS_SpcTargetBean>) query.list();
			
			
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
