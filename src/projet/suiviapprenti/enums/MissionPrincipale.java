package projet.suiviapprenti.enums;

public enum MissionPrincipale {
	DeveloppementWeb("D�veloppement Web"),
	DeveloppementLogiciel("D�veloppement Logiciel"),
	Reseau("R�seau"),
	Systeme("Syst�me"),
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
