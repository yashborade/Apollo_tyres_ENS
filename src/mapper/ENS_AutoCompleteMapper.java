package mapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;

import org.hibernate.Session;

import beans.ENS_AddMachBean;
import beans.UserBean;
import dao.ENS_AutoCompleteDao;
import utility.ENS_hibernateFactory;

public class ENS_AutoCompleteMapper implements ENS_AutoCompleteDao{
	
	
	public ENS_AutoCompleteMapper() {
		// TODO Auto-generated constructor stub
		//System.out.println("Inside AutoComplete Mapper");
		ENS_hibernateFactory.buildIfNeeded();
	}
	
	@Override
	public List<String> getS_Node(HttpServletRequest request, UserBean users) {
		Session session = null;
		List<String> lst = null;
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT NOD_NAM from ENS_AddNodBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and NOD_NAM LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("siz"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getNode(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT USERDISP from ENS_AddNodBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and USERDISP LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("siz"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getEquip(HttpServletRequest request, UserBean users) {
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT EQUIP_NAM from ENS_AddEquipBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and EQUIP_NAM LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("siz"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getMach(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		List<String> lst = null;
		String key = request.getParameter("term");
		System.out.println("Keyword = " + key);
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT MACH_NAM from ENS_AddMachBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and MACH_NAM LIKE '%" + request.getParameter("term") + "%'");
			
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
			
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getGroup(HttpServletRequest request, UserBean users) {
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT EQUIP_NAM from ENS_AddGroupBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and EQUIP_NAM LIKE '%" + request.getParameter("term") + "%'");
			
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
			
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getGroup_mach(HttpServletRequest request, UserBean users) {
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT FIDD_NAM_PLUS from ENS_AddGroupBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and FIDD_NAM_PLUS LIKE '%" + request.getParameter("term") + "%'");
			
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
			
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getEquip1(HttpServletRequest request, UserBean users) {
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT EQUIP_NAM from ENS_AddGroupBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and EQUIP_NAM LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getArea(HttpServletRequest request, UserBean users) {
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT AREA_NAME from ENS_AddAreaBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and AREA_NAME LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getArea1(HttpServletRequest request, UserBean users) {
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT AREA_NAME from ENS_AreaGroupBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and AREA_NAME LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getAreaEquip(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT EQUIP_NAM from ENS_AddEquipBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and EQUIP_NAM LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getCalEquip(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT EQUIP_NAM from ENS_AddEquipBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and EQUIP_NAM LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> callNode(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT NOD_NAM from ENS_AddNodBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and NOD_NAM LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getm_node(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT USERDISP from ENS_AddMachBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and USERDISP LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getAreaTarget(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		Session session = null;
		List<String> lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("SELECT AREA-TARGET from ENS_AddAreaTargetBean where "
					+ " PLT=" + users.getPlt() + " "
					+ " and USERDISP LIKE '%" + request.getParameter("term") + "%'");
			lst = (List<String>) query.list();
			System.out.println("size :"+lst.size());
		}
		catch(Exception ex)
		{
			System.out.println("Auto complete --> Exception" + ex.getMessage());
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
	public List<String> getSpcTarget(HttpServletRequest request, UserBean users) {
		// TODO Auto-generated method stub
		return null;
	}
}


