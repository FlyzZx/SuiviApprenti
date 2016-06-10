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

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.REST.JSON.JSONViews;

@Path("/entreprise")
public class Entreprise {
	private static final String ERR_LOG		= "Veuillez d'abord vous connecter";
	private static final String VUE_AT_ENT	= "logged/ajax/enterprises.jsp";
	
	@GET
	@Path("/autocomplete")
	public String autocompleteEntreprise(@Context HttpServletRequest request,
			 							@Context HttpServletResponse response, 
										@QueryParam("term") String term) {
		String retour = ERR_LOG;
		ObjectMapper objMap = new ObjectMapper();
		Apprenti app = (Apprenti) request.getSession().getAttribute(Login.SESSION_APP);
		if(app != null) {
			List<projet.suiviapprenti.DAL.Entitys.Entreprise> ent;
			objMap.configure(Feature.DEFAULT_VIEW_INCLUSION, false); //Utiliser uniquement les champs marqués
			try {
				ent = HibernateUtil.getEntrepriseDAO().getEntrepriseBeginBy(term);
				retour = objMap.writerWithDefaultPrettyPrinter().withView(JSONViews.AutocompleteEntreprise.class).writeValueAsString(ent);

			} catch (Exception e) {
				retour = e.getMessage();
			}
		}
		return retour;
	}
}
