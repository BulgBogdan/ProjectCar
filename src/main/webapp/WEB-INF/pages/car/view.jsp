<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyCar</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <p>Ваш логин: <sec:authentication property="principal.username"/></p>
    <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
</sec:authorize>

<h2>PersonalCabinet</h2>
<form:form modelAttribute="car" >
<table>
    <tr>
        <th>id</th>
        <th>Цена авто при покупке</th>
        <th>Затраты при регистрации</th>
    </tr>
        <tr>
            <td>${car.id}</td>
            <td>${car.priceCar}</td>
            <td>${car.costRegistration}</td>
            <td>
                <c:url value="/car/edit/${car.id}" var="editCar"/>
                <a href="${editCar}">edit</a>
            </td>
        </tr>
</table>
</form:form>
<c:url value="/personalCabinet" var="cabinet"/>
<a href="${cabinet}">My cabinet</a>
</body>
</html>
