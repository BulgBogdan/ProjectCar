<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Document</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
Ваш логин: <sec:authentication property="principal.username"/>
<s></s>
<a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>
</sec:authorize>

<body>
<div>
    <form:form method="POST" modelAttribute="repair">

        <h2>Ремонт:</h2>
        <div>
            <form:input type="text" path="nameRepair" placeholder="Name"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="number" path="beginMileage" placeholder="Mileage start"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="number" path="costsRepair" placeholder="Cost"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="number" path="serviceLife" placeholder="Service Mileage"
                        autofocus="true"></form:input>
        </div>

        <button type="submit">Create</button>

    </form:form>
    <br>
    <c:url value="/car/repairs/${car.id}" var="view"/>
    <a href="${view}">Back</a>
</div>

</body>
</html>
