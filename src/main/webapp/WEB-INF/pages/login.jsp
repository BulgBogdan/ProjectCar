<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>
<div>
    <c:url value="/login-check" var="loginUrl" />
    <form method="post" action="${loginUrl}">
        <h2>Вход в систему</h2>
        <div>
            <input name="check_username" type="text" placeholder="Login"
                   autofocus="true"/>
            <input name="check_password" type="password" placeholder="Password"/>
            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <button type="submit">Log In</button>
            <h4><a href="/">Зарегистрироваться</a></h4>
        </div>
    </form>
</div>

</body>
</html>