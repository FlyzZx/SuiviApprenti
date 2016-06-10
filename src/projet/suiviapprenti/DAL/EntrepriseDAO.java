package projet.suiviapprenti.DAL;

import java.util.List;

import projet.suiviapprenti.DAL.Entitys.Entreprise;

public interface EntrepriseDAO {
	public List<Entreprise> getEntreprises();
	public Entreprise getEntrepriseById(int idEntreprise);
	public void addOrUpdateEntreprise(Entreprise ent) throws Exception;
	public Entreprise getLastEntreprise();
	public List<Entreprise> getEntrepriseBeginBy(String begin) throws Exception;
}