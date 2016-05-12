package projet.suiviapprenti.filtre;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
	
	public static final String ATT_SESSION				= "logged";
	public static final String SERVLET_CONNECTION 		= "/login";
	public static final String SERVLET_PROFILE			= "/profile";
	
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
        /* Non-filtrage des ressources statiques */
        String chemin = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (chemin.startsWith("/css") || chemin.startsWith("/images") || chemin.startsWith("/js")) {
            chain.doFilter( httpRequest, httpResponse );
            return;
        }
		
		if(httpRequest.getSession().getAttribute(ATT_SESSION) == null) { //Si la session n'existe pas donc si l'utilisateur n'est pas connecté
			httpRequest.getRequestDispatcher(SERVLET_CONNECTION).forward(httpRequest, httpResponse);
		}
		else {
			
			if(chemin.endsWith("/")) {
				httpRequest.getRequestDispatcher(SERVLET_PROFILE).forward(httpRequest, httpResponse);
			}
			else {
				chain.doFilter(httpRequest, httpResponse);
			}
			
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
