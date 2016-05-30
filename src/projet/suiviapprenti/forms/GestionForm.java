package projet.suiviapprenti.forms;

import java.util.Map;

public abstract class GestionForm {
	
	protected Map<String, String> erreurs;
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	protected void verifierRue(String rue) throws Exception {
		if(rue.isEmpty()){
			throw new Exception("Veuillez indiquer un nom de rue");
		}
	}
	
	protected void verifierVille(String ville) throws Exception {
		if(ville.isEmpty()){
			throw new Exception("Veuillez indiquer un nom de ville");
		}
	}
	
	protected void verifierCodePostal(String codePostal) throws Exception {
		if(codePostal.isEmpty()){
			throw new Exception("Veuillez indiquer un code postal");
		}
	}
	
	protected void verifierMail(String mail) throws Exception {
		if(!mail.isEmpty()) {
			//if(!mail.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$")) {
				throw new Exception("Merci de saisir une adresse mail valide !");
			//}
		}
		else { //Si le mail est null
			throw new Exception("Veuillez saisir une adresse mail");
		}
	}
	
	protected void verifierTel(String tel) throws Exception {
		if(tel.isEmpty()) {
			throw new Exception("Veuillez saisir un numéro de téléphone !");
		}
	}
	
	protected void verifierPassword(String pass, String passVerif) throws Exception {
		if(pass.isEmpty() || pass.isEmpty()){
			throw new Exception("Veuillez remplir les deux champs des mots de passe");
		}
		else {
			if(!pass.equals(passVerif)) {
				throw new Exception("Les mots de passe ne correspondent pas");
			}
		}
	}
	
	protected void verifierNonVide(String data) throws Exception {
		if(data.isEmpty()) {
			throw new Exception("Ce champs ne peut pas être vide");
		}
	}
	
	protected void verifierDate(java.sql.Date date) throws Exception {
		if(date == null) {
			throw new Exception("Veuillez spécifier une date valide");
		}
	}
	
	protected void verifierSiteWeb(String site) throws Exception {
		if(site.isEmpty()){
			throw new Exception("Veuillez saisir un site, ou 'aucun'");
		}
	}
}
