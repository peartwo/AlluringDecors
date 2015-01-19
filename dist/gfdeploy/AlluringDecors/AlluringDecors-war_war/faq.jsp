<%-- 
    Document   : faq
    Created on : Jan 13, 2015, 12:33:18 AM
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
        <script>
            // create form for FAQ when edit button clicked
            $(document).on("click", ".edit", function () {
                var id = $(this).attr("id");
                $("#q" + id).html("<div class='col-md-12 form-group has-warning'><textarea class='form-control'>" + $("#q" + id).text() + "</textarea></div>");
                $("#a" + id).html("<div class='col-md-12 form-group has-warning'><textarea class='form-control'>" + $("#a" + id).text() + "</textarea></div>");
                $(this).text("Apply Change");
                $(this).removeClass("edit");
                $(this).addClass("apply");
                $("#faqForm" + id + ".delete").removeClass(".delete");
                $("#faqForm" + id + ".delete").addClass(".cancel");
                $("#faqForm" + id + ".cancel").text("Cancel");
            });
            // create new form for FAQ when Add new Question & Answer button clicked
            $(document).on("click", "#addNewFaq .showform", function () {
                $("#addNewFaq").html("<div class='col-md-12 form-group has-warning'><textarea id=\"newq\" class='form-control' placeholder=\"New question here.\"></textarea></div>\n\
                                    <div class='col-md-12 form-group has-warning'><textarea id=\"newa\" class='form-control' placeholder=\"New answer here.\"></textarea></div>\n\
                                    <button class='btn btn-xs btn-success addnew'>Add FAQ</button><button class='btn btn-xs btn-danger cancel'>Cancel</button>");
                $("#addNewFaq").css("margin-left", "0");
            });
            // cancel editing when Cancel button clicked
            $(document).on("click", ".cancel", function () {
                location.reload(true);
            });
            // update faq via Ajax when Apply change button clicked
            $(document).on("click", ".apply", function () {
                var id = $(this).attr("id");
                var q = $("#q" + id + " textarea").val();
                var a = $("#a" + id + " textarea").val();
                $.ajax({
                    url: "ManageFaq",
                    data: {
                        "id": id,
                        "question": q,
                        "answer": a,
                        "action": "update"
                    },
                    success: function (result) {
                        $("#faqForm" + id).html(result);
                    },
                    error: function () {
                        $("#faqForm" + id).html("<h3>Error updating the FAQ.<h3>");
                    }
                });
            });
            // add new faq via Ajax when Add new Question & Answer button clicked
            $(document).on("click", ".addnew", function () {
                var q = $("#newq").val();
                var a = $("#newa").val();
                $.ajax({
                    url: "ManageFaq",
                    data: {
                        "id": "0",
                        "question": q,
                        "answer": a,
                        "action": "addnew"
                    },
                    success: function (result) {
                        $("#addNewFaq").html("<button class='btn btn-xs btn-success showform'><span class='fa fa-plus-square'>Add new Question & Answer</span></button><br /><br />" + result);
                    },
                    error: function () {
                        $("#addNewFaq").html("<h3>Error adding the FAQ. Make sure Question is not empty.<h3>\n\
                                        <button class=\"btn btn-xs btn-success showform\"><span class=\"fa fa-plus-square\">Add new Question & Answer</span></button><br /><br />");
                    }
                });
            });
            // delete faq via Ajax when Delete Question/Answer button clicked
            $(document).on("click", ".delete", function () {
                var id = $(this).attr("id");
                if (confirm("Are you sure you want to delete?")) {
                    $.ajax({
                        url: "ManageFaq",
                        data: {
                            "id": id,
                            "action": "delete"
                        },
                        success: function (result) {$("#faqForm" + id).html(result);},
                        error: function () {
                            $("#faqForm" + id).html("<h3>Error deleting the FAQ.<h3>");
                        }
                    });
                } return false;
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
        <div class="container" id="faq">
            <div class="row">
                <div class="col-md-8 col-md-offset-2 greybox">
                    <legend class="text-center header">Frequently Asked Questions</legend>
                    <jsp:include page="DisplayFaq" flush="true" />
                </div>
            </div>
        </div>
    </body>
</html>
