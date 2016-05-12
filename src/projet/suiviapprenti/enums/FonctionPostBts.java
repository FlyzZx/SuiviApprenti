package projet.suiviapprenti.enums;

public enum FonctionPostBts {
	Developpeur("D�veloppeur"),
	AdministrateurReseau("Administrateur R�seau"),
	Ingenieur("Ing�nieur"),
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
