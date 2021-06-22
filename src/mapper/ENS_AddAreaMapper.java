package mapper;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_AddAreaBean;
import beans.ENS_Control_No;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_AddAreaMapper implements dao.ENS_AddAreaDao {
	
	public String msg="";
	
	
	 public ENS_AddAreaMapper() {
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
	public String insArea(HttpServletRequest request) {
		
		Session session = null;
		Transaction tx = null;
		String msg = "";
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			tx = (Transaction) session.beginTransaction();			
			
			ENS_AddAreaBean areaBean = new ENS_AddAreaBean();
				
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_AreaMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_AreaMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			areaBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			areaBean.setPLT(users.getPlt());
			areaBean.setSEC(users.getSec());
			areaBean.setAREA_NAME(request.getParameter("area_nam"));
			areaBean.setFLAG("Y");
			areaBean.setUPD_ON(new Date());
			areaBean.setUPD_BY(users.getUid());
			
			session.save(areaBean);
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
	public ArrayList<ENS_AddAreaBean> genreport(UserBean users, HttpServletRequest request) {
		Session session = null;
		ArrayList<ENS_AddAreaBean> lstArea = null;
		
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_AddAreaBean "
						+ "where PLT= "+ users.getPlt() + " ");
				lstArea = (ArrayList<ENS_AddAreaBean>) query.list();
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Equipment Method --> Exception" + ex.getMessage());
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
		
		return lstArea;
	}
}
