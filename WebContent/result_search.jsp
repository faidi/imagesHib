<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/sources/bootstrap.css " />
<title>Insert title here</title>
</head>
<body>
<h4>Que voulez vous faire maintenant?</h4>
<a href="upload_image.jsp "> Ajouter une autre image à la base</a><br><hr>
<a href="index.jsp">Acceuil</a>

<h3>Résultat trouvé par l'algorithme de calcule de la distance euclidienne</h3>
	<c:forEach items="${images}" var="image">
		<div class=" btn   news">
			<div  class="titreNews">
				<c:out value="${image['name']}" />
			</div>
			<div class="corpsNews">
			<img src="data:image/jpeg;base64,${image['image']}" width="200" height="200"> 	 
			</div>
			 
		</div>
		</c:forEach>
		<h3>Résultat trouver l'algorithme d'intersection</h3> 
		<c:forEach items="${images2}" var="image">
		<div class="news">
			<div class="titreNews">
				<c:out value="${image['name']}" />
			</div>
			<div class="corpsNews">
			<img src="data:image/jpeg;base64,${image['image']}" width="200" height="200"> 	 
			</div>
			 
		</div>
		</c:forEach>
</body>
</html>