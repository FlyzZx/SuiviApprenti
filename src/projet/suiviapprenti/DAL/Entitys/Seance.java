package projet.suiviapprenti.DAL.Entitys;
// Generated 15 mars 2016 11:09:10 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Seance generated by hbm2java
 */
@Entity
@Table(name = "seance", catalog = "suiviapprenti")
public class Seance implements java.io.Serializable {

	private Integer idseance;
	private Professeur professeur;
	private Date heureDebut;
	private Date heureFin;
	private Date date;
	private String matiere;
	private Set<ParticipationSeance> participationSeances = new HashSet<ParticipationSeance>(0);

	public Seance() {
	}

	public Seance(Professeur professeur, Date heureDebut, Date heureFin, Date date, String matiere,
			Set<ParticipationSeance> participationSeances) {
		this.professeur = professeur;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.date = date;
		this.matiere = matiere;
		this.participationSeances = participationSeances;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idseance", unique = true, nullable = false)
	public Integer getIdseance() {
		return this.idseance;
	}

	public void setIdseance(Integer idseance) {
		this.idseance = idseance;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idprofesseur")
	public Professeur getProfesseur() {
		return this.professeur;
	}

	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heureDebut", length = 8)
	public Date getHeureDebut() {
		return this.heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heureFin", length = 8)
	public Date getHeureFin() {
		return this.heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "matiere", length = 45)
	public String getMatiere() {
		return this.matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "seance")
	public Set<ParticipationSeance> getParticipationSeances() {
		return this.participationSeances;
	}

	public void setParticipationSeances(Set<ParticipationSeance> participationSeances) {
		this.participationSeances = participationSeances;
	}

}
