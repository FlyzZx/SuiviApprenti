<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter une étape à votre cursus</title>
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
		<form class="form" method="post" action="addCursus">
		<table>
		<tr><td><h3>Ajouter une étape</h3></td></tr>
			<tr>
				<td><p>Diplôme : <input type="text" name="diplome" id="diplome" value="<c:out default="" value="${saisies['diplome'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['diplome'] }" /></p></td>
				<td><p>Spécialisation : <input type="text" name="specialisation" id="specialisation" value="<c:out default="" value="${saisies['specialisation'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['specialisation'] }" /></p></td>
			</tr>
			<tr>
				<td><p>Date début : <input type="text" name="annee" id="dateDebut" value="<c:out default="" value="${saisies['annee'] }" />" /></p>
				<p class="error"><c:out value="${erreurs['annee'] }" /></p></td>
				<td><p>Obtention : 
				<select name="obtention" id="obtention">
					<option value="Oui">Oui</option>
					<option value="Non" selected>Non</option>
				</select></p>
				<p class="error"><c:out value="${erreurs['obtention'] }" /></p></td>
			</tr>
			</table>
			<p class="error"><c:out value="${erreurs['errAjout'] }" /></p>
			<input type="submit" value="Ajouter" />
		</form>
	</div>
</body>
</html>