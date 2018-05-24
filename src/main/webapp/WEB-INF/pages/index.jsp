<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en" ng-app="indexPage">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>All events for your rest</title>
    <meta name="description" content="Посмотри чем занимаются в твоем городе и присоединяйся к нам!" />
    <%@include file="include/cssModules.jsp"%>
</head>
<body>

    <%@include file="include/header.jsp" %>

    <%--

    <div class="container">
    <ul ng-controller="ExCtrl">
        <li ng-repeat="user in phones">
            <p>{{user.name}}</p>
            <p>{{user.phone}}</p>
        </li>
    </ul>
</div>
   <div class="header">
           <div class="container">
               <div class="navbar-header">
                       <div class="navbar-brand">
                          <!-- <img src="<c:url value ="/resources/images/logo.jpg" /> " width="50px" height="60px" class="logoHeader left" alt="logo_site"> -->
                           <span class="text-logo">InfoQuel</span>
                       </div>
                   <button type="button" class="navbar-toggle menuButton" data-toggle="collapse" data-target="#responsive-menu">
                       <span class="sr-only"></span>
                       <span class="icon-bar"></span>
                       <span class="icon-bar"></span>
                       <span class="icon-bar"></span>
                   </button>
               </div>
               <ul class="nav navbar-nav">
                   <li><a class="header-links" href="#">Home</a></li>
                   <li><a class="header-links" href="#">Page 1</a></li>
                   <li><a class="header-links" href="#">Page 2</a></li>
               </ul>
               <ul class="nav navbar-nav navbar-right">
                   <sec:authorize access="isAnonymous()" >
                       <li><a class="header-links" href="<c:url value="/registration" />"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                       <li><a class="header-links" href="<c:url value="/login" />"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                   </sec:authorize>
                   <sec:authorize access="isAuthenticated()" >
                       <li>Hi , <security:authentication property="principal.username" /><a class="header-links" href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                   </sec:authorize>
               </ul>
           </div>
   </div>
   <div class="content">
      <%-- <%@include file="include/login.jsp"%>
      </div>--%>




    <%@include file="include/jsFrameworks.jsp"%>
    <script src="<c:url value="/resources/js/login.js"/>"  type="text/javascript" ></script>
    <script src="<c:url value="/resources/js/main.js"/>"  type="text/javascript" ></script>
</body>
</html>
