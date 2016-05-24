package projet.suiviapprenti.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.suiviapprenti.DAL.Entitys.Cursusformation;
import projet.suiviapprenti.forms.CursusForm;

/**
 * Servlet implementation class ModifCursus
 */
@WebServlet("/modifCursus")
public class ModifCursus extends HttpServlet {
	private static final long serialVersionUID 		= 1L;
	public static final String VUE_MODIF_CURSUS		= "/logged/modifCursus.jsp";
	public static final String SERVLET_MODIF_CURSUS	= "/modifCursus";
	public static final String ATT_CURSUS			= "cursus";
	public static final String ATT_ACTION			= "action";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifCursus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CursusForm cForm = new CursusForm();
		Cursusformation cursus = cForm.verifierIdCursus(request);
		if(cursus != null) {
			request.setAttribute(ATT_CURSUS, cursus);
			this.getServletContext().getRequestDispatcher(VUE_MODIF_CURSUS).forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + UpdateProfile.SERVLET_PROFILE);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CursusForm cForm = new CursusForm();
		Cursusformation cursus = cForm.verifierIdCursus(request);
		String action = request.getParameter(ATT_ACTION);
		switch (action) {
		case "modifier":
			//Update en base
			cForm.verifierModificationCursus(request);
			request.setAttribute(UpdateProfile.ATT_SAISIES, cForm.getSaisie());
			request.setAttribute(UpdateProfile.ATT_ERREURS, cForm.getErreurs());
			if(cForm.getErreurs().isEmpty()) {
				request.removeAttribute(UpdateProfile.ATT_SAISIES); //Suppression des saisies
				response.sendRedirect(request.getContextPath() + Cursus.SERVLET_CURSUS); //Redirection vers page cursus
			} else { //Si il y a erreurs
				request.setAttribute(ATT_CURSUS, cursus);
				this.getServletContext().getRequestDispatcher(VUE_MODIF_CURSUS).forward(request, response);
			}
			break;
		case "supprimer":
			cForm.verifierDeleteCursus(request);
			request.setAttribute(UpdateProfile.ATT_ERREURS, cForm.getErreurs());
			if(cForm.getErreurs().isEmpty()) {
				response.sendRedirect(request.getContextPath() + Cursus.SERVLET_CURSUS);
			} else {
				request.setAttribute(UpdateProfile.ATT_ERREURS, cForm.getErreurs());
				this.getServletContext().getRequestDispatcher(VUE_MODIF_CURSUS).forward(request, response);
			}
			break;
		default:
			break;
		}
	}

}
