package projet.suiviapprenti.REST;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.map.ObjectMapper;

import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.REST.util.HttpParamWrapper;
import projet.suiviapprenti.forms.ParcoursForm;
import projet.suiviapprenti.forms.ProfileForm;

@Path("/parcours")
public class Parcours {
	
	@POST
	@Path("/ajouterParcours")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String ajouterParcours(MultivaluedMap<String, String> params, @Context HttpServletRequest request) {
		String jsonReturn = "Veuillez vous connecter...";
		Apprenti app = (Apprenti) request.getSession().getAttribute(Login.SESSION_APP);
		HttpParamWrapper wrapperRequest = new HttpParamWrapper(request, params);
		if(app != null) {
			//TODO Ajouter le parcours
			ParcoursForm parcoursForm = new ParcoursForm();
			parcoursForm.verifierAjoutParcours(wrapperRequest);
			if(parcoursForm.getErreurs().isEmpty()) {
				jsonReturn = "";
			} else {
				ObjectMapper mapper = new ObjectMapper();
				try {
					jsonReturn = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parcoursForm.getErreurs());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return jsonReturn;
	}
	
	@POST
	@Path("/modifierParcours")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String modifierParcours(MultivaluedMap<String, String> params, @Context HttpServletRequest request) {
		String jsonReturn = "Veuillez vous connecter...";
		Apprenti app = (Apprenti) request.getSession().getAttribute(Login.SESSION_APP);
		HttpParamWrapper wrapperRequest = new HttpParamWrapper(request, params);
		if(app != null) {
			//TODO Ajouter le parcours
			ParcoursForm parcoursForm = new ParcoursForm();
			parcoursForm.verifierUpdateParcours(wrapperRequest);
			if(parcoursForm.getErreurs().isEmpty()) {
				jsonReturn = "";
			} else {
				ObjectMapper mapper = new ObjectMapper();
				try {
					jsonReturn = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parcoursForm.getErreurs());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return jsonReturn;
	}
	
	@POST
	@Path("/supprimerParcours")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String supprimerParcours(MultivaluedMap<String, String> params, @Context HttpServletRequest request) {
		String jsonReturn = "Veuillez vous connecter...";
		Apprenti app = (Apprenti) request.getSession().getAttribute(Login.SESSION_APP);
		HttpParamWrapper wrapperRequest = new HttpParamWrapper(request, params);
		if(app != null) {
			//TODO Ajouter le parcours
			ParcoursForm parcoursForm = new ParcoursForm();
			parcoursForm.verifierDeleteParcours(wrapperRequest);
			if(parcoursForm.getErreurs().isEmpty()) {
				jsonReturn = "";
			} else {
				ObjectMapper mapper = new ObjectMapper();
				try {
					jsonReturn = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parcoursForm.getErreurs());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return jsonReturn;
	}
}
