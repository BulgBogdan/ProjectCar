<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fuel</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
    Ваш логин: <sec:authentication property="principal.username"/>
    <s></s>
    <a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>
</sec:authorize>

<form:form modelAttribute="car">
    <p>${car.nameCar}</p>
    <p>Незабывайте редактировать пробег, чтобы корректно производились расчеты:</p>
    <p>${car.mileage} км</p>
    <c:url value="/car/edit/${car.id}" var="editCar"/>
    <a href="${editCar}">edit</a>
</form:form>

<c:url value="/car/fuel/create/${car.id}" var="createFuel"/>
<c:if test="${fuel.size() != 0}">
    <h2>Заправка топлива:</h2>
    <table>
        <tr>
            <th>Цена за литр</th>
            <th>Заправленно литров</th>
            <th>На какую сумму заправленно</th>
            <th>Средний запас хода по регламенту</th>
        </tr>

        <c:forEach items="${fuel}" var="fuels">
            <tr>
                <td>${fuels.literCost}</td>
                <td>${fuels.literValue}</td>
                <td>${fuels.summ}</td>
                <td>${fuels.fuelDistance}</td>

                <td>
                    <c:url value="/car/fuel/edit/${fuels.id}" var="editFuel"/>
                    <a href="${editFuel}">edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${createFuel}">Добавить заправку топлива</a>
</c:if>

<c:if test="${fuel.size() == 0}">
    <p>В вашем каталоге нет заправок топливом, <a href="${createFuel}">хотите добавить?</a></p>
</c:if>

<br>

<c:url value="/car/view/${car.id}" var="cabinet"/>
<a href="${cabinet}">Вернуться назад</a>
</body>
</html>