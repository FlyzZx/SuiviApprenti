package projet.suiviapprenti.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.DAL.Entitys.Cursusformation;
import projet.suiviapprenti.servlets.Login;

public class CursusForm extends GestionForm {
	public static final String CHAMP_DIPLOME		= "diplome";
	public static final String CHAMP_SPECIALISATION	= "specialisation";
	public static final String CHAMP_DATE_DEBUT		= "annee";
	public static final String CHAMP_OBTENTION		= "obtention";
	public static final String CHAMP_ERREUR_ADD		= "errAjout";
	
	private Map<String, String> saisies;
	
	public CursusForm() {
		erreurs = new HashMap<String, String>(0);
		saisies = new HashMap<String, String>(0);
	}
	
	public Map<String, String> getSaisie() {
		return saisies;
	}
	
	public void verifierAjoutCursus(HttpServletRequest request) {
		String diplome			= request.getParameter(CHAMP_DIPLOME);
		String specialisation	= request.getParameter(CHAMP_SPECIALISATION);
		String dateDebut		= request.getParameter(CHAMP_DATE_DEBUT);
		String obtention		= request.getParameter(CHAMP_OBTENTION);
		
		Cursusformation cursus = new Cursusformation();
		
		//Verification diplome
		saisies.put(CHAMP_DIPLOME, diplome);
		try {
			verifierNonVide(diplome);
			cursus.setType(diplome);
		} catch (Exception e) {
			erreurs.put(CHAMP_DIPLOME, e.getMessage());
		}
		
		//SPECIALISATION 
		saisies.put(CHAMP_SPECIALISATION, specialisation);
		cursus.setTitreComplement(specialisation);
		
		
		//Vérification date
		saisies.put(CHAMP_DATE_DEBUT, dateDebut);
		java.sql.Date d;
		try {
			d = java.sql.Date.valueOf(dateDebut);
		} catch (Exception e1) {
			d = null;
		}
		try {
			verifierDate(d);
			cursus.setAnnee(d);
		} catch (Exception e) {
			erreurs.put(CHAMP_DATE_DEBUT, e.getMessage());
		}
		
		//Verification obtention
		saisies.put(CHAMP_OBTENTION, obtention);
		try {
			verifierObtention(obtention);
			cursus.setTitreObtenu(obtention);
		} catch (Exception e) {
			erreurs.put(CHAMP_OBTENTION, e.getMessage());
		}
		
		//Ajout en BDD
		Apprenti app = (Apprenti) request.getSession().getAttribute(Login.ATT_SESSION);
		if(erreurs.isEmpty()) {
			cursus.setApprenti(app);
			try {
				HibernateUtil.getCursusDAO().addCursus(cursus);
				app.getCursusformations().add(cursus); //Ajout pour eviter rechargement du bean depuis la BDD
			} catch (Exception e) {
				erreurs.put(CHAMP_ERREUR_ADD, e.getMessage());
			}
		}
		
	}
	
	private void verifierObtention(String obtention) throws Exception {
		if(!obtention.equals("Oui") && !obtention.equals("Non")) {
			throw new Exception("Erreur dans la sélection de l'obtention du diplôme");
		}
	}
}
