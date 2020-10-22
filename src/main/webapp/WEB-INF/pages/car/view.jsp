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

<h2>${car.nameCar}</h2>
<form:form modelAttribute="car">
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

    <h2>Parameter auto</h2>
<form:form modelAttribute="parameter">
    <c:if test="${parameter!=null}">
        <table>
            <tr>
                <th>Марка Авто</th>
                <th>Модель Авто</th>
                <th>Год выпуска</th>
                <th>Пробег</th>
                <th>Масса Авто</th>
                <th>Цвет Авто</th>
                <th>Средний расход</th>
                <th>VIN</th>
                <th>Регистрационный знак</th>
            </tr>
            <tr>
                <td>${parameter.mark}</td>
                <td>${parameter.model}</td>
                <td>${parameter.year}</td>
                <td>${parameter.mileage}</td>
                <td>${parameter.mass}</td>
                <td>${parameter.color}</td>
                <td>${parameter.averageRate}</td>
                <td>${parameter.vin}</td>
                <td>${parameter.registrationNumber}</td>
                <td>
                    <c:url value="/car/editParameter/${car.id}" var="editParameter"/>
                    <a href="${editParameter}">edit</a>
                </td>
            </tr>
        </table>
    </c:if>
    <c:if test="${parameter==null}">
        <c:url value="/car/parameter/${car.id}" var="createParameter"/>
        <p>Параметры небыли добавлены, <a href="${createParameter}">хотите добавить?</a></p>
    </c:if>
</form:form>

<c:url value="/personalCabinet" var="cabinet"/>
<a href="${cabinet}">My cabinet</a>
</body>
</html>
