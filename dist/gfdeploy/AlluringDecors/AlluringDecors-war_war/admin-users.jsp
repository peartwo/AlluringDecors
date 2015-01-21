<%-- 
    Document   : admin-users
    Created on : Jan 18, 2015, 9:17:21 PM
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
        
        <script>
            jQuery.fn.reset = function () {
                $(this).each(function () {
                    this.reset();
                });
            };
            // Register New Client when Register Client button is clicked
            $(document).on("click", "#registerClient", function (e) {
                e.preventDefault();
                var form = $("#clientRegistrationForm");
                //alert(form.serialize());
                $.ajax({
                    type: "POST",
                    url: "RegisterNewUser",
                    data: form.serialize(),
                    success: function (result) {
                        $("#clientRegistrationResult").html(result);
                    },
                    error: function () {
                        $("#clientRegistrationResult").html("<h3>Error registering new user.<h3>");
                    }
                });
                form.reset();
                $("#adminRegistrationResult").css("display","none");
                $("#clientRegistrationResult").css("display","block");
            }); 
            // Register New Admin when Register Admin button is clicked
            $(document).on("click", "#registerAdmin", function (e) {
                e.preventDefault();
                var form = $("#adminRegistrationForm");
                //alert(form.serialize());
                $.ajax({
                    type: form.attr("method"),
                    url: "RegisterNewUser",
                    data: form.serialize(),
                    success: function (result) {
                        $("#adminRegistrationResult").html(result);
                    },
                    error: function () {
                        $("#adminRegistrationResult").html("<h3>Error registering new admin.<h3>");
                    }
                });
                form.reset();
                $("#clientRegistrationResult").css("display","none");
                $("#adminRegistrationResult").css("display","block");
            });
            
        </script>
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

        <div class="container" id="userRegistrationSection">
            <div class="row">
                <div class="col-md-6">
                    <form id="clientRegistrationForm" class="form-horizontal" action="" method="post">
                        <fieldset>
                            <legend class="text-center header">Client Registration</legend>
                            <input type="hidden" name="userType" value="client">
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-at bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input name="email" type="text" placeholder="Email Address" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-lock bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input name="password" type="password" placeholder="Choose Password" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-2">
                                    <input name="passwordr" type="password" placeholder="Repeat Password" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-user bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input name="fname" type="text" placeholder="First Name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                
                                <div class="col-md-8 col-md-offset-2">
                                    <input name="lname" type="text" placeholder="Last Name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-envelope-o bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input name="street" type="text" placeholder="Street" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-2">
                                    <input name="number" type="text" placeholder="Nr" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-2">
                                    <input name="city" type="text" placeholder="City" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-2">
                                    <input name="zip" type="text" placeholder="ZIP" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-phone-square bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input name="phone" type="text" placeholder="Phone" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-2">
                                    <button id="registerClient" type="submit" class="btn btn-danger btn-lg">Register Client</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                    <div id="clientRegistrationResult" class="row responseText"></div>
                </div>
                <div class="col-md-6">
                    <form id="adminRegistrationForm" class="form-horizontal" action="" method="post">
                        <fieldset>
                            <legend class="text-center header">Admin Registration</legend>
                            <input type="hidden" name="userType" value="admin">
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-at bigicon"></i>
                                </span>
                                <div class="col-md-9">
                                    <input name="email" type="text" placeholder="Email Address" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-lock bigicon"></i>
                                </span>
                                <div class="col-md-8">
                                    <input name="password" type="password" placeholder="Choose Password" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-9 col-md-offset-2">
                                    <input name="passwordr" type="password" placeholder="Repeat Password" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-right">
                                    <i class="fa fa-user bigicon"></i>
                                </span>
                                <div class="col-md-9">
                                    <input name="fname" type="text" placeholder="First Name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                
                                <div class="col-md-9 col-md-offset-2">
                                    <input name="lname" type="text" placeholder="Last Name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-9 col-md-offset-2">
                                    <button id="registerAdmin" type="submit" class="btn btn-danger btn-lg">Register Admin</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                    <div id="adminRegistrationResult" class="row responseText"></div>
                </div>
                <div class="col-md-6">
                    <br/><br/>
                    <a href=""><legend class="text-center header">View/Edit Existing Users</legend></a>
                </div>
                <div class="col-md-6">
                    <br/>
                    <a href=""><legend class="text-center header">View/Edit Existing Clients</legend></a>
                </div>
            </div>
        </div>
    </body>
</html>

