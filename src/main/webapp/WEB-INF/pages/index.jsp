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
    <div ng-controller="userCtrl">
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>email</th>
            </tr>
            <tr ng-repeat = "user in users">
                <td>{{ user.id }}</td>
                <td>{{ user.name }}</td>
                <td>{{ user.email }}</td>
            </tr>
        </table>
    </div>

    <%@include file="include/footer.jsp"%>

    <script src="<c:url value="/resources/js/main.js"/>"  type="text/javascript" ></script>
</body>
</html>
