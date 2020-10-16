<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>myPage</title>
</head>
<body>

<h2>PersonalCabinet</h2>
<c:forEach var="user" items="${user}">

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
    <br>
    <h1>${user.firstName} select your car</h1>
    <c:url value="/carView" var="cars"/>
    <a href="${carView}">Open car</a>
    <br>
    <br>
    <h1>add new car</h1>
    <c:url value="/createCar" var="cars"/>
    <a href="${createCar}">Create car</a>
</c:forEach>
</body>
</html>
