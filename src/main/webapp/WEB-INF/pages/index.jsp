<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
</head>
<body>
 <h1 id="hi">Hello Quel!</h1>
 <a href="/InfoQuel/login">LOG IN</a>



 <script src="<c:url value = "http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js" />"></script>
 <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"  type="text/javascript" ></script>
 <script src="<c:url value="/resources/js/login.js"/>"  type="text/javascript" ></script>
</body>
</html>
