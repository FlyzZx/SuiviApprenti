package projet.suiviapprenti.enums;

public enum FonctionPostBts {
	Developpeur("Développeur"),
	AdministrateurReseau("Administrateur Réseau"),
	Ingenieur("Ingénieur"),
	Commerce("Commerce"),
	Webmaster("Webmaster"),
	Technicien("Technicien"),
	Autre("Autre");
	
	private String label = "";
	
	//Constructeur
	FonctionPostBts(String label){
		this.label = label;
	}
	
	public String getString(){
		return this.label;
	}
}
