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
    <form method="POST" enctype="multipart/form-data" id="fileUploadForm">
        <input type="text" name="login" placeholder="login">
        <input type="email" name="email" placeholder="email">
        <input type="password" name="password" placeholder="password">
        <input type="file" name="file" />
        <button class="RegistrationButton">login</button>
        <button class="Check">check</button>
    </form>



    <script src="<c:url value = "http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js" />"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/registration.js"/>"  type="text/javascript" ></script>
</body>
</html>
