<%-- 
    Document   : index
    Created on : Jan 11, 2015, 5:25:42 PM
    Author     : zuzanahruskova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

        <!-- Google library font -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Font awesome for icons -->
        <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="styles/website-style.css" />

        <title>Alluring Decors</title>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%
            if (session.getAttribute("userRole") != null) {
                if (session.getAttribute("userRole").equals("client")) {
        %>
        <jsp:include page="/WEB-INF/jspf/clientnavigation.jspf"/>
        <%
            } else if (session.getAttribute("userRole").equals("admin")) {
        %>
        <jsp:include page="/WEB-INF/jspf/adminnavigation.jspf"/>
        <%
            }
            } else {
        %>
        <jsp:include page="/WEB-INF/jspf/defaultnavigation.jspf"/>
        <%
            }
        %>

        <div class="container" id="about">
            <div class="row">
                <div class="col-md-10 col-md-offset-1 info">
                    <p>
                        Some text about Alluring Decors. Some text about Alluring Decors.
                        Some text about Alluring Decors. Some text about Alluring Decors.
                        Some text about Alluring Decors. Some text about Alluring Decors.
                        Some text about Alluring Decors. Some text about Alluring Decors.
                    </p>
                </div>
            </div>
            <div class="row">

                <div id="carousel-advert" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-advert" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-advert" data-slide-to="1"></li>
                        <li data-target="#carousel-advert" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="images/carousel/home.jpg" alt="home design" class="img-responsive center-block">
                            <div class="carousel-caption">
                                <h3 class="">Need to Decorate Your Home?</h3>
                            </div>
                        </div>
                        <div class="item">
                            <img src="images/carousel/office.jpg" alt="office design" class="img-responsive center-block">
                            <div class="carousel-caption">
                                <h3>Need to Decorate Your Office?</h3>
                            </div>
                        </div>
                        <div class="item">
                            <img src="images/carousel/restaurant.jpg" alt="restaurant desing" class="img-responsive center-block">
                            <div class="carousel-caption">
                                <h3>Need to Decorate Your Restaurant or Hall?</h3>
                            </div>
                        </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                </div> <!-- Carousel -->
                <%
                    if (session.getAttribute("userRole") == null) {
                %>
                <h2><a href="registration.jsp">Register Now</a></h2>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>
