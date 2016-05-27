package projet.suiviapprenti.REST;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.REST.JSON.JSONViews;

@Path("/profil")
public class Profil {

	@GET
	@Path("/infosPersonnelles")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProfilPerso(@Context HttpServletRequest request) {
		String jsonReturn = "Veuillez vous connecter...";
		Apprenti app = (Apprenti) request.getSession().getAttribute(Login.SESSION_APP);
		if(app != null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.DEFAULT_VIEW_INCLUSION, false); //Utiliser uniquement les champs marqués
			try {
				jsonReturn = mapper.writerWithView(JSONViews.InfoProfil.class).writeValueAsString(app);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return jsonReturn;
	}
}
