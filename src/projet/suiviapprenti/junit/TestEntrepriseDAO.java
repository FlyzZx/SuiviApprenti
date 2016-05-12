package projet.suiviapprenti.junit;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Entreprise;

public class TestEntrepriseDAO {
	
	@Test
	public void testGetEntrepriseList() {
		//On s'attend � r�cuperer une list d'entreprise
		Entreprise expected = new Entreprise();
		assertEquals(expected.getClass(), HibernateUtil.getEntrepriseDAO().getEntreprises().iterator().next().getClass());
	}
	
	@Test
	public void testGetEntrepriseById() {
		//On v�rifie qu'on r�cup�re bien l'entreprise d'id = 2
		Entreprise tmp = HibernateUtil.getEntrepriseDAO().getEntrepriseById(2);
		assertEquals(Double.valueOf(2), Double.valueOf(tmp.getIdentreprise()));
		
		//On v�rifie qu'on r�cup�re bien null en cas d'erreur
		try {
			HibernateUtil.getEntrepriseDAO().getEntrepriseById(123456789);
			fail("Erreur, pas de valeur null lors d'erreur");
		} catch(NullPointerException e) {
			return; //Validation du test en cas de lev�e de l'exception
		}
	}
	
	@Test
	public void getLastEntreprise() {
		assertEquals(Entreprise.class, HibernateUtil.getEntrepriseDAO().getLastEntreprise().getClass());
	}	
	
	@Test
	public void testAddOrUpdateEntreprise() throws Exception {
		//Test ajout / modification entreprise (2fois pour bien montrer l'�criture des donn�es)
		Entreprise tmp = HibernateUtil.getEntrepriseDAO().getEntrepriseById(2);
		tmp.setNbSalaries(250);
		HibernateUtil.getEntrepriseDAO().addOrUpdateEntreprise(tmp);
		assertEquals(250, HibernateUtil.getEntrepriseDAO().getEntrepriseById(2).getNbSalaries());
		tmp.setNbSalaries(854);
		HibernateUtil.getEntrepriseDAO().addOrUpdateEntreprise(tmp);
		assertEquals(854, HibernateUtil.getEntrepriseDAO().getEntrepriseById(2).getNbSalaries());
		//V�rification de la lev� d'exception en cas d'erreur
		try {		
			tmp = new Entreprise();
			HibernateUtil.getEntrepriseDAO().addOrUpdateEntreprise(tmp);
			fail("Erreur, pas de lev�e d'exception en cas d'erreur");
		} catch (Exception e) {
			return; //Validation du test en cas de lev�e d'exception
		}
	}

}
