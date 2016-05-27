package projet.suiviapprenti.REST;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;

@Path("/login")
public class Login {
	public static final String SESSION_APP	= "restLog";
	
	@GET
	@Path("/{identifiant}/{mdp}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTest(@Context HttpServletRequest req, @PathParam("identifiant") String log, @PathParam("mdp") String password) {
		String json_return = null;
		try {
			Apprenti app = HibernateUtil.getApprentiDAO().getApprenti(log, password);
			JSONObject jObj = new JSONObject();
			jObj.put("Apprenti", app);
			json_return = jObj.toJSONString();
			req.getSession().setAttribute(SESSION_APP, app);
			
		} catch (Exception e) {
			json_return = "Utilisateur introuvable !";
			req.getSession().removeAttribute(SESSION_APP);
		}
		return json_return;
	}
	
	@GET
	@Path("/testLog")
	@Produces(MediaType.APPLICATION_JSON)
	public String getApp(@Context HttpServletRequest req) {
		Apprenti app = (Apprenti) req.getSession().getAttribute(SESSION_APP);
		String ret = "Erreur";
		if(app != null) {
			ret = app.getNom();
		}
		
		return ret;
	}
}
