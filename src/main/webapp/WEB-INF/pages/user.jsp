<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="indexPage">
<head>
    <title>Личный кабинет</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="include/cssModules.jsp"%>
</head>
<body>
    <%@include file="include/header.jsp"%>

    <div class="container">
        <div class="row my-2">
            <div class="col-lg-8 order-lg-2">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a href="" data-target="#profile" data-toggle="tab" class="nav-link active">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a href="" data-target="#messages" data-toggle="tab" class="nav-link">Messages</a>
                    </li>
                    <li class="nav-item">
                        <a href="" data-target="#edit" data-toggle="tab" class="nav-link">Edit</a>
                    </li>
                </ul>
                <div class="tab-content py-4">
                    <div class="tab-pane active" id="profile">
                        <h5 class="mb-3">User Profile</h5>
                        <div class="row">
                            <div class="col-md-6">
                                <h6>About</h6>
                                <p>
                                    ${User.name}
                                    ${User.email}
                                </p>
                                <h6>Hobbies</h6>
                                <p>
                                    Indie music, skiing and hiking. I love the great outdoors.
                                </p>
                            </div>
                            <div class="col-md-6">
                                <h6>Recent badges</h6>
                                <a href="#" class="badge badge-dark badge-pill">html5</a>
                                <a href="#" class="badge badge-dark badge-pill">react</a>
                                <a href="#" class="badge badge-dark badge-pill">codeply</a>
                                <a href="#" class="badge badge-dark badge-pill">angularjs</a>
                                <a href="#" class="badge badge-dark badge-pill">css3</a>
                                <a href="#" class="badge badge-dark badge-pill">jquery</a>
                                <a href="#" class="badge badge-dark badge-pill">bootstrap</a>
                                <a href="#" class="badge badge-dark badge-pill">responsive-design</a>
                                <hr>
                                <span class="badge badge-primary"><i class="fa fa-user"></i> 900 Followers</span>
                                <span class="badge badge-success"><i class="fa fa-cog"></i> 43 Forks</span>
                                <span class="badge badge-danger"><i class="fa fa-eye"></i> 245 Views</span>
                            </div>
                            <div class="col-md-12">
                                <h5 class="mt-2"><span class="fa fa-clock-o ion-clock float-right"></span> Recent Activity</h5>
                                <table class="table table-sm table-hover table-striped">
                                    <tbody>
                                    <tr>
                                        <td>
                                            <strong>Abby</strong> joined ACME Project Team in <strong>`Collaboration`</strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>Gary</strong> deleted My Board1 in <strong>`Discussions`</strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>Kensington</strong> deleted MyBoard3 in <strong>`Discussions`</strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>John</strong> deleted My Board1 in <strong>`Discussions`</strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>Skell</strong> deleted his post Look at Why this is.. in <strong>`Discussions`</strong>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!--/row-->
                    </div>
                    <div class="tab-pane" id="messages">
                        <div class="alert alert-info alert-dismissable">
                            <a class="panel-close close" data-dismiss="alert">×</a> This is an <strong>.alert</strong>. Use this to show important messages to the user.
                        </div>
                        <table class="table table-hover table-striped">
                            <tbody>
                            <tr>
                                <td>
                                    <span class="float-right font-weight-bold">3 hrs ago</span> Here is your a link to the latest summary report from the..
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="float-right font-weight-bold">Yesterday</span> There has been a request on your account since that was..
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="float-right font-weight-bold">9/10</span> Porttitor vitae ultrices quis, dapibus id dolor. Morbi venenatis lacinia rhoncus.
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="float-right font-weight-bold">9/4</span> Vestibulum tincidunt ullamcorper eros eget luctus.
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="float-right font-weight-bold">9/4</span> Maxamillion ais the fix for tibulum tincidunt ullamcorper eros.
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane" id="edit">
                        <form role="form" id="edit_user_form" ng-controller="updateUserCtrl">
                            <div class="form-group row">
                                <label class="col-lg-3 col-form-label form-control-label" for="user_name" >Имя </label>
                                <div class="col-lg-9">
                                    <input class="form-control" type="text" id="user_name" placeholder="Новое имя" ng-model="user_name">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-lg-3 col-form-label form-control-label" for="user_email">Почта </label>
                                <div class="col-lg-9">
                                    <input class="form-control" type="email" id="user_email" placeholder="Новая почта"  ng-model="user_email">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-lg-3 col-form-label form-control-label" for="user_password" >Пароль</label>
                                <div class="col-lg-9">
                                    <input class="form-control" type="password" id="user_password" placeholder="Новый пароль" ng-model="user_password">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-lg-3 col-form-label form-control-label" for="confirm_password">Подтвердите пароль</label>
                                <div class="col-lg-9">
                                    <input class="form-control" type="password" id="confirm_password" >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-lg-3 col-form-label form-control-label"></label>
                                <div class="col-lg-9">
                                    <input type="reset" class="btn btn-secondary" value="Отменить">
                                    <input type="button" class="btn btn-primary" value="Сохранить изменения" ng-click="updateUser(user_email,user_name,user_password)">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 order-lg-1 text-center" ng-controller="avatarCtrl">
                <img id="user_logo" src="http://localhost:8080/InfoQuel/user/getLogo?fileName=${User.logo}" class="mx-auto img-fluid img-circle d-block" alt="avatar" width="150px" height="150px">
                <h6 class="mt-2">Ваш Аватар</h6>
                <label class="btn btn-default">
                    Изменить <input type="file" id="file" class="custom-file-input" custom-on-change="updateAvatarFront" hidden>
                </label>
                <button id="save_user_avatar" class="btn btn-success" ng-click="updateAvatarBack()">Сохранить</button>
            </div>
        </div>
    </div>

    <%@include file="include/footer.jsp"%>
</body>
</html>
