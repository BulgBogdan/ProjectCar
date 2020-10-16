<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>
<div>
    <form method="POST" action="${contextPath}/login">
        <h2>Вход в систему</h2>
        <div>
            <input name="login" type="text" placeholder="Login"
                   autofocus="true"/>
            <input name="password" type="password" placeholder="Password"/>
            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <button type="submit">Log In</button>
            <h4><a href="/">Зарегистрироваться</a></h4>
        </div>
    </form>
</div>

</body>
</html>