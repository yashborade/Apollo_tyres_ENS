package mapper;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_AddEquipBean;
import beans.ENS_Control_No;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_AddEquipMapper implements dao.ENS_AddEquipDao {
	
	public String msg="";
	
	
	 public ENS_AddEquipMapper() {
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
	public String insEquip(HttpServletRequest request) {
		
		
		Session session = null;
		Transaction tx = null;
		String msg = "";
		
		try{
			session = ENS_hibernateFactory.openSession();
			tx = (Transaction) session.beginTransaction();			
			
			ENS_AddEquipBean equipBean = new ENS_AddEquipBean();
				
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_EquipmentMST");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_EquipmentMST");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			equipBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			equipBean.setPLT(users.getPlt());
			equipBean.setSEC(users.getSec());
			equipBean.setEQUIP_NAM(request.getParameter("eqp_nam"));
			equipBean.setFLAG("Y");
			equipBean.setUPD_ON(new Date());
			equipBean.setUPD_BY(users.getPlt());

			session.save(equipBean);
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
	public ArrayList<ENS_AddEquipBean> genreport(UserBean users, HttpServletRequest request) {
		Session session = null;
		ArrayList<ENS_AddEquipBean> lstEquip = null;
		
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_AddEquipBean "
						+ "where PLT= "+ users.getPlt() + " ");
				lstEquip = (ArrayList<ENS_AddEquipBean>) query.list();
			
			
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
		
		return lstEquip;
	}
	
}
