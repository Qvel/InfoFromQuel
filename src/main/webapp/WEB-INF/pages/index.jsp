<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en" ng-app="indexPage">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Мой Львовский Квартал</title>
    <meta name="description" content="Посмотри чем занимаются в твоем квартале" />
    <%@include file="include/cssModules.jsp"%>
</head>
<body>
    <%@include file="include/header.jsp" %>
    <div class="container" ng-controller="findAllPostCtrl">
        <div data-ng-init="getAllPosts()">
        <div class="well"  ng-repeat="topic in topics">
            <div class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" ng-src="http://localhost:8080/InfoQuel/getLogo?fileName={{topic.user.logo}}" width="150px" height="150px">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">{{topic.title}}</h4>
                    <p class="text-right"> Автор : {{topic.user.name}}</p>
                    <p>{{topic.body}}</p>
                    <ul class="list-inline list-unstyled">
                        <li><span><i class="glyphicon glyphicon-calendar"></i> {{topic.date}} </span></li>
                        <li>|</li>
                        <span><i class="glyphicon glyphicon-comment"></i> 2 comments</span>
                        <li>|</li>
                        <li>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                        </li>
                        <li>|</li>
                        <li>
                            <span><i class="fa fa-facebook-square"></i></span>
                            <span><i class="fa fa-twitter-square"></i></span>
                            <span><i class="fa fa-google-plus-square"></i></span>
                        </li>
                        <div class="pull-right"><span class="label label-default">alice</span> <span class="label label-primary">story</span> <span class="label label-success">blog</span> <span class="label label-info">personal</span> <span class="label label-warning">Warning</span>
                            <span class="label label-danger">Danger</span></div>
                    </ul>
                </div>
            </div>
        </div>
        </div>
    </div>
    <%@include file="include/footer.jsp"%>
</body>
</html>
