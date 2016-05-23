package projet.suiviapprenti.DAL;

import java.util.Properties;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil {
	public static final SessionFactory sessionFactory;

	static {
		try {
			// Création de la SessionFactory à partir de hibernate.cfg.xml
			/*Configuration conf = new Configuration().configure();
			conf.setProperty("hibernate.connection.url", "jdbc:mysql://82.240.139.63/suiviapprenti");*/
			sessionFactory = new Configuration().configure().buildSessionFactory();
			
			
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static ApprentiDAO getApprentiDAO() {
		return new ApprentiDAOHibernate(HibernateUtil.getSessionFactory());
	}
	
	public static ParcoursDAO getParcoursDAO() {
		return new ParcoursDAOHibernate(HibernateUtil.getSessionFactory());
	}
	
	public static EntrepriseDAO getEntrepriseDAO() {
		return new EntrepriseDAOHibernate(HibernateUtil.getSessionFactory());
	}
	
	public static CursusDAO getCursusDAO() {
		return new CursusDAOHibernate(HibernateUtil.getSessionFactory());
	}
	
}
