<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="projet.suiviapprenti.DAL.HibernateUtil"%>
<%@page import="projet.suiviapprenti.DAL.Entitys.Entreprise"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
	JSONArray jsonArr = new JSONArray();
	JSONObject jsonObj = new JSONObject();
	List<Entreprise> ent = HibernateUtil.getEntrepriseDAO().getEntreprises();
	Iterator i = ent.iterator();
	String query = (String)request.getParameter("term");
	String choix = (String)request.getParameter("choix");
	switch(choix) {
	case "autocomplete":
		while(i.hasNext()) {
			Entreprise tmp = (Entreprise) i.next();
			if(tmp.getNomEntreprise().toUpperCase().startsWith(query.toUpperCase())) {
				jsonArr.add(tmp.getNomEntreprise());
			}
		}
		out.print(jsonArr);
		break;
	case "entchoose":
		while(i.hasNext()) {
			Entreprise tmp = (Entreprise) i.next();
			if(tmp.getNomEntreprise().toUpperCase().equals(query.toUpperCase())) { //Si le nom de l'entreprise est celui actuel dans la boucle
				jsonObj.put("identreprise", tmp.getIdentreprise());
				jsonObj.put("branche", tmp.getBranche());
				jsonObj.put("nbSalaries", tmp.getNbSalaries());
				jsonObj.put("rue", tmp.getCoordonnees().getRue());
				jsonObj.put("ville", tmp.getCoordonnees().getVille());
				jsonObj.put("codePostal", tmp.getCoordonnees().getCodePostal());
				jsonObj.put("telephone", tmp.getCoordonnees().getTelephone());
				jsonObj.put("mobile", tmp.getCoordonnees().getMobile());
				jsonObj.put("email", tmp.getCoordonnees().getEmail());
				jsonObj.put("site", tmp.getCoordonnees().getSite());
			}
		}
		out.print(jsonObj);
		break;
	default:
		break;
	}
%>
