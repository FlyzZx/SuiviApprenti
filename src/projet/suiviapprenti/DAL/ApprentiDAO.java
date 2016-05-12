package projet.suiviapprenti.DAL;

import projet.suiviapprenti.DAL.Entitys.Apprenti;

public interface ApprentiDAO {
	public Apprenti getApprenti(String mailEnter, String password) throws Exception;
	public void updateApprenti(Apprenti app) throws Exception;
}
