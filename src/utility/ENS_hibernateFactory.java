package utility;

import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class ENS_hibernateFactory {
	
	private static String cfg="/cfgs/ENS.cfg.xml"; //DB2 
	//private static String cfg="/cfgs/Transport_Outwards.cfg.xml"; // SQL SERVER
	
	
	   private static SessionFactory sessionFactory;
	  //  private static Log log = LogFactory.getLog(HibernateFactory.class);
	    
	    public static SessionFactory buildSessionFactory() throws HibernateException {
	        if(sessionFactory != null){
	            closeFactory();
	        }
	        
	        AnnotationConfiguration configuration = new AnnotationConfiguration();
	        //configuration.configure("/cfgs/Trnsp_Out.cfg.xml");
	        configuration.configure(cfg);
	        sessionFactory = configuration.buildSessionFactory();	         
	        return sessionFactory;
	    }
	    
	    public static SessionFactory buildIfNeeded() throws HibernateException {
	        if(sessionFactory == null){
	        	  //configuration.configure("/cfgs/Trnsp_Out.cfg.xml");
	            sessionFactory = new AnnotationConfiguration().configure(cfg).buildSessionFactory();
	        	//sessionFactory = new Configuration().configure().buildSessionFactory();
	        }
	        return sessionFactory;
	        
	    }
	    
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	    
	    public static org.hibernate.classic.Session openSession() throws HibernateException {
	        return sessionFactory.openSession();
	    }
	    
	    public static void closeFactory() {
	        if (sessionFactory != null) {
	            try {
	                sessionFactory.close();
	                sessionFactory = null;
	            }
	            catch (HibernateException ignored) {
	              //  log.error("Couldn't close SessionFactory", ignored);
	            }
	        }
	    }
	    
	    public static void close(org.hibernate.Session session) {
	        if (session != null) {
	            try {
	                session.close();
	            }
	            catch (HibernateException ignored) {
	                //log.error("Couldn't close Session", ignored);
	            }
	        }
	    }
	    
	    public static void rollback(Transaction tx) throws IllegalStateException, SystemException {
	        try {
	            if (tx != null) {
	            tx.rollback();
	            }
	        }
	        catch (HibernateException ignored) {
	           // log.error("Couldn't rollback Transaction", ignored);
	        }
	   
	    }
}
