<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <!-- La lib FMT permet le formatage des dates sans utiliser de code JAVA -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<script type="text/javascript" src="js/jquery-1.12.1.min.js" ></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
<title>Aide d'utilisation</title>
</head>
<body>
<c:import url="navigation.jsp"></c:import>
<c:import url="headerb.jsp"></c:import>
<div class="content2">
	<h3>Aide à l'utilisation de l'application</h3>
	<h4>Le profil personnel</h4>
	<p>Le profil personnel regroupe toutes les informations vous concernant. Vous pouvez modifier certaines de ces informations.</p>
	<br>
	<h4>Les parcours en entreprise</h4>
	<p>Les parcours en entreprise représentent soit une formation en alternance, sois un emploi après le BTS.</p>
	<br>
	<h4>Le cursus scolaire</h4>
	<p>Le cursus scolaire représente le parcours scolaire, ainsi que les parcours post BTS en filière théorique.</p>
</div>
</body>
</html>