<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>All events for your rest</title>
    <meta name="description" content="Посмотри чем занимаются в твоем городе и присоединяйся к нам!" />
    <%@include file="cssModules.jsp"%>
</head>
<body>

        <form action="<c:url value="/login" />" method="post">
            <input type="text" name="j_username" placeholder="Username" value="" />
            <input type="text" name="j_password" placeholder="Password" value="" />
            <input type="submit" value="Log In" />
        </form>

        <c:if test="${param.error != null}">
            <div id="error">
                <p>You have error with login or password</p>
            </div>
        </c:if>

</body>
</html>