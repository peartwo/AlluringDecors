<%-- 
    Document   : client-newserviceorder
    Created on : Jan 23, 2015, 6:40:02 PM
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
        <div class="container" id="register">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <legend class="text-center header">Request for New Service</legend>
                    <jsp:include page="/CreateServiceOrder" flush="true"></jsp:include>
                </div>
            </div>
        </div>
    </body>
</html>
