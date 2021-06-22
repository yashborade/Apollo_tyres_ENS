package mapper;


import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import beans.ENS_AddNodBean;
import beans.ENS_Control_No;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_AddNodeMapper implements dao.ENS_AddNode {
	
	public String msg="";
	
	
	public ENS_AddNodeMapper(){
		
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
	public String insMachine(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		String msg = "";
		
		try{
			session = ENS_hibernateFactory.openSession();
			tx = (Transaction) session.beginTransaction();			
			
			ENS_AddNodBean nodBean = new ENS_AddNodBean(); 
				
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_NodeMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_NodeMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			String a=request.getParameter("equip_nam");
			System.out.println("inside insert equipment :"+a);
			
			String b=request.getParameter("date1");
			System.out.println("inside insert date :"+b);
			
			String c=request.getParameter("total");
			System.out.println("inside insert total :"+c);
			
			String d=request.getParameter("batch1");
			System.out.println("inside insert total batch :"+d);
			
			String e=request.getParameter("batch2");
			System.out.println("inside insert bias batch :"+e);
		
		
			
			nodBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			nodBean.setPLT(users.getPlt());
			nodBean.setSEC(request.getParameter("section"));
			nodBean.setNOD_NAM(request.getParameter("nod_nam"));
			nodBean.setFLAG("Y");
			nodBean.setUPD_ON(new Date());
			nodBean.setUPD_BY(users.getPlt());
			nodBean.setUSERDISP(request.getParameter("section").trim()+"~"+request.getParameter("nod_nam").trim());
			session.save(nodBean);
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
	public ArrayList<ENS_AddNodBean> genreport(UserBean users, HttpServletRequest request) {
		//HttpServletRequest request = null;
		Session session = null;
		ArrayList<ENS_AddNodBean> lstNodeno = null;
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();
			
			String a = request.getParameter("section");
			System.out.println("Section :"+a);
			
			if(a.equalsIgnoreCase("--Select--"))
			{
				Query query = session.createQuery("from ENS_AddNodBean "
						+ "where PLT=" + users.getPlt() +"");
				lstNodeno = (ArrayList<ENS_AddNodBean>) query.list();
			}
			else
			{
				Query query = session.createQuery("from ENS_AddNodBean "
						+ "where PLT=" + users.getPlt() + " "
						+ " and SEC='" + request.getParameter("section")+"'");
				lstNodeno = (ArrayList<ENS_AddNodBean>) query.list();
			}
		}
		catch(Exception ex)
		{
			System.out.println("Node Number Method --> Exception" + ex.getMessage());
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
	public ArrayList<ENS_AddNodBean> getNode(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AddNodBean> lstCat = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();
			
			String a = request.getParameter("section");
			System.out.println("Section :"+a);
			
			if(!a.equalsIgnoreCase("--Select--"))
			{
				Query query = session.createQuery("from ENS_AddNodBean "
						+ "where PLT=" + users.getPlt() +"");
				lstCat = (ArrayList<ENS_AddNodBean>) query.list();
			}
			else
			{
				Query query = session.createQuery("from ENS_AddNodBean "
						+ "where PLT=" + users.getPlt() + " "
						+ " and SEC='" + request.getParameter("section"));
				lstCat = (ArrayList<ENS_AddNodBean>) query.list();
			}
		}
		catch(Exception ex)
		{
			System.out.println("ENS_GetNode --> Exception" + ex.getMessage());
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
	public ArrayList<ENS_AddNodBean> gExcel(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session = null;
		ArrayList<ENS_AddNodBean> lstCat = null;
		int b=0;
		
		String a = request.getParameter("section");
		System.out.println("Sections in gExcel :"+a);
		
		try
		{
			String sec = request.getParameter("section");
			
			session = ENS_hibernateFactory.openSession();
			
			Criteria cExcel = session.createCriteria(ENS_AddNodBean.class);
			cExcel.add(Restrictions.eq("SEC", sec));
			
			lstCat = (ArrayList<ENS_AddNodBean>) cExcel.list();
			b = lstCat.size();
			System.out.println("Size in Excel :"+b);
			
		}
		catch(Exception ex)
		{
			System.out.println("ENS_gexcel --> Exception" + ex.getMessage());
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
}
