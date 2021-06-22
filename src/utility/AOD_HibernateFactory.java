package utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class AOD_HibernateFactory {
   private static SessionFactory sessionFactory;
  //  private static Log log = LogFactory.getLog(HibernateFactory.class);
    
    public static SessionFactory buildSessionFactory() throws HibernateException {
        if(sessionFactory != null){
            closeFactory();
        }
        
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
         
        return sessionFactory;
    }
    
    public static SessionFactory buildIfNeeded() throws HibernateException {
        if(sessionFactory == null){
            sessionFactory = new AnnotationConfiguration().configure("/cfgs/EMPINFO.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
        
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session openSession() throws HibernateException {
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
    
    public static void close(Session session) {
        if (session != null) {
            try {
                session.close();
            }
            catch (HibernateException ignored) {
                //log.error("Couldn't close Session", ignored);
            }
        }
    }
    
    public static void rollback(Transaction tx) {
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

