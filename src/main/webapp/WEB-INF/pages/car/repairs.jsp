<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Repairs</title>
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

<c:url value="/car/repairs/create/${car.id}" var="createRepair"/>
<c:if test="${repairs.size() != 0}">
    <h2>Ремонты:</h2>
    <table>
        <tr>
            <th>Название</th>
            <th>Пробег при поломке</th>
            <th>Цена ремонта и(или) запчастей</th>
            <th>Срок износа запчасти по регламенту(км)</th>
            <th>Срок истекает</th>
        </tr>

        <c:forEach items="${repairs}" var="repair">
            <tr>
                <td>${repair.nameRepair}</td>
                <td>${repair.beginMileage}</td>
                <td>${repair.costsRepair}</td>
                <td>${repair.serviceLife} км</td>
                <td>${repair.endMileage} км</td>
                <td>
                    <c:url value="/car/repairs/edit/${repair.id}" var="editDoc"/>
                    <a href="${editDoc}">edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${createRepair}">Добавить произведенный ремонт</a>
</c:if>

<c:if test="${repairs.size() == 0}">
    <p>В вашем каталоге нет произведенных ремонтных работ, <a href="${createRepair}">хотите добавить?</a></p>
</c:if>

<br>

<c:url value="/car/view/${car.id}" var="cabinet"/>
<a href="${cabinet}">Вернуться назад</a>
</body>
</html>
