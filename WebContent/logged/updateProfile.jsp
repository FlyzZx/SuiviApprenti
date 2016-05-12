<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mise à jour profil personnel</title>
<link rel="stylesheet" type="text/css" href="css/layout.css" />
</head>
<body>
<c:import url="navigation.jsp"></c:import>
<c:import url="headerb.jsp"></c:import>
	<div class="content2" >
		
		<a href="<c:url value="/profile" />">Retour au profil</a>
		<form method="post" action="updateProfile" class="form">
		<table>
		<tr><td><h3>Coordonnées : </h3></td></tr>
		<tr>
			<td><p>Rue : <input name="rue" id="rue" type="text" value="<c:out default="${sessionScope['logged'].coordonnees.rue }" value="${saisies['rue'] }" />" /></p>
			<p class="error"><c:out value="${erreurs['rue'] }" /></p></td>
			
			<td><p>Ville : <input name="ville" id="ville" type="text" value="<c:out default="${sessionScope['logged'].coordonnees.ville}" value="${saisies['ville'] }"/>" /></p>
				<p class="error"><c:out value="${erreurs['ville'] }" /></p></td>
		</tr>
		<tr>
			<td><p>Code Postal : <input name="codePostal" id="codePostal" type="text" value="<c:out default="${sessionScope['logged'].coordonnees.codePostal}" value="${saisies['codePostal'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['codePostal'] }" /></p></td>			
			<td><p>Adresse e-mail : <input name="email" id="email" type="text" value="<c:out default="${sessionScope['logged'].coordonnees.email}" value="${saisies['email'] }" />" /></p>
			<p class="error"><c:out value="${erreurs['email'] }" /></p></td>
		</tr>
		<tr>			
			<td><p>Téléphone : <input name="telephone" id="telephone" type="text" value="<c:out default="${sessionScope['logged'].coordonnees.telephone}" value="${saisies['telephone'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['telephone']}"/></p></td>
				<td><p>Mobile : <input name="mobile" id="mobile" type="text" value="<c:out default="${sessionScope['logged'].coordonnees.mobile}" value="${saisies['mobile'] }"/>" /></p></td>
		</tr>
		<tr>	
			<td><p>Site web : <input name="site" id="site" type="text" value="<c:out default="${sessionScope['logged'].coordonnees.site}" value="${saisies['site'] }" />" />
			<p class="error"><c:out value="${erreurs['site'] }" /></p></td>
			
			<td><p>Mission principale : 
			<select name="missionPrincipale" id="missionPrincipale">
				<c:forEach  var="mission" items="${missionPrincipale }">
				<c:choose>
					<c:when test="${mission.string == sessionScope['logged'].missionPrincipale }">
						<option value="${mission.string }" selected><c:out value="${mission.string }"/></option>
					</c:when>
					<c:otherwise>
						<option value="${mission.string }"><c:out value="${mission.string }"/></option>
					</c:otherwise>
				</c:choose>
					
				</c:forEach>
			</select></p>
			<p class="error"><c:out value="${erreurs['missionPrincipale'] }" /></p></td>
		</tr>
		<tr>	
			<td><p>Mot de passe : <input type="password" name="password" id="password" /></p></td>
			<td><p>Confirmez le Mot de passe : <input type="password" name="passwordVerif" id="passwordVerif" /></p>
			<p class="error"><c:out value="${erreurs['password'] }" /></p></td>
		</tr>
		</table>	
			<input type="submit" value="Enregistrer les modifications" />

		</form>
	</div>
</div>	
</body>
</html>