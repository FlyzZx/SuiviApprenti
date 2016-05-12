package projet.suiviapprenti.DAL.Entitys;
// Generated 23 f�vr. 2016 13:46:09 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Secretaire generated by hbm2java
 */
@Entity
@Table(name = "secretaire", catalog = "suiviapprenti")
public class Secretaire implements java.io.Serializable {

	private Integer idsecretaire;
	private String login;
	private String motDePasse;
	private String nom;
	private String prenom;

	public Secretaire() {
	}

	public Secretaire(String login, String motDePasse, String nom, String prenom) {
		this.login = login;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idsecretaire", unique = true, nullable = false)
	public Integer getIdsecretaire() {
		return this.idsecretaire;
	}

	public void setIdsecretaire(Integer idsecretaire) {
		this.idsecretaire = idsecretaire;
	}

	@Column(name = "login", nullable = false, length = 45)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "motDePasse", nullable = false, length = 45)
	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Column(name = "nom", nullable = false, length = 45)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "prenom", nullable = false, length = 45)
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
