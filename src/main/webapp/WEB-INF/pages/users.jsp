<%--
  Created by IntelliJ IDEA.
  User: quelz
  Date: 25.10.2017
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <c:forEach items="${users}" var="userForm">
        <h1><c:out value="${userForm.User.id}"/></h1>
        <h1><c:out value="${userForm.User.name}"/></h1>
        <h1><c:out value="${userForm.User.age}"/></h1>
    </c:forEach>
</body>
</html>
