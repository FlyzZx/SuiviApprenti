package projet.suiviapprenti.DAL;

import java.util.Set;

import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.DAL.Entitys.Cursusformation;

public interface CursusDAO {
	public void addCursus(Cursusformation cursus) throws Exception;
	public void updateCursus(Cursusformation cursus) throws Exception;
	public void deleteCursus(Cursusformation cursus) throws Exception;
	public Cursusformation getCursusById(int id) throws Exception;
	public Set<Cursusformation> getCursusByApp(Apprenti app) throws Exception;
}
