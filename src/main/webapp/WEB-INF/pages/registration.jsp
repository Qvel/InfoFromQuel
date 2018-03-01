<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>All events for your rest</title>
    <meta name="description" content="Посмотри чем занимаются в твоем городе и присоединяйся к нам!" />
    <link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico"/>" type="image/x-icon">
    <link rel="icon" href="<c:url value="/resources/images/favicon.ico"/>" type="image/x-icon">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <h1>Registration</h1>
    <div class="RegistrationForm">
        <input type="text" class="UserName" placeholder="login">
        <input type="email" class="UserEmail" placeholder="email">
        <input type="password" class="UserPassword" placeholder="password">
        <input type="password" class="RetryPassword" placeholder="repeat password">
        <button class="RegistrationButton">login</button>
    </div>



    <script src="<c:url value = "http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js" />"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/registration.js"/>"  type="text/javascript" ></script>
</body>
</html>
