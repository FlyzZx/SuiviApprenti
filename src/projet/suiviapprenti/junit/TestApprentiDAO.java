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
		//On test l'�galit� du mail entr� avec celle du mail r�cup�r� une fois connect�
		assertEquals("nicolasfaraci@outlook.com", HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projetapprenti").getCoordonnees().getEmail());
		//On test l'appel de l'exception en cas d'erreur
		try {
			HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projet");
		} catch (Exception e) {
			return; //L'exception a �t� lev�e, test valid�
		}
	}
	
	@Test
	public void testUpdateCorrect() throws Exception {
		Apprenti appTest;
		appTest = HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projetapprenti"); //On r�cup�re un apprenti valide
		appTest.setLieuNaissance("Lille");
		HibernateUtil.getApprentiDAO().updateApprenti(appTest);
		//On v�rifie l'�galit� entre le champs entr� et le champs sauvegarder en base
		assertEquals("Lille", HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projetapprenti").getLieuNaissance()); 
		
		appTest.setLieuNaissance("Lens");
		HibernateUtil.getApprentiDAO().updateApprenti(appTest);
		//On v�rifie l'�galit� entre le champs entr� et le champs sauvegarder en base une seconde fois pour �tre sur de l'enregistrement
		assertEquals("Lens", HibernateUtil.getApprentiDAO().getApprenti("nicolasfaraci@outlook.com", "projetapprenti").getLieuNaissance());
		
		try {
			//On test l'appel de l'exception en cas d'erreur
			Apprenti app = new Apprenti();
			HibernateUtil.getApprentiDAO().updateApprenti(app);	
		} catch (Exception e) {
			return; //L'exception a �t� lev�e, test valid�
		}
	}
}
