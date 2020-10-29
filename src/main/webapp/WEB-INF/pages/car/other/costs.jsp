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

<c:url value="/car/other/costs/create/${car.id}" var="createCost"/>
<c:if test="${otherCosts.size() != 0}">
    <h2>Различные затраты на авто:</h2>
    <table>
        <tr>
            <th>Название</th>
            <th>Дата</th>
            <th>Стоимость</th>
        </tr>

        <c:forEach items="${otherCosts}" var="costs">
            <tr>
                <td>${costs.nameOtherCost}</td>
                <td>${costs.dateCost}</td>
                <td>${costs.cost}</td>

                <td>
                    <c:url value="/car/other/costs/edit/${costs.id}" var="editCost"/>
                    <a href="${editCost}">edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${createCost}">Добавить затрату</a>
</c:if>

<c:if test="${otherCosts.size() == 0}">
    <p>В вашем каталоге нет затрат, <a href="${createCost}">хотите добавить?</a></p>
</c:if>

<br>

<c:url value="/car/view/${car.id}" var="cabinet"/>
<a href="${cabinet}">Вернуться назад</a>
</body>
</html>