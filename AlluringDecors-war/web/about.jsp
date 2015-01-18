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
    </head>
    <body>
        <jsp:include page="/WEB-INF/jspf/defaultnavigation.jspf"/>

        <div class="container" id="about">
            <div class="row">
                <div class="col-md-6 col-md-offset-3 topMargin">
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
                    <p>
                        Some bla bla bla about the company. Some bla bla bla about the company.
                        Some bla bla bla about the company. Some bla bla bla about the company.
                        Some bla bla bla about the company. Some bla bla bla about the company.
                    </p>
                </div>
            </div>
        </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>
