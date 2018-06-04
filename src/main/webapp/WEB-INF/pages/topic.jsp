<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head ng-app="indexPage">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Мой Львовский Квартал</title>
    <meta name="description" content="Посмотри чем занимаются в твоем квартале" />
    <%@include file="include/cssModules.jsp"%>
</head>
<body>
    <%@include file="include/header.jsp" %>
    <div class="container">
        <div class="well">
            <div class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" src="http://localhost:8080/InfoQuel/getLogo?fileName=${Topic.user.logo}" width="150px" height="150px">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">${Topic.title}</h4>
                    <p class="text-right"> Автор : ${Topic.user.name}</p>
                    <p>${Topic.body}</p>
                    <ul class="list-inline list-unstyled">
                        <li><span><i class="glyphicon glyphicon-calendar"></i> ${Topic.date} </span></li>
                        <div class="pull-right"><span class="label label-default">alice</span> <span class="label label-primary">story</span> <span class="label label-success">blog</span> <span class="label label-info">personal</span> <span class="label label-warning">Warning</span>
                            <span class="label label-danger">Danger</span></div>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <%@include file="include/footer.jsp"%>
</body>
</html>
