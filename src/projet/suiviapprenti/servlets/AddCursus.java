package projet.suiviapprenti.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.suiviapprenti.enums.FonctionPostBts;
import projet.suiviapprenti.forms.CursusForm;
import projet.suiviapprenti.forms.ParcoursForm;

/**
 * Servlet implementation class AddCursus
 */
@WebServlet("/addCursus")
public class AddCursus extends HttpServlet {
	private static final long serialVersionUID 	= 1L;
	public static final String VUE_ADD_CURSUS	= "/logged/addCursus.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCursus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_ADD_CURSUS).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CursusForm  cursusForm = new CursusForm();
		cursusForm.verifierAjoutCursus(request);
		request.setAttribute(UpdateProfile.ATT_ERREURS, cursusForm.getErreurs());
		request.setAttribute(UpdateProfile.ATT_SAISIES, cursusForm.getSaisie());
		if(cursusForm.getErreurs().isEmpty()){
			request.removeAttribute(UpdateProfile.ATT_SAISIES); //Suppression des saisies enregistrées
			response.sendRedirect(request.getContextPath() + Cursus.SERVLET_CURSUS);
		} else {
			this.getServletContext().getRequestDispatcher(VUE_ADD_CURSUS).forward(request, response);
		}
	}

}
