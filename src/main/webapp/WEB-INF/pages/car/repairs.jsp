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

<c:url value="/car/repairs/create/${car.id}" var="createRepair"/>
<c:if test="${repairs.size() != 0}">
    <h2>Ремонты:</h2>
    <table>
        <tr>
            <th>Название</th>
            <th>Пробег при поломке</th>
            <th>Срок службы по регламенту(км)</th>
            <th>Цена ремонта и(или) запчастей</th>
            <th>Срок истекает по регламенту</th>
        </tr>

        <c:forEach items="${repairs}" var="repair">
            <tr>
                <td>${repair.nameRepair}</td>
                <td>${repair.beginMileage}</td>
                <td>${repair.endMileage}</td>
                <td>${repair.costsRepair}</td>
                    <%--<td>${docs.numberOf} дня(ей)</td>--%>
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
