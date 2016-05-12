package projet.suiviapprenti.DAL;

import java.util.Set;

import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.DAL.Entitys.ParcoursPostBts;

public interface ParcoursDAO {
	public void addParcours(ParcoursPostBts parcours) throws Exception;
	public void delParcours(ParcoursPostBts parcours) throws Exception;
	public ParcoursPostBts getParcoursById(int idParcours) throws Exception;
	public void updateParcours(ParcoursPostBts parcours) throws Exception;
	public Set<ParcoursPostBts> getParcoursByApp(Apprenti app) throws Exception;
}
