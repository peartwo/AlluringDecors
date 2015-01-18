<%-- 
    Document   : contact
    Created on : Jan 13, 2015, 12:32:22 AM
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
        <script type="text/javascript">
            function getFeedbacks() {
                $.ajax({
                    url: "LoadFeedbacks",
                    success: function (result) {
                        $("#feedbacks").html(result);
                    },
                    error: function () {
                        alert("Error loading feedbacks.");
                    }
                });
            }
            
            jQuery.fn.reset = function () {
                $(this).each(function () {
                    this.reset();
                });
            };
            
            $(document).on('click', "#formbutton", function (event) {
                event.preventDefault();
                var form = $("#feedbackForm");
                $.ajax({
                    url: "AddNewFeedback",
                    data: form.serialize(),
                    success: function (result) {
                        $("#feedbackFormResult").html(result);
                    },
                    error: function () {
                        $("#feedbackFormResult").html("<div class=\"col-md-10 col-md-offset-2 responseText\">\n\
                            <h4>ERROR</h4><p>An error occured when submitting the feedback.</p></div>");
                    }
                });
                $("#feedbackForm").reset();
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

        <div class="container" id="contact">
            <div class="row">
                <div class="col-md-4">
                    <legend class="text-center header">Visit Our Office</legend>
                    <p class="address">
                        ALLURING DECORS<br />
                        Drienova 5228/14<br />
                        82101 Bratislava<br />
                        Slovakia
                    </p>
                    <div class="flexibleContainer">
                        <iframe width="360" height="300" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d5322.959751921431!2d17.15027193080388!3d48.15883187971231!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x476c8ecd89c65249%3A0xa772e8d6f62f69e5!2sDrie%C5%88ov%C3%A1+5228%2F14%2C+831+04+Bratislava%2C+Slovakia!5e0!3m2!1sen!2sus!4v1420795662211"></iframe>
                    </div>
                </div>
                <div class="col-md-6 col-md-offset-1">
                    <%
                        if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                    %>
                    <script>getFeedbacks();</script>
                    <legend class="text-center header">New Feedbacks</legend>
                    <div id="feedbacks"></div>
                    <%
                    } else {
                    %>
                    <form id="feedbackForm" class="form-horizontal" action="" method="get">
                        <fieldset>
                            <legend class="text-center header">Contact us / Feedback</legend>
                            <div class="form-group">
                                <span class="col-md-2 text-center">
                                    <i class="fa fa-user bigicon"></i>
                                </span>
                                <div class="col-md-10">
                                    <input name="name" type="text" placeholder="Full Name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-center">
                                    <i class="fa fa-envelope-o bigicon"></i>
                                </span>
                                <div class="col-md-10">
                                    <input name="email" type="text" placeholder="Email Address" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-md-2 text-center">
                                    <i class="fa fa-pencil-square-o bigicon"></i>
                                </span>
                                <div class="col-md-10">
                                    <textarea class="form-control" name="message" placeholder="Enter your massage for us here. " rows="7"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-10 col-md-offset-2">
                                    <button type="submit" class="btn btn-danger btn-lg" id="formbutton">Submit</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                    <div id="feedbackFormResult"></div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
