<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parameter Car</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
<p>Ваш логин: <sec:authentication property="principal.username"/></p>
<p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
</sec:authorize>

<body>
<div>
    <form:form method="POST" modelAttribute="parameter">
        <h2>Изменить Параметры</h2>
        <div>
            <form:input type="text" path="mark" title="${parameter.mark}"></form:input>
        </div>
        <div>
            <form:input type="text" path="model" title="${parameter.model}"></form:input>
        </div>
        <div>
            <form:input type="date" path="year" title="${parameter.year}"></form:input>
        </div>
        <div>
            <form:input type="number" path="mileage" title="${parameter.mileage}"></form:input>
        </div>
        <div>
            <form:input type="number" path="mass" title="${parameter.mass}"></form:input>
        </div>
        <div>
            <form:input type="text" path="color" title="${parameter.color}"></form:input>
        </div>
        <div>
            <form:input type="double" path="averageRate" title="${parameter.averageRate}"></form:input>
        </div>
        <div>
            <form:input type="text" path="vin" title="${parameter.vin}"></form:input>
        </div>
        <div>
            <form:input type="text" path="registrationNumber" title="${parameter.registrationNumber}"></form:input>
        </div>
        <button type="submit">Create</button>

    </form:form>
    <br>
    <c:url value="/car/view/${parameter.car.id}" var="carView"/>
    <a href="${carView}">Back to ${parameter.car.nameCar}</a>

</body>
</html>
