<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<sec:authorize access="!isAuthenticated()">
    <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
</sec:authorize>

<body>
<div>
    <form:form method="POST" modelAttribute="user">

        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="login" placeholder="Username"
                        autofocus="true"></form:input>
            <form:errors path="login"></form:errors>
                ${loginError}
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"></form:input>
        </div>
        <div>
            <form:input type="password" path="confirmPassword"
                        placeholder="Confirm your password"></form:input>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
        <div>
            <form:input type="text" path="email" placeholder="email"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="firstName" placeholder="firstName"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="secondName" placeholder="secondName"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="date"  path="birthday"></form:input>
        </div>
        <button type="submit">Зарегистрироваться</button>
        ${Errors}
    </form:form>
    <br>
    <c:url value="/user" var="user"/>
    <a href="${user}">Главная</a>
</div>
</body>
</html>
