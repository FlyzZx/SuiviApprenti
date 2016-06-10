package projet.suiviapprenti.DAL;


import java.util.Iterator;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.DAL.Entitys.ParcoursPostBts;

public class ApprentiDAOHibernate implements ApprentiDAO {
	public static final String EXC_USER = "L'utilisateur spécifié est introuvable !";
	
	private SessionFactory sessionFact;
	
	public ApprentiDAOHibernate(SessionFactory sessionFact) {
		this.sessionFact = sessionFact;
	}
	
	@Override
	public Apprenti getApprenti(String mailEnter, String password) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		Apprenti user = new Apprenti();
		
		try {
			Query query = session.createQuery("From Apprenti app where app.coordonnees.email = :mail AND app.motDePasse = :pass");
			query.setParameter("mail", mailEnter).setParameter("pass", password);
			
			user = (Apprenti) query.uniqueResult();
			
			if(user != null) {
				Hibernate.initialize(user.getCoordonnees());
				Hibernate.initialize(user.getClasse());
				Hibernate.initialize(user.getEntreprise());
				Hibernate.initialize(user.getEntreprise().getCoordonnees());
				Hibernate.initialize(user.getParcoursPostBtses());
				Hibernate.initialize(user.getCursusformations());
				Iterator<ParcoursPostBts> it = user.getParcoursPostBtses().iterator();
				while(it.hasNext()) {
					ParcoursPostBts tmp = (ParcoursPostBts)it.next();
					Hibernate.initialize(tmp.getEntreprise());
					Hibernate.initialize(tmp.getEntreprise().getCoordonnees());	
				}
				
			}
			else {
				tx.commit();
				throw new Exception(EXC_USER);
			}
			
			tx.commit();
			
		} catch (HibernateException e) {
			tx.rollback();
			
		}
		finally {
			session.close();
		}
		
		return user;
	}

	@Override
	public void updateApprenti(Apprenti app) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(app);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new Exception("Erreur lors de la mise à jour");
		} finally {
			session.close();
		}
	}

}
