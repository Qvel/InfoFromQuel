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
    <link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico"/>" type="image/x-icon">
    <link rel="icon" href="<c:url value="/resources/images/favicon.ico"/>" type="image/x-icon">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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