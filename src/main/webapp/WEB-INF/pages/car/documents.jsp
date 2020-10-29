<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Documents</title>
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

<c:url value="/car/documents/create/${car.id}" var="createDoc"/>

<c:if test="${documents.size() != 0}">

    <h2>Документы:</h2>
    <table>
        <tr>
            <th>Название</th>
            <th>Стоимость</th>
            <th>Дата начала</th>
            <th>Дата окончания</th>
            <th>Срок истекает через</th>
            <th>В месяцах</th>
        </tr>
    <c:forEach items="${documents}" var="docs">

        <tr>
            <td>${docs.nameDocument}</td>
            <td>${docs.documentCost}</td>
            <td>${docs.beginDate}</td>
            <td>${docs.endDate}</td>
            <td>${docs.numberOfDays} дня(ей)</td>
            <td>${docs.numberOfMonth} месяцев</td>
            <td>
                <c:url value="/car/documents/edit/${docs.id}" var="editDoc"/>
                <a href="${editDoc}">edit</a>
            </td>
        </tr>

    </c:forEach>
    </table>
    <br>
    <a href="${createDoc}">Добавить документ</a>
</c:if>

<c:if test="${documents.size() == 0}">
    <c:url value="/car/documents/create/${car.id}" var="createDoc"/>
    <p>Документы небыли добавлены, <a href="${createDoc}">хотите добавить?</a></p>
</c:if>

<br>

<c:url value="/car/view/${car.id}" var="cabinet"/>
<a href="${cabinet}">Вернуться назад</a>
</body>
</html>
