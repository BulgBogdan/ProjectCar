<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyCar</title>
    <link href="../../../res/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="../../../res/js/bootstrap.bundle.min.js"></script>
    <script src="../../../res/jquery/jquery-3.2.1.min.js"></script>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    Ваш логин: <sec:authentication property="principal.username"/>
    <s></s>
    <a class="btn btn-sm btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>
</sec:authorize>

<form:form modelAttribute="car">
    <p>${car.nameCar}</p>
    <p>Незабывайте редактировать пробег, чтобы корректно производились расчеты:</p>
    <p>${car.mileage} км</p>
    <c:url value="/car/edit/${car.id}" var="editCar"/>
    <a href="${editCar}" class="btn btn-sm btn-info">
        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd" d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
    </svg></a>

</form:form>

<h2>Цена автомобиля и затраты на оформление</h2>

<form:form modelAttribute="registration">
<c:if test="${registration!=null}">
    <table>
        <tr>
            <th>Цена Авто</th>
            <th>Оформление</th>
        </tr>
        <tr>
            <td>${registration.priceCar} $</td>
            <td>${registration.priceRegistration} $</td>
            <td>
                <c:url value="/car/costs/edit/${car.id}" var="editFirst"/>
                <a href="${editFirst}">edit</a>
            </td>
        </tr>
    </table>
</c:if>

<c:if test="${registration==null}">
    <c:url value="/car/costs/first/${car.id}" var="createFirst"/>
    <p>Отсутствуют, <a href="${createFirst}">хотите добавить?</a></p>
</c:if>
</form:form>
<h2>Параметры вашего авто:</h2>

<form:form modelAttribute="parameter">
    <c:if test="${parameter!=null}">
        <table>
            <tr>
                <th>Марка Авто</th>
                <th>Модель Авто</th>
                <th>Год выпуска</th>
                <th>Пробег при покупке</th>
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
                <td>${parameter.firstMileage} km</td>
                <td>${parameter.mass} kg</td>
                <td>${parameter.color}</td>
                <td>${parameter.averageRate} km/h</td>
                <td>${parameter.vin}</td>
                <td>${parameter.registrationNumber}</td>
                <td>
                    <c:url value="/car/parameters/edit/${car.id}" var="editParameter"/>
                    <a href="${editParameter}">edit</a>
                </td>
            </tr>
        </table>
    </c:if>

    <c:if test="${parameter==null}">
        <c:url value="/car/parameters/${car.id}" var="createParameter"/>
        <p>Параметры небыли добавлены, <a href="${createParameter}">хотите добавить?</a></p>
    </c:if>

</form:form>

<h2>Выбирете, чтобы открыть:</h2>

<table>
    <tr>
        <td><c:url value="/car/documents/${car.id}" var="documents"/>
            <a href="${documents}">Документы</a></td>
    </tr>
    <tr>
        <td><c:url value="/car/repairs/${car.id}" var="repairs"/>
            <a href="${repairs}">Ремонт</a></td>
    </tr>
    <tr>
        <td><c:url value="/car/fuel/${car.id}" var="fuel"/>
            <a href="${fuel}">Топливо</a></td>
    </tr>
    <tr>
        <td><c:url value="/car/other/costs/${car.id}" var="costs"/>
            <a href="${costs}">Инные расходы</a></td>
    </tr>
</table>

<br>
<c:url value="/" var="cabinet"/>
<a href="${cabinet}">My cabinet</a>
</body>
</html>