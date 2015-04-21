<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chercher des images</title>
</head>
<body>

<div class="container">

<div><h2>Chercher des images :</h2><br><hr>
<h4>Ajouter une image depuis votre ordinateur et trouver des similaires:</h4></div>
<div class="panel-body">
		<legend>
		
		<form method="post" action="recherche" enctype="multipart/form-data">
			<fieldset >
				 
				 <input type="file" name="image"  accept="image/x-png, image/gif, image/jpeg" >
				<br>
				<input type="submit" value="Charger l'image">
			</fieldset>
		</form>
	</legend>
	</div></div>
</body>
</html>