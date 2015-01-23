<%-- 
    Document   : services
    Created on : Jan 13, 2015, 12:30:59 AM
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

        <script type="text/javascript">
            jQuery.fn.reset = function () {
                $(this).each(function () {
                    this.reset();
                });
            };
            // Display services under domain when domainbox clicked
            $(document).on('click', ".domainbox", function (event) {
                event.preventDefault();
                var id = $(this).attr("id");
                $.ajax({
                    url: "DisplayServices",
                    data: {"id": id},
                    success: function (result) {
                        $("#domains").html(result);
                    },
                    error: function () {
                        alert("Error loading the page.")
                    }
                });
            });
            // Display form to add new domain when Add New Domain button clicked
            $(document).on('click', "#addDomain", function () {
                $("#newDomain").html("<form id='newDomainForm' class='form-horizontal' action='AddNewDomain' method='post' enctype='multipart/form-data'>\n\
                    <fieldset>\n\
                        <h3>New Service Domain</h3>\n\
                        <div class='form-group'>\n\
                            <div class='col-md-12'>\n\
                                New Service Domain Name<input name='name' type='text' placeholder='Name Here' class='form-control'>\n\
                            </div>\n\
                        </div>\n\
                        <div class='form-group'>\n\
                            <div class='col-md-12'>\n\
                                New Service Domain Title Image<input type='file' name='file' id='file' class='form-control'>\n\
                                <br><p>Image must be in .jpg format and have dimensions 300px x 300px</p>\n\
                            </div>\n\
                        </div>\n\
                        <div class='form-group'>\n\
                            <div class='col-md-12'>\n\
                                <button type='submit' class='btn btn-warning btn-lg'>Add Domain</button>\n\
                                <button class='btn btn-danger btn-lg cancel'>Cancel</button>\n\
                            </div>\n\
                        </div>\n\
                    </fieldset>\n\
                </form>");
            });
            // Hide form to add new domain when Cancel clicked
            $(document).on("click", ".cancel", function (e) {
                e.preventDefault();
                $("#newDomain").html("<button id=\"addDomain\" class=\"btn btn-large btn-success\"><span class=\"fa fa-plus-square\">Add New Domain</span></button>");
            });
            // Add new domain when Add Domain clicked
            $(document).on("submit", "#newDomainForm", function (e) {
                var formObj = $(this);
                var formURL = formObj.attr("action");
                var formData = new FormData(this);
                $.ajax({
                    type: "POST",
                    url: formURL,
                    data: formData,
                    mimeType: "multipart/form-data",
                    contentType: false,
                    cache: false,
                    processData: false,
                    success: function (result)
                    {
                        $(result).insertAfter($(".domainbox").last());
                        formObj.reset();
                    },
                    error: function (errorThrown)
                    {
                        alert(errorThrown);
                    }
                });
                e.preventDefault();
                e.unbind();
            });
            // create form for editing domain when Edit button clicked
            $(document).on("click", ".edit", function () {
                var id = $(this).parent().attr("id");
                $(this).text("Apply Change");
                $(this).removeClass("edit");
                $(this).parent().removeClass("domainbox");
                $(this).parent().addClass("domainformbox");
                var html = $(this).parent().html();
                $(this).parent().html("<form id='editDomainForm' action='ManageDomains' method='post' enctype='multipart/form-data'>\n\
                            <input type='hidden' name='action' value='update'><input type='hidden' name='id' value='" + id + "'>" + html + "</form>");
                $("#name" + id).html("<div class='col-md-8 form-group has-warning'><input type='text' name=\"name\" class='form-control' value='" + $("#name" + id).text() + "'></div>");
                $("#" + id + " img").css("width", "45%");
                $("#" + id + " center").append("<input type='file' name='file' id='file' class='form-control'>If need to change title image, please uppload new image here.");
                preventDefault();
            });
            // update domain via Ajax when Apply change button clicked
            $(document).on("submit", "#editDomainForm", function (e) {
                var formObj = $(this);
                var formURL = formObj.attr("action");
                var formData = new FormData(this);
                $.ajax({
                    type: "POST",
                    url: formURL,
                    data: formData,
                    mimeType: "multipart/form-data",
                    contentType: false,
                    cache: false,
                    processData: false,
                    success: function (result)
                    {
                        $(".domainformbox").html(result);
                    },
                    error: function (errorThrown)
                    {
                        alert(errorThrown);
                    }
                });
                e.preventDefault();
                e.unbind();
            });
            // Delete domain via Ajax when Delete button clicked
            $(document).on("click", ".delete", function () {
                var id = $(this).parent().attr("id");
                if (confirm("Are you sure you want to delete this domain and its title image?")) {
                    $.ajax({
                        url: "ManageDomains",
                        data: {
                            "id": id,
                            "action": "delete"
                        },
                        success: function (result) {
                            $("#" + id).html(result);
                        },
                        error: function () {
                            $("#" + id).html("<h3>Error deleting the Domain.<h3>");
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
        <div class="container" id="domains">
            <div class="row">
                <jsp:include page="DisplayDomains" flush="true" />
            </div>
        </div>
    </body>
</html>
