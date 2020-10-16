<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>myPage</title>
</head>
<body>

<h2>PersonalCabinet</h2>
<c:forEach var="user" items="${userList}">

<h1></h1>
<table>
    <tr>
        <th>login</th>
        <th>email</th>
        <th>firstName</th>
        <th>secondName</th>
        <th>birthday</th>
    </tr>
        <tr>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.secondName}</td>
            <td>${user.birthday}</td>
        </tr>
</table>
    <br>
    <h1>${user.firstName} select your car</h1>
    <c:url value="/cars" var="cars"/>
<a href="${user.cars}" var="cars"/>
</c:forEach>
</body>
<h2>SignUp USER</h2>
<c:url value="/" var="signup"/>
<a href="${signup}">SignUp</a>
<br>
<c:url value="/login" var="login"/>
<a href="${login}">LOGIN</a>
</body>
</html>


