<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<c:url value="/edit" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${user.id}">
    <label for="login">Login</label>
    <input type="text" name="login" id="login">
    <label for="password">Password</label>
    <input type="text" name="password" id="password">
    <label for="email">Email</label>
    <input type="text" name="email" id="email">
    <label for="firstName">firstName</label>
    <input type="text" name="firstName" id="firstName">
    <label for="secondName">secondName</label>
    <input type="text" name="secondName" id="secondName">
    <label for="birthday">birthday</label>
    <input type="text" name="birthday" id="birthday">
    <input type="submit" value="Edit user">
</form>
</body>
</html>
