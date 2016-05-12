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
		//On s'attend à récuperer une list d'entreprise
		Entreprise expected = new Entreprise();
		assertEquals(expected.getClass(), HibernateUtil.getEntrepriseDAO().getEntreprises().iterator().next().getClass());
	}
	
	@Test
	public void testGetEntrepriseById() {
		//On vérifie qu'on récupère bien l'entreprise d'id = 2
		Entreprise tmp = HibernateUtil.getEntrepriseDAO().getEntrepriseById(2);
		assertEquals(Double.valueOf(2), Double.valueOf(tmp.getIdentreprise()));
		
		//On vérifie qu'on récupère bien null en cas d'erreur
		try {
			HibernateUtil.getEntrepriseDAO().getEntrepriseById(123456789);
			fail("Erreur, pas de valeur null lors d'erreur");
		} catch(NullPointerException e) {
			return; //Validation du test en cas de levée de l'exception
		}
	}
	
	@Test
	public void getLastEntreprise() {
		assertEquals(Entreprise.class, HibernateUtil.getEntrepriseDAO().getLastEntreprise().getClass());
	}	
	
	@Test
	public void testAddOrUpdateEntreprise() throws Exception {
		//Test ajout / modification entreprise (2fois pour bien montrer l'écriture des données)
		Entreprise tmp = HibernateUtil.getEntrepriseDAO().getEntrepriseById(2);
		tmp.setNbSalaries(250);
		HibernateUtil.getEntrepriseDAO().addOrUpdateEntreprise(tmp);
		assertEquals(250, HibernateUtil.getEntrepriseDAO().getEntrepriseById(2).getNbSalaries());
		tmp.setNbSalaries(854);
		HibernateUtil.getEntrepriseDAO().addOrUpdateEntreprise(tmp);
		assertEquals(854, HibernateUtil.getEntrepriseDAO().getEntrepriseById(2).getNbSalaries());
		//Vérification de la levé d'exception en cas d'erreur
		try {		
			tmp = new Entreprise();
			HibernateUtil.getEntrepriseDAO().addOrUpdateEntreprise(tmp);
			fail("Erreur, pas de levée d'exception en cas d'erreur");
		} catch (Exception e) {
			return; //Validation du test en cas de levée d'exception
		}
	}

}
