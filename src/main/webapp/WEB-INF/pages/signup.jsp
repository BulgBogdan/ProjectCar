<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
    <link href="<c:url value="/res/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value="/res/js/bootstrap.js" />"></script>
    <style>body {
        background-color: #f6fcff
    }</style>
</head>

<sec:authorize access="!isAuthenticated()">
    <p><a class="btn btn-xs btn-success" href="<c:url value="/login" />" role="button">Login</a></p>
</sec:authorize>

<body>
<div>

    <div class="container">
        <div id="singUp-row" class="row justify-content-center align-items-center">
            <div id="singUp-column" class="col-md-6">
                <div id="singUp-box" class="col-md-12">

                    <form:form method="POST" modelAttribute="user">
                        <h3 class="text-center text-info">Registration</h3>
                        <div class="form-group">
                            <label for="login" class="text-info">Username:</label><br>
                            <input type="text" name="login" id="login" class="form-control">
                                ${loginError}
                                ${message}
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">password:</label><br>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword" class="text-info">confirmPassword:</label><br>
                            <input type="password" name="confirmPassword" id="confirmPassword" class="form-control">
                                ${passwordError}
                                ${message}
                        </div>
                        <div class="form-group">
                            <label for="email" class="text-info">email:</label><br>
                            <input type="email" name="email" id="email" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="firstName" class="text-info">firstName:</label><br>
                            <input type="text" name="firstName" id="firstName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="secondName" class="text-info">secondName:</label><br>
                            <input type="text" name="secondName" id="secondName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="birthday" class="text-info">birthday:</label><br>
                            <input type="date" name="birthday" id="birthday" class="form-control">
                        </div>

                        <div class="form-group">
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="Registration">
                                ${Errors}
                        </div>
                        <div id="register-link" class="text-right">
                            <a href="/" class="text-info">My cabinet</a>

                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
