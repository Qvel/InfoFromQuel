<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Мой Львовский Квартал</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Главные категории <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="dropdown-header">Nav header</li>
                        <li><a href="#">Separated link</a></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Поиск">
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()" >
                    <li><a class="header-links" href="<c:url value="/registration" />"><span class="glyphicon glyphicon-user"></span> Регистрация</a></li>
                    <li><a class="header-links" href="<c:url value="/login" />"><span class="glyphicon glyphicon-log-in"></span> Войти </a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()" >
                    <li><a class="header-links">Привет , <security:authentication property="principal.username" /></a></li>
                    <li><a class="header-links" href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-in"></span> Выйти </a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>