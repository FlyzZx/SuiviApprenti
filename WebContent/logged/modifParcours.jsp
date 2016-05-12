<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier le parcours</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js" ></script>
<script type="text/javascript" src="js/jquery.js" ></script>
</head>
<body>
<c:import url="navigation.jsp"></c:import>
<c:import url="headerb.jsp"></c:import>
	<div class="content2">
	<a href="<c:url value="/parcours" />">Retour aux parcours</a>	
		<form class="form" method="post" action="modifParcours">
		<table>
		<tr><td><h3>Modifier le parcours</h3></td></tr>
		<tr>
			<td><p>Fonction :
			<select name="fonction" id="fonction">
				<c:forEach  var="fonct" items="${fonctions }">
				<c:choose>
					<c:when test="${fonct.string == parcours.fonction }">
						<option value="${fonct.string }" selected><c:out value="${fonct.string }"/></option>
					</c:when>
					<c:otherwise>
						<option value="${fonct.string }"><c:out value="${fonct.string }"/></option>
					</c:otherwise>
				</c:choose>
					
				</c:forEach>
			</select>
			</p>
			<p class="error"><c:out value="${erreurs['fonction'] }" /></p></td>
			<td><p>Date de début : <input  type="date"  name="dateDebut" id="dateDebut" value="<c:out default="${parcours.dateDebut }" value="${saisies['dateDebut'] }" />" /></p>
			<p class="error"><c:out value="${erreurs['dateDebut'] }" /></p></td>
		</tr>
			<tr><td><h3>Informations entreprise</h3></td></tr>
			<tr>
				<td><p>Nom entreprise : <input type="text" name="nomEntreprise" id="nomEntreprise" value="<c:out default="${parcours.entreprise.nomEntreprise }" value="${saisies['nomEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['nomEntreprise'] }" /></p></td>
				<td><p>Branche : <input type="text" name="brancheEntreprise" id="brancheEntreprise" value="<c:out default="${parcours.entreprise.branche }" value="${saisies['brancheEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['brancheEntreprise'] }" /></p></td>
			</tr>
			<tr>
				<td><p>Nombre de salariés : <input type="text" name="nbSalariesEntreprise" id="nbSalariesEntreprise" value="<c:out default="${parcours.entreprise.nbSalaries }" value="${saisies['nbSalariesEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['nbSalariesEntreprise'] }" /></p></td>
				<td><p>Rue : <input type="text" name="rueEntreprise" id="rueEntreprise" value="<c:out default="${parcours.entreprise.coordonnees.rue }" value="${saisies['rueEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['rueEntreprise'] }" /></p></td>
			</tr>
			<tr>
				<td><p>Ville : <input type="text" name="villeEntreprise" id="villeEntreprise" value="<c:out default="${parcours.entreprise.coordonnees.ville }" value="${saisies['villeEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['villeEntreprise'] }" /></p></td>
				<td><p>Code Postal : <input type="text" name="codePostalEntreprise" id="codePostalEntreprise" value="<c:out default="${parcours.entreprise.coordonnees.codePostal }" value="${saisies['codePostalEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['codePostalEntreprise'] }" /></p></td>
			</tr>
			<tr>
				<td><p>Telephone : <input type="text" name="telephoneEntreprise" id="telephoneEntreprise" value="<c:out default="${parcours.entreprise.coordonnees.telephone }" value="${saisies['telephoneEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['telephoneEntreprise'] }" /></p></td>
				<td><p>Mobile : <input type="text" name="mobileEntreprise" id="mobileEntreprise" value="<c:out default="${parcours.entreprise.coordonnees.mobile }" value="${saisies['mobileEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['mobileEntreprise'] }" /></p></td>
			</tr>
			<tr>
				<td><p>Email : <input type="text" name="emailEntreprise" id="emailEntreprise" value="<c:out default="${parcours.entreprise.coordonnees.email }" value="${saisies['emailEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['emailEntreprise'] }" /></p></td>
				<td><p>Site : <input type="text" name="siteEntreprise" id="siteEntreprise" value="<c:out default="${parcours.entreprise.coordonnees.site }" value="${saisies['siteEntreprise'] }" />" /></p>			
				<p class="error"><c:out value="${erreurs['siteEntreprise'] }" /></p></td>
			</tr>
			<input type="hidden" name="identreprise" id="identreprise" value="<c:out default="${parcours.entreprise.identreprise }" value="${saisies['identreprise'] }" />" />
			
			<p class="error"><c:out value="${erreurs['errAjout'] }" /></p>
			<p class="error"><c:out value="${erreurs['errDelParcours'] }" /></p>
			<input type="hidden" name="select_parcours" id="select_parcours" value="<c:out default="${parcours.idparcoursPostBts }" value="${saisies['idParcours'] }" />" />
			<input type="hidden" name="action" id="action" value="modifier" />
			<tr><td><input type="submit" value="Mettre à jour" /></td>
		</form>
		<br/>
		<form method="POST" class="form" action="modifParcours" >
		<input type="hidden" name="select_parcours" id="select_parcours" value="<c:out default="${parcours.idparcoursPostBts }" value="${saisies['idParcours'] }" />" />
		<input type="hidden" name="action" id="action" value="supprimer" />
		<td><span class="btn_error"><input type="submit" value="Supprimer" /></span></td></tr>
		</form>
		</table>
	</div>
</body>
</html>