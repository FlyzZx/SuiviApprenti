package projet.suiviapprenti.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.suiviapprenti.DAL.Entitys.Apprenti;
import projet.suiviapprenti.forms.ConnectionForm;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VUE_CONNECTION 			= "/login.jsp";
	public static final String SERVLET_PROFILE			= "/profile";
	public static final String ATT_SESSION				= "logged";
	public static final String ATT_ERREURS				= "erreurs";
	

	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_CONNECTION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionForm form = new ConnectionForm();
		Apprenti app = null;

		app = form.verifierAcces(request);
		
		request.setAttribute(ATT_ERREURS, form.getErreurs());
		
		if(form.getErreurs().isEmpty()) {
			request.getSession().setAttribute(ATT_SESSION, app);
			response.sendRedirect(request.getContextPath() + SERVLET_PROFILE); //Redirection vers le profil
		}
		else {
			this.getServletContext().getRequestDispatcher(VUE_CONNECTION).forward(request, response);
		}
	}

}
