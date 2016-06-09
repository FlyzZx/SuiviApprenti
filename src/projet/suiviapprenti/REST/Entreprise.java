package projet.suiviapprenti.REST;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;

@Path("/entreprise")
public class Entreprise {
	private static final String ERR_LOG		= "Veuillez d'abord vous connecter";
	private static final String VUE_AT_ENT	= "logged/ajax/enterprises.jsp";
	
	@GET
	@Path("/autocomplete")
	public String autocompleteEntreprise(@Context HttpServletRequest request,
			 							@Context HttpServletResponse response,
										@QueryParam("choix") String choix, 
										@QueryParam("term") String term) {
		String retour = ERR_LOG;
		System.out.println(choix);
		Apprenti app = (Apprenti) request.getSession().getAttribute(Login.SESSION_APP);
		if(app != null) {
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			List<projet.suiviapprenti.DAL.Entitys.Entreprise> ent = HibernateUtil.getEntrepriseDAO().getEntreprises();
			Iterator i = ent.iterator();
			String query = (String)request.getParameter("term");

				while(i.hasNext()) {
					projet.suiviapprenti.DAL.Entitys.Entreprise tmp = (projet.suiviapprenti.DAL.Entitys.Entreprise) i.next();
					if(tmp.getNomEntreprise().toUpperCase().startsWith(query.toUpperCase())) {
						jsonArr.add(tmp.getNomEntreprise());
						jsonArr.add(tmp.getIdentreprise());
					}
				}
				retour = jsonArr.toJSONString();

		}
		return retour;
	}
}
