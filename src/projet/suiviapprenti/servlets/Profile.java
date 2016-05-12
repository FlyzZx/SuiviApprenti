package projet.suiviapprenti.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.suiviapprenti.DAL.HibernateUtil;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_PROFILE = "/logged/profile.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_PROFILE).forward(request, response);
	}


}
