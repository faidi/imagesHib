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
<style>body
{
    background: url('http://farm3.staticflickr.com/2832/12303719364_c25cecdc28_b.jpg') fixed;
    background-size: cover;
    padding: 0;
    margin: 0;
}
.wrap
{
    width: 100%;
    height: 100%;
    min-height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 99;
}
</style>
<style type="text/css">.hero-widget { text-align: center; padding-top: 20px; padding-bottom: 20px; }
.hero-widget .icon { display: block; font-size: 96px; line-height: 96px; margin-bottom: 10px; text-align: center; }
.hero-widget var { display: block; height: 64px; font-size: 64px; line-height: 64px; font-style: normal; }
.hero-widget label { font-size: 17px; }
.hero-widget .options { margin-top: 10px; }</style>
<title>Insert title here</title>
</head>
<body>

 <div class="text">
                     
                    <label class="text-muted"><h1 >Bienvenu dans l'application</h1></label>
                </div>


<hr>
<a  href="" > Ajouter une nouvelle image dans ma base de données</a> <br>
<a href="" >Chercher des images</a> 
  
<div class="container">
	<div class="row">
		<div class="col-sm-3">
    	    <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="glyphicon glyphicon-user"></i>
                </div>
                <div class="text">
                     
                    <label class="text-muted">invited guests</label>
                </div>
                <div class="options">
                    <a href="upload_image.jsp" class="btn btn-primary btn-lg"><i class="glyphicon glyphicon-plus"></i> Ajouter une image  </a>
                </div>
            </div>
		</div>
        <div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="glyphicon glyphicon-star"></i>
                </div>
                <div class="text">
                     
                    <label class="text-muted">page likes</label>
                </div>
                <div class="options">
                    <a href="recherche.jsp" class="btn btn-default btn-lg"><i class="glyphicon glyphicon-search"></i> Chercher une image</a>
                </div>
            </div>
		</div>
        <div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="glyphicon glyphicon-tags"></i>
                </div>
                <div class="text">
                     
                    <label class="text-muted">Ajouter un repertoire</label>
                </div>
                <div class="options">
                    <a href="javascript:;" class="btn btn-default btn-lg"><i class="glyphicon glyphicon-folder"></i> Ajouter un repertoire</a>
                </div>
            </div>
    	</div>
        <div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="glyphicon glyphicon-cog"></i>
                </div>
                <div class="text">
                   
                    <label class="text-muted">Mon espace</label>
                </div>
                <div class="options">
                    <a href="javascript:;" class="btn btn-default btn-lg"><i class="glyphicon glyphicon-wrench"></i> Mon espace</a>
                </div>
            </div>
		</div>
	</div>
</div>
</body>
</html>