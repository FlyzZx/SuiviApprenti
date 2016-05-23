package projet.suiviapprenti.DAL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.DAL.Entitys.Cursusformation;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCursus(Cursusformation cursus) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cursusformation getCursusById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
