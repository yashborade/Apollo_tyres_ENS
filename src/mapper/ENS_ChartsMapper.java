package mapper;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.ENS_AreaTargetBean;
import beans.ENS_CalAreaGroupBean;
import beans.ENS_CalSpcGroupBean;
import beans.ENS_CalculateGroupBean;
import beans.ENS_SpcTargetBean;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_ChartsMapper implements dao.ENS_ChartDao{
	
	public String msg="";
	
	
	 public ENS_ChartsMapper() {
		// TODO Auto-generated constructor stub
		
		ENS_hibernateFactory.buildIfNeeded();
	}

	@Override
	public ArrayList<ENS_CalSpcGroupBean> genereport(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_CalSpcGroupBean> lstgraph = null;
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
			Query query = session.createQuery("from ENS_CalSpcGroupBean "
					+ "where PLT= "+ users.getPlt() + " "
					+ "and AREA_NAME = '"+request.getParameter("spc")+"' "
					+ "and SPC_DATE between '"+request.getParameter("date1")+"' "
					+ "and  '"+request.getParameter("date2")+"'"
					+ "order by SPC_DATE");
			lstgraph = (ArrayList<ENS_CalSpcGroupBean>) query.list();
			
		}
		catch(Exception ex)
		{
			System.out.println("Graph Method --> Exception" + ex.getMessage());
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
		
		return lstgraph;
	}

	@Override
	public ArrayList<ENS_CalculateGroupBean> getEquip(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		
		Session session = null;
		ArrayList<ENS_CalculateGroupBean> lstgraph = null;
		
		try
		{
				session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_CalculateGroupBean "
					+ "where PLT= "+ users.getPlt() + " "
					+ "and EQUIP_NAM = '"+request.getParameter("equip")+"'"
					+ "and GRP_DATE between '"+request.getParameter("date1")+"'"
					+ "and '"+request.getParameter("date2")+"'"
					+ "order by GRP_DATE");
				lstgraph = (ArrayList<ENS_CalculateGroupBean>) query.list();
			
		}
		catch(Exception ex)
		{
			System.out.println("Graph Method --> Exception" + ex.getMessage());
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
		return lstgraph;
	}

	@Override
	public ArrayList<ENS_AreaTargetBean> gettarget(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_AreaTargetBean> lstgraph = null;
		
		try
		{
				session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_AreaTargetBean "
					+ "where PLT= "+ users.getPlt() + " "
					+ "and AREA_NAM = '"+request.getParameter("area")+"'"
					+ "and MONTH = '"+request.getParameter("month")+"'");
				
				lstgraph = (ArrayList<ENS_AreaTargetBean>) query.list();
			
		}
		catch(Exception ex)
		{
			System.out.println("Graph Method --> Exception" + ex.getMessage());
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
		return lstgraph;
	}

	@Override
	public ArrayList<ENS_SpcTargetBean> getspctarget(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_SpcTargetBean> lstgraph = null;
		
		try
		{
				session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_SpcTargetBean "
					+ "where PLT= "+ users.getPlt() + " "
					+ "and AREA_NAM = '"+request.getParameter("spc")+"'"
					+ "and MONTH = '"+request.getParameter("month")+"'");
				
				lstgraph = (ArrayList<ENS_SpcTargetBean>) query.list();
			
		}
		catch(Exception ex)
		{
			System.out.println("Graph Method --> Exception" + ex.getMessage());
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
		return lstgraph;
	}

	@Override
	public ArrayList<ENS_CalAreaGroupBean> getArea(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		ArrayList<ENS_CalAreaGroupBean> lstgraph = null;
		
		try
		{
				session = ENS_hibernateFactory.openSession();
			
				Query query = session.createQuery("from ENS_CalAreaGroupBean "
					+ "where PLT= "+ users.getPlt() + " "
					+ "and AREA_NAM = '"+request.getParameter("area")+"'"
					+ "and AREA_DATE between '"+request.getParameter("date1")+"'"
					+ "and '"+request.getParameter("date2")+"'"
					+ "order by AREA_DATE");
				lstgraph = (ArrayList<ENS_CalAreaGroupBean>) query.list();
			
		}
		catch(Exception ex)
		{
			System.out.println("Graph Method --> Exception" + ex.getMessage());
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
		return lstgraph;
	}

}
