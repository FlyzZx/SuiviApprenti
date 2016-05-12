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
<script type="text/javascript">
//paste this code under the head tag or in a separate js file.
	// Wait for window load
	$(window).load(function() {
		// Animate loader off screen
		$(".se-pre-con").fadeOut("slow");;
	});
</script>
<title>Profil Personnel</title>
</head>
<body>
<c:import url="navigation.jsp"></c:import>
<c:import url="headerb.jsp"></c:import>
<div class="content2">
			<div class="col-mid1">
				<h3>Profil personnel</h3>
				<p><b>Nom :</b>    <c:out value="${sessionScope['logged'].nom }" /></p>
				<p><b>Prenom :</b> <c:out value="${sessionScope['logged'].prenom }" /></p>
				<p><b>Classe :</b> <c:out value="${sessionScope['logged'].classe.nom }" />
					 <c:out value="${sessionScope['logged'].classe.annee }" /></p>
				<p><b>Né(e) le :</b> 	<f:formatDate value="${sessionScope['logged'].dateNaissance }" pattern="dd/MM/yyyy" /> à <c:out value="${sessionScope['logged'].lieuNaissance }" /></p>
				<p><b>Adresse :</b> 	<c:out value="${sessionScope['logged'].coordonnees.rue }" />, 
								<c:out value="${sessionScope['logged'].coordonnees.codePostal }" /> <c:out value="${sessionScope['logged'].coordonnees.ville }" />
				<p><b>Adresse e-mail :</b>  <c:out value="${sessionScope['logged'].coordonnees.email }" /></p>
				<p><b>Téléphone :</b> <c:out value="${sessionScope['logged'].coordonnees.telephone }" /></p>
				<p><b>Mobile :</b> 	<c:out value="${sessionScope['logged'].coordonnees.mobile }" /></p>
				<p><b>Site Web :</b>	<c:out value="${sessionScope['logged'].coordonnees.site }" /></p>
				<p><b>Mission principale :</b> <c:out value="${sessionScope['logged'].missionPrincipale}" /></p>
				
			</div>
			
			<div class="col-mid2">
				<h3>Informations entreprise</h3>
				<p><b>Nom de l'entreprise :</b> <c:out value="${sessionScope['logged'].entreprise.nomEntreprise }" /></p>
				<p><b>Branche :</b>			<c:out value="${sessionScope['logged'].entreprise.branche }" /></p>
				<p><b>Nombre de salariés :</b> <c:out value="${sessionScope['logged'].entreprise.nbSalaries }" /></p>
			</div>
			
			<div>
				<a class="btn" href='<c:url value="/updateProfile" />'>Mettre à jour</a>
			</div>
</div>
</body>
</html>