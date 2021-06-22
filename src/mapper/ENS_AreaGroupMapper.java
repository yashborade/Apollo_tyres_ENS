package mapper;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_AreaGroupBean;
import beans.ENS_Control_No;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_AreaGroupMapper implements dao.ENS_AreaGroupDao{
	
	public String msg="";
	
	
	  public ENS_AreaGroupMapper() {
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
		
		try{
			session = ENS_hibernateFactory.openSession();
			tx = (Transaction) session.beginTransaction();			

			ENS_AreaGroupBean areagrpBean = new ENS_AreaGroupBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_AreaGroupMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_AreaGroupMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			String str1 = request.getParameter("search");
			
			String arg[] = str1.split(",");
			
			String a = " ";
			
			int x = arg.length;
			int y = x - 1;
			
			for(int i=0;i<arg.length;i++)
			{
				if(i==y)
				{
					a = a+("'"+arg[i]+"'");	
				}
				else
				{
					a = a+("'"+arg[i]+"',");
				}
				
			}
			System.out.println("add :=="+a);
			
			areagrpBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			areagrpBean.setPLT(users.getPlt());
			areagrpBean.setSEC(users.getSec());
			areagrpBean.setAREA_NAME(request.getParameter("area_nam"));
			areagrpBean.setEQUIP_NAME(request.getParameter("search"));
			areagrpBean.setEQUIP_NAME_COR(a);
			areagrpBean.setFLAG("Y");
			areagrpBean.setUPD_ON(new Date());
			areagrpBean.setUPD_BY(users.getUid());
			
			session.save(areagrpBean);
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
	public ArrayList<ENS_AreaGroupBean> genereport(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AreaGroupBean> lstEquip = null;
		
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_AreaGroupBean "
						+ "where PLT= "+ users.getPlt() + " ");
				lstEquip = (ArrayList<ENS_AreaGroupBean>) query.list();
			
			
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
	public ENS_AreaGroupBean getArea(HttpServletRequest request) {
		Session session = null;
		ENS_AreaGroupBean lst = null;
		
		String equip = request.getParameter("equip_nam");
		System.out.println("into group mapper get equipment :"+equip);
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
			Query query = session.createQuery("from ENS_AreaGroupBean where "
					+ "AREA_NAME ='" + request.getParameter("equip_nam") + "'");
			lst = (ENS_AreaGroupBean) query.uniqueResult();
		}
		catch(Exception ex)
		{
			System.out.println("Get Area --> Exception" + ex.getMessage());
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

			ENS_AreaGroupBean areagrpBean = new ENS_AreaGroupBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_AreaGroupMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_AreaGroupMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			String str1 = request.getParameter("fiddadd");
			
			String arg[] = str1.split(",");
			
			String a = " ";
			
			int x = arg.length;
			int y = x - 1;
			
			for(int i=0;i<arg.length;i++)
			{
				if(i==y)
				{
					a = a+("'"+arg[i]+"'");	
				}
				else
				{
					a = a+("'"+arg[i]+"',");
				}
				
			}
			System.out.println("add :=="+a);
			
			areagrpBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			areagrpBean.setPLT(users.getPlt());
			areagrpBean.setSEC(users.getSec());
			areagrpBean.setAREA_NAME(request.getParameter("equip_nam"));
			areagrpBean.setEQUIP_NAME(request.getParameter("fiddadd"));
			areagrpBean.setEQUIP_NAME_COR(a);
			areagrpBean.setFLAG("Y");
			areagrpBean.setUPD_ON(new Date());
			areagrpBean.setUPD_BY(users.getUid());
			
			session.update(areagrpBean);
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
	public ENS_AreaGroupBean deletedata(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ENS_AreaGroupBean lstgrp = null;

		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("delete from ENS_AreaGroupBean where "
						+ "AREA_NAME = :area_nam");
				query.setString("area_nam", request.getParameter("area_nam"));
				int rowCount = query.executeUpdate();
				System.out.println("Rows Affected :"+ rowCount);
				
				
		}
		catch(Exception ex)
		{
			System.out.println("delete Method --> Exception" + ex.getMessage());
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
}
