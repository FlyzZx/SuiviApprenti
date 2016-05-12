package projet.suiviapprenti.enums;

public enum TypeFormation {
	License("License"),
	Master("Master"),
	Formation("Formation"),
	Ingenieur("Ingénieur"),
	Autre("Autre");
	
	private String label = "";
	
	//Constructeur
	TypeFormation(String label){
		this.label = label;
	}
	
	public String getString(){
		return this.label;
	}
}
