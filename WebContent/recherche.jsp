<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Chercher des images :</h2><br><hr>
<h4>Ajouter une image depuis votre ordinateur et trouver des similaires:</h4>
<legend>

		<form method="post" action="recherche" enctype="multipart/form-data">
			<fieldset>
				 
				 <input type="file" name="image"  accept="image/x-png, image/gif, image/jpeg" >
				<br>
				<input type="submit" value="Charger l'image">
			</fieldset>
		</form>
	</legend>
</body>
</html>