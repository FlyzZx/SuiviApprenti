package projet.suiviapprenti.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;

public class TestApprentiDAO {

	
	@Test
	public void testIdentification() throws Exception {
		//On test l'égalité du mail entré avec celle du mail récupéré une fois connecté
		assertEquals("nicolasfaraci@outlook.com", HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projetapprenti").getCoordonnees().getEmail());
		//On test l'appel de l'exception en cas d'erreur
		try {
			HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projet");
		} catch (Exception e) {
			return; //L'exception a été levée, test validé
		}
	}
	
	@Test
	public void testUpdateCorrect() throws Exception {
		Apprenti appTest;
		appTest = HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projetapprenti"); //On récupère un apprenti valide
		appTest.setLieuNaissance("Lille");
		HibernateUtil.getApprentiDAO().updateApprenti(appTest);
		//On vérifie l'égalité entre le champs entré et le champs sauvegarder en base
		assertEquals("Lille", HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projetapprenti").getLieuNaissance()); 
		
		appTest.setLieuNaissance("Lens");
		HibernateUtil.getApprentiDAO().updateApprenti(appTest);
		//On vérifie l'égalité entre le champs entré et le champs sauvegarder en base une seconde fois pour être sur de l'enregistrement
		assertEquals("Lens", HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projetapprenti").getLieuNaissance());
		
		try {
			//On test l'appel de l'exception en cas d'erreur
			Apprenti app = new Apprenti();
			HibernateUtil.getApprentiDAO().updateApprenti(app);	
		} catch (Exception e) {
			return; //L'exception a été levée, test validé
		}
	}
}
