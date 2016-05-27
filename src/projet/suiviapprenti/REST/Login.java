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
import org.json.simple.JSONObject;

import projet.suiviapprenti.DAL.HibernateUtil;
import projet.suiviapprenti.DAL.Entitys.Apprenti;

@Path("/login")
public class Login {
	public static final String SESSION_APP	= "restLog";
	
	//Permet la connection d'un utilisateur en REST
	@GET
	@Path("/{identifiant}/{mdp}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTest(@Context HttpServletRequest req, @PathParam("identifiant") String log, @PathParam("mdp") String password) {
		String json_return = "Connecté";
		try {
			Apprenti app = HibernateUtil.getApprentiDAO().getApprenti(log, password);
			/*JSONObject jObj = new JSONObject();
			jObj.put("Apprenti", app);*/
			req.getSession().setAttribute(SESSION_APP, app);
			ObjectMapper mapper = new ObjectMapper();
			json_return = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(app);
			
		} catch (Exception e) {
			json_return = e.getMessage();
			req.getSession().removeAttribute(SESSION_APP);
		} 
		return json_return;
	}
	
	
	//Permet de savoir si l'utilisateur est actuellement connecté ou non
	@GET
	@Path("/isConnected")
	@Produces(MediaType.APPLICATION_JSON)
	public String getApp(@Context HttpServletRequest req) {
		Apprenti app = (Apprenti) req.getSession().getAttribute(SESSION_APP);
		JSONObject jObj = new JSONObject();
		jObj.put("connected", false);
		if(app != null) {
			jObj.replace("connected", true);
		}
		
		return jObj.toJSONString();
	}
}
