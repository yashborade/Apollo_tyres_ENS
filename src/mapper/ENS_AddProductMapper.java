package mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_AddProductBean;
import beans.ENS_Control_No;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_AddProductMapper implements dao.ENS_AddProductDao{
	
	
	public String msg="";
	
	
	 public ENS_AddProductMapper() {
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
	public String insProduct(HttpServletRequest request) {
		
		Session session = null;
		Transaction tx = null;
		String msg = "";
		
		try{
			session = ENS_hibernateFactory.openSession();
			tx = (Transaction) session.beginTransaction();			
			 
			ENS_AddProductBean proBean = new ENS_AddProductBean();
				
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_ProductionMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_ProductionMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			String b=request.getParameter("date1");
			System.out.println("inside insert date :"+b);
			SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd"); 
			Date d2 = sdfo.parse(b);
			
			String name = request.getParameter("equip_nam");
			System.out.println("Equipment Name :"+name);
			
			String a = request.getParameter("total");
			System.out.println("Total Consuption :"+a);
			double tot = Double.parseDouble(a);
			
			String c = request.getParameter("batch1");
			System.out.println("Total Batch :"+c);
			double btch1 = Double.parseDouble(c);
			
			String d = request.getParameter("batch2");
			System.out.println("Total Bias Batch :"+d);
			double btch2 = Double.parseDouble(d);
			
			double rad = 0.0;
			rad = btch1 - btch2;
			System.out.println("Total Radial Batch :"+rad);
			
			double avg1 = 0.0;
			avg1 = tot/btch1;
			System.out.println("Total consuption :"+avg1);
			
			double avg3 = 0.0;
			avg3 = avg1*btch2;
			System.out.println("Total Consuption for Bias :"+avg3);
			
			double avg = 0.0;
			avg = avg1*rad;
			System.out.println("Total Consuption for Radial :"+avg);
			
			
			
			proBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			proBean.setPLT(users.getPlt());
			proBean.setSEC(users.getSec());
			proBean.setBTCH_DATE(d2);
			proBean.setEQUIP_NAM(request.getParameter("equip_nam"));
			proBean.setEQUIP_TOTAL(tot);
			proBean.setTOT_BTCH(btch1);
			proBean.setBIAS_BTCH(btch2);
			proBean.setRADIAL_BTCH(rad);
			proBean.setBIAS_CONSUP(avg3);
			proBean.setRADIAL_CONSUP(avg);
			proBean.setTOTAL(avg1);
			proBean.setFLAG("y");
			proBean.setUPD_ON(new Date());
			proBean.setUPD_BY(users.getUid());
			
			session.saveOrUpdate(proBean);
			session.flush();
			tx.commit();
			msg = "<font color='green' style='font-size: 14pt;'><b>Data Inserted Successfully.</b></font>";
			
			obj.setCTRLNO_NEXT_NO(obj.getCTRLNO_NEXT_NO()+1);
			session.saveOrUpdate(obj);
			session.flush();

		}
		catch(Exception e)
		{
			System.out.println("Exception fired in insert Product :" + e.getMessage());
			e.printStackTrace();
			
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
	public ArrayList<ENS_AddProductBean> genreport(UserBean users, HttpServletRequest request) {
		Session session = null;
		ArrayList<ENS_AddProductBean> lstMachno = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();
			
			String a = request.getParameter("date1");
			System.out.println("date :"+a);
			
			if(a.equalsIgnoreCase(""))
			{
				Query query = session.createQuery("from ENS_AddProductBean "
						+ " where PLT ="+users.getPlt()+"");
				lstMachno = (ArrayList<ENS_AddProductBean>) query.list();
			}
			else
			{
				Query query = session.createQuery("from ENS_AddProductBean "
						+ " where PLT ="+users.getPlt()+" and "
						+ "  BTCH_DATE = '"+request.getParameter("date1")+"'");
				lstMachno = (ArrayList<ENS_AddProductBean>) query.list();
			}
			
			
			
		}
		catch(Exception ex)
		{
			System.out.println(" Product Report --> Exception" + ex.getMessage());
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
		
		return lstMachno;
	}
}
