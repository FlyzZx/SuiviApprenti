package projet.suiviapprenti.forms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.DAL.Entitys.Coordonnees;
import projet.suiviapprenti.DAL.Entitys.Entreprise;
import projet.suiviapprenti.DAL.Entitys.ParcoursPostBts;
import projet.suiviapprenti.servlets.Login;

public class ParcoursForm extends GestionForm {
	public static final String CHAMP_FONCTION		= "fonction";
	public static final String CHAMP_DATE_DEBUT		= "dateDebut";
	public static final String CHAMP_ID_ENT			= "identreprise";
	public static final String CHAMP_ERREUR_ADD		= "errAjout";
	public static final String CHAMP_ERREUR_DEL		= "errDelParcours";
	public static final String CHAMP_ID_PARCOURS	= "idParcours";
	public static final String ATT_PARCOURS_SELECT	= "select_parcours";
	public static final String CHAMP_NOM_ENT		= "nomEntreprise";
	public static final String CHAMP_BRANCHE_ENT	= "brancheEntreprise";
	public static final String CHAMP_NB_SALARIE_ENT	= "nbSalariesEntreprise";
	public static final String CHAMP_RUE_ENT		= "rueEntreprise";
	public static final String CHAMP_VILLE_ENT		= "villeEntreprise";
	public static final String CHAMP_CODEP_ENT		= "codePostalEntreprise";
	public static final String CHAMP_TELEPHONE_ENT	= "telephoneEntreprise";
	public static final String CHAMP_MOBILE_ENT		= "mobileEntreprise";
	public static final String CHAMP_MAIL_ENT		= "emailEntreprise";
	public static final String CHAMP_SITE_ENT		= "siteEntreprise";
	
	private Map<String, String> saisies;
	
	public ParcoursForm(){
		saisies = new HashMap<String, String>(0);
		erreurs = new HashMap<String, String>(0);
	}
	
	public Map<String, String> getSaisies() {
		return saisies;
	}

	public List<Entreprise> getEntreprisesList() {		
		return HibernateUtil.getEntrepriseDAO().getEntreprises();
	}
	
	public void verifierDeleteParcours(HttpServletRequest request) {
		ParcoursPostBts parcours = null;
		parcours = verifierIdParcours(request);
		
		if (parcours != null) {
			try {
				HibernateUtil.getParcoursDAO().delParcours(parcours);
				Apprenti app = (Apprenti) request.getSession().getAttribute(Login.ATT_SESSION); //Récupération de l'apprenti dans la Session
				app.setParcoursPostBtses(HibernateUtil.getParcoursDAO().getParcoursByApp(app)); //Mise à jour des parcours de l'apprenti
			} catch (Exception e) {
				erreurs.put(CHAMP_ERREUR_DEL, e.getMessage());
			}
		}
			
	}
	
