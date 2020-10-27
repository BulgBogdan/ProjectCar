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
                <td>${parameters.firstMileage}</td>
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
        </tr>
    <c:forEach items="${documents}" var="docs">

        <tr>
            <td>${docs.nameDocument}</td>
            <td>${docs.documentCost}</td>
            <td>${docs.beginDate}</td>
            <td>${docs.endDate}</td>
            <td>${docs.numberOfDays} дня(ей)</td>
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
