<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="navigation">
	<div id="logo"><img src="images/logoJRB.png" alt="Lycée Jean Rostand" /></div>
	<div id="menu">
		<ul>
			<li><a href='<c:url value="/profile" />'>Mon profil</a></li>
		    <li><a href='<c:url value="/parcours" />'>Mes parcours</a></li>
		    <!-- <li><a href='#'>Mon cursus</a></li> -->
		    <!-- <li><a href='#'>Mes absences</a></li> -->
			<li><a class="btnError" href='<c:url value="/logoff" />'>Déconnexion</a></li>
		</ul>
	</div>
</div>