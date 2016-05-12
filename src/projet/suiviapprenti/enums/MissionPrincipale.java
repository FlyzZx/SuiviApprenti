package projet.suiviapprenti.enums;

public enum MissionPrincipale {
	DeveloppementWeb("Développement Web"),
	DeveloppementLogiciel("Développement Logiciel"),
	Reseau("Réseau"),
	Systeme("Système"),
	Autre("Autre");
	
	private String label = "";
	
	//Constructeur
	MissionPrincipale(String label){
		this.label = label;
	}
	
	public String getString(){
		return this.label;
	}
}
