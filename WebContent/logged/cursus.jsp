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
<title>Profil Personnel</title>
</head>
<body>
<c:import url="navigation.jsp"></c:import>
<c:import url="headerb.jsp"></c:import>
	<div class="content2">
		<h3>Cursus scolaire</h3>
		<div class="col-full">	
			<table>
				<thead>
					<td><p><b>Diplôme</b></p></td>
					<td><p><b>Spécialisation</b></p></td>
					<td><p><b>Année</b></p></td>
					<td><p><b>Obtention</b></p></td>
				</thead>
				<c:forEach items="${sessionScope['logged'].cursusformations }" var="cursus">
					<tr>
						<td><c:out value="${cursus.type}" /></td>
						<td><c:out value="${cursus.titreComplement}" /></td>
						<td><c:out value="${cursus.annee}" /></td>
						<td><c:out value="${cursus.titreObtenu}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>