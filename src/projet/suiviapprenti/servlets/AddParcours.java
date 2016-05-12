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
 * Servlet implementation class UpdateParcours
 */
@WebServlet("/addParcours")
public class AddParcours extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_ADD_PARCOURS		= "/logged/addParcours.jsp";
	public static final String ATT_FONCTION_ENUM	= "fonctions";
	public static final String ATT_ENT_SELECT		= "entreprises";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddParcours() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParcoursForm parcoursForm = new ParcoursForm();
		//request.setAttribute(ATT_ENT_SELECT, parcoursForm.getEntreprisesList()); //Récupération de la liste des entreprise pour le select du formulaire
		request.setAttribute(ATT_FONCTION_ENUM, FonctionPostBts.values());
		this.getServletContext().getRequestDispatcher(VUE_ADD_PARCOURS).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParcoursForm  parcoursForm = new ParcoursForm();
		parcoursForm.verifierAjoutParcours(request);
		request.setAttribute(UpdateProfile.ATT_ERREURS, parcoursForm.getErreurs());
		request.setAttribute(UpdateProfile.ATT_SAISIES, parcoursForm.getSaisies());
		if(parcoursForm.getErreurs().isEmpty()){
			request.removeAttribute(UpdateProfile.ATT_SAISIES); //Suppression des saisies enregistrées
			response.sendRedirect(request.getContextPath() + Parcours.SERVLET_PARCOURS);
		} else {
			request.setAttribute(ATT_FONCTION_ENUM, FonctionPostBts.values());
			//request.setAttribute(ATT_ENT_SELECT, parcoursForm.getEntreprisesList()); //Récupération de la liste des entreprise pour le select du formulaire
			this.getServletContext().getRequestDispatcher(VUE_ADD_PARCOURS).forward(request, response);
		}

	}

}
