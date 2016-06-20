package projet.suiviapprenti.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.suiviapprenti.enums.MissionPrincipale;
import projet.suiviapprenti.forms.ProfileForm;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/updateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VUE_UPDT_PROFILE = "/logged/updateProfile.jsp";
	public static final String SERVLET_PROFILE	= "/profile";
	public static final String ATT_SAISIES		= "saisies";
	public static final String ATT_ERREURS		= "erreurs";
	public static final String ATT_SUCCESS		= "success";
	public static final String ATT_MISSION_ENUM	= "missionPrincipale";
	public static final String MSG_SUCCESS		= "Informations mise à jour avec succès !";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_MISSION_ENUM, MissionPrincipale.values());
		this.getServletContext().getRequestDispatcher(VUE_UPDT_PROFILE).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileForm pform = new ProfileForm();
		pform.verifierFormulaire(request);
		request.setAttribute(ATT_SAISIES, pform.getSaisies());
		request.setAttribute(ATT_ERREURS, pform.getErreurs());
		if (pform.getErreurs().isEmpty()) {
			request.removeAttribute(ATT_SAISIES);
			response.sendRedirect(request.getContextPath() + SERVLET_PROFILE);			
		} else {
			request.setAttribute(ATT_MISSION_ENUM, MissionPrincipale.values());
			request.getRequestDispatcher(VUE_UPDT_PROFILE).forward(request, response);
		}
		
	}

}