	public void verifierUpdateParcours(HttpServletRequest request) {
		String fonction 			= request.getParameter(CHAMP_FONCTION);
		String dateDebut 			= request.getParameter(CHAMP_DATE_DEBUT);
		String idEntreprise 		= request.getParameter(CHAMP_ID_ENT);
		String idParcours			= request.getParameter(ATT_PARCOURS_SELECT);
		String nomEntreprise		= request.getParameter(CHAMP_NOM_ENT);
		String brancheEntreprise	= request.getParameter(CHAMP_BRANCHE_ENT);
		String nbSalariesEntreprise	= request.getParameter(CHAMP_NB_SALARIE_ENT);
		String rueEntreprise		= request.getParameter(CHAMP_RUE_ENT);
		String villeEntreprise		= request.getParameter(CHAMP_VILLE_ENT);
		String codePostalEntreprise	= request.getParameter(CHAMP_CODEP_ENT);
		String telephoneEntreprise	= request.getParameter(CHAMP_TELEPHONE_ENT);
		String mobileEntreprise		= request.getParameter(CHAMP_MOBILE_ENT);
		String mailEntreprise		= request.getParameter(CHAMP_MAIL_ENT);
		String siteEntreprise		= request.getParameter(CHAMP_SITE_ENT);
		
		int nbSalaries = 0;
		ParcoursPostBts parcours = verifierIdParcours(request);
		Entreprise ent = null;
		ent = HibernateUtil.getEntrepriseDAO().getEntrepriseById(Integer.parseInt(idEntreprise));
		Apprenti app = (Apprenti) request.getSession().getAttribute(Login.ATT_SESSION); //Récupération de l'apprenti dans la Session
		
		try {
			nbSalaries = Integer.parseInt(nbSalariesEntreprise);
		} catch (NumberFormatException e1) {
			nbSalaries = 0;
		}
		
		saisies.put(CHAMP_ID_PARCOURS, idParcours); //Stockage de l'id parcours pour le renvoyer à la page en cas d'erreur (Effectué coté SERVLET)
				
		saisies.put(CHAMP_FONCTION, fonction);
		if(!parcours.getFonction().equals(fonction)) { //Vérification de la difference des champs
			try {
				verifierFonction(fonction);
				parcours.setFonction(fonction);
			} catch (Exception e) {
				erreurs.put(CHAMP_FONCTION, e.getMessage());
			}
		}
		
		saisies.put(CHAMP_DATE_DEBUT, dateDebut);
		java.sql.Date d;
		try {
			d = java.sql.Date.valueOf(dateDebut);
		} catch (Exception e1) {
			d = null;
		}
		if(!parcours.getDateDebut().equals(d)) {
			try {
				verifierDate(d);
				parcours.setDateDebut(d);
			} catch (Exception e) {
				erreurs.put(CHAMP_DATE_DEBUT, e.getMessage());
			}
		}
		
		saisies.put(CHAMP_ID_ENT, idEntreprise);
		/*//L'entreprise peut etre NULL donc on ne vérifie pas l'égalité de l'entreprise saisie et celle présente
		try {
			verifierEntreprise(idEntreprise);
			parcours.setEntreprise(ent);
		} catch (Exception e) {
			parcours.setEntreprise(null);
		}*/
		
		if(ent != null) { //Si l'entreprise existe
			//Vérif nom entreprise
			saisies.put(CHAMP_NOM_ENT, nomEntreprise);
			if(!ent.getNomEntreprise().equals(nomEntreprise)) {
				try {
					verifierNom(nomEntreprise);
					ent.setNomEntreprise(nomEntreprise);
				} catch (Exception e) {
					erreurs.put(CHAMP_NOM_ENT, e.getMessage());
				}
			}
			
			//Vérif branche entreprise
			saisies.put(CHAMP_BRANCHE_ENT, brancheEntreprise);
			if(!ent.getBranche().equals(brancheEntreprise)) {
				try {
					verifierNom(brancheEntreprise);
					ent.setBranche(brancheEntreprise);
				} catch (Exception e) {
					erreurs.put(CHAMP_BRANCHE_ENT, e.getMessage());
				}
			}
			
			//Vérif nombre salaries
			saisies.put(CHAMP_NB_SALARIE_ENT, String.valueOf(nbSalariesEntreprise));
			if(!(ent.getNbSalaries() == nbSalaries)) {
				try {
					verifierNbPositif(nbSalaries);
					ent.setNbSalaries(nbSalaries);
				} catch (Exception e) {
					erreurs.put(CHAMP_NB_SALARIE_ENT, e.getMessage());
				}
			}
			
			//Vérif coordonnées entreprise
			saisies.put(CHAMP_RUE_ENT, rueEntreprise);
			if(!ent.getCoordonnees().getRue().equals(rueEntreprise)) {
				try {
					verifierRue(rueEntreprise);
					ent.getCoordonnees().setRue(rueEntreprise);
				} catch (Exception e) {
					erreurs.put(CHAMP_RUE_ENT, e.getMessage());
				}
			}
			
			saisies.put(CHAMP_VILLE_ENT, villeEntreprise);
			if(!ent.getCoordonnees().getVille().equals(villeEntreprise)) {
				try {
					verifierVille(villeEntreprise);
					ent.getCoordonnees().setVille(villeEntreprise);
				} catch (Exception e) {
					erreurs.put(CHAMP_VILLE_ENT, e.getMessage());
				}
			}
			
			saisies.put(CHAMP_CODEP_ENT, codePostalEntreprise);
			if(!ent.getCoordonnees().getCodePostal().equals(codePostalEntreprise)) {
				try {
					verifierCodePostal(codePostalEntreprise);
					ent.getCoordonnees().setCodePostal(codePostalEntreprise);
				} catch (Exception e) {
					erreurs.put(CHAMP_CODEP_ENT, e.getMessage());
				}
			}
			
			saisies.put(CHAMP_TELEPHONE_ENT, telephoneEntreprise);
			if(!ent.getCoordonnees().getTelephone().equals(telephoneEntreprise)) {
				try {
					verifierTel(telephoneEntreprise);
					ent.getCoordonnees().setTelephone(telephoneEntreprise);
				} catch (Exception e) {
					erreurs.put(CHAMP_TELEPHONE_ENT, e.getMessage());
				}
			}
			
			saisies.put(CHAMP_MOBILE_ENT, mobileEntreprise);
			if(!ent.getCoordonnees().getMobile().equals(mobileEntreprise)) {
				try {
					verifierTel(mobileEntreprise);
					ent.getCoordonnees().setMobile(mobileEntreprise);
				} catch (Exception e) {
					erreurs.put(CHAMP_MOBILE_ENT, e.getMessage());
				}
			}
			
			saisies.put(CHAMP_MAIL_ENT, mailEntreprise);
			if(!ent.getCoordonnees().getEmail().equals(mailEntreprise)) {
				try {
					verifierMail(mailEntreprise);
					ent.getCoordonnees().setEmail(mailEntreprise);
				} catch (Exception e) {
					erreurs.put(CHAMP_MAIL_ENT, e.getMessage());
				}
			}
			
			saisies.put(CHAMP_SITE_ENT, siteEntreprise);
			if(!ent.getCoordonnees().getSite().equals(siteEntreprise)) {
				try {
					verifierSiteWeb(siteEntreprise);
					ent.getCoordonnees().setSite(siteEntreprise);
				} catch (Exception e) {
					erreurs.put(CHAMP_SITE_ENT, e.getMessage());
				}
			}
			
			
		}
		else { //Pas d'entreprise
			parcours.setEntreprise(null);
		}
						
		//Si il n'y a aucunes erreurs
		if(erreurs.isEmpty()) {
			try {
				if(ent != null) parcours.setEntreprise(ent);
				HibernateUtil.getParcoursDAO().updateParcours(parcours);
				app.setParcoursPostBtses(HibernateUtil.getParcoursDAO().getParcoursByApp(app)); //Mise à jour des parcours de l'apprenti
			} catch (Exception e) {
				erreurs.put(CHAMP_ERREUR_ADD, e.getMessage());
			}
		}
		
	}
	
