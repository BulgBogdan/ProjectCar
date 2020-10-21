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

        <h2>Параметры:</h2>
        <div>
            <form:input type="text" path="mark" placeholder="Mark Auto"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="model" placeholder="Model Auto"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="date" path="year" placeholder="Year"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="number" path="mileage" placeholder="Mileage"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="number" path="mass" placeholder="Mass Auto"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="color" placeholder="Color Auto"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="double" path="averageRate" placeholder="Average Rate"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="vin" placeholder="VIN Code"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="registrationNumber" placeholder="Registration Number"
                        autofocus="true"></form:input>
        </div>

        <button type="submit">Create</button>

    </form:form>
    <br>
    <c:url value="/personalCabinet" var="cabinet"/>
    <a href="${cabinet}">Back</a>
</div>

</body>
</html>
