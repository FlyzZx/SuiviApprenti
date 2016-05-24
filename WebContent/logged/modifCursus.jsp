<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modification du cursus</title>
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
	<a href="<c:url value="/cursus" />">Retour aux cursus</a>			
		<form class="form" method="POST" action="modifCursus">
		<table>
		<tr><td><h3>Modification du parcours</h3></td></tr>
			<tr>
				<td><p>Diplôme : <input type="text" name="diplome" id="diplome" value="<c:out default="${cursus.type }" value="${saisies['diplome'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['diplome'] }" /></p></td>
				<td><p>Spécialisation : <input type="text" name="specialisation" id="specialisation" value="<c:out default="${cursus.titreComplement }" value="${saisies['specialisation'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['specialisation'] }" /></p></td>
			</tr>
			<tr>
				<td><p>Date début : <input type="text" name="annee" id="dateDebut" value="<c:out default="${cursus.annee }" value="${saisies['annee'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['annee'] }" /></p></td>
				<td><p>Obtention : 
				<select name="obtention" id="obtention">
				<c:choose>
					<c:when test="${cursus.titreObtenu == 'Non' }">
						<option value="Oui">Oui</option>
						<option value="Non" selected>Non</option>
					</c:when>
					<c:when test="${cursus.titreObtenu == 'Oui' }">
						<option value="Oui"  selected>Oui</option>
						<option value="Non">Non</option>
					</c:when>
				</c:choose>
				</select></p>
				<p class="error"><c:out value="${erreurs['obtention'] }" /></p></td>
			</tr>
			
			<p class="error"><c:out value="${erreurs['errAjout'] }" /></p>
			<input type="hidden" name="action" id="action" value="modifier" />
			<input type="hidden" name="select_cursus" id="select_cursus" value="<c:out default="${cursus.idcursusformation }" value="${saisies['idCursus'] }" />" />
			<tr><td><input type="submit" value="Mettre à jour" /></td>
		</form>
		<form method="POST" class="form" action="modifCursus" >
		<input type="hidden" name="select_cursus" id="select_cursus" value="<c:out default="${cursus.idcursusformation }" value="${saisies['idCursus'] }" />" />
		<input type="hidden" name="action" id="action" value="supprimer" />
		<td><span class="btn_error"><input type="submit" value="Supprimer" /></span></td></tr>
		</form>
		</table>
	</div>
</body>
</html>