	public ParcoursPostBts verifierIdParcours(HttpServletRequest request) {
		ParcoursPostBts parcours = null;
		try {
			Apprenti app = (Apprenti) request.getSession().getAttribute(Login.ATT_SESSION); //Récupération de l'apprenti dans la Session
			int idParcours = Integer.parseInt(request.getParameter(ATT_PARCOURS_SELECT)); //Récupération de l'id
			
			Iterator<ParcoursPostBts> it_parcours = app.getParcoursPostBtses().iterator();
			
			while(it_parcours.hasNext()) { //Tant qu'il y a des objets dans le set
				ParcoursPostBts tmp = it_parcours.next();
				if(tmp.getIdparcoursPostBts() == idParcours) { //Si l'ID demandé correspond a un des parcours de l'apprenti
					//parcours = tmp;
					try {
						parcours = HibernateUtil.getParcoursDAO().getParcoursById(tmp.getIdparcoursPostBts()); //Récupération direct depuis BDD pour MAJ des données en cas de 
																											   //modification au cours de la session actuelle
					} catch (Exception e) {
						
					}
				}
			}
		} catch (NumberFormatException e) {
			parcours = null;
		}
				
		return parcours;
	}
	
	public void verifierAjoutParcours(HttpServletRequest request) {
		String fonction 			= request.getParameter(CHAMP_FONCTION);
		String dateDebut 			= request.getParameter(CHAMP_DATE_DEBUT);
		String idEntreprise 		= request.getParameter(CHAMP_ID_ENT);
		String nomEntreprise		= request.getParameter(CHAMP_NOM_ENT);
		String brancheEntreprise	= request.getParameter(CHAMP_BRANCHE_ENT);
		String nbSalariesEntreprise	= request.getParameter(CHAMP_NB_SALARIE_ENT);
		String rueEntreprise		= request.getParameter(CHAMP_RUE_ENT);
		String villeEntreprise		= request.getParameter(CHAMP_VILLE_ENT);
		String codePostalEntreprise	= request.getParameter(CHAMP_CODEP_ENT);
		String telephoneEntreprise	= request.getParameter(CHAMP_TELEPHONE_ENT);
		String mobileEntreprise		= request.getParameter(CHAMP_MOBILE_ENT);
		String mailEntreprise		= request.getParameter(CHAMP_MAIL_ENT);
		String siteEntreprise		= request.getParameter(CHAMP_SITE_ENT);
		Apprenti app 				= null;
		Entreprise ent				= new Entreprise();
		ent.setCoordonnees(new Coordonnees());
		ParcoursPostBts parcours 	= new ParcoursPostBts();
		int nbSalaries 				= 0;
		int idEnt					= 0;
		
		//Vérification des champs
		saisies.put(CHAMP_FONCTION, fonction);
		try {
			verifierFonction(fonction);
			parcours.setFonction(fonction);
		} catch (Exception e) {
			erreurs.put(CHAMP_FONCTION, e.getMessage());
		}
		
		saisies.put(CHAMP_DATE_DEBUT, dateDebut);
		java.sql.Date d;
		try {
			d = java.sql.Date.valueOf(dateDebut);
		} catch (Exception e1) {
			d = null;
		}
		try {
			verifierDate(d);
			parcours.setDateDebut(d);
		} catch (Exception e) {
			erreurs.put(CHAMP_DATE_DEBUT, e.getMessage());
		}

		saisies.put(CHAMP_ID_ENT, idEntreprise);
		//Vérif nom entreprise
		saisies.put(CHAMP_NOM_ENT, nomEntreprise);
			try {
				verifierNom(nomEntreprise);
				ent.setNomEntreprise(nomEntreprise);
			} catch (Exception e) {
				erreurs.put(CHAMP_NOM_ENT, e.getMessage());
			}

		
		//Vérif branche entreprise
		saisies.put(CHAMP_BRANCHE_ENT, brancheEntreprise);
			try {
				verifierNom(brancheEntreprise);
				ent.setBranche(brancheEntreprise);
			} catch (Exception e) {
				erreurs.put(CHAMP_BRANCHE_ENT, e.getMessage());
			}
		
		//Vérif nombre salaries
		try {
			nbSalaries = Integer.parseInt(nbSalariesEntreprise);
		} catch (NumberFormatException e1) {
			nbSalaries = 0;
		}
		saisies.put(CHAMP_NB_SALARIE_ENT, nbSalariesEntreprise);
			try {
				verifierNbPositif(nbSalaries);
				ent.setNbSalaries(nbSalaries);
			} catch (Exception e) {
				erreurs.put(CHAMP_NB_SALARIE_ENT, e.getMessage());
			}
		
		//Vérif coordonnées entreprise
		saisies.put(CHAMP_RUE_ENT, rueEntreprise);
			try {
				verifierRue(rueEntreprise);
				ent.getCoordonnees().setRue(rueEntreprise);
			} catch (Exception e) {
				erreurs.put(CHAMP_RUE_ENT, e.getMessage());
			}
		
		saisies.put(CHAMP_VILLE_ENT, villeEntreprise);
			try {
				verifierVille(villeEntreprise);
				ent.getCoordonnees().setVille(villeEntreprise);
			} catch (Exception e) {
				erreurs.put(CHAMP_VILLE_ENT, e.getMessage());
			}
		
		saisies.put(CHAMP_CODEP_ENT, codePostalEntreprise);
			try {
				verifierCodePostal(codePostalEntreprise);
				ent.getCoordonnees().setCodePostal(codePostalEntreprise);
			} catch (Exception e) {
				erreurs.put(CHAMP_CODEP_ENT, e.getMessage());
			}
		
		saisies.put(CHAMP_TELEPHONE_ENT, telephoneEntreprise);
			try {
				verifierTel(telephoneEntreprise);
				ent.getCoordonnees().setTelephone(telephoneEntreprise);
			} catch (Exception e) {
				erreurs.put(CHAMP_TELEPHONE_ENT, e.getMessage());
			}
		
		saisies.put(CHAMP_MOBILE_ENT, mobileEntreprise);
			try {
				verifierTel(mobileEntreprise);
				ent.getCoordonnees().setMobile(mobileEntreprise);
			} catch (Exception e) {
				erreurs.put(CHAMP_MOBILE_ENT, e.getMessage());
			}
		
		saisies.put(CHAMP_MAIL_ENT, mailEntreprise);
			try {
				verifierMail(mailEntreprise);
				ent.getCoordonnees().setEmail(mailEntreprise);
			} catch (Exception e) {
				erreurs.put(CHAMP_MAIL_ENT, e.getMessage());
			}
		
		saisies.put(CHAMP_SITE_ENT, siteEntreprise);
			try {
				verifierSiteWeb(siteEntreprise);
				ent.getCoordonnees().setSite(siteEntreprise);
			} catch (Exception e) {
				erreurs.put(CHAMP_SITE_ENT, e.getMessage());
			}
		
		//Vérification de la création ou non de l'entreprise
		try {
			idEnt = Integer.parseInt(idEntreprise);
		} catch (NumberFormatException e1) {
			idEnt = 0;
		}
		if(idEnt > 0) { //Cas ou l'entreprise à été choisi grâce à l'autocomplete
			ent.setIdentreprise(idEnt);			
		} else if (idEnt == 0) { //Sinon l'entreprise est crée
			
		}
		
		//Récupération et association de l'apprenti au parcours, puis envois en BDD
		//erreurs = new HashMap<String, String>();
		app = (Apprenti) request.getSession().getAttribute(Login.ATT_SESSION);
		if(erreurs.isEmpty() && app != null){
			parcours.setApprenti(app);
			parcours.setEntreprise(ent);
			try {				
				HibernateUtil.getParcoursDAO().addParcours(parcours);
				app.getParcoursPostBtses().add(parcours); //Ajout du parcours dans l'objet app de session pour eviter une requete en BDD de MAJ
				//System.out.print("ok");
			} catch (Exception e) {
				erreurs.put(CHAMP_ERREUR_ADD, e.getMessage());
			}
		}
		else {
			/*System.out.println("Erreur form");
			for(String s : erreurs.values()) {
				System.out.println(s);
			}
			for(String s : saisies.values()) {
				System.out.println(s);
			}*/
		}
	}
		
	private void verifierFonction(String fonction) throws Exception {
		if(fonction.isEmpty()) {
			throw new Exception("La fonction ne peut pas être vide");
		}
	}
	
	
	private void verifierNom(String nom) throws Exception {
		if(nom.isEmpty()) {
			throw new Exception("Ce champ ne peut pas être vide.");
		}
	}
	
	private void verifierNbPositif(int nb) throws Exception {
		if(nb <= 0) {
			throw new Exception("Erreur, la valeur doit être au minimum égale à 1.");
		}
	}
	
	private void verifierEntreprise(String id) throws Exception {
		if(id == "-1") {
			throw new Exception();
		}
	}
	
}
