package projet.suiviapprenti.DAL;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Iterator;
import projet.suiviapprenti.DAL.Entitys.Entreprise;

public class EntrepriseDAOHibernate implements EntrepriseDAO {

	private SessionFactory sessionFact;
	
	public EntrepriseDAOHibernate(SessionFactory sessionFact) {
		this.sessionFact = sessionFact;
	}
	
	
	@Override
	public List<Entreprise> getEntreprises(){
		Session sess = sessionFact.openSession();
		Transaction tx = sess.beginTransaction();
		List<Entreprise> retour = null;
		
		try{
			Query q = sess.createQuery("From Entreprise");
			retour = (List<Entreprise>) q.list();
			Iterator<Entreprise> it = retour.iterator();
			while(it.hasNext()) {
				Hibernate.initialize(it.next().getCoordonnees());
			}
			
			tx.commit();
		}
		catch(Exception e){
			tx.rollback();
			
			
		}finally{
			sess.close();
		}
		
		return retour;
	}


	@Override
	public Entreprise getEntrepriseById(int idEntreprise) {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		Entreprise ent = null;
		
		try {
			Query q = session.createQuery("From Entreprise where idEntreprise = :id");
			q.setParameter("id", idEntreprise);
			ent = (Entreprise) q.uniqueResult();
			Hibernate.initialize(ent.getCoordonnees());
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		
		return ent;
	}
	
	@Override
	public Entreprise getLastEntreprise() {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		Entreprise retour = null;
		try {
			Query req = session.createQuery("From Entreprise ORDER BY idEntreprise DESC");
			req.setMaxResults(1);
			retour = (Entreprise) req.uniqueResult();
			tx.commit();
		} catch(HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		
		return retour;
	}

	@Override
	public void addOrUpdateEntreprise(Entreprise ent) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(ent);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw new Exception("Erreur lors de l'ajout entreprise");
		} finally {
			session.close();
		}
		
	}


	@Override
	public List<Entreprise> getEntrepriseBeginBy(String begin) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		List<Entreprise> retour = null;
		
		try {
			Query req = session.createQuery("From Entreprise e Where e.nomEntreprise like :beg");
			req.setParameter("beg", begin + "%");
			req.setMaxResults(10);
			retour = (List<Entreprise>) req.list();
			Iterator<Entreprise> it = retour.iterator();
			while (it.hasNext()) {
				Entreprise tmp = it.next();
				Hibernate.initialize(tmp.getCoordonnees());
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw new Exception("Erreur lors de la récupération");
		} finally {
			session.close();
		}
		return retour;
	}



}
