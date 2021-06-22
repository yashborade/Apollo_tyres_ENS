package mapper;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_AddMachBean;
import beans.ENS_Control_No;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_AddMachMapper implements dao.ENS_AddMachDao{
	
	
	
	public String msg = "";
	
	public ENS_AddMachMapper(){
		
		ENS_hibernateFactory.buildIfNeeded();
	}
	
	private ENS_Control_No obj = null;
	
	private  void getControlsno(int plt, String table) 
	{
		Session session = null;
		session = ENS_hibernateFactory.openSession();
		Query query = session.createQuery("from ENS_Control_No where PLT = " + plt + " "
				+ " and CTRLNO_DOCUMENT='" + table + "'");
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
			
			ENS_AddMachBean machBean = new ENS_AddMachBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_MachineMst");
			
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_MachineMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			String node = request.getParameter("nod_nam");
			String arg[] = node.split("~");
			
			/*String one="";
			String two="";
			
			for (int i=0; i<arg.length; i++)
			{
				one = arg[0];
				two = arg[1];bias~node1
			}	
			
			System.out.println("One = " + one);
			System.out.println("Two = " + two);*/
			
			machBean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			machBean.setPLT(users.getPlt());
			machBean.setSEC(arg[0]);
			machBean.setNOD_NAM(arg[1]);
			machBean.setMACH_NAM(request.getParameter("mach_nam"));
			machBean.setFLAG("Y");
			machBean.setUPD_ON(new Date());
			machBean.setUPD_BY(users.getUid());
			machBean.setUSERDISP(arg[1]+"~"+request.getParameter("mach_nam"));
			
			session.save(machBean);
			session.flush();
			tx.commit();
			msg = "<font color='green' style='font-size: 14pt;'><b>Data Inserted Successfully.</b></font>";
			
			obj.setCTRLNO_NEXT_NO(obj.getCTRLNO_NEXT_NO()+1);
			session.saveOrUpdate(obj);
			session.flush();
			
		}
		catch(Exception ex){
			
			/*String[] exp = ((ConstraintViolationException)ex).getSQLException().toString().split(":");
			
			if (((ConstraintViolationException)).getSQLState().equals("23505"))
			{
				msg = "<font color='red' style='font-size: 14pt;'><b>Error in Inserting Data.</font>";
			}
			else
			{
				msg = "<font color='red' style='font-size: 16pt;'><b>Error in Inserting Data. <br /><br />Reason: " + exp[0] + " <br />SQL State: " + ((ConstraintViolationException)ex).getSQLState() + "</b></font>";
			}*/
			
			System.out.println("Exception fired" + ex.getMessage());
			ex.printStackTrace();
			
			
		}
		finally{
			
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
	public ArrayList<ENS_AddMachBean> genreport(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session = null;
		ArrayList<ENS_AddMachBean> lstMachno = null;
		
		String a = request.getParameter("section");
		System.out.println("Section in machine :"+a);
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();
			
			/*if(a.equalsIgnoreCase("--Select--"))
			{
				Query query = session.createQuery("from ENS_AddMachBean "
						+ " where PLT ="+users.getPlt()+" "
						+ "order by NOD_NAM, MACH_NAM");
				lstMachno = (ArrayList<ENS_AddMachBean>) query.list();
			}
			else*/
			
				Query query = session.createQuery("from ENS_AddMachBean "
						+ " where PLT ="+users.getPlt()+" "
						+ "and SEC ='"+request.getParameter("section")+"' "
						+ "order by NOD_NAM, MACH_NAM");
				lstMachno = (ArrayList<ENS_AddMachBean>) query.list();
			

			
		}
		catch(Exception ex)
		{
			System.out.println("Machine Number Method --> Exception" + ex.getMessage());
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

	@Override
	public ArrayList<ENS_AddMachBean> getMach(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session = null;
		ArrayList<ENS_AddMachBean> lstMach = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();

			Query query = session.createQuery("from ENS_AddMachBean "
					+ "where PLT=" + users.getPlt() + " "
					+ " and SEC='" + request.getParameter("section"));
			lstMach = (ArrayList<ENS_AddMachBean>) query.list();
			
		}
		catch(Exception ex)
		{
			System.out.println("get machine--> Exception" + ex.getMessage());
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
		
		return lstMach;
	}
}
