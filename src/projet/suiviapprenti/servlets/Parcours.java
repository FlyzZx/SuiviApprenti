package projet.suiviapprenti.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Parcours
 */
@WebServlet("/parcours")
public class Parcours extends HttpServlet {
	private static final long serialVersionUID 	= 1L;
	public static final String VUE_PARCOURS		= "/logged/parcours.jsp";
	public static final String SERVLET_PARCOURS	= "/parcours";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parcours() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_PARCOURS).forward(request, response);
	}

}
