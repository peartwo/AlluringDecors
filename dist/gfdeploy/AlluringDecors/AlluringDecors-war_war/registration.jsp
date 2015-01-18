<%-- 
    Document   : registration
    Created on : Jan 13, 2015, 8:03:37 AM
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
        <div class="container" id="register">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <form id="registrationForm" class="form-horizontal" action="RegisterNewUser" method="post">
                        <fieldset>
                            <legend class="text-center header">New Client Registration</legend>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-at bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input id="email" name="email" type="text" placeholder="Email Address" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-lock bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input id="email" name="password" type="password" placeholder="Choose Password" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-2">
                                    <input id="email" name="password" type="password" placeholder="Repeat Password" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-user bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input id="name" name="fname" type="text" placeholder="First Name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                
                                <div class="col-md-8 col-md-offset-2">
                                    <input id="name" name="lname" type="text" placeholder="Last Name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-envelope-o bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input id="phone" name="street" type="text" placeholder="Street" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-2">
                                    <input id="phone" name="number" type="text" placeholder="Nr" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-2">
                                    <input id="phone" name="city" type="text" placeholder="City" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-2">
                                    <input id="phone" name="zip" type="text" placeholder="ZIP" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-phone-square bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input id="phone" name="phone" type="text" placeholder="Phone" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-2">
                                    <button type="submit" class="btn btn-danger btn-lg" id="formbutton">Submit</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>
