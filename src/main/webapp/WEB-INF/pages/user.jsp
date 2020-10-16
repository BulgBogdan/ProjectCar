<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<h2>Users</h2>
<table>
    <tr>
        <th>id</th>
        <th>login</th>
        <th>email</th>
        <th>firstName</th>
        <th>secondName</th>
        <th>birthday</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.secondName}</td>
            <td>${user.birthday}</td>
        </tr>
    </c:forEach>
</table>
</body>
<h2>SignUp USER</h2>
<c:url value="/" var="signup"/>
<a href="${signup}">SignUp</a>
<br>
<c:url value="/login" var="login"/>
<a href="${login}">LOGIN</a>
</body>
</html>