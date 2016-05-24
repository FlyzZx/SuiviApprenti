package projet.suiviapprenti.DAL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.DAL.Entitys.Cursusformation;
import projet.suiviapprenti.DAL.Entitys.ParcoursPostBts;

public class CursusDAOHibernate implements CursusDAO {
	
	private SessionFactory sessionFact;
	
	public CursusDAOHibernate(SessionFactory sessionFact) {
		this.sessionFact = sessionFact;
	}

	@Override
	public void addCursus(Cursusformation cursus) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(cursus);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw new Exception("Erreur lors de l'ajout du cursus");
		} finally {
			session.close();
		}	
	}

	@Override
	public void updateCursus(Cursusformation cursus) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(cursus);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw new Exception("Erreur lors de la mise à jour du cursus");
		} finally {
			session.close();
		}			
	}

	@Override
	public void deleteCursus(Cursusformation cursus) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(cursus);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw new Exception("Erreur lors de la suppression du cursus");
		} finally {
			session.close();
		}	
	}

	@Override
	public Cursusformation getCursusById(int id) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		Cursusformation cursus = new Cursusformation();
		
		try {
			Query q = session.createQuery("From Cursusformation cursus where cursus.idcursusformation = :id");
			q.setParameter("id", id);
			
			cursus = (Cursusformation) q.uniqueResult();
			if(cursus == null){
				tx.commit();
				throw new Exception("Erreur, le parcours spécifié n'éxiste pas");
			}
			
			tx.commit();
			
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		
		return cursus;
	}

	@Override
	public Set<Cursusformation> getCursusByApp(Apprenti app) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		Set<Cursusformation> result = null;
		
		try {
			Query que = session.createQuery("From Cursusformation cursus where cursus.apprenti = :app");
			que.setParameter("app", app);
			List<Cursusformation> list_retour = (List<Cursusformation>) que.list();
			
			result = new HashSet<Cursusformation>(list_retour);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new Exception("Erreur mise à jour apprenti - " + e.getMessage());
		} finally {
			session.close();
		}
		
		return result;
	}

}
