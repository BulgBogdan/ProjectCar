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

<h2>${car.nameCar}</h2>

<p>Параметры авто:</p>
<form:form modelAttribute="parameters">
    <c:if test="${parameters!=null}">
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
                <td>${parameters.mark}</td>
                <td>${parameters.model}</td>
                <td>${parameters.year}</td>
                <td>${parameters.mileage}</td>
                <td>${parameters.mass}</td>
                <td>${parameters.color}</td>
                <td>${parameters.averageRate}</td>
                <td>${parameters.vin}</td>
                <td>${parameters.registrationNumber}</td>
                <td>
                    <c:url value="/car/parameters/edit/${car.id}" var="editParameter"/>
                    <a href="${editParameter}">edit</a>
                </td>
            </tr>
        </table>
    </c:if>
    <c:if test="${parameters==null}">
        <c:url value="/car/parameters/${car.id}" var="createParameter"/>
        <p>Параметры небыли добавлены, <a href="${createParameter}">хотите добавить?</a></p>
    </c:if>
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
                    <%--<td>${fuel.fuelDistance}</td>--%>

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