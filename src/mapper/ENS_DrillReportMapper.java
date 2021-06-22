package mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.ENS_AddGroupBean;
import beans.ENS_AddSpcGroupBean;
import beans.ENS_AreaGroupBean;
import beans.ENS_CalAreaGroupBean;
import beans.ENS_CalculateGroupBean;
import beans.ENS_SummaryBean;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_DrillReportMapper implements dao.ENS_DrillReportDao{
	
	public String msg="";
	
	
	public ENS_DrillReportMapper() {
		// TODO Auto-generated constructor stub
		 
		ENS_hibernateFactory.buildIfNeeded();
	}
	 
	@Override
	public ArrayList<ENS_AddGroupBean> genereport(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		String a = "";
		String b = "";
		ArrayList<ENS_AddGroupBean> lstgrp = null;
		ArrayList<ENS_SummaryBean> lstsum = null;
		String c = request.getParameter("ename");
		System.out.println("Equipment Name :"+c);
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_AddGroupBean "
						+ "where PLT= "+ users.getPlt() + " "
						+ "and EQUIP_NAM = '"+request.getParameter("ename")+"'");
				lstgrp = (ArrayList<ENS_AddGroupBean>) query.list();
				
				for(ENS_AddGroupBean grpBean : lstgrp)
				{
					a = grpBean.getFIDD_NAM_PLUS();
					System.out.println("Plus :"+a);
					
					String x = grpBean.getFIDD_NAM_PLUS();
					String arg[] = x.split(",");
					
					for(int i=0;i<arg.length;i++)
					{
						System.out.println("Spllited values :"+arg[i]);
					
						Query q1 = session.createQuery("from ENS_SummaryBean "
								+ "where PLT= "+ users.getPlt() + " "
								+ "and MACH_NAM = '"+arg[i]+"'"
								+ "and DATE between '"+request.getParameter("date1")+"'"
								+ "and '"+request.getParameter("date2")+"'");
						lstsum = (ArrayList<ENS_SummaryBean>) q1.list();
						
						for(ENS_SummaryBean sumBean : lstsum)
						{
							System.out.println("Date :"+sumBean.getDATE());
							System.out.println("Machine Name :"+sumBean.getMACH_NAM());
							System.out.println("Summary Differenece :"+sumBean.getDIFF());
						
						}
					}
				}
				for(ENS_AddGroupBean grpBean : lstgrp)
				{
					b = grpBean.getFIDD_NAM_MINUS();
					System.out.println("minus :"+b);
					
					String x = grpBean.getFIDD_NAM_MINUS();
					String arg[] = x.split(",");
					
					for(int i=0;i<arg.length;i++)
					{
						System.out.println("Spllited values :"+arg[i]);
					
					
						Query q1 = session.createQuery("from ENS_SummaryBean "
								+ "where PLT= "+ users.getPlt() + " "
								+ "and MACH_NAM = '"+arg[i]+"'"
								+ "and DATE between '"+request.getParameter("date1")+"'"
								+ "and '"+request.getParameter("date2")+"'");
						lstsum = (ArrayList<ENS_SummaryBean>) q1.list();
						
						for(ENS_SummaryBean sumBean : lstsum)
						{
							System.out.println("Date :"+sumBean.getDATE());
							System.out.println("Machine Name :"+sumBean.getMACH_NAM());
							System.out.println("Summary Differenece :"+sumBean.getDIFF());
						
						}
					
					}
					
				}
		}
		catch(Exception ex)
		{
			System.out.println("drill report Method --> Exception" + ex.getMessage());
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
	public String[][] genreportMaster(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AddGroupBean> mList = null;
		ArrayList<ENS_SummaryBean> sListOld = null;
		ArrayList<ENS_SummaryBean> sListOld1 = null;
		ArrayList<ENS_SummaryBean> sList = new ArrayList<ENS_SummaryBean>();
		
		int noMachin = 0;
		int dateDiff = 0;
		String arg[] = null;
		String arg1[] = null;
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
			
			Query query = session.createQuery("from ENS_AddGroupBean "
					+ "where PLT= "+ users.getPlt() + " "
					+ "and EQUIP_NAM = '"+request.getParameter("ename")+"'");
			mList = (ArrayList<ENS_AddGroupBean>) query.list();
			noMachin = mList.size();
			
			
			int b=1;
			for (ENS_AddGroupBean lst : mList)
			{
				System.out.println(b + "Equipment Name = " + lst.getEQUIP_NAM() 
									+ "Fedders in Addition = " + lst.getFIDD_NAM_PLUS()
									+ "Fedders in Subtraction = "+ lst.getFIDD_NAM_MINUS());
				
				String x = lst.getFIDD_NAM_PLUS();
				arg = x.split(",");
				
				for(int i=0;i<arg.length;i++)
				{
					
					System.out.println("Splited values in addtion :"+arg[i]);
					//System.out.println("Size of arg :"+noMachin);
					
					Query q1 = session.createQuery("from ENS_SummaryBean "
							+ "where PLT= "+ users.getPlt() + " "
							+ "and MACH_NAM = '"+arg[i]+"'"
							+ "and DATE between '"+request.getParameter("date1")+"'"
							+ "and '"+request.getParameter("date2")+"'");
					sListOld = (ArrayList<ENS_SummaryBean>) q1.list();
					
					sList.addAll(sListOld);
					
				}
				
				String y = lst.getFIDD_NAM_MINUS();
				System.out.println("Value of Y :"+y);
				arg1 = y.split(",");
				if(arg1 == null)
				{
					System.out.println("Fidders not Found :");
				}
				else
				{
					
					for(int i=0;i<arg1.length;i++)
					{
					
						System.out.println("Splited values in subtraction :"+arg1[i]);
						//System.out.println("Size of arg :"+noMachin);
					
						Query q2 = session.createQuery("from ENS_SummaryBean "
							+ "where PLT= "+ users.getPlt() + " "
							+ "and MACH_NAM = '"+arg1[i]+"'"
							+ "and DATE between '"+request.getParameter("date1")+"'"
							+ "and '"+request.getParameter("date2")+"'");
						sListOld1 = (ArrayList<ENS_SummaryBean>) q2.list();
					
						sList.addAll(sListOld1);
					}
				}
			}
			
			
			dateDiff = (int) diff+3;
			
			int dateD = (int)diff+1;
			
			//System.out.println("New Date Diff = " + dateDiff );
			//System.out.println("Date D = "+dateD);
			
			int row=0,column=0;
			String temp = "";
			String temp2 = "";
			String nodeTemp ="";
			String tempDt ="";
			
			
			
			String[] arrDate = new String[dateD];
			String dateCk = "";
			int rowDt=0;
			
			System.out.println("Length of arg "+arg.length);
			System.out.println("Length of arg1 "+arg1.length);
					
			arrData = new String[arg.length][dateDiff];
			
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
				
				found = "N";

				int i = 0;
				for(ENS_SummaryBean sMstr : sList)
				{	
					
					System.out.println("Machine from sMaster = " + sMstr.getMACH_NAM());
					temp2 = sMstr.getMACH_NAM();
					if(!temp2.equalsIgnoreCase(arg[i]))
					{
						temp2 = sMstr.getMACH_NAM();
						i++;
						column=0;
						++row;
					}
					if(sMstr.getMACH_NAM().equalsIgnoreCase(arg[i]))
					{	
						found = "Y";
						
						if(!temp.equalsIgnoreCase(sMstr.getMACH_NAM()))
						{	
							
							temp = sMstr.getMACH_NAM();
							
							arrData[row][column]=sMstr.getSEC();
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
					/*arrData[row][column]=mMstr.getNOD_NAM();
					arrData[row][++column]=mMstr.getMACH_NAM();*/
					for(int k=0;k<arrDate.length;k++)
					{
						arrData[row][++column]="-";
					}
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
	public String[][] genreportArea(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AreaGroupBean> mList = null;
		ArrayList<ENS_CalculateGroupBean> sListOld = null;
		ArrayList<ENS_CalculateGroupBean> sListOld1 = null;
		ArrayList<ENS_CalculateGroupBean> sList = new ArrayList<ENS_CalculateGroupBean>();
		int noMachin = 0;
		int dateDiff = 0;
		String arg[] = null;
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
			
			Query query = session.createQuery("from ENS_AreaGroupBean "
					+ "where PLT= "+ users.getPlt() + " "
					+ "and AREA_NAME = '"+request.getParameter("ename")+"'");
			mList = (ArrayList<ENS_AreaGroupBean>) query.list();
			noMachin = mList.size();
			
			System.out.println("Size of Area Group Bean :"+noMachin);
			
			
			int b=1;
			for (ENS_AreaGroupBean lst : mList)
			{
				System.out.println(b + "Area Name = " + lst.getAREA_NAME() 
									+ "Equipment in Addition = " + lst.getEQUIP_NAME());
				
				String x = lst.getEQUIP_NAME();
				arg = x.split(",");
				
				for(int i=0;i<arg.length;i++)
				{
					
					System.out.println("Splited values in addtion :"+arg[i]);
					//System.out.println("Size of arg :"+noMachin);
					
					Query q1 = session.createQuery("from ENS_CalculateGroupBean "
							+ "where PLT= "+ users.getPlt() + " "
							+ "and EQUIP_NAM = '"+arg[i]+"'"
							+ "and GRP_DATE between '"+request.getParameter("date1")+"'"
							+ "and '"+request.getParameter("date2")+"'");
					sListOld = (ArrayList<ENS_CalculateGroupBean>) q1.list();
					sList.addAll(sListOld);
					
				}
			}
			
			
			dateDiff = (int) diff+3;
			
			int dateD = (int)diff+1;
			
			//System.out.println("New Date Diff = " + dateDiff );
			//System.out.println("Date D = "+dateD);
			
			int row=0,column=0;
			String temp = "";
			String temp2 = "";
			String nodeTemp ="";
			String tempDt ="";
			
			
			
			String[] arrDate = new String[dateD];
			String dateCk = "";
			int rowDt=0;
			
			System.out.println("Length of arg "+arg.length);
					
			arrData = new String[arg.length][dateDiff];
			
			for(ENS_CalculateGroupBean sMstr1 : sList){
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
				
				found = "N";

				int i = 0;
				for(ENS_CalculateGroupBean sMstr : sList)
				{	
					
					System.out.println("Machine from sMaster = " + sMstr.getEQUIP_NAM());
					temp2 = sMstr.getEQUIP_NAM();
					if(!temp2.equalsIgnoreCase(arg[i]))
					{
						temp2 = sMstr.getEQUIP_NAM();
						i++;
						column=0;
						++row;
					}
					if(sMstr.getEQUIP_NAM().equalsIgnoreCase(arg[i]))
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
								//System.out.println("C_Date = " + currentDate);
								//System.out.println("arrDate = " + arrDate[k]);
								//System.out.println("K = " + k);
								
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
							//System.out.println("tempDt = " + tempDt);
						
							arrData[row][++column]=sMstr.getTOTAL()+"";	
						}
						//System.out.println("Temp date = " + tempDt);
						//System.out.println("S Date = " + sMstr.getDATE().toString());
						
					}
					
				}
				if(found.equalsIgnoreCase("N"))
				{
					//System.out.println("Not Found = " + mMstr.getMACH_NAM());
					/*arrData[row][column]=mMstr.getNOD_NAM();
					arrData[row][++column]=mMstr.getMACH_NAM();*/
					for(int k=0;k<arrDate.length;k++)
					{
						arrData[row][++column]="-";
					}
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
	public String[][] genreportSpc(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AddSpcGroupBean> mList = null;
		ArrayList<ENS_CalAreaGroupBean> sListOld = null;
		ArrayList<ENS_CalAreaGroupBean> sListOld1 = null;
		ArrayList<ENS_CalAreaGroupBean> sList = new ArrayList<ENS_CalAreaGroupBean>();
		int noMachin = 0;
		int dateDiff = 0;
		String arg[] = null;
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
			
			Query query = session.createQuery("from ENS_AddSpcGroupBean "
					+ "where PLT= "+ users.getPlt() + " ");
			mList = (ArrayList<ENS_AddSpcGroupBean>) query.list();
			noMachin = mList.size();
			
			System.out.println("Size of SPC Group Bean :"+noMachin);
			
			
			int b=1;
			for (ENS_AddSpcGroupBean lst : mList)
			{
				System.out.println(b + "Equipment in Addition = " + lst.getSPC_GRP());
				
				String x = lst.getSPC_GRP();
				arg = x.split(",");
				
				for(int i=0;i<arg.length;i++)
				{
					
					System.out.println("Splited values in addtion :"+arg[i]);
					//System.out.println("Size of arg :"+noMachin);
					
					Query q1 = session.createQuery("from ENS_CalAreaGroupBean "
							+ "where PLT= "+ users.getPlt() + " "
							+ "and AREA_NAM = '"+arg[i]+"'"
							+ "and AREA_DATE between '"+request.getParameter("date1")+"'"
							+ "and '"+request.getParameter("date2")+"'");
					sListOld = (ArrayList<ENS_CalAreaGroupBean>) q1.list();
					sList.addAll(sListOld);
					
				}
			}
			
			
			dateDiff = (int) diff+3;
			
			int dateD = (int)diff+1;
			
			//System.out.println("New Date Diff = " + dateDiff );
			//System.out.println("Date D = "+dateD);
			
			int row=0,column=0;
			String temp = "";
			String temp2 = "";
			String nodeTemp ="";
			String tempDt ="";
			
			
			
			String[] arrDate = new String[dateD];
			String dateCk = "";
			int rowDt=0;
			
			System.out.println("Length of arg "+arg.length);
					
			arrData = new String[arg.length][dateDiff];
			
			for(ENS_CalAreaGroupBean sMstr1 : sList){
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
				
				found = "N";

				int i = 0;
				for(ENS_CalAreaGroupBean sMstr : sList)
				{	
					
					System.out.println("Machine from sMaster = " + sMstr.getAREA_NAM());
					temp2 = sMstr.getAREA_NAM();
					if(!temp2.equalsIgnoreCase(arg[i]))
					{
						temp2 = sMstr.getAREA_NAM();
						i++;
						column=0;
						++row;
					}
					if(sMstr.getAREA_NAM().equalsIgnoreCase(arg[i]))
					{	
						found = "Y";
						
						if(!temp.equalsIgnoreCase(sMstr.getAREA_NAM()))
						{	
							
							temp = sMstr.getAREA_NAM();
							
							arrData[row][column]=sMstr.getSEC();
							arrData[row][++column]=sMstr.getAREA_NAM();
							
							currentDate = sMstr.getAREA_DATE().toString();
							
								for(int k=0;k<arrDate.length;k++)
								{
									//System.out.println("C_Date = " + currentDate);
									//System.out.println("arrDate = " + arrDate[k]);
									//System.out.println("K = " + k);
								
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
							//System.out.println("tempDt = " + tempDt);
						
							arrData[row][++column]=sMstr.getTOTAL()+"";	
						}
						//System.out.println("Temp date = " + tempDt);
						//System.out.println("S Date = " + sMstr.getDATE().toString());
						
					}
					
				}
				if(found.equalsIgnoreCase("N"))
				{
					//System.out.println("Not Found = " + mMstr.getMACH_NAM());
					/*arrData[row][column]=mMstr.getNOD_NAM();
					arrData[row][++column]=mMstr.getMACH_NAM();*/
					for(int k=0;k<arrDate.length;k++)
					{
						arrData[row][++column]="-";
					}
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
}
