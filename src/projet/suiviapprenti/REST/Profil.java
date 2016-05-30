package projet.suiviapprenti.REST;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.REST.JSON.JSONViews;
import projet.suiviapprenti.forms.ParcoursForm;
import projet.suiviapprenti.forms.ProfileForm;

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
				jsonReturn = mapper.writerWithDefaultPrettyPrinter().withView(JSONViews.InfoProfil.class).writeValueAsString(app);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return jsonReturn;
	}
	
	@GET
	@Path("/logoff")
	public void disconnect(@Context HttpServletRequest request) {
		Apprenti app = (Apprenti) request.getSession().getAttribute(Login.SESSION_APP);
		if(app != null) request.getSession().removeAttribute(Login.SESSION_APP);
	}
	
	/**
	 * 
	 * @param request contenant les champs avec leurs valeurs 
	 * @return chaine vide en cas de succès, Liste d'erreurs en JSON en cas d'erreur
	 */
	@POST
	@Path("/modifierProfil")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifierApprenti(@Context HttpServletRequest request) {
		String jsonReturn = "Veuillez vous connecter...";
		Apprenti app = (Apprenti) request.getSession().getAttribute(Login.SESSION_APP);
		if(app != null) {
			ProfileForm profilForm = new ProfileForm();
			profilForm.verifierFormulaire(request);
			if(profilForm.getErreurs().isEmpty()) {
				jsonReturn = "";
			} else {
				ObjectMapper mapper = new ObjectMapper();
				try {
					jsonReturn = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(profilForm.getErreurs());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return jsonReturn;
	}
}
