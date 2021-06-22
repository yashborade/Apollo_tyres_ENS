package mapper;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_AddSpcGroupBean;
import beans.ENS_Control_No;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_AddSpcGroupMapper implements dao.ENS_AddSpcGroupDao {
	
	public String msg="";
	
	
	  public ENS_AddSpcGroupMapper() {
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
		try
		{
			session = ENS_hibernateFactory.openSession();
			tx = (Transaction) session.beginTransaction();
			
			ENS_AddSpcGroupBean spcBean = new ENS_AddSpcGroupBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_SpcGroupMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_SpcGroupMst");
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
			
			spcBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			spcBean.setPLT(users.getPlt());
			spcBean.setSEC(users.getSec());
			spcBean.setSPC_GRP(request.getParameter("search"));
			spcBean.setSPC_GRP_CR(a);
			spcBean.setFLAG("Y");
			spcBean.setUPD_ON(new Date());
			spcBean.setUPD_BY(users.getUid());
			
			session.saveOrUpdate(spcBean);
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
	public ArrayList<ENS_AddSpcGroupBean> genereport(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AddSpcGroupBean> lstEquip = null;
		
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_AddSpcGroupBean "
						+ "where PLT= "+ users.getPlt() + " ");
				lstEquip = (ArrayList<ENS_AddSpcGroupBean>) query.list();
			
			
		}
		catch(Exception ex)
		{
			System.out.println("SPC Group Method --> Exception" + ex.getMessage());
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
	public ENS_AddSpcGroupBean getSpcgroup(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ENS_AddSpcGroupBean lst = null;
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
			Query query = session.createQuery("from ENS_AddSpcGroupBean");
			lst = (ENS_AddSpcGroupBean) query.uniqueResult();
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
			
			ENS_AddSpcGroupBean spcBean = new ENS_AddSpcGroupBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_SpcGroupMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_SpcGroupMst");
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
			
			spcBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			spcBean.setPLT(users.getPlt());
			spcBean.setSEC(users.getSec());
			spcBean.setSPC_GRP(request.getParameter("fiddadd"));
			spcBean.setSPC_GRP_CR(a);
			spcBean.setFLAG("Y");
			spcBean.setUPD_ON(new Date());
			spcBean.setUPD_BY(users.getUid());
			
			session.update(spcBean);
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
}
