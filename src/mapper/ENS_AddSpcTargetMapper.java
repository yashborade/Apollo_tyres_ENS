package mapper;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_Control_No;
import beans.ENS_SpcTargetBean;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_AddSpcTargetMapper implements dao.ENS_AddSpcTagetDao{
	
	
	public String msg="";
	
	
	public ENS_AddSpcTargetMapper(){
		
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
	public String insSpcTraget(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session = null;
		Transaction tx = null;
		String msg = "";
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			tx = (Transaction) session.beginTransaction();	
			
			ENS_SpcTargetBean spcBean = new ENS_SpcTargetBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_SpcTargetMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_SpcTargetMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			String a = request.getParameter("area_nam");
			System.out.println("Area name in spc :"+a);
			
			String b = request.getParameter("month");
			System.out.println("Month in spc :"+b);
			
			String c = request.getParameter("target");
			System.out.println("Target in spc :"+c);
			double tar = Double.parseDouble(c);
			
			spcBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			spcBean.setPLT(users.getPlt());
			spcBean.setSEC(users.getSec());
			spcBean.setAREA_NAM(request.getParameter("area_nam"));
			spcBean.setMONTH(request.getParameter("month"));
			spcBean.setSPC_TARGET(tar);
			spcBean.setFLG("Y");
			spcBean.setUPD_ON(new Date());
			spcBean.setUPD_BY(users.getUid());
			spcBean.setFINYR(users.getFinyear());
			
			session.save(spcBean);
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
	public ArrayList<ENS_SpcTargetBean> genereport(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_SpcTargetBean> lstspc = null;
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
			Query query = session.createQuery("from ENS_SpcTargetBean "
					+ "where PLT= "+ users.getPlt() + ""
					+ "and MONTH = '"+request.getParameter("month")+"'");
			lstspc = (ArrayList<ENS_SpcTargetBean>) query.list();
		}
		catch(Exception ex)
		{
			System.out.println("Exception Fired"  + ex.getMessage());
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
		return lstspc;
	}
}
