<%-- 
    Document   : about
    Created on : Jan 13, 2015, 12:30:19 AM
    Author     : zuzanahruskova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <div class="col-md-6 col-md-offset-3">
                    <legend class="text-center header">Meet Our Team</legend>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <h3 class="light">Paul - The Boss</h3>
                    <center><img src="images/team/photo4.jpg" alt="Boss Photo" class="img-circle" /></center>
                </div>
                <div class="col-md-3">
                    <h3 class="light">Tony - Designer</h3>
                    <center><img src="images/team/photo1.jpg" alt="Designer Photo" class="img-circle" /></center>
                </div>
                <div class="col-md-3">
                    <h3 class="light">Marry - Designer</h3>
                    <center><img src="images/team/photo3.jpg" alt="Designer Photo" class="img-circle" /></center>
                </div>
                <div class="col-md-3">
                    <h3 class="light">Peter - Designer</h3>
                    <center><img src="images/team/photo2.jpg" alt="Designer Photo" class="img-circle" /></center>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-md-offset-3 topMargin">
                    <p class="light">
                        We are a young team of people who believe that one should feel well in 
                        every environment, especially at home and at work. Therefore, we made interior 
                        and exterior design our living. We are on a mission to make your home / office 
                        / restaurant / hall look great in every perspective offering a range 
                        of interior and exterior design services.
                    </p>
                    <p class="light">
                        If you would like to have a taste of our work please take a look at some of our 
                        <a class="text-danger" href="projects.jsp">PROJECTS </a>.
                    </p>
                    <%
                        if (session.getAttribute("userRole") == null) {
                    %>
                    <p class="light">Interested? <a class="text-danger" href="registration.jsp">REGISTER</a> with us
                        and let us know what <a class="text-danger" href="services.jsp">SERVICE OFFERS</a> you are interested in.</p>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
