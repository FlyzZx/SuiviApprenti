package projet.suiviapprenti.REST;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.json.simple.JSONObject;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.REST.JSON.JSONViews;

@Path("/login")
public class Login {
	public static final String SESSION_APP	= projet.suiviapprenti.servlets.Login.ATT_SESSION;
	
	//Permet la connection d'un utilisateur en REST
	@GET
	@Path("/{identifiant}/{mdp}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTest(@Context HttpServletRequest req, @PathParam("identifiant") String log, @PathParam("mdp") String password) {
		String json_return = "";
		try {
			Apprenti app = HibernateUtil.getApprentiDAO().getApprenti(log, password);
			req.getSession().setAttribute(SESSION_APP, app);
		} catch (Exception e) {
			json_return = e.getMessage();
			req.getSession().removeAttribute(SESSION_APP);
		} 
		return json_return;
	}
}
