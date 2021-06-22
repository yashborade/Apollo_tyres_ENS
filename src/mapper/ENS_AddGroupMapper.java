package mapper;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_AddGroupBean;
import beans.ENS_Control_No;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_AddGroupMapper implements dao.ENS_AddGroupDao{
	
	public String msg="";
	
	
	  public ENS_AddGroupMapper() {
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
			
			ENS_AddGroupBean grpBean = new ENS_AddGroupBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_GroupMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_GroupMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			String str1 = request.getParameter("search");
			String str2 = request.getParameter("search1");		
			
			String arg[] = str1.split(",");
			String arg1[] = str2.split(",");
			
			String a = " ";
			String b = " ";
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
			
			int m = arg1.length;
			int n = m - 1;
			
			for(int i=0;i<arg1.length;i++)
			{
				if(i==n)
				{
					b = b+("'"+arg1[i]+"'");
				}
				else
				{
					b = b+("'"+arg1[i]+"',");
				}	
			}
			System.out.println("Subtract :=="+b);
			
			grpBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			grpBean.setPLT(users.getPlt());
			grpBean.setSEC(users.getSec());
			grpBean.setEQUIP_NAM(request.getParameter("eqp_nam"));
			grpBean.setNAM_PLUS(a);
			grpBean.setNAM_MINUS(b);
			grpBean.setFIDD_NAM_PLUS(request.getParameter("search"));
			grpBean.setFIDD_NAM_MINUS(request.getParameter("search1"));
			grpBean.setFLAG("Y");
			grpBean.setUPD_ON(new Date());
			grpBean.setUPD_BY(users.getPlt());
			

			session.save(grpBean);
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
	public ArrayList<ENS_AddGroupBean> genreport(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AddGroupBean> lstEquip = null;
		
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_AddGroupBean "
						+ "where PLT= "+ users.getPlt() + " ");
				lstEquip = (ArrayList<ENS_AddGroupBean>) query.list();
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Group Method --> Exception" + ex.getMessage());
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
	public String editData(HttpServletRequest request) {
		Session session = null;
		Transaction tx = null;
		String msg = "";
		
		try{
			session = ENS_hibernateFactory.openSession();
			tx = (Transaction) session.beginTransaction();			
			
			ENS_AddGroupBean grpBean = new ENS_AddGroupBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_GroupMst");
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_GroupMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			String str1 = request.getParameter("fiddadd");
			String str2 = request.getParameter("fiddsub");		
			
			String arg[] = str1.split(",");
			String arg1[] = str2.split(",");
			
			String a = " ";
			String b = " ";
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
			
			int m = arg1.length;
			int n = m - 1;
			
			for(int i=0;i<arg1.length;i++)
			{
				if(i==n)
				{
					b = b+("'"+arg1[i]+"'");
				}
				else
				{
					b = b+("'"+arg1[i]+"',");
				}	
			}
			System.out.println("Subtract :=="+b);
			
			grpBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			grpBean.setPLT(users.getPlt());
			grpBean.setSEC(users.getSec());
			grpBean.setEQUIP_NAM(request.getParameter("equip_nam"));
			grpBean.setFIDD_NAM_PLUS(request.getParameter("fiddadd"));
			grpBean.setFIDD_NAM_MINUS(request.getParameter("fiddsub"));
			grpBean.setNAM_PLUS(a);
			grpBean.setNAM_MINUS(b);
			grpBean.setFLAG("Y");
			grpBean.setUPD_ON(new Date());
			grpBean.setUPD_BY(users.getUid());
		

			session.update(grpBean);
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

	@Override
	public ENS_AddGroupBean getEquip(HttpServletRequest request) {
		Session session = null;
		ENS_AddGroupBean lst = null;
		
		String equip = request.getParameter("equip_nam");
		System.out.println("into group mapper get equipment :"+equip);
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
			Query query = session.createQuery("from ENS_AddGroupBean where "
					+ "EQUIP_NAM ='" + request.getParameter("equip_nam") + "'");
			lst = (ENS_AddGroupBean) query.uniqueResult();
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
	public ArrayList<ENS_AddGroupBean> getMixer(UserBean userBean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AddGroupBean> lstgrp = null;

		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("select NAM_PLUS from ENS_AddGroupBean "
						+ "where PLT= "+ userBean.getPlt() + ""
						+ "and EQUIP_NAM = '"+request.getParameter("equip_nam")+"'");
				lstgrp = (ArrayList<ENS_AddGroupBean>) query.list();
				
		}
		catch(Exception ex)
		{
			System.out.println("get mixer Method --> Exception" + ex.getMessage());
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
	public ArrayList<ENS_AddGroupBean> getMixer1(UserBean userBean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AddGroupBean> lstgrp = null;

		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("select NAM_MINUS from ENS_AddGroupBean "
						+ "where PLT= "+ userBean.getPlt() + ""
						+ "and EQUIP_NAM = '"+request.getParameter("equip_nam")+"'");
				lstgrp = (ArrayList<ENS_AddGroupBean>) query.list();
				
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
	public ENS_AddGroupBean deletedata(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ENS_AddGroupBean lstgrp = null;

		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("delete from ENS_AddGroupBean where "
						+ "EQUIP_NAM = :equip_nam");
				query.setString("equip_nam", request.getParameter("equip_nam"));
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