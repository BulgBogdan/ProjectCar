<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ремонт</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
Ваш логин:
    <sec:authentication property="principal.username"/>
<s></s>
<a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>
</sec:authorize>

<body>
<div>
    <form:form method="POST" modelAttribute="repair">

    <h2>Изменить Ремонтную Работу</h2>
    <div>
        <form:input type="text" path="nameRepair" title="${repair.nameRepair}"></form:input>
    </div>
    <div>
        <form:input type="number" path="beginMileage" title="${repair.beginMileage}"></form:input>
    </div>
    <div>
        <form:input type="number" path="costsRepair" title="${repair.costsRepair}"></form:input>
    </div>
    <div>
        <form:input type="number" path="serviceLife" title="${repair.serviceLife}"></form:input>
    </div>

    <button type="submit">Create</button>

    </form:form>
    <br>
    <c:url value="/car/view/${repair.car.id}" var="carView"/>
    <a href="${carView}">Back to ${repair.car.nameCar}</a>

</body>
</html>
