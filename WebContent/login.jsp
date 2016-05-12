<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/loading.js"></script>
<title>Bienvenue !</title>
</head>
<body>
 	<!-- Importation du Header -->
	<c:import url="header.jsp"></c:import>
	<!-- Contenu de la page -->
	<div class="content">		
		<form method="post" action="login" class="form form_center" id="connection">
		<p class="error"><c:out value="${erreurs['erreurConnexion'] }" /></p>
			<p>
				<label for="mail">Adresse e-mail : </label>
				<input type="text" id="mail" name="mail" autocomplete="off"/>
				<p class="error"><c:out value="${erreurs['mail'] }" /></p>
			</p>
			
			<p>
				<label for="pass"> Mot de passe : </label>
				<input type="password" id="pass" name="pass" autocomplete="off" />
				<p class="error"><c:out value="${erreurs['pass'] }" /></p>
			</p>
			
			<input id="btn_connect" class="buttonValid" type="submit" value="Se connecter" />
		</form>
	</div>
	<div id="spinner" class="spinner" style="display:none;">
		<p>Chargement...</p>
    	<img id="img-spinner" src="images/loader.gif" alt="Loading"/>
	</div>
</body>
</html>