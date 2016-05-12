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
import projet.suiviapprenti.DAL.Entitys.ParcoursPostBts;

public class ParcoursDAOHibernate implements ParcoursDAO {

	private SessionFactory sessionFact;
	
	public ParcoursDAOHibernate(SessionFactory sessionFact) {
		this.sessionFact = sessionFact;
	}
	
	@Override
	public void addParcours(ParcoursPostBts parcours) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(parcours);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw new Exception("Erreur lors de l'ajout");
		} finally {
			session.close();
		}
		
	}

	@Override
	public void delParcours(ParcoursPostBts parcours) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(parcours);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw new Exception("Erreur lors de la suppression");
		} finally {
			session.close();
		}
		
	}

	@Override
	public ParcoursPostBts getParcoursById(int idParcours) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		ParcoursPostBts parcours = new ParcoursPostBts();
		
		try {
			Query q = session.createQuery("From ParcoursPostBts parcours where parcours.idparcoursPostBts = :id");
			q.setParameter("id", idParcours);
			
			parcours = (ParcoursPostBts) q.uniqueResult();
			if(parcours != null){
				Hibernate.initialize(parcours.getEntreprise()); //Récupération de l'entreprise du parcours (si elle existe)
				Hibernate.initialize(parcours.getEntreprise().getCoordonnees());
				
			} else {
				tx.commit();
				throw new Exception("Erreur, le parcours spécifié n'éxiste pas");
			}
			
			tx.commit();
			
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		
		return parcours;
	}

	@Override
	public void updateParcours(ParcoursPostBts parcours) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(parcours);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw new Exception("Erreur lors de la mise à jour");
		} finally {
			session.close();
		}
		
	}

	@Override
	public Set<ParcoursPostBts> getParcoursByApp(Apprenti app) throws Exception {
		Session session = sessionFact.openSession();
		Transaction tx = session.beginTransaction();
		Set<ParcoursPostBts> result = null;
		
		try {
			Query que = session.createQuery("From ParcoursPostBts parcours where parcours.apprenti = :app");
			que.setParameter("app", app);
			List<ParcoursPostBts> list_retour = (List<ParcoursPostBts>) que.list();
			for(ParcoursPostBts p : list_retour) {
				Hibernate.initialize(p.getEntreprise()); //Récupération de l'entreprise du parcours
			}
			
			result = new HashSet<ParcoursPostBts>(list_retour);
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
