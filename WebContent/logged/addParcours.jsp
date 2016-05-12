<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter une étape à votre parcours !</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js" ></script>
<script type="text/javascript" src="js/jquery.js" ></script>
<script type="text/javascript" src="js/datepicker-fr.js" ></script>
</head>
<body>
<c:import url="navigation.jsp"></c:import>
<c:import url="headerb.jsp"></c:import>
	<div class="content2">
	<a href="<c:url value="/parcours" />">Retour aux parcours</a>			
		<form class="form" method="post" action="addParcours">
		<table>
		<tr><td><h3>Ajouter une étape</h3></td></tr>
		<tr>	
			<td><p>Fonction : 
			<select name="fonction" id="fonction">
				<c:forEach  var="fonct" items="${fonctions }">
					<option value="${fonct.string }"><c:out value="${fonct.string }"/></option>
				</c:forEach>
			</select>
			</p>
			<p class="error"><c:out value="${erreurs['fonction'] }" /></p></td>
			<td><p>Date de début : <input type="text" name="dateDebut" id="dateDebut" value="<c:out value="${saisies['dateDebut'] }" />" /></p>
			<p class="error"><c:out value="${erreurs['dateDebut'] }" /></p></td>
		</tr>
			<tr><td><h3>Informations entreprise</h3></td></tr>
			<tr>
				<td><p>Nom entreprise : <input type="text" name="nomEntreprise" id="nomEntreprise" value="<c:out default="" value="${saisies['nomEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['nomEntreprise'] }" /></p></td>
				<td><p>Branche : <input type="text" name="brancheEntreprise" id="brancheEntreprise" value="<c:out default="" value="${saisies['brancheEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['brancheEntreprise'] }" /></p></td>
			</tr>
			<tr>
				<td><p>Nombre de salariés : <input type="text" name="nbSalariesEntreprise" id="nbSalariesEntreprise" value="<c:out default="" value="${saisies['nbSalariesEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['nbSalariesEntreprise'] }" /></p></td>
				<td><p>Rue : <input type="text" name="rueEntreprise" id="rueEntreprise" value="<c:out default="" value="${saisies['rueEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['rueEntreprise'] }" /></p></td>
			</tr>
			<tr>
				<td><p>Ville : <input type="text" name="villeEntreprise" id="villeEntreprise" value="<c:out default="" value="${saisies['villeEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['villeEntreprise'] }" /></p></td>
				<td><p>Code Postal : <input type="text" name="codePostalEntreprise" id="codePostalEntreprise" value="<c:out default="" value="${saisies['codePostalEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['codePostalEntreprise'] }" /></p></td>
			</tr>
			<tr>
				<td><p>Telephone : <input type="text" name="telephoneEntreprise" id="telephoneEntreprise" value="<c:out default="" value="${saisies['telephoneEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['telephoneEntreprise'] }" /></p></td>
				<td><p>Mobile : <input type="text" name="mobileEntreprise" id="mobileEntreprise" value="<c:out default="" value="${saisies['mobileEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['mobileEntreprise'] }" /></p></td>
			</tr>
			<tr>
				<td><p>Email : <input type="text" name="emailEntreprise" id="emailEntreprise" value="<c:out default="" value="${saisies['emailEntreprise'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['emailEntreprise'] }" /></p></td>
				<td><p>Site : <input type="text" name="siteEntreprise" id="siteEntreprise" value="<c:out default="" value="${saisies['siteEntreprise'] }" />" /></p>			
				<p class="error"><c:out value="${erreurs['siteEntreprise'] }" /></p></td>
			</tr>
			
			</table>
			<input type="hidden" name="identreprise" id="identreprise" value="<c:out default="0" value="${saisies['identreprise'] }" />" />
			<p class="error"><c:out value="${erreurs['errAjout'] }" /></p>
			<input type="submit" value="Ajouter" />
		</form>
	</div>
</body>
</html>