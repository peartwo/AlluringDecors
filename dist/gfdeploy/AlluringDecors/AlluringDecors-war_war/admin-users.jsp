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
            $(document).on("click", "#goToUsers", function (e) {
                e.preventDefault();
                $.ajax({
                    url: "DisplayUsers",
                    success: function (result) {
                        $("#displayUsersSection").html(result);
                        $("#userRegistrationSection").css("display", "none");
                        $("#displayUsersSection").css("display", "block");
                    },
                    error: function () {
                        alert("Error loading user information.");
                    }
                });
            });
            $(document).on("click", ".goBack", function () {
                $("#displayUsersSection").css("display", "none");
                $("#userRegistrationSection").css("display", "block");
            });

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
                $("#adminRegistrationResult").css("display", "none");
                $("#clientRegistrationResult").css("display", "block");
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
                $("#clientRegistrationResult").css("display", "none");
                $("#adminRegistrationResult").css("display", "block");
            });

            // create form for user when edit button clicked
            $(document).on("click", ".edit", function () {
                var id = $(this).attr("id");
                var role = $("#role" + id).text();
                $("#user" + id).removeClass("col-md-4");
                $("#user" + id).addClass("col-md-8");
                $("#email" + id).html("<div class='col-md-6 form-group has-warning'><input type=\"text\" class='form-control' value='" + $("#email" + id).text() + "'></div>");
                $("#role" + id).html("<div class='col-md-6 form-group has-warning'><input type=\"text\" class='form-control' value='" + role + "' disabled></div>");
                $("#fname" + id).html("<div class='col-md-6 form-group has-warning'><input type=\"text\" class='form-control' value='" + $("#fname" + id).text() + "'></div>");
                $("#lname" + id).html("<div class='col-md-6 form-group has-warning'><input type=\"text\" class='form-control' value='" + $("#lname" + id).text() + "'></div>");
                // Check if user is client to apply additional fields
                if (role === "client") {
                    $("#label1-" + id).html("<div class='col-md-2 form-group has-warning'>" + $("#label1-" + id).text() + "</div>");
                    $("#address" + id).html("<div class='col-md-7 form-group has-warning'><input type=\"text\" class='form-control' value='" + $("#address" + id).text() + "'></div>");
                    $("#nr" + id).html("<div class='col-md-3 form-group has-warning'><input type=\"text\" class='form-control' value='" + $("#nr" + id).text() + "'></div>");
                    $("#city" + id).html("<div class='col-md-7 col-md-offset-2 form-group has-warning'><input type=\"text\" class='form-control' value='" + $("#city" + id).text() + "'></div>");
                    $("#zip" + id).html("<div class='col-md-3 form-group has-warning'><input type=\"text\" class='form-control' value='" + $("#zip" + id).text() + "'></div>");
                    $("#label2-" + id).html("<div class='col-md-2 form-group has-warning'>" + $("#label2-" + id).text() + "</div>");
                    $("#phone" + id).html("<div class='col-md-6 form-group has-warning'><input type=\"text\" class='form-control' value='" + $("#phone" + id).text() + "'></div>");
                }
                $(this).text("Apply Change");
                $(this).removeClass('edit');
                $(this).addClass('apply');
            });
            // update user via Ajax when Apply change button clicked
            $(document).on("click", ".apply", function () {
                var id = $(this).attr("id");
                var email = $("#email" + id + " input").val();
                var role = $("#role" + id + " input").val();
                var fname = $("#fname" + id + " input").val();
                var lname = $("#lname" + id + " input").val();
                // Check if user is client to add additional data
                if (role === "client") {
                    var street = $("#address" + id + " input").val();
                    var nr = $("#nr" + id + " input").val();
                    var city = $("#city" + id + " input").val();
                    var zip = $("#zip" + id + " input").val();
                    var phone = $("#phone" + id + " input").val();
                }
                //alert("User id " + id + ", Email: " + email + ", Role: " + role + ", Name: " + fname + " " + lname + ", Address: " + street + " " + nr + ", " + city + " - " + zip + ", Phone: " + phone);
                $.ajax({
                    url: "ManageUsers",
                    data: {
                        "id": id,
                        "email": email,
                        "role": role,
                        "fname": fname,
                        "lname": lname,
                        "action": "update",
                        "street": street,
                        "nr": nr,
                        "city": city,
                        "zip": zip,
                        "phone": phone
                    },
                    success: function (result) {
                        $("#user" + id).removeClass("col-md-8");
                        $("#user" + id).addClass("col-md-4");
                        $("#user" + id).html(result);
                    },
                    error: function () {
                        $("#user" + id).removeClass("col-md-8");
                        $("#user" + id).addClass("col-md-4");
                        $("#user" + id).html("<h3>Error updating the user.<h3>");
                    }
                });
            });
            // delete user via Ajax when Delete button clicked
            $(document).on("click", ".delete", function () {
                var id = $(this).attr("id");
                var role = $("#role" + id).text();
                if (confirm("Are you sure you want to delete?")) {
                    $.ajax({
                        url: "ManageUsers",
                        data: {
                            "id": id,
                            "role": role,
                            "action": "delete"
                        },
                        success: function (result) {
                            $("#user" + id).html(result);
                        },
                        error: function () {
                            $("#user" + id).html("<h3>Error deleting the User.<h3>");
                        }
                    });
                }
                return false;
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
                    <button id="goToUsers" class="btn btn-warning">
                        <span class="fa fa-arrow-circle-right bigicon">View/Edit Existing Users</span>
                    </button>
                </div>
            </div>
        </div>
        <div class="container" id="displayUsersSection">
            <!-- Calling servlet DisplayUsers via ajax -->
        </div>
    </body>
</html>

