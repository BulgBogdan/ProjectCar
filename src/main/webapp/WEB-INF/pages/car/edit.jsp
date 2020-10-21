<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Car</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <p>Ваш логин: <sec:authentication property="principal.username" /></p>
    <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
</sec:authorize>

<form:form method="POST" modelAttribute="car">

    <h2>Correct ${car.nameCar}</h2>
    <br>
    <div>
        <form:input type="hidden" path="id" title="${car.id}"></form:input>
    </div>
    <div>
        <form:input type="text" path="nameCar" title="${car.nameCar}"></form:input>
    </div>
    <div>
        <form:input type="number" path="priceCar" title="${car.priceCar}"></form:input>
    </div>
    <div>
        <form:input type="number" path="costRegistration" title="${car.costRegistration}"></form:input>
    </div>
    <button type="submit">edit</button>

</form:form>
<br>
<c:url value="/car/view/${car.id}" var="carView"/>
<a href="${carView}">Back to ${car.nameCar}</a>

</body>
</html>
