package projet.suiviapprenti.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.suiviapprenti.DAL.Entitys.ParcoursPostBts;
import projet.suiviapprenti.enums.FonctionPostBts;
import projet.suiviapprenti.forms.ParcoursForm;

/**
 * Servlet implementation class ModifParcours
 */
@WebServlet("/modifParcours")
public class ModifParcours extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_MODIF_PARCOURS		= "/logged/modifParcours.jsp";
	public static final String SERVLET_MODIF_PARCOURS	= "/modifParcours";
	public static final String ATT_PARCOURS				= "parcours";
	public static final String ATT_ACTION				= "action";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifParcours() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParcoursForm pForm = new ParcoursForm();
		ParcoursPostBts parcours = pForm.verifierIdParcours(request); //Vérification de l'ID
		if(parcours != null) { //Si le parcours spécifié est bien associé à l'apprenti connecté
			request.setAttribute(ATT_PARCOURS, parcours);
			request.setAttribute(AddParcours.ATT_FONCTION_ENUM, FonctionPostBts.values());
			//request.setAttribute(AddParcours.ATT_ENT_SELECT, pForm.getEntreprisesList()); //Récupération de la liste des entreprise pour le select du formulaire
			this.getServletContext().getRequestDispatcher(VUE_MODIF_PARCOURS).forward(request, response);
		} else { //Si l'apprenti a modifié le parametre get et que le parcours n'est pas associé à lui
			response.sendRedirect(request.getContextPath() + UpdateProfile.SERVLET_PROFILE);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParcoursForm pForm = new ParcoursForm();
		String action = request.getParameter(ATT_ACTION);
		ParcoursPostBts parcours = pForm.verifierIdParcours(request); //Vérification de l'ID
		switch (action) {
			case "modifier":
				pForm.verifierUpdateParcours(request);
				request.setAttribute(UpdateProfile.ATT_SAISIES, pForm.getSaisies());
				request.setAttribute(UpdateProfile.ATT_ERREURS, pForm.getErreurs());
				if(pForm.getErreurs().isEmpty()) {
					request.removeAttribute(UpdateProfile.ATT_SAISIES);
					response.sendRedirect(request.getContextPath() + Parcours.SERVLET_PARCOURS);
				} else {
					request.setAttribute(ATT_PARCOURS, parcours);
					request.setAttribute(AddParcours.ATT_FONCTION_ENUM, FonctionPostBts.values());
					request.getRequestDispatcher(VUE_MODIF_PARCOURS).forward(request, response);
				}
				break;
			case "supprimer":
				pForm.verifierDeleteParcours(request);
				request.setAttribute(UpdateProfile.ATT_ERREURS, pForm.getErreurs());
				if(pForm.getErreurs().isEmpty()) {
					response.sendRedirect(request.getContextPath() + Parcours.SERVLET_PARCOURS);
				} else {
					request.setAttribute(UpdateProfile.ATT_ERREURS, pForm.getErreurs());
					request.setAttribute(AddParcours.ATT_ENT_SELECT, pForm.getEntreprisesList()); //Récupération de la liste des entreprise pour le select du formulaire
					request.getRequestDispatcher(VUE_MODIF_PARCOURS).forward(request, response);
				}
				break;
			default:
				
				break;				
		}

	}

}
