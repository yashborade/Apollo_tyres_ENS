package mapper;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import utility.AOD_HibernateFactory;

public class EMP_INFOMapper implements dao.EMP_INFODao {
	
	public String msg="";
	
	 public EMP_INFOMapper() {
		// TODO Auto-generated constructor stub
		
		AOD_HibernateFactory.buildIfNeeded();
	}

	@Override
	public List<String> getMailID(int plt, String sec, String application) {

		Session session = null;
		List<String> lst = null;
		
		try
		{
			session = AOD_HibernateFactory.openSession();
			Query query = session.createQuery("select EMAIL from EMP_INFO where "
					+ "PLT = "+plt+" " 
					+ "and SECT = '"+sec+"'"
					+ "and EMP_ID in( select EMP_ID from AuthorizatnBean where PLT = "+plt+""
									+ "and APPL = '"+application+"')");
			lst = (List<String>) query.list();
			System.out.println("Size :"+lst.size());	
		}
		catch(Exception ex)
		{
			System.out.println("get Mail ID List --> Exception" + ex.getMessage());
			ex.printStackTrace();	
		}
		finally
		{
			try
			{
				AOD_HibernateFactory.close(session);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return lst;
	}
}
