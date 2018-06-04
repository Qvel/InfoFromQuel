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
            <a class="navbar-brand" href="<c:url value="/" />">Мой Львовский Квартал</a>
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
            <form class="navbar-form navbar-left" ng-controller="searchCtrl" ng-submit="redirectToSearchPage()">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Поиск" ng-model="title" >
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()" >
                    <li><a class="header-links" data-toggle="modal" data-target="#registration" ><span class="glyphicon glyphicon-user"></span> Регистрация</a></li>
                    <li><a class="header-links" data-toggle="modal" data-target="#logIn"><span class="glyphicon glyphicon-log-in"></span> Войти </a></li>
                    <c:if test="${param.error != null}">
                        <li><a class="header-links">Логин или пароль не верные</a></li>
                    </c:if>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()" >
                    <li ng-controller="userCtrl">
                        <a class="header-links" user_email="<security:authentication property="principal.username"/>" id="user_email" >
                            Привет , <security:authentication property="principal.username" />

                        </a>
                    </li>
                    <li><a class="header-links" href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-in"></span> Выйти </a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>

<!-- Registration Modal -->
<div id="registration" class="modal fade" role="dialog">
    <div class="modal-dialog modal-sm">
        <!-- Modal content-->
        <div class="modal-content" ng-controller="registrationCtrl">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Регистрация</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="reg_email">Почта:</label>
                    <input type="email" class="form-control" id="reg_email" ng-model="userEmail">
                </div>
                <div class="form-group">
                    <label for="reg_log">Имя:</label>
                    <input type="text" class="form-control" id="reg_log" ng-model="userName">
                </div>
                <div class="form-group">
                    <label for="reg_pwd">Пароль:</label>
                    <input type="password" class="form-control" id="reg_pwd" ng-model="userPassword">
                </div>
                <div class="form-group">
                    <label for="reg_rep_pw">Повторить пароль:</label>
                    <input type="password" class="form-control" id="reg_rep_pw">
                </div>
                <div class="checkbox">
                    <label><input type="checkbox">Пользовательское соглашение</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                <button type="button" class="btn btn-success" ng-click="registration(userEmail,userName,userPassword)">Зарегистрироваться</button>
            </div>
        </div>
    </div>
</div>
<!-- Log in Modal -->
<div id="logIn" class="modal fade" role="dialog">
    <div class="modal-dialog modal-sm">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Войти как :</h4>
            </div>
            <form action="<c:url value="/login" />" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="log_email">Почта:</label>
                        <input type="email" name="j_username" class="form-control" id="log_email" required />
                    </div>
                    <div class="form-group">
                        <label for="log_pw">Пароль:</label>
                        <input type="password" name="j_password" class="form-control" id="log_pw"  required />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <input type="submit" class="btn btn-success" value="Войти" />
                </div>
            </form>
        </div>
    </div>
</div>
