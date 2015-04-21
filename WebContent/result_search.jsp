<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>




<title>Résultat de la recherche</title>
<style type="text/css">/*
 
.mag {
	width: 200px;
	margin: 0 auto;
	float: none;
}

.mag img {
	max-width: 100%;
}

.magnify {
	position: relative;
	cursor: none
}

.magnify-large {
	position: absolute;
	display: none;
	width: 175px;
	height: 175px;
	-webkit-box-shadow: 0 0 0 7px rgba(255, 255, 255, 0.85), 0 0 7px 7px
		rgba(0, 0, 0, 0.25), inset 0 0 40px 2px rgba(0, 0, 0, 0.25);
	-moz-box-shadow: 0 0 0 7px rgba(255, 255, 255, 0.85), 0 0 7px 7px
		rgba(0, 0, 0, 0.25), inset 0 0 40px 2px rgba(0, 0, 0, 0.25);
	box-shadow: 0 0 0 7px rgba(255, 255, 255, 0.85), 0 0 7px 7px
		rgba(0, 0, 0, 0.25), inset 0 0 40px 2px rgba(0, 0, 0, 0.25);
	-webkit-border-radius: 100%;
	-moz-border-radius: 100%;
	border-radius: 100%
}
</style>
</head>
<body>
	
	<div class="container">
<h4>Que voulez vous faire maintenant?</h4>
	<a href="upload_image.jsp "> Ajouter une autre image à la base</a>
	<br>
	
	<a href="index.jsp">Acceuil</a>
 <hr>
		<div class="row">
			<h3>Résultat trouvé par l'algorithme de calcule de la distance
				euclidienne</h3>

			<hr>
		</div>


		<div class="row">
 
			<c:forEach items="${images2}" var="image">
				<div class="col-md-4">
					<div class="mag">
						<p><c:out value="${image['name']}" /><br><c:out value="${image['distance']}" /></p>
						<br> <img data-toggle="magnify"
							src="data:image/jpeg;base64,${image['image']}" alt="" height="200" width="200">
					</div><hr>
				</div>
				<!--/span-->

			</c:forEach>
 
		</div>
		<!--/row-->
 
	</div>
	<!-- / .container -->
	<script>
 !function ($) {

	    "use strict"; // jshint ;_;


	    /* MAGNIFY PUBLIC CLASS DEFINITION
	     * =============================== */

	    var Magnify = function (element, options) {
	        this.init('magnify', element, options)
	    }

	    Magnify.prototype = {

	        constructor: Magnify

	        , init: function (type, element, options) {
	            var event = 'mousemove'
	                , eventOut = 'mouseleave';

	            this.type = type
	            this.$element = $(element)
	            this.options = this.getOptions(options)
	            this.nativeWidth = 0
	            this.nativeHeight = 0

	            this.$element.wrap('<div class="magnify" \>');
	            this.$element.parent('.magnify').append('<div class="magnify-large" \>');
	            this.$element.siblings(".magnify-large").css("background","url('" + this.$element.attr("src") + "') no-repeat");

	            this.$element.parent('.magnify').on(event + '.' + this.type, $.proxy(this.check, this));
	            this.$element.parent('.magnify').on(eventOut + '.' + this.type, $.proxy(this.check, this));
	        }

	        , getOptions: function (options) {
	            options = $.extend({}, $.fn[this.type].defaults, options, this.$element.data())

	            if (options.delay && typeof options.delay == 'number') {
	                options.delay = {
	                    show: options.delay
	                    , hide: options.delay
	                }
	            }

	            return options
	        }

	        , check: function (e) {
	            var container = $(e.currentTarget);
	            var self = container.children('img');
	            var mag = container.children(".magnify-large");

	            // Get the native dimensions of the image
	            if(!this.nativeWidth && !this.nativeHeight) {
	                var image = new Image();
	                image.src = self.attr("src");

	                this.nativeWidth = image.width;
	                this.nativeHeight = image.height;

	            } else {

	                var magnifyOffset = container.offset();
	                var mx = e.pageX - magnifyOffset.left;
	                var my = e.pageY - magnifyOffset.top;

	                if (mx < container.width() && my < container.height() && mx > 0 && my > 0) {
	                    mag.fadeIn(100);
	                } else {
	                    mag.fadeOut(100);
	                }

	                if(mag.is(":visible"))
	                {
	                    var rx = Math.round(mx/container.width()*this.nativeWidth - mag.width()/2)*-1;
	                    var ry = Math.round(my/container.height()*this.nativeHeight - mag.height()/2)*-1;
	                    var bgp = rx + "px " + ry + "px";

	                    var px = mx - mag.width()/2;
	                    var py = my - mag.height()/2;

	                    mag.css({left: px, top: py, backgroundPosition: bgp});
	                }
	            }

	        }
	    }


	    /* MAGNIFY PLUGIN DEFINITION
	     * ========================= */

	    $.fn.magnify = function ( option ) {
	        return this.each(function () {
	            var $this = $(this)
	                , data = $this.data('magnify')
	                , options = typeof option == 'object' && option
	            if (!data) $this.data('tooltip', (data = new Magnify(this, options)))
	            if (typeof option == 'string') data[option]()
	        })
	    }

	    $.fn.magnify.Constructor = Magnify

	    $.fn.magnify.defaults = {
	        delay: 0
	    }


	    /* MAGNIFY DATA-API
	     * ================ */

	    $(window).on('load', function () {
	        $('[data-toggle="magnify"]').each(function () {
	            var $mag = $(this);
	            $mag.magnify()
	        })
	    })

	} ( window.jQuery );</script>
</body>
</html>