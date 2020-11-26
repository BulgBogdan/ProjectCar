<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link href="<c:url value="/res/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value="/res/js/bootstrap.js" />"></script>
    <style>body{background-color: #f6fcff}</style>
</head>

<body>
<c:url value="/login-check" var="loginUrl"/>
<div id="login">

    <h3 class="text-center text-white pt-5"></h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">

                    <form id="login-form" class="form" method="post" action="${loginUrl}">
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <h3 class="text-center text-info">Login</h3>
                        <div class="form-group">
                            <label for="username" class="text-info">Username:</label><br>
                            <input type="text" name="check_username" id="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Password:</label><br>
                            <input type="password" name="check_password" id="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <%--<label for="remember-me" class="text-info"><span>Remember me</span> --%>
                                <%--<span><input id="remember-me" name="remember-me" type="checkbox"></span>--%>
                            <%--</label><br>--%>
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                        </div>
                        <div id="register-link" class="text-right">
                            <a href="/registration" class="text-info">Registration</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>