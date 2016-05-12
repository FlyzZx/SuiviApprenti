package projet.suiviapprenti.DAL.Entitys;
// Generated 23 f�vr. 2016 13:46:09 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Cursusformation generated by hbm2java
 */
@Entity
@Table(name = "cursusformation", catalog = "suiviapprenti")
public class Cursusformation implements java.io.Serializable {

	private Integer idcursusformation;
	private Apprenti apprenti;
	private String type;
	private Date annee;
	private String titreObtenu;
	private String titreComplement;

	public Cursusformation() {
	}

	public Cursusformation(Apprenti apprenti, String type, Date annee, String titreObtenu, String titreComplement) {
		this.apprenti = apprenti;
		this.type = type;
		this.annee = annee;
		this.titreObtenu = titreObtenu;
		this.titreComplement = titreComplement;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idcursusformation", unique = true, nullable = false)
	public Integer getIdcursusformation() {
		return this.idcursusformation;
	}

	public void setIdcursusformation(Integer idcursusformation) {
		this.idcursusformation = idcursusformation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEleve", nullable = false)
	public Apprenti getApprenti() {
		return this.apprenti;
	}

	public void setApprenti(Apprenti apprenti) {
		this.apprenti = apprenti;
	}

	@Column(name = "type", nullable = false, length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "annee", nullable = false, length = 10)
	public Date getAnnee() {
		return this.annee;
	}

	public void setAnnee(Date annee) {
		this.annee = annee;
	}

	@Column(name = "titreObtenu", nullable = false, length = 4)
	public String getTitreObtenu() {
		return this.titreObtenu;
	}

	public void setTitreObtenu(String titreObtenu) {
		this.titreObtenu = titreObtenu;
	}

	@Column(name = "titreComplement", nullable = false, length = 50)
	public String getTitreComplement() {
		return this.titreComplement;
	}

	public void setTitreComplement(String titreComplement) {
		this.titreComplement = titreComplement;
	}

}
