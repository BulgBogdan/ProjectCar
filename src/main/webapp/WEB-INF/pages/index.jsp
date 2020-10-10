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
        <th>password</th>
        <th>email</th>
        <th>firstName</th>
        <th>secondName</th>
        <th>birthday</th>
        <th>action</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.secondName}</td>
            <td>${user.birthday}</td>
            <td>
                <a href="/edit/${user.id}">edit</a>
                <a href="/delete/${user.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add new user</a>
</body>

<h2>SignUp USER</h2>
<c:url value="/signup" var="signup"/>
<a href="${signup}">SignUp</a>
</body>
</html>
