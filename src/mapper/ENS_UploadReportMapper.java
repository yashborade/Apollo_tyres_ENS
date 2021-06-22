package mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ENS_AddGroupBean;
import beans.ENS_Control_No;
import beans.ENS_UploadBean;
import beans.UserBean;
import dao.ENS_UploadReportDao;
import utility.ENS_hibernateFactory;

public class ENS_UploadReportMapper implements ENS_UploadReportDao{
	
	
	
	public ENS_UploadReportMapper(){
		
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
	public ArrayList<ENS_UploadBean> genreport(UserBean users, HttpServletRequest request) {

		Session session = null;
		ArrayList<ENS_UploadBean> lstNodeno = null;
		
		String a = request.getParameter("date1");
		String b = request.getParameter("sec");
		String c = request.getParameter("nod_nam");
	
		try
		{
			session = ENS_hibernateFactory.openSession();
			System.out.println("into open session :");
			int plt = users.getPlt();
			
			if(a.equalsIgnoreCase("") && b.equalsIgnoreCase("--select--") && c.equalsIgnoreCase(""))
			{
				Query query = session.createQuery("from ENS_UploadBean "
						+ " where PLT= '"+users.getPlt()+"'");
				lstNodeno = (ArrayList<ENS_UploadBean>) query.list();
			}
			else
			{
				Query query = session.createQuery("from ENS_UploadBean "
						+ " where PLT= '"+users.getPlt()+"'"
						+ "and DATE = '"+request.getParameter("date1")+"'"
						+ "and SEC = '"+request.getParameter("sec")+"'"
						+ "and NOD_NAM = '"+request.getParameter("nod_nam")+"'");
				lstNodeno = (ArrayList<ENS_UploadBean>) query.list();
			}
		}
		catch(Exception ex)
		{
			System.out.println("upload Method --> Exception" + ex.getMessage());
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
	public ArrayList<ENS_UploadBean> getNode(UserBean users, HttpServletRequest request) {
		
		Session session = null;
		ArrayList<ENS_UploadBean> lstCat = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();

			Query query = session.createQuery("from ENS_UploadBean "
					+ " where PLT=" + users.getPlt() + ""
					+ " and DATE='" + request.getParameter("date1")+"'");
			lstCat = (ArrayList<ENS_UploadBean>) query.list();
		}
		catch(Exception ex)
		{
			System.out.println("ENS_get node --> Exception" + ex.getMessage());
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
	public ENS_UploadBean getReadings(UserBean users, String node, String mach, String dat) {
		Session session = null;
		ENS_UploadBean lst = null;
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();

			Query query = session.createQuery("from ENS_UploadBean where PLT = '"+users.getPlt()+"' "
					+ "and NOD_NAM = '"+ node.toUpperCase() +"' "
					+ "and MACH_NAM = '"+mach.toUpperCase()+"' "
					+ "and DATE = '"+dat.toString()+"'");
			lst = (ENS_UploadBean) query.uniqueResult();
			
		}
		catch(Exception ex)
		{
			System.out.println("ENS_get readings --> Exception" + ex.getMessage());
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
	public ENS_UploadBean getReadings(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		ENS_UploadBean lst = null;
		
		String a = request.getParameter("nod_nam");
		String arg[] = a.split("~");
		
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			
			Query query = session.createQuery("from ENS_UploadBean where "
					+ "NOD_NAM ='" + arg[0] + "'"
					+ "and MACH_NAM = '"+arg[1]+"'"
					+ "and DATE = '"+request.getParameter("date1")+"'");
			lst = (ENS_UploadBean) query.uniqueResult();
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
			
			ENS_UploadBean uploadbean = new ENS_UploadBean();
			
			HttpSession httpSession = request.getSession();
			UserBean users = (UserBean) httpSession.getAttribute("Users");
			getControlsno(users.getPlt(),"ENS_UploadMst");
			if (obj == null)
			{
				session = ENS_hibernateFactory.openSession();
				tx = (Transaction) session.beginTransaction();
				System.out.println("into obj...");
				obj = new ENS_Control_No();
				obj.setPLT(users.getPlt());
				obj.setFIN_YR(users.getFinyear());
				obj.setCTRLNO_DOCUMENT("ENS_UploadMst");
				obj.setCTRLNO_NEXT_NO(0);
			}
			
			String b = request.getParameter("sec");
			System.out.println("Section :"+b);
			
			String c = request.getParameter("nod_nam");
			System.out.println("Node name :"+c);
			
			String d = request.getParameter("mach_nam");
			System.out.println("Machine name :"+d);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date firstDate = sdf.parse(request.getParameter("date1"));
		    System.out.println("Date "+firstDate);
			
			
			String a = request.getParameter("read");
			double r = Double.parseDouble(a);
			System.out.println("Value of Readings :"+r);
			
			uploadbean.setSRNO(obj.getCTRLNO_NEXT_NO()+1);
			uploadbean.setPLT(users.getPlt());
			uploadbean.setSEC(request.getParameter("sec"));
			uploadbean.setNOD_NAM(request.getParameter("nod_nam"));
			uploadbean.setMACH_NAM(request.getParameter("mach_nam"));
			uploadbean.setDATE(firstDate);
			uploadbean.setREADINGS(r);
			uploadbean.setFLAG("Y");
			uploadbean.setUPD_ON(new Date());
			uploadbean.setUPD_BY(users.getUid());
			
			session.update(uploadbean);
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
}
