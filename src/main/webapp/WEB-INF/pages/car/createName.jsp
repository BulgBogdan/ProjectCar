<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Car</title>
</head>
<h1>Создать машинку</h1>

<sec:authorize access="isAuthenticated()">
    <p>Ваш логин: <sec:authentication property="principal.username"/></p>
    <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
</sec:authorize>

<body>
<div>
    <form:form method="POST" modelAttribute="newCar">

        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="nameCar" placeholder="Car Name"
                        autofocus="true"></form:input>
        </div>

        <div>
            <form:input type="double" path="priceCar" placeholder="Price car"
                        autofocus="true"></form:input>
        </div>

        <div>
            <form:input type="double" path="costRegistration" placeholder="Registration cost"
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
