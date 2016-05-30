package projet.suiviapprenti.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;

public class ProfileForm extends GestionForm {
	public static final String CHAMP_RUE		= "rue";
	public static final String CHAMP_VILLE		= "ville";
	public static final String CHAMP_POSTAL		= "codePostal";
	public static final String CHAMP_MAIL		= "email";
	public static final String CHAMP_TEL		= "telephone";
	public static final String CHAMP_MOBILE		= "mobile";
	public static final String CHAMP_SITE		= "site";
	public static final String CHAMP_M_PRINCIP	= "missionPrincipale";
	public static final String CHAMP_PASS		= "password";
	public static final String CHAMP_PASS_VERIF	= "passwordVerif";
	public static final String ATT_SESSION		= "logged";
	public static final String ATT_ERR_AJOUT	= "ajout";
	
	private Map<String, String> saisies;
	
	public Map<String, String> getSaisies() {
		return saisies;
	}

	public ProfileForm(){
		erreurs = new HashMap<String, String>();
		saisies = new HashMap<String, String>();
	}
	
	public void verifierFormulaire(HttpServletRequest request) {
		Apprenti app = (Apprenti) request.getSession().getAttribute(ATT_SESSION);
		
		String rue 					= request.getParameter(CHAMP_RUE);
		String ville 				= request.getParameter(CHAMP_VILLE);
		String codePostal			= request.getParameter(CHAMP_POSTAL);
		String mail 				= request.getParameter(CHAMP_MAIL);
		String telephone 			= request.getParameter(CHAMP_TEL);
		String mobile 				= request.getParameter(CHAMP_MOBILE);
		String site 				= request.getParameter(CHAMP_SITE);
		String missionPrincipale 	= request.getParameter(CHAMP_M_PRINCIP);
		String password 			= request.getParameter(CHAMP_PASS);
		String passwordVerif 		= request.getParameter(CHAMP_PASS_VERIF);
		
		if(!app.getCoordonnees().getRue().equals(rue)) { //Si la donnée saisie est différente de la donnée déjà enregistrée
			saisies.put(CHAMP_RUE, rue);
			try {
				verifierRue(rue);
				app.getCoordonnees().setRue(rue);
			} catch (Exception e) {
				erreurs.put(CHAMP_RUE, e.getMessage());
			}
		}
		
		if(!app.getCoordonnees().getVille().equals(ville)) {
			saisies.put(CHAMP_VILLE, ville);
			try {
				verifierVille(ville);
				app.getCoordonnees().setVille(ville);
			} catch (Exception e) {
				erreurs.put(CHAMP_VILLE, e.getMessage());
			}
		}
		
		if(!app.getCoordonnees().getCodePostal().equals(codePostal)) {
			saisies.put(CHAMP_POSTAL, codePostal);
			try {
				verifierCodePostal(codePostal);
				app.getCoordonnees().setCodePostal(codePostal);
			} catch (Exception e) {
				erreurs.put(CHAMP_POSTAL, e.getMessage());
			}
		}
		
		if(!app.getCoordonnees().getEmail().equals(mail)) {
			saisies.put(CHAMP_MAIL, mail);
			try {
				verifierMail(mail);
				app.getCoordonnees().setEmail(mail);
			} catch (Exception e) {
				erreurs.put(CHAMP_MAIL, e.getMessage());
			}
		}
		
		if(!app.getCoordonnees().getTelephone().equals(telephone)) {
			saisies.put(CHAMP_TEL, telephone);
			try {
				verifierTel(telephone);
				app.getCoordonnees().setTelephone(telephone);
			} catch (Exception e) {
				erreurs.put(CHAMP_TEL, e.getMessage());
			}
		}
		
		if(!app.getCoordonnees().getMobile().equals(mobile)) {
			saisies.put(CHAMP_MOBILE, mobile);
			try {
				verifierTel(mobile);
				app.getCoordonnees().setMobile(mobile);
			} catch (Exception e) {
				erreurs.put(CHAMP_MOBILE, e.getMessage());
			}
		}
		
		if(!app.getCoordonnees().getSite().equals(site)) {
			saisies.put(CHAMP_SITE, site);
			try {
				verifierSiteWeb(site);
				app.getCoordonnees().setSite(site);
			} catch (Exception e) {
				erreurs.put(CHAMP_SITE, e.getMessage());
			}
		}
		
		if(!app.getMissionPrincipale().equals(missionPrincipale)) {
			saisies.put(CHAMP_M_PRINCIP, missionPrincipale);
			try {
				verifierMission(missionPrincipale);
				app.setMissionPrincipale(missionPrincipale);
			} catch (Exception e) {
				erreurs.put(CHAMP_M_PRINCIP, e.getMessage());
			}
		}
		
		if(!password.isEmpty() || !passwordVerif.isEmpty()) {
			try {
				verifierPassword(password, passwordVerif);
				app.setMotDePasse(password);
			} catch (Exception e) {
				erreurs.put(CHAMP_PASS, e.getMessage());
			}
		}
		
		if(erreurs.isEmpty()) {
			try {
				HibernateUtil.getApprentiDAO().updateApprenti(app);
			} catch (Exception e) {
				erreurs.put(ATT_ERR_AJOUT, e.getMessage());
			}
		}				
	}
			
	private void verifierMission(String mission) throws Exception {
		if(mission.isEmpty()) {
			throw new Exception("Veuillez saisir une mission principale");
		}
	}
}
