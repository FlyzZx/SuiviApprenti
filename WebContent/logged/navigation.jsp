<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="navigation">
	<div id="logo"><img src="images/logoJRB.png" alt="Lycée Jean Rostand" /></div>
	<div id="menu">
		<ul>
			<li><a href='<c:url value="/profile" />'>Profil</a></li>
		    <li><a href='<c:url value="/parcours" />'>Parcours en entreprise</a></li>
		    <li><a href='<c:url value="/cursus" />'>Cursus scolaire</a></li>
			<li><a class="btnError" href='<c:url value="/logoff" />'>Déconnexion</a></li>
		</ul>
	</div>
</div>