package projet.suiviapprenti.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;

public class ConnectionForm extends GestionForm {
	public static final String CHAMP_MAIL 				= "mail";
	public static final String CHAMP_PWD  				= "pass";
	public static final String CHAMP_ERREUR_CONNEXION	= "erreurConnexion";
		
	public ConnectionForm() {
		erreurs = new HashMap<String, String>();
	}
	
	public Apprenti verifierAcces(HttpServletRequest request) {
		String mail = request.getParameter(CHAMP_MAIL);
		String pass = request.getParameter(CHAMP_PWD);
		Apprenti app = null;
		
		//Gestions des erreurs et vérification
		try {
			verifierMail(mail);
		} catch (Exception e) {
			erreurs.put(CHAMP_MAIL, e.getMessage());
		}
		
		try {
			verifierPassword(pass);
		} catch (Exception e) {
			erreurs.put(CHAMP_PWD, e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			try {
				app = HibernateUtil.getApprentiDAO().getApprenti(mail, pass);
			} catch (Exception e) {
				erreurs.put(CHAMP_ERREUR_CONNEXION, e.getMessage());
			}
		}
				
		return app;
	}
		
	private void verifierPassword(String pass) throws Exception {
		if(pass.isEmpty()) {
			throw new Exception("Le mot de passe ne peut pas être absent !");
		}
	}
}
