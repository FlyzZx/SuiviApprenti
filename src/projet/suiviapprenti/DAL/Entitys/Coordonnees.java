package projet.suiviapprenti.DAL.Entitys;
// Generated 23 f�vr. 2016 13:46:09 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonView;

import projet.suiviapprenti.REST.JSON.JSONViews;

/**
 * Coordonnees generated by hbm2java
 */
@Entity
@Table(name = "coordonnees", catalog = "suiviapprenti")
public class Coordonnees implements java.io.Serializable {

	@JsonView(JSONViews.InfoProfil.class) private Integer idcoordonnees;
	@JsonView(JSONViews.InfoProfil.class) private String rue;
	@JsonView(JSONViews.InfoProfil.class) private String ville;
	@JsonView(JSONViews.InfoProfil.class) private String codePostal;
	@JsonView(JSONViews.InfoProfil.class) private String telephone;
	@JsonView(JSONViews.InfoProfil.class) private String mobile;
	@JsonView(JSONViews.InfoProfil.class) private String email;
	@JsonView(JSONViews.InfoProfil.class) private String site;
	private Set<Apprenti> apprentis = new HashSet<Apprenti>(0);
	private Set<Entreprise> entreprises = new HashSet<Entreprise>(0);

	public Coordonnees() {
	}

	public Coordonnees(String rue, String ville, String codePostal, String telephone, String mobile, String email,
			String site) {
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.telephone = telephone;
		this.mobile = mobile;
		this.email = email;
		this.site = site;
	}

	public Coordonnees(String rue, String ville, String codePostal, String telephone, String mobile, String email,
			String site, Set<Apprenti> apprentis, Set<Entreprise> entreprises) {
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.telephone = telephone;
		this.mobile = mobile;
		this.email = email;
		this.site = site;
		this.apprentis = apprentis;
		this.entreprises = entreprises;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idcoordonnees", unique = true, nullable = false)
	public Integer getIdcoordonnees() {
		return this.idcoordonnees;
	}

	public void setIdcoordonnees(Integer idcoordonnees) {
		this.idcoordonnees = idcoordonnees;
	}

	@Column(name = "rue", nullable = false, length = 80)
	public String getRue() {
		return this.rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	@Column(name = "ville", nullable = false, length = 40)
	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Column(name = "codePostal", nullable = false, length = 10)
	public String getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	@Column(name = "telephone", nullable = false, length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "mobile", nullable = false, length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "site", nullable = false, length = 40)
	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coordonnees")
	public Set<Apprenti> getApprentis() {
		return this.apprentis;
	}

	public void setApprentis(Set<Apprenti> apprentis) {
		this.apprentis = apprentis;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coordonnees")
	public Set<Entreprise> getEntreprises() {
		return this.entreprises;
	}

	public void setEntreprises(Set<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

}
