<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/res/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value="/res/js/bootstrap.js" />"></script>
    <style>body {
        background-color: #d9eeff
    }</style>
</head>
<body>
<h2 align="center">Ошибка аутентификации. Неверно введен логин или пароль.</h2>
<div>
    &nbsp;
</div>
<div id="register-link" class="text-center">
    <a href="/login" class="text-info">Попробовать снова</a>
</div>
</body>
</html>
