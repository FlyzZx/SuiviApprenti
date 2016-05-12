package projet.suiviapprenti.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.DAL.Entitys.ParcoursPostBts;

public class TestParcoursDAO {
	Apprenti app;

	
	@Before
	public void setUpApprenti() throws Exception {
		app = HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projetapprenti");
	}
	
	@Test
	public void testAddParcours() throws Exception {	
		ParcoursPostBts tmp = new ParcoursPostBts(app, "Autre", java.sql.Date.valueOf("2016-02-20"));
		int tailleAvantAjout = app.getParcoursPostBtses().size();
		
		try {
			HibernateUtil.getParcoursDAO().addParcours(tmp);
			}
		catch (Exception e) {
			fail("Echec ajout parcours"); //Test echoue
		}	
		setUpApprenti();
		//Verification de la taille des parcours post bts
		assertEquals(tailleAvantAjout + 1, app.getParcoursPostBtses().size());
	}
	
	@Test
	public void testDelParcours() throws Exception {
		//Récupération d'un parcours de l'apprenti et de la taille des parcours
		ParcoursPostBts tmp = app.getParcoursPostBtses().iterator().next(); //Le set de parcours n'est pas vide pour le test
		int tailleAvantSupp = app.getParcoursPostBtses().size();
		try {
			HibernateUtil.getParcoursDAO().delParcours(tmp);
		} catch (Exception e) {
			fail("Erreur de suppression");
		}
		setUpApprenti();
		assertEquals(tailleAvantSupp - 1, app.getParcoursPostBtses().size()); //Vérification taille
		assertFalse(app.getParcoursPostBtses().contains(tmp)); //Vérification que le parcours n'est plus présent
	}
	
	@Test
	public void testGetParcoursById() {
		int idP = app.getParcoursPostBtses().iterator().next().getIdparcoursPostBts(); //On récupère un id existant
		try {
			//On vérifie qu'on récupère bien un parcours Post Bts
			assertEquals(ParcoursPostBts.class, HibernateUtil.getParcoursDAO().getParcoursById(idP).getClass());	
		} catch (Exception e) {
			fail("Echec de récupération");
		}
	}
	
	@Test
	public void getParcoursByApp() {
		//On s'attend à recevoir un hashset de Parcours Post Bts
		HashSet<ParcoursPostBts> typeExpected = new HashSet<>();
		try {
			assertEquals(typeExpected.getClass(), HibernateUtil.getParcoursDAO().getParcoursByApp(app).getClass());
		} catch (Exception e) {
			fail("Echec de récupération");
		}
	}
	
	@Test
	public void updateParcours() {
		//Récupération d'un parcours de l'apprenti et de la taille des parcours
		ParcoursPostBts tmp = app.getParcoursPostBtses().iterator().next(); //Le set de parcours n'est pas vide pour le test
		ParcoursPostBts afterUpdt;
		assertFalse(tmp.getFonction().equals("Webmaster")); //On s'assure que la fonction n'est pas celle qu'on va modifier
		
		tmp.setFonction("Webmaster");
		try {
			HibernateUtil.getParcoursDAO().updateParcours(tmp);
		} catch (Exception e) {
			fail("Erreur de mise à jour");
		}
		
		//On tente de récupérer le parcours grace a son ID et on compare les deux
		try {
			afterUpdt = HibernateUtil.getParcoursDAO().getParcoursById(tmp.getIdparcoursPostBts());
			assertEquals(tmp.getFonction(), afterUpdt.getFonction());
		} catch (Exception e) {
			fail("Echec de récupération");
		}
	}
	
	

}
