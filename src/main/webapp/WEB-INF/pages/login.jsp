<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
</head>
<body>
    <h1 id="hi">Hello Quel! Talas</h1>



    <div class="form-bottom">
        <form role="form" action="<c:url value="/login" />" method="post" class="login-form">
            <div class="form-group">
                <label class="sr-only" for="j_username">Email</label>
                <input type="text" name="j_username" placeholder="Email..."
                       class="form-username form-control" id="j_username">
            </div>
            <div class="form-group">
                <label class="sr-only" for="j_password">Password</label>
                <input type="password" name="j_password" placeholder="Password..."
                       class="form-password form-control" id="j_password">
            </div>
            <button type="submit" class="btn">Sign in!</button>
            <div class="form-group rememberMe">
                <label class="rememberMeLabel" for="form-remember-me">Remember Me</label>
                <input type="checkbox" checked name="form-rememberMe" class="rememberMeCheckbox"
                       id="form-remember-me">
            </div>
        </form>
    </div>

    <script src="<c:url value = "http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"  type="text/javascript" ></script>
    <script src="<c:url value="/resources/js/login.js"/>"  type="text/javascript" ></script>
</body>
</html